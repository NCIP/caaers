package gov.nih.nci.cabig.caaers.resolver;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.RemoteStudy;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;
import gov.nih.nci.coppa.po.HealthCareProvider;
import gov.nih.nci.coppa.po.Person;
import gov.nih.nci.coppa.services.pa.DocumentWorkflowStatus;
import gov.nih.nci.coppa.services.pa.PlannedActivity;
import gov.nih.nci.coppa.services.pa.StudyOverallStatus;
import gov.nih.nci.coppa.services.pa.StudyProtocol;
import gov.nih.nci.coppa.services.pa.StudySiteContact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.iso._21090.DSETII;
import org.iso._21090.ENXP;
import org.iso._21090.EntityNamePartType;
import org.iso._21090.II;
import org.iso._21090.TEL;

import com.semanticbits.coppa.infrastructure.service.RemoteResolver;
import com.semanticbits.coppasimulator.util.CoppaObjectFactory;
import com.semanticbits.coppasimulator.util.CoppaPAObjectFactory;

public class RemoteStudyResolver extends BaseResolver implements RemoteResolver{
	
	/** The log. */
	private Logger log = Logger.getLogger(RemoteStudyResolver.class);

	/**
	 * Searches COPPA database for Study Protocols similar to the example Study object that is passed into it.
	 * 
	 * @param Object the remote Study
	 * @return the object list; list of remoteHealthcareSites
	 */
	public List<Object> find(Object example) {
		
		log.info("Entering RemoteStudyResolver.find()");
		
		RemoteStudy remoteStudyExample = (RemoteStudy)example;
		
		//get by all other criteria
		OrganizationAssignedIdentifier organizationAssignedIdentifier = remoteStudyExample.getNciAssignedIdentifier();
		String identifierValue = null;
		if(organizationAssignedIdentifier != null){
			identifierValue = organizationAssignedIdentifier.getValue();
		}
		String paPayLoad = CoppaPAObjectFactory.getStudyProtocolSearchXML(remoteStudyExample.getShortTitle(), identifierValue, remoteStudyExample.getStatus());
		String limitOffsetPayload = CoppaPAObjectFactory.getLimitOffsetXML(5, 0);
		List<String> payLoads = new ArrayList<String>();
		List<Object> remoteStudies = new ArrayList<Object>();
		payLoads.add(paPayLoad);
		payLoads.add(limitOffsetPayload);
		Metadata mData = new Metadata(OperationNameEnum.search.getName(), "extId", ServiceTypeEnum.STUDY_PROTOCOL.getName());
		String resultXml = "";
		try{
			resultXml = broadcastCOPPA(payLoads, mData);
			List<String> results = XMLUtil.getObjectsFromCoppaResponse(resultXml);
			List< gov.nih.nci.coppa.services.pa.StudyProtocol> studyProtocols = 
							new ArrayList< gov.nih.nci.coppa.services.pa.StudyProtocol>();
			
			for (String result:results) {
				studyProtocols.add(CoppaPAObjectFactory.getStudyProtocolObject(result));
			}
			
			for (gov.nih.nci.coppa.services.pa.StudyProtocol studyProtocol : studyProtocols) {
				RemoteStudy remoteStudy = getRemoteStudyFromStudyProtocol(studyProtocol);
				
				if (remoteStudy != null) {
					remoteStudies.add(remoteStudy);
				}
			}
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		log.info("Exiting RemoteStudyResolver.find()");
		return remoteStudies;
	}

	/**
	 * This method is invoked by the Interceptor. It fetches the COPPA Study given the extension/externalId. 
	 */
	public Object getRemoteEntityByUniqueId(String externalId) {
		
		log.info("Entering RemoteStudyResolver.getRemoteEntityByUniqueId(externalId :" + externalId + ")");
		String payLoad = CoppaPAObjectFactory.getPAIdXML(CoppaPAObjectFactory.getPAId(externalId));
		Metadata mData = new Metadata(OperationNameEnum.getById.getName(), "extId", ServiceTypeEnum.STUDY_PROTOCOL.getName());
		
		String resultXml = "";
		List<Object> remoteStudies = new ArrayList<Object>();
		try{
			resultXml = broadcastCOPPA(payLoad, mData);
			List<String> results = XMLUtil.getObjectsFromCoppaResponse(resultXml);
			List< gov.nih.nci.coppa.services.pa.StudyProtocol> studyProtocols = 
							new ArrayList< gov.nih.nci.coppa.services.pa.StudyProtocol>();
			
			for (String result:results) {
				studyProtocols.add(CoppaPAObjectFactory.getStudyProtocolObject(result));
			}
			
			for (gov.nih.nci.coppa.services.pa.StudyProtocol studyProtocol : studyProtocols) {
				RemoteStudy remoteStudy = getRemoteStudyFromStudyProtocol(studyProtocol);
				if (remoteStudy != null) {
					remoteStudies.add(remoteStudy);
				}
			}
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		log.info("Exiting RemoteStudyResolver.getRemoteEntityByUniqueId()");
		if(remoteStudies.size() > 0){
			return remoteStudies.get(0);
		}
		return null;
	}

	public Object saveOrUpdate(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**This method takes a interventionalStudyProtocol and converts it to a RemoteStudy which caAERS understands.
	 * This is responsible for mapping all the attributes/associations that we fetch from COPPA.
	 * 
	 * @param interventionalStudyProtocol
	 * @return
	 */
	public RemoteStudy getRemoteStudyFromStudyProtocol(StudyProtocol studyProtocol) throws Exception {
		RemoteStudy remoteStudy = new RemoteStudy();
		
		//Set core attributes from COPPA Object
		//shortTitle --> PublicTitle 
		remoteStudy.setShortTitle(CoppaPAObjectFactory.getShortTitleFromStudyProtocol(studyProtocol));
		//longTitle --> OfficialTitle
		remoteStudy.setLongTitle(CoppaPAObjectFactory.getLongTitleFromStudyProtocol(studyProtocol));
		remoteStudy.setPhaseCode(CoppaConstants.coppaMap.get(CoppaPAObjectFactory.getPhaseCodeFromStudyProtocol(studyProtocol)));
		remoteStudy.setDescription(CoppaPAObjectFactory.getPublicDescriptionFromStudyProtocol(studyProtocol));
		remoteStudy.setExternalId(studyProtocol.getIdentifier().getExtension());
		remoteStudy.setStatus(CoppaConstants.coppaMap.get(getStudyStatus(studyProtocol)));
		
		//NOT NULL Fields
		remoteStudy.setMultiInstitutionIndicator(Boolean.TRUE);
		remoteStudy.setAdeersReporting(Boolean.FALSE);
		remoteStudy.setAeTerminology(createCtcV3Terminology(remoteStudy));
		remoteStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
		//Mapping StudyProtocol's Assigned Identifer as OrganizationAssignedIdentifer in caAERS.
		//Assign organization will be NCI.
		String identifierValue = studyProtocol.getAssignedIdentifier().getExtension();
		if(identifierValue != null && !"".equals(identifierValue)){
			populateIdentifer(remoteStudy,null,identifierValue,CoppaConstants.NCI_ASSIGNED_IDENTIFIER);
		}
		//If ShortTitle/PublicTitle is empty ShortTitle = LongTitle.
		if(remoteStudy.getShortTitle() == null){
			remoteStudy.setShortTitle(remoteStudy.getLongTitle());
		}
		populateStudyOrganizationsForStudyProtocol(studyProtocol,remoteStudy);
		populateStudyTherapies(studyProtocol, remoteStudy);
		reArrangeStudyIdentifers(remoteStudy);
		
		return remoteStudy;
	}
	
	/**
	 * This method will copy the {@link StudyTherapy}s.  
	 * @param studyProtocol - Study from PA
	 * @param remoteStudy - A caAERS Study, marked as Remote. 
	 */
	public void populateStudyTherapies(StudyProtocol studyProtocol,RemoteStudy remoteStudy){
		String payLoad = CoppaPAObjectFactory.getPAIdXML(CoppaPAObjectFactory.getPAId(studyProtocol.getIdentifier().getExtension()));
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.PLANNED_ACTIVITY.getName());
		String resultXml  = "";
		try {
			resultXml  = broadcastCOPPA(payLoad,mData);
			List<String> results = XMLUtil.getObjectsFromCoppaResponse(resultXml);
			
			//1. remove all the therapies that are not present in PlannedActivities
			//2. add all therapies present in PlannedActivities
			
			List<StudyTherapyType> unwantedTherapyTypes = new ArrayList<StudyTherapyType>(Arrays.asList(StudyTherapyType.values()));
			
			for (String result:results) {
				PlannedActivity plannedActivity = CoppaPAObjectFactory.getPlannedActivity(result);
				if(StringUtils.equals(plannedActivity.getCategoryCode().getCode(), "Intervention")){
					StudyTherapyType therapyType = StudyTherapyType.getByCoppaName(plannedActivity.getSubcategoryCode().getCode());
					unwantedTherapyTypes.remove(therapyType);
					if(!remoteStudy.hasTherapyOfType(therapyType)){
						remoteStudy.addStudyTherapy(therapyType);
					}
				}
			}
			
			//remove all therapies not present in COPPA.
			for(StudyTherapyType therapyType : unwantedTherapyTypes){
				remoteStudy.removeTherapiesOfType(therapyType);
			}
			
		} catch (CaaersSystemException e) {
			log.error(e);
		}
	}
	
	/**
	 * Gets the study organizations for interventional study protocol.
	 * 
	 * @param interventionalStudyProtocol the interventional study protocol
	 * 
	 * @return the study organizations for interventional study protocol
	 */
	public void populateStudyOrganizationsForStudyProtocol(StudyProtocol studyProtocol,RemoteStudy remoteStudy) throws Exception{
		//call search on studyParticipation using the interventionalStudyProtocol II. use the returned list
		String payLoad = CoppaPAObjectFactory.getPAIdXML(CoppaPAObjectFactory.getPAId(studyProtocol.getIdentifier().getExtension()));
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_SITE.getName());
		String resultXml  = "";
		try {
			resultXml  = broadcastCOPPA(payLoad,mData);
		} catch (CaaersSystemException e) {
			log.error(e);
		}

		List<String> results = XMLUtil.getObjectsFromCoppaResponse(resultXml);
		List<gov.nih.nci.coppa.services.pa.StudySite> studySiteList = new ArrayList<gov.nih.nci.coppa.services.pa.StudySite>();
		
		for (String result:results) {
			studySiteList.add(CoppaPAObjectFactory.getStudySite(result));
		}

		//go thru the returned list. we need the ones who have functional Code as LeadOrg(ResearchOrganization)/TreatingSite(HealthcareFacility)/Funding source(Sponsor)
		for(gov.nih.nci.coppa.services.pa.StudySite studyParticipationTemp: studySiteList){
			if(studyParticipationTemp.getStatusCode().getCode() != CoppaConstants.NULLIFIED_RECORD){
				if(studyParticipationTemp.getFunctionalCode().getCode().equals(CoppaConstants.LEAD_ORGANIZATION)){
					//Fetch the coordinating center(ResearchOrganziation) and add to the list to be returned
					StudyCoordinatingCenter studyCoordinatingCenter = getCoordinatingCenterFromCoppaStudySite(studyParticipationTemp);
					if(studyCoordinatingCenter != null){
						String identifierValue = studyParticipationTemp.getLocalStudyProtocolIdentifier().getValue();
						populateIdentifer(remoteStudy,studyCoordinatingCenter,identifierValue,CoppaConstants.COORDINATING_CENTER_IDENTIFER);
						populateStudyInvestigators(studyParticipationTemp,studyCoordinatingCenter);
						remoteStudy.addStudyOrganization(studyCoordinatingCenter);
					}
				}
				if(studyParticipationTemp.getFunctionalCode().getCode().equals(CoppaConstants.SPONSOR)){
					//Fetch the Funding sponsor(ResearchOrganziation) and add to the list to be returned
					StudyFundingSponsor studyFundingSponsor = getFundingSponsorFromCoppaStudySite(studyParticipationTemp);
					if(studyFundingSponsor != null){
						String identifierValue = studyParticipationTemp.getLocalStudyProtocolIdentifier().getValue();
						populateIdentifer(remoteStudy,studyFundingSponsor,identifierValue,CoppaConstants.PROTOCOL_AUTHORITY_IDENTIFIER);
						populateStudyInvestigators(studyParticipationTemp,studyFundingSponsor);
						remoteStudy.addStudyOrganization(studyFundingSponsor);
					}
				}
				if(studyParticipationTemp.getFunctionalCode().getCode().equals(CoppaConstants.TREATING_SITE)){
					//Fetch the Study Site(HealthcareFacility) and add to the list to be returned
					StudySite studySite = getStudySiteFromCoppaStudySite(studyParticipationTemp);
					if(studySite != null){
						populateStudyInvestigators(studyParticipationTemp,studySite);
						remoteStudy.addStudyOrganization(studySite);
					}
				}
			}
		}
	}
	
	/**
	 * This method creates a OrganizationAssignedIdentifer and assign's it to the Study.
	 * @param studyOrganization
	 * @param identifierValue
	 * @param organizationRole
	 */
	public void populateIdentifer(RemoteStudy remoteStudy,StudyOrganization studyOrganization,String identifierValue,String organizationRole){
		Date today = new Date();
		OrganizationAssignedIdentifier orgAssignedIdentifier = new OrganizationAssignedIdentifier();
		orgAssignedIdentifier.setType(organizationRole);
		if(studyOrganization != null){
			orgAssignedIdentifier.setOrganization(studyOrganization.getOrganization());
		}
		
		if(identifierValue == null || "".equals(identifierValue)){
			identifierValue = "**NULL**-" + Long.toString(today.getTime());
		}
		orgAssignedIdentifier.setValue(identifierValue);
		if(CoppaConstants.PROTOCOL_AUTHORITY_IDENTIFIER.equals(organizationRole)){
			orgAssignedIdentifier.setPrimaryIndicator(Boolean.TRUE);
		}
		
		remoteStudy.addIdentifier(orgAssignedIdentifier);
	}
	
	/**
	 * Kludge 
	 * Method to rearrange identifiers to have the PROTOCOL_AUTHORITY_IDENTIFIER first in the list.
	 * @param remoteStudy
	 */
	public void reArrangeStudyIdentifers(RemoteStudy remoteStudy){
		
		Map<String,Identifier> identiferMap = new HashMap<String,Identifier>(); 
		for(Identifier identifier : remoteStudy.getIdentifiers()){
			if(identifier instanceof OrganizationAssignedIdentifier){
				identiferMap.put(identifier.getType(), identifier);
			}
		}
		remoteStudy.getIdentifiers().clear();
		Identifier fsIdentifier = identiferMap.get(CoppaConstants.PROTOCOL_AUTHORITY_IDENTIFIER);
		if(fsIdentifier != null){
			remoteStudy.addIdentifier(fsIdentifier);
		}
		Identifier ccIdentifer =  identiferMap.get(CoppaConstants.COORDINATING_CENTER_IDENTIFER);
		if(ccIdentifer != null){
			remoteStudy.addIdentifier(ccIdentifer);
		}
		Identifier nciIdentifier = identiferMap.get(CoppaConstants.NCI_ASSIGNED_IDENTIFIER);
		if(nciIdentifier != null){
			remoteStudy.addIdentifier(nciIdentifier);
		}
	}
	
	/**
	 * Gets the coordinating center from the studySite.
	 * 
	 * @param studyParticipationTemp the study participation temp
	 * 
	 * @return the coordinating center
	 */
	public StudyCoordinatingCenter getCoordinatingCenterFromCoppaStudySite(gov.nih.nci.coppa.services.pa.StudySite studyParticipationTemp) throws Exception {
		StudyCoordinatingCenter studyCoordinatingCenter = new StudyCoordinatingCenter();
		studyCoordinatingCenter.setStartDate(DateUtils.today());
		RemoteOrganization remoteOrganization = getRemoteOrganizationFromStudyparticipation(studyParticipationTemp);
		studyCoordinatingCenter.setOrganization(remoteOrganization);
		return studyCoordinatingCenter;
		
	}

	/**
	 * Gets the funding sponsor from study participation.
	 * 
	 * @param studyParticipationTemp the study participation temp
	 * 
	 * @return the funding sponsor from study participation
	 */
	public StudyFundingSponsor getFundingSponsorFromCoppaStudySite(gov.nih.nci.coppa.services.pa.StudySite studyParticipationTemp) throws Exception{
		StudyFundingSponsor studyFundingSponsor = new StudyFundingSponsor();
		studyFundingSponsor.setStartDate(DateUtils.today());
		RemoteOrganization remoteOrganization = getRemoteOrganizationFromStudyparticipation(studyParticipationTemp);
		studyFundingSponsor.setOrganization(remoteOrganization);
		return studyFundingSponsor;
	}
	
	
	/**
	 * Gets the studysite from study participation.
	 * 
	 * @param studyParticipationTemp the study participation temp
	 * 
	 * @return the studysite from study participation
	 */
	public StudySite getStudySiteFromCoppaStudySite(gov.nih.nci.coppa.services.pa.StudySite studyParticipationTemp) throws Exception{
		StudySite studySite = new StudySite();
		studySite.setStartDate(DateUtils.today());
		String hcfResultXml = "";
		
		String x = studyParticipationTemp.getHealthcareFacility().getExtension();
		String hcfPayLoad = CoppaObjectFactory.getHealthcareFacilityIdXML(CoppaObjectFactory.getHealthcareFacilityId(x));
		Metadata mData = new Metadata(OperationNameEnum.getById.getName(), "extId", ServiceTypeEnum.HEALTH_CARE_FACILITY.getName());
		hcfResultXml = broadcastCOPPA(hcfPayLoad,mData);
		List<String> hcfResults = XMLUtil.getObjectsFromCoppaResponse(hcfResultXml);
		gov.nih.nci.coppa.po.HealthCareFacility healthcareFacility = CoppaObjectFactory.getHealthcareFacility(hcfResults.get(0));
		
		//Assuming here that the payerII in the HCF is the Organization II
		DSETII dsetii = CoppaObjectFactory.getDSETIISearchCriteria(healthcareFacility.getPlayerIdentifier().getExtension());
		String organizationPayload = CoppaObjectFactory.getCoppaIIXml(dsetii);
		Metadata organizationMData = new Metadata(OperationNameEnum.getById.getName(),  "extId", ServiceTypeEnum.ORGANIZATION.getName());
		String organizationResultXml = broadcastCOPPA(organizationPayload, organizationMData);
		List<String> results = XMLUtil.getObjectsFromCoppaResponse(organizationResultXml);
		gov.nih.nci.coppa.po.Organization coppaOrganization = CoppaObjectFactory.getCoppaOrganization(results.get(0));
		
		//RemoteOrganization remoteOrganization = (RemoteOrganization)organizationResolver.populateRemoteOrganization(coppaOrganization);
		RemoteOrganization remoteOrganization = getRemoteOrganizationFromCoppaOrganization(coppaOrganization,healthcareFacility.getIdentifier().getItem());;
		studySite.setOrganization(remoteOrganization);
		
		return studySite;
	}
	
	/**
	 * Gets the organization from study participation.
	 * 
	 * @param studyParticipationTemp the study participation temp
	 * 
	 * @return the organization from study participation
	 */
	public RemoteOrganization getRemoteOrganizationFromStudyparticipation(gov.nih.nci.coppa.services.pa.StudySite studyParticipationTemp) throws Exception{
		
		String researchOrgPayLoad = CoppaObjectFactory.getResearchOrganizationIdXML
		(CoppaObjectFactory.getResearchOrganizationId(studyParticipationTemp.getResearchOrganization().getExtension()));
		Metadata researchOrgMData = new Metadata(OperationNameEnum.getById.getName(), "extId", ServiceTypeEnum.RESEARCH_ORGANIZATION.getName());
		String researchOrgResultXml = broadcastCOPPA(researchOrgPayLoad, researchOrgMData);
		List<String> roResults = XMLUtil.getObjectsFromCoppaResponse(researchOrgResultXml);
		gov.nih.nci.coppa.po.ResearchOrganization researchOrganization = CoppaObjectFactory.getResearchOrganization(roResults.get(0));
		
		II playerII = CoppaObjectFactory.getIISearchCriteria(researchOrganization.getPlayerIdentifier().getExtension());
		String organizationPayload = CoppaObjectFactory.getCoppaIIXml(playerII);
		Metadata organizationMData = new Metadata(OperationNameEnum.getById.getName(),  "extId", ServiceTypeEnum.ORGANIZATION.getName());
		String organizationResultXml = broadcastCOPPA(organizationPayload, organizationMData);
		List<String> results = XMLUtil.getObjectsFromCoppaResponse(organizationResultXml);
		gov.nih.nci.coppa.po.Organization coppaOrganization = CoppaObjectFactory.getCoppaOrganization(results.get(0));
		
		RemoteOrganization remoteOrganization = getRemoteOrganizationFromCoppaOrganization(coppaOrganization,researchOrganization.getIdentifier().getItem());

		return remoteOrganization;
	}
	
	/**
	 * This method populates a RemoteOrganization from Coppa Organization.
	 * @param coppaOrganization
	 * @param iiList
	 * @return
	 */
	private RemoteOrganization getRemoteOrganizationFromCoppaOrganization(gov.nih.nci.coppa.po.Organization coppaOrganization, List<II> iiList){
		RemoteOrganization remoteOrganization = new RemoteOrganization();
		remoteOrganization.setName(CoppaObjectFactory.getName(coppaOrganization.getName()));
		remoteOrganization.setCity(CoppaObjectFactory.getCity(coppaOrganization.getPostalAddress()));
		remoteOrganization.setCountry(CoppaObjectFactory.getCountry(coppaOrganization.getPostalAddress()));
		remoteOrganization.setExternalId(coppaOrganization.getIdentifier().getExtension());
		for(II ii: iiList){
			if(ii.getRoot().equalsIgnoreCase(CoppaConstants.CTEP_ROOT) || ii.getIdentifierName().equals(CoppaConstants.CTEP_IDENTIFIER_NAME)){
				remoteOrganization.setNciInstituteCode(ii.getExtension());
			}
		}
		return remoteOrganization;
	}
	
	
	/**     
     * This method invokes STUDY_SITE_CONTACT.getByStudySite to fetch Site Investigator for a Study.
     * roleCode's supported are in SITE_INVESTIGATOR_LIST.      
     * @param studyProtocol     
     */     
    public void populateStudyInvestigators(gov.nih.nci.coppa.services.pa.StudySite coppaStudySite, StudyOrganization studyOrganization){
        String studySiteContactResultXml = null;
        String studySitePayLoad = CoppaPAObjectFactory.getPAIdXML(CoppaPAObjectFactory.getStudySiteId(coppaStudySite.getIdentifier().getExtension()));
        Metadata mData = new Metadata("getByStudySite", "extId", ServiceTypeEnum.STUDY_SITE_CONTACT.getName());
        studySiteContactResultXml = broadcastCOPPA(studySitePayLoad,mData);
        StudySiteContact studySiteContact = null;
        List<String> results = XMLUtil.getObjectsFromCoppaResponse(studySiteContactResultXml);
        for(String studySiteContactXml : results){  
        	studySiteContact = CoppaPAObjectFactory.getStudySiteContact(studySiteContactXml);
        	if(studySiteContact != null && studySiteContact.getStatusCode().getCode() != CoppaConstants.NULLIFIED_RECORD){
            	if(CoppaConstants.SITE_INVESTIGATOR_LIST.contains(studySiteContact.getRoleCode().getCode())){
            		HealthCareProvider healthCareProvider = getHealthCareProviderFromExtension(studySiteContact.getHealthCareProvider().getExtension());
            		if(healthCareProvider != null){
            			 gov.nih.nci.coppa.po.Person coppaPerson = getCoppaPersonFromPersonExtension(healthCareProvider.getPlayerIdentifier().getExtension());
            			 StudyInvestigator studyInvestigator = getPopulatedStudyInvestigator(coppaPerson, studyOrganization, "SI");
            			 studyOrganization.addStudyInvestigators(studyInvestigator);
            		}
            	}
        	}
        }
    }
	
	/**
	 * Fetch HealthCareProvider from COPPA, given the extension value.
	 * @param extension
	 * @return
	 */
    private HealthCareProvider getHealthCareProviderFromExtension(String extension){
        String coppaHealthCareProviderXml = CoppaObjectFactory.getHealthCareProviderIdXML(extension);
        Metadata healthCareProviderMData = new Metadata(OperationNameEnum.getById.getName(), "extId", ServiceTypeEnum.HEALTH_CARE_PROVIDER.getName());
        String healthCareProviderResult  = broadcastCOPPA(coppaHealthCareProviderXml,healthCareProviderMData);
        List<String> healthCareProviderResults = XMLUtil.getObjectsFromCoppaResponse(healthCareProviderResult);
        if(healthCareProviderResults != null && !"".equals(healthCareProviderResults)){
        	return CoppaObjectFactory.getCoppaHealthCareProvider(healthCareProviderResults.get(0));
        }
        return null;
    }
	
	/**
	 * Fetch Person from COPPA, given the extension value.
	 * @param extension
	 * @return
	 */
    private Person getCoppaPersonFromPersonExtension(String extension) {
        String idXml = CoppaObjectFactory.getCoppaPersonIdXML(extension);                
        //above player id is the Id of a Person. Now get the Person by Id.
        Metadata personMData = new Metadata(OperationNameEnum.getById.getName(), "externalId", ServiceTypeEnum.PERSON.getName());
        String personResultXml = broadcastCOPPA(idXml,personMData);
        List<String> persons = XMLUtil.getObjectsFromCoppaResponse(personResultXml);
        if(persons.size() > 0){
            return CoppaObjectFactory.getCoppaPerson(persons.get(0));
        }
        return null;
    }
	
    private StudyInvestigator getPopulatedStudyInvestigator(Person coppaPerson, StudyOrganization studyOrganization, String roleCode) {
    	
    	StudyInvestigator studyInvestigator = new StudyInvestigator();
    	studyInvestigator.setStartDate(DateUtils.today());
    	RemoteInvestigator remoteInvestigator = createRemoteInvestigator(coppaPerson, coppaPerson.getIdentifier().getExtension());
        remoteInvestigator.setExternalId(coppaPerson.getIdentifier().getExtension());
        SiteInvestigator siteInvestigator = new SiteInvestigator();
        siteInvestigator.setStartDate(DateUtils.today());
        siteInvestigator.setOrganization(studyOrganization.getOrganization());
        siteInvestigator.setInvestigator(remoteInvestigator);
        remoteInvestigator.addSiteInvestigator(siteInvestigator);
        studyInvestigator.setSiteInvestigator(siteInvestigator);
        studyInvestigator.setRoleCode(roleCode);
        return studyInvestigator;
    }
	
	
	private RemoteInvestigator createRemoteInvestigator(Person coppaPerson,String nciIdentifier) {
		RemoteInvestigator remoteInvestigator = new RemoteInvestigator();

		Iterator<ENXP> enxpItr = coppaPerson.getName().getPart().iterator();
		ENXP enxp;
		String firstName = "";
		String lastName = "";
		while(enxpItr.hasNext()){
			enxp = enxpItr.next();
			if(enxp.getType().equals(EntityNamePartType.GIV)){
				firstName = enxp.getValue();
			}

			if(enxp.getType().equals(EntityNamePartType.FAM)){
				lastName = enxp.getValue();
			}
		}        

        List<TEL> email = coppaPerson.getTelecomAddress().getItem();
        Iterator<TEL> emailItr = email.iterator();
        TEL nextTel = emailItr.next();
        String emailStr = "";
		emailStr = nextTel.getValue();
		//remove mailto: string from email 
		if (emailStr.startsWith("mailto:")) {
			emailStr = emailStr.substring("mailto:".length(), emailStr.length());
		}
		remoteInvestigator.setFirstName(firstName);
		remoteInvestigator.setLastName(lastName);
		remoteInvestigator.setEmailAddress(emailStr);
		remoteInvestigator.setExternalId(coppaPerson.getIdentifier().getExtension());
		if (nciIdentifier != null) {
			remoteInvestigator.setNciIdentifier(nciIdentifier);
		}
		remoteInvestigator.setAllowedToLogin(Boolean.FALSE);
		return remoteInvestigator;

	}
	
	
	/**
	 * This method will invoke STUDY_OVERALL_STATUS service to get the status of the StudyProtocol
	 * @param studyProtocol
	 * @return
	 * @throws Exception
	 */
	public String getStudyStatus(StudyProtocol studyProtocol) throws Exception{
		
		String paIdPayLoad = CoppaPAObjectFactory.getPAIdXML(CoppaPAObjectFactory.getPAId(studyProtocol.getIdentifier().getExtension()));
		Metadata mData = new Metadata(OperationNameEnum.getCurrentByStudyProtocol.getName(), "extId", ServiceTypeEnum.DOCUMENT_WORKFLOW_STATUS.getName());
		String documentWorkflowResultXml = broadcastCOPPA(paIdPayLoad, mData);
		List<String> documentWorkflowResults = XMLUtil.getObjectsFromCoppaResponse(documentWorkflowResultXml);
		DocumentWorkflowStatus documentWorkflowStatus = CoppaPAObjectFactory.getDocumentWorkflowStatus(documentWorkflowResults.get(0));
		
		String statusCode = "";
		StudyOverallStatus status = null;
		//If the DocumentWorkflowStatus does not have ABSTRACTED keyword in it then dont bother getting the overallStatus
		if(CoppaConstants.DOCUMENT_WORKFLOW_STATUS_LIST.contains(documentWorkflowStatus.getStatusCode().getCode())){
			//Call search on StudyOverallStatus using the StudyProtocol II.	
			mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_OVERALL_STATUS.getName());
			String studyStatusResultXml = broadcastCOPPA(paIdPayLoad,mData);
			List<String> results = XMLUtil.getObjectsFromCoppaResponse(studyStatusResultXml);
			if(results != null && results.size() > 0){
				status = CoppaPAObjectFactory.getStudyOverallStatus(results.get(0));
				statusCode = status.getStatusCode().getCode();
			}
		}
		return statusCode;
	}
	
	/**
	 * Creates default AeTerminology and returns.
	 * @param 
	 * @return
	 */
    private AeTerminology createCtcV3Terminology(final Study s) {
        AeTerminology t = s.getAeTerminology();
        Ctc v3 =  new Ctc();
        v3.setId(3);
        v3.setName(4 + "");
        t.setTerm(Term.CTC);
        t.setCtcVersion(v3);
        return t;
    }
	
	
}