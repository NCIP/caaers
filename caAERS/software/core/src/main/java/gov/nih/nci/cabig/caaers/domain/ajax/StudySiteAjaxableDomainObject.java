package gov.nih.nci.cabig.caaers.domain.ajax;



 
/**
 * The Class StudySiteAjaxableDomainObject.
 */
public class StudySiteAjaxableDomainObject extends AbstractAjaxableDomainObject {


    /** The name. */
    private String name;
    
    /** The nci institute code. */
    private String nciInstituteCode;
    
    /** The study id. */
    private Integer studyId;
    
    /** The type. */
    private String type;
    
    /** The primary id. */
    private String primaryId;
    
    /** The study short title. */
    private String studyShortTitle;
    
    /** The status. */
    private String status;
    
    /** The study phase. */
    private String studyPhase;

    private Boolean dataEntryStatus;
    private String fundingSponsorIdentifierValue;


    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the study id.
	 *
	 * @return the study id
	 */
	public Integer getStudyId() {
		return studyId;
	}

	/**
	 * Sets the study id.
	 *
	 * @param studyId the new study id
	 */
	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}

	/**
	 * Gets the nci institute code.
	 *
	 * @return the nci institute code
	 */
	public String getNciInstituteCode() {
		return nciInstituteCode;
	}

	/**
	 * Sets the nci institute code.
	 *
	 * @param nciInstituteCode the new nci institute code
	 */
	public void setNciInstituteCode(String nciInstituteCode) {
		this.nciInstituteCode = nciInstituteCode;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplayName(){
    	StringBuffer displayNameBuffer = new StringBuffer();
    	displayNameBuffer.append(name);
    	if(nciInstituteCode != null && !nciInstituteCode.equals("")){
    		displayNameBuffer.append("(");
    		displayNameBuffer.append(nciInstituteCode);
    		displayNameBuffer.append(")");
    	}
    	return displayNameBuffer.toString();
    }

    /**
     * Gets the primary id.
     *
     * @return the primary id
     */
    public String getPrimaryId() {
        return primaryId;
    }

    /**
     * Sets the primary id.
     *
     * @param primaryId the new primary id
     */
    public void setPrimaryId(String primaryId) {
        this.primaryId = primaryId;
    }

    /**
     * Gets the study short title.
     *
     * @return the study short title
     */
    public String getStudyShortTitle() {
        return studyShortTitle;
    }

    /**
     * Sets the study short title.
     *
     * @param studyShortTitle the new study short title
     */
    public void setStudyShortTitle(String studyShortTitle) {
        this.studyShortTitle = studyShortTitle;
    }

    /**
     * Gets the study phase.
     *
     * @return the study phase
     */
    public String getStudyPhase() {
        return studyPhase;
    }

    /**
     * Sets the study phase.
     *
     * @param studyPhase the new study phase
     */
    public void setStudyPhase(String studyPhase) {
        this.studyPhase = studyPhase;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object arg0) {
        if (arg0 == null) {
            return false;
        }

        if (!(arg0 instanceof StudySiteAjaxableDomainObject)) {
            return false;
        }

        StudySiteAjaxableDomainObject other = (StudySiteAjaxableDomainObject) arg0;

        if (this.getNciInstituteCode().equals(other.getNciInstituteCode())) {
            return true;
        }

        return false;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isDataEntryStatus() {
        return dataEntryStatus;
    }

    public void setDataEntryStatus(Boolean dataEntryStatus) {
        this.dataEntryStatus = dataEntryStatus;
    }

    public String getFundingSponsorIdentifierValue() {
        return fundingSponsorIdentifierValue;
    }

    public void setFundingSponsorIdentifierValue(String fundingSponsorIdentifierValue) {
        this.fundingSponsorIdentifierValue = fundingSponsorIdentifierValue;
    }
}