package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.business.service.RulesEngineService;
import gov.nih.nci.cabig.caaers.rules.business.service.RulesEngineServiceImpl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class ExportRuleController extends SimpleFormController {
	
	public ExportRuleController() {
        setCommandClass(ExportRuleCommand.class);
        setBindOnNewForm(true);
        setFormView("rule/author/export");
        setSuccessView("rule/author/export");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	ExportRuleCommand command =  new ExportRuleCommand();
    	command.setFolder(request.getParameter("exportDir"));
    	return command;
    }


	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
		
		ExportRuleCommand exportRuleCommand =  (ExportRuleCommand)command;
		exportRuleCommand.setUpdated(true);
		
		if (validate(exportRuleCommand)) {
			RulesEngineService rulesEngineService = new RulesEngineServiceImpl();
			rulesEngineService.exportRules(exportRuleCommand.getFolder());
			exportRuleCommand.setMessage("Rules exported successfully");

		} 

		ModelAndView modelAndView= new ModelAndView(getSuccessView(), errors.getModel());
		
		return modelAndView;
		
    }    

    private boolean validate(ExportRuleCommand command) {
    	boolean valid = false;
    	
    	File file = new File(command.getFolder());
    	
    	if (file.isDirectory()) {
    		if (file.listFiles().length > 0 ) {
    			command.setMessage( command.getFolder() + "  - is not an empty directory");
    		} else {
    			valid = true;
    		}
    			
    	} else {
    		command.setMessage( command.getFolder() + "  - is not a valid directory");
    	}
    	
    	return valid;

    }
    
}