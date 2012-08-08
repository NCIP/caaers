package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.SiteResearchStaffQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.UserAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.*;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

import static gov.nih.nci.cabig.caaers.domain.DateValue.stringToDateValue;

public class SearchStudyAjaxFacade extends AbstractAjaxFacade {
    private static final Log log = LogFactory.getLog(SearchStudyAjaxFacade.class);

    private StudyRepository studyRepository;
    private StudyDao studyDao;
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
    private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;
    private ParticipantDao participantDao;
    private OrganizationDao organizationDao;
    private OrganizationRepository organizationRepository;
    private InvestigatorDao investigatorDao;
    private InvestigatorRepository investigatorRepository; 
    private ResearchStaffDao researchStaffDao;
    private ResearchStaffRepository researchStaffRepository;
    private AdverseEventDao adverseEventDao;
    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    private InvestigationalNewDrugDao investigationalNewDrugDao;
    private AgentRepository agentRepository;
    private DeviceRepository deviceRepository;

    private static Class<?>[] CONTROLLERS = {};

    public SearchStudyAjaxFacade() {
    }

    public SearchStudyAjaxFacade(final StudyDao studyDao,
                                 final ParticipantDao participantDoa,
                                 final AdverseEventDao adverseEventDao,
                                 final ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao,
                                 final OrganizationDao organizationDao) {
        this.studyDao = studyDao;
        this.participantDao = participantDoa;
        this.adverseEventDao = adverseEventDao;
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
        this.organizationDao = organizationDao;
    }

    public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	/**
     * YUI result
     * 
     * */
    @SuppressWarnings("unchecked")
    public List<InvestigationalNewDrug> getINDTable(final Map parameterMap, final String type, final String text,final HttpServletRequest request) throws Exception {
        List<InvestigationalNewDrug> items = new ArrayList<InvestigationalNewDrug>();
        HashMap<String, String> map = new HashMap<String, String>();
        if (type != null && text != null) {
            String[] fields = type.split(",");
            String[] values = text.split(",");
            for (int i = 0; i < fields.length; i++) {
                map.put(fields[i], values[i]);
            }
            items = investigationalNewDrugDao.searchInvestigationalNewDrugs(map);
        }
        return ObjectTools.reduceAll(items, "indNumber", "holderName");
    }

    @SuppressWarnings("finally")
    private List<StudySearchableAjaxableDomainObject> constructExecuteStudyQuery(final String type, final String text) throws ParseException {
        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;
        Map<String, String> propValue = new HashMap<String, String>();

        StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
        // map the html properties to the model properties
        Map<String, String> m = new HashMap<String, String>();
        m.put("prop0", "studyIdentifier");
        m.put("prop1", "studyShortTitle");
        m.put("prop2", "participantIdentifier");
        m.put("prop3", "participantFirstName");
        m.put("prop4", "participantLastName");
        m.put("prop5", "participantEthnicity");
        m.put("prop6", "participantGender");
        m.put("prop7", "participantDateOfBirth");

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            // Create a map of property key ,and search criteria
            propValue.put(m.get(sType), sText);


        }


        query.filterStudiesWithMatchingIdentifierOnly(propValue.get("studyIdentifier"));

