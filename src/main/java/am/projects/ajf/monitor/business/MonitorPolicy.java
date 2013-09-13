package am.projects.ajf.monitor.business;

import am.projects.ajf.monitor.Constants;
import am.projects.ajf.monitor.listeners.MongoListener;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

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

    public static long batchInsert(InputStream is, String applicationName) throws IOException {
        long nbElts = 0;
        BufferedReader bufferedReader = null;
//        URLConnection yc = null;
        try {
//            URL u = new URL(strUrl);
//            yc = u.openConnection();
//            yc.setConnectTimeout(HTTP_TIMEOUT);
//            yc.setReadTimeout(HTTP_TIMEOUT);
//            yc.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            DB db = MongoListener.getMongoDB();
            DBCollection collection = db.getCollection(applicationName);
            //Create index  if needed
            //checkIndex(collection);
            long before = collection.count();
            SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
            while ((line = bufferedReader.readLine()) != null) {

                DBObject doc = (DBObject) JSON.parse(line);

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
}
