package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.semanticbits.rules.api.RuleAuthoringService;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Sameer Sawant.
 */
public class ListRuleController extends SimpleFormController {

    private RuleAuthoringService ruleAuthoringService;
    private CaaersRulesEngineService caaersRulesEngineService;

    public ListRuleController() {
        setCommandClass(ListRuleCommand.class);
        setBindOnNewForm(true);
        setFormView("rule/author/list");
        setSuccessView("rule/author/list");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new ListRuleCommand(ruleAuthoringService);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("pageTitle", "Manage Rules: Select Rule to Update");
        return refdata;
    }
    
    
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
                    Object cmd, BindException errors) throws Exception {

        ListRuleCommand command = (ListRuleCommand) cmd;
        command.setUpdated(true);
        if (validate(command)) {
            File ruleSetFile1 = File.createTempFile("ruleset", "import.xml");
            FileCopyUtils.copy(command.getRuleSetFile1().getInputStream(),
                            new FileOutputStream(ruleSetFile1));
            StringBuffer sb = new StringBuffer();
            try {
                List<String> rds = caaersRulesEngineService.importRules(ruleSetFile1.getAbsolutePath());
                if (rds.size() > 0) {

                    sb.append("Following report definitions are created with basic information.<br/>");
                    sb.append("Please update these report definitions<br/>");
                    for (String rd : rds) {
                        sb.append(rd + "<br/>");
                    }

                }
                command.setMessage("Rules imported successfully <br/>" + sb.toString());
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
        // Populate the rulesSets again.
        command.populateRuleSets(ruleAuthoringService);
        ModelAndView modelAndView = new ModelAndView(getSuccessView(), errors.getModel());

        return modelAndView;

    }
    
    private boolean validate(ListRuleCommand command) {
        // boolean valid = false;
        boolean isEmpty = command.getRuleSetFile1().isEmpty();
        if (isEmpty) {
            command.setMessage("Please choose either a stuy or a participant file");
        }

        return !isEmpty;
    }

    public RuleAuthoringService getRuleAuthoringService() {
        return ruleAuthoringService;
    }

    public void setRuleAuthoringService(RuleAuthoringService ruleAuthoringService) {
        this.ruleAuthoringService = ruleAuthoringService;
    }
    
    public CaaersRulesEngineService getCaaersRulesEngineService() {
		return caaersRulesEngineService;
	}

	public void setCaaersRulesEngineService(
			CaaersRulesEngineService caaersRulesEngineService) {
		this.caaersRulesEngineService = caaersRulesEngineService;
	}
}