package gov.nih.nci.cabig.caaers.web.admin;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

/*
* @author Ion C. Olaru
* 
* */
public class ASAELImporter extends Importer {

    private AgentSpecificTermsImporter importer;
    
    @Override
    public void processEntities(File file, ImportCommand command) {
    }

    @Override
    public void save(ImportCommand command, HttpServletRequest request) {
        System.out.println("Save...");
        importer.setFile(command.getXmlFile());
        try {
            Map<String, Object> map =  importer.importFile();
            request.setAttribute("results", map);
        } catch (Exception e) {
        }
    }

    public AgentSpecificTermsImporter getImporter() {
        return importer;
    }

    public void setImporter(AgentSpecificTermsImporter importer) {
        this.importer = importer;
    }
}
