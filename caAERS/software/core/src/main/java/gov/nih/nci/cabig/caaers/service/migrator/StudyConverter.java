package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.Address;
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
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
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
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.webservice.*;
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

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import java.util.*;
import java.math.BigInteger;

/**
 * This class has one public method which Converts a JAXB generated Study Type object
 * to a Domain Object Study Type as required by StudyMigrator.
 * @author Monish Dombla
 * @author Biju Joseph (added start/end dates)
 *
 */
public class StudyConverter {


    public gov.nih.nci.cabig.caaers.webservice.Studies convertStudyDomainToStudyDto(Study study) throws Exception {
        ObjectFactory objectFactory = new ObjectFactory();
        gov.nih.nci.cabig.caaers.webservice.Study studyDto = objectFactory.createStudy();

//        try {
            studyDto.setShortTitle(study.getShortTitle());
            studyDto.setLongTitle(study.getLongTitle());
            studyDto.setPrecis(study.getPrecis());
            studyDto.setDescription(study.getDescription());
            studyDto.setPhaseCode(StudyPhaseType.fromValue(study.getPhaseCode()));
            studyDto.setStatus(StatusType.fromValue(study.getStatus()));
            studyDto.setMultiInstitutionIndicator(study.getMultiInstitutionIndicator());
            studyDto.setAdeersReporting(study.getAdeersReporting());
            studyDto.setDrugAdministrationTherapyType(study.getDrugAdministrationTherapyType());
            studyDto.setDeviceTherapyType(study.getDeviceTherapyType());
            studyDto.setRadiationTherapyType(study.getRadiationTherapyType());
            studyDto.setSurgeryTherapyType(study.getSurgeryTherapyType());
            studyDto.setBehavioralTherapyType(study.getBehavioralTherapyType());

            if(study.getOtherMeddra() != null && !study.getOtherMeddra().equals("")){
                MeddraVersion otherMeddra = new MeddraVersion();
                otherMeddra.setName(study.getOtherMeddra().getName());
                studyDto.setOtherMeddra(otherMeddra.getName());
            }
            
            populateDesignCodeDomain2Dto(studyDto, study);
            populateAeTerminologyDomain2Dto(studyDto, study);
            populateDiseaseTerminologyDomain2Dto(studyDto,study);
            populateFundingSponsorDomain2Dto(studyDto, study);
            populateCoordinatingCenterDomain2Dto(studyDto, study);
            populateStudySitesDomain2Dto(studyDto, study);

            populateIdentifiersDomain2Dto(studyDto, study);
            populateTreatmentAssignmentsDomain2Dto(studyDto, study);
            populateStudyAgentsDomain2Dto(studyDto, study);
            populateStudyDiseasesDomain2Dto(studyDto, study);

            populateStudyEvaluationPeriodsDomain2Dto(studyDto, study);
            populateStudyExpectedAEsDomain2Dto(studyDto, study);

/*
*/

/*
        } catch (Exception e) {
            e.printStackTrace();
            throw new CaaersSystemException("Exception while StudyDto Conversion", e);
        }
*/


        gov.nih.nci.cabig.caaers.webservice.Studies studies = objectFactory.createStudies();
        studies.getStudy().add(studyDto);

        return studies;
    }

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

    private XMLGregorianCalendar convertCalendar2XmlGregorianCalendar(Calendar c) throws Exception {
        XMLGregorianCalendar xml =  DatatypeFactory.newInstance().newXMLGregorianCalendar();
        xml.setYear(c.get(Calendar.YEAR));
        xml.setMonth(c.get(Calendar.MONTH));
        xml.setDay(c.get(Calendar.DAY_OF_MONTH));
        return xml;
    }

