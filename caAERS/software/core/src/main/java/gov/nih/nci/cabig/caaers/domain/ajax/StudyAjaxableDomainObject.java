/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.ajax;

import org.apache.commons.lang.StringUtils;


 
/**
 * The Class StudyAjaxableDomainObject.
 */
public class StudyAjaxableDomainObject extends AbstractAjaxableDomainObject {

    /** The short title. */
    private String shortTitle;
    
    /** The primary identifier value. */
    private String primaryIdentifierValue;
    
    /** The phase code. */
    private String phaseCode;
    
    /** The primary sponsor code. */
    private String primarySponsorCode;
    
    /** The status. */
    private String status;
    
    /** The cc identifier value. */
    private String ccIdentifierValue;

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" : " ( " + this.getPrimaryIdentifierValue() + " ) ";
        return  primaryIdentifier + this.getShortTitle();
    }

    /**
     * Gets the truncated display name.
     *
     * @return the truncated display name
     */
    public String getTruncatedDisplayName() {
        String identifier = this.getCcIdentifierValue() == null ? "" : " ( "+this.getCcIdentifierValue()+" ) ";
        String suffix = "";
        String studyTitle = this.getShortTitle();
        int end = studyTitle.length();
        if(end > 30){
        	end = 30;
        	suffix = "...";
        }
        studyTitle = StringUtils.substring(studyTitle, 0, end);
        studyTitle = studyTitle+suffix;
        return  identifier + studyTitle;
    }

    /**
     * Gets the primary identifier value.
     *
     * @return the primary identifier value
     */
    public String getPrimaryIdentifierValue() {
        return primaryIdentifierValue;
    }

    /**
     * Sets the primary identifier value.
     *
     * @param primaryIdentifierValue the new primary identifier value
     */
    public void setPrimaryIdentifierValue(String primaryIdentifierValue) {
        this.primaryIdentifierValue = primaryIdentifierValue;
    }

    /**
     * Gets the short title.
     *
     * @return the short title
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * Sets the short title.
     *
     * @param shortTitle the new short title
     */
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    /**
     * Gets the phase code.
     *
     * @return the phase code
     */
    public String getPhaseCode() {
        return phaseCode;
    }

    /**
     * Sets the phase code.
     *
     * @param phaseCode the new phase code
     */
    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    /**
     * Gets the primary sponsor code.
     *
     * @return the primary sponsor code
     */
    public String getPrimarySponsorCode() {
        return primarySponsorCode;
    }

    /**
     * Sets the primary sponsor code.
     *
     * @param primarySponsorCode the new primary sponsor code
     */
    public void setPrimarySponsorCode(String primarySponsorCode) {
        this.primarySponsorCode = primarySponsorCode;
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

	/**
	 * Gets the cc identifier value.
	 *
	 * @return the cc identifier value
	 */
	public String getCcIdentifierValue() {
		return ccIdentifierValue;
	}

	/**
	 * Sets the cc identifier value.
	 *
	 * @param ccIdentifierValue the new cc identifier value
	 */
	public void setCcIdentifierValue(String ccIdentifierValue) {
		this.ccIdentifierValue = ccIdentifierValue;
	}
}
