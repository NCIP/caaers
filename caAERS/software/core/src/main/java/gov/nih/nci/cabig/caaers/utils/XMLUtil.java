package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cagrid.caxchange.client.CaXchangeRequestProcessorClient;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.caxchange.ResponseMessage;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.axis.message.MessageElement;

public class XMLUtil {
	public static void main (String[] args ) {
		XMLUtil.getObjectsFromCoppaResponse("");
	}
    public static String getXML(Object rootElement) throws Exception {

    	
    	JAXBContext jaxbContext = JAXBContext.newInstance("gme.ccts_cabig._1_0.gov_nih_nci_cabig_ccts_ae");
	       // org.jibx.xsd2jibx.GeneratorAntTask f;
	        Marshaller  m = jaxbContext.createMarshaller();
	        StringWriter w = new StringWriter();
	        m.marshal(rootElement, w);
	        
	        return w.toString();
    }
    
    public static List<String>getObjectsFromCoppaResponse(String response ){
    	if (response == null) {
    		return new ArrayList<String>();
    	}
    	List<String> objList = new ArrayList<String>();

            StringReader reader = new StringReader(response);
            InputStream wsddIs = CaXchangeRequestProcessorClient.class.getResourceAsStream("/gov/nih/nci/cagrid/caxchange/client/client-config.wsdd");
            Object deserializedObject = null;
            try {
            	deserializedObject = Utils.deserializeObject(reader,ResponseMessage.class, wsddIs);
            } catch (Exception e) {
            	e.printStackTrace();
            }
            if (deserializedObject != null) {
	            ResponseMessage rm = (ResponseMessage)deserializedObject;
	            MessageElement mElement = rm.getResponse().getTargetResponse()[0].getTargetBusinessMessage().get_any()[0];
	            if( mElement.getName() .equals("Array")) {
	            	List<MessageElement> meList = mElement.getChildren();
	            	if (meList == null) {
	            		return objList;
	            	}
	            	for(MessageElement me : meList){
		                try {
		                	objList.add(me.getAsString());
		                } catch (Exception e) {
		             
		                    e.printStackTrace();
		                }
		            }
	            } else {
	            	try {
						objList.add(mElement.toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            
            }

        return objList;
    }

}
