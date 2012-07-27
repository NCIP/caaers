package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.integration.schema.common.*;
import gov.nih.nci.cabig.caaers.integration.schema.study.*;
import gov.nih.nci.cabig.caaers.integration.schema.study.AgentType;
import gov.nih.nci.cabig.caaers.integration.schema.study.DiseaseTermType;
import gov.nih.nci.cabig.caaers.integration.schema.study.IndType;
import gov.nih.nci.cabig.caaers.integration.schema.study.InvestigationalNewDrugType;
import gov.nih.nci.cabig.caaers.integration.schema.study.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.study.ObjectFactory;
import gov.nih.nci.cabig.caaers.integration.schema.study.ResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.study.SiteInvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudyDeviceType.*;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudyDeviceType.StudyDeviceINDAssociations;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudyDeviceINDAssociationType;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudyIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.study.TreatmentAssignmentType;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class has one public method which Converts a JAXB generated Study Type object
 * to a Domain Object Study Type as required by StudyMigrator.
 * @author Monish Dombla
 * @author Biju Joseph (added start/end dates)
 * @author Ion C. Olaru
 *
 */
public class StudyConverter {

    private static Log logger = LogFactory.getLog(StudyConverter.class);

	private CtcTermDao ctcTermDao;
	private LowLevelTermDao lowLevelTermDao;

