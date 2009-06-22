package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.CoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Design;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerminology;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAEMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.FundingSponsor;
import gov.nih.nci.cabig.caaers.domain.INDType;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.webservice.CtepStudyDiseaseType;
import gov.nih.nci.cabig.caaers.webservice.DesignCodeType;
import gov.nih.nci.cabig.caaers.webservice.DiseaseCodeType;
import gov.nih.nci.cabig.caaers.webservice.EvaluationPeriodType;
import gov.nih.nci.cabig.caaers.webservice.ExpectedAECtcTermType;
import gov.nih.nci.cabig.caaers.webservice.ExpectedAEMeddraTermType;
import gov.nih.nci.cabig.caaers.webservice.IndType;
import gov.nih.nci.cabig.caaers.webservice.InvestigationalNewDrugType;
import gov.nih.nci.cabig.caaers.webservice.MeddraStudyDiseaseType;
import gov.nih.nci.cabig.caaers.webservice.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.webservice.SiteInvestigatorType;
import gov.nih.nci.cabig.caaers.webservice.SolicitedAdverseEventType;
import gov.nih.nci.cabig.caaers.webservice.StudyAgentINDAssociationType;
import gov.nih.nci.cabig.caaers.webservice.StudyAgentType;
import gov.nih.nci.cabig.caaers.webservice.StudyInvestigatorType;
import gov.nih.nci.cabig.caaers.webservice.StudyPersonnelType;
import gov.nih.nci.cabig.caaers.webservice.StudySiteType;
import gov.nih.nci.cabig.caaers.webservice.TreatmentAssignmentType;
import gov.nih.nci.cabig.caaers.webservice.Study.CtepStudyDiseases;
import gov.nih.nci.cabig.caaers.webservice.Study.EvaluationPeriods;
import gov.nih.nci.cabig.caaers.webservice.Study.ExpectedAECtcTerms;
import gov.nih.nci.cabig.caaers.webservice.Study.ExpectedAEMeddraTerms;
import gov.nih.nci.cabig.caaers.webservice.Study.Identifiers;
import gov.nih.nci.cabig.caaers.webservice.Study.MeddraStudyDiseases;
import gov.nih.nci.cabig.caaers.webservice.Study.StudyAgents;
import gov.nih.nci.cabig.caaers.webservice.Study.StudyOrganizations;
import gov.nih.nci.cabig.caaers.webservice.Study.TreatmentAssignments;
import gov.nih.nci.cabig.caaers.webservice.StudyAgentType.StudyAgentINDAssociations;

import java.util.List;

/**
 * This class has one public method which Converts a JAXB generated Study Type object
 * to a Domain Object Study Type as required by StudyMigrator.
 * @author Monish Dombla
 * @author Biju Joseph (added start/end dates)
 *
 */
public class StudyConverter {
	
