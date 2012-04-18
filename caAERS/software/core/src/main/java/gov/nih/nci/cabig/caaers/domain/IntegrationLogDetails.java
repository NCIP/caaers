package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="integration_log_details")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_integration_log_details_id") })
public class IntegrationLogDetails extends AbstractMutableDomainObject{
	
	// business Id of the entity
	private String businessId;
	
	// reference to the integration log
	private IntegrationLog integrationLog;
	
	// error details 
	private String outcome;
	
	private SynchStatus synchStatus;
	
	@Enumerated(EnumType.ORDINAL)
	public SynchStatus getSynchStatus() {
		return synchStatus;
	}

	public void setSynchStatus(SynchStatus synchStatus) {
		this.synchStatus = synchStatus;
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

	// entity type
	private String entity;
	
	// operation name
	private String operation;

}
