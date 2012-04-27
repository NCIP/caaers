package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.ws.client.core.WebServiceTemplate;



public class ProxyWebServiceFacade implements AdeersIntegrationFacade{

	public static final String SYNC_ORG_ENTITY_NAME="organization";
	public static final String SYNC_ORG_OPERATION_NAME="getOrganizationsLOV";
	public static final boolean SYNC_ORG_OPERATION_MODE=false;

	public static final String SYNC_AGENT_ENTITY_NAME="agent";
	public static final String SYNC_AGENT_OPERATION_NAME="getAgentsLOV";
	public static final boolean SYNC_AGENT_OPERATION_MODE=false;

	public static final String SYNC_DEVICE_ENTITY_NAME="device";
	public static final String SYNC_DEVICE_OPERATION_NAME="getDevicesLOV";
	public static final boolean SYNC_DEVICE_OPERATION_MODE=false;

	public static final String SYNC_PRIOR_THERAPY_ENTITY_NAME="priortherapy";
	public static final String SYNC_PRIOR_THERAPY_OPERATION_NAME="getTherapiesLOV";
	public static final boolean SYNC_PRIOR_THERAPY_OPERATION_MODE=false;

	public static final String SYNC_ASAEL_ENTITY_NAME="asael";
	public static final String SYNC_ASAEL_OPERATION_NAME="getASAEL";
	public static final boolean SYNC_ASAEL_OPERATION_MODE=false;

	public static final String SYNC_PRE_EXISTING_COND_ENTITY_NAME="preexistingcondition";
	public static final String SYNC_PRE_EXISTING_COND_OPERATION_NAME="getPreExistingConditionsLOV";
	public static final boolean SYNC_PRE_EXISTING_COND_OPERATION_MODE=false;

	public static final String GET_STUDY_ENTITY_NAME="study";
	public static final String GET_STUDY_OPERATION_NAME="getStudyDetails";
	public static final boolean GET_STUDY_OPERATION_MODE=false;

	public static final String SEARCH_STUDY_SYSTEM_NAME="";
	public static final String SEARCH_STUDY_ENTITY_NAME="";
	public static final String SEARCH_STUDY_OPERATION_NAME="";
	public static final boolean SEARCH_STUDY_OPERATION_MODE=true;

    private WebServiceTemplate webServiceTemplate;

    public void setDefaultUri(String defaultUri) {
        webServiceTemplate.setDefaultUri(defaultUri);
    }

    // send to the configured default URI
    public String simpleSendAndReceive(String message) {
        StreamSource source = new StreamSource(new StringReader(message));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);
        return result.toString();
    }
    
    // send to the configured default URI
    public String send(String entity, String operationName, boolean sync, Map<String, String> criteria) {
    	String corelationId = RandomStringUtils.randomAlphanumeric(10);
        String message = buildMessage(corelationId, "adeers", entity, operationName, sync?"sync":"async", criteria);
        String result = simpleSendAndReceive(message);
        System.out.println(result);
        return corelationId;
    }

	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}
	
	private static String buildMessage(String corelationId, String system, String entity, String operationName, String operationMode, Map<String, String> criteria) {
		StringBuffer sb = new StringBuffer();
		sb.append("<gen:GenericRequest xmlns:gen=\"http://webservice.caaers.cabig.nci.nih.gov/GenericProcessor/\">");
		sb.append("<payload correlationId=\""+corelationId+"\">");
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
        String since = DateUtils.formatDateForWS(DateUtils.yesterday());
        criteriaMap.put("createdDate", since);
        criteriaMap.put("lastUpdatedDate", since);
		return criteriaMap;
	}

	public String syncOrganizations() {
		return send(SYNC_ORG_ENTITY_NAME, SYNC_ORG_OPERATION_NAME, SYNC_ORG_OPERATION_MODE, buildCriteriaMap(null));
	}

	public String syncAgents() {
		return send(SYNC_AGENT_ENTITY_NAME, SYNC_AGENT_OPERATION_NAME, SYNC_AGENT_OPERATION_MODE,buildCriteriaMap(null));
	}

	public String syncDevices() {
		return send(SYNC_DEVICE_ENTITY_NAME, SYNC_DEVICE_OPERATION_NAME, SYNC_DEVICE_OPERATION_MODE, buildCriteriaMap(null));
	}

	public String syncPriorTherapyLOV() {
		return send(SYNC_PRIOR_THERAPY_ENTITY_NAME, SYNC_PRIOR_THERAPY_OPERATION_NAME, SYNC_PRIOR_THERAPY_OPERATION_MODE, buildCriteriaMap(null));
	}

	public String syncPreExistingConditionLOV() {
		return send(SYNC_PRE_EXISTING_COND_ENTITY_NAME, SYNC_PRE_EXISTING_COND_OPERATION_NAME, SYNC_PRE_EXISTING_COND_OPERATION_MODE, buildCriteriaMap(null));
	}

	public String syncASAEL() {
		return send( SYNC_ASAEL_ENTITY_NAME, SYNC_ASAEL_OPERATION_NAME, SYNC_ASAEL_OPERATION_MODE, buildCriteriaMap(null));
	}

	public String getStudy() {
		return send(GET_STUDY_ENTITY_NAME, GET_STUDY_OPERATION_NAME, GET_STUDY_OPERATION_MODE, buildCriteriaMap(null));
	}

	public String syncStudies() {
		// TODO Auto-generated method stub
		return null;
	}

    // ToDo This methos should be implemented to use ServiceMix

    /**
     *
     * @param id
     * @param createOrUpdate CREATE or UPDATE
     * @return
     */
	public String syncStudy(String id, String createOrUpdate) {
        System.out.println("Synchronizing: " + id + ", " + createOrUpdate);
        // Simulating response wait from ServiceMix :P
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        return createOrUpdate + "-91";
	}

    // ToDo This should be removed after the implementation of gov.nih.nci.cabig.caaers.service.ProxyWebServiceFacade.searchStudies
    private static Study createStudy(final String shortTitle) {
        Study s = new LocalStudy();
        s.setShortTitle(shortTitle);
        s.setLongTitle(shortTitle);

        OrganizationAssignedIdentifier i = new OrganizationAssignedIdentifier();
        i.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
        i.setValue("ABC-01");
        s.addIdentifier(i);

        Organization o = new LocalOrganization();
        o.setNciInstituteCode("CTEP");
        StudyFundingSponsor so = new StudyFundingSponsor();
        so.setOrganization(o);
        so.setStudy(s);

        i.setOrganization(o);
        s.addStudyFundingSponsor(so);

        return s;
    }

    // ToDo This methos should be implemented to get Studies from adERRS
	public List<Study> searchStudies(String searchText) {

        System.out.println(">>> WS Facade Searching by: " + searchText);

        List<Study> studies = new ArrayList<Study>();

        studies.add(createStudy("Short Title - 01"));
        studies.add(createStudy("Short Title - 02"));

        studies.get(0).setStatus("UPDATE");
        studies.get(0).setId(90);

        studies.get(0).getIdentifiers().get(0).setValue("ABC-01");
        studies.get(0).setId(90);

        studies.get(1).getIdentifiers().get(0).setValue("ABC-99");

        // Simulating response wait from ServiceMix :P
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        return studies;
	}
	
}