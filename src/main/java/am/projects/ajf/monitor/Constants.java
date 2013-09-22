package am.projects.ajf.monitor;

/**
 * User: E010925
 * Date: 27/11/12
 * Time: 14:50
 */
public interface Constants {

    public static final String CONFIGURATION_PROPERTIES = "/configuration.properties";

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    // public static final String MONITOR_CONFIG_COLLECTION = "scheduler-config";
    // public static final String SYSTEM_INDEXES_COLLECTION = "system.indexes";

    public static final String FIELD_FUNCTION = "func_name";
    public static final String FIELD_UT = "ut_name";
    public static final String FIELD_SERVICE = "srv_name";

    public static final int SRV_TYPE_UT = 1;
    public static final int SRV_TYPE_SERVICE = 3;
    public static final int SRV_TYPE_FUNCTION = 5;
    public static final String SRV_TYPE_UT_LABEL = "UT";
    public static final String SRV_TYPE_SERVICE_LABEL = "SERVICE";
    //public static final String DAO = "DAO";


}