	/**
	 * This method accepts a studyDto which is a JAXB generated Study Object
	 * and a Study domain object. 
	 * It walks through the studyDto object and prepares a Study object 
	 * which is StudyMigrator Complaint.
	 * @param studyDto
	 * @param study 
	 */
	public void convertStudyDtoToStudyDomain(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws CaaersSystemException{
		assert study != null : "Domain study should not be null";
		
		try{
			//Populate Study Instance attributes
			study.setShortTitle(studyDto.getShortTitle());
			study.setLongTitle(studyDto.getLongTitle());
			study.setPrecis(studyDto.getPrecis());
			study.setDescription(studyDto.getDescription());
			study.setPhaseCode(studyDto.getPhaseCode().value());
			study.setStatus(studyDto.getStatus().value());
			study.setMultiInstitutionIndicator(studyDto.isMultiInstitutionIndicator());
			study.setAdeersReporting(studyDto.isAdeersReporting());
			study.setDrugAdministrationTherapyType(studyDto.isDrugAdministrationTherapyType());
			study.setDeviceTherapyType(studyDto.isDeviceTherapyType());
			study.setRadiationTherapyType(studyDto.isRadiationTherapyType());
			study.setSurgeryTherapyType(studyDto.isSurgeryTherapyType());
			study.setBehavioralTherapyType(studyDto.isBehavioralTherapyType());
			
			if(! "".equals(studyDto.getOtherMeddra()) && studyDto.getOtherMeddra() != null){
				MeddraVersion otherMeddra = null;
				otherMeddra = new MeddraVersion();
				otherMeddra.setName(studyDto.getOtherMeddra());
				study.setOtherMeddra(otherMeddra);
			}
			
			//Populate DesignCode
			populateDesignCode(studyDto, study);
			//populate AeTerminology
			populateAeTerminology(studyDto, study);
			//Populate DiseaseTerminology
			populateDiseaseTerminology(studyDto,study);
			//Populate Funding Sponsor
			populateFundingSponsor(studyDto, study);
			//Populate CoordinatingCenter
			populateCoordinatingCenter(studyDto, study);
			//Populate StudySites
			populateStudySites(studyDto, study);
			//Populate Identifiers
			populateIdentifiers(studyDto, study);
			//Populate TreatmentAssignments
			populateTreatmentAssignments(studyDto, study);
			//Populate StudyAgents
			populateStudyAgents(studyDto, study);
			//Populate StudyDiseases
			populateStudyDiseases(studyDto, study);
			//Populate EvaluationPeriods
			populateStudyEvaluationPeriods(studyDto, study);
			//populate Expected AE's
			populateStudyExpectedAEs(studyDto, study);
			
		}catch(Exception e){
			throw new CaaersSystemException("Exception while StudyDto Conversion",e);
		}
	}
	

	
	//Populate StudyInvestigators for a StudyOrganization
	private void populateStudyInvestigators(List<StudyInvestigatorType> studyInvestigatorList,StudyOrganization studyOrganization) throws Exception{
		
		if(studyInvestigatorList != null && !studyInvestigatorList.isEmpty()){
			StudyInvestigator studyInvestigator = null;
			SiteInvestigator siteInvestigator;
			Investigator investigator;
			for(StudyInvestigatorType studyInvestigatorType : studyInvestigatorList){
				studyInvestigator = new StudyInvestigator();
				studyInvestigator.setRoleCode(studyInvestigatorType.getRoleCode().value());
				studyInvestigator.setStartDate(studyInvestigatorType.getStartDate().toGregorianCalendar().getTime());
				if(studyInvestigatorType.getEndDate() != null){
					studyInvestigator.setEndDate(studyInvestigatorType.getEndDate().toGregorianCalendar().getTime());
				}
				SiteInvestigatorType siteInvestigatorType = studyInvestigatorType.getSiteInvestigator();
				siteInvestigator = new SiteInvestigator();
				investigator = new LocalInvestigator();
				investigator.setFirstName(siteInvestigatorType.getInvestigator().getFirstName());
				investigator.setLastName(siteInvestigatorType.getInvestigator().getLastName());
				investigator.setNciIdentifier(siteInvestigatorType.getInvestigator().getNciIdentifier());
				siteInvestigator.setInvestigator(investigator);
				studyInvestigator.setSiteInvestigator(siteInvestigator);
				studyOrganization.addStudyInvestigators(studyInvestigator);
			}
			
		}
	}
	
	//Populate StudyPersonnel for a StudyOrganization
	private void populateStudyPersonnel(List<StudyPersonnelType> studyPersonnelList,StudyOrganization studyOrganization) throws Exception{
		if(studyPersonnelList != null && !studyPersonnelList.isEmpty()){
			StudyPersonnel studyPersonnel;
			for(StudyPersonnelType studyPersonnelType : studyPersonnelList){
				studyPersonnel = new StudyPersonnel();
				studyPersonnel.setRoleCode(studyPersonnelType.getRoleCode().value());
				studyPersonnel.setStartDate(studyPersonnelType.getStartDate().toGregorianCalendar().getTime());
				if(studyPersonnelType.getEndDate() != null){
					studyPersonnel.setEndDate(studyPersonnelType.getEndDate().toGregorianCalendar().getTime());
				}
				ResearchStaff researchStaff = new LocalResearchStaff();
				researchStaff.setFirstName(studyPersonnelType.getResearchStaff().getFirstName());
				researchStaff.setLastName(studyPersonnelType.getResearchStaff().getLastName());
				researchStaff.setNciIdentifier(studyPersonnelType.getResearchStaff().getNciIdentifier());
				studyPersonnel.setResearchStaff(researchStaff);
				studyOrganization.addStudyPersonnel(studyPersonnel);
			}
		}
	}
	
	//Populate DesignCode
	private void populateDesignCode(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		DesignCodeType designCodeType = studyDto.getDesign();
		if(DesignCodeType.BLIND.equals(designCodeType)){
			study.setDesign(Design.BLIND);
		}
		if(DesignCodeType.OPEN_UNBLIND.equals(designCodeType)){
			study.setDesign(Design.OPEN_UNBLIND);
		}
		if(DesignCodeType.PARTIAL.equals(designCodeType)){
			study.setDesign(Design.PARTIAL);
		}
	}
	
	private void populateAeTerminology(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		if(studyDto.getAeTerminology() != null){
			AeTerminology aeTerminology = null;
			
			if(studyDto.getAeTerminology().getCtcVersion() != null){
				aeTerminology = new AeTerminology();
				Ctc ctcVersion = new Ctc();
				ctcVersion.setName(studyDto.getAeTerminology().getCtcVersion().getName());
				aeTerminology.setCtcVersion(ctcVersion);
				study.setAeTerminology(aeTerminology);
			}
			if (studyDto.getAeTerminology().getMeddraVersion() != null) {
				aeTerminology = new AeTerminology();
				MeddraVersion meddraVersion = new MeddraVersion();
				meddraVersion.setName(studyDto.getAeTerminology().getMeddraVersion().getName());
				aeTerminology.setMeddraVersion(meddraVersion);
            	study.setAeTerminology(aeTerminology);
            }
		}
		
	}
	
	private void populateDiseaseTerminology(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		if(studyDto.getDiseaseTerminology() != null){
			DiseaseTerminology diseaseTerminology = study.getDiseaseTerminology();
			
			if(DiseaseCodeType.CTEP.equals(studyDto.getDiseaseTerminology().getDiseaseCodeTerm())){
				diseaseTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
			}
			if(DiseaseCodeType.MEDDRA.equals(studyDto.getDiseaseTerminology().getDiseaseCodeTerm())){
				diseaseTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);
			}
		}
		
	}
	
