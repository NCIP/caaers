package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This class provides a static method to validate an XMLFile against a Schema.
 * 
 * @author Monish Dombla
 *
 */
public class XmlValidator {
	
	private static Log logger = LogFactory.getLog(XmlValidator.class);
	
	public static boolean  validateAgainstSchema(String xmlContent, String xsdUrl,StringBuffer validationResult) {
		boolean validXml = false;
        try {
            // parse an XML document into a DOM tree
        	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        	documentBuilderFactory.setValidating(false);
        	documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder parser = documentBuilderFactory.newDocumentBuilder();
            Document document = parser.parse(new InputSource(new StringReader(xmlContent)));

            // create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // load a WXS schema, represented by a Schema instance
            Source schemaFile = new StreamSource(getResources(xsdUrl)[0].getFile());
            
            Schema schema = schemaFactory.newSchema(schemaFile);
            // create a Validator instance, which can be used to validate an instance document
            Validator validator = schema.newValidator();
            // validate the DOM tree
            validator.validate(new DOMSource(document));
            validXml = true;
        } catch (FileNotFoundException ex) {
            throw new CaaersSystemException("File Not found Exception", ex);
        } catch (IOException ioe) {
        	validationResult.append(ioe.getMessage());
        	logger.error(ioe.getMessage());
        } catch (SAXParseException spe) {
        	validationResult.append("Line : " + spe.getLineNumber() + " - "
                    + spe.getMessage());
        	logger.error("Line : " + spe.getLineNumber() + " - "+ spe.getMessage());
        } catch (SAXException e) {
        	validationResult.append(e.toString());
        	logger.error(e.toString());
        } catch (ParserConfigurationException pce) {
        	validationResult.append(pce.getMessage());
        }
        return validXml;
    }
	
	
	/**
	 * This method fetches the specified resource pattern from classpath.
	 * In this context used to fetch xsd files.
	 * @param pattern
	 * @return
	 * @throws IOException
	 */
	public static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        if (logger.isDebugEnabled()) logger.debug("Looking for resources matching " + pattern);
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
}
