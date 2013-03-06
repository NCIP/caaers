/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.RuleSetDao;
import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ExportRuleController extends AbstractCommandController {

    private CaaersRulesEngineService caaersRulesEngineService;
    private RuleSetDao ruleSetDao;


    public ExportRuleController() {
        setCommandClass(ExportRuleCommand.class);
    }

    @Override
    protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
                    Object arg2, BindException arg3) throws Exception {

        String ruleSetName = request.getParameter("ruleSetName");

        try {

            RuleSet ruleSet = ruleSetDao.getByBindURI(ruleSetName);
            String fileName = ruleSet.shortReadableName() + ".xml";

            String xml = caaersRulesEngineService.exportRules(ruleSetName);

            response.setContentType("application/xml");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-length", String.valueOf(xml.length()));
            response.setHeader("Pragma", "private");
            response.setHeader("Cache-control", "private, must-revalidate");
            PrintWriter out = response.getWriter();
            
            IOUtils.write(xml, out);
            IOUtils.closeQuietly(out);
            
        } catch (Exception e) {
            logger.error("Unable to export rules file" , e);
            throw new CaaersSystemException("Error exporting ruleset [" + e.getMessage() + "]", e);
        }
        return null;
    }

    @Required
    public CaaersRulesEngineService getCaaersRulesEngineService() {
        return caaersRulesEngineService;
    }

    public void setCaaersRulesEngineService(CaaersRulesEngineService caaersRulesEngineService) {
        this.caaersRulesEngineService = caaersRulesEngineService;
    }

    @Required
    public RuleSetDao getRuleSetDao() {
        return ruleSetDao;
    }

    public void setRuleSetDao(RuleSetDao ruleSetDao) {
        this.ruleSetDao = ruleSetDao;
    }

    /*
    * @Override protected Object formBackingObject(HttpServletRequest request) throws Exception {
    * ExportRuleCommand command = new ExportRuleCommand();
    * //command.setFolder(request.getParameter("exportDir")); return command; }
    *
    * @Override protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse
    * response, Object command, BindException errors) throws Exception {
    *
    * System.out.println("IN SUBMIT ... " + request.getParameter("ruleSetName"));
    *
    *
    * ModelAndView modelAndView= new ModelAndView(getSuccessView(), errors.getModel());
    *
    * return modelAndView;
    *  }
    *
    *  /* @Override protected Object formBackingObject(HttpServletRequest request) throws Exception {
    * ExportRuleCommand command = new ExportRuleCommand();
    * command.setFolder(request.getParameter("exportDir")); return command; }
    *
    *
    * @Override protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse
    * response, Object command, BindException errors) throws Exception {
    *
    * ExportRuleCommand exportRuleCommand = (ExportRuleCommand)command;
    * exportRuleCommand.setUpdated(true);
    *
    * if (validate(exportRuleCommand)) { RulesEngineService rulesEngineService = new
    * RulesEngineServiceImpl(); rulesEngineService.exportRules(exportRuleCommand.getFolder());
    * exportRuleCommand.setMessage("Rules exported successfully");
    *  }
    *
    * ModelAndView modelAndView= new ModelAndView(getSuccessView(), errors.getModel());
    *
    * return modelAndView;
    *  }
    *
    * private boolean validate(ExportRuleCommand command) { boolean valid = false;
    *
    * File file = new File(command.getFolder());
    *
    * if (file.isDirectory()) { if (file.listFiles().length > 0 ) { command.setMessage(
    * command.getFolder() + " - is not an empty directory"); } else { valid = true; }
    *  } else { command.setMessage( command.getFolder() + " - is not a valid directory"); }
    *
    * return valid;
    *  }
    */
}