	private void populateFundingSponsor(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		if(studyDto.getFundingSponsor() != null){
			FundingSponsor fundingSponsor = null;
			StudyFundingSponsor studyFundingSponsor = null;
			if(studyDto.getFundingSponsor().getOrganizationAssignedIdentifier() != null){
				fundingSponsor = new FundingSponsor();
				OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
				organizationAssignedIdentifier.setValue(studyDto.getFundingSponsor().getOrganizationAssignedIdentifier().getValue());
				fundingSponsor.setOrganizationAssignedIdentifier(organizationAssignedIdentifier);
			}
			if(studyDto.getFundingSponsor().getStudyFundingSponsor() != null){
				studyFundingSponsor = new StudyFundingSponsor();
				if(studyDto.getFundingSponsor().getStudyFundingSponsor().getOrganization() != null){
					Organization organization = new LocalOrganization();
					organization.setName(studyDto.getFundingSponsor().getStudyFundingSponsor().getOrganization().getName());
					organization.setNciInstituteCode(studyDto.getFundingSponsor().getStudyFundingSponsor().getOrganization().getNciInstituteCode());
					studyFundingSponsor.setOrganization(organization);
					fundingSponsor.setStudyFundingSponsor(studyFundingSponsor);
				}
			}
			
			if(studyDto.getFundingSponsor().getStudyFundingSponsor().getStudyInvestigators() != null){
				populateStudyInvestigators(studyDto.getFundingSponsor().getStudyFundingSponsor().getStudyInvestigators().getStudyInvestigator(),fundingSponsor.getStudyFundingSponsor());
			}
			if(studyDto.getFundingSponsor().getStudyFundingSponsor().getStudyPersonnels() != null){
				populateStudyPersonnel(studyDto.getFundingSponsor().getStudyFundingSponsor().getStudyPersonnels().getStudyPersonnel(),fundingSponsor.getStudyFundingSponsor());
			}
			study.setFundingSponsor(fundingSponsor);
		}
		
	}
	