	// Populate StudyInvestigators for a StudyOrganization
	private void populateStudyInvestigatorsDomain2Dto(List<StudyInvestigator> studyInvestigators, StudyFundingSponsorType sf) throws Exception{

		if(studyInvestigators != null && !studyInvestigators.isEmpty()) {

            sf.setStudyInvestigators(new StudyFundingSponsorType.StudyInvestigators());
            sf.getStudyInvestigators().setStudyInvestigator(new ArrayList<StudyInvestigatorType>());

			StudyInvestigatorType siType = null;

            for (StudyInvestigator si : studyInvestigators) {
                siType = new StudyInvestigatorType();
                siType.setRoleCode(RoleCodeType.fromValue(si.getRoleCode()));

                Calendar c = Calendar.getInstance();
                if (si.getStartDate() != null) {
                    c.setTime(si.getStartDate());
                    siType.setStartDate(convertCalendar2XmlGregorianCalendar(c));
                }

                if (si.getEndDate() != null) {
                    c.setTime(si.getEndDate());
                    siType.setEndDate(convertCalendar2XmlGregorianCalendar(c));
                }

                siType.setSiteInvestigator(new SiteInvestigatorType());
                siType.getSiteInvestigator().setInvestigator(new InvestigatorType());
                siType.getSiteInvestigator().getInvestigator().setFirstName(si.getSiteInvestigator().getInvestigator().getFirstName());
                siType.getSiteInvestigator().getInvestigator().setLastName(si.getSiteInvestigator().getInvestigator().getLastName());
                siType.getSiteInvestigator().getInvestigator().setNciIdentifier(si.getSiteInvestigator().getInvestigator().getNciIdentifier());

                sf.getStudyInvestigators().getStudyInvestigator().add(siType);
            }

        }
	}

	private void populateStudyInvestigatorsDomain2DtoForCoordinatingSite(List<StudyInvestigator> studyInvestigators, StudyCoordinatingCenterType sc) throws Exception{

		if(studyInvestigators != null && !studyInvestigators.isEmpty()) {

            sc.setStudyInvestigators(new StudyCoordinatingCenterType.StudyInvestigators());
            sc.getStudyInvestigators().setStudyInvestigator(new ArrayList<StudyInvestigatorType>());

			StudyInvestigatorType siType = null;

            for (StudyInvestigator si : studyInvestigators) {
                siType = new StudyInvestigatorType();
                siType.setRoleCode(RoleCodeType.fromValue(si.getRoleCode()));

                Calendar c = Calendar.getInstance();
                if (si.getStartDate() != null) {
                    c.setTime(si.getStartDate());
                    siType.setStartDate(convertCalendar2XmlGregorianCalendar(c));
                }

                if (si.getEndDate() != null) {
                    c.setTime(si.getEndDate());
                    siType.setEndDate(convertCalendar2XmlGregorianCalendar(c));
                }

                siType.setSiteInvestigator(new SiteInvestigatorType());
                siType.getSiteInvestigator().setInvestigator(new InvestigatorType());
                siType.getSiteInvestigator().getInvestigator().setFirstName(si.getSiteInvestigator().getInvestigator().getFirstName());
                siType.getSiteInvestigator().getInvestigator().setLastName(si.getSiteInvestigator().getInvestigator().getLastName());
                siType.getSiteInvestigator().getInvestigator().setNciIdentifier(si.getSiteInvestigator().getInvestigator().getNciIdentifier());

                sc.getStudyInvestigators().getStudyInvestigator().add(siType);
            }

        }
	}

