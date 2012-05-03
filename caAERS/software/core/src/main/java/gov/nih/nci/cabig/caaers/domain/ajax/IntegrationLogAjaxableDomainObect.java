package gov.nih.nci.cabig.caaers.domain.ajax;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IntegrationLogAjaxableDomainObect {
	
	private Date loggedOn;
	private String service;
	private String overallStatus;
	private Map<String,String> steps = new HashMap<String,String>();
	
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
