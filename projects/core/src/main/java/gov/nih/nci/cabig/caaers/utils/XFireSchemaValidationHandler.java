package gov.nih.nci.cabig.caaers.utils;

import java.io.IOException;

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

        // Load the schema - note that handler is only
        // instantiated once, so we can keep the schema in 
        // an instance variable
        factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    }

	public void invoke(MessageContext ctx) throws Exception {
        InMessage message = ctx.getInMessage();
        XMLStreamReader streamReader = message.getXMLStreamReader();
        // create JDom from the stream - alternatively we can rely on
        // DOM and XFire DOM handler
        StaxBuilder builder = new StaxBuilder();
        Document doc = builder.build(streamReader);
        // get to the body first
        Element body = 
            (Element)doc.getRootElement().getChildren().get(0);
        
        Element payload = (Element)((Element) (body.getChildren().get(0))).getChildren().get(0);
        
        if(payload.getName().toLowerCase().equals("studies")){
        	ss = new StreamSource(getResources("classpath:gov/nih/nci/cabig/caaers/StudySchema.xsd")[0].getFile());
        	schema = factory.newSchema(ss);
        }else if(payload.getName().toLowerCase().equals("participants")){
        	ss = new StreamSource(getResources("classpath:gov/nih/nci/cabig/caaers/ParticipantSchema.xsd")[0].getFile());
        	schema = factory.newSchema(ss);
        }
        
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
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
}

