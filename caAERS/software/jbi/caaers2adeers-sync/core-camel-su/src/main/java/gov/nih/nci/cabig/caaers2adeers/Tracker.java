package gov.nih.nci.cabig.caaers2adeers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Will record the status of each step in the database
 */
public class Tracker implements InitializingBean {
    
    private String trackTableSequenceName;
    private String trackDetailsTableSequenceName;
    private String trackTableName;
    private String trackDetailsTableName;
    private String datasourceDriverName;

    private boolean oracleDB;
    
    private JdbcTemplate jdbc;

    public Tracker(DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
    }

    public void afterPropertiesSet() throws Exception {
       trackDetailsTableName = isEmpty(trackDetailsTableName) ? "integration_log_details":trackDetailsTableName.trim();
       trackTableName = isEmpty(trackTableName) ? "integration_logs":trackTableName.trim();

       oracleDB = datasourceDriverName.contains("oracle");
       if(oracleDB){

       }else{

       }
       

    }

    public void record(String corelationId, Stage stage, String note, Map<String, String> details){
        // id, corelation_id, stage, description, on, details,

        //insert into tacker values()
    }

    public String getTrackTableSequenceName() {
        return trackTableSequenceName;
    }

    public void setTrackTableSequenceName(String trackTableSequenceName) {
        this.trackTableSequenceName = trackTableSequenceName;
    }

    public String getTrackDetailsTableSequenceName() {
        return trackDetailsTableSequenceName;
    }

    public void setTrackDetailsTableSequenceName(String trackDetailsTableSequenceName) {
        this.trackDetailsTableSequenceName = trackDetailsTableSequenceName;
    }

    public String getDatasourceDriverName() {
        return datasourceDriverName;
    }

    public void setDatasourceDriverName(String datasourceDriverName) {
        this.datasourceDriverName = datasourceDriverName;
    }

    public String getTrackTableName() {
        return trackTableName;
    }

    public void setTrackTableName(String trackTableName) {
        this.trackTableName = trackTableName;
    }

    public String getTrackDetailsTableName() {
        return trackDetailsTableName;
    }

    public void setTrackDetailsTableName(String trackDetailsTableName) {
        this.trackDetailsTableName = trackDetailsTableName;
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim() == null;
    }
    
    public static enum Stage{
        REQUEST_RECEIVED(1, "Message Received"),
        ROUTED_TO_ADEERS_SINK(10, "Message Routed to AdEERS Sink Channel"),
        ROUTED_TO_ADEERS_WS_INVOCATION_CHANNEL(20, "Routed to AdEERS Webservice Invocation route"),
        ADEERS_WS_IN_TRANSFORMATION(30, "AdEERS Webservice request transformation"),
        ADEERS_WS_INVOCATION(35, "AdEERS Webservice invocation"),
        ADEERS_WS_OUT_TRANSFORMATION(40, "AdEERS Webservice response transformation") ,
        ROUTED_TO_CAAERS_SINK(50, "Message Routed to caAERS Sink Channel"),
        CAAERS_WS_IN_TRANSFORMATION(60, "caAERS Webservice request transformation"),
        CAAERS_WS_INVOCATION(65, "caAERS Webservice invocation"),
        CAAERS_WS_OUT_TRANSFORMATION(70, "caAERS Webservice response transformation") ,

        REQUST_PROCESSING_ERROR(900, "Error while processing request"),
        REQUEST_COMPLETION(999, "Message processing complete")

        ;
        private int code;
        private String stageName;

         private Stage(int code, String stageName) {
            this.code = code;
            this.stageName = stageName;
        }

        public int getCode() {
            return code;
        }

        public String getStageName() {
            return stageName;
        }
    }
}
