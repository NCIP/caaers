package gov.nih.nci.cabig.caaers.utils;

import java.io.IOException;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.ValidatorHandler;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.exchange.InMessage;
import org.codehaus.xfire.handler.AbstractHandler;
import org.codehaus.xfire.handler.Phase;
import org.codehaus.xfire.soap.handler.ReadHeadersHandler;
import org.codehaus.xfire.util.jdom.StaxBuilder;
import org.codehaus.xfire.util.stax.JDOMStreamReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.SAXOutputter;
import org.jdom.output.XMLOutputter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.xml.sax.SAXException;

public class XFireSchemaValidationHandler extends AbstractHandler {
    
    private Schema schema = null;
    private StreamSource ss = null;
    private SchemaFactory factory = null;
    
    public XFireSchemaValidationHandler() throws SAXException, IOException{
        super();
        setPhase(Phase.PARSE);
        before(ReadHeadersHandler.class.getName());
        factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    }

	public void invoke(MessageContext ctx) throws Exception {
        InMessage message = ctx.getInMessage();
        XMLStreamReader streamReader = message.getXMLStreamReader();
        StaxBuilder builder = new StaxBuilder();
        Document doc = builder.build(streamReader);
        
        Element payload = getPayLoad(doc.getRootElement());
        
        if(schema != null){
            // dump the message for testing purposes
            XMLOutputter outputter = 
                new XMLOutputter(Format.getPrettyFormat());
            outputter.output(payload, System.out);
            
            // create validation handler from the pre-complied schema
            ValidatorHandler vh = schema.newValidatorHandler();
            // the handler only works with SAX events, so we create 
            // SAX from JDom 
            SAXOutputter so = new SAXOutputter(vh);
            
            // Validator will run as a SAX handler and throw an exception
            // if the validation fails.
            so.output(payload);
            
            System.out.println("\nValidation passed");
            // rewind the stream reader for subsequent processing 
            message.setXMLStreamReader( new JDOMStreamReader( doc) );
        }
	}
	
	private Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
	
	private Element getPayLoad(Element element) throws Exception{
		Element payLoad = null;
		if(element.getChildren() != null && element.getChildren().size() > 0){
			List<Element> elements = element.getChildren();
			for(Element eachElement : elements){
				String elementName = eachElement.getName();
				String elementNamespaceURI = eachElement.getNamespaceURI();
				String investigatorNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/investigator";
				String researchStaffNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/researchstaff";
				if("studies".equalsIgnoreCase(elementName)){
					payLoad = eachElement;
					ss = new StreamSource(getResources("classpath:gov/nih/nci/cabig/caaers/StudySchema.xsd")[0].getFile());
		        	schema = factory.newSchema(ss);
					break;
				}else if("participants".equalsIgnoreCase(elementName)){
					payLoad = eachElement;
					ss = new StreamSource(getResources("classpath:gov/nih/nci/cabig/caaers/ParticipantSchema.xsd")[0].getFile());
		        	schema = factory.newSchema(ss);
					break;
				}else if("staff".equalsIgnoreCase(elementName) && investigatorNamespace.equalsIgnoreCase(elementNamespaceURI)){
					payLoad = eachElement;
					ss = new StreamSource(getResources("classpath:gov/nih/nci/cabig/caaers/Investigator.xsd")[0].getFile());
		        	schema = factory.newSchema(ss);
					break;
				}else if("staff".equalsIgnoreCase(elementName) && researchStaffNamespace.equalsIgnoreCase(elementNamespaceURI)){
					payLoad = eachElement;
					ss = new StreamSource(getResources("classpath:gov/nih/nci/cabig/caaers/ResearchStaff.xsd")[0].getFile());
		        	schema = factory.newSchema(ss);
					break;
				}else{
					if(payLoad == null){
						payLoad = getPayLoad(eachElement);
					}
				}
			}
		}
		return payLoad;
	}
}