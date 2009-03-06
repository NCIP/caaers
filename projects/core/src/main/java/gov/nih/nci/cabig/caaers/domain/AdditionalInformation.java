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

    private Boolean autopsyReport = false;

    private Boolean consults = false;

    private Boolean dischargeSummary = false;

    private Boolean flowCharts = false;

    private Boolean labReports = false;

    private Boolean obaForm = false;

    private Boolean other = false;

    private Boolean pathologyReport = false;

    private Boolean progressNotes = false;

    private Boolean radiologyReports = false;

    private Boolean referralLetters = false;

    private Boolean irbReport = false;

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
     * @param autopsyReport
     */
    public void setAutopsyReport(Boolean autopsyReport) {
        this.autopsyReport = autopsyReport;
    }

    public Boolean getConsults() {
        return consults;
    }

    public void setConsults(Boolean consults) {
        this.consults = consults;
    }

    public Boolean getDischargeSummary() {
        return dischargeSummary;
    }

    public void setDischargeSummary(Boolean dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }

    public Boolean getFlowCharts() {
        return flowCharts;
    }

    public void setFlowCharts(Boolean flowCharts) {
        this.flowCharts = flowCharts;
    }

    public Boolean getLabReports() {
        return labReports;
    }

    public void setLabReports(Boolean labReports) {
        this.labReports = labReports;
    }

    public Boolean getObaForm() {
        return obaForm;
    }

    public void setObaForm(Boolean obaForm) {
        this.obaForm = obaForm;
    }

    public Boolean getOther() {
        return other;
    }

    public void setOther(Boolean other) {
        this.other = other;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public Boolean getPathologyReport() {
        return pathologyReport;
    }

    public void setPathologyReport(Boolean pathologyReport) {
        this.pathologyReport = pathologyReport;
    }

    public Boolean getProgressNotes() {
        return progressNotes;
    }

    public void setProgressNotes(Boolean progressNotes) {
        this.progressNotes = progressNotes;
    }

    public Boolean getRadiologyReports() {
        return radiologyReports;
    }

    public void setRadiologyReports(Boolean radiologyReports) {
        this.radiologyReports = radiologyReports;
    }

    public Boolean getReferralLetters() {
        return referralLetters;
    }

    public void setReferralLetters(Boolean referralLetters) {
        this.referralLetters = referralLetters;
    }

    public Boolean getIrbReport() {
        return irbReport;
    }

    public void setIrbReport(Boolean irbReport) {
        this.irbReport = irbReport;
    }


    public AdditionalInformation copy() {
        AdditionalInformation additionalInformation = new AdditionalInformation();
        BeanUtils.copyProperties(this, additionalInformation, new String[]{"id", "gridId",
                "version", "report"});

        return additionalInformation;

    }


}
