package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="integration_logs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_integration_logs_id") })
public class IntegrationLog extends AbstractMutableDomainObject{
	
	// time the event is logged
	private Date loggedOn = new Date();
	
	// universal unique identifier to identify each request/update
	private String correlationId;
	
	// entity type
	private String entity;
	
	// operation name
	private String operation;
	
	// progress made by synch request
	private SynchStatus synchStatus;
	
	// details 
	private String notes;
	
	private List<IntegrationLogDetail> integrationLogDetails = new ArrayList<IntegrationLogDetail>();
	
	public String getNotes() {
		return notes;
	}

	@OneToMany
	@JoinColumn(name="log_id")
	@Cascade({CascadeType.PERSIST})
	public List<IntegrationLogDetail> getIntegrationLogDetails() {
		return integrationLogDetails;
	}

	public void setIntegrationLogDetails(
			List<IntegrationLogDetail> integrationLogDetails) {
		this.integrationLogDetails = integrationLogDetails;
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
	public SynchStatus getSynchStatus() {
		return synchStatus;
	}

	public void setSynchStatus(SynchStatus synchStatus) {
		this.synchStatus = synchStatus;
	}
	
	@Transient
	public String getIfSuccess(){
		if(synchStatus == SynchStatus.REQUST_PROCESSING_ERROR){
			return "Failed";
		}
		return "Success";
	}

}
