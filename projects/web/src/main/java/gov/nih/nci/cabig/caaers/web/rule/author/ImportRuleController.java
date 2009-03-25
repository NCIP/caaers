package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class ImportRuleController extends SimpleFormController {
	
	private CaaersRulesEngineService caaersRulesEngineService;
	
    public ImportRuleController() {
        setCommandClass(ImportRuleCommand.class);
        setBindOnNewForm(true);
        setFormView("rule/author/import");
        setSuccessView("rule/author/import");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        ImportRuleCommand command = new ImportRuleCommand();
        command.setFolder(request.getParameter("importDir"));
        return command;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
                    Object command, BindException errors) throws Exception {

        ImportRuleCommand importRuleCommand = (ImportRuleCommand) command;
        importRuleCommand.setUpdated(true);
        if (validate(importRuleCommand)) {
            File ruleSetFile1 = File.createTempFile("ruleset", "import.xml");
            FileCopyUtils.copy(importRuleCommand.getRuleSetFile1().getInputStream(),
                            new FileOutputStream(ruleSetFile1));
            StringBuffer sb = new StringBuffer();
            try {
                List<String> rds = caaersRulesEngineService.importRules(ruleSetFile1.getAbsolutePath());
                if (rds.size() > 0) {

                    sb
                                    .append("Following report definitions are created with basic information.<br/>");
                    sb.append("Please update these report definitions<br/>");
                    for (String rd : rds) {
                        sb.append(rd + "<br/>");
                    }

                }
                importRuleCommand.setMessage("Rules imported successfully <br/>" + sb.toString());
            } catch (EOFException ex) {
                System.out.println("EndOfFile Reached");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException("Class Not found Exception", ex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException("File Not found Exception", ex);
            } catch (IOException ex) {
                throw new RuntimeException("IO Exception", ex);
            }

        }

        /*
         * if (validate(importRuleCommand)) { //RulesEngineService rulesEngineService = new
         * RulesEngineServiceImpl(); RulesEngineService rulesEngineService = (RulesEngineService)
         * getApplicationContext().getBean("ruleEngineService");
         * 
         * File file = new File(importRuleCommand.getFolder()); File[] list = file.listFiles();
         * String fileName = ""; StringBuffer sb = new StringBuffer(); for (int i=0;i<list.length;i++) {
         * fileName = ((File)list[i]).getAbsolutePath(); if (fileName.endsWith(".xml")) {
         * System.out.println(" importing " + fileName); try { List<String> rds =
         * rulesEngineService.importRules(fileName); if (rds.size() > 0 ) {
         * 
         * sb.append("Following report definitions are created with basic information.<br/>");
         * sb.append("Please update these report definitions<br/>"); for (String rd : rds) {
         * sb.append(rd + "<br/>"); }
         *  } } catch (Exception e) { logger.error(" Import Ruleset failed : " + fileName , e ); } } }
         * importRuleCommand.setMessage("Rules imported successfully <br/>" + sb.toString());
         *  }
         */
        ModelAndView modelAndView = new ModelAndView(getSuccessView(), errors.getModel());

        return modelAndView;

    }

    private boolean validate(ImportRuleCommand command) {
        // boolean valid = false;
        boolean isEmpty = command.getRuleSetFile1().isEmpty();
        if (isEmpty) {
            command.setMessage("Please choose either a stuy or a participant file");
        }

        /*
         * File file = new File(command.getFolder());
         * 
         * if (file.isDirectory()) { if (file.listFiles().length == 0 ) { command.setMessage(
         * command.getFolder() + " - no rules sets found in this directory"); } else { valid = true; }
         *  } else { command.setMessage( command.getFolder() + " - is not a valid directory"); }
         */
        // return valid;
        return !isEmpty;

    }

	public CaaersRulesEngineService getCaaersRulesEngineService() {
		return caaersRulesEngineService;
	}

	public void setCaaersRulesEngineService(
			CaaersRulesEngineService caaersRulesEngineService) {
		this.caaersRulesEngineService = caaersRulesEngineService;
	}

}
