/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersConfigurationException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.integration.schema.study.Studies;
import gov.nih.nci.cabig.caaers.service.migrator.StudyConverter;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.utils.XsltTransformer;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.*;


public class ProxyWebServiceFacade implements AdeersIntegrationFacade{

    protected final Log log = LogFactory.getLog(getClass());

	public static final String SYNC_ORG_ENTITY_NAME="organization";
	public static final String SYNC_ORG_OPERATION_NAME="getOrganizationsLOV";

	public static final String SYNC_AGENT_ENTITY_NAME="agent";
	public static final String SYNC_AGENT_OPERATION_NAME="getAgentsLOV";

	public static final String SYNC_DEVICE_ENTITY_NAME="device";
	public static final String SYNC_DEVICE_OPERATION_NAME="getDevicesLOV";
	
	public static final String SYNC_LAB_ENTITY_NAME="lab";
	public static final String SYNC_LAB_OPERATION_NAME="getLabsLOV";

	public static final String SYNC_PRIOR_THERAPY_ENTITY_NAME="priortherapy";
	public static final String SYNC_PRIOR_THERAPY_OPERATION_NAME="getTherapiesLOV";

	public static final String SYNC_ASAEL_ENTITY_NAME="asael";
	public static final String SYNC_ASAEL_OPERATION_NAME="getASAEL";

    public static final String SYNC_DOSE_UOM_ENTITY_NAME="agentDoseUOM";
    public static final String SYNC_DOSE_UOM_OPERATION_NAME="getAgentDoseUOMLOV";

	public static final String SYNC_PRE_EXISTING_COND_ENTITY_NAME="preexistingcondition";
	public static final String SYNC_PRE_EXISTING_COND_OPERATION_NAME="getPreExistingConditionsLOV";

	public static final String CREATE_STUDY_ENTITY_NAME="study";
	public static final String CREATE_STUDY_OPERATION_NAME="createStudy";

	public static final String UPDATE_STUDY_ENTITY_NAME="study";
	public static final String UPDATE_STUDY_OPERATION_NAME="updateStudy";

	public static final String SEARCH_STUDY_ENTITY_NAME="study";
	public static final String SEARCH_STUDY_OPERATION_NAME="searchStudy";
	
	public static final String SYNC_CTCAE_ENTITY_NAME="ctcae";
	public static final String SYNC_CTCAE_OPERATION_NAME="getCTCAELOV";
	
	public static final String MERGED_ORG_ENTITY_NAME="mergedorganization";
	public static final String MERGED_ORG_OPERATION_NAME="getMergedOrganization";

    private EventFactory eventFactory;
    private WebServiceTemplate webServiceTemplate;
    private StudyConverter studyConverter;
    private Configuration configuration;
    private StudyDao studyDao;
    
    private JAXBContext jaxbContext = null;
    private Unmarshaller unmarshaller = null;
    private XsltTransformer xsltTransformer;

    
    public ProxyWebServiceFacade() {
       try{
           jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.study");
           unmarshaller = jaxbContext.createUnmarshaller();
           xsltTransformer = new XsltTransformer();
       } catch (JAXBException jb){
           throw new CaaersConfigurationException("Unable to create proxy webservice : " + jb.getMessage() , jb);
       }
    }

    public void setDefaultUri(String defaultUri) {
        webServiceTemplate.setDefaultUri(defaultUri);
    }

