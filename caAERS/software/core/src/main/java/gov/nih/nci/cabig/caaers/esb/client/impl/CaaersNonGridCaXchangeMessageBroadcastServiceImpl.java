package gov.nih.nci.cabig.caaers.esb.client.impl;

import edu.duke.cabig.c3pr.esb.Metadata;
import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;
import gov.nih.nci.caxchange.caxchangerequest.CaXchangeRequestPortType;
import gov.nih.nci.caxchange.messaging.Message;
import gov.nih.nci.caxchange.messaging.MessagePayload;
import gov.nih.nci.caxchange.messaging.ObjectFactory;
import gov.nih.nci.caxchange.messaging.Request;
import gov.nih.nci.caxchange.messaging.TransactionControls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

/**
 * /**
 * This implementation will broadcast messages to caXchange non grid interface.
  * @author sakkala
 *
 */
public class CaaersNonGridCaXchangeMessageBroadcastServiceImpl implements MessageBroadcastService {
	private org.apache.cxf.jaxws.JaxWsProxyFactoryBean clientFactory;
	private static final Log log = LogFactory.getLog(CaaersNonGridCaXchangeMessageBroadcastServiceImpl.class);
	
	public void broadcast(String message) throws BroadcastException {
		// TODO Auto-generated method stub
		
	}

	public String broadcastCOPPA(List<String> messages, Metadata metaData) throws BroadcastException {
		String operationName = metaData.getOperationName();
		String serviceType = metaData.getServiceType();
		//String caXchangeNonGridUrl = Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.CAEXCHANGE_NONGRID_URL);
		//clientFactory.setAddress(caXchangeNonGridUrl);
		//clientFactory.setAddress("https://ncias-c278-v.nci.nih.gov:8194/CaXchangeRequestService");
		gov.nih.nci.caxchange.messaging.ResponseMessage responseMessage = null;
		try {
			CaXchangeRequestPortType caXchangeRequestPortType = (CaXchangeRequestPortType) getClientFactory().create();
			Message messageToCXFBC = buildMessageToCXFBC(serviceType, messages, operationName);
		
			//synchronus
			responseMessage = caXchangeRequestPortType.processRequest(messageToCXFBC);
		} catch (Exception e ) {
			log.error("Error while broadcasting the message to COPPA", e);
            throw new gov.nih.nci.cabig.caaers.esb.client.BroadcastException(e);
		}
		
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement jaxbElement = objectFactory.createCaXchangeResponseMessage(responseMessage);
		String responseString = XMLUtil.getXML(jaxbElement, "gov.nih.nci.caxchange.messaging");
		return responseString;
	}

	/**
	 * Builds the request message to CXF binding component
	 * 
	 * @param serviceType
	 * @param payloadFileName
	 * @param operationName
	 * @return
	 */
	private Message buildMessageToCXFBC(String serviceType,	List<String> payload, String operationName) {
		Message requestMessageToESB = new Message();
		try {
//			 Create and set the metadata
			gov.nih.nci.caxchange.messaging.Metadata  metadata = new gov.nih.nci.caxchange.messaging.Metadata();
			metadata.setTransactionControl(TransactionControls.PROCESS);
			/*
			Credentials credentials = new Credentials();
			credentials.setUserName("ccts@nih.gov");
			credentials.setPassword("!Ccts@nih.gov1");
			metadata.setCredentials(credentials);
			*/
			//metadata.setCaXchangeIdentifier("037068f0-23a8-11de-a5f1-d00caf9050fd");
			metadata.setExternalIdentifier("caExternalIdentifier");
			metadata.setOperationName(new ObjectFactory().createMetadataOperationName(operationName));
			metadata.setServiceType(serviceType);
			requestMessageToESB.setMetadata(metadata);
			
//			 Create and set the request
			Request request = new Request();
			MessagePayload messagePayload = new MessagePayload();
			messagePayload.setXmlSchemaDefinition("");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//InputStream message = new ByteArrayInputStream(payload.get(0).getBytes());;
			
			for(int i=0;i < payload.size() ; i++){  
				InputStream message = new ByteArrayInputStream(payload.get(i).getBytes());
				Document payloadDoc = db.parse(message);
				messagePayload.getAny().add(payloadDoc.getDocumentElement());
			}	
			
			//Document payloadDoc = db.parse(message);
		//	messagePayload.setAny(payloadDoc.getDocumentElement());
			request.setBusinessMessagePayload(messagePayload);

			requestMessageToESB.setRequest(request);

		} catch (Exception e) {
			log.error("Exception building payload" + e);
			//e.printStackTrace();
		}

		return requestMessageToESB;
	}
	public List<String> getBroadcastStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setClientFactory(
			org.apache.cxf.jaxws.JaxWsProxyFactoryBean clientFactory) {
		this.clientFactory = clientFactory;
	}

	public org.apache.cxf.jaxws.JaxWsProxyFactoryBean getClientFactory() {
		return clientFactory;
	}

}
