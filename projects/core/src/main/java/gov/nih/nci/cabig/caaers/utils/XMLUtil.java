package gov.nih.nci.cabig.caaers.utils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class XMLUtil {

    public static String getXML(Object rootElement) throws Exception {
    	
    	/*
        IMarshallingContext mctx = null;
        StringWriter writer = new StringWriter();
        try {
            IBindingFactory bfact = BindingDirectory.getFactory(rootElement.getClass());
            mctx = bfact.createMarshallingContext();
            mctx.setOutput(writer);
            mctx.marshalDocument(rootElement);
        } catch (JiBXException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        }
        // String returnString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + writer.toString();
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + writer.toString();
        
        */
    	
    	JAXBContext jaxbContext = JAXBContext.newInstance("gme.ccts_cabig._1_0.gov_nih_nci_cabig_ccts_ae");
	       // org.jibx.xsd2jibx.GeneratorAntTask f;
	        Marshaller  m = jaxbContext.createMarshaller();
	        StringWriter w = new StringWriter();
	        m.marshal(rootElement, w);
	        
	        return w.toString();
    }

}
