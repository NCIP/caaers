package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.MedDRADao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.Terminology;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class StudyServiceImpl extends AbstractImportServiceImpl implements StudyService {
	
	private OrganizationDao organizationDao;
	private AgentDao agentDao; 
	private MedDRADao meddraDao;
	private CtcDao ctcDao;
	private MeddraVersionDao meddraVersionDao;
	private SiteInvestigatorDao siteInvestigatorDao;
	private ResearchStaffDao researchStaffDao;
	private DiseaseTermDao diseaseTermDao;
	private LowLevelTermDao lowLevelTermDao;
	//TODO hook esb call
	// ProtocolBroadcastService esbCreateProtocol;

	/**
	 * Search using a sample populate Study object
	 * @param study the study object
	 * @return List of Study objects based on the sample study object
	 * @throws Exception runtime exception object
	 */
	public List<Study> search(Study study) throws Exception {		
		return getStudyDao().searchByExample(study, true);
	}

	/**
	 * Saves a study object
	 * @param study the study object
  	 * @throws Exception runtime exception object
  	 */
	public void save(Study study) throws Exception {
		//TODO call esb to broadcast protocol, POC
		getStudyDao().save(study);		
	}
	
	/*
	 *  Given a study object which has been serialized from an xml format
	 *  is recreated here and made ready to be saved. 
	 */
	public DomainObjectImportOutcome<Study> createStudyObjects(Study xstreamStudy)
	{
		Study st = new Study();
		DomainObjectImportOutcome<Study> studyImportOutcome = new DomainObjectImportOutcome<Study>();
		
		st.setShortTitle(StringUtils.isNotEmpty(xstreamStudy.getShortTitle()) ? xstreamStudy.getShortTitle() : "NA");
		st.setLongTitle(xstreamStudy.getLongTitle());
		st.setDescription(xstreamStudy.getDescription());
		st.setPrecis(xstreamStudy.getPrecis());
		st.setPhaseCode(xstreamStudy.getPhaseCode());
		st.setStatus(xstreamStudy.getStatus());
		
		st.setMultiInstitutionIndicator(xstreamStudy.getMultiInstitutionIndicator());
		st.setDrugAdministrationTherapyType(xstreamStudy.getDrugAdministrationTherapyType());
		st.setRadiationTherapyType(xstreamStudy.getRadiationTherapyType());
		st.setDeviceTherapyType(xstreamStudy.getDeviceTherapyType());
		st.setSurgeryTherapyType(xstreamStudy.getSurgeryTherapyType());
		st.setBehavioralTherapyType(xstreamStudy.getBehavioralTherapyType());
		
		migrateTerminology(st,xstreamStudy, studyImportOutcome); //Done
		migrateIdentifiers(st,xstreamStudy, studyImportOutcome);
		migrateStudyOrganizations(st,xstreamStudy, studyImportOutcome);
		migrateOrganizationAssignedIdentifier(st,xstreamStudy, studyImportOutcome);
		migrateStudyAgents(st,xstreamStudy, studyImportOutcome);
		migrateCtepStudyDiseases(st,xstreamStudy, studyImportOutcome);
		migrateTreatmentAssignments(st,xstreamStudy, studyImportOutcome);
		migrateMeddraStudyDiseases(st,xstreamStudy, studyImportOutcome);
		
		studyImportOutcome.setImportedDomainObject(st);
		studyUniquenessCheck(st,studyImportOutcome,Severity.ERROR);
		
		return studyImportOutcome;
	}
	
	
	private void migrateTreatmentAssignments(Study destination, Study source , DomainObjectImportOutcome studyImportOutcome){ 
		
		//TreatmentAssignments
		if (source.getTreatmentAssignments() != null) {
			for ( TreatmentAssignment treatmentAssignment : source.getTreatmentAssignments()){
				TreatmentAssignment target = new TreatmentAssignment();
				target.setCode(treatmentAssignment.getCode());
				target.setDoseLevelOrder(treatmentAssignment.getDoseLevelOrder());
				target.setDescription(treatmentAssignment.getDescription());
				target.setComments(treatmentAssignment.getComments());
				destination.addTreatmentAssignment(target);
			}
		}
	}
	
	
	private void migrateStudyAgents(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome){
		
		//StudyAgents
		if (source.getStudyAgents() != null) {
			
			for (int i = 0; i < source.getStudyAgents().size(); i++) {
				StudyAgent studyAgent = source.getStudyAgents().get(i);
				StudyAgent target = new StudyAgent();
				Agent agent = null;
				
				if ( studyAgent.getAgent().getName() != null ){
					agent = agentDao.getByName(studyAgent.getAgent().getName()) ;
					target.setAgent(agent);
				}
				if ( studyAgent.getAgent().getNscNumber() != null && agent == null ){
					agent = agentDao.getByNscNumber(studyAgent.getAgent().getNscNumber()) ;
					target.setAgent(agent);
				}
				if ( studyAgent.getOtherAgent() != null && agent == null) {
					target.setOtherAgent(studyAgent.getOtherAgent());
				}
				//TODO: this is incomplete 
				//for( StudyAgentINDAssociation indAssociation :studyAgent.getStudyAgentINDAssociations()){
					//indAssociation.getInvestigationalNewDrug().getIndNumber();
					//target.addStudyAgentINDAssociation(ass)
				//}
				destination.addStudyAgent(target);
				// TODO: ADD error handling with user interaction
			}
		}
	}
	
	private void migrateCtepStudyDiseases(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome){
		
		//CtepStudyDiseases
		if (source.getCtepStudyDiseases() != null){
			
			for (CtepStudyDisease ctepStudyDisease : source.getCtepStudyDiseases()) {
				CtepStudyDisease destinationCtepStudyDisease = new CtepStudyDisease();
				DiseaseTerm diseaseTerm = null; 
				if (ctepStudyDisease.getTerm() != null && ctepStudyDisease.getTerm().getTerm() != null) {
					diseaseTerm = diseaseTermDao.getByTermName(ctepStudyDisease.getTerm().getTerm());
				}
				if (ctepStudyDisease.getTerm() != null && diseaseTerm == null && ctepStudyDisease.getTerm().getMedraCode() != null) {
					diseaseTerm = diseaseTermDao.getByMeddra(ctepStudyDisease.getTerm().getMedraCode());
				}
				destinationCtepStudyDisease.setTerm(diseaseTerm);
				destinationCtepStudyDisease.setLeadDisease(ctepStudyDisease.getLeadDisease() == null ? Boolean.FALSE : ctepStudyDisease.getLeadDisease() );
				destination.addCtepStudyDisease(destinationCtepStudyDisease);
			}
		}
	}
	
	private void migrateMeddraStudyDiseases(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome){
		
		//CtepStudyDiseases
		if (source.getMeddraStudyDiseases() != null){
			
			for (MeddraStudyDisease meddraStudyDisease : source.getMeddraStudyDiseases()) {
				MeddraStudyDisease destinationMeddraStudyDisease = new MeddraStudyDisease();
				LowLevelTerm lowLevelTerm = null; 
				if (meddraStudyDisease.getMeddraCode()!= null) {
					 lowLevelTerm = lowLevelTermDao.getByMeddraCode(meddraStudyDisease.getMeddraCode()).size() > 0 ?
							 lowLevelTermDao.getByMeddraCode(meddraStudyDisease.getMeddraCode()).get(0) : null; 
				}
				
				destinationMeddraStudyDisease.setTerm(lowLevelTerm == null ? null : lowLevelTerm);
				destinationMeddraStudyDisease.setMeddraCode(meddraStudyDisease.getMeddraCode());
				destination.addMeddraStudyDisease(destinationMeddraStudyDisease);
			}
		}
	}
	
	private void migrateTerminology(Study destination,Study source, DomainObjectImportOutcome studyImportOutcome){
		
		// Terminology and Version 
		if (source.getTerminology() != null){
			if(source.getTerminology().getCtcVersion() != null){
				Ctc ctc = ctcDao.getById(Integer.parseInt(source.getTerminology().getCtcVersion().getName()));
				Terminology t = destination.getTerminology();
				t.setTerm(Term.CTC);
				t.setCtcVersion(ctc);
				ifNullObject(ctc,studyImportOutcome, Severity.ERROR);
			}
			if(source.getTerminology().getMeddraVersion() != null){
				MeddraVersion meddraVersion = meddraVersionDao.getById(Integer.parseInt(source.getTerminology().getMeddraVersion().getName()));
				Terminology t = destination.getTerminology();
				t.setTerm(Term.MEDDRA);
				t.setMeddraVersion(meddraVersion);
				ifNullObject(meddraVersion,studyImportOutcome, Severity.ERROR);
			}
		}
		ifNullObject(source.getTerminology(),studyImportOutcome, Severity.ERROR);
	}
	
	
	private void migrateStudyOrganizations(Study destination,Study source, DomainObjectImportOutcome studyImportOutcome){
		
		if (source.getStudyOrganizations() != null) {
			for (int i = 0; i < source.getStudyOrganizations().size(); i++) {
				StudyOrganization studyOrganization = source.getStudyOrganizations().get(i);
				if (studyOrganization instanceof StudySite) {
					StudySite studySite = (StudySite) studyOrganization;
					Organization organization = getOrganization(studySite.getOrganization().getName());
					studySite.setOrganization(organization);
					// Migrate Study investigators and Study Personnels
					migrateStudyInvestigators(studySite,organization);
					migrateStudyPersonnels(studySite,organization);
					
					destination.addStudySite(studySite);	
				} 
				if (studyOrganization instanceof StudyFundingSponsor) {
					StudyFundingSponsor studyFundingSponsor = (StudyFundingSponsor) studyOrganization;
					Organization organization = getOrganization(studyFundingSponsor.getOrganization().getName());
					studyFundingSponsor.setOrganization(organization);
					//	Migrate Study investigators and Study Personnels
					migrateStudyInvestigators(studyFundingSponsor,organization);
					migrateStudyPersonnels(studyFundingSponsor,organization);
					
					destination.addStudyFundingSponsor(studyFundingSponsor);	
				} 
				
			}
		}
		
	}
	
	private void migrateStudyInvestigators(StudyOrganization studyOrganization, Organization organization){
		
		for ( StudyInvestigator studyInvestigator: studyOrganization.getStudyInvestigators()){
			Investigator investigator = studyInvestigator.getSiteInvestigator().getInvestigator();
			// TODO  : search should be done on something else too 
			String[] investigatorFirstAndLast = {investigator.getFirstName(), investigator.getLastName() };
			List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getBySubnames(investigatorFirstAndLast, organization.getId());
			if (siteInvestigators.size() > 0 ) {
				studyInvestigator.setSiteInvestigator(siteInvestigators.get(0));
				studyInvestigator.setStudyOrganization(studyOrganization);	
			}else{
				studyOrganization.getStudyInvestigators().remove(studyInvestigator);
			}
		}
	}
	

	private void migrateStudyPersonnels(StudyOrganization studyOrganization,
			Organization organization ) {

		for (StudyPersonnel studyPersonnel : studyOrganization.getStudyPersonnels()) {
			ResearchStaff researchStaffer = studyPersonnel.getResearchStaff();
			// TODO : search should be done on something else too
			String[] investigatorFirstAndLast = {researchStaffer.getFirstName(),researchStaffer.getLastName() };
			ResearchStaff researchStaff = researchStaffDao.getBySubnames(
					investigatorFirstAndLast, organization.getId()).get(0);
			studyPersonnel.setResearchStaff(researchStaff);
			studyPersonnel.setStudyOrganization(studyOrganization);
		}

	}
	
	
	
	/*
	 * This is used in study
	 */
	private void migrateOrganizationAssignedIdentifier(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

		if (source.getMultiInstitutionIndicator().booleanValue() )
		{
			String organizationName = source.getOrganizationAssignedIdentifier().getOrganization().getName();
			Organization organization = getOrganization(organizationName);
			
			destination.getOrganizationAssignedIdentifier().setOrganization(organization);
			destination.getOrganizationAssignedIdentifier().setValue(source.getOrganizationAssignedIdentifier().getValue());
			// On UI this is being done in Controller processFinish
			destination.addIdentifier(destination.getOrganizationAssignedIdentifier());
		}
		
	}
	
	private StudyAgent createStudyAgent(Agent agent){
		
		StudyAgent studyAgent = new StudyAgent();
		studyAgent.setAgent(agent);
		return studyAgent;
	}
	
	private void studyUniquenessCheck(Study study, DomainObjectImportOutcome studyImportOutcome, Severity severity){
		
		for (Identifier identifier : study.getIdentifiers()) 
		{	
			Study tempStudy = getStudyDao().getByIdentifier(identifier);
			if (tempStudy != null) {
				studyImportOutcome.addErrorMessage(study.getClass().getSimpleName() + " identifier already exists. ",severity);
				break;
			} 		
		}
	}

	public AgentDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}

	public CtcDao getCtcDao() {
		return ctcDao;
	}

	public void setCtcDao(CtcDao ctcDao) {
		this.ctcDao = ctcDao;
	}

	public MedDRADao getMeddraDao() {
		return meddraDao;
	}

	public void setMeddraDao(MedDRADao meddraDao) {
		this.meddraDao = meddraDao;
	}

	public SiteInvestigatorDao getSiteInvestigatorDao() {
		return siteInvestigatorDao;
	}

	public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
		this.siteInvestigatorDao = siteInvestigatorDao;
	}

	public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}

	public DiseaseTermDao getDiseaseTermDao() {
		return diseaseTermDao;
	}

	public void setDiseaseTermDao(DiseaseTermDao diseaseTermDao) {
		this.diseaseTermDao = diseaseTermDao;
	}

	public LowLevelTermDao getLowLevelTermDao() {
		return lowLevelTermDao;
	}

	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}

	public MeddraVersionDao getMeddraVersionDao() {
		return meddraVersionDao;
	}

	public void setMeddraVersionDao(MeddraVersionDao meddraVersionDao) {
		this.meddraVersionDao = meddraVersionDao;
	}
	
	
	
	
	
	
	
	
	
	
}