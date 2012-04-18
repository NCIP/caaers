package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="integration_log_details")
public class IntegrationLogDetails extends AbstractMutableDomainObject{
	
	// business Id of the entity
	private String businessId;
	
	// reference to the integration log
	private IntegrationLog integrationLog;
	
	// details 
	private String descirption;
		
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

	public String getDescirption() {
		return descirption;
	}

	public void setDescirption(String descirption) {
		this.descirption = descirption;
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
