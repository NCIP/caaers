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
	
	
	private Date ctcaeLastUpdated;
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


	public void setCtcaeLastUpdated(Date ctcaeLastUpdated) {
		this.ctcaeLastUpdated = ctcaeLastUpdated;
	}


	public Date getDevicesLastUpdated() {
		return devicesLastUpdated;
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
