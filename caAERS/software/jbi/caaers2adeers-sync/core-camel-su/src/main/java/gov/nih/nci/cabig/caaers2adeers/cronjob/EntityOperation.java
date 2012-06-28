package gov.nih.nci.cabig.caaers2adeers.cronjob;

public enum EntityOperation{
    AGENT("agent", "getAgentsLOV", "0+0+1+*+*+?"),
    AGENT_UOM("agentDoseUOM", "getAgentDoseUOMLOV", "0+0+1+*+*+?", true),
    ASAEL("asael", "getASAEL", "0+0+1+*+*+?"),
    DEVICE("device", "getDevicesLOV", "0+0+1+*+*+?"),
    LAB("lab", "getLabsLOV", "0+0+1+*+*+?"),
    PRIOR_THERAPY("priortherapy", "getTherapiesLOV", "0+0+1+*+*+?"),
    PRE_EXISTING_CONDITION("preexistingcondition", "getPreExistingConditionsLOV", "0+0+1+*+*+?"),
    ORGANIZATION("organization", "getOrganizationsLOV", "0+0+1+*+*+?"),
    MERGED_ORGANIZATION("mergedorganization", "getMergedOrganization", "0+0+1+*+*+?"),
    CTCAE("ctcae", "getCTCAELOV", "0+0+1+*+*+?"),

    ;
    private String qualifiedName;
    private String operationName;
    private Boolean async = true;
    private Boolean useDefaultDate = true;
    private String cronJobExpression;

     private EntityOperation(String qualifiedName, String operationName, String cronJobExpression, boolean async, boolean useDefaultDate) {
        this.qualifiedName = qualifiedName;
        this.operationName = operationName;
        this.cronJobExpression = cronJobExpression;
        this.async = async;
        this.useDefaultDate = useDefaultDate;
    }
     
     private EntityOperation(String qualifiedName, String operationName, String cronJobExpression) {
         this(qualifiedName, operationName, cronJobExpression , true, false);
     }
     
     private EntityOperation(String qualifiedName, String operationName, String cronJobExpression, boolean useDefaultDate) {
         this(qualifiedName, operationName, cronJobExpression , true, useDefaultDate);
     }

	public String getQualifiedName() {
		return qualifiedName;
	}

	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Boolean getAsync() {
		return async;
	}

	public void setAsync(Boolean async) {
		this.async = async;
	}
     
    public String getMode(){
    	return async ? "async" : "sync" ;
    }

	public String getCronJobExpression() {
		return cronJobExpression;
	}

	public void setCronJobExpression(String cronJobExpression) {
		this.cronJobExpression = cronJobExpression;
	}

	public Boolean getUseDefaultDate() {
		return useDefaultDate;
	}

	public void setUseDefaultDate(Boolean useDefaultDate) {
		this.useDefaultDate = useDefaultDate;
	}

}