        query.filterStudiesWithMatchingShortTitleOnly(propValue.get("studyShortTitle"));
        query.filterByParticipant(propValue.get("participantFirstName"), propValue.get("participantLastName"),
                propValue.get("participantEthnicity"), propValue.get("participantIdentifier"), propValue.get("participantGender"),
                stringToDateValue(propValue.get("participantDateOfBirth")));

        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);
        return studySearchableAjaxableDomainObjects;


    }

    @SuppressWarnings("finally")
    private List<ParticipantAjaxableDomainObject> constructExecuteParticipantQuery(final String type, final String text) {

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;
        Map<String, String> propValue = new HashMap<String, String>();
        
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = new ArrayList<ParticipantAjaxableDomainObject>();

        // map the html properties to the model properties
        Map<String, String> m = new HashMap<String, String>();
        m.put("prop0", "studyIdentifier");
        m.put("prop1", "studyShortTitle");
        m.put("prop2", "participantIdentifier");
        m.put("prop3", "participantFirstName");
        m.put("prop4", "participantLastName");
        m.put("prop5", "participantEthnicity");
        m.put("prop6", "participantGender");
        m.put("prop7", "participantDateOfBirth");

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            // Create a map of property key ,and search criteria
            propValue.put(m.get(sType), sText);
        }
        
        try {
        	query.filterByPrimaryIdentifiers();
			query.filterParticipants(propValue);
//			query.filterByStudyIdentifierValue(propValue.get("studyIdentifier"));
//			query.filterByStudyShortTitle(propValue.get("studyShortTitle"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Query Parsing Error : constructExecuteParticipantQuery", e);
		}
 
		participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        return participantAjaxableDomainObjects;
        /*
        try {
            participants = participantDao.searchParticipant(propValue);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return participants;
        }
        */
    }

    @SuppressWarnings("finally")
    private List<Organization> constructExecuteOrganizationQuery(final String type, final String text) {

        StringTokenizer typeToken = new StringTokenizer(type, ",");
        StringTokenizer textToken = new StringTokenizer(text, ",");
        log.debug("type :: " + type);
        log.debug("text :: " + text);
        String sType, sText;
        List<Organization> organizations = new ArrayList<Organization>();

        OrganizationQuery organizationQuery = new OrganizationQuery();
        

        while (typeToken.hasMoreTokens() && textToken.hasMoreTokens()) {
            sType = typeToken.nextToken();
            sText = textToken.nextToken();
            if (sType.equals("name")) {
                organizationQuery.filterByOrganizationName(sText);
            }
            if (sType.equals("nciInstituteCode")) {
            	organizationQuery.filterByNciCodeExactMatch(sText);
            }

        }

        try {
            organizations = organizationRepository.searchOrganization(organizationQuery);
        }
        catch (Exception e) {
            throw new RuntimeException("Formatting Error", e);
        }
        finally {
            return organizations;
        }
    }



    /**
     *
     * YUI 
     * 
     * */
    public List<Organization> getOrganizationTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {
        List<Organization> organizations = new ArrayList<Organization>();
        if (type != null && text != null) {
            organizations = constructExecuteOrganizationQuery(type, text);
        }

        List<Organization> rs = new ArrayList<Organization>();
        for (Organization o : organizations) {
            LocalOrganization lo = new LocalOrganization();
            lo.setId(o.getId());
            lo.setName(o.getName());
            lo.setNciInstituteCode(o.getNciInstituteCode());
            lo.setExternalId(o.getExternalId() != null ? o.getExternalId().trim() : "");
            lo.setRetiredIndicator(o.getRetiredIndicator());
            rs.add(lo);
        }
        return rs;
        //return ObjectTools.reduceAll(organizations, "id", "name", "nciInstituteCode", "externalId");
    }

    public List<Agent> getAgentsTable(final Map parameterMap, final String text, final String nsc, final HttpServletRequest request) {
        List<Agent> agents = new ArrayList<Agent>();
        agents = agentRepository.getAgentsByNameAndNsc(text, nsc, false);
        return ObjectTools.reduceAll(agents, "id", "name", "nscNumber", "retiredIndicator");
    }

    /**
     * This method is invoked from admin page to Search all Devices
     * @param parameterMap
     * @param text
     * @param request
     * @return
     */
    public List<Device> getDevices(final Map parameterMap, final String text, final HttpServletRequest request) {
        List<Device> devices = new ArrayList<Device>();
        if (text != null) {
            devices = deviceRepository.getByMatchText(text, false);
        }
        return ObjectTools.reduceAll(devices, "id", "commonName", "brandName", "type", "retiredIndicator");
    }

    @SuppressWarnings("finally")
    private List<ParticipantAjaxableDomainObject> getParticipants(String text) {
        log.debug("Searching participants by: " + text);
        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterParticipantsWithMatchingText(text);
        query.filterByStudyPrimaryIdentifiers();

        try {
            participants = participantAjaxableDomainObjectRepository.findParticipants(query);
        } catch (Exception e) {
            log.error("ERROR while searching Participants: " , e);
            throw new RuntimeException("Formatting Error", e);
        } finally {
            return participants;
        }
    }

    public List<ParticipantAjaxableDomainObject> buildParticipantTable(final Map parameterMap, final String type, final String text, final HttpServletRequest request) {
        List<ParticipantAjaxableDomainObject> participants = new ArrayList<ParticipantAjaxableDomainObject>();
        if (type != null && text != null) {
            participants = getParticipants(text);
        }
        return participants;
    }


    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(final StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(final StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(final ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    public AdverseEventDao getAdverseEventDao() {
        return adverseEventDao;
    }

    public void setAdverseEventDao(final AdverseEventDao adverseEventDao) {
        this.adverseEventDao = adverseEventDao;
    }

    public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
        return expeditedAdverseEventReportDao;
    }

    public void setExpeditedAdverseEventReportDao(final ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }


    public InvestigationalNewDrugDao getInvestigationalNewDrugDao() {
        return investigationalNewDrugDao;
    }

    public void setInvestigationalNewDrugDao(final InvestigationalNewDrugDao investigationalNewDrugDao) {
        this.investigationalNewDrugDao = investigationalNewDrugDao;
    }

    public void setInvestigatorDao(final InvestigatorDao investigatorDao) {
        this.investigatorDao = investigatorDao;
    }

    public DeviceRepository getDeviceRepository() {
        return deviceRepository;
    }

    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Required
    public void setStudySearchableAjaxableDomainObjectRepository(StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository) {
        this.studySearchableAjaxableDomainObjectRepository = studySearchableAjaxableDomainObjectRepository;
    }
    @Required
	public void setParticipantAjaxableDomainObjectRepository(
			ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository) {
		this.participantAjaxableDomainObjectRepository = participantAjaxableDomainObjectRepository;
	}

	public OrganizationRepository getOrganizationRepository() {
		return organizationRepository;
	}
	
	@Required
	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public ResearchStaffRepository getResearchStaffRepository() {
		return researchStaffRepository;
	}

	@Required
	public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

	public InvestigatorRepository getInvestigatorRepository() {
		return investigatorRepository;
	}
	
	@Required
	public void setInvestigatorRepository(InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}

    public AgentRepository getAgentRepository() {
        return agentRepository;
    }

    public void setAgentRepository(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public Class<?>[] controllers() {
        return CONTROLLERS; 
    }
}

class ColumnValueObject {
    public String title;

    public String propertyName;

    public String alias;

    public String format;

    public String cellDisplay;

    public String cellType;

    public boolean sortable;

    public ColumnValueObject(final String propertyName, final String title, final String alias, final String format,
                             final String cellDisplay, final String cellType) {
        this.title = title;
        this.propertyName = propertyName;
        this.alias = alias;
        this.format = format;
        this.cellDisplay = cellDisplay;
        this.cellType = cellType;
    }

    public static ColumnValueObject create(final String propertyName) {
        return ColumnValueObject.create(propertyName, null, null);
    }

    public static ColumnValueObject create(final String propertyName, final String title) {
        return ColumnValueObject.create(propertyName, title, null);
    }

    public static ColumnValueObject create(final String propertyName, final String title, final String alias) {
        return new ColumnValueObject(propertyName, title, alias, null, null, null);
    }
}
