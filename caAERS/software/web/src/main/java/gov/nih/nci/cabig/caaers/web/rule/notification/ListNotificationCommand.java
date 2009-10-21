package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ListNotificationCommand {

    private List<ReportDefinition> reportDefinitionList;
    
    // Attributes for import report definition feature
    private String folder;
    private String message;
    private String errorMessage;
    
    private boolean updated = false;

    private MultipartFile ruleSetFile1;
    
    // Done with attributes for import report definition feature.

    public ListNotificationCommand(ReportDefinitionDao rctDao) {
    }

    /**
     * @return the reportCalendarList
     */
    public List<ReportDefinition> getReportCalendarTemplateList() {
        return reportDefinitionList;
    }

    /**
     * @param reportCalendarList
     *                the reportCalendarList to set
     */
    public void setReportCalendarTemplateList(List<ReportDefinition> reportCalendarList) {
        this.reportDefinitionList = reportCalendarList;
    }
    
    // Getters and setters of attributes for import report definition
    public MultipartFile getRuleSetFile1() {
        return ruleSetFile1;
    }

    public void setRuleSetFile1(MultipartFile ruleSetFile1) {
        this.ruleSetFile1 = ruleSetFile1;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    // Done with getters and setters of attributes for import report definition

}
