package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.business.service.RulesEngineService;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class ImportRuleController extends SimpleFormController {
	
	public ImportRuleController() {
        setCommandClass(ImportRuleCommand.class);
        setBindOnNewForm(true);
        setFormView("rule/author/import");
        setSuccessView("rule/author/import");
    }

	
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	ImportRuleCommand command =  new ImportRuleCommand();
    	command.setFolder(request.getParameter("importDir"));
    	return command;
    }


	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, 
			Object command, BindException errors) throws Exception {
		
		ImportRuleCommand importRuleCommand =  (ImportRuleCommand)command;
		importRuleCommand.setUpdated(true);
		
		if (validate(importRuleCommand)) {
			//RulesEngineService rulesEngineService = new RulesEngineServiceImpl();
			RulesEngineService rulesEngineService = (RulesEngineService) getApplicationContext().getBean("ruleEngineService");
			
			File file = new File(importRuleCommand.getFolder());
			File[] list = file.listFiles();
			String fileName = "";
			StringBuffer sb = new StringBuffer();
			for (int i=0;i<list.length;i++) {
				fileName = ((File)list[i]).getAbsolutePath();
				if (fileName.endsWith(".xml")) {
					System.out.println(" importing " + fileName);
					try {
						List<String> rds = rulesEngineService.importRules(fileName);
						if (rds.size() > 0 ) {
							
							sb.append("Following report definitions are created with basic information.<br/>");
							sb.append("Please update these report definitions<br/>");
							for (String rd : rds) {
								sb.append(rd + "<br/>");
							}
							
						}
					} catch (Exception e) {
						logger.error(" Import Ruleset failed  : " + fileName , e );						
					}
				}
			}
			importRuleCommand.setMessage("Rules imported successfully <br/>" + sb.toString());

		} 

		ModelAndView modelAndView= new ModelAndView(getSuccessView(), errors.getModel());
		
		return modelAndView;
		
    }    

    private boolean validate(ImportRuleCommand command) {
    	boolean valid = false;
    	
    	File file = new File(command.getFolder());
    	
    	if (file.isDirectory()) {
    		if (file.listFiles().length == 0 ) {
    			command.setMessage( command.getFolder() + "  - no rules sets found in this directory");
    		} else {
    			valid = true;
    		}
    			
    	} else {
    		command.setMessage( command.getFolder() + "  - is not a valid directory");
    	}
    	
    	return valid;

    }
    

}
