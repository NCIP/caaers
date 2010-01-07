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
import gov.nih.nci.caxchange.messaging.Response;
import gov.nih.nci.caxchange.messaging.TransactionControls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class CaaersNonGridCaXchangeMessageBroadcastServiceImpl implements MessageBroadcastService {
	private org.apache.cxf.jaxws.JaxWsProxyFactoryBean clientFactory;
	
	public void broadcast(String message) throws BroadcastException {
		// TODO Auto-generated method stub
		
	}

	public String broadcastCOPPA(List<String> messages, Metadata metaData) throws BroadcastException {
		String operationName = metaData.getOperationName();
		String serviceType = metaData.getServiceType();
		//String caXchangeNonGridUrl = Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.CAEXCHANGE_URL);
		//clientFactory.setAddress(caXchangeNonGridUrl);
		//clientFactory.setAddress("https://ncias-c278-v.nci.nih.gov:8194/CaXchangeRequestService");

		org.apache.cxf.jaxws.JaxWsProxyFactoryBean cf = getClientFactory();
		
		CaXchangeRequestPortType caXchangeRequestPortType = (CaXchangeRequestPortType) cf.create();
		Message messageToCXFBC = buildMessageToCXFBC(serviceType, messages, operationName);
		
		//synchronus
		gov.nih.nci.caxchange.messaging.ResponseMessage _processRequest_return = caXchangeRequestPortType
		.processRequest(messageToCXFBC);
		
		ObjectFactory objectFactory = new ObjectFactory();
		JAXBElement jaxbElement = objectFactory.createCaXchangeResponseMessage(_processRequest_return);
		return XMLUtil.getXML(jaxbElement, "gov.nih.nci.caxchange.messaging");
		/*
		Response response = _processRequest_return.getResponse();
		System.out.println("RESPONSE STATUS: "	+ response.getResponseStatus());
		System.out.println("Error Code " + response.getCaXchangeError().getErrorDescription());


		// TODO Auto-generated method stub
		return null;*/
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
			metadata.setExternalIdentifier("myExternalIdentifier");
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
			e.printStackTrace();
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
