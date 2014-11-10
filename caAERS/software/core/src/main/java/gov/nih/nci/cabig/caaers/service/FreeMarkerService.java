/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Biju Joseph
 *
 */
public class FreeMarkerService {
    protected final Log log = LogFactory.getLog(FreeMarkerService.class);
	/**
	 * applyRuntimeReplacementsForReport
	 * @param rawText
	 * @param variableMap
	 * @return
	 */
	public String applyRuntimeReplacementsForReport(final String rawText, final Map<Object, Object> variableMap) {
	   if(rawText == null) return "";
	   
		Configuration cfg = new Configuration();
        cfg.setTemplateExceptionHandler(new TemplateExceptionHandler() {
            public void handleTemplateException(TemplateException e, Environment environment, Writer writer) throws TemplateException {
                 log.error("Error while replacing variables", e);
                 if(log.isDebugEnabled()) {
                     log.debug("rawText :" + rawText);
                     log.debug("variableMap :" + String.valueOf(variableMap));
                 }
                 try {writer.write("_");}catch (IOException ignore) {}
            }
        });
	    try {
	        Template t = new Template("message", new StringReader(rawText), cfg);
	        
	        StringWriter writer = new StringWriter();
	        t.process(variableMap, writer);
	        return writer.toString();
	    } catch (TemplateException e) {
	        throw new CaaersSystemException("Error while applying freemarker template", e);
	    } catch (IOException e) {
	        throw new CaaersSystemException("Error while applying freemarker template", e);
	    }
	}
	
}
