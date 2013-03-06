/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

 
/**
 * This class represents the Additional Information domain object associated with the Adverse event
 * report.
 *
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "additional_information")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_additional_information_id")})
public class AdditionalInformation extends AbstractExpeditedReportSingleChild {

    /** The autopsy report. */
    private Boolean autopsyReport;

    /** The consults. */
    private Boolean consults;

    /** The discharge summary. */
    private Boolean dischargeSummary;

    /** The flow charts. */
    private Boolean flowCharts;

    /** The lab reports. */
    private Boolean labReports;

    /** The oba form. */
    private Boolean obaForm;

    /** The other. */
    private Boolean other;

    /** The pathology report. */
    private Boolean pathologyReport;

    /** The progress notes. */
    private Boolean progressNotes;

    /** The radiology reports. */
    private Boolean radiologyReports;

    /** The referral letters. */
    private Boolean referralLetters;

    /** The irb report. */
    private Boolean irbReport;

    /** The other information. */
    private String otherInformation;

    // //// LOGIC

    // //// BEAN PROPERTIES

    /**
     * getter method for autopsy Report.
     *
     * @return autopsyReport
     */
    public Boolean getAutopsyReport() {
        return autopsyReport;
    }

    /**
     * setter method for autopsy Report.
     *
     * @param autopsyReport the new autopsy report
     */
    public void setAutopsyReport(Boolean autopsyReport) {
        this.autopsyReport = autopsyReport;
    }

    /**
     * Gets the consults.
     *
     * @return the consults
     */
    public Boolean getConsults() {
        return consults;
    }

    /**
     * Sets the consults.
     *
     * @param consults the new consults
     */
    public void setConsults(Boolean consults) {
        this.consults = consults;
    }

    /**
     * Gets the discharge summary.
     *
     * @return the discharge summary
     */
    public Boolean getDischargeSummary() {
        return dischargeSummary;
    }

    /**
     * Sets the discharge summary.
     *
     * @param dischargeSummary the new discharge summary
     */
    public void setDischargeSummary(Boolean dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }

    /**
     * Gets the flow charts.
     *
     * @return the flow charts
     */
    public Boolean getFlowCharts() {
        return flowCharts;
    }

    /**
     * Sets the flow charts.
     *
     * @param flowCharts the new flow charts
     */
    public void setFlowCharts(Boolean flowCharts) {
        this.flowCharts = flowCharts;
    }

    /**
     * Gets the lab reports.
     *
     * @return the lab reports
     */
    public Boolean getLabReports() {
        return labReports;
    }

    /**
     * Sets the lab reports.
     *
     * @param labReports the new lab reports
     */
    public void setLabReports(Boolean labReports) {
        this.labReports = labReports;
    }

    /**
     * Gets the oba form.
     *
     * @return the oba form
     */
    public Boolean getObaForm() {
        return obaForm;
    }

    /**
     * Sets the oba form.
     *
     * @param obaForm the new oba form
     */
    public void setObaForm(Boolean obaForm) {
        this.obaForm = obaForm;
    }

    /**
     * Gets the other.
     *
     * @return the other
     */
    public Boolean getOther() {
        return other;
    }

    /**
     * Sets the other.
     *
     * @param other the new other
     */
    public void setOther(Boolean other) {
        this.other = other;
    }

    /**
     * Gets the other information.
     *
     * @return the other information
     */
    public String getOtherInformation() {
        return otherInformation;
    }

    /**
     * Sets the other information.
     *
     * @param otherInformation the new other information
     */
    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    /**
     * Gets the pathology report.
     *
     * @return the pathology report
     */
    public Boolean getPathologyReport() {
        return pathologyReport;
    }

    /**
     * Sets the pathology report.
     *
     * @param pathologyReport the new pathology report
     */
    public void setPathologyReport(Boolean pathologyReport) {
        this.pathologyReport = pathologyReport;
    }

    /**
     * Gets the progress notes.
     *
     * @return the progress notes
     */
    public Boolean getProgressNotes() {
        return progressNotes;
    }

    /**
     * Sets the progress notes.
     *
     * @param progressNotes the new progress notes
     */
    public void setProgressNotes(Boolean progressNotes) {
        this.progressNotes = progressNotes;
    }

    /**
     * Gets the radiology reports.
     *
     * @return the radiology reports
     */
    public Boolean getRadiologyReports() {
        return radiologyReports;
    }

    /**
     * Sets the radiology reports.
     *
     * @param radiologyReports the new radiology reports
     */
    public void setRadiologyReports(Boolean radiologyReports) {
        this.radiologyReports = radiologyReports;
    }

    /**
     * Gets the referral letters.
     *
     * @return the referral letters
     */
    public Boolean getReferralLetters() {
        return referralLetters;
    }

    /**
     * Sets the referral letters.
     *
     * @param referralLetters the new referral letters
     */
    public void setReferralLetters(Boolean referralLetters) {
        this.referralLetters = referralLetters;
    }

    /**
     * Gets the irb report.
     *
     * @return the irb report
     */
    public Boolean getIrbReport() {
        return irbReport;
    }

    /**
     * Sets the irb report.
     *
     * @param irbReport the new irb report
     */
    public void setIrbReport(Boolean irbReport) {
        this.irbReport = irbReport;
    }


    /**
     * Copy.
     *
     * @return the additional information
     */
    public AdditionalInformation copy() {
        AdditionalInformation additionalInformation = new AdditionalInformation();
        BeanUtils.copyProperties(this, additionalInformation, new String[]{"id", "gridId",
                "version", "report"});

        return additionalInformation;

    }


}
