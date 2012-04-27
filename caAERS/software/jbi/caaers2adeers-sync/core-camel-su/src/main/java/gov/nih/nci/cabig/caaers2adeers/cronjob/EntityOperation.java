package gov.nih.nci.cabig.caaers2adeers.cronjob;

public enum EntityOperation{
    AGENT("agent", "getAgentsLOV", "0+0+1+*+*+?"),

    ;
    private String qualifiedName;
    private String operationName;
    private Boolean async = true;
    private String cronJobExpression;

     private EntityOperation(String qualifiedName, String operationName, String cronJobExpression, boolean async) {
        this.qualifiedName = qualifiedName;
        this.operationName = operationName;
        this.cronJobExpression = cronJobExpression;
        this.async = async;
    }
     
     private EntityOperation(String qualifiedName, String operationName, String cronJobExpression) {
         this(qualifiedName, operationName, cronJobExpression , true);
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

}