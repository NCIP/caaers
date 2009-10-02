package gov.nih.nci.cabig.caaers.web.rule.author;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.semanticbits.rules.api.RulesEngineService;
import com.semanticbits.rules.utils.RuleUtil;

public class ExportRuleController extends AbstractCommandController {

    public ExportRuleController() {
        setCommandClass(ExportRuleCommand.class);
        // setBindOnNewForm(true);
        // setFormView("rule/author/export");
        // setSuccessView("rule/author/export");

    }

    @Override
    protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
                    Object arg2, BindException arg3) throws Exception {
        // System.out.println("IN HANDLE -- " + request.getParameter("ruleSetName"));

        String tempDir = System.getProperty("java.io.tmpdir");
        String ruleSetName = request.getParameter("ruleSetName");
        try {
            // File ruleSetFile1 = File.createTempFile(ruleSetName,"export.xml");
            try {
            	
            	String fileName = "rules_" + System.currentTimeMillis() + ".xml";
            	
                RulesEngineService rulesEngineService = (RulesEngineService) getApplicationContext().getBean("ruleEngineService");
                rulesEngineService.exportRule(ruleSetName, tempDir);
                File file = new File(tempDir + File.separator + fileName);
                FileInputStream fileIn = new FileInputStream(file);

                response.setContentType("application/xml");
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                response.setHeader("Content-length", String.valueOf(file.length()));
                response.setHeader("Pragma", "private");
                response.setHeader("Cache-control", "private, must-revalidate");

                OutputStream out = response.getOutputStream();

                byte[] buffer = new byte[2048];
                int bytesRead = fileIn.read(buffer);
                while (bytesRead >= 0) {
                    if (bytesRead > 0) out.write(buffer, 0, bytesRead);
                    bytesRead = fileIn.read(buffer);
                }
                out.flush();
                out.close();
                fileIn.close();

                // Reader input = new BufferedReader( new
                // FileReader(tempDir+File.separator+RuleUtil.getStringWithoutSpaces(ruleSetName)+".xml"));

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RemoteException("Error exporting ruleset ", e);
            }

            // input = new BufferedReader( new FileReader(xmlFile) );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
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