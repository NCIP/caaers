package gov.nih.nci.cabig.caaers.resolver;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;
import edu.duke.cabig.c3pr.esb.impl.CaXchangeMessageBroadcasterImpl;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.RemoteStudy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;
import gov.nih.nci.coppa.po.HealthCareProvider;
import gov.nih.nci.coppa.services.pa.DocumentWorkflowStatus;
import gov.nih.nci.coppa.services.pa.StudyContact;
import gov.nih.nci.coppa.services.pa.StudyOverallStatus;
import gov.nih.nci.coppa.services.pa.StudyProtocol;
import gov.nih.nci.coppa.services.pa.StudySiteContact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.iso._21090.DSETII;
import org.iso._21090.II;

import com.semanticbits.coppa.infrastructure.service.RemoteResolver;
import com.semanticbits.coppasimulator.util.CoppaObjectFactory;
import com.semanticbits.coppasimulator.util.CoppaPAObjectFactory;

public class RemoteStudyResolver extends BaseResolver implements RemoteResolver{
	
	/** The log. */
	private Logger log = Logger.getLogger(RemoteStudyResolver.class);

	private InvestigatorResolver investigatorResolver;
	
	/**
	 * Searches Coppa database for Study Protocols simliar to the example Study object that is passed into it.
	 * 
	 * @param Object the remote Study
	 * @return the object list; list of remoteHealthcareSites
	 */
	public List<Object> find(Object example) {
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
			resultXml = sendMessage(payLoads, mData);
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
			e.printStackTrace();
		}
		return remoteStudies;
	}


	public Object getRemoteEntityByUniqueId(String arg0) {
		// TODO Auto-generated method stub
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
		Date today = new Date();
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
		remoteStudy.setAdeersReporting(Boolean.FALSE);
		remoteStudy.setAeTerminology(createCtcV3Terminology(remoteStudy));
		remoteStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
		//Mapping StudyProtocol's Assigned Identifer as OrganizationAssignedIdentifer in caAERS.
		//Assign organization will be NCI.
		String identifierValue = studyProtocol.getAssignedIdentifier().getExtension();
		if(identifierValue != null && !"".equals(identifierValue)){
			populateIdentifer(remoteStudy,null,identifierValue,CoppaConstants.NCI_ASSIGNED_IDENTIFIER);
		}
		//If ShortTitle  is null ShortTitle = AssignedIdentifer + 30 Chars of LongTitle.
		if(remoteStudy.getShortTitle() == null){
			StringBuilder fabricatedShortTile = new StringBuilder(identifierValue);
			fabricatedShortTile.append("-");
			fabricatedShortTile.append(remoteStudy.getLongTitle());
			if(fabricatedShortTile.length() > 30){
				remoteStudy.setShortTitle(fabricatedShortTile.substring(0, 30));
			}
		}
		
		populateStudyOrganizationsForStudyProtocol(studyProtocol,remoteStudy);

		return remoteStudy;
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
			resultXml  = sendMessage(payLoad,mData);
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
			if(studyParticipationTemp.getFunctionalCode().getCode().equals(CoppaConstants.LEAD_ORGANIZATION)){
				//Fetch the coordinating center(ResearchOrganziation) and add to the list to be returned
				StudyCoordinatingCenter studyCoordinatingCenter = getCoordinatingCenterFromCoppaStudySite(studyParticipationTemp);
				if(studyCoordinatingCenter != null){
					String identifierValue = studyParticipationTemp.getLocalStudyProtocolIdentifier().getValue();
					populateIdentifer(remoteStudy,studyCoordinatingCenter,identifierValue,CoppaConstants.COORDINATING_CENTER_IDENTIFER);
					remoteStudy.addStudyOrganization(studyCoordinatingCenter);
				}
			}
			if(studyParticipationTemp.getFunctionalCode().getCode().equals(CoppaConstants.SPONSOR)){
				//Fetch the Funding sponsor(ResearchOrganziation) and add to the list to be returned
				StudyFundingSponsor studyFundingSponsor = getFundingSponsorFromCoppaStudySite(studyParticipationTemp);
				if(studyFundingSponsor != null){
					String identifierValue = studyParticipationTemp.getLocalStudyProtocolIdentifier().getValue();
					populateIdentifer(remoteStudy,studyFundingSponsor,identifierValue,CoppaConstants.PROTOCOL_AUTHORITY_IDENTIFIER);
					remoteStudy.addStudyOrganization(studyFundingSponsor);
				}
			}
			if(studyParticipationTemp.getFunctionalCode().getCode().equals(CoppaConstants.TREATING_SITE)){
				//Fetch the Study Site(HealthcareFacility) and add to the list to be returned
				StudySite studySite = getStudySiteFromCoppaStudySite(studyParticipationTemp);
				if(studySite != null){
					remoteStudy.addStudyOrganization(studySite);
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
		
		if(CoppaConstants.PROTOCOL_AUTHORITY_IDENTIFIER.equals(organizationRole) || CoppaConstants.COORDINATING_CENTER_IDENTIFER.equals(organizationRole)){
			if(identifierValue == null || "".equals(identifierValue)){
				identifierValue = "caAERS-" + Long.toString(today.getTime());
				orgAssignedIdentifier.setPrimaryIndicator(Boolean.FALSE);
			}else{
				orgAssignedIdentifier.setPrimaryIndicator(Boolean.TRUE);
			}
		}else{
			orgAssignedIdentifier.setPrimaryIndicator(Boolean.FALSE);
		}
		orgAssignedIdentifier.setValue(identifierValue);
		remoteStudy.addIdentifier(orgAssignedIdentifier);
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
		String hcfResultXml = "";
		
		String x = studyParticipationTemp.getHealthcareFacility().getExtension();
		String hcfPayLoad = CoppaObjectFactory.getHealthcareFacilityIdXML(CoppaObjectFactory.getHealthcareFacilityId(x));
		Metadata mData = new Metadata(OperationNameEnum.getById.getName(), "extId", ServiceTypeEnum.HEALTH_CARE_FACILITY.getName());
		hcfResultXml = sendMessage(hcfPayLoad,mData);
		List<String> hcfResults = XMLUtil.getObjectsFromCoppaResponse(hcfResultXml);
		gov.nih.nci.coppa.po.HealthCareFacility healthcareFacility = CoppaObjectFactory.getHealthcareFacility(hcfResults.get(0));
		
		//Assuming here that the payerII in the HCF is the Organization II
		DSETII dsetii = CoppaObjectFactory.getDSETIISearchCriteria(healthcareFacility.getPlayerIdentifier().getExtension());
		String organizationPayload = CoppaObjectFactory.getCoppaIIXml(dsetii);
		Metadata organizationMData = new Metadata(OperationNameEnum.getById.getName(),  "extId", ServiceTypeEnum.ORGANIZATION.getName());
		String organizationResultXml = sendMessage(organizationPayload, organizationMData);
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
		String researchOrgResultXml = sendMessage(researchOrgPayLoad, researchOrgMData);
		List<String> roResults = XMLUtil.getObjectsFromCoppaResponse(researchOrgResultXml);
		gov.nih.nci.coppa.po.ResearchOrganization researchOrganization = CoppaObjectFactory.getResearchOrganization(roResults.get(0));
		
		II playerII = CoppaObjectFactory.getIISearchCriteria(researchOrganization.getPlayerIdentifier().getExtension());
		String organizationPayload = CoppaObjectFactory.getCoppaIIXml(playerII);
		Metadata organizationMData = new Metadata(OperationNameEnum.getById.getName(),  "extId", ServiceTypeEnum.ORGANIZATION.getName());
		String organizationResultXml = sendMessage(organizationPayload, organizationMData);
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
	 * This method instantiates a RemoteInvestigator and populates all the relevant attributes.  
	 * @param studyOrganization
	 * @param studyProtocol
	 * @param remoteStudy
	 * @throws Exception
	 */
	public void populateStudyInvestigators(StudyOrganization studyOrganization, StudyProtocol studyProtocol, RemoteStudy remoteStudy) throws Exception{
		RemoteInvestigator investigator = getPrincipalInvestigator(studyProtocol);
		StudyInvestigator studyInvestigator;
		if(investigator != null){
			studyInvestigator = new StudyInvestigator();
			studyInvestigator.setSiteInvestigator(investigator.getSiteInvestigators().get(0));
			studyInvestigator.setRoleCode("PI");
			studyOrganization.addStudyInvestigators(studyInvestigator);
		}
//		investigator = getStudyPrincipalInvestigator(studyProtocol);
//		if(investigator != null){
//			studyInvestigator = new StudyInvestigator();
//			studyInvestigator.setSiteInvestigator(investigator.getSiteInvestigators().get(0));
//			studyInvestigator.setRoleCode("SPI");
//			studyOrganization.addStudyInvestigators(studyInvestigator);
//		}
	}
	
	
	/**
	 * This method invokes STUDY_PARTICIPATION_CONTACT.getByStudyProtocolAndRole to fetch Principal Investigator for a Study.
	 * roleCode's supported are  
	 * @param studyProtocol
	 */
	public RemoteInvestigator getPrincipalInvestigator(StudyProtocol studyProtocol) throws Exception{
		RemoteInvestigator remoteInvestigator = null;
		StudySiteContact studySiteContact = null;
		HealthCareProvider healthCareProvider = null;
		
		String paPayLoad = CoppaPAObjectFactory.getPAIdXML(CoppaPAObjectFactory.getPAId(studyProtocol.getIdentifier().getExtension()));
		String rolePayLoad = CoppaPAObjectFactory.getStudySiteContactRoleCodeXml(CoppaConstants.PRINCIPAL_INVESTIGATOR);
		List<String> payLoads = new ArrayList<String>();
		payLoads.add(paPayLoad);
		payLoads.add(rolePayLoad);
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocolAndRole.getName(), "extId", ServiceTypeEnum.STUDY_SITE_CONTACT.getName());
		String studySiteResults = sendMessage(payLoads, mData);
		List<String> results = XMLUtil.getObjectsFromCoppaResponse(studySiteResults);
		if(results != null && results.size() > 0){
			studySiteContact = (StudySiteContact)CoppaPAObjectFactory.getStudySiteContact(results.get(0));
		}
		if(studySiteContact != null){
			II hcPII = studySiteContact.getHealthCareProvider();
			String coppaHealthCareProviderXml = CoppaObjectFactory.getIdXML(CoppaObjectFactory.getHealthcareProviderId(hcPII.getExtension()));
			Metadata healthCareProviderMData = new Metadata(OperationNameEnum.getById.getName(), "extId", ServiceTypeEnum.HEALTH_CARE_PROVIDER.getName());
			String healthCareProviderResult = sendMessage(coppaHealthCareProviderXml, healthCareProviderMData);
			healthCareProvider = CoppaObjectFactory.getCoppaHealthCareProvider(healthCareProviderResult);
			if(healthCareProvider != null){
				II personII = healthCareProvider.getPlayerIdentifier();	
				String idXml = CoppaObjectFactory.getCoppaIIXml(personII);
				//above player id is the Id of a Person . 						
				// now get  the Person by Id ... 
				Metadata personMData = new Metadata(OperationNameEnum.getById.getName(), "externalId", ServiceTypeEnum.PERSON.getName());
				String personResultXml = sendMessage(idXml, personMData);
				remoteInvestigator = investigatorResolver.loadInvestigatorForPersonResult(personResultXml);
			}
		}
		return remoteInvestigator;
	}
	
	/**
	 * This method invokes STUDY_SITE_CONTACT.getByStudyProtocolAndRole to fetch Principal Investigator for a Study.
	 * roleCode's supported are  
	 * @param studyProtocol
	 */
	public RemoteInvestigator getStudyPrincipalInvestigator(StudyProtocol studyProtocol) throws Exception{
		RemoteInvestigator remoteInvestigator = null;
		StudyContact studyContact = null;
		HealthCareProvider healthCareProvider = null;
		
		String paPayLoad = CoppaPAObjectFactory.getPAIdXML(CoppaPAObjectFactory.getPAId(studyProtocol.getIdentifier().getExtension()));
		String rolePayLoad = CoppaPAObjectFactory.getStudySiteContactRoleCodeXml(CoppaConstants.STUDY_PRINCIPAL_INVESTIGATOR);
		List<String> payLoads = new ArrayList<String>();
		payLoads.add(paPayLoad);
		payLoads.add(rolePayLoad);
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocolAndRole.getName(), "extId", ServiceTypeEnum.STUDY_CONTACT.getName());
		String studyParticipatioresults = sendMessage(payLoads, mData);
		List<String> results = XMLUtil.getObjectsFromCoppaResponse(studyParticipatioresults);
		if(results != null && results.size() > 0){
			studyContact = (StudyContact)CoppaPAObjectFactory.getStudyContact(results.get(0));
		}
		if(studyContact != null){
			II hcPII = studyContact.getHealthCareProvider();
			String coppaHealthCareProviderXml = CoppaObjectFactory.getIdXML(CoppaObjectFactory.getHealthcareProviderId(hcPII.getExtension()));
			Metadata healthCareProviderMData = new Metadata(OperationNameEnum.getById.getName(), "extId", ServiceTypeEnum.HEALTH_CARE_PROVIDER.getName());
			String healthCareProviderResult = sendMessage(coppaHealthCareProviderXml, healthCareProviderMData);
			healthCareProvider = CoppaObjectFactory.getCoppaHealthCareProvider(healthCareProviderResult);
			if(healthCareProvider != null){
				II personII = healthCareProvider.getPlayerIdentifier();	
				String idXml = CoppaObjectFactory.getCoppaIIXml(personII);
				//above player id is the Id of a Person . 						
				// now get  the Person by Id ... 
				Metadata personMData = new Metadata(OperationNameEnum.getById.getName(), "externalId", ServiceTypeEnum.PERSON.getName());
				String personResultXml = sendMessage(idXml, personMData);
				remoteInvestigator = investigatorResolver.loadInvestigatorForPersonResult(personResultXml);
			}
		}
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
		String documentWorkflowResultXml = sendMessage(paIdPayLoad, mData);
		List<String> documentWorkflowResults = XMLUtil.getObjectsFromCoppaResponse(documentWorkflowResultXml);
		DocumentWorkflowStatus documentWorkflowStatus = CoppaPAObjectFactory.getDocumentWorkflowStatus(documentWorkflowResults.get(0));
		
		String statusCode = "";
		StudyOverallStatus status = null;
		//If the DocumentWorkflowStatus does not have ABSTRACTED keyword in it then dont bother getting the overallStatus
		if(CoppaConstants.DOCUMENT_WORKFLOW_STATUS_LIST.contains(documentWorkflowStatus.getStatusCode().getCode())){
			//Call search on StudyOverallStatus using the StudyProtocol II.	
			mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_OVERALL_STATUS.getName());
			String studyStatusResultXml = sendMessage(paIdPayLoad,mData);
			List<String> results = XMLUtil.getObjectsFromCoppaResponse(studyStatusResultXml);
			if(results != null && results.size() > 0){
				status = CoppaPAObjectFactory.getStudyOverallStatus(results.get(0));
				statusCode = status.getStatusCode().getCode();
			}
		}
		return statusCode;
	}
	
	/**
	 * Delegates call to message broadcaster with a single pay-load element.
	 * @param message
	 * @param metaData
	 * @return
	 * @throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException
	 */
	public String sendMessage(String message,Metadata metaData) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {    	
        String result = null;
        try {
        	CaXchangeMessageBroadcasterImpl broadCaster =  new CaXchangeMessageBroadcasterImpl();
            broadCaster.setCaXchangeURL(CoppaConstants.CAXCHANGE_URL);
        	result = broadCaster.broadcastCoppaMessage(message, metaData);
            
		} catch (edu.duke.cabig.c3pr.esb.BroadcastException e) {

            throw new gov.nih.nci.cabig.caaers.esb.client.BroadcastException(e);
		}
    	return result;
    }
	
	/**
	 * 
	 * @param s
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
	
	/**
	 * Delegates call to message broadcaster with multiple pay-load elements.
	 * @param messages
	 * @param metaData
	 * @return
	 * @throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException
	 */
	public String sendMessage(List<String> messages,Metadata metaData) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {    	
        String result = null;
        try {
        	CaXchangeMessageBroadcasterImpl broadCaster =  new CaXchangeMessageBroadcasterImpl();
        	broadCaster.setCaXchangeURL(CoppaConstants.CAXCHANGE_URL);
        	result = broadCaster.broadcastCoppaMessage(messages, metaData);
		} catch (edu.duke.cabig.c3pr.esb.BroadcastException e) {

            throw new gov.nih.nci.cabig.caaers.esb.client.BroadcastException(e);
		}
    	return result;
    }

	public void setInvestigatorResolver(InvestigatorResolver investigatorResolver) {
		this.investigatorResolver = investigatorResolver;
	}
}