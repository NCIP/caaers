/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.ajax;

import org.apache.commons.lang.StringUtils;


/**
 *
 */
public class StudyAjaxableDomainObject extends AbstractAjaxableDomainObject {

    private String shortTitle;
    private String primaryIdentifierValue;
    private String phaseCode;
    private String primarySponsorCode;
    private String status;
    private String ccIdentifierValue;

    public String getDisplayName() {
        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" : " ( " + this.getPrimaryIdentifierValue() + " ) ";
        return  primaryIdentifier + this.getShortTitle();
    }

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

    public String getPrimaryIdentifierValue() {
        return primaryIdentifierValue;
    }

    public void setPrimaryIdentifierValue(String primaryIdentifierValue) {
        this.primaryIdentifierValue = primaryIdentifierValue;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getPrimarySponsorCode() {
        return primarySponsorCode;
    }

    public void setPrimarySponsorCode(String primarySponsorCode) {
        this.primarySponsorCode = primarySponsorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getCcIdentifierValue() {
		return ccIdentifierValue;
	}

	public void setCcIdentifierValue(String ccIdentifierValue) {
		this.ccIdentifierValue = ccIdentifierValue;
	}
}