	private void populateCoordinatingCenter(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		if(studyDto.getCoordinatingCenter() != null){
			CoordinatingCenter coordinatingCenter = null;
			StudyCoordinatingCenter studyCoordinatingCenter = null;
			if(studyDto.getCoordinatingCenter().getOrganizationAssignedIdentifier() != null){
				coordinatingCenter = new CoordinatingCenter();
				OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
				organizationAssignedIdentifier.setValue(studyDto.getCoordinatingCenter().getOrganizationAssignedIdentifier().getValue());
				coordinatingCenter.setOrganizationAssignedIdentifier(organizationAssignedIdentifier);
			}
			if(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter() != null){
				studyCoordinatingCenter = new StudyCoordinatingCenter();
				if(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization() != null){
					Organization organization = new LocalOrganization();
					organization.setName(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization().getName());
					organization.setNciInstituteCode(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization().getNciInstituteCode());
					studyCoordinatingCenter.setOrganization(organization);
					coordinatingCenter.setStudyCoordinatingCenter(studyCoordinatingCenter);
				}
				if(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter().getStudyInvestigators() != null){
					populateStudyInvestigators(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter().getStudyInvestigators().getStudyInvestigator(),coordinatingCenter.getStudyCoordinatingCenter());
				}
				if(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter().getStudyPersonnels() != null){
					populateStudyPersonnel(studyDto.getCoordinatingCenter().getStudyCoordinatingCenter().getStudyPersonnels().getStudyPersonnel(), coordinatingCenter.getStudyCoordinatingCenter());
				}
			}
			study.setCoordinatingCenter(coordinatingCenter);
		}
		
	}
	
	private void populateStudySites(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		StudyOrganizations studySites = studyDto.getStudyOrganizations();
		if(studySites != null){
			List<StudyOrganization> studyOrganizations = study.getStudyOrganizations();
			List<StudySiteType> studySiteList = studySites.getStudySite();
			if(studySiteList != null && !studySiteList.isEmpty()){
				StudySite studySite = null;
				for(StudySiteType studySiteType : studySiteList){
					studySite = new StudySite();
					studySite.setStartDate(studySiteType.getStartDate().toGregorianCalendar().getTime());
					if(studySiteType.getEndDate() != null){
						studySite.setEndDate(studySiteType.getEndDate().toGregorianCalendar().getTime());
					}
					if(studySiteType.getOrganization() != null){
						Organization organization = new LocalOrganization();
						organization.setName(studySiteType.getOrganization().getName());
						organization.setNciInstituteCode(studySiteType.getOrganization().getNciInstituteCode());
						studySite.setOrganization(organization);
					}
					if(studySiteType.getStudyInvestigators() != null){
						populateStudyInvestigators(studySiteType.getStudyInvestigators().getStudyInvestigator(),studySite);
					}
					if(studySiteType.getStudyPersonnels() != null){
						populateStudyPersonnel(studySiteType.getStudyPersonnels().getStudyPersonnel(), studySite);
					}
					studyOrganizations.add(studySite);
				}
				study.setStudyOrganizations(studyOrganizations);
			}
		}
		
	}
	
	private void populateIdentifiers(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		Identifiers identifiers = studyDto.getIdentifiers();
		if(identifiers != null){
			List<Identifier> identifierList = study.getIdentifiers();
			List<OrganizationAssignedIdentifierType> orgAssignedIdList = identifiers.getOrganizationAssignedIdentifier();
			if(orgAssignedIdList != null && !orgAssignedIdList.isEmpty()){
				OrganizationAssignedIdentifier orgIdentifier;
				Organization organization = new LocalOrganization();
				for(OrganizationAssignedIdentifierType organizationAssignedIdentifierType : orgAssignedIdList){
					orgIdentifier = new OrganizationAssignedIdentifier();
					orgIdentifier.setType(organizationAssignedIdentifierType.getType().value());
					orgIdentifier.setValue(organizationAssignedIdentifierType.getValue());
					orgIdentifier.setPrimaryIndicator(organizationAssignedIdentifierType.isPrimaryIndicator());
					organization.setName(organizationAssignedIdentifierType.getOrganization().getName());
					orgIdentifier.setOrganization(organization);
					identifierList.add(orgIdentifier);
				}
				study.setIdentifiers(identifierList);
			}
		}
		
	}
	
