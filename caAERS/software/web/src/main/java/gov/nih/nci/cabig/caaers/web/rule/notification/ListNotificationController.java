package gov.nih.nci.cabig.caaers.web.rule.notification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.ReportDefinitionConverter;
import gov.nih.nci.cabig.caaers.service.synchronizer.ReportDefinitionSynchronizer;
import gov.nih.nci.cabig.caaers.web.rule.author.ImportRuleCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * For now list all the notifications from the notifications table.
 * 
 */
public class ListNotificationController extends SimpleFormController {

	private static final String DISABLE_ACTION = "disable";
	private static final String ENABLE_ACTION = "enable";
    protected ReportDefinitionDao reportDefinitionDao;
	protected ReportDefinitionConverter reportDefinitionConverter;
	protected ReportDefinitionSynchronizer reportDefinitionSynchronizer;
	
    public ListNotificationController() {
        setCommandClass(ListNotificationCommand.class);
        setBindOnNewForm(true);
        setFormView("rule/notification/list");
        setSuccessView("rule/notification/list");
    }
    
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object cmd, BindException errors) throws Exception {
    	
    	ListNotificationCommand command = (ListNotificationCommand) cmd;
        
    	if (command.getRuleSetFile1().isEmpty()) {
        	command.setMessage("Please choose a file");
        	command.setUpdated(true);
        	ModelAndView modelAndView = new ModelAndView(getFormView(), errors.getModel());
            return modelAndView;

        }
    	
    	ReportDefinitions reportDefinitions = null;
    	JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.reportdefinition");
    	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    	reportDefinitions = (ReportDefinitions)unmarshaller.unmarshal(command.getRuleSetFile1().getInputStream());
    	String reportDefName = reportDefinitions.getReportDefinition().get(0).getName();
    	ReportDefinition dbReportDefinition = reportDefinitionDao.getByName(reportDefName);
    	ReportDefinition xmlReportDefinition = reportDefinitionConverter.dtoToDomain(reportDefinitions.getReportDefinition().get(0));

    	if (dbReportDefinition != null){
    		DomainObjectImportOutcome<ReportDefinition> outcome = new DomainObjectImportOutcome<ReportDefinition>();
    		reportDefinitionSynchronizer.migrate(xmlReportDefinition, dbReportDefinition, outcome);
    		reportDefinitionDao.save(dbReportDefinition);
    		StringBuffer messageSb = new StringBuffer(dbReportDefinition.getName());
    		messageSb.append("\n");
    		messageSb.append("Updated Successfully");
    		
    		// Provide a warning if the parent name provided was incorrect
    		if(reportDefinitions.getReportDefinition().get(0).getParent() != null && xmlReportDefinition.getParent() == null){
    			messageSb.append("\n");
    			messageSb.append("Warning: Parent report name provided in the xml doesn't exist.");
    		}
    		command.setMessage(messageSb.toString());
        }else{
        	// Handle the case when there is missing organization
        	if(xmlReportDefinition.getOrganization() != null){
        		reportDefinitionDao.save(xmlReportDefinition);
        		StringBuffer messageSb = new StringBuffer(xmlReportDefinition.getName());
        		messageSb.append("\n");
        		messageSb.append("Imported Successfully !");
        		// Provide a warning if the parent name provided was incorrect
        		if(reportDefinitions.getReportDefinition().get(0).getParent() != null && xmlReportDefinition.getParent() == null){
        			messageSb.append("\n");
        			messageSb.append("Warning: Parent report name provided in the xml doesn't exist.");
        		}
        		
        		command.setMessage(messageSb.toString());
        	
        		// Fetch all the reportDefinitions so that the newly imported report definition is also displayed in the list.
        		command.setReportCalendarTemplateList(reportDefinitionDao.getAll());
        	}else{
        		StringBuffer messageSb = new StringBuffer(xmlReportDefinition.getName());
        		messageSb.append("\n");
        		messageSb.append("Import failed as Organization provided in the xml doesn't exist");
        		command.setErrorMessage(messageSb.toString());
        		ModelAndView modelAndView  = new ModelAndView(getFormView(), errors.getModel());
        		return modelAndView;
        	}
        }
    	command.setUpdated(true);
		ModelAndView modelAndView = new ModelAndView(getSuccessView(), errors.getModel());
	    return modelAndView;

    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object cmd,
                    final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        ListNotificationCommand command = (ListNotificationCommand) cmd;
        String rpDefIdObject = request.getParameter("repDefId");
        Integer rpDefId = null;
        if(rpDefIdObject != null)
        	rpDefId = Integer.valueOf(rpDefIdObject);
        String action = request.getParameter("action");
        if(action != null && action.equals(DISABLE_ACTION) && rpDefId != null){
        	for(ReportDefinition rd: command.getReportCalendarTemplateList()){
        		if(rd.getId().equals(rpDefId)){
        			rd.setEnabled(false);
        			reportDefinitionDao.save(rd);
        		}
        	}
        	initializeActiveInactiveLists(command);
        	refDataMap.put("disabled", true);
        }
        if(action != null && action.equals(ENABLE_ACTION) && rpDefId != null){
        	for(ReportDefinition rd: command.getReportCalendarTemplateList()){
        		if(rd.getId().equals(rpDefId)){
        			rd.setEnabled(true);
        			reportDefinitionDao.save(rd);
        		}
        	}
        	initializeActiveInactiveLists(command);
        	refDataMap.put("enabled", true);
        }
        
        return refDataMap;
    }

    @Override
    public Object formBackingObject(HttpServletRequest request) {
    	ListNotificationCommand command = new ListNotificationCommand(reportDefinitionDao);
    	command.setReportCalendarTemplateList(reportDefinitionDao.getAll());
    	initializeActiveInactiveLists(command);
    	command.setFolder(request.getParameter("importDir"));
        return command;
    }
    
    public void initializeActiveInactiveLists(ListNotificationCommand command){
    	List<ReportDefinition> activeReportDefinitionsList = new ArrayList<ReportDefinition>();
    	List<ReportDefinition> inactiveReportDefinitionsList = new ArrayList<ReportDefinition>();
    	
    	for(ReportDefinition rd: command.getReportCalendarTemplateList()){
    		if(rd.getEnabled())
    			activeReportDefinitionsList.add(rd);
    		else
    			inactiveReportDefinitionsList.add(rd);
    	}
    	command.setActiveReportDefinitionsList(activeReportDefinitionsList);
    	command.setInactiveReportDefinitionsList(inactiveReportDefinitionsList);
    }
    
    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
		this.reportDefinitionDao = reportDefinitionDao;
	}

	public void setReportDefinitionConverter(ReportDefinitionConverter reportDefinitionConverter) {
		this.reportDefinitionConverter = reportDefinitionConverter;
	}
    
	public void setReportDefinitionSynchronizer(ReportDefinitionSynchronizer reportDefinitionSynchronizer){
		this.reportDefinitionSynchronizer = reportDefinitionSynchronizer;
	}
}
