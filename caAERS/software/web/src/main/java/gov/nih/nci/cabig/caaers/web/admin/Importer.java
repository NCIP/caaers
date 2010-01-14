package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Sameer Sawant
 */
public abstract class Importer{
	
	static final String STUDY_IMPORT = "study";
	static final String SUBJECT_IMPORT = "participant";
	static final String RESEARCH_STAFF_IMPORT = "researchStaff";
	static final String INVESTIGATOR_IMPORT = "investigator";
	
	private static Logger logger = Logger.getLogger(Importer.class);
	
	// The child classes will override this method to implement specific logic.
	public abstract void processEntities(File xmlFile,ImportCommand command);
	
	// The child classes will override this method to save the respecive domain objects.
	public abstract void save(ImportCommand command, HttpServletRequest request);
	
	public boolean validateAgainstSchema(File xmlFile, ImportCommand command, String xsdUrl) {
    	boolean validXml = false;
    	try {
            // parse an XML document into a DOM tree
        	
        	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        	documentBuilderFactory.setValidating(false);
        	documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder parser = documentBuilderFactory.newDocumentBuilder();
            Document document = parser.parse(xmlFile);
            // create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // load a WXS schema, represented by a Schema instance

            // load a WXS schema, represented by a Schema instance
            Source schemaFile = new StreamSource(getResources(xsdUrl)[0].getFile());
            
            Schema schema = schemaFactory.newSchema(schemaFile);

            // create a Validator instance, which can be used to validate an instance document
            Validator validator = schema.newValidator();

            // validate the DOM tree

            validator.validate(new DOMSource(document));
            validXml = true;
            // return xmlFile;
        } catch (FileNotFoundException ex) {
            throw new CaaersSystemException("File Not found Exception", ex);
        } catch (IOException ioe) {
            command.setSchemaValidationResult(ioe.getMessage());
            throw new CaaersSystemException(ioe);
        } catch (SAXParseException spe) {
            command.setSchemaValidationResult("Line : " + spe.getLineNumber() + " - "
                            + spe.getMessage());
        } catch (SAXException e) {
            command.setSchemaValidationResult(e.toString());
            throw new CaaersSystemException(e);
        } catch (ParserConfigurationException pce) {
            throw new CaaersSystemException("Parser configuration exception ", pce);
        }
        return validXml;
    }
	
	public Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        if (logger.isDebugEnabled()) logger.debug("Looking for resources matching " + pattern);
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    } 
	
	public String getXSDLocation(String type) {
        if ("study".equals(type)) {
        	return "classpath:gov/nih/nci/cabig/caaers/StudySchema.xsd";
        }
        if ("participant".equals(type)) {
        	return "classpath:gov/nih/nci/cabig/caaers/ParticipantSchema.xsd";
        }
        if ("routineAeReport".equals(type)) {
            return "classpath:gov/nih/nci/cabig/caaers/routineAeXSD.xsd";
        }
        if ("investigator".equals(type)) {
            return "classpath:gov/nih/nci/cabig/caaers/Investigator.xsd";
        }  
        if ("researchStaff".equals(type)) {
            return "classpath:gov/nih/nci/cabig/caaers/ResearchStaff.xsd";
        }        
        return null;
    }
}