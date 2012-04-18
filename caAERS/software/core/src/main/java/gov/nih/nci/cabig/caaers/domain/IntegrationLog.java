package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class IntegrationLog extends AbstractMutableDomainObject{
	
	// time the event is logged
	private Date loggedOn = new Date();
	
	// universal unique identifier to identify each request/update
	private String correlationId;
	
	// progress of the request
	private Stage stage;
	
	// details 
	private String descirption;
	
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

	@Enumerated(EnumType.ORDINAL)
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
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
