package gov.nih.nci.cabig.caaers.web.rule.notification;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * For now list all the notifications from the notifications table.
 * 
 */
public class ListNotificationController extends SimpleFormController {

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

    @Override
    public Object formBackingObject(HttpServletRequest request) {
    	ListNotificationCommand command = new ListNotificationCommand(reportDefinitionDao);
    	command.setReportCalendarTemplateList(reportDefinitionDao.getAll());
    	command.setFolder(request.getParameter("importDir"));
        return command;
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
