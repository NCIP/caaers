package gov.nih.nci.cabig.caaers.esb.client;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

public abstract class ResponseMessageProcessor {
	
    public Element getResponseElement(String message,String responseType,String child) {

        SAXBuilder saxBuilder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
        Reader stringReader = new StringReader(message);
        Element jobInfo = null;
        try {
            org.jdom.Document jdomDocument = saxBuilder.build(stringReader);
            org.jdom.Element root = jdomDocument.getRootElement();

            Element body = root.getChild("Body", root.getNamespace());
            Element response = body.getChild(responseType);
            Namespace n = ((Element) response.getChildren().get(0)).getNamespace();
            jobInfo = response.getChild(child, n);

        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jobInfo;
    }
    
    
    
	public abstract void processMessage(String message) throws CaaersSystemException;
	
}
