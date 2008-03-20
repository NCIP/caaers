package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class StudyImportServiceImpl extends AbstractImportServiceImpl {

    private OrganizationDao organizationDao;
    private AgentDao agentDao;
    private MedDRADao meddraDao;
    private CtcDao ctcDao;
    private MeddraVersionDao meddraVersionDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;
    private DiseaseTermDao diseaseTermDao;
    private LowLevelTermDao lowLevelTermDao;
    private InvestigationalNewDrugDao investigationalNewDrugDao;

    /**
     * Given a study object which has been serialized from an xml format
     * is recreated here and made ready to be saved.
     *
     * @param xstreamStudy - The serialized xml stream
     * @return An outcome object containing the {@link Study
     */
    public DomainObjectImportOutcome<Study> createStudyObjects(Study xstreamStudy) {
        Study st = new Study();
        DomainObjectImportOutcome<Study> studyImportOutcome = new DomainObjectImportOutcome<Study>();

        st.setShortTitle(StringUtils.isNotEmpty(xstreamStudy.getShortTitle()) ? xstreamStudy.getShortTitle() : "NA");
        st.setLongTitle(xstreamStudy.getLongTitle());
        st.setDescription(xstreamStudy.getDescription());
        st.setPrecis(xstreamStudy.getPrecis());
        st.setPhaseCode(xstreamStudy.getPhaseCode());
        st.setStatus(xstreamStudy.getStatus());
        st.setMultiInstitutionIndicator(xstreamStudy.getMultiInstitutionIndicator());
        st.setAdeersReporting(xstreamStudy.getAdeersReporting());
        st.setDesign(xstreamStudy.getDesign());

        st.setDrugAdministrationTherapyType(xstreamStudy.getDrugAdministrationTherapyType());
        st.setRadiationTherapyType(xstreamStudy.getRadiationTherapyType());
        st.setDeviceTherapyType(xstreamStudy.getDeviceTherapyType());
        st.setSurgeryTherapyType(xstreamStudy.getSurgeryTherapyType());
        st.setBehavioralTherapyType(xstreamStudy.getBehavioralTherapyType());

        migrateTherapies(st, xstreamStudy, studyImportOutcome);
        migrateTerminology(st, xstreamStudy, studyImportOutcome);
        migrateDiseaseTerminology(st, xstreamStudy, studyImportOutcome);
        migrateFundingSponsor(st, xstreamStudy, studyImportOutcome);
        migrateCoordinatingCenter(st, xstreamStudy, studyImportOutcome);
        migrateIdentifiers(st, xstreamStudy, studyImportOutcome);
        migrateStudyOrganizations(st, xstreamStudy, studyImportOutcome);
        migrateOrganizationAssignedIdentifier(st, xstreamStudy, studyImportOutcome);
        migrateStudyAgents(st, xstreamStudy, studyImportOutcome);
        migrateCtepStudyDiseases(st, xstreamStudy, studyImportOutcome);
        migrateTreatmentAssignments(st, xstreamStudy, studyImportOutcome);
        migrateMeddraStudyDiseases(st, xstreamStudy, studyImportOutcome);

        studyImportOutcome.setImportedDomainObject(st);
        studyUniquenessCheck(st, studyImportOutcome, DomainObjectImportOutcome.Severity.ERROR);

        return studyImportOutcome;
    }


    /**
     * This method will deep clone the {@link gov.nih.nci.cabig.caaers.domain.StudyTherapy} objects from the source to destination
     *
     * @param destination        - The to which the objects are copied
     * @param source             - The study from which objects are copied
     * @param studyImportOutcome - This object will store the outcome.
     */
    private void migrateTherapies(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        if (source.getDrugAdministrationTherapyType()) {
            StudyTherapy drugAdministrationTherapy = new StudyTherapy();
            drugAdministrationTherapy.setStudy(destination);
            drugAdministrationTherapy.setStudyTherapyType(StudyTherapyType.DRUG_ADMINISTRATION);
            destination.getStudyTherapies().add(drugAdministrationTherapy);
        }

        if (source.getDeviceTherapyType()) {
            StudyTherapy deviceTherapy = new StudyTherapy();
            deviceTherapy.setStudy(destination);
            deviceTherapy.setStudyTherapyType(StudyTherapyType.DEVICE);
            destination.getStudyTherapies().add(deviceTherapy);
        }

        if (source.getRadiationTherapyType()) {
            StudyTherapy radiationTherapy = new StudyTherapy();
            radiationTherapy.setStudy(destination);
            radiationTherapy.setStudyTherapyType(StudyTherapyType.RADIATION);
            destination.getStudyTherapies().add(radiationTherapy);
        }
        if (source.getSurgeryTherapyType()) {
            StudyTherapy surgeryTherapy = new StudyTherapy();
            surgeryTherapy.setStudy(destination);
            surgeryTherapy.setStudyTherapyType(StudyTherapyType.SURGERY);
            destination.getStudyTherapies().add(surgeryTherapy);
        }

        if (source.getBehavioralTherapyType()) {
            StudyTherapy behavioralTherapy = new StudyTherapy();
            behavioralTherapy.setStudy(destination);
            behavioralTherapy.setStudyTherapyType(StudyTherapyType.BEHAVIORAL);
            destination.getStudyTherapies().add(behavioralTherapy);
        }
    }


    private void migrateFundingSponsor(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        OrganizationAssignedIdentifier organizationAssignedIdentifier = source.getFundingSponsor().getOrganizationAssignedIdentifier();
        StudyFundingSponsor studyFundingSponsor = source.getFundingSponsor().getStudyFundingSponsor();

        // Setup organizationAssignedIdentifier
        Organization organization = getOrganization(studyFundingSponsor.getOrganization().getName());
        studyImportOutcome.ifNullObject(organization, DomainObjectImportOutcome.Severity.ERROR, "The organization specified in fundingSponsor is invalid");
        organizationAssignedIdentifier.setOrganization(organization);
        organizationAssignedIdentifier.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
        organizationAssignedIdentifier.setPrimaryIndicator(false);
        destination.getIdentifiers().add(organizationAssignedIdentifier);

        // Setup fundingSponsor
        studyFundingSponsor.setOrganization(organization);
        //	Migrate Study investigators and Study Personnels
        migrateStudyInvestigators(studyFundingSponsor, organization, studyImportOutcome);
        migrateStudyPersonnels(studyFundingSponsor, organization, studyImportOutcome);
        destination.addStudyFundingSponsor(studyFundingSponsor);
    }

    private void migrateCoordinatingCenter(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        OrganizationAssignedIdentifier organizationAssignedIdentifier = source.getCoordinatingCenter().getOrganizationAssignedIdentifier();
        StudyCoordinatingCenter studyCoordinatingCenter = source.getCoordinatingCenter().getStudyCoordinatingCenter();

        // Setup organizationAssignedIdentifier
        Organization organization = getOrganization(studyCoordinatingCenter.getOrganization().getName());
        studyImportOutcome.ifNullObject(organization, DomainObjectImportOutcome.Severity.ERROR, "The organization specified in coordinatingCenter is invalid");
        organizationAssignedIdentifier.setOrganization(organization);
        organizationAssignedIdentifier.setType(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE);
        organizationAssignedIdentifier.setPrimaryIndicator(true);
        destination.getIdentifiers().add(organizationAssignedIdentifier);

        // Setup StudyCoordinatingCenter
        studyCoordinatingCenter.setOrganization(organization);
        //	Migrate Study investigators and Study Personnels
        migrateStudyInvestigators(studyCoordinatingCenter, organization, studyImportOutcome);
        migrateStudyPersonnels(studyCoordinatingCenter, organization, studyImportOutcome);

        ((Study) destination).addStudyOrganization(studyCoordinatingCenter);
    }


    private void migrateTreatmentAssignments(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        //TreatmentAssignments
        if (source.getTreatmentAssignments() != null) {
            for (TreatmentAssignment treatmentAssignment : source.getTreatmentAssignments()) {
                TreatmentAssignment target = new TreatmentAssignment();
                target.setCode(treatmentAssignment.getCode());
                target.setDoseLevelOrder(treatmentAssignment.getDoseLevelOrder());
                target.setDescription(treatmentAssignment.getDescription());
                target.setComments(treatmentAssignment.getComments());
                destination.addTreatmentAssignment(target);
            }
        }
    }


    private void migrateStudyAgents(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        //StudyAgents
        if (source.getStudyAgents() != null) {

            for (int i = 0; i < source.getStudyAgents().size(); i++) {
                StudyAgent studyAgent = source.getStudyAgents().get(i);
                StudyAgent target = new StudyAgent();
                Agent agent = null;

                if (studyAgent.getAgent().getName() != null) {
                    agent = agentDao.getByName(studyAgent.getAgent().getName());
                    target.setAgent(agent);
                }
                if (studyAgent.getAgent().getNscNumber() != null && agent == null) {
                    agent = agentDao.getByNscNumber(studyAgent.getAgent().getNscNumber());
                    target.setAgent(agent);
                }
                if (studyAgent.getOtherAgent() != null && agent == null) {
                    target.setOtherAgent(studyAgent.getOtherAgent());
                }
                studyImportOutcome.ifNullObject(agent, DomainObjectImportOutcome.Severity.ERROR, " Provdided Agent is not Valid ");
                target.setIndType(studyAgent.getIndType());
                if (target.getIndType() == INDType.DCP_IND || target.getIndType() == INDType.CTEP_IND) {
                    studyImportOutcome.ifNullObject(studyAgent.getPartOfLeadIND(), DomainObjectImportOutcome.Severity.ERROR, " Lead IND required ");
                    target.setPartOfLeadIND(studyAgent.getPartOfLeadIND());
                }
                if (target.getIndType() == INDType.CTEP_IND) {
                    // Ok so we have to provide the id here , well i can't see a different way to do this , defenitly ugly
                    // TODO: see how to enhance.
                    String[] inds = {"-111"};
                    List<InvestigationalNewDrug> investigationalNewDrugs = investigationalNewDrugDao.findByIds(inds);
                    StudyAgentINDAssociation indAssociation = new StudyAgentINDAssociation();
                    if (investigationalNewDrugs.size() > 0) {
                        InvestigationalNewDrug ind = investigationalNewDrugs.get(0);
                        indAssociation.setInvestigationalNewDrug(ind);
                        target.addStudyAgentINDAssociation(indAssociation);
                    } else {
                        studyImportOutcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, "The investigational new drug for a CTEP IND"
                                + " is not Valid ");
                    }
                }
                if (target.getIndType() == INDType.OTHER) {
                    studyImportOutcome.ifNullObject(studyAgent.getPartOfLeadIND(), DomainObjectImportOutcome.Severity.ERROR, " Lead IND required ");
                    target.setPartOfLeadIND(studyAgent.getPartOfLeadIND());
                    studyImportOutcome.ifNullOrEmptyList(studyAgent.getStudyAgentINDAssociations(), DomainObjectImportOutcome.Severity.ERROR, "With the selected IND Type it is " +
                            "required to provide an investigational new drug ");

                    for (StudyAgentINDAssociation indAssociation : studyAgent.getStudyAgentINDAssociations()) {
                        String indNumber = indAssociation.getInvestigationalNewDrug().getIndNumber().toString();
                        String[] inds = {indNumber};
                        List<InvestigationalNewDrug> investigationalNewDrugs = investigationalNewDrugDao.findByIds(inds);

                        if (investigationalNewDrugs.size() > 0) {
                            InvestigationalNewDrug ind = investigationalNewDrugs.get(0);
                            indAssociation.setInvestigationalNewDrug(ind);
                            target.addStudyAgentINDAssociation(indAssociation);
                        } else {
                            studyImportOutcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, "The selected investigational new drug "
                                    + indNumber + " is not Valid ");
                        }
                    }
                }

                destination.addStudyAgent(target);
                // TODO: ADD error handling with user interaction
            }
        }
    }

    private void migrateCtepStudyDiseases(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        //CtepStudyDiseases
        if (source.getCtepStudyDiseases() != null && source.getCtepStudyDiseases().size() > 0) {

            if (destination.getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.CTEP) {

                for (CtepStudyDisease ctepStudyDisease : source.getCtepStudyDiseases()) {
                    CtepStudyDisease destinationCtepStudyDisease = new CtepStudyDisease();
                    DiseaseTerm diseaseTerm = null;
                    String term = null;
                    if (ctepStudyDisease.getTerm() != null && ctepStudyDisease.getTerm().getTerm() != null) {
                        term = ctepStudyDisease.getTerm().getTerm();
                        diseaseTerm = diseaseTermDao.getByTermName(ctepStudyDisease.getTerm().getTerm());
                    }
                    if (ctepStudyDisease.getTerm() != null && diseaseTerm == null && ctepStudyDisease.getTerm().getMedraCode() != null) {
                        term = ctepStudyDisease.getTerm().getMedraCode();
                        diseaseTerm = diseaseTermDao.getByMeddra(ctepStudyDisease.getTerm().getMedraCode());
                    }

                    studyImportOutcome.ifNullObject(diseaseTerm, DomainObjectImportOutcome.Severity.ERROR, "The selected disease Term " +
                            term + " is not Valid ");

                    destinationCtepStudyDisease.setTerm(diseaseTerm);
                    destinationCtepStudyDisease.setLeadDisease(ctepStudyDisease.getLeadDisease() == null ? Boolean.FALSE : ctepStudyDisease.getLeadDisease());
                    destination.addCtepStudyDisease(destinationCtepStudyDisease);
                }
            } else {
                studyImportOutcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, " Selected terminology is not CTEP ");
            }
        }
    }

    private void migrateMeddraStudyDiseases(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        //MeddraStudyDiseases
        if (source.getMeddraStudyDiseases() != null && source.getMeddraStudyDiseases().size() > 0) {
            if (destination.getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.MEDDRA) {
                for (MeddraStudyDisease meddraStudyDisease : source.getMeddraStudyDiseases()) {
                    MeddraStudyDisease destinationMeddraStudyDisease = new MeddraStudyDisease();
                    LowLevelTerm lowLevelTerm = null;
                    if (meddraStudyDisease.getMeddraCode() != null) {
                        lowLevelTerm = lowLevelTermDao.getByMeddraCode(meddraStudyDisease.getMeddraCode()).size() > 0 ?
                                lowLevelTermDao.getByMeddraCode(meddraStudyDisease.getMeddraCode()).get(0) : null;
                    }

                    studyImportOutcome.ifNullObject(lowLevelTerm, DomainObjectImportOutcome.Severity.ERROR, "The selected MedDRA code " +
                            meddraStudyDisease.getMeddraCode() + " is not Valid ");

                    destinationMeddraStudyDisease.setTerm(lowLevelTerm == null ? null : lowLevelTerm);
                    destinationMeddraStudyDisease.setMeddraCode(meddraStudyDisease.getMeddraCode());
                    destination.addMeddraStudyDisease(destinationMeddraStudyDisease);
                }
            } else {
                studyImportOutcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, " Selected terminology is not MedDRA ");
            }
        }
    }

    private void migrateTerminology(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        // AeTerminology and Version
        if (source.getAeTerminology() != null) {
            if (source.getAeTerminology().getCtcVersion() != null) {
                Ctc ctc = ctcDao.getById(Integer.parseInt(source.getAeTerminology().getCtcVersion().getName()));
                AeTerminology t = destination.getAeTerminology();
                t.setTerm(Term.CTC);
                t.setCtcVersion(ctc);
                studyImportOutcome.ifNullObject(ctc, DomainObjectImportOutcome.Severity.ERROR, "CTC is either Empty or Not Valid");

            }
            if (source.getAeTerminology().getMeddraVersion() != null) {
                MeddraVersion meddraVersion = meddraVersionDao.getById(Integer.parseInt(source.getAeTerminology().getMeddraVersion().getName()));
                AeTerminology t = destination.getAeTerminology();
                t.setTerm(Term.MEDDRA);
                t.setMeddraVersion(meddraVersion);
                studyImportOutcome.ifNullObject(meddraVersion, DomainObjectImportOutcome.Severity.ERROR, "MedDRA Version is either Empty or Not Valid");
            }
        }
        studyImportOutcome.ifNullObject(source.getAeTerminology(), DomainObjectImportOutcome.Severity.ERROR, "AeTerminology is either Empty or Not Valid");
    }

    private void migrateDiseaseTerminology(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        // AeTerminology and Version
        destination.setDiseaseTerminology(source.getDiseaseTerminology());
        studyImportOutcome.ifNullObject(destination.getDiseaseTerminology(), DomainObjectImportOutcome.Severity.ERROR, "Disease AeTerminology is either Empty or Not Valid");
        if (destination.getDiseaseTerminology() != null) {
            destination.getDiseaseTerminology().setStudy(destination);
            studyImportOutcome.ifNullObject(destination.getDiseaseTerminology().getDiseaseCodeTerm(), DomainObjectImportOutcome.Severity.ERROR, "Disease Code Term is either Empty or Not Valid");
        }
    }


    private void migrateStudyOrganizations(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        if (source.getStudyOrganizations() != null) {
            for (int i = 0; i < source.getStudyOrganizations().size(); i++) {
                StudyOrganization studyOrganization = source.getStudyOrganizations().get(i);
                if (studyOrganization instanceof StudySite) {
                    StudySite studySite = (StudySite) studyOrganization;
                    Organization organization = getOrganization(studySite.getOrganization().getName());
                    studySite.setOrganization(organization);
                    // Migrate Study investigators and Study Personnels
                    migrateStudyInvestigators(studySite, organization, studyImportOutcome);
                    migrateStudyPersonnels(studySite, organization, studyImportOutcome);

                    destination.addStudySite(studySite);
                }
                if (studyOrganization instanceof StudyFundingSponsor) {
                    StudyFundingSponsor studyFundingSponsor = (StudyFundingSponsor) studyOrganization;
                    Organization organization = getOrganization(studyFundingSponsor.getOrganization().getName());
                    studyFundingSponsor.setOrganization(organization);
                    //	Migrate Study investigators and Study Personnels
                    migrateStudyInvestigators(studyFundingSponsor, organization, studyImportOutcome);
                    migrateStudyPersonnels(studyFundingSponsor, organization, studyImportOutcome);

                    destination.addStudyFundingSponsor(studyFundingSponsor);
                }

            }
        }

    }

    private void migrateStudyInvestigators(StudyOrganization studyOrganization, Organization organization, DomainObjectImportOutcome studyImportOutcome) {

        for (StudyInvestigator studyInvestigator : studyOrganization.getStudyInvestigators()) {

            Investigator investigator = studyInvestigator.getSiteInvestigator().getInvestigator();
            // TODO  : search should be done on something else too
            String[] investigatorFirstAndLast = {investigator.getFirstName(), investigator.getLastName()};
            List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getBySubnames(investigatorFirstAndLast, organization.getId());
            if (siteInvestigators.size() > 0) {
                studyInvestigator.setSiteInvestigator(siteInvestigators.get(0));
                studyInvestigator.setStudyOrganization(studyOrganization);
            } else {
                //studyOrganization.getStudyInvestigators().remove(studyInvestigator);
                studyImportOutcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, "The selected investigator " +
                        investigator.getFirstName() + " " + investigator.getLastName() + " is not Valid ");
            }
        }
    }


    private void migrateStudyPersonnels(StudyOrganization studyOrganization,
                                        Organization organization, DomainObjectImportOutcome studyImportOutcome) {

        for (StudyPersonnel studyPersonnel : studyOrganization.getStudyPersonnels()) {
            ResearchStaff researchStaffer = studyPersonnel.getResearchStaff();
            // TODO : search should be done on something else too
            String[] investigatorFirstAndLast = {researchStaffer.getFirstName(), researchStaffer.getLastName()};
            List<ResearchStaff> researchStaffs = researchStaffDao.getBySubnames(investigatorFirstAndLast, organization.getId());

            if (researchStaffs.size() > 0) {
                ResearchStaff researchStaff = researchStaffs.get(0);
                studyPersonnel.setResearchStaff(researchStaff);
                studyPersonnel.setStudyOrganization(studyOrganization);
            } else {
                studyImportOutcome.ifNullObject(null, DomainObjectImportOutcome.Severity.ERROR, "The selected personnel " +
                        researchStaffer.getFirstName() + " " + researchStaffer.getLastName() + " is not Valid ");
            }
        }
    }


    /*
    * This is used in study
    */
    private void migrateOrganizationAssignedIdentifier(Study destination, Study source, DomainObjectImportOutcome studyImportOutcome) {

        if (source.getMultiInstitutionIndicator().booleanValue()) {
            String organizationName = source.getOrganizationAssignedIdentifier().getOrganization().getName();
            Organization organization = getOrganization(organizationName);

            destination.getOrganizationAssignedIdentifier().setOrganization(organization);
            destination.getOrganizationAssignedIdentifier().setValue(source.getOrganizationAssignedIdentifier().getValue());
            // On UI this is being done in Controller processFinish
            destination.addIdentifier(destination.getOrganizationAssignedIdentifier());
        }

    }

    private StudyAgent createStudyAgent(Agent agent) {

        StudyAgent studyAgent = new StudyAgent();
        studyAgent.setAgent(agent);
        return studyAgent;
    }

    private void studyUniquenessCheck(Study study, DomainObjectImportOutcome studyImportOutcome, DomainObjectImportOutcome.Severity severity) {

        for (Identifier identifier : study.getIdentifiers()) {
            Study tempStudy = getStudyDao().getByIdentifier(identifier);
            if (tempStudy != null) {
                studyImportOutcome.addErrorMessage(study.getClass().getSimpleName() + " identifier already exists. ", severity);
                break;
            }
        }
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setAgentDao(final AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public void setMeddraDao(final MedDRADao meddraDao) {
        this.meddraDao = meddraDao;
    }

    public void setCtcDao(final CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    public void setMeddraVersionDao(final MeddraVersionDao meddraVersionDao) {
        this.meddraVersionDao = meddraVersionDao;
    }

    public void setSiteInvestigatorDao(final SiteInvestigatorDao siteInvestigatorDao) {
        this.siteInvestigatorDao = siteInvestigatorDao;
    }

    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    public void setDiseaseTermDao(final DiseaseTermDao diseaseTermDao) {
        this.diseaseTermDao = diseaseTermDao;
    }

    public void setLowLevelTermDao(final LowLevelTermDao lowLevelTermDao) {
        this.lowLevelTermDao = lowLevelTermDao;
    }

    public void setInvestigationalNewDrugDao(final InvestigationalNewDrugDao investigationalNewDrugDao) {
        this.investigationalNewDrugDao = investigationalNewDrugDao;
    }
}
