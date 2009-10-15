package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cagrid.caxchange.client.CaXchangeRequestProcessorClient;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.caxchange.ResponseMessage;
import gov.nih.nci.caxchange.Statuses;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

public class XMLUtil {
	private static Logger log = Logger.getLogger(XMLUtil.class);
	
    public static String getXML(Object rootElement) throws Exception {

    	
    	JAXBContext jaxbContext = JAXBContext.newInstance("gme.ccts_cabig._1_0.gov_nih_nci_cabig_ccts_ae");
	       // org.jibx.xsd2jibx.GeneratorAntTask f;
	        Marshaller  m = jaxbContext.createMarshaller();
	        StringWriter w = new StringWriter();
	        m.marshal(rootElement, w);
	        
	        return w.toString();
    }
    
    public static List<String>getObjectsFromCoppaResponse(String response ){

    	List<String> objList = new ArrayList<String>();
    	if (response == null) {
    		return objList;
    	}
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
	            // error processing message ..
	            if (rm.getResponse().getResponseStatus().equals(Statuses.FAILURE)) {
	            	if (rm.getResponse().getCaXchangeError() != null) {
	            		log.error("caXchange ERROR  : " + rm.getResponse().getCaXchangeError().getErrorDescription().toString());
	            	}
	            	return objList;
	            }
	            
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
    
    /*
     	public static void main (String[] args ) throws Exception {
		
		XMLUtil.getObjectsFromCoppaResponse(XMLUtil.readFileAsString("/Users/sakkala/tech/caxresponse.txt"));
	}
	private static String readFileAsString(String filePath)
    throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
     */

}
