/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;


public class CTEPDataInitializationCommand {
	
	public final static String CTCAE= "CTCAE";
	public final static String DEVICES= "Devices";
	public final static String PRE_EXISTING_CONDITIONS= "Pre-Existing Conditions";
	public final static String THERAPIES= "Therapies";
	public final static String ADUOM= "Agent Dose Units of Measure";
	public final static String LAB= "Lab";
	public final static String AGENTS= "Agents";
	public final static String ASAEL= "ASAEL";
	public final static String ORGANIZATIONS= "Organizations";
	
	private boolean ctcaeChecked = false;
	private boolean devicesChecked = false;
	private boolean preExistingConditinosChecked = false;
	private boolean therapiesChecked = false;
	private boolean agentDoseMeasureChecked = false;
	private boolean labChecked = false;
	private boolean agentsChecked = false;
	private boolean asaelChecked = false;
	private boolean organizationsChecked = false;
	
	
	
	private Date ctcaeLastSuccessfullyUpdated;
	private Date devicesLastSuccessfullyUpdated;
	private Date preExistingConditionsLastSuccessfullyUpdated;
	private Date therapiesLastSuccessfullyUpdated;
	private Date agentDoseMeasureLastSuccessfullyUpdated;
	private Date labLastSuccessfullyUpdated;
	private Date agentsLastSuccessfullyUpdated;
	private Date asaelLastSuccessfullyUpdated;
	private Date organizationsLastSuccessfullyUpdated;
	
	
	private Date ctcaeLastUpdated;
	public Date getCtcaeLastSuccessfullyUpdated() {
		return ctcaeLastSuccessfullyUpdated;
	}

	public void setCtcaeLastSuccessfullyUpdated(Date ctcaeLastSuccessfullyUpdated) {
		this.ctcaeLastSuccessfullyUpdated = ctcaeLastSuccessfullyUpdated;
	}

	public Date getDevicesLastSuccessfullyUpdated() {
		return devicesLastSuccessfullyUpdated;
	}

	public void setDevicesLastSuccessfullyUpdated(
			Date devicesLastSuccessfullyUpdated) {
		this.devicesLastSuccessfullyUpdated = devicesLastSuccessfullyUpdated;
	}

	public Date getPreExistingConditionsLastSuccessfullyUpdated() {
		return preExistingConditionsLastSuccessfullyUpdated;
	}

	public void setPreExistingConditionsLastSuccessfullyUpdated(
			Date preExistingConditionsLastSuccessfullyUpdated) {
		this.preExistingConditionsLastSuccessfullyUpdated = preExistingConditionsLastSuccessfullyUpdated;
	}

	public Date getTherapiesLastSuccessfullyUpdated() {
		return therapiesLastSuccessfullyUpdated;
	}

	public void setTherapiesLastSuccessfullyUpdated(
			Date therapiesLastSuccessfullyUpdated) {
		this.therapiesLastSuccessfullyUpdated = therapiesLastSuccessfullyUpdated;
	}

	public Date getAgentDoseMeasureLastSuccessfullyUpdated() {
		return agentDoseMeasureLastSuccessfullyUpdated;
	}

	public void setAgentDoseMeasureLastSuccessfullyUpdated(
			Date agentDoseMeasureLastSuccessfullyUpdated) {
		this.agentDoseMeasureLastSuccessfullyUpdated = agentDoseMeasureLastSuccessfullyUpdated;
	}

	public Date getLabLastSuccessfullyUpdated() {
		return labLastSuccessfullyUpdated;
	}

	public void setLabLastSuccessfullyUpdated(Date labLastSuccessfullyUpdated) {
		this.labLastSuccessfullyUpdated = labLastSuccessfullyUpdated;
	}

	public Date getAgentsLastSuccessfullyUpdated() {
		return agentsLastSuccessfullyUpdated;
	}

	public void setAgentsLastSuccessfullyUpdated(Date agentsLastSuccessfullyUpdated) {
		this.agentsLastSuccessfullyUpdated = agentsLastSuccessfullyUpdated;
	}

	public Date getAsaelLastSuccessfullyUpdated() {
		return asaelLastSuccessfullyUpdated;
	}

	public void setAsaelLastSuccessfullyUpdated(Date asaelLastSuccessfullyUpdated) {
		this.asaelLastSuccessfullyUpdated = asaelLastSuccessfullyUpdated;
	}

	public Date getOrganizationsLastSuccessfullyUpdated() {
		return organizationsLastSuccessfullyUpdated;
	}

	public void setOrganizationsLastSuccessfullyUpdated(
			Date organizationsLastSuccessfullyUpdated) {
		this.organizationsLastSuccessfullyUpdated = organizationsLastSuccessfullyUpdated;
	}


	private Date devicesLastUpdated;
	private Date preExistingConditionsLastUpdated;
	private Date therapiesLastUpdated;
	private Date agentDoseMeasureLastUpdated;
	private Date labLastUpdated;
	private Date agentsLastUpdated;
	private Date asaelLastUpdated;
	private Date organizationsLastUpdated;
	
	public Date getCtcaeLastUpdated() {
		return ctcaeLastUpdated;
	}
	
