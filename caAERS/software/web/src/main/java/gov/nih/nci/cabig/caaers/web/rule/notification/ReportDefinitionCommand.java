package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.query.ReportDefinitionExistsQuery;
import gov.nih.nci.cabig.caaers.dao.query.ReportDefinitionQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportType;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepository;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.list.LazyList;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 22, 2007
 * @author Ion
 * @version %I%, %G%
 * @since 1.0
 */
public class ReportDefinitionCommand {


    // page -4
    private String pointOnScale = "0"; // the selected point in the time scale
    private String lastPointOnScale;
    private String indexToFetch = "0";
    private Map<String, Integer> mandatoryFieldMap;

    // supporting domain objects
    protected ReportDefinition rpDef;
    protected ReportDefinitionDao rpDefDao;
    protected ConfigPropertyRepository cpRepository;

    // flow support variables                                  c
    private String indexToDelete; // index in the list to be deleted
    private String tempProperty;

    // hide validation errors
    private boolean hideErrors;

    protected Map<Object, Object> groupOptions;
    protected Map<Object, Object> reportTypeOptions;
    
    protected Map<Object, Object> parentOptions;
    
    protected Map<Object, Object> recipientRoleOptions;

    public ReportDefinitionCommand(){
    }
    
    public ReportDefinitionCommand(ReportDefinition rpDef, ReportDefinitionDao rpDefDao, ConfigPropertyRepository cpRepo) {
        this.rpDef = rpDef;
        this.rpDefDao = rpDefDao;
        this.cpRepository = cpRepo;
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
        if(CollectionUtils.isNotEmpty(rpDef.getMandatoryFields())){
        	String path;
            int i = 0;
            for (ReportMandatoryFieldDefinition mf : rpDef.getMandatoryFields()) {
                path = mf.getFieldPath();
                mandatoryFieldMap.put(path, i);
                i++;
            }
        }
    }
     
    protected Map<Object, Object> collectReportTypeOptions(){
    	if(reportTypeOptions == null){
    		reportTypeOptions = WebUtils.collectOptions(ReportType.values(), "Please select");
    	}
    	return reportTypeOptions;
    }
    
    /**
     * Will populate the roles that is to be displayed in email notification recipient drop-down
     * @return
     */
    protected Map<Object, Object> collectRecipientRoleOptions() {
    	if(recipientRoleOptions == null){
    		recipientRoleOptions = new LinkedHashMap<Object, Object>();
    		recipientRoleOptions.put("", "Please select");
    		recipientRoleOptions.putAll(WebUtils.collectOptions(cpRepository.getByType(ConfigPropertyType.REPORT_ROLE_TYPE), "code", "name"));
    		recipientRoleOptions.putAll(WebUtils.collectOptions(cpRepository.getByType(ConfigPropertyType.RESEARCH_STAFF_ROLE_TYPE), "code", "name"));
    		recipientRoleOptions.putAll(WebUtils.collectOptions(cpRepository.getByType(ConfigPropertyType.INVESTIGATOR_ROLE_TYPE), "code", "name"));
    	}
        return recipientRoleOptions;
    }
    
    /**
     * Will populate the report group options. 
     */
    public void refreshGroupOptions(){
    	groupOptions = WebUtils.collectOptions(cpRepository.getByType(ConfigPropertyType.REPORT_GROUP), "id", "name", "Please select");
    	groupOptions.put(" ", "Create New");
    }
    
    /**
     * This method will refresh the parentOptions. 
     * @param organizationID
     */
    public void refreshParentOptions(Integer organizationID){
    	List<ReportDefinition> rdList = new ArrayList<ReportDefinition>();
    	
    	if(organizationID != null){
    		ReportDefinitionQuery query = new ReportDefinitionQuery();
        	query.filterByOrganizationId(organizationID);
        	query.filterOffReportDefinitionId(this.rpDef.getId());
        	rdList = (List<ReportDefinition>)rpDefDao.search(query);
    	}
    	
    	parentOptions = WebUtils.collectOptions(rdList, "id", "name", "Please select");
    }

    // /BEAN PROPERTIES


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
        return collectRecipientRoleOptions();
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

    public ConfigPropertyRepository getCpRepository() {
        return cpRepository;
    }

    public void setCpRepository(ConfigPropertyRepository cpRepository) {
        this.cpRepository = cpRepository;
    }

    public Map<Object, Object> getGroupOptions() {
        return groupOptions;
    }

    public void setGroupOptions(Map<Object, Object> reportTypeOptions) {
        this.groupOptions = reportTypeOptions;
    }

    public Map<Object, Object> getParentOptions() {
        return parentOptions;
    }

    public void setParentOptions(Map<Object, Object> parentOptions) {
        this.parentOptions = parentOptions;
    }
}