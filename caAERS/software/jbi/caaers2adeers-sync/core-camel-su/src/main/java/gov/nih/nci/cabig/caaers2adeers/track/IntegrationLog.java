/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers.track;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 	
 	private List<IntegrationLogDetail> integrationLogDetails = new ArrayList<IntegrationLogDetail>();
 	
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
	
	@OneToMany(mappedBy = "integrationLog", fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<IntegrationLogDetail> getIntegrationLogDetails() {
		return integrationLogDetails;
	}

	public void setIntegrationLogDetails(
			List<IntegrationLogDetail> integrationLogDetails) {
		this.integrationLogDetails = integrationLogDetails;
	}
	
	public void addIntegrationLogDetail(IntegrationLogDetail integrationLogDetail){
		integrationLogDetail.setIntegrationLog(this);
		getIntegrationLogDetails().add(integrationLogDetail);
	}
	
	public static enum Stage{
        REQUEST_RECEIVED(5, "Message Received"),
        ROUTED_TO_ADEERS_REQUEST_SINK(10, "Message Routed to AdEERS Request Sink Channel"),
        ROUTED_TO_ADEERS_RESPONSE_SINK(11, "Message Routed to AdEERS Response Sink Channel"),
        ROUTED_TO_ADEERS_WS_INVOCATION_CHANNEL(20, "Routed to AdEERS Webservice Invocation route"),
        ADEERS_WS_IN_TRANSFORMATION(30, "AdEERS Webservice request transformation"),
        ADEERS_WS_INVOCATION_INITIATED(35, "AdEERS Webservice invocation initiated"),
        ADEERS_WS_INVOCATION_COMPLETED(36, "AdEERS Webservice invocation completed"),
        ADEERS_WS_OUT_TRANSFORMATION(40, "AdEERS Webservice response transformation") ,
        ROUTED_TO_CAAERS_REQUEST_SINK(50, "Message Routed to caAERS Request Sink Channel"),
        ROUTED_TO_CAAERS_RESPONSE_SINK(51, "Message Routed to caAERS Response Sink Channel"),
        ROUTED_TO_CAAERS_WS_INVOCATION_CHANNEL(52, "Routed to caAERS Webservice Invocation route"),
        CAAERS_WS_IN_TRANSFORMATION(60, "caAERS Webservice request transformation"),
        CAAERS_WS_INVOCATION_INITIATED(65, "caAERS Webservice invocation initiated"),
        CAAERS_WS_INVOCATION_COMPLETED(66, "caAERS Webservice invocation completed"),
        CAAERS_WS_OUT_TRANSFORMATION(70, "caAERS Webservice response transformation") ,

        REQUST_PROCESSING_ERROR(900, "Error while processing request"),
        NO_DATA_AVAILABLE(998, "No data available"),
        REQUEST_COMPLETION(999, "Message processing complete"),
        
        PRE_PROCESS_OPEN_ODM_MSG(12, "Add Exchange headers to OPEN ODM participant message")


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
