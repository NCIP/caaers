package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.api.InvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.api.ParticipantService;
import gov.nih.nci.cabig.caaers.api.ResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.api.StudyProcessor;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.XmlValidator;
import gov.nih.nci.cabig.caaers.webservice.Studies;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.globus.wsrf.security.SecurityManager;
import org.xml.sax.InputSource;

/**
 * This is a listener class which listens for messages on the ctmsCaaersRecvQueue "ctms-caaers.inputQueue".
 * The JMS infrastructure is provided by ServiceMix. 
 * The ctms-caaers-sa needs to be deployed on ServiceMix.
 * 
 * The Message received will be processed and the Response will be sent to the ctmsCaaersResponseQueue "ctms-caaers.outputQueue"
 * This Class can Handle only JMS TextMessage.
 * The Header should have a MESSAGE_TYPE Key with Values being any one from the below list.
 * CREATE_STUDY
 * UPDATE_STUDY
 * CREATE_PARTICIPANT
 * UPDATE_PARTICIPANT
 * CREATE_INVESTIGATOR
 * UPDATE_INVESTIGATOR
 * CREATE_RESEARCHSTAFF
 * UPDATE_RESEARCHSTAFF
 * 
 * @author Monish Dombla
 *
 */
public class CtmsCaaersMessageConsumer implements MessageListener{
	
	private static Log logger = LogFactory.getLog(CtmsCaaersMessageConsumer.class);
	
	private Configuration configuration = null;
	private ConnectionFactory connectionFactory = null;
	private Connection connection = null;
	private Session session = null;
	private MessageConsumer consumer = null;
	private MessageProducer producer = null;
	private ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
	private ActiveMQQueue ctmsCaaersRecvQueue = null;
	private ActiveMQQueue ctmsCaaersResponseQueue = null;
	
    private StudyProcessor studyProcessor;
    private ParticipantService participantService;
    private InvestigatorMigratorService investigatorMigratorService;
    private ResearchStaffMigratorService researchStaffMigratorService;
    
    private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private Marshaller marshaller = null;
    
	/**
	 * This Method is invoked by spring to initialize the connections to the queues. 
	 * @throws Exception
	 */
	public void initialize() throws Exception{
		String esbURL = configuration.get(Configuration.ESB_URL);
    	if("".equals(esbURL) || esbURL == null){
    		logger.error("Could not start CtmsCaaersMessageConsumer, ESB URL not specified");
    		return;
    	}
    	try{
    		mqConnectionFactory.setBrokerURL(esbURL);
            connectionFactory = mqConnectionFactory;
            connection = connectionFactory.createConnection();
    		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    		consumer = session.createConsumer(ctmsCaaersRecvQueue);
            consumer.setMessageListener(this);
            producer = session.createProducer(ctmsCaaersResponseQueue);
            logger.debug("starting connection....");
            connection.start();
    	}catch(Exception e){
    		//logger.error("Error Initializing CtmsCaaersMessageConsumer" , e);
    		logger.info("CtmsCaaersMessageConsumer not available");
    	}
	}
	
	/**
	 * This is the Callback method which gets invoked whenever there is a message on the InputQueue.
	 * Once it receives a message this method delegates the processing to different methods.
	 */
	public void onMessage(Message message){
		
		String identity = SecurityManager.getManager().getCaller();
        if (identity == null) {
            identity = "ctms-caaers.inputQueue";
        }
        logger.debug("Auditing request from " + identity);
        String info = null;
        info = configuration.get(Configuration.ESB_URL);
        gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo.setLocal(new DataAuditInfo(identity,
                        info, new Date(), info));
		
		try {
			if (message instanceof TextMessage){
				String messageType = message.getStringProperty("MESSAGE_TYPE");
				String jmsCorelationID = message.getJMSCorrelationID();
				String responseXml = "";
				StringBuffer sb = new StringBuffer();
				boolean messageValid = false;
				
				logger.debug("JMSCorelationID="+ jmsCorelationID);
				logger.debug("MESSAGE_TYPE="+ messageType);
				
				//Step 1
				 messageValid = validateMessage(message,messageType,sb);
				
				//Step 2
				if(messageValid){
					responseXml = processMessage(message,messageType);
				}else{
					gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse caaersResponse = new gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse();
					gov.nih.nci.cabig.caaers.webservice.Response response = new gov.nih.nci.cabig.caaers.webservice.Response();
					response.setResponsecode("1");
					response.setDescription("Invalid Xml Content");
					response.getMessage().add(sb.toString());
					caaersResponse.setResponse(response);
					jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
					marshaller = jaxbContext.createMarshaller();
					responseXml = responseAsString(caaersResponse, marshaller);
				}
				
				//Step 3
				sendResponse(responseXml,jmsCorelationID,messageType);
			}
		}catch (JMSException je) {
			logger.error("onMessage caught ",je);
		} catch (JAXBException e) {
			logger.error("onMessage caught ",e);
		}
	}
	
