package gov.nih.nci.cabig.ctms.tools;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.CoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerminology;
import gov.nih.nci.cabig.caaers.domain.FundingSponsor;
import gov.nih.nci.cabig.caaers.domain.INDType;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.StudyImportServiceImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XLstudyImporter {

    /**
     * @param
     * @return void
     */
    File inputFile;

    POIFSFileSystem poifs;

    HSSFWorkbook wb;

    HSSFSheet studyInfoSheet;

    HSSFSheet agentInfoSheet;

    HSSFSheet diseaseInfoSheet;

    HSSFSheet tacInfoSheet;

    HSSFSheet orgInfoSheet;

    HSSFSheet investigatorInfoSheet;

    HSSFSheet therapyInfoSheet;

    // Study related objects
    Study study;

    OrganizationDao orgdao;

    CtcDao ctcdao;

    StudyDao studydao;

    AgentDao agentdao;

    InvestigationalNewDrugDao investigationalnewdrugdao;

    DiseaseTermDao diseasetermdao;

    InvestigatorDao investigatordao;

    StudyImportServiceImpl studyImportService;

    Organization primaryFundingSponsorOrganization;

    String primaryIdentifierString;

    Identifier primaryIdentifier;

    String localDocumentNumber;

    String studyTitle;

    String phaseCode;

    List<Identifier> identifiers;

    List<StudyOrganization> studyOrganizations;

    StudyFundingSponsor primaryStudyFundingSponsorOrganization;

    DiseaseTerminology diseaseTerminology;

    AeTerminology aeTerminology;

    DomainObjectImportOutcome<Study> studyImportOutcome;

    int rowCount = 0;

    private static ApplicationContext applicationContext = null;

    public final String STUDY_SHEET_NAME = "admin info";

    public final String AGENT_SHEET_NAME = "agent info";

    public final String DISEASE_SHEET_NAME = "disease info";

    public final String TAC_SHEET_NAME = "TAC info";

    public final String ORG_SHEET_NAME = "organizations";

    public final String INVESTIGATOR_SHEET_NAME = "investigators";

    public final String THERAPY_SHEET_NAME = "therapies";

    public XLstudyImporter() {
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File(
                            "C:/Documents and Settings/Jojo Singh/Desktop/caaers/Mayo-22-protocols.xls");
            applicationContext = new ClassPathXmlApplicationContext(
                            new String[] { "classpath*:gov/nih/nci/cabig/ctms/tools/applicationContext-XLStudyImport.xml" });
            XLstudyImporter importerObject = (XLstudyImporter) applicationContext
                            .getBean("XLstudyImporter");
            importerObject.setInputFile(inputFile);

            String identity = "ANONYMOUS";
            String info = "importStudy";
            gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo.setLocal(new DataAuditInfo(identity,
                            "localhost", new Date(), info));

            importerObject.importXLstudy();
            // private ParticipantDao dao = (ParticipantDao)
            // getApplicationContext().getBean("participantDao");

        }
        catch (Exception ex) {
            System.out.println("\n Error occured: ");
            ex.printStackTrace();
        }

    }

    public void importXLstudy() throws Exception {
        bootstrap();
        syncInvestigators();
        cleanStudies();
        boolean hasMoreStudies = true;

        while (rowCount > 0) {
            study = new Study();
            initializeStudy(study);
            setStudyAgents(study);
            setStudyDiseases(study);
            setStudyTreatmentAssignments(study);
            setStudyOrganizations(study);
            setStudyInvestigators(study);
            setStudyTherapies(study);
            validateStudy(study);
            // saveStudy(study);
            rowCount--;
            System.out.println(rowCount);
        }
    }

    private void bootstrap() throws Exception {

        poifs = new POIFSFileSystem(new FileInputStream(inputFile));

        // create a workbook out of the input stream
        wb = new HSSFWorkbook(poifs);
        studyInfoSheet = wb.getSheet(STUDY_SHEET_NAME);
        agentInfoSheet = wb.getSheet(AGENT_SHEET_NAME);
        diseaseInfoSheet = wb.getSheet(DISEASE_SHEET_NAME);
        tacInfoSheet = wb.getSheet(TAC_SHEET_NAME);
        orgInfoSheet = wb.getSheet(ORG_SHEET_NAME);
        investigatorInfoSheet = wb.getSheet(INVESTIGATOR_SHEET_NAME);
        therapyInfoSheet = wb.getSheet(THERAPY_SHEET_NAME);

        // orgdao = (OrganizationDao)applicationContext.getBean("orgdao");
        rowCount = studyInfoSheet.getLastRowNum();

    }

    private void initializeStudy(Study study) {
        /*
         * identifiers = new ArrayList<Identifier>();
         */
        primaryIdentifierString = getCellData(STUDY_SHEET_NAME, rowCount, studyInfoSheet.getRow(rowCount).getCell((short) 0));

        localDocumentNumber = getCellData(STUDY_SHEET_NAME, rowCount,studyInfoSheet.getRow(rowCount).getCell((short) 1));
        studyTitle = getCellData(STUDY_SHEET_NAME, rowCount,studyInfoSheet.getRow(rowCount).getCell((short) 2));
        phaseCode = getCellData(STUDY_SHEET_NAME, rowCount,studyInfoSheet.getRow(rowCount).getCell((short) 3));
        /*
         * primaryIdentifier = Identifier.createTemplate(
         * OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE, primaryIdentifierString);
         * primaryIdentifier.setPrimaryIndicator(Boolean.TRUE); identifiers.add(primaryIdentifier);
         * identifiers.add(Identifier.createTemplate(
         * OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE,
         * localDocumentNumber)); // Funding Sponsor for all studies is CTEP
         * primaryFundingSponsorOrganization = orgdao.getByName("Cancer Therapy Evaluation
         * Program"); primaryStudyFundingSponsorOrganization = new StudyFundingSponsor();
         * primaryStudyFundingSponsorOrganization.setOrganization(primaryFundingSponsorOrganization);
         * primaryStudyFundingSponsorOrganization.setStudy(study); studyOrganizations = new
         * ArrayList<StudyOrganization>();
         * studyOrganizations.add(primaryStudyFundingSponsorOrganization); FundingSponsor fs = new
         * FundingSponsor(); fs.setStudyFundingSponsor(primaryStudyFundingSponsorOrganization);
         * study.setFundingSponsor(fs); study.setIdentifiers(identifiers);
         * study.setStudyOrganizations(studyOrganizations);
         */

        FundingSponsor fs = new FundingSponsor();
        StudyFundingSponsor sfs = new StudyFundingSponsor();
        Organization fo = new Organization();
        fo.setName("Cancer Therapy Evaluation Program");
        sfs.setOrganization(fo);
        fs.setStudyFundingSponsor(sfs);
        OrganizationAssignedIdentifier sfi = new OrganizationAssignedIdentifier();
        sfi.setValue(getCellData(STUDY_SHEET_NAME, rowCount,studyInfoSheet.getRow(rowCount).getCell((short) 0)));
        sfi.setPrimaryIndicator(true);
        fs.setOrganizationAssignedIdentifier(sfi);
        study.setFundingSponsor(fs);

        study.setShortTitle(localDocumentNumber);
        study.setLongTitle(studyTitle);
        aeTerminology = study.getAeTerminology();
        // aeTerminology.setCtcVersion(getCtcdao().getCtcaeV3());
        Ctc ctc = new Ctc();
        ctc.setName("3");
        aeTerminology.setCtcVersion(ctc);
        study.setAeTerminology(aeTerminology);
        study.setDescription(studyTitle);
        diseaseTerminology = study.getDiseaseTerminology();
        diseaseTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.getByCode(1));
        study.setDiseaseTerminology(diseaseTerminology);
        study.setMultiInstitutionIndicator(true);
        study.setStatus(gov.nih.nci.cabig.caaers.domain.Study.STATUS_ACTIVE);
        study.setAdeersReporting(Boolean.TRUE);
        study.setPhaseCode(phaseCode);

    }

    private void setStudyAgents(Study study) {
        int agentRows = agentInfoSheet.getLastRowNum();
        List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
        StudyAgent studyAgent = null;
        INDType indType;
        String indNumber;
        List<StudyAgentINDAssociation> studyAgentINDAssociations;
        StudyAgentINDAssociation studyAgentINDAssociation;
        for (int agentRow = 0; agentRow <= agentRows; agentRow++) {
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(AGENT_SHEET_NAME, agentRow, agentInfoSheet
                            .getRow(agentRow).getCell((short) 0)))) {
                studyAgent = new StudyAgent(agentdao.getByNscNumber(getCellData(AGENT_SHEET_NAME, agentRow, agentInfoSheet
                                .getRow(agentRow).getCell((short) 2))));
                studyAgent.setStudy(study);
                indType = getCellData(AGENT_SHEET_NAME, agentRow, agentInfoSheet.getRow(agentRow).getCell((short) 4))
                                .equalsIgnoreCase("Investigational") ? INDType.CTEP_IND
                                : INDType.NA_COMMERCIAL;
                studyAgent.setIndType(indType);
                                
                                studyAgent.setPartOfLeadIND(getCellData(AGENT_SHEET_NAME, agentRow, (agentInfoSheet.getRow(agentRow).getCell((short) 3)))
                                .equalsIgnoreCase("Yes") ? true : false);
                indNumber = getCellData(AGENT_SHEET_NAME, agentRow, agentInfoSheet.getRow(agentRow).getCell((short) 6));
                if (!(indNumber.equalsIgnoreCase("null"))) {
                    studyAgentINDAssociation = new StudyAgentINDAssociation();
                    studyAgentINDAssociation.setStudyAgent(studyAgent);
                    studyAgentINDAssociation.setInvestigationalNewDrug(investigationalnewdrugdao
                                    .fetchCtepInd());
                    studyAgentINDAssociations = new ArrayList<StudyAgentINDAssociation>();
                    studyAgentINDAssociations.add(studyAgentINDAssociation);
                    studyAgent.setStudyAgentINDAssociations(studyAgentINDAssociations);

                }

                studyAgents.add(studyAgent);

            }
        }
        study.setStudyAgents(studyAgents);
    }

    private void setStudyDiseases(Study study) {
        int diseaseRows = diseaseInfoSheet.getLastRowNum();
        List<CtepStudyDisease> ctepStudyDiseases = new ArrayList<CtepStudyDisease>();
        CtepStudyDisease ctepStudyDisease = null;
        String diseaseTermString = null;
        boolean leadDisease;
        for (int diseaseRow = 0; diseaseRow <= diseaseRows; diseaseRow++) {
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(DISEASE_SHEET_NAME, diseaseRow, diseaseInfoSheet.getRow(
                            diseaseRow).getCell((short) 0)))) {
                diseaseTermString = getCellData(DISEASE_SHEET_NAME, diseaseRow, diseaseInfoSheet.getRow(diseaseRow).getCell(
                                (short) 1));
                ctepStudyDisease = new CtepStudyDisease();
                ctepStudyDisease
                                .setDiseaseTerm(diseasetermdao.getByCTEPTermName(diseaseTermString));
                leadDisease = getCellData(DISEASE_SHEET_NAME, diseaseRow, diseaseInfoSheet.getRow(diseaseRow).getCell((short) 2))
                                .equalsIgnoreCase("Yes") ? true : false;
                ctepStudyDisease.setLeadDisease(leadDisease);
                ctepStudyDiseases.add(ctepStudyDisease);

            }

        }
        study.setCtepStudyDiseases(ctepStudyDiseases);
    }

    private void setStudyTreatmentAssignments(Study study) {
        int tacRows = tacInfoSheet.getLastRowNum();
        List<TreatmentAssignment> treatmentAssignments = new ArrayList<TreatmentAssignment>();
        TreatmentAssignment treatmentAssignment;

        for (int tacRow = 0; tacRow <= tacRows; tacRow++) {
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(TAC_SHEET_NAME, tacRow, tacInfoSheet.getRow(tacRow)
                            .getCell((short) 0)))) {
                treatmentAssignment = new TreatmentAssignment();
                treatmentAssignment.setCode(getCellData(TAC_SHEET_NAME, tacRow, tacInfoSheet.getRow(tacRow).getCell(
                                (short) 1)));
                treatmentAssignment.setDescription(getCellData(TAC_SHEET_NAME, tacRow, tacInfoSheet.getRow(tacRow).getCell(
                                (short) 2)));
                treatmentAssignment.setStudy(study);
                treatmentAssignments.add(treatmentAssignment);

            }
        }
        study.setTreatmentAssignments(treatmentAssignments);
    }

    private void setStudyOrganizations(Study study) {
        int orgRows = orgInfoSheet.getLastRowNum();
        boolean leadOrg;
        StudyOrganization studyOrganization;
        Organization organization;
        /*
         * for (int orgRow = 0; orgRow <= orgRows; orgRow++) { if
         * (primaryIdentifierString.equalsIgnoreCase(getCellData(orgInfoSheet.getRow(orgRow)
         * .getCell((short) 0)))) { leadOrg =
         * getCellData(orgInfoSheet.getRow(orgRow).getCell((short) 1)) .equalsIgnoreCase("Lead") ?
         * true : false; organization =
         * orgdao.getByNCIcode(formatNCIcode(getCellData(orgInfoSheet.getRow(
         * orgRow).getCell((short) 3)))); if (leadOrg) { studyOrganization = new
         * StudyCoordinatingCenter(); } else { studyOrganization = new StudySite(); }
         * studyOrganization.setOrganization(organization);
         * study.addStudyOrganization(studyOrganization); } }
         */

        for (int orgRow = 0; orgRow <= orgRows; orgRow++) {
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(ORG_SHEET_NAME, orgRow, orgInfoSheet.getRow(orgRow)
                            .getCell((short) 0)))) {
                leadOrg = getCellData(ORG_SHEET_NAME, orgRow,orgInfoSheet.getRow(orgRow).getCell((short) 1))
                                .equalsIgnoreCase("Lead") ? true : false;
                organization = new Organization();
                organization.setName(getCellData(ORG_SHEET_NAME, orgRow,orgInfoSheet.getRow(orgRow).getCell((short) 2)));
                organization.setNciInstituteCode(getCellData(ORG_SHEET_NAME, orgRow,orgInfoSheet.getRow(orgRow).getCell(
                                (short) 3)));

                if (leadOrg) {
                    CoordinatingCenter cc = new CoordinatingCenter();
                    StudyCoordinatingCenter scc = new StudyCoordinatingCenter();
                    Organization cco = new Organization();
                    cco.setName(getCellData(ORG_SHEET_NAME, orgRow,orgInfoSheet.getRow(orgRow).getCell((short) 2)));
                    cco.setNciInstituteCode(getCellData(ORG_SHEET_NAME, orgRow,orgInfoSheet.getRow(orgRow).getCell(
                                    (short) 3)));
                    scc.setOrganization(cco);
                    cc.setStudyCoordinatingCenter(scc);
                    OrganizationAssignedIdentifier cci = new OrganizationAssignedIdentifier();
                    cci.setValue(localDocumentNumber);
                    cci.setPrimaryIndicator(false);
                    cc.setOrganizationAssignedIdentifier(cci);
                    study.setCoordinatingCenter(cc);

                }
                else {
                    StudySite ss = new StudySite();
                    ss.setOrganization(organization);
                    study.addStudySite(ss);
                }

            }

        }
    }

    private void setStudyTherapies(Study study) {
        int therapyRows = therapyInfoSheet.getLastRowNum();
        StudyTherapy studyTherapy;
        for (int therapyRow = 0; therapyRow <= therapyRows; therapyRow++) {
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(THERAPY_SHEET_NAME, therapyRow, therapyInfoSheet.getRow(
                            therapyRow).getCell((short) 0)))) {
                studyTherapy = new StudyTherapy();
                studyTherapy.setStudy(study);
                studyTherapy.setStudyTherapyType(getStudyTherapyType(getCellData(THERAPY_SHEET_NAME, therapyRow, therapyInfoSheet
                                .getRow(therapyRow).getCell((short) 1))));
                study.addStudyTherapy(studyTherapy);
            }
        }
    }

    private void setStudyInvestigators(Study study) {

        StudyCoordinatingCenter scc = study.getStudyCoordinatingCenter();
        setInvestigatorsInOrg(scc);
        for (StudySite ss : study.getStudySites()) {
            setInvestigatorsInOrg(ss);
        }
    }

    private void validateStudy(Study study) {
        studyImportOutcome = studyImportService.importStudy(study);
        List<DomainObjectImportOutcome.Message> messages = studyImportOutcome.getMessages();
        for (Message message : messages) {
            System.out.println("\n " + message.toString());

        }
        // study = studyImportOutcome.getImportedDomainObject();
        if (studyImportOutcome.isSavable()) saveStudy(studyImportOutcome.getImportedDomainObject());
    }

    private void saveStudy(Study study) {
        System.out.println("\n saving study" + study.getLongTitle());
        study.setDescription(study.getDescription() + "xxxx");
        studydao.save(study);
        /*
         * study = new Study(); study.setShortTitle("xxxx"); study.setLongTitle("Long Title
         * Inserted"); study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
         * study.setMultiInstitutionIndicator(Boolean.FALSE);
         * study.setAdeersReporting(Boolean.TRUE); studydao.save(study);
         */
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOrgdao(OrganizationDao orgdao) {
        this.orgdao = orgdao;
    }

    public CtcDao getCtcdao() {
        return ctcdao;
    }

    public void setCtcdao(CtcDao ctcdao) {
        this.ctcdao = ctcdao;
    }

    // utility method to get contents of cell irrespective of cell type.
    public String getCellData(String sheetname, int rowNum, HSSFCell cell) {
        int cellType = cell.getCellType(); 
        if(cellType == 0){ if(cell.getNumericCellValue()==0) throw new
         CaaersSystemException("Invalid data or Blank cell at following location: \n Sheet: "+sheetname+"\n Row: "+rowNum+"\n Cell: "+cell.getCellNum());
         return Integer.toString((int)
         cell.getNumericCellValue());}
         
        if (cellType == 1){ 
            if(cell.getStringCellValue().equals("")) throw new
            CaaersSystemException("Invalid data or Blank cell at following location: \n Sheet: "+sheetname+"\n Row: "+rowNum+"\n Cell: "+cell.getCellNum());
            return cell.getStringCellValue();}

        return "";

    }

    // Converts 4 digit nci codes to 5 digits to correctly map to DB. Does not apply to alphanumeric
    // strings.
    private String formatNCIcode(String nciCode) {

        if (nciCode.length() < 5) {
            try {
                int i = Integer.parseInt(nciCode);
                if (i / 10000 == 0) nciCode = '0' + nciCode;

            }
            catch (NumberFormatException nfe) {
            }

        }
        return nciCode;
    }

    // utility method to map therapy type values from excel sheet to StudyTherapyType enum
    private StudyTherapyType getStudyTherapyType(String studyTherapyString) {
        StudyTherapyType type = null;
        if (studyTherapyString.equalsIgnoreCase("Drug and/or Immunotherapy")) type = StudyTherapyType.DRUG_ADMINISTRATION;
        if (studyTherapyString.equalsIgnoreCase("Radiation Therapy")) type = StudyTherapyType.RADIATION;
        return type;
    }

    // create investigators if not already present in DB
    private void syncInvestigators() {
        int invRows = investigatorInfoSheet.getLastRowNum();
        List<Investigator> invList;
        Investigator inv;
        InvestigatorQuery iq;
        String invEmail;
        String invFax;
        String invPhone;
        SiteInvestigator siteInv;
        Organization org;
        String invNciId;
        String orgNciId;
        for (int invRow = 1; invRow <= invRows; invRow++) {
            iq = new InvestigatorQuery();
            invNciId = getCellData(INVESTIGATOR_SHEET_NAME, invRow, investigatorInfoSheet.getRow(invRow).getCell((short) 3));
            iq.filterByNciIdentifierExactMatch(invNciId);
            System.out
                            .println(getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow).getCell(
                                            (short) 3)));
            invList = investigatordao.searchInvestigator(iq);
            if (invList.size() == 0) {
                inv = new Investigator();
                inv.setNciIdentifier(invNciId);
                inv
                                .setLastName(getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow)
                                                .getCell((short) 4)));
                inv.setFirstName(getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow)
                                .getCell((short) 5)));
                invEmail = getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow).getCell((short) 6));
                if (invEmail.equalsIgnoreCase("null")) {
                    invEmail = "default@abc.com";
                }
                inv.setEmailAddress(invEmail);

                invPhone = getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow).getCell((short) 6));
                if (invPhone.equalsIgnoreCase("null")) {
                    invPhone = "(000)000-0000";
                }
                inv.setPhoneNumber(invPhone);

                invFax = getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow).getCell((short) 6));
                if (invFax.equalsIgnoreCase("null")) {
                    invFax = "(000)000-0000";
                }
                inv.setFaxNumber(invFax);
                siteInv = new SiteInvestigator();
                orgNciId = formatNCIcode(getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow).getCell(
                                (short) 1)));
                org = orgdao.getByNCIcode(orgNciId);
                siteInv.setOrganization(org);
                inv.addSiteInvestigator(siteInv);
                investigatordao.save(inv);
                System.out.println("\n created inv:" + inv.getId());

            }

        }

    }

    private void setInvestigatorsInOrg(StudyOrganization so) {
        int invRows = investigatorInfoSheet.getLastRowNum();
        StudyInvestigator studyInvestigator;
        SiteInvestigator siteInvestigator;
        Investigator investigator;
        String invStudyId;
        String invOrgId;

        for (int invRow = 1; invRow <= invRows; invRow++) {
            invStudyId = getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow).getCell((short) 0));
            invOrgId = getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow).getCell((short) 1));
            if (invStudyId.equalsIgnoreCase(primaryIdentifierString)
                            && invOrgId
                                            .equalsIgnoreCase(so.getOrganization()
                                                            .getNciInstituteCode())) {
                investigator = new Investigator();
                investigator.setFirstName(getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow)
                                .getCell((short) 5)));
                investigator.setLastName(getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow)
                                .getCell((short) 4)));
                investigator.setNciIdentifier(getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow)
                                .getCell((short) 3)));
                siteInvestigator = new SiteInvestigator();
                siteInvestigator.setInvestigator(investigator);
                studyInvestigator = new StudyInvestigator();
                studyInvestigator.setSiteInvestigator(siteInvestigator);
                studyInvestigator.setRoleCode(getCellData(INVESTIGATOR_SHEET_NAME, invRow,investigatorInfoSheet.getRow(invRow)
                                .getCell((short) 2)));
                studyInvestigator.setStatusCode("Active");
                so.addStudyInvestigators(studyInvestigator);
            }
        }

    }

    // bringing back DB to original state to facilitate testing
    private void cleanStudies() {
        for (int i = 1; i < studyInfoSheet.getLastRowNum(); i++) {
            Study s = studydao.getByShortTitle(getCellData(STUDY_SHEET_NAME, i,(studyInfoSheet.getRow(i)
                            .getCell((short) 1))));
            if (s != null) studydao.delete(s);
        }
    }

    public void setStudydao(StudyDao studydao) {
        this.studydao = studydao;
    }

    public void setAgentdao(AgentDao agentdao) {
        this.agentdao = agentdao;
    }

    public void setInvestigationalnewdrugdao(InvestigationalNewDrugDao investigationalnewdrugdao) {
        this.investigationalnewdrugdao = investigationalnewdrugdao;
    }

    public void setDiseasetermdao(DiseaseTermDao diseasetermdao) {
        this.diseasetermdao = diseasetermdao;
    }

    public void setInvestigatordao(InvestigatorDao investigatordao) {
        this.investigatordao = investigatordao;
    }

    public void setStudyImportService(StudyImportServiceImpl studyImportService) {
        this.studyImportService = studyImportService;
    }

}
