package am.projects.ajf.monitor.test;

import am.projects.ajf.monitor.Constants;
import am.projects.ajf.monitor.business.MonitorPolicy;
import am.projects.ajf.monitor.listeners.MongoListener;
import com.mongodb.DB;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Collection;

/**
 * User: mlecoutre
 * Date: 13/09/13
 * Time: 14:10
 */
public class MonitorPolicyTest {


    private static final Logger logger = LoggerFactory.getLogger(MonitorPolicy.class);
    public static final String APPLICATION_NAME = "plf";

    private static MongoListener ml;

    @Before
    public void setup() {
        ml = new MongoListener();
        ml.contextInitialized(null);
        //DB db = MongoListener.getMongoDB();
       // db.getCollection(APPLICATION_NAME).drop();
    }

    @After
    public void tearDown() {
        ml.contextDestroyed(null);
    }


    @Test
    public void testBacthInsert() throws Exception {

        long nbElts = MonitorPolicy.getInstance().batchInsert(getClass().getResourceAsStream("/monitor.log"), APPLICATION_NAME);
        Assert.assertTrue(String.format("We should succeed to insert %s elements", nbElts), nbElts > 0);
        logger.info(String.format("testBacthInsert : Nb Elts inserted: %s ", nbElts));

    }

    @Test
    public void testRequestDistinctByServiceType() throws Exception {

        Collection<String> listFcts = MonitorPolicy.getInstance().requestDistinctByServiceType(APPLICATION_NAME, Constants.SRV_TYPE_FUNCTION, null, null);
        logger.info(String.format("testRequestDistinctByServiceType :function inserted: %s ", listFcts));
        Assert.assertTrue("We should get 2 functions and we have " + listFcts.size(), listFcts.size() == 2);

        Collection<String> listUts = MonitorPolicy.getInstance().requestDistinctByServiceType(APPLICATION_NAME, Constants.SRV_TYPE_UT, null, null);
        logger.info(String.format("testRequestDistinctByServiceType : Nb unit tasks inserted: %s ", listUts));
        Assert.assertTrue("We should get 3 unit tasks and we have " + listUts.size(), listUts.size() == 3);

        Collection<String> listServices = MonitorPolicy.getInstance().requestDistinctByServiceType(APPLICATION_NAME, Constants.SRV_TYPE_SERVICE, null, null);
        logger.info(String.format("testRequestDistinctByServiceType : Nb services inserted: %s:%s ", listServices.size(), listServices));
        Assert.assertTrue("We should get 24 services and we have " + listServices.size(), listServices.size() == 24);


        //with filter

        listUts = MonitorPolicy.getInstance().requestDistinctByServiceType(APPLICATION_NAME, Constants.SRV_TYPE_UT, Constants.FIELD_FUNCTION, "GererAPG");
        logger.info(String.format("testRequestDistinctByServiceType : Nb unit tasks for GererAPG: %s ", listUts));
        Assert.assertTrue("We should get 3 unit tasks for GererAPG and we have " + listUts.size(), listUts.size() == 2);


    }

    @Test
    public void testAverageDuration() throws Exception {
        OutputStream bos = new ByteArrayOutputStream();

        logger.info("UT");
        MonitorPolicy.getInstance().averageDuration(APPLICATION_NAME, Constants.SRV_TYPE_UT_LABEL, "utDuration", bos);
        Assert.assertTrue("we should have some result", bos.toString() != null);
        logger.info(bos.toString());
        logger.info("SERVICE");
        bos = new ByteArrayOutputStream();
        MonitorPolicy.getInstance().averageDuration(APPLICATION_NAME, Constants.SRV_TYPE_SERVICE_LABEL, "serviceDuration", bos);
        Assert.assertTrue("we should have some result", bos.toString() != null);
        logger.info(bos.toString());
    }

    @Test
    public void testListDurationByName() throws Exception {
        OutputStream bos = new ByteArrayOutputStream();

        MonitorPolicy.getInstance().listDurationByName(APPLICATION_NAME, Constants.SRV_TYPE_SERVICE, "ArticleDePilotageGlobalServices.findArticleDePilotageGlobal", bos);
        Assert.assertTrue("we should have some result", bos.toString() != null);
        logger.info(bos.toString());
    }
}