	private void populateTreatmentAssignments(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		TreatmentAssignments treatmentAssignments = studyDto.getTreatmentAssignments();
		if(treatmentAssignments!=null){
			List<TreatmentAssignmentType> treatmentAssignmentsTypeList = treatmentAssignments.getTreatmentAssignment();
			List<TreatmentAssignment> treatmentAssignmentList = study.getTreatmentAssignments();
			if(treatmentAssignmentsTypeList != null && !treatmentAssignmentsTypeList.isEmpty()){
				TreatmentAssignment treatmentAssignment = null;
				for(TreatmentAssignmentType treatmentAssignmentType : treatmentAssignmentsTypeList){
					treatmentAssignment = new TreatmentAssignment();
					treatmentAssignment.setCode(treatmentAssignmentType.getCode());
					if(treatmentAssignmentType.getDoseLevelOrder() != null){
						treatmentAssignment.setDoseLevelOrder(Integer.parseInt(treatmentAssignmentType.getDoseLevelOrder()));
					}
					treatmentAssignment.setDescription(treatmentAssignmentType.getDescription());
					treatmentAssignment.setComments(treatmentAssignmentType.getComments());
					treatmentAssignmentList.add(treatmentAssignment);
				}
				study.setTreatmentAssignments(treatmentAssignmentList);
			}
		}
		
	}
	
	private void populateStudyAgents(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		StudyAgents studyAgents = studyDto.getStudyAgents();
		if(studyAgents != null){
			List<StudyAgentType> studyAgentTypeList = studyAgents.getStudyAgent();
			List<StudyAgent> studyAgentList = study.getStudyAgents();
			if(studyAgentTypeList != null && !studyAgentTypeList.isEmpty()){
				StudyAgent studyAgent = null;
				Agent agent = null;
				for(StudyAgentType studyAgentType : studyAgentTypeList){
					studyAgent = new StudyAgent();
					agent = new Agent();
					studyAgent.setAgent(agent);
					if(studyAgentType.getOtherAgent() != null){
						studyAgent.setOtherAgent(studyAgentType.getOtherAgent());
					}else{
						
						if(studyAgentType.getAgent().getNscNumber() != null){
							
							studyAgent.getAgent().setNscNumber(studyAgentType.getAgent().getNscNumber());
						}
						
						if(studyAgentType.getAgent().getName() != null){
							studyAgent.getAgent().setName(studyAgentType.getAgent().getName());
						}
						
					}
					
					if(IndType.CTEP_IND.equals(studyAgentType.getIndType())){
						studyAgent.setIndType(INDType.CTEP_IND);
					}
					if(IndType.DCP_IND.equals(studyAgentType.getIndType())){
						studyAgent.setIndType(INDType.DCP_IND);	
					}
					if(IndType.IND_EXEMPT.equals(studyAgentType.getIndType())){
						studyAgent.setIndType(INDType.IND_EXEMPT);
					}
					if(IndType.NA.equals(studyAgentType.getIndType())){
						studyAgent.setIndType(INDType.NA);
					}
					if(IndType.NA_COMMERCIAL.equals(studyAgentType.getIndType())){
						studyAgent.setIndType(INDType.NA_COMMERCIAL);
					}
					if(IndType.OTHER.equals(studyAgentType.getIndType())){
						studyAgent.setIndType(INDType.OTHER);
					}
					studyAgent.setPartOfLeadIND(studyAgentType.isPartOfLeadIND());
					
					StudyAgentINDAssociation studyAgentINDAssociation = new StudyAgentINDAssociation();
					InvestigationalNewDrug investigationalNewDrug = new InvestigationalNewDrug();
					StudyAgentINDAssociations studyAgentINDAssociations = studyAgentType.getStudyAgentINDAssociations();
					if(studyAgentINDAssociations != null){
						StudyAgentINDAssociationType studyAgentINDAssociationType = studyAgentINDAssociations.getStudyAgentINDAssociation();
						if(studyAgentINDAssociationType != null){
							InvestigationalNewDrugType investigationalNewDrugType = studyAgentINDAssociationType.getInvestigationalNewDrug();
							if(investigationalNewDrugType != null){
								investigationalNewDrug.setIndNumber(investigationalNewDrugType.getIndNumber().intValue());
								studyAgentINDAssociation.setInvestigationalNewDrug(investigationalNewDrug);
								studyAgent.getStudyAgentINDAssociations().add(studyAgentINDAssociation);
							}
						}
					}
					studyAgentList.add(studyAgent);
				}
				study.setStudyAgents(studyAgentList);
			}
		}
		
	}
	