	/**
	 * This method validates the Content of the JMS TextMessage against the 
	 * relevant Schema and return true if valid and false if not valid.
	 * the sb is used as a output parameter. The Validation results are 
	 * set in this StringBuffer and is used by the caller in generating the response.
	 * @param message
	 * @param messageType
	 * @param sb
	 * @return
	 */
	private boolean validateMessage(Message message,String messageType,StringBuffer sb){
		boolean validContent = false;
		try {
			
			if("CREATE_STUDY".equals(messageType) || "UPDATE_STUDY".equals(messageType)){
				validContent = XmlValidator.validateAgainstSchema(((TextMessage)message).getText(), "classpath:gov/nih/nci/cabig/caaers/StudySchema.xsd", sb);
			}
			
			if("CREATE_PARTICIPANT".equals(messageType) || "UPDATE_PARTICIPANT".equals(messageType)){
				validContent = XmlValidator.validateAgainstSchema(((TextMessage)message).getText(), "classpath:gov/nih/nci/cabig/caaers/ParticipantSchema.xsd", sb);
			}
			
			if("CREATE_INVESTIGATOR".equals(messageType) || "UPDATE_INVESTIGATOR".equals(messageType)){
				validContent = XmlValidator.validateAgainstSchema(((TextMessage)message).getText(), "classpath:gov/nih/nci/cabig/caaers/Investigator.xsd", sb);
			}
			
			if("CREATE_RESEARCHSTAFF".equals(messageType) || "UPDATE_RESEARCHSTAFF".equals(messageType)){
				validContent = XmlValidator.validateAgainstSchema(((TextMessage)message).getText(), "classpath:gov/nih/nci/cabig/caaers/ResearchStaff.xsd", sb);
			}
		} catch (JMSException e) {
			logger.error("validateMessage caught " , e);
		}
		return validContent;
	}
	
	/**
	 * This method identifies the messageType and delegates the call to appropriate 
	 * processor.
	 * @param message
	 * @param messageType
	 * @return
	 */
	private String processMessage(Message message,String messageType){
		String responseXml = "";
		try {
			if("CREATE_STUDY".equals(messageType) || "UPDATE_STUDY".equals(messageType)){
				responseXml = processStudy(message,messageType);
			}else if("CREATE_PARTICIPANT".equals(messageType) || "UPDATE_PARTICIPANT".equals(messageType)){
				responseXml = processParticipant(message,messageType);
			}else if("CREATE_INVESTIGATOR".equals(messageType) || "UPDATE_INVESTIGATOR".equals(messageType)){
				responseXml = processInvestigator(message);
			}else if("CREATE_RESEARCHSTAFF".equals(messageType) || "UPDATE_RESEARCHSTAFF".equals(messageType)){
				responseXml = processResearchStaff(message);
			}
		} catch (Exception e) {
			logger.error("caught processMessage",e);
		} 
		return responseXml;
	}
	
	/**
	 * This method handles messageType of Create and Update study
	 * @param message
	 * @param messageType
	 * @return
	 */
	private String processStudy(Message message,String messageType){
		String responseXml = "";
		gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse studyServiceResponse = null;
		try {
			jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
			unmarshaller = jaxbContext.createUnmarshaller();
			marshaller = jaxbContext.createMarshaller();
			
			Studies studies = (Studies)unmarshaller.unmarshal(new InputSource(new StringReader(((TextMessage)message).getText())));
			if("CREATE_STUDY".equals(messageType)){
				studyServiceResponse = studyProcessor.createStudy(studies);
			}else if("UPDATE_STUDY".equals(messageType)){
				studyServiceResponse = studyProcessor.updateStudy(studies);
			}
			responseXml = responseAsString(studyServiceResponse,marshaller);
		} catch (JAXBException e) {
			logger.error("caught processStudy",e);
		} catch (JMSException e) {
			logger.error("caught processStudy",e);
		}
		return responseXml;
	}
	
