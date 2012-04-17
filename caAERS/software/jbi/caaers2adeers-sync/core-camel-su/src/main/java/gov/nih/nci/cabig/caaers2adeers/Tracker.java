package gov.nih.nci.cabig.caaers2adeers;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Will record the status of each step in the database
 */
public class Tracker {

    private JdbcTemplate jdbc;

    public Tracker(DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
    }
    
    public void record(String corelationId, String stage, String description, String furtherDetails){
        // id, corelation_id, stage, description, on, details,

        //insert into tacker values()
    }
}