	public String getCtcaeLastUpdatedStr() {
		String ctcaeLastUpdatedStr = "";
		if(ctcaeLastUpdated != null){
			ctcaeLastUpdatedStr = DateUtils.formatDate(ctcaeLastUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return ctcaeLastUpdatedStr;
	}
	
	public String getCtcaeLastSuccessfullyUpdatedStr() {
		String ctcaeLastSuccessfullyUpdatedStr = "";
		if(ctcaeLastSuccessfullyUpdated != null){
			ctcaeLastSuccessfullyUpdatedStr = DateUtils.formatDate(ctcaeLastSuccessfullyUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return ctcaeLastSuccessfullyUpdatedStr;
	}


	public void setCtcaeLastUpdated(Date ctcaeLastUpdated) {
		this.ctcaeLastUpdated = ctcaeLastUpdated;
	}


	public Date getDevicesLastUpdated() {
		return devicesLastUpdated;
	}
	
	public String getDevicesLastSuccessfullyUpdatedStr() {
		String devicesLastSuccessfullyUpdatedStr = "";
		if(devicesLastSuccessfullyUpdated != null){
			devicesLastSuccessfullyUpdatedStr = DateUtils.formatDate(devicesLastSuccessfullyUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return devicesLastSuccessfullyUpdatedStr;
	}
	
	public String getDevicesLastUpdatedStr() {
		String devicesLastUpdatedStr = "";
		if(devicesLastUpdated != null){
			devicesLastUpdatedStr = DateUtils.formatDate(devicesLastUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return devicesLastUpdatedStr;
	}


	public void setDevicesLastUpdated(Date devicesLastUpdated) {
		this.devicesLastUpdated = devicesLastUpdated;
	}


	public Date getPreExistingConditionsLastUpdated() {
		return preExistingConditionsLastUpdated;
	}
	
	public String getPreExistingConditionsLastUpdatedStr() {
		String preExistingConditionsLastUpdatedStr = "";
		if(preExistingConditionsLastUpdated != null){
			preExistingConditionsLastUpdatedStr = DateUtils.formatDate(preExistingConditionsLastUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return preExistingConditionsLastUpdatedStr;
	}
	
	public String getPreExistingConditionsLastSuccessfullyUpdatedStr() {
		String preExistingConditionsLastSuccessfullyUpdatedStr = "";
		if(preExistingConditionsLastSuccessfullyUpdated != null){
			preExistingConditionsLastSuccessfullyUpdatedStr = DateUtils.formatDate(preExistingConditionsLastSuccessfullyUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return preExistingConditionsLastSuccessfullyUpdatedStr;
	}


	public void setPreExistingConditionsLastUpdated(
			Date preExistingConditionsLastUpdated) {
		this.preExistingConditionsLastUpdated = preExistingConditionsLastUpdated;
	}


	public Date getTherapiesLastUpdated() {
		return therapiesLastUpdated;
	}
	
	public String getTherapiesLastUpdatedStr() {
		String therapiesLastUpdatedStr = "";
		if(therapiesLastUpdated != null){
			therapiesLastUpdatedStr = DateUtils.formatDate(therapiesLastUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return therapiesLastUpdatedStr;
	}
	
	
	public String getTherapiesLastSuccessfullyUpdatedStr() {
		String therapiesLastSuccessfullyUpdatedStr = "";
		if(therapiesLastSuccessfullyUpdated != null){
			therapiesLastSuccessfullyUpdatedStr = DateUtils.formatDate(therapiesLastSuccessfullyUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return therapiesLastSuccessfullyUpdatedStr;
	}


	public void setTherapiesLastUpdated(Date therapiesLastUpdated) {
		this.therapiesLastUpdated = therapiesLastUpdated;
	}


	public Date getAgentDoseMeasureLastUpdated() {
		return agentDoseMeasureLastUpdated;
	}
	
	public String getAgentDoseMeasureLastUpdatedStr() {
		String agentDoseMeasureLastUpdatedStr = "";
		if(agentDoseMeasureLastUpdated != null){
			agentDoseMeasureLastUpdatedStr = DateUtils.formatDate(agentDoseMeasureLastUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return agentDoseMeasureLastUpdatedStr;
	}
	
	public String getAgentDoseMeasureLastSuccessfullyUpdatedStr() {
		String agentDoseMeasureLastSuccessfullyUpdatedStr = "";
		if(agentDoseMeasureLastSuccessfullyUpdated != null){
			agentDoseMeasureLastSuccessfullyUpdatedStr = DateUtils.formatDate(agentDoseMeasureLastSuccessfullyUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return agentDoseMeasureLastSuccessfullyUpdatedStr;
	}


	public void setAgentDoseMeasureLastUpdated(Date agentDoseMeasureLastUpdated) {
		this.agentDoseMeasureLastUpdated = agentDoseMeasureLastUpdated;
	}


	public Date getAgentsLastUpdated() {
		return agentsLastUpdated;
	}
	
	public String getAgentsLastUpdatedStr() {
		String agentsLastUpdatedStr = "";
		if(agentsLastUpdated != null){
			agentsLastUpdatedStr = DateUtils.formatDate(agentsLastUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return agentsLastUpdatedStr;
	}
	
	public String getAgentsLastSuccessfullyUpdatedStr() {
		String agentsLastSuccessfullyUpdatedStr = "";
		if(agentsLastSuccessfullyUpdated != null){
			agentsLastSuccessfullyUpdatedStr = DateUtils.formatDate(agentsLastSuccessfullyUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return agentsLastSuccessfullyUpdatedStr;
	}


	public void setAgentsLastUpdated(Date agentsLastUpdated) {
		this.agentsLastUpdated = agentsLastUpdated;
	}


	public Date getAsaelLastUpdated() {
		return asaelLastUpdated;
	}
	
	public String getAsaelLastUpdatedStr() {
		String asaelLastUpdatedStr = "";
		if(asaelLastUpdated != null){
			asaelLastUpdatedStr = DateUtils.formatDate(asaelLastUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return asaelLastUpdatedStr;
	}
	
	public String getAsaelLastSuccessfullyUpdatedStr() {
		String asaelLastSuccessfullyUpdatedStr = "";
		if(asaelLastSuccessfullyUpdated != null){
			asaelLastSuccessfullyUpdatedStr = DateUtils.formatDate(asaelLastSuccessfullyUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return asaelLastSuccessfullyUpdatedStr;
	}


	public void setAsaelLastUpdated(Date asaelLastUpdated) {
		this.asaelLastUpdated = asaelLastUpdated;
	}


	public Date getOrganizationsLastUpdated() {
		return organizationsLastUpdated;
	}
	
	public String getOrganizationsLastUpdatedStr() {
		String organizationsLastUpdatedStr = "";
		if(organizationsLastUpdated != null){
			organizationsLastUpdatedStr = DateUtils.formatDate(organizationsLastUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return organizationsLastUpdatedStr;
	}
	
	public String getOrganizationsLastSuccessfullyUpdatedStr() {
		String organizationsLastSuccessfullyUpdatedStr = "";
		if(organizationsLastSuccessfullyUpdated != null){
			organizationsLastSuccessfullyUpdatedStr = DateUtils.formatDate(organizationsLastSuccessfullyUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return organizationsLastSuccessfullyUpdatedStr;
	}


	public void setOrganizationsLastUpdated(Date organizationsLastUpdated) {
		this.organizationsLastUpdated = organizationsLastUpdated;
	}


	public boolean isCtcaeChecked() {
		return ctcaeChecked;
	}


	public void setCtcaeChecked(boolean ctcaeChecked) {
		this.ctcaeChecked = ctcaeChecked;
	}


	public boolean isDevicesChecked() {
		return devicesChecked;
	}


	public void setDevicesChecked(boolean devicesChecked) {
		this.devicesChecked = devicesChecked;
	}


	public boolean isPreExistingConditinosChecked() {
		return preExistingConditinosChecked;
	}


	public void setPreExistingConditinosChecked(boolean preExistingConditinosChecked) {
		this.preExistingConditinosChecked = preExistingConditinosChecked;
	}


	public boolean isTherapiesChecked() {
		return therapiesChecked;
	}


	public void setTherapiesChecked(boolean therapiesChecked) {
		this.therapiesChecked = therapiesChecked;
	}


	public boolean isAgentDoseMeasureChecked() {
		return agentDoseMeasureChecked;
	}


	public void setAgentDoseMeasureChecked(boolean agentDoseMeasureChecked) {
		this.agentDoseMeasureChecked = agentDoseMeasureChecked;
	}


	public boolean isAgentsChecked() {
		return agentsChecked;
	}


	public void setAgentsChecked(boolean agentsChecked) {
		this.agentsChecked = agentsChecked;
	}


	public boolean isAsaelChecked() {
		return asaelChecked;
	}


	public boolean isLabChecked() {
		return labChecked;
	}


	public void setLabChecked(boolean labChecked) {
		this.labChecked = labChecked;
	}


	public Date getLabLastUpdated() {
		return labLastUpdated;
	}
	
	public String getLabLastUpdatedStr() {
		String labLastUpdatedStr = "";
		if(labLastUpdated != null){
			labLastUpdatedStr = DateUtils.formatDate(labLastUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return labLastUpdatedStr;
	}
	
	public String getLabLastSuccessfullyUpdatedStr() {
		String labLastSuccessfullyUpdatedStr = "";
		if(labLastSuccessfullyUpdated != null){
			labLastSuccessfullyUpdatedStr = DateUtils.formatDate(labLastSuccessfullyUpdated, DateUtils.DATE_PATTERN_WITH_TZ);
		}
		return labLastSuccessfullyUpdatedStr;
	}


	public void setLabLastUpdated(Date labLastUpdated) {
		this.labLastUpdated = labLastUpdated;
	}


	public void setAsaelChecked(boolean asaelChecked) {
		this.asaelChecked = asaelChecked;
	}


	public boolean isOrganizationsChecked() {
		return organizationsChecked;
	}


	public void setOrganizationsChecked(boolean organizationsChecked) {
		this.organizationsChecked = organizationsChecked;
	}
}