	/**
	 * This method handles messageType of Create and Update participant.
	 * @param message
	 * @param messageType
	 * @return
	 */
	private String processParticipant(Message message,String messageType){
		String responseXml = "";
		gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse participantServiceResponse = null;
		try {
			jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.participant");
			unmarshaller = jaxbContext.createUnmarshaller();
			marshaller = jaxbContext.createMarshaller();
			
			Participants participants = (Participants)unmarshaller.unmarshal(new InputSource(new StringReader(((TextMessage)message).getText())));
			if("CREATE_PARTICIPANT".equals(messageType)){
				participantServiceResponse = participantService.createParticipant(participants);
			}else if("UPDATE_PARTICIPANT".equals(messageType)){
				participantServiceResponse = participantService.updateParticipant(participants);
			}
			responseXml = responseAsString(participantServiceResponse,marshaller);
		} catch (JAXBException e) {
			logger.error("caught processParticipant",e);
		} catch (JMSException e) {
			logger.error("caught processParticipant",e);
		}
		return responseXml;
	}
	
	/**
	 * This method handles messageType of CREATE_INVESTIGATOR and UPDATE_INVESTIGATOR
	 * @param message
	 * @return
	 */
	private String processInvestigator(Message message){
		String responseXml = "";
		gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse investigatorServiceResponse = null;
		
		try {
			jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.investigator");
			unmarshaller = jaxbContext.createUnmarshaller();
			gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff)unmarshaller.unmarshal(new InputSource(new StringReader(((TextMessage)message).getText())));
			investigatorServiceResponse = investigatorMigratorService.saveInvestigator(staff);
			
			jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.common");
			marshaller = jaxbContext.createMarshaller();
			responseXml = responseAsString(investigatorServiceResponse,marshaller);
		} catch (JAXBException e) {
			logger.error("caught processParticipant",e);
		} catch (JMSException e) {
			logger.error("caught processParticipant",e);
		}
		
		return responseXml;
	}
	
	/**
	 * This method handles messageType of CREATE_RESEARCHSTAFF and UPDATE_RESEARCHSTAFF
	 * @param message
	 * @return
	 */
	private String processResearchStaff(Message message){
		String responseXml = "";
		gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse researchStaffServiceResponse = null;
		
		try {
			jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
			unmarshaller = jaxbContext.createUnmarshaller();
			gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(new InputSource(new StringReader(((TextMessage)message).getText())));
			researchStaffServiceResponse = researchStaffMigratorService.saveResearchStaff(staff);
			
			jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.common");
			marshaller = jaxbContext.createMarshaller();
			responseXml = responseAsString(researchStaffServiceResponse,marshaller);
		} catch (JAXBException e) {
			logger.error("caught processParticipant",e);
		} catch (JMSException e) {
			logger.error("caught processParticipant",e);
		}
		
		return responseXml;
	}
	
	/**
	 * This method sends the response out. It uses a producer which is connected to "ctms-caaers.outputQueue"
	 * @param responseXml
	 * @param jmsCorelationID
	 * @param messageType
	 */
	private void sendResponse(String responseXml, String jmsCorelationID,String messageType){
		try {
			TextMessage message = session.createTextMessage();
			message.setJMSCorrelationID(jmsCorelationID);
			message.setStringProperty("MESSAGE_TYPE", messageType);
			message.setText(responseXml);
			producer.send(message);
		} catch (JMSException e) {
			logger.error("caught sendResponse",e);
		}
	}
	
	/**
	 * This method is used to marshal the Response objects from Individual Services to xml format
	 * so that it can be set as the Content of the Response JMS TextMessage
	 * @param pObject
	 * @param marshaller
	 * @return
	 * @throws JAXBException
	 */
	public String responseAsString(Object pObject,Marshaller marshaller) throws JAXBException {
		StringWriter sw = new StringWriter();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(pObject, sw);
		return sw.toString();
	}
	
	/**
	 * This method closes the Connection 
	 * @throws Exception
	 */
	public void close(){
		logger.debug("closing connection....");
		try {
			connection.close();
		} catch (JMSException e) {
			logger.error("caught close",e);
		}
		// Any other cleanup
	}

	public void setStudyProcessor(StudyProcessor studyProcessor) {
		this.studyProcessor = studyProcessor;
	}

	public void setParticipantService(ParticipantService participantService) {
		this.participantService = participantService;
	}

	public void setInvestigatorMigratorService(
			InvestigatorMigratorService investigatorMigratorService) {
		this.investigatorMigratorService = investigatorMigratorService;
	}

	public void setResearchStaffMigratorService(
			ResearchStaffMigratorService researchStaffMigratorService) {
		this.researchStaffMigratorService = researchStaffMigratorService;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void setCtmsCaaersRecvQueue(ActiveMQQueue ctmsCaaersRecvQueue) {
		this.ctmsCaaersRecvQueue = ctmsCaaersRecvQueue;
	}

	public void setCtmsCaaersResponseQueue(ActiveMQQueue ctmsCaaersResponseQueue) {
		this.ctmsCaaersResponseQueue = ctmsCaaersResponseQueue;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
} 