	private void populateStudyDiseases(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		
		CtepStudyDiseases ctepStudyDiseases = studyDto.getCtepStudyDiseases();
		if(ctepStudyDiseases != null){
			List<CtepStudyDiseaseType> ctepStudyDiseaseTypeList = ctepStudyDiseases.getCtepStudyDisease();
			List<CtepStudyDisease> ctepStudyDiseaseList = study.getCtepStudyDiseases();
			if(ctepStudyDiseaseTypeList != null && !ctepStudyDiseaseTypeList.isEmpty()){
				CtepStudyDisease ctepStudyDisease = null;
				DiseaseTerm diseaseTerm = null;
				for(CtepStudyDiseaseType ctepStudyDiseaseType : ctepStudyDiseaseTypeList){
					ctepStudyDisease = new CtepStudyDisease();
					diseaseTerm = new DiseaseTerm();
					ctepStudyDisease.setDiseaseTerm(diseaseTerm);
					ctepStudyDisease.setLeadDisease(ctepStudyDiseaseType.isLeadDisease());
					if(ctepStudyDiseaseType.getDiseaseTerm().getTerm() != null){
						ctepStudyDisease.getDiseaseTerm().setTerm(ctepStudyDiseaseType.getDiseaseTerm().getTerm());
					}else{
						if(ctepStudyDiseaseType.getDiseaseTerm().getMeddraCode() != null){
							ctepStudyDisease.getDiseaseTerm().setMeddraCode(ctepStudyDiseaseType.getDiseaseTerm().getMeddraCode());
						}
					}
					ctepStudyDiseaseList.add(ctepStudyDisease);
				}
				study.setCtepStudyDiseases(ctepStudyDiseaseList);
			}
		}else{
			MeddraStudyDiseases meddraStudyDiseases = studyDto.getMeddraStudyDiseases();
			if(meddraStudyDiseases != null){
				List<MeddraStudyDiseaseType> meddraStudyDiseaseTypeList = meddraStudyDiseases.getMeddraStudyDisease();
				List<MeddraStudyDisease> meddraStudyDiseaseList = study.getMeddraStudyDiseases();
				if(meddraStudyDiseaseTypeList != null && !meddraStudyDiseaseTypeList.isEmpty()){
					MeddraStudyDisease meddraStudyDisease = null;
					for(MeddraStudyDiseaseType meddraStudyDiseaseType : meddraStudyDiseaseTypeList){
						meddraStudyDisease = new MeddraStudyDisease();
						meddraStudyDisease.setMeddraCode(meddraStudyDiseaseType.getMeddraCode());
						meddraStudyDiseaseList.add(meddraStudyDisease);
					}
				}
				study.setMeddraStudyDiseases(meddraStudyDiseaseList);
			}
		}
	}
	
