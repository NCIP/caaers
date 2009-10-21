package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions;
import gov.nih.nci.cabig.caaers.service.migrator.ReportDefinitionConverter;
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
    	
    	
    	if (reportDefinitionDao.getByName(reportDefName) != null){
    		StringBuffer message = new StringBuffer(reportDefName);
    		message.append("\n");
    		message.append("Exists in caAERS");
//    		importRuleCommand.setMessage(message.toString());
    		command.setErrorMessage(message.toString());
    		command.setUpdated(true);
        	ModelAndView modelAndView = new ModelAndView(getFormView(), errors.getModel());
            return modelAndView;
        }
    	
    	ReportDefinition reportDefinition = reportDefinitionConverter.dtoToDomain(reportDefinitions.getReportDefinition().get(0));
    	reportDefinitionDao.save(reportDefinition);
    	StringBuffer messageSb = new StringBuffer(reportDefinition.getName());
    	messageSb.append("\n");
    	messageSb.append("Imported Successfully !");
    	command.setMessage(messageSb.toString());
    	command.setUpdated(true);
    	command.setReportCalendarTemplateList(reportDefinitionDao.getAll());
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
    

}
