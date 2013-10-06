package am.projects.ajf.monitor.business;

import am.projects.ajf.monitor.Constants;
import am.projects.ajf.monitor.listeners.MongoListener;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * User: mlecoutre
 * Date: 13/09/13
 * Time: 10:59
 */
public class MonitorPolicy implements Constants {

    private static MonitorPolicy instance = null;
    private static final Logger logger = LoggerFactory.getLogger(MonitorPolicy.class);


    public static MonitorPolicy getInstance() {
        if (instance == null) {
            instance = new MonitorPolicy();
        }
        return instance;
    }

    private MonitorPolicy() {

    }

    /**
     * Get distinct names of service, ut or function
     *
     * @param applicationName Mongo Collection to request
     * @throws Exception TODO manage cache or local file storage to avoid to call DB for each request.
     */
    public Collection<String> requestDistinctByServiceType(String applicationName, int serviceType, String filterName, String filterValue) throws Exception {
        String fieldName = giveFieldName(serviceType);

        DB db = MongoListener.getMongoDB();
        DBCollection coll = db.getCollection(applicationName);
        BasicDBObject filter = null;
        if (filterName != null)
            filter = new BasicDBObject(filterName, filterValue);

        try {
            Collection<String> elements = coll.distinct(fieldName, filter);
            elements.remove(null);
            elements.remove("");
            return elements;

        } catch (Exception e) {
            logger.error("Request failed", e);

        }
        return null;
    }

    private String giveFieldName(int serviceType) {
        String fieldName = null;
        switch (serviceType) {
            case SRV_TYPE_FUNCTION:
                fieldName = FIELD_FUNCTION;
                break;
            case SRV_TYPE_UT:
                fieldName = FIELD_UT;
                break;
            case SRV_TYPE_SERVICE:
                fieldName = FIELD_SERVICE;
                break;
        }
        return fieldName;
    }


    public long batchInsert(InputStream is, String applicationName) throws IOException {
        long nbElts = 0;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            DB db = MongoListener.getMongoDB();
            DBCollection collection = db.getCollection(applicationName);
            //Create index  if needed
            //checkIndex(collection);
            long before = collection.count();
            // SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
            while ((line = bufferedReader.readLine()) != null) {

                DBObject doc = (DBObject) JSON.parse(line);
                if (doc != null)
                    collection.insert(doc);
            }
            nbElts = collection.count() - before;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    logger.error("Error trying to close the buffered reader", e);
                }
            }
        }
        return nbElts;
    }

    //db.plf_test.find( {"ut_name" : "consulterAPG", "srv_type":"UT", "duration": {"$ne": null}}, {duration:1, "_id": 0});
    public void averageDuration(String applicationName, String srvType, String collectionName, OutputStream os) throws FileNotFoundException {
        DB db = MongoListener.getMongoDB();
        Scanner in = new Scanner(new FileReader(new File(getClass().getResource("/scripts/averageDuration.js").getFile())));
        StringBuffer buff = new StringBuffer();
        while (in.hasNextLine()) {
            buff.append(in.nextLine());
        }
        CommandResult cr = db.doEval(buff.toString(), srvType, collectionName);
        logger.info(cr.toString());

        DBCollection collection = db.getCollection(collectionName);
        Iterator<DBObject> it = collection.find().iterator();
        while (it.hasNext()) {
            try {
                os.write(it.next().toString().getBytes());
            } catch (IOException e) {
                logger.error("Error during writing on the OutputStream", e);
            }
        }
        try {
            os.flush();
        } catch (IOException e) {
            logger.error("Error during OutputStream flush", e);
        }
    }

    public int nbCalls(){
                  return 0;
    }

    public void listDurationByName(String applicationName, int srvType, String name, OutputStream os) {


        DB db = MongoListener.getMongoDB();
        DBCollection coll = db.getCollection(applicationName);
        BasicDBObject filter = new BasicDBObject();
        String fieldName = giveFieldName(srvType);
        filter.put(fieldName, name);
        filter.put("action_type", "end") ;
        DBCursor cursor = coll.find(filter);
        try {

            os.write("[".getBytes());
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                Integer duration = (Integer) obj.get("duration");
                if (duration != null)
                    os.write(duration.toString().getBytes());
                if (duration != null && cursor.hasNext())
                    os.write(", ".getBytes());
            }
            os.write("]".getBytes());

        } catch (Exception e) {
            logger.error("Error writing on OutputStream", e);
        }
    }
}
