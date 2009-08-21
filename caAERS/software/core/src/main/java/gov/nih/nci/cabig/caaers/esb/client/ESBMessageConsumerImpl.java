package gov.nih.nci.cabig.caaers.esb.client;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ESBMessageConsumerImpl implements ESBMessageConsumer {

	protected final Log log = LogFactory.getLog(getClass());
    
    private ApplicationContext applicationContext;

    private String getResponseType(String message) {
    	SAXBuilder saxBuilder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
        Reader stringReader = new StringReader(message);
        String responseType = "";

        try {
            org.jdom.Document jdomDocument = saxBuilder.build(stringReader);
            org.jdom.Element root = jdomDocument.getRootElement();

            Element body = root.getChild("Body", root.getNamespace());
            Element response = (Element)body.getChildren().get(0);
            responseType=response.getName();

        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseType;
    }
    
    public void processMessage(String message) throws Exception{
        log.debug("ESB Listner - message recieved");
        log.debug(message);
        String responseType = getResponseType(message);
    	ResponseMessageProcessor responseMessageProcessor = (ResponseMessageProcessor)applicationContext.getBean(responseType);
    	responseMessageProcessor.processMessage(message);
    }

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext= applicationContext;
	}

}

class Validator implements ErrorHandler {
    public void warning(SAXParseException exception) throws SAXException {
        // Bring things to a crashing halt
        System.out.println("**Parsing Warning**" + "  Line:    " + exception.getLineNumber() + ""
                        + "  URI:     " + exception.getSystemId() + "" + "  Message: "
                        + exception.getMessage());
        throw new SAXException("Warning encountered");
    }

    public void error(SAXParseException exception) throws SAXException {
        // Bring things to a crashing halt
        System.out.println("**Parsing Error**" + "  Line:    " + exception.getLineNumber() + ""
                        + "  URI:     " + exception.getSystemId() + "" + "  Message: "
                        + exception.getMessage());
        throw new SAXException("Error encountered");
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        // Bring things to a crashing halt
        System.out.println("**Parsing Fatal Error**" + "  Line:    " + exception.getLineNumber()
                        + "" + "  URI:     " + exception.getSystemId() + "" + "  Message: "
                        + exception.getMessage());
        throw new SAXException("Fatal Error encountered");
    }
}