	private void populateStudyInvestigatorsDomain2DtoForStudySite(List<StudyInvestigator> studyInvestigators, StudySiteType sst) throws Exception{

		if(studyInvestigators != null && !studyInvestigators.isEmpty()) {

            sst.setStudyInvestigators(new StudySiteType.StudyInvestigators());
            sst.getStudyInvestigators().setStudyInvestigator(new ArrayList<StudyInvestigatorType>());

			StudyInvestigatorType siType = null;

            for (StudyInvestigator si : studyInvestigators) {
                siType = new StudyInvestigatorType();
                siType.setRoleCode(RoleCodeType.fromValue(si.getRoleCode()));

                Calendar c = Calendar.getInstance();
                if (si.getStartDate() != null) {
                    c.setTime(si.getStartDate());
                    siType.setStartDate(convertCalendar2XmlGregorianCalendar(c));
                }

                if (si.getEndDate() != null) {
                    c.setTime(si.getEndDate());
                    siType.setEndDate(convertCalendar2XmlGregorianCalendar(c));
                }

                siType.setSiteInvestigator(new SiteInvestigatorType());
                siType.getSiteInvestigator().setInvestigator(new InvestigatorType());
                siType.getSiteInvestigator().getInvestigator().setFirstName(si.getSiteInvestigator().getInvestigator().getFirstName());
                siType.getSiteInvestigator().getInvestigator().setLastName(si.getSiteInvestigator().getInvestigator().getLastName());
                siType.getSiteInvestigator().getInvestigator().setNciIdentifier(si.getSiteInvestigator().getInvestigator().getNciIdentifier());

                sst.getStudyInvestigators().getStudyInvestigator().add(siType);
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
				researchStaff.setAddress(new Address());
				SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
				researchStaff.setFirstName(studyPersonnelType.getResearchStaff().getFirstName());
				researchStaff.setLastName(studyPersonnelType.getResearchStaff().getLastName());
				researchStaff.setNciIdentifier(studyPersonnelType.getResearchStaff().getNciIdentifier());
				siteResearchStaff.setResearchStaff(researchStaff);
				siteResearchStaff.setOrganization(studyOrganization.getOrganization());
				studyPersonnel.setSiteResearchStaff(siteResearchStaff);
				studyOrganization.addStudyPersonnel(studyPersonnel);
			}
		}
	}
	
	private void populateStudyPersonnelDomain2Dto(List<StudyPersonnel> studyPersonnelList, StudyFundingSponsorType sf) throws Exception{
        if (studyPersonnelList != null && !studyPersonnelList.isEmpty()) {

            sf.setStudyPersonnels(new StudyFundingSponsorType.StudyPersonnels());
            sf.getStudyPersonnels().setStudyPersonnel((new ArrayList<StudyPersonnelType>()));

            StudyPersonnelType p;
            
            for (StudyPersonnel personnel : studyPersonnelList) {
                p = new StudyPersonnelType();
                p.setRoleCode(PersonnelRoleCodeType.fromValue(personnel.getRoleCode()));

                Calendar c = Calendar.getInstance();
                if (personnel.getStartDate() != null) {
                    c.setTime(personnel.getStartDate());
                    p.setStartDate(convertCalendar2XmlGregorianCalendar(c));
                }

                if (personnel.getEndDate() != null) {
                    c.setTime(personnel.getEndDate());
                    p.setEndDate(convertCalendar2XmlGregorianCalendar(c));
                }

                p.setResearchStaff(new ResearchStaffType());
                p.getResearchStaff().setFirstName(personnel.getSiteResearchStaff().getResearchStaff().getFirstName());
                p.getResearchStaff().setLastName(personnel.getSiteResearchStaff().getResearchStaff().getLastName());
                p.getResearchStaff().setNciIdentifier(personnel.getSiteResearchStaff().getResearchStaff().getNciIdentifier());

                sf.getStudyPersonnels().getStudyPersonnel().add(p);
            }
        }
    }

	private void populateStudyPersonnelDomain2DtoForCoordinatingSite(List<StudyPersonnel> studyPersonnelList, StudyCoordinatingCenterType sc) throws Exception{
        if (studyPersonnelList != null && !studyPersonnelList.isEmpty()) {

            sc.setStudyPersonnels(new StudyCoordinatingCenterType.StudyPersonnels());
            sc.getStudyPersonnels().setStudyPersonnel((new ArrayList<StudyPersonnelType>()));

            StudyPersonnelType p;

            for (StudyPersonnel personnel : studyPersonnelList) {
                p = new StudyPersonnelType();
                p.setRoleCode(PersonnelRoleCodeType.fromValue(personnel.getRoleCode()));

                Calendar c = Calendar.getInstance();
                if (personnel.getStartDate() != null) {
                    c.setTime(personnel.getStartDate());
                    p.setStartDate(convertCalendar2XmlGregorianCalendar(c));
                }

                if (personnel.getEndDate() != null) {
                    c.setTime(personnel.getEndDate());
                    p.setEndDate(convertCalendar2XmlGregorianCalendar(c));
                }

                p.setResearchStaff(new ResearchStaffType());
                p.getResearchStaff().setFirstName(personnel.getSiteResearchStaff().getResearchStaff().getFirstName());
                p.getResearchStaff().setLastName(personnel.getSiteResearchStaff().getResearchStaff().getLastName());
                p.getResearchStaff().setNciIdentifier(personnel.getSiteResearchStaff().getResearchStaff().getNciIdentifier());

                sc.getStudyPersonnels().getStudyPersonnel().add(p);
            }
        }
    }

