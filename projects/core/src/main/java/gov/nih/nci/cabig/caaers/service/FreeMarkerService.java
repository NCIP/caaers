package gov.nih.nci.cabig.caaers.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * 
 * @author Biju Joseph
 *
 */
public class FreeMarkerService {

	public String applyRuntimeReplacementsForReport(String rawText, Map<Object, Object> variableMap) {
	   if(rawText == null) return "";
	   
		Configuration cfg = new Configuration();
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
