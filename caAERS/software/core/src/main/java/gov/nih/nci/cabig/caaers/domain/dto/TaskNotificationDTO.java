package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
* @author Ion C. Olaru
*
* */
public class TaskNotificationDTO implements Serializable {

    private Integer reportingPeriodId;
    private String description;
    private String status;
    private String task;
    private String subjectFullName;
    private String studyShortTitle;
    private Date date;
    private List<String> possibleActions;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSubjectFullName() {
        return subjectFullName;
    }

    public void setSubjectFullName(String subjectFullName) {
        this.subjectFullName = subjectFullName;
    }

    public String getStudyShortTitle() {
        return studyShortTitle;
    }

    public void setStudyShortTitle(String studyShortTitle) {
        this.studyShortTitle = studyShortTitle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReportingPeriodId() {
        return reportingPeriodId;
    }

    public void setReportingPeriodId(Integer reportingPeriodId) {
        this.reportingPeriodId = reportingPeriodId;
    }

    public List<String> getPossibleActions() {
        return possibleActions;
    }

    public void setPossibleActions(List<String> possibleActions) {
        this.possibleActions = possibleActions;
    }
}