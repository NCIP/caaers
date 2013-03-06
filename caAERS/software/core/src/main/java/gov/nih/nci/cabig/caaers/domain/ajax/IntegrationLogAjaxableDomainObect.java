/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.ajax;

import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class IntegrationLogAjaxableDomainObect {
	
	private String entity;
	private String correlationId;
	public Boolean getHasLogDetails() {
		return hasLogDetails;
	}
	public void setHasLogDetails(Boolean hasLogDetails) {
		this.hasLogDetails = hasLogDetails;
	}
	private Boolean hasLogDetails;
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
	private Date loggedOn;
	private String service;
	private String overallStatus;
	private Map<String,String> steps = new LinkedHashMap<String,String>();
	private String loggedOnDateStr = null;
	
	public String getLoggedOnDateStr() {
		if(getLoggedOn() == null){
			return null;
		}
		
		return DateUtils.formatDate(getLoggedOn(), DateUtils.DATE_WITH_HYPHENS);
	}
	public void setLoggedOnDateStr(String loggedOnDateStr) {
		this.loggedOnDateStr = loggedOnDateStr;
	}
	public Date getLoggedOn() {
		return loggedOn;
	}
	public void setLoggedOn(Date loggedOn) {
		this.loggedOn = loggedOn;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getOverallStatus() {
		return overallStatus;
	}
	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}
	public Map<String, String> getSteps() {
		return steps;
	}
	public void setSteps(Map<String, String> steps) {
		this.steps = steps;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	private String notes;

}
