package gov.nih.nci.cabig.caaers2adeers.track;


import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name="integration_log_details")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_integration_log_details_id") })
public class IntegrationLogDetail{
	
	private Integer id;
    private Integer version;

    @Id @GeneratedValue(generator = "id-generator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
	
 // business Id of the entity
 	private String businessId;
 	
 	// reference to the integration log
 	private IntegrationLog integrationLog;
 	
 	// error details 
 	private String outcome;
 	
 	private Stage stage;
 	
 	public IntegrationLogDetail(String businessId, String outcome) {
		super();
		this.businessId = businessId;
		this.outcome = outcome;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="synch_status")
 	public Stage getStage() {
 		return stage;
 	}

 	public void setStage(Stage stage) {
 		this.stage = stage;
 	}

 	public String getOutcome() {
 		return outcome;
 	}

 	public void setOutcome(String outcome) {
 		this.outcome = outcome;
 	}
 	
 	public String getBusinessId() {
 		return businessId;
 	}

 	public void setBusinessId(String businessId) {
 		this.businessId = businessId;
 	}

 	@ManyToOne
 	@JoinColumn(name="log_id",nullable=false)
 	public IntegrationLog getIntegrationLog() {
 		return integrationLog;
 	}

 	public void setIntegrationLog(IntegrationLog integrationLog) {
 		this.integrationLog = integrationLog;
 	}

}
