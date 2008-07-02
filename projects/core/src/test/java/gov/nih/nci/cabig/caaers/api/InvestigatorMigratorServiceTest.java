package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InvestigatorMigratorServiceTest extends CaaersDbTestCase {

    public String getTestDataFileName() {
        String fileName = "testdata/InvestigatorMigratorServiceTest.xml";
        return fileName;
    }

    private void validateAgainstSchema(File xmlFile, String xsdUrl) {
        try {
            // parse an XML document into a DOM tree
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = parser.parse(xmlFile);

            // create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // load a WXS schema, represented by a Schema instance
             Source schemaFile = new StreamSource(new File(xsdUrl));
            
            //Source schemaFile = new StreamSource(getApplicationContext().getResource(xsdUrl)
              //              .getFile());
            Schema schema = factory.newSchema(schemaFile);

            // create a Validator instance, which can be used to validate an instance document
            Validator validator = schema.newValidator();

            // validate the DOM tree

            validator.validate(new DOMSource(document));
            // return xmlFile;
        } catch (FileNotFoundException ex) {
            throw new CaaersSystemException("File Not found Exception", ex);
        } catch (IOException ioe) {
            throw new CaaersSystemException(ioe);
        } catch (SAXParseException spe) {
        	spe.printStackTrace();
        	throw new CaaersSystemException(spe);
        } catch (SAXException e) {        	
            throw new CaaersSystemException(e);
        } catch (ParserConfigurationException pce) {
            throw new CaaersSystemException("Parser configuration exception ", pce);
        }
        
        System.out.println("VALIDATED - SCHEMA");
    }

    
	public void testSaveInvestigator() {
//		InvestigatorMigratorService svc = (InvestigatorMigratorService) getApplicationContext()
//        .getBean("investigatorMigratorService");
//
//		try {
//			File xmlFile = new File ("/Users/sakkala/tech/caaers/InvestigatorMigratorServiceTest0.xml");//getResources("/schema/integration/investigator.xml")[0].getFile();
//
//			validateAgainstSchema(xmlFile,"/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/schema/integration/Investigator.xsd");
//			/*
//			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.investigator");
//			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//			Staff staff = (Staff)unmarshaller.unmarshal(xmlFile);
//
//			svc.saveInvestigator(staff);
//			*/
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Error running test: " + e.getMessage());
//		}
	}
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
}