    // send to the configured default URI
    public String simpleSendAndReceive(String message) {
        StreamSource source = new StreamSource(new StringReader(message));
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        String wsURI = configuration.get(Configuration.ESB_WS_URL);
        if(wsURI != null) webServiceTemplate.setDefaultUri(wsURI);
        webServiceTemplate.sendSourceAndReceiveToResult(source, new WebServiceMessageCallback() {
			
			public void doWithMessage(WebServiceMessage message) throws IOException,
					TransformerException {
				SoapMessage soapMessage = (SoapMessage) message;
			    
			    StringBuffer sbHeader = new StringBuffer();
			    sbHeader.append("<wsse:Security xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ")
			    	.append("xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">")
			        .append("<wsse:UsernameToken wsu:Id=\"UsernameToken-2765109\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">")
			        .append("<wsse:Username>")
			        .append(configuration.get(Configuration.WS_USERNAME))
			        .append("</wsse:Username>")
			        .append("<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">")
			        .append(configuration.get(Configuration.WS_PASSWORD))
			        .append("</wsse:Password>")
			        .append("</wsse:UsernameToken>")
			    	.append("</wsse:Security> ");
			    
			    StringSource headerSource = new StringSource(sbHeader.toString());
			    SoapHeader soapHeader = ((SoapMessage) message).getSoapHeader();
			    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(headerSource, soapHeader.getResult());
			}
		} ,result);

        return sw.toString();
    }
    
    // send to the configured default URI
    public String send(String entity, String operationName, boolean sync, Map<String, String> criteria) {
    	String corelationId = RandomStringUtils.randomAlphanumeric(15);
        String message = buildMessage(corelationId, "adeers", entity, operationName, sync?"sync":"async", criteria);
        String result = simpleSendAndReceive(message);
        return corelationId;
    }

	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

    public void setStudyConverter(StudyConverter studyConverter) {
        this.studyConverter = studyConverter;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public void setEventFactory(EventFactory eventFactory) {
        this.eventFactory = eventFactory;
    }

    private static String buildMessage(String corelationId, String system, String entity, String operationName, String operationMode, Map<String, String> criteria) {
		StringBuffer sb = new StringBuffer();
		sb.append("<gen:GenericRequest xmlns:gen=\"http://webservice.caaers.cabig.nci.nih.gov/GenericProcessor/\">");
		sb.append("<payload correlationId=\""+corelationId+"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		sb.append("<system>"+system+"</system>");
		sb.append("<request>");
		sb.append("<!--Optional:-->");
		sb.append("<entity>"+entity+"</entity>");
		sb.append("<operation name=\""+operationName+"\" mode=\""+operationMode+"\">");
		sb.append("<criteria>");
		for(String key: criteria.keySet()){
			sb.append("<criterion name=\""+key+"\">"+criteria.get(key)+"</criterion>");
		}
		sb.append("</criteria>");
		sb.append("</operation>");
		sb.append("</request>");
		sb.append("</payload>");
		sb.append("</gen:GenericRequest>");
		return sb.toString();
	}
	
	private static Map<String, String> buildCriteriaMap(String criteria){
		Map<String, String> criteriaMap = new HashMap<String, String>();
        String since = "";
		try {
			since = DateUtils.formatDateForWS(DateUtils.parseDate("01/01/1990"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        criteriaMap.put("createdDate", since);
        criteriaMap.put("lastUpdatedDate", since);
		return criteriaMap;
	}

	public String syncOrganizations() {
		return send(SYNC_ORG_ENTITY_NAME, SYNC_ORG_OPERATION_NAME, false, buildCriteriaMap(null));
	}
	
	public String mergeOrganizations() {
		return send(MERGED_ORG_ENTITY_NAME, MERGED_ORG_OPERATION_NAME, false, buildCriteriaMap(null));
	}

	public String syncAgents() {
		return send(SYNC_AGENT_ENTITY_NAME, SYNC_AGENT_OPERATION_NAME, false,buildCriteriaMap(null));
	}

	public String syncDevices() {
		return send(SYNC_DEVICE_ENTITY_NAME, SYNC_DEVICE_OPERATION_NAME, false, buildCriteriaMap(null));
	}
	
	public String syncLabs() {
		return send(SYNC_LAB_ENTITY_NAME, SYNC_LAB_OPERATION_NAME, false, buildCriteriaMap(null));
	}

	public String syncPriorTherapyLOV() {
		return send(SYNC_PRIOR_THERAPY_ENTITY_NAME, SYNC_PRIOR_THERAPY_OPERATION_NAME, false, buildCriteriaMap(null));
	}

	public String syncPreExistingConditionLOV() {
		return send(SYNC_PRE_EXISTING_COND_ENTITY_NAME, SYNC_PRE_EXISTING_COND_OPERATION_NAME, false, buildCriteriaMap(null));
	}

	public String syncASAEL() {
		return send( SYNC_ASAEL_ENTITY_NAME, SYNC_ASAEL_OPERATION_NAME, false, buildCriteriaMap(null));
	}

    public String syncAgentUOM() {
        return send( SYNC_DOSE_UOM_ENTITY_NAME, SYNC_DOSE_UOM_OPERATION_NAME, false, buildCriteriaMap(null));
    }
    
	public String syncCTCAE() {
		 return send( SYNC_CTCAE_ENTITY_NAME, SYNC_CTCAE_OPERATION_NAME, false, buildCriteriaMap(null));
	}

    public List<Study> searchStudies(String searchText) {
        List<Study> studyList =  new ArrayList<Study>();
        if(StringUtils.isNotEmpty(searchText)){
            try{

                //invoke the webservice
                Map<String, String> criteriaMap = new HashMap<String, String>();
                criteriaMap.put("documentTitle", searchText);
                criteriaMap.put("nciDocumentNumber", searchText);
                criteriaMap.put("localDocumentNumber", searchText);

                String correlationId = RandomStringUtils.randomAlphanumeric(15);
                String message = buildMessage(correlationId, "adeers", SEARCH_STUDY_ENTITY_NAME, SEARCH_STUDY_OPERATION_NAME, "sync", criteriaMap);            
         
                String xmlSearchResult = simpleSendAndReceive(message);                
                
                // Added the line to fix CAAERS-5799 issue                
                xmlSearchResult = xmlSearchResult.replaceAll("encoding=\"UTF-8\"", "encoding=\"ISO-8859-1\"");                
                
                if(log.isDebugEnabled()) log.debug("xmlSearchResult : for (" + searchText + ") :" + xmlSearchResult);

                String xmlStudies = xsltTransformer.toText(xmlSearchResult, "xslt/c2a_generic_response.xslt");               
                                
                if(StringUtils.isEmpty(xmlStudies)) return studyList;

                Studies studies = (Studies) unmarshaller.unmarshal(new StringReader(xmlStudies));
                
                for(gov.nih.nci.cabig.caaers.integration.schema.study.Study dtoStudy : studies.getStudy()){
                    Study domainStudy = new LocalStudy();
                    studyConverter.convertStudyDtoToStudyDomain(dtoStudy, domainStudy);
                    //the following extra steps are need for the UI to get a valid Study structure.
                    domainStudy.addStudyFundingSponsor(domainStudy.getFundingSponsor().getStudyFundingSponsor());
                    OrganizationAssignedIdentifier identifier = domainStudy.getFundingSponsor().getOrganizationAssignedIdentifier();
                    identifier.setOrganization(domainStudy.getFundingSponsor().getStudyFundingSponsor().getOrganization());
                    domainStudy.addIdentifier(identifier);
                    SystemAssignedIdentifier systemAssignedIdentifier = new SystemAssignedIdentifier();
                    systemAssignedIdentifier.setSystemName("CTEP-ESYS");
                    systemAssignedIdentifier.setType("Other");
                    systemAssignedIdentifier.setValue(domainStudy.getFundingSponsorIdentifierValue());
                    domainStudy.addIdentifier(systemAssignedIdentifier);
                    studyList.add(domainStudy);
                }
            }catch (Exception e){
                log.error("Error occured while invoking ServiceMix Study Search : " + e.getMessage(), e);
                log.info("Returning empty study list : unable to search in adeers for (" + searchText + ") :");
                throw new CaaersSystemException(e.getMessage(), e);
            }
        }

        return studyList;

    }
	public String syncStudies() {
		if(true) throw new CaaersSystemException("gov.nih.nci.cabig.caaers.service.ProxyWebServiceFacade.syncStudies : Not implemented");
		return null;
	}

    private String syncStudy(String operationName, String sponsorIdentifierValue){
        try{
            //invoke the webservice
            Map<String, String> criteriaMap = new HashMap<String, String>();
            criteriaMap.put("nciDocumentNumber", sponsorIdentifierValue);

            String correlationId = RandomStringUtils.randomAlphanumeric(15);

            String message = buildMessage(correlationId, "adeers", "study", operationName, "async", criteriaMap);
            String xmlStudyDetails = simpleSendAndReceive(message);
            if(log.isDebugEnabled()) log.debug("result for getStudyDetails : for (" + sponsorIdentifierValue + ") :" + xmlStudyDetails);
            String studyDbId = xsltTransformer.toText(xmlStudyDetails, "xslt/c2a_generic_response.xslt");
            studyDbId = StringUtils.trim(studyDbId);
            if(log.isInfoEnabled()) log.info("Got study details : Study DB ID :" + studyDbId);
            return studyDbId;
        }catch (Exception e){
            log.error("Error occurred while invoking ServiceMix Study Details : " + e.getMessage(), e);
            return "Import study failed:" +  e.getMessage();
        }

    }

    public String importStudy(String sponsorIdentifierValue) {

        String studyDbId = syncStudy(CREATE_STUDY_OPERATION_NAME, sponsorIdentifierValue);
        Study study = new LocalStudy();
        if(NumberUtils.isNumber(studyDbId)){
            study.setId(NumberUtils.toInt(studyDbId));
            if(eventFactory != null) eventFactory.publishEntityModifiedEvent(study, false);
        }
        return studyDbId;

       
    }

    public String updateStudy(Integer id, boolean force) {
        Study study = studyDao.getById(id);
        if(study == null) return "Unable to find the study (" + id + ")";
        if(!force){
            Date lastSyncedOn = study.getLastSynchedDate();
            long diff = DateUtils.differenceInMinutes(DateUtils.today(), lastSyncedOn);
            Integer allowedDuration = configuration.get(Configuration.STUDY_SYNC_DELAY);
            allowedDuration = allowedDuration == null ? 0 : allowedDuration;
            if(diff < allowedDuration){
                log.info("Ignoring the Sync Study request, as it was last updated on " + String.valueOf( lastSyncedOn));
                return String.valueOf(study.getId());
            }
        }
        String idText = study.getCtepEsysIdentifierValue();
        if(idText == null){
        	idText = study.getFundingSponsorIdentifierValue();
        }
        return syncStudy(UPDATE_STUDY_OPERATION_NAME, idText);
    }

	public String routeAdeersReportSubmissionResponse(String response, Report r) {
		Map<String, String> criteriaMap = new HashMap<String, String>();
		criteriaMap.put("routeAdeersResponse", response);
		String message = buildMessage(r.getCaseNumber()+"##"+System.currentTimeMillis(), "caAERS", "AdeersSubmissionResponse", "routeReportSubmissionResponse", "sync", criteriaMap);
		return simpleSendAndReceive(message);
	}
    
    
    
}
