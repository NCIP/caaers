package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.query.ReportDefinitionExistsQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.list.LazyList;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 22, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public class ReportDefinitionCommand {

    private ConfigProperty configurationProperty;

    // page -4
    private String pointOnScale = "0"; // the selected point in the time scale

    private String lastPointOnScale;

    private String indexToFetch = "0";

    private Map<String, Integer> mandatoryFieldMap;

    // supporting domain objects
    protected ReportDefinition rpDef;

    protected ReportDefinitionDao rpDefDao;

    // flow support variables
    private String indexToDelete; // index in the list to be deleted

    private String tempProperty;

    // hide validation errors
    private boolean hideErrors;
    
    public ReportDefinitionCommand(){
    	
    }
    
    public ReportDefinitionCommand(ReportDefinition rpDef, ReportDefinitionDao rpDefDao, ConfigProperty configurationProperty) {
        this.rpDef = rpDef;
        this.rpDefDao = rpDefDao;
        this.configurationProperty = configurationProperty;
        initializeMandatoryFieldMap();
    }

    @SuppressWarnings("unchecked")
    public List<PlannedNotification> getEmailNotifications() {
        List<PlannedNotification> plannedNotifications = rpDef.fetchPlannedNotification(Integer.parseInt(indexToFetch));
        return LazyList.decorate(plannedNotifications, new PlannedEmailNotificationFactory(this.rpDef.getPlannedNotifications()));
    }

    // /LOGIC
    public void initializeMandatoryFieldMap() {
        mandatoryFieldMap = new LinkedHashMap<String, Integer>();
        String path;
        int i = 0;
        for (ReportMandatoryFieldDefinition mf : rpDef.getMandatoryFields()) {
            path = mf.getFieldPath();
            mandatoryFieldMap.put(path, i);
            i++;
        }
    }

    protected Map<Object, Object> collectRoleOptions() {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        options.putAll(WebUtils.collectOptions(configurationProperty.getMap().get("reportingRolesRefData"), "code", "desc"));
        options.putAll(WebUtils.collectOptions(configurationProperty.getMap().get("invRoleCodeRefData"), "code", "desc"));
        options.putAll(WebUtils.collectOptions(configurationProperty.getMap().get("studyPersonnelRoleRefData"), "code", "desc"));

        return options;
    }

    // /BEAN PROPERTIES

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    /**
     * Will tell the index of the PlannedNotification to be deleted.
     */
    public String getIndexToDelete() {
        return indexToDelete;
    }

    public void setIndexToDelete(String delete) {
        this.indexToDelete = delete;
    }

    public ReportDefinitionDao getReportDefinitionDao() {
        return rpDefDao;
    }

    public void setReportDefinitionDao(ReportDefinitionDao rdDao) {
        this.rpDefDao = rdDao;
    }

    /**
     * The underlying domain object, used by this command object
     * 
     * @return
     */
    public ReportDefinition getReportDefinition() {
        return rpDef;
    }

    public void setReportDefinition(ReportDefinition calendarTemplate) {
        this.rpDef = calendarTemplate;
    }

    public String getPointOnScale() {
        return pointOnScale;
    }

    public void setPointOnScale(String pointOnScale) {
        this.pointOnScale = pointOnScale;
    }

    public String getLastPointOnScale() {
        return lastPointOnScale;
    }

    public void setLastPointOnScale(String lastPointOnScale) {
        this.lastPointOnScale = lastPointOnScale;
    }

    public Map<Object, Object> getRoles() {
        return collectRoleOptions();
    }

    public Map<String, Integer> getMandatoryFieldMap() {
        return mandatoryFieldMap;
    }

    public void setMandatoryFieldMap(Map<String, Integer> mandatoryFieldMap) {
        this.mandatoryFieldMap = mandatoryFieldMap;
    }

    /**
     * @return the indexToFetch
     */
    public String getIndexToFetch() {
        return indexToFetch;
    }

    /**
     * @param indexToFetch
     *                the indexToFetch to set
     */
    public void setIndexToFetch(String indexToFetch) {
        this.indexToFetch = indexToFetch;
    }

    public String getTempProperty() {
        return tempProperty;
    }

    public void setTempProperty(String tempProperty) {
        this.tempProperty = tempProperty;
    }

    public void setHideErrors(boolean hideErrors) {
        this.hideErrors = hideErrors;
    }

    public boolean getHideErrors() {
        return this.hideErrors;
    }
    
    public boolean isSimilarReportDefinitionExist(ReportDefinition reportDef){
    	ReportDefinitionExistsQuery query = new ReportDefinitionExistsQuery();
    	query.filterByDifferentId(reportDef.getId());
    	query.filterByName(reportDef.getName());
    	int cnt = rpDefDao.noOfSimilarReportDefinitions(query);
    	return cnt > 0;
    }

    class PlannedEmailNotificationFactory<T extends PlannedNotification> implements Factory<T> {
        private List<PlannedNotification> plannedNotificationList;

        public PlannedEmailNotificationFactory(List<PlannedNotification> plannedNotificationList) {
            this.plannedNotificationList = plannedNotificationList;
        }

        @SuppressWarnings("unchecked")
        public T create() {
            PlannedEmailNotification nf = new PlannedEmailNotification();
            NotificationBodyContent bodyContent = new NotificationBodyContent();
            nf.setNotificationBodyContent(bodyContent);
            nf.setRecipients(new ArrayList<Recipient>());
            this.plannedNotificationList.add(nf);
            return (T) nf;
        }
    }

}