    public gov.nih.nci.cabig.caaers.integration.schema.study.Studies convertStudyDomainToStudyDto(Study study) throws Exception {
        ObjectFactory objectFactory = new ObjectFactory();
        gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto = objectFactory.createStudy();
            studyDto.setShortTitle(study.getShortTitle());
            studyDto.setPhaseCode(StudyPhaseType.fromValue(study.getPhaseCode()));
            studyDto.setStudyPurpose(study.getStudyPurpose());
            studyDto.setAeTermUnique(study.getAeTermUnique());
            studyDto.setVerbatimFirst(study.getVerbatimFirst());
            studyDto.setParticipationType(study.getParticipationType());

            if(study.getOtherMeddra() != null && !study.getOtherMeddra().equals("")){
                MeddraVersion otherMeddra = new MeddraVersion();
                otherMeddra.setName(study.getOtherMeddra().getName());
                studyDto.setOtherMeddra(otherMeddra.getName());
            }

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
            populateStudyDevicesDomain2Dto(studyDto, study);
            convertStudyOtherInterventions(study, studyDto);

            gov.nih.nci.cabig.caaers.integration.schema.study.Studies studies = objectFactory.createStudies();
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
	public void convertStudyDtoToStudyDomain(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws CaaersSystemException{
		assert study != null : "Domain study should not be null";

		try {
			//Populate Study Instance attributes
			study.setShortTitle(studyDto.getShortTitle());
            study.setVerbatimFirst(studyDto.isVerbatimFirst());
            study.setParticipationType(studyDto.getParticipationType());
            study.setAeTermUnique(studyDto.isAeTermUnique());
			if(studyDto.getPhaseCode() != null) study.setPhaseCode(studyDto.getPhaseCode().value());
            study.setStudyPurpose(studyDto.getStudyPurpose());

			if (! "".equals(studyDto.getOtherMeddra()) && studyDto.getOtherMeddra() != null) {
				MeddraVersion otherMeddra = null;
				otherMeddra = new MeddraVersion();
				otherMeddra.setName(studyDto.getOtherMeddra());
				study.setOtherMeddra(otherMeddra);
			}

			populateAeTerminology(studyDto, study);
			populateDiseaseTerminology(studyDto,study);
			populateFundingSponsor(studyDto, study);
			populateCoordinatingCenter(studyDto, study);
			populateStudySites(studyDto, study);
			populateIdentifiers(studyDto, study);
			populateTreatmentAssignments(studyDto, study);
			populateStudyAgents(studyDto, study);
			populateStudyDiseases(studyDto, study);
			populateStudyEvaluationPeriods(studyDto, study);
			populateStudyExpectedAEs(studyDto, study);
            populateStudyDevices(studyDto, study);
            convertStudyOtherInterventions(studyDto, study);

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
        xml.setMonth(c.get(Calendar.MONTH) + 1);
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

                if (studyPersonnelType.getStartDate() != null) {
				    studyPersonnel.setStartDate(studyPersonnelType.getStartDate().toGregorianCalendar().getTime());
                }

                if (studyPersonnelType.getEndDate() != null) {
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

/*
	private void populateStudyReportTypes(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{
		if (studyDto.isReportTypeAdeersPDF() != null && studyDto.isReportTypeAdeersPDF()) study.updateReportFormats(Boolean.TRUE, ReportFormatType.ADEERSPDF);
		if (studyDto.isReportTypeCaaersXML() != null && studyDto.isReportTypeCaaersXML()) study.updateReportFormats(Boolean.TRUE, ReportFormatType.CAAERSXML);
		if (studyDto.isReportTypeCIOMSAEForm() != null && studyDto.isReportTypeCIOMSAEForm()) study.updateReportFormats(Boolean.TRUE, ReportFormatType.CIOMSSAEFORM);
		if (studyDto.isReportTypeCIOMSForm() != null && studyDto.isReportTypeCIOMSForm()) study.updateReportFormats(Boolean.TRUE, ReportFormatType.CIOMSFORM);
		if (studyDto.isReportTypeDCPSAEForm() != null && studyDto.isReportTypeDCPSAEForm()) study.updateReportFormats(Boolean.TRUE, ReportFormatType.DCPSAEFORM);
		if (studyDto.isReportTypeMedwatchPDF() != null && studyDto.isReportTypeMedwatchPDF()) study.updateReportFormats(Boolean.TRUE, ReportFormatType.MEDWATCHPDF);
	}
*/

	private void populateAeTerminology(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

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

/*
	private void populateStudyReportTypesDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study){
        studyDto.setReportTypeMedwatchPDF(study.getMedwatchPDFType());
        studyDto.setReportTypeAdeersPDF(study.getAdeersPDFType());
        studyDto.setReportTypeCaaersXML(study.getCaaersXMLType());
        studyDto.setReportTypeCIOMSAEForm(study.getCiomsSaePDFType());
        studyDto.setReportTypeCIOMSForm(study.getCiomsPDFType());
        studyDto.setReportTypeDCPSAEForm(study.getDcpSAEPDFType());
	}
*/

	private void populateAeTerminologyDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

        if (study.getAeTerminology() != null) {
            AeTerminologyType aeTerminology = new AeTerminologyType();

            if (study.getAeTerminology().getCtcVersion() != null) {
                CtcVersionType ctc = new CtcVersionType();
                ctc.setName(study.getAeTerminology().getCtcVersion().getName());
                aeTerminology.setCtcVersion(ctc);

            }

            if (study.getAeTerminology().getMeddraVersion() != null) {
                MeddraVersionType meddra = new MeddraVersionType();
                meddra.setName(study.getAeTerminology().getMeddraVersion().getName());
                aeTerminology.setMeddraVersion(meddra);
            }

            studyDto.setAeTerminology(aeTerminology);
        }

    }

	private void populateDiseaseTerminology(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

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

	private void populateDiseaseTerminologyDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

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

	private void populateFundingSponsor(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

		if(studyDto.getFundingSponsor() != null){
			FundingSponsor fundingSponsor = null;
			StudyFundingSponsor studyFundingSponsor = null;
			if(studyDto.getFundingSponsor().getOrganizationAssignedIdentifier() != null){
				fundingSponsor = new FundingSponsor();
				OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
				organizationAssignedIdentifier.setValue(studyDto.getFundingSponsor().getOrganizationAssignedIdentifier().getValue());
                organizationAssignedIdentifier.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
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

	private void populateFundingSponsorDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception {

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

	private void populateCoordinatingCenter(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

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

	private void populateCoordinatingCenterDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

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

	private void populateStudySites(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

		gov.nih.nci.cabig.caaers.integration.schema.study.Study.StudyOrganizations studySites = studyDto.getStudyOrganizations();
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

	private void populateStudySitesDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception {

        List<StudySite> studySites = study.getStudySites();

        if (studySites != null && !studySites.isEmpty()) {
            gov.nih.nci.cabig.caaers.integration.schema.study.Study.StudyOrganizations studyOrganizations = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.StudyOrganizations();
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

	private void populateIdentifiers(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

		gov.nih.nci.cabig.caaers.integration.schema.study.Study.Identifiers identifiers = studyDto.getIdentifiers();
		if(identifiers != null){
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
					study.addIdentifier(orgIdentifier);
				}
			}
            
            List<SystemAssignedIdentifierType> sysAssignedIdList = identifiers.getSystemAssignedIdentifier();
            if(sysAssignedIdList != null && !sysAssignedIdList.isEmpty()){
                for(SystemAssignedIdentifierType systemAssignedIdentifierType : sysAssignedIdList){
                    if(StringUtils.isEmpty(systemAssignedIdentifierType.getSystemName()) ||  
                            StringUtils.isEmpty(systemAssignedIdentifierType.getValue())) continue;
                    
                    if(systemAssignedIdentifierType.getType() == null) continue;
                    
                    SystemAssignedIdentifier sysIdentifier = new SystemAssignedIdentifier();

                    sysIdentifier.setSystemName(systemAssignedIdentifierType.getSystemName());
                    sysIdentifier.setType(systemAssignedIdentifierType.getType().value());
                    sysIdentifier.setValue(systemAssignedIdentifierType.getValue());
                    study.addIdentifier(sysIdentifier);
                }
            }

		}

	}

	private void populateIdentifiersDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

		List<Identifier> ids = study.getIdentifiers();

		if(ids != null && ids.size() > 0){

            gov.nih.nci.cabig.caaers.integration.schema.study.Study.Identifiers identifiers = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.Identifiers();
            identifiers.setOrganizationAssignedIdentifier(new ArrayList<OrganizationAssignedIdentifierType>());
            identifiers.setSystemAssignedIdentifier(new ArrayList<SystemAssignedIdentifierType>());

			List<OrganizationAssignedIdentifier> orgIds = study.getOrganizationAssignedIdentifiers();
			List<SystemAssignedIdentifier> sysIds = study.getSystemAssignedIdentifiers();

            if (orgIds != null) {
                for (OrganizationAssignedIdentifier oid : orgIds) {
                    if (StudyIdentifierType.fromValue(oid.getType()).equals(StudyIdentifierType.PROTOCOL_AUTHORITY_IDENTIFIER)) continue;
                    if (StudyIdentifierType.fromValue(oid.getType()).equals(StudyIdentifierType.COORDINATING_CENTER_IDENTIFIER)) continue;

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
                    o.setPrimaryIndicator(sid.getPrimaryIndicator());
                    o.setSystemName(sid.getSystemName());
                    o.setValue(sid.getValue());
                    o.setType(StudyIdentifierType.fromValue(sid.getType()));
                    identifiers.getSystemAssignedIdentifier().add(o);
                }
            }

            studyDto.setIdentifiers(identifiers);
		}

	}

	private void populateTreatmentAssignments(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

		gov.nih.nci.cabig.caaers.integration.schema.study.Study.TreatmentAssignments treatmentAssignments = studyDto.getTreatmentAssignments();
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
                    treatmentAssignment.setCtepDbIdentifier(treatmentAssignmentType.getCtepDbIdentifier());
					treatmentAssignment.setDescription(treatmentAssignmentType.getDescription());
					treatmentAssignment.setComments(treatmentAssignmentType.getComments());
					treatmentAssignmentList.add(treatmentAssignment);
				}
				study.setTreatmentAssignments(treatmentAssignmentList);
			}
		}

	}

	private void populateTreatmentAssignmentsDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

		List<TreatmentAssignment> list = study.getTreatmentAssignments();

        if (list != null && list.size() > 0) {
                gov.nih.nci.cabig.caaers.integration.schema.study.Study.TreatmentAssignments ts = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.TreatmentAssignments();
                List<TreatmentAssignmentType> treatmentAssignmentsTypeList = new ArrayList<TreatmentAssignmentType>();
                ts.setTreatmentAssignment(treatmentAssignmentsTypeList);

                for (TreatmentAssignment ta : list) {
                    TreatmentAssignmentType tat = new TreatmentAssignmentType();
                    tat.setCode(ta.getCode());
                    if (ta.getDoseLevelOrder() != null) tat.setDoseLevelOrder(ta.getDoseLevelOrder().toString());
                    tat.setDescription(ta.getDescription());
                    tat.setComments(ta.getComments());
                    tat.setCtepDbIdentifier(ta.getCtepDbIdentifier());
                    treatmentAssignmentsTypeList.add(tat);
                }

                studyDto.setTreatmentAssignments(ts);
        }

    }

    private INDType convertINDType(IndType indType){
        if(indType == IndType.CTEP_IND) return INDType.CTEP_IND;
        if(indType == IndType.DCP_IND) return INDType.DCP_IND;
        if(indType == IndType.OTHER) return INDType.OTHER;
        if(indType == IndType.IND_EXEMPT) return INDType.IND_EXEMPT;
        if(indType == IndType.NA) return INDType.NA;
        if(indType == IndType.NA_COMMERCIAL) return INDType.NA_COMMERCIAL;
        return null;
    }
    
    private void populateIND(StudyAgentType.StudyAgentINDAssociations associations, StudyAgent sa){
        StudyAgentINDAssociationType assType = associations.getStudyAgentINDAssociation();
        if(assType == null) return;
        StudyAgentINDAssociation saAssociation = new StudyAgentINDAssociation();
        sa.addStudyAgentINDAssociation(saAssociation);

        InvestigationalNewDrugType indType = assType.getInvestigationalNewDrug();
        if(indType == null) return;
        if(indType.getIndNumber() == null && indType.getHolderName() == null) return;
        InvestigationalNewDrug ind = new InvestigationalNewDrug();
        saAssociation.setInvestigationalNewDrug(ind);       

        if(indType.getIndNumber() != null) ind.setIndNumber(indType.getIndNumber().intValue());
        if(StringUtils.isNotEmpty(indType.getHolderName())) ind.setHolderName(indType.getHolderName());
        ind.setStatus(ActiveInactiveStatus.AC.getCode());
        if(indType.getEndDate() != null){
            Calendar c = indType.getEndDate().toGregorianCalendar();
            if(Calendar.getInstance().after(c)) ind.setStatus(ActiveInactiveStatus.IN.getCode());
        }
        logger.info("    IND " + saAssociation.getInvestigationalNewDrug().getHolderName() + " , " + saAssociation.getInvestigationalNewDrug().getIndNumber());

    }
    
    private void populateStudyAgent(StudyAgentType saType, Study study){
        StudyAgent sa = new StudyAgent();
        Agent agent = new Agent();
        sa.setAgent(agent);
        sa.setOtherAgent(saType.getOtherAgent());
        sa.setIndType(convertINDType(saType.getIndType()));
        sa.setPartOfLeadIND(saType.isPartOfLeadIND());

        if(saType.getAgent() != null){
            agent.setName(saType.getAgent().getName());
            agent.setNscNumber(saType.getAgent().getNscNumber());
        }

        if(saType.getStudyAgentINDAssociations() != null){
               populateIND(saType.getStudyAgentINDAssociations(), sa);
        }
        logger.info(" Study Agent " + sa.getAgentName());
        study.addStudyAgent(sa);
    }
    
    private void populateStudyAgents(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception {

        gov.nih.nci.cabig.caaers.integration.schema.study.Study.StudyAgents studyAgents = studyDto.getStudyAgents();
        if(studyAgents == null) return ;
        if(studyAgents.getStudyAgent().isEmpty()) return;
        for(StudyAgentType studyAgentType : studyAgents.getStudyAgent()){
            populateStudyAgent(studyAgentType, study);
        }
        
    }

    private void populateStudyAgentsDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception {

        List<StudyAgent> agents = study.getStudyAgents();

        if (agents != null && !agents.isEmpty()) {

            gov.nih.nci.cabig.caaers.integration.schema.study.Study.StudyAgents agentsDto = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.StudyAgents();
            agentsDto.setStudyAgent(new ArrayList<StudyAgentType>());

            for (StudyAgent sa : agents) {

                if (sa == null && sa.getOtherAgent() == null && sa.getAgent() == null || sa.isRetired()) continue;

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


                List<StudyAgentINDAssociation> as = sa.getStudyAgentINDAssociations();

                if (as != null && as.size() > 0) {
                    sat.setStudyAgentINDAssociations(new StudyAgentType.StudyAgentINDAssociations());
                    sat.getStudyAgentINDAssociations().setStudyAgentINDAssociation(new StudyAgentINDAssociationType());

                    InvestigationalNewDrug ind =   as.get(0).getInvestigationalNewDrug();
                    if (ind != null) {

                        InvestigationalNewDrugType idt = new InvestigationalNewDrugType();
                        sat.getStudyAgentINDAssociations().getStudyAgentINDAssociation().setInvestigationalNewDrug(idt);

                        if(ind.getIndNumber() != null) idt.setIndNumber(new BigInteger((ind.getStrINDNo())));
                        if(ind.getINDHolder() != null) idt.setHolderName(ind.getHolderName());
                        idt.setStatus(ind.isActive() ? ActiveInactiveStatusType.ACTIVE : ActiveInactiveStatusType.INACTIVE);
                    }
                }

                agentsDto.getStudyAgent().add(sat);
            }

            studyDto.setStudyAgents(agentsDto);
        }
    }

    private void populateStudyDiseases(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

		gov.nih.nci.cabig.caaers.integration.schema.study.Study.CtepStudyDiseases ctepStudyDiseases = studyDto.getCtepStudyDiseases();
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
					}
					if(ctepStudyDiseaseType.getDiseaseTerm().getMeddraCode() != null){
						ctepStudyDisease.getDiseaseTerm().setMeddraCode(ctepStudyDiseaseType.getDiseaseTerm().getMeddraCode());
					}
					ctepStudyDiseaseList.add(ctepStudyDisease);
				}
				study.setCtepStudyDiseases(ctepStudyDiseaseList);
			}
		}else{
			gov.nih.nci.cabig.caaers.integration.schema.study.Study.MeddraStudyDiseases meddraStudyDiseases = studyDto.getMeddraStudyDiseases();
			if(meddraStudyDiseases != null){
				List<MeddraStudyDiseaseType> meddraStudyDiseaseTypeList = meddraStudyDiseases.getMeddraStudyDisease();
				List<MeddraStudyDisease> meddraStudyDiseaseList = study.getMeddraStudyDiseases();
				if(meddraStudyDiseaseTypeList != null && !meddraStudyDiseaseTypeList.isEmpty()){
					MeddraStudyDisease meddraStudyDisease = null;
					for(MeddraStudyDiseaseType meddraStudyDiseaseType : meddraStudyDiseaseTypeList){
						meddraStudyDisease = new MeddraStudyDisease();
						meddraStudyDisease.setTerm(new LowLevelTerm());
						meddraStudyDisease.getTerm().setMeddraCode(meddraStudyDiseaseType.getMeddraCode());
						meddraStudyDiseaseList.add(meddraStudyDisease);
					}
				}
				study.setMeddraStudyDiseases(meddraStudyDiseaseList);
			}
		}
	}

	private void populateStudyDiseasesDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

        List<CtepStudyDisease> ctep = study.getCtepStudyDiseases();
        List<MeddraStudyDisease> meddra = study.getMeddraStudyDiseases();

        if (ctep != null && ctep.size() > 0) {

            gov.nih.nci.cabig.caaers.integration.schema.study.Study.CtepStudyDiseases cteps = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.CtepStudyDiseases();
            cteps.setCtepStudyDisease(new ArrayList<CtepStudyDiseaseType>());

            for (CtepStudyDisease ctepD : ctep) {
                if (ctepD.isRetired()) continue;
                CtepStudyDiseaseType ct = new CtepStudyDiseaseType();

                ct.setDiseaseTerm(new DiseaseTermType());
                ct.getDiseaseTerm().setTerm(ctepD.getDiseaseTerm().getTerm());
                // ct.getDiseaseTerm().setMeddraCode(ctepD.getDiseaseTerm().getMeddraCode());
                ct.setLeadDisease(ctepD.getLeadDisease() == null ? false : ctepD.getLeadDisease());

                cteps.getCtepStudyDisease().add(ct);
            }

            if (cteps.getCtepStudyDisease().size() > 0) studyDto.setCtepStudyDiseases(cteps);

        }

        if (meddra != null && meddra.size() > 0) {
            gov.nih.nci.cabig.caaers.integration.schema.study.Study.MeddraStudyDiseases meddras = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.MeddraStudyDiseases();
            meddras.setMeddraStudyDisease(new ArrayList<MeddraStudyDiseaseType>());

            for (MeddraStudyDisease meddraD : meddra) {
                if(meddraD.isRetired()) continue;
                MeddraStudyDiseaseType md = new MeddraStudyDiseaseType();
                md.setMeddraCode(meddraD.getTerm().getMeddraCode());
                meddras.getMeddraStudyDisease().add(md);
            }

            if (meddras.getMeddraStudyDisease().size() > 0) studyDto.setMeddraStudyDiseases(meddras);

        }
    }

	private void populateStudyEvaluationPeriods(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{
		gov.nih.nci.cabig.caaers.integration.schema.study.Study.EvaluationPeriods evaluationPeriods = studyDto.getEvaluationPeriods();
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
									List<CtcTerm> terms = ctcTermDao.getByCtepCodeandVersion(solicitedAdverseEventType.getCtepCode(), studyDto.getAeTerminology().getCtcVersion().getName());
									if (terms.size() == 0) {
										break;
									}
									solicitedAdverseEvent.setCtcterm(terms.get(0));
								}
								if(solicitedAdverseEventType.getMeddraCode() != null && !"".equals(solicitedAdverseEventType.getMeddraCode()) ){

									List<LowLevelTerm> terms = lowLevelTermDao.getByMeddraCode(solicitedAdverseEventType.getMeddraCode());
									if (terms.size() == 0) {
										break;
									}
									solicitedAdverseEvent.setLowLevelTerm(terms.get(0));
								}
								if(solicitedAdverseEventType.getOtherMeddraCode() != null && !"".equals(solicitedAdverseEventType.getOtherMeddraCode())){
									List<LowLevelTerm> terms = lowLevelTermDao.getByMeddraCode(solicitedAdverseEventType.getOtherMeddraCode());
									if (terms.size() == 0) {
										break;
									}
									solicitedAdverseEvent.setOtherTerm(terms.get(0));
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

    private void populateStudyEvaluationPeriodsDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception {

        gov.nih.nci.cabig.caaers.integration.schema.study.Study.EvaluationPeriods evaluationPeriods = studyDto.getEvaluationPeriods();
        List<Epoch> epochs = study.getEpochs();

        if (epochs != null && epochs.size() > 0) {
            gov.nih.nci.cabig.caaers.integration.schema.study.Study.EvaluationPeriods eps = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.EvaluationPeriods();
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

                if (saes.getSolicitedAdverseEvent().size() > 0) ept.setSolicitedAdverseEvents(saes);
                eps.getEvaluationPeriod().add(ept);
            }

            studyDto.setEvaluationPeriods(eps);
        }

    }

    private void populateStudyDevices(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception {
        List<StudyDevice> l = new ArrayList<StudyDevice>();

        if (studyDto.getStudyDevices() == null) return;

        for (StudyDeviceType sdt : studyDto.getStudyDevices().getStudyDevice()) {
            StudyDevice sd  = new StudyDevice();
            
            if (sdt.getDevice() != null) {
                sd.setDevice(new Device());
                sd.getDevice().setBrandName(sdt.getDevice().getBrandName());
                sd.getDevice().setCommonName(sdt.getDevice().getCommonName());
                sd.getDevice().setType(sdt.getDevice().getType());
                sd.getDevice().setCtepDbIdentifier(sdt.getDevice().getCtepDbIdentifier());
            } else {
                sd.setOtherBrandName(sdt.getOtherBrandName());
                sd.setOtherCommonName(sdt.getOtherCommonName());
                sd.setOtherDeviceType(sdt.getOtherDeviceType());
            }
            sd.setCatalogNumber(sdt.getCatalogNumber());
            sd.setManufacturerCity(sdt.getManufacturerCity());
            sd.setManufacturerName(sdt.getManufacturerName());
            sd.setManufacturerState(sdt.getManufacturerState());
            sd.setModelNumber(sdt.getModelNumber());

            //add IDEs
            StudyDeviceINDAssociations ideAssociations = sdt.getStudyDeviceINDAssociations(); 
            if( ideAssociations != null){
                StudyDeviceINDAssociationType ideAssociationType =  ideAssociations.getStudyDeviceINDAssociation();
                if(ideAssociationType != null){
                    StudyDeviceINDAssociation studyDeviceINDAssociation = new StudyDeviceINDAssociation();
                    sd.addStudyDeviceINDAssociation(studyDeviceINDAssociation);

                    InvestigationalNewDrugType ideType = ideAssociationType.getInvestigationalNewDrug();
                    if(ideType != null){
                        String ideHolder = ideType.getHolderName();
                        BigInteger ideNumber = ideType.getIndNumber();

                        if(ideNumber != null){
                            InvestigationalNewDrug ind = new InvestigationalNewDrug();
                            ind.setHolderName(ideHolder);
                            ind.setIndNumber(ideNumber.intValue());
                            ind.setStatus(ActiveInactiveStatus.AC.getCode());
                            if(ideType.getStatus() != null){
                                ind.setStatus(ideType.getStatus().value());
                            }
                            studyDeviceINDAssociation.setInvestigationalNewDrug(ind);

                        }
                    }

                }
            }
            
            l.add(sd);
        }

        study.setStudyDevices(l);
	}

    private void populateStudyExpectedAEs(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception{

		gov.nih.nci.cabig.caaers.integration.schema.study.Study.ExpectedAECtcTerms expectedAECtcTerms = studyDto.getExpectedAECtcTerms();
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
			gov.nih.nci.cabig.caaers.integration.schema.study.Study.ExpectedAEMeddraTerms expectedAEMeddraTerms = studyDto.getExpectedAEMeddraTerms();
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

    private void populateStudyDevicesDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception {
        List<StudyDevice> d = study.getStudyDevices();
        gov.nih.nci.cabig.caaers.integration.schema.study.Study.StudyDevices studyDevices = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.StudyDevices();
        studyDevices.setStudyDevice(new ArrayList<StudyDeviceType>());

        for (StudyDevice sd : d) {
            StudyDeviceType sdt = new StudyDeviceType();
            if (sd.getDevice() != null) {
                sdt.setDevice(new DeviceType());
                sdt.getDevice().setBrandName(sd.getDevice().getBrandName());
                sdt.getDevice().setCommonName(sd.getDevice().getCommonName());
                sdt.getDevice().setType(sd.getDevice().getType());
                sdt.getDevice().setCtepDbIdentifier(sd.getDevice().getCtepDbIdentifier());
            } else {
                sdt.setOtherBrandName(sd.getOtherBrandName());
                sdt.setOtherCommonName(sd.getOtherCommonName());
                sdt.setOtherDeviceType(sd.getOtherDeviceType());
            }
            sdt.setCatalogNumber(sd.getCatalogNumber());
            sdt.setManufacturerCity(sd.getManufacturerCity());
            sdt.setManufacturerName(sd.getManufacturerName());
            sdt.setManufacturerState(sd.getManufacturerState());
            sdt.setModelNumber(sd.getModelNumber());

            studyDevices.getStudyDevice().add(sdt);
            List<StudyDeviceINDAssociation> studyDeviceINDAssociations = sd.getStudyDeviceINDAssociations();
            if(CollectionUtils.isNotEmpty(studyDeviceINDAssociations)){
                StudyDeviceINDAssociations associationTypes = new StudyDeviceINDAssociations();
                sdt.setStudyDeviceINDAssociations(associationTypes);
                for(StudyDeviceINDAssociation sdINDAssociation : studyDeviceINDAssociations){

                    StudyDeviceINDAssociationType associationType = new StudyDeviceINDAssociationType();
                    associationTypes.setStudyDeviceINDAssociation(associationType);
                    InvestigationalNewDrugType indType = new InvestigationalNewDrugType();
                    associationType.setInvestigationalNewDrug(indType);

                    InvestigationalNewDrug ind = sdINDAssociation.getInvestigationalNewDrug();
                    if(ind != null){
                        indType.setHolderName(ind.getHolderName());
                        indType.setIndNumber(BigInteger.valueOf(ind.getIndNumber()));
                        indType.setStatus(ind.isActive() ? ActiveInactiveStatusType.ACTIVE : ActiveInactiveStatusType.INACTIVE);
                    }
                    
                }
            }
        }

        studyDto.setStudyDevices(studyDevices);
    }

    private void populateStudyExpectedAEsDomain2Dto(gov.nih.nci.cabig.caaers.integration.schema.study.Study studyDto, Study study) throws Exception {

        List<ExpectedAEMeddraLowLevelTerm> meddraTerms = study.getExpectedAEMeddraLowLevelTerms();
        List<ExpectedAECtcTerm> ctcTerms = study.getExpectedAECtcTerms();

        if (ctcTerms != null && ctcTerms.size() > 0) {
            gov.nih.nci.cabig.caaers.integration.schema.study.Study.ExpectedAECtcTerms terms = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.ExpectedAECtcTerms();
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
            gov.nih.nci.cabig.caaers.integration.schema.study.Study.ExpectedAEMeddraTerms terms = new gov.nih.nci.cabig.caaers.integration.schema.study.Study.ExpectedAEMeddraTerms();
            terms.setExpectedAEMeddraTerm(new ArrayList<ExpectedAEMeddraTermType>());

            for (ExpectedAEMeddraLowLevelTerm term : meddraTerms) {
                ExpectedAEMeddraTermType tt = new ExpectedAEMeddraTermType();
                tt.setMeddraCode(term.getTerm().getMeddraCode());
                terms.getExpectedAEMeddraTerm().add(tt);
            }
            if (terms.getExpectedAEMeddraTerm().size() > 0) studyDto.setExpectedAEMeddraTerms(terms);

        }
    }

    /**
     * Convert the Intervention lisy of an XML Study into an analogue list for a domain Study
     * @param xmlStudy source xmlStudy
     * @param study destination domainStudy
     * @throws Exception
     */
    private void convertStudyOtherInterventions(gov.nih.nci.cabig.caaers.integration.schema.study.Study xmlStudy, Study study) throws Exception {
        List<OtherIntervention> otherInterventions = new ArrayList<OtherIntervention>();
        study.setOtherInterventions(otherInterventions);

        if (xmlStudy.getOtherInterventions() == null || CollectionUtils.isEmpty(xmlStudy.getOtherInterventions().getOtherIntervention())) return;

        for (gov.nih.nci.cabig.caaers.integration.schema.study.StudyInterventionType oi : xmlStudy.getOtherInterventions().getOtherIntervention()) {
            OtherIntervention si = new OtherIntervention();
            si.setStudyTherapyType(StudyTherapyType.valueOf(oi.getInterventionType().value()));
            si.setName(oi.getName());
            si.setDescription(oi.getDescription());
            otherInterventions.add(si);
        }
    }

    /**
     * Convert the Intervention lisy of a domain Study into an analogue list for an XML Study
     * @param study source domainStudy
     * @param xmlStudy destination xmlStudy
     * @throws Exception
     */
    private void convertStudyOtherInterventions(Study study, gov.nih.nci.cabig.caaers.integration.schema.study.Study xmlStudy) throws Exception {

        xmlStudy.setOtherInterventions(new gov.nih.nci.cabig.caaers.integration.schema.study.Study.OtherInterventions());
        xmlStudy.getOtherInterventions().setOtherIntervention(new ArrayList<StudyInterventionType>());

        if (CollectionUtils.isEmpty(study.getOtherInterventions())) return;

        for (OtherIntervention oi : study.getOtherInterventions()) {
            gov.nih.nci.cabig.caaers.integration.schema.study.StudyInterventionType siType = new gov.nih.nci.cabig.caaers.integration.schema.study.StudyInterventionType();
            siType.setInterventionType(OtherInterventionType.fromValue(oi.getStudyTherapyType().getName()));
            siType.setDescription(oi.getDescription());
            siType.setName(oi.getName());
            xmlStudy.getOtherInterventions().getOtherIntervention().add(siType);
        }
    }

	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}

	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}
}
