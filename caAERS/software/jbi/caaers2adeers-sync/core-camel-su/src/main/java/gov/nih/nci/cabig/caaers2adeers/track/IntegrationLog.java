package gov.nih.nci.cabig.caaers2adeers.track;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="integration_logs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_integration_logs_id") })
public class IntegrationLog{
	
	private Integer id;
    private Integer version;
    
 // time the event is logged
 	private Date loggedOn = new Date();
 	
 	// universal unique identifier to identify each request/update
 	private String correlationId;
 	
 	// entity type
 	private String entity;
 	
 	// operation name
 	private String operation;
 	
 	// progress made by synch request
 	private Stage stage;
 	
 	// details 
 	private String notes;
 	
 	public IntegrationLog(String correlationId, Stage stage, String entity,
			String operation, String notes) {
		super();
		this.correlationId = correlationId;
		this.entity = entity;
		this.operation = operation;
		this.stage = stage;
		this.notes = notes;
		this.loggedOn = new Date();
	}
 	
 	public IntegrationLog() {
		super();
	}

	public String getNotes() {
 		return notes;
 	}

 	public void setNotes(String notes) {
 		this.notes = notes;
 	}

 	public Date getLoggedOn() {
 		return loggedOn;
 	}

 	public void setLoggedOn(Date loggedOn) {
 		this.loggedOn = loggedOn;
 	}

 	public String getCorrelationId() {
 		return correlationId;
 	}

 	public void setCorrelationId(String correlationId) {
 		this.correlationId = correlationId;
 	}

 	public String getEntity() {
 		return entity;
 	}


 	public void setEntity(String entity) {
 		this.entity = entity;
 	}

 	public String getOperation() {
 		return operation;
 	}

 	public void setOperation(String operation) {
 		this.operation = operation;
 	}
 	
 	@Enumerated(EnumType.STRING)
 	@Column(name="synch_status")
 	public Stage getStage() {
 		return stage;
 	}

 	public void setStage(Stage stage) {
 		this.stage = stage;
 	}
 	
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
	
	public static enum Stage{
        REQUEST_RECEIVED(5, "Message Received"),
        ROUTED_TO_ADEERS_SINK(10, "Message Routed to AdEERS Sink Channel"),
        ROUTED_TO_ADEERS_WS_INVOCATION_CHANNEL(20, "Routed to AdEERS Webservice Invocation route"),
        ADEERS_WS_IN_TRANSFORMATION(30, "AdEERS Webservice request transformation"),
        ADEERS_WS_INVOCATION(35, "AdEERS Webservice invocation"),
        ADEERS_WS_OUT_TRANSFORMATION(40, "AdEERS Webservice response transformation") ,
        ROUTED_TO_CAAERS_SINK(50, "Message Routed to caAERS Sink Channel"),
        CAAERS_WS_IN_TRANSFORMATION(60, "caAERS Webservice request transformation"),
        CAAERS_WS_INVOCATION_INITIATED(65, "caAERS Webservice invocation initiated"),
        CAAERS_WS_INVOCATION_COMPLETED(66, "caAERS Webservice invocation completed"),
        CAAERS_WS_OUT_TRANSFORMATION(70, "caAERS Webservice response transformation") ,

        REQUST_PROCESSING_ERROR(900, "Error while processing request"),
        NO_DATA_AVAILABLE(998, "No data available"),
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