	private void populateStudyPersonnelDomain2DtoForStudySite(List<StudyPersonnel> studyPersonnelList, StudySiteType sst) throws Exception{
        if (studyPersonnelList != null && !studyPersonnelList.isEmpty()) {

            sst.setStudyPersonnels(new StudySiteType.StudyPersonnels());
            sst.getStudyPersonnels().setStudyPersonnel((new ArrayList<StudyPersonnelType>()));

            StudyPersonnelType p;

            for (StudyPersonnel personnel : studyPersonnelList) {
                p = new StudyPersonnelType();
                p.setRoleCode(PersonnelRoleCodeType.fromValue(personnel.getRoleCode()));

                Calendar c = Calendar.getInstance();
                if (personnel.getStartDate() != null) {
                    c.setTime(personnel.getStartDate());
                    p.setStartDate(convertCalendar2XmlGregorianCalendar(c));
                }

                if (personnel.getEndDate() != null) {
                    c.setTime(personnel.getEndDate());
                    p.setEndDate(convertCalendar2XmlGregorianCalendar(c));
                }

                p.setResearchStaff(new ResearchStaffType());
                p.getResearchStaff().setFirstName(personnel.getSiteResearchStaff().getResearchStaff().getFirstName());
                p.getResearchStaff().setLastName(personnel.getSiteResearchStaff().getResearchStaff().getLastName());
                p.getResearchStaff().setNciIdentifier(personnel.getSiteResearchStaff().getResearchStaff().getNciIdentifier());

                sst.getStudyPersonnels().getStudyPersonnel().add(p);
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
	
	// Populate DesignCode
	private void populateDesignCodeDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{
        if (study.getDesign() == null) return;
        Design d = study.getDesign();
        studyDto.setDesign(DesignCodeType.fromValue(d.name()));
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
	
	private void populateAeTerminologyDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{

        if (study.getAeTerminology() != null) {
            AeTerminologyType aeTerminology = new AeTerminologyType();

            if (study.getAeTerminology().getCtcVersion() != null) {
                CtcVersionType ctc = new CtcVersionType();
                ctc.setName(study.getAeTerminology().getCtcVersion().getId().toString());
                aeTerminology.setCtcVersion(ctc);

            }
            
            if (study.getAeTerminology().getMeddraVersion() != null) {
                MeddraVersionType meddra = new MeddraVersionType();
                meddra.setName(study.getAeTerminology().getMeddraVersion().getId().toString());
                aeTerminology.setMeddraVersion(meddra);
            }

            studyDto.setAeTerminology(aeTerminology);
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
	
	private void populateDiseaseTerminologyDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{

		if(study.getDiseaseTerminology() != null){
			DiseaseTerminologyType  dt = new DiseaseTerminologyType();

			if(study.getDiseaseTerminology().getDiseaseCodeTerm().equals(DiseaseCodeTerm.CTEP)) {
                dt.setDiseaseCodeTerm(DiseaseCodeType.CTEP);
			}

			if(study.getDiseaseTerminology().getDiseaseCodeTerm().equals(DiseaseCodeTerm.MEDDRA)) {
                dt.setDiseaseCodeTerm(DiseaseCodeType.MEDDRA);
			}

			if(study.getDiseaseTerminology().getDiseaseCodeTerm().equals(DiseaseCodeTerm.OTHER)) {
                dt.setDiseaseCodeTerm(DiseaseCodeType.OTHER);
			}

            studyDto.setDiseaseTerminology(dt);
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
	
	private void populateFundingSponsorDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception {

        if (study.getFundingSponsor() != null) {
            FundingSponsorType fs = new FundingSponsorType();
            StudyFundingSponsorType sfs = null;

            if (study.getFundingSponsor().getOrganizationAssignedIdentifier() != null) {
                ReducedIdentifierType identifier = new ReducedIdentifierType();
                identifier.setValue(study.getFundingSponsor().getOrganizationAssignedIdentifier().getValue());
                fs.setOrganizationAssignedIdentifier(identifier);
            }

            if (study.getFundingSponsor().getStudyFundingSponsor() != null) {
                sfs = new StudyFundingSponsorType();
                if (study.getFundingSponsor().getStudyFundingSponsor().getOrganization() != null) {
                    OrganizationType organization = new OrganizationType();
                    organization.setName(study.getFundingSponsor().getStudyFundingSponsor().getOrganization().getName());
                    organization.setNciInstituteCode(study.getFundingSponsor().getStudyFundingSponsor().getOrganization().getNciInstituteCode());
                    sfs.setOrganization(organization);
                    fs.setStudyFundingSponsor(sfs);
                }
            }

            if (study.getFundingSponsor().getStudyFundingSponsor().getStudyInvestigators() != null) {
                populateStudyInvestigatorsDomain2Dto(study.getFundingSponsor().getStudyFundingSponsor().getStudyInvestigators(), fs.getStudyFundingSponsor());
            }
            
            if (study.getFundingSponsor().getStudyFundingSponsor().getStudyPersonnels() != null) {
                populateStudyPersonnelDomain2Dto(study.getFundingSponsor().getStudyFundingSponsor().getStudyPersonnels(), fs.getStudyFundingSponsor());
            }

            studyDto.setFundingSponsor(fs);
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
	
	private void populateCoordinatingCenterDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{

        if (study.getCoordinatingCenter() != null) {
            CoordinatingCenterType coordinatingCenterType = null;
            StudyCoordinatingCenterType studyCoordinatingCenterType = null;

            if (study.getCoordinatingCenter().getOrganizationAssignedIdentifier() != null) {
                coordinatingCenterType = new CoordinatingCenterType();
                ReducedIdentifierType identifier = new ReducedIdentifierType();
                identifier.setValue(study.getCoordinatingCenter().getOrganizationAssignedIdentifier().getValue());
                coordinatingCenterType.setOrganizationAssignedIdentifier(identifier);
            }

            if (study.getCoordinatingCenter().getStudyCoordinatingCenter() != null) {
                studyCoordinatingCenterType = new StudyCoordinatingCenterType();
                if (study.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization() != null) {
                    OrganizationType organization = new OrganizationType();
                    organization.setName(study.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization().getName());
                    organization.setNciInstituteCode(study.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization().getNciInstituteCode());
                    studyCoordinatingCenterType.setOrganization(organization);
                    coordinatingCenterType.setStudyCoordinatingCenter(studyCoordinatingCenterType);
                }

				if(study.getCoordinatingCenter().getStudyCoordinatingCenter().getStudyInvestigators() != null){
					populateStudyInvestigatorsDomain2DtoForCoordinatingSite(study.getCoordinatingCenter().getStudyCoordinatingCenter().getStudyInvestigators(), coordinatingCenterType.getStudyCoordinatingCenter());
				}
                
				if(study.getCoordinatingCenter().getStudyCoordinatingCenter().getStudyPersonnels() != null){
					populateStudyPersonnelDomain2DtoForCoordinatingSite(study.getCoordinatingCenter().getStudyCoordinatingCenter().getStudyPersonnels(), coordinatingCenterType.getStudyCoordinatingCenter());
				}
            }

            studyDto.setCoordinatingCenter(coordinatingCenterType);
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

                    if (studySiteType.getStartDate() != null) studySite.setStartDate(studySiteType.getStartDate().toGregorianCalendar().getTime());
                    if (studySiteType.getEndDate() != null) studySite.setEndDate(studySiteType.getEndDate().toGregorianCalendar().getTime());

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
	
	private void populateStudySitesDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception {

        List<StudySite> studySites = study.getStudySites();
            
        if (studySites != null && !studySites.isEmpty()) {
            StudyOrganizations studyOrganizations = new StudyOrganizations();
            studyOrganizations.setStudySite(new ArrayList<StudySiteType>());

            StudySiteType sst = null;

            for (StudySite ss : studySites) {

                sst = new StudySiteType();


                            Calendar c = Calendar.getInstance();
                            if (ss.getStartDate() != null) {
                                c.setTime(ss.getStartDate());
                                sst.setStartDate(convertCalendar2XmlGregorianCalendar(c));
                            }
                            if (ss.getEndDate() != null) {
                                c.setTime(ss.getEndDate());
                                sst.setEndDate(convertCalendar2XmlGregorianCalendar(c));
                            }

                            if (ss.getOrganization() != null) {
                                OrganizationType organizationType = new OrganizationType();
                                organizationType.setName(ss.getOrganization().getName());
                                organizationType.setNciInstituteCode(ss.getOrganization().getNciInstituteCode());
                                sst.setOrganization(organizationType);
                            }

                            if (ss.getStudyInvestigators() != null) {
                                populateStudyInvestigatorsDomain2DtoForStudySite(ss.getStudyInvestigators(), sst);
                            }

                            if (ss.getStudyPersonnels() != null) {
                                populateStudyPersonnelDomain2DtoForStudySite(ss.getStudyPersonnels(), sst);
                            }

                studyOrganizations.getStudySite().add(sst);
            }

            studyDto.setStudyOrganizations(studyOrganizations);
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
	
	private void populateIdentifiersDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{

		List<Identifier> ids = study.getIdentifiers();
        
		if(ids != null && ids.size() > 0){

            Identifiers identifiers = new Identifiers();
            identifiers.setOrganizationAssignedIdentifier(new ArrayList<OrganizationAssignedIdentifierType>());
            identifiers.setSystemAssignedIdentifier(new ArrayList<SystemAssignedIdentifierType>());

			List<OrganizationAssignedIdentifier> orgIds = study.getOrganizationAssignedIdentifiers();
			List<SystemAssignedIdentifier> sysIds = study.getSystemAssignedIdentifiers();

            if (orgIds != null) {
                for (OrganizationAssignedIdentifier oid : orgIds) {
                    OrganizationAssignedIdentifierType o = new OrganizationAssignedIdentifierType();
                    o.setOrganization(new OrganizationType());
                    o.getOrganization().setName(oid.getOrganization().getName());
                    o.getOrganization().setNciInstituteCode(oid.getOrganization().getNciInstituteCode());
                    o.setType(StudyIdentifierType.fromValue(oid.getType()));
                    o.setValue(oid.getValue());
                    o.setPrimaryIndicator(oid.getPrimaryIndicator());
                    identifiers.getOrganizationAssignedIdentifier().add(o);
                }
            }

            if (sysIds != null) {
                for (SystemAssignedIdentifier sid : sysIds) {
                    SystemAssignedIdentifierType o = new SystemAssignedIdentifierType();
                    o.setSystemName(sid.getSystemName());
                    o.setValue(sid.getValue());
                    identifiers.getSystemAssignedIdentifier().add(o);
                }
            }

            studyDto.setIdentifiers(identifiers);
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
	
	private void populateTreatmentAssignmentsDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{

		List<TreatmentAssignment> list = study.getTreatmentAssignments();

        if (list != null && list.size() > 0) {
                TreatmentAssignments ts = new TreatmentAssignments();
                List<TreatmentAssignmentType> treatmentAssignmentsTypeList = new ArrayList<TreatmentAssignmentType>();
                ts.setTreatmentAssignment(treatmentAssignmentsTypeList);

                TreatmentAssignment treatmentAssignment = null;

                for (TreatmentAssignment ta : list) {
                    TreatmentAssignmentType tat = new TreatmentAssignmentType();
                    tat.setCode(ta.getCode());
                    if (ta.getDoseLevelOrder() != null) tat.setDoseLevelOrder(ta.getDoseLevelOrder().toString());
                    tat.setDescription(ta.getDescription());
                    tat.setComments(ta.getComments());
                    treatmentAssignmentsTypeList.add(tat);
                }

                studyDto.setTreatmentAssignments(ts);
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

	private void populateStudyAgentsDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{

        List<StudyAgent> agents = study.getStudyAgents();

        if (agents != null && !agents.isEmpty()) {

            StudyAgents agentsDto = new StudyAgents();
            agentsDto.setStudyAgent(new ArrayList<StudyAgentType>());

            for (StudyAgent sa : agents) {

                if (sa == null && sa.getOtherAgent() == null && sa.getAgent() == null) continue;

                StudyAgentType sat = new StudyAgentType();

                if (sa.getOtherAgent() != null) {
                    sat.setOtherAgent(sa.getOtherAgent());
                } else if (sa.getAgent() != null) {
                    sat.setAgent(new AgentType());
                    sat.getAgent().setName(sa.getAgent().getName());
                    // sat.getAgent().setNscNumber(sa.getAgent().getNscNumber());
                }

                if (sa.getIndType() != null) sat.setIndType(IndType.fromValue(sa.getIndType().name()));
                if (sa.getPartOfLeadIND() != null) sat.setPartOfLeadIND(sa.getPartOfLeadIND());

                
/*
                List<StudyAgentINDAssociation> as = sa.getStudyAgentINDAssociations();

                if (as != null && as.size() > 0) {
                    sat.setStudyAgentINDAssociations(new StudyAgentINDAssociations());
                    sat.getStudyAgentINDAssociations().setStudyAgentINDAssociation(new StudyAgentINDAssociationType());

                    if (as.get(0).getInvestigationalNewDrug() != null) {
                        sat.getStudyAgentINDAssociations().getStudyAgentINDAssociation().setInvestigationalNewDrug(new InvestigationalNewDrugType());
                        sat.getStudyAgentINDAssociations().getStudyAgentINDAssociation().getInvestigationalNewDrug().setIndNumber(new BigInteger(as.get(0).getInvestigationalNewDrug().getIndNumber().intValue()));
                    }
                }

*/
                agentsDto.getStudyAgent().add(sat);
            }

            studyDto.setStudyAgents(agentsDto);
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
	
	private void populateStudyDiseasesDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception{

        List<CtepStudyDisease> ctep = study.getCtepStudyDiseases();
        List<MeddraStudyDisease> meddra = study.getMeddraStudyDiseases();

        if (ctep != null && ctep.size() > 0) {

            CtepStudyDiseases cteps = new CtepStudyDiseases();
            cteps.setCtepStudyDisease(new ArrayList<CtepStudyDiseaseType>());

            for (CtepStudyDisease ctepD : ctep) {
                CtepStudyDiseaseType ct = new CtepStudyDiseaseType();

                ct.setDiseaseTerm(new DiseaseTermType());
                ct.getDiseaseTerm().setTerm(ctepD.getDiseaseTerm().getTerm());
                ct.getDiseaseTerm().setMeddraCode(ctepD.getDiseaseTerm().getMeddraCode());
                ct.setLeadDisease(ctepD.getLeadDisease());

                cteps.getCtepStudyDisease().add(ct);
            }

            if (cteps.getCtepStudyDisease().size() > 0) studyDto.setCtepStudyDiseases(cteps);

        } else if (meddra != null && meddra.size() > 0) {
            
            MeddraStudyDiseases meddras = new MeddraStudyDiseases();
            meddras.setMeddraStudyDisease(new ArrayList<MeddraStudyDiseaseType>());

            for (MeddraStudyDisease meddraD : meddra) {
                MeddraStudyDiseaseType md = new MeddraStudyDiseaseType();
                md.setMeddraCode(meddraD.getMeddraCode());
                meddras.getMeddraStudyDisease().add(md);
            }

            if (meddras.getMeddraStudyDisease().size() > 0) studyDto.setMeddraStudyDiseases(meddras);

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

    private void populateStudyEvaluationPeriodsDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception {

        EvaluationPeriods evaluationPeriods = studyDto.getEvaluationPeriods();
        List<Epoch> epochs = study.getEpochs();

        if (epochs != null && epochs.size() > 0) {
            EvaluationPeriods eps = new EvaluationPeriods();
            eps.setEvaluationPeriod(new ArrayList<EvaluationPeriodType>());

            for (Epoch e : epochs) {
                EvaluationPeriodType ept = new EvaluationPeriodType();

                ept.setDescriptionText(e.getDescriptionText());
                ept.setEpochOrder(e.getEpochOrder());
                ept.setName(e.getName());

                // SAES

                Arm arm = e.getArms().get(0);
                EvaluationPeriodType.SolicitedAdverseEvents saes = new EvaluationPeriodType.SolicitedAdverseEvents();

                for (SolicitedAdverseEvent sae: arm.getSolicitedAdverseEvents()) {
                    SolicitedAdverseEventType saet = new SolicitedAdverseEventType();

                    if (sae.getCtcterm() != null) {
                        saet.setCtepCode(sae.getCtcterm().getCtepCode());
                    }

                    if (sae.getLowLevelTerm() != null) {
                        saet.setMeddraCode(sae.getLowLevelTerm().getMeddraCode());
                    }

                    if (sae.getOtherTerm() != null) {
                        saet.setOtherMeddraCode(sae.getOtherTerm().getMeddraCode());
                    }
                    
                    saes.getSolicitedAdverseEvent().add(saet);
                }

                ept.setSolicitedAdverseEvents(saes);
                eps.getEvaluationPeriod().add(ept);
            }

            studyDto.setEvaluationPeriods(eps);
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

    private void populateStudyExpectedAEsDomain2Dto(gov.nih.nci.cabig.caaers.webservice.Study studyDto, Study study) throws Exception {

        List<ExpectedAEMeddraLowLevelTerm> meddraTerms = study.getExpectedAEMeddraLowLevelTerms();
        List<ExpectedAECtcTerm> ctcTerms = study.getExpectedAECtcTerms();

        if (ctcTerms != null && ctcTerms.size() > 0) {
            ExpectedAECtcTerms terms = new ExpectedAECtcTerms();
            terms.setExpectedAECtcTerm(new ArrayList<ExpectedAECtcTermType>());

            for (ExpectedAECtcTerm term : ctcTerms) {
                ExpectedAECtcTermType tt = new ExpectedAECtcTermType();
                if (term.getCtcTerm() != null) {
                    tt.setCtepCode(term.getCtcTerm().getCtepCode());
                }
                if (term.getOtherMeddraTerm() != null) {
                    tt.setOtherMeddraCode(term.getOtherMeddraTerm().getMeddraCode());
                }
                terms.getExpectedAECtcTerm().add(tt);
            }

            if (terms.getExpectedAECtcTerm().size() > 0) studyDto.setExpectedAECtcTerms(terms);

        } else if (meddraTerms != null && meddraTerms.size() > 0) {
            ExpectedAEMeddraTerms terms = new ExpectedAEMeddraTerms();
            terms.setExpectedAEMeddraTerm(new ArrayList<ExpectedAEMeddraTermType>());

            for (ExpectedAEMeddraLowLevelTerm term : meddraTerms) {
                ExpectedAEMeddraTermType tt = new ExpectedAEMeddraTermType();
                tt.setMeddraCode(term.getTerm().getMeddraCode());
                terms.getExpectedAEMeddraTerm().add(tt);
            }
            if (terms.getExpectedAEMeddraTerm().size() > 0) studyDto.setExpectedAEMeddraTerms(terms);
            
        }
    }
}
