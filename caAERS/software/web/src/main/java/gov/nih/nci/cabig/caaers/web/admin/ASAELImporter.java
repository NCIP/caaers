package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dataimport.AgentSpecificTermsImporter;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
* @author Ion C. Olaru
* 
* */
public class ASAELImporter extends Importer {

    private AgentSpecificTermsImporter importer;
    private static final Log logger = LogFactory.getLog(ASAELImporter.class);
    
    @Override
    public void processEntities(File file, ImportCommand command) {
    }

    @Override
    public void save(ImportCommand command, HttpServletRequest request) {
        importer.setFile(command.getXmlFile());
        try {
            Map<String, Object> map =  importer.importFile();
            request.setAttribute("results", map);
        } catch (Exception e) {
        	logger.error(e);
        }
    }

    public AgentSpecificTermsImporter getImporter() {
        return importer;
    }

    public void setImporter(AgentSpecificTermsImporter importer) {
        this.importer = importer;
    }
}