	private void populateStudyEvaluationPeriods(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		EvaluationPeriods evaluationPeriods = studyDto.getEvaluationPeriods();
		if(evaluationPeriods != null){
			List<EvaluationPeriodType> evalutionPeriodTypeList = evaluationPeriods.getEvaluationPeriod();
			if(evalutionPeriodTypeList != null && !evalutionPeriodTypeList.isEmpty()){
				Epoch epoch = null;
				for(EvaluationPeriodType evaluationPeriodType : evalutionPeriodTypeList){
					epoch = new Epoch();
					epoch.setName(evaluationPeriodType.getName());
					epoch.setDescriptionText(evaluationPeriodType.getDescriptionText());
					epoch.setEpochOrder(evaluationPeriodType.getEpochOrder());
					Arm arm = new Arm();
					arm.setName(epoch.getName());
					arm.setDescriptionText(epoch.getDescriptionText());
					if(evaluationPeriodType.getSolicitedAdverseEvents() != null){
						List<SolicitedAdverseEventType> solicitedAdverseEventTypeList = evaluationPeriodType.getSolicitedAdverseEvents().getSolicitedAdverseEvent();
						if(solicitedAdverseEventTypeList != null & !solicitedAdverseEventTypeList.isEmpty()){
							SolicitedAdverseEvent solicitedAdverseEvent = null;
							for(SolicitedAdverseEventType solicitedAdverseEventType : solicitedAdverseEventTypeList){
								solicitedAdverseEvent = new SolicitedAdverseEvent();
								if(solicitedAdverseEventType.getCtepCode() != null && !"".equals(solicitedAdverseEventType.getCtepCode())){
									CtcTerm ctcTerm = new CtcTerm();
									ctcTerm.setCtepCode(solicitedAdverseEventType.getCtepCode());
									solicitedAdverseEvent.setCtcterm(ctcTerm);
								}
								if(solicitedAdverseEventType.getMeddraCode() != null && !"".equals(solicitedAdverseEventType.getMeddraCode()) ){
									LowLevelTerm meddraTerm = new LowLevelTerm();
									meddraTerm.setMeddraCode(solicitedAdverseEventType.getMeddraCode());
									solicitedAdverseEvent.setLowLevelTerm(meddraTerm);
								}
								if(solicitedAdverseEventType.getOtherMeddraCode() != null && !"".equals(solicitedAdverseEventType.getOtherMeddraCode())){
									LowLevelTerm otherTerm = new LowLevelTerm();
									otherTerm.setMeddraCode(solicitedAdverseEventType.getOtherMeddraCode());
									solicitedAdverseEvent.setOtherTerm(otherTerm);
								}
								arm.getSolicitedAdverseEvents().add(solicitedAdverseEvent);
							}
						}
					}
					epoch.getArms().add(arm);
					study.getEpochs().add(epoch);
				}
			}
		}
	}
	
	
	private void populateStudyExpectedAEs(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
		ExpectedAECtcTerms expectedAECtcTerms = studyDto.getExpectedAECtcTerms();
		if(expectedAECtcTerms != null){
			ExpectedAECtcTerm expectedAECtcTerm = null;
			CtcTerm ctcTerm = null;
			LowLevelTerm otherMeddraTerm = null;
			for(ExpectedAECtcTermType expectedAECtcTermType : expectedAECtcTerms.getExpectedAECtcTerm()){
				if(expectedAECtcTermType.getCtepCode() != null && !"".equals(expectedAECtcTermType.getCtepCode())){
					expectedAECtcTerm = new ExpectedAECtcTerm();
					ctcTerm = new CtcTerm();
					ctcTerm.setCtepCode(expectedAECtcTermType.getCtepCode());
					expectedAECtcTerm.setCtcTerm(ctcTerm);
				}
				if(expectedAECtcTermType.getOtherMeddraCode() != null && !"".equals(expectedAECtcTermType.getOtherMeddraCode())){
					if(expectedAECtcTerm == null){
						expectedAECtcTerm = new ExpectedAECtcTerm();
					}
					otherMeddraTerm = new LowLevelTerm();
					otherMeddraTerm.setMeddraCode(expectedAECtcTermType.getOtherMeddraCode());
					expectedAECtcTerm.setOtherMeddraTerm(otherMeddraTerm);
				}
				if(expectedAECtcTerm != null){
					study.getExpectedAECtcTerms().add(expectedAECtcTerm);
				}
			}
		}else{
			ExpectedAEMeddraTerms expectedAEMeddraTerms = studyDto.getExpectedAEMeddraTerms();
			if(expectedAEMeddraTerms != null){
				ExpectedAEMeddraLowLevelTerm expectedAEMeddraLowLevelTerm = null;
				LowLevelTerm meddraTerm = null;
				for(ExpectedAEMeddraTermType expectedAEMeddraTermType : expectedAEMeddraTerms.getExpectedAEMeddraTerm()){
					if(expectedAEMeddraTermType.getMeddraCode() != null && !"".equals(expectedAEMeddraTermType.getMeddraCode())){
						expectedAEMeddraLowLevelTerm = new ExpectedAEMeddraLowLevelTerm();
						meddraTerm = new LowLevelTerm();
						meddraTerm.setMeddraCode(expectedAEMeddraTermType.getMeddraCode());
						expectedAEMeddraLowLevelTerm.setLowLevelTerm(meddraTerm);
						study.getExpectedAEMeddraLowLevelTerms().add(expectedAEMeddraLowLevelTerm);
					}
				}
			}
		}
	}
}
