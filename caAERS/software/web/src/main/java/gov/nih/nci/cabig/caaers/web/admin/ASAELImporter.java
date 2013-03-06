/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dataimport.AgentSpecificTermsImporter;

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
