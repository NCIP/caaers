package gov.nih.nci.cabig.ctms.tools;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerminology;
import gov.nih.nci.cabig.caaers.domain.INDType;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
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

    int rowCount = 0;

    private static ApplicationContext applicationContext = null;

    // public XLstudyImporter(File inputFile) {
    // this.inputFile = inputFile;
    // orgdao=new OrganizationDao();
    // }

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
            // validateStudy(Study study);
            // saveStudy(new Study());
            rowCount--;
            System.out.println(rowCount);
        }
    }

    private void bootstrap() throws Exception {

        poifs = new POIFSFileSystem(new FileInputStream(inputFile));

        // create a workbook out of the input stream
        wb = new HSSFWorkbook(poifs);
        studyInfoSheet = wb.getSheet("admin info");
        agentInfoSheet = wb.getSheet("agent info");
        diseaseInfoSheet = wb.getSheet("disease info");
        tacInfoSheet = wb.getSheet("TAC info");
        orgInfoSheet = wb.getSheet("organizations");
        investigatorInfoSheet = wb.getSheet("investigators");
        therapyInfoSheet = wb.getSheet("therapies");

        // orgdao = (OrganizationDao)applicationContext.getBean("orgdao");
        rowCount = studyInfoSheet.getLastRowNum();
        
    }

    private void initializeStudy(Study study) {

        identifiers = new ArrayList<Identifier>();
        primaryIdentifierString = getCellData(studyInfoSheet.getRow(rowCount).getCell((short) 0));
        localDocumentNumber = getCellData(studyInfoSheet.getRow(rowCount).getCell((short) 1));
        studyTitle = getCellData(studyInfoSheet.getRow(rowCount).getCell((short) 2));
        phaseCode = getCellData(studyInfoSheet.getRow(rowCount).getCell((short) 3));
        primaryIdentifier = Identifier.createTemplate(
                        OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE,
                        primaryIdentifierString);
        primaryIdentifier.setPrimaryIndicator(Boolean.TRUE);
        identifiers.add(primaryIdentifier);
        identifiers.add(Identifier.createTemplate(
                        OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE,
                        localDocumentNumber));

        // Funding Sponsor for all studies is CTEP
        primaryFundingSponsorOrganization = orgdao.getByName("Cancer Therapy Evaluation Program");
        primaryStudyFundingSponsorOrganization = new StudyFundingSponsor();
        primaryStudyFundingSponsorOrganization.setOrganization(primaryFundingSponsorOrganization);
        studyOrganizations = new ArrayList<StudyOrganization>();
        studyOrganizations.add(primaryStudyFundingSponsorOrganization);

        study.setIdentifiers(identifiers);
        study.setStudyOrganizations(studyOrganizations);
        study.setShortTitle(localDocumentNumber);
        study.setLongTitle(studyTitle);
        aeTerminology = new AeTerminology();
        aeTerminology.setCtcVersion(getCtcdao().getCtcaeV3());
        study.setAeTerminology(aeTerminology);
        study.setDescription(studyTitle);
        diseaseTerminology = new DiseaseTerminology();
        diseaseTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.getByCode(1));
        study.setDiseaseTerminology(diseaseTerminology);
        study.setMultiInstitutionIndicator(true);
        study.setStatus(gov.nih.nci.cabig.caaers.domain.Study.STATUS_ACTIVE);
        study.setAdeersReporting(Boolean.TRUE);
        study.setPhaseCode(phaseCode);

        // System.out.println("\n Finished study: "+primaryIdentifierString+" --
        // "+studyTitle.toString());

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
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(agentInfoSheet
                            .getRow(agentRow).getCell((short) 0)))) {
                studyAgent = new StudyAgent(agentdao.getByNscNumber(getCellData(agentInfoSheet
                                .getRow(agentRow).getCell((short) 2))));
                indType = getCellData(agentInfoSheet.getRow(agentRow).getCell((short) 4))
                                .equalsIgnoreCase("Investigational") ? INDType.CTEP_IND
                                : INDType.NA_COMMERCIAL;
                studyAgent.setIndType(indType);
                studyAgent.setPartOfLeadIND(getCellData(
                                (agentInfoSheet.getRow(agentRow).getCell((short) 3)))
                                .equalsIgnoreCase("Yes") ? true : false);
                indNumber = getCellData(agentInfoSheet.getRow(agentRow).getCell((short) 6));
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
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(diseaseInfoSheet.getRow(
                            diseaseRow).getCell((short) 0)))) {
                diseaseTermString = getCellData(diseaseInfoSheet.getRow(diseaseRow).getCell(
                                (short) 1));
                ctepStudyDisease = new CtepStudyDisease();
                ctepStudyDisease
                                .setDiseaseTerm(diseasetermdao.getByCTEPTermName(diseaseTermString));
                leadDisease = getCellData(diseaseInfoSheet.getRow(diseaseRow).getCell((short) 2))
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
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(tacInfoSheet.getRow(tacRow)
                            .getCell((short) 0)))) {
                treatmentAssignment = new TreatmentAssignment();
                treatmentAssignment.setCode(getCellData(tacInfoSheet.getRow(tacRow).getCell(
                                (short) 1)));
                treatmentAssignment.setDescription(getCellData(tacInfoSheet.getRow(tacRow).getCell(
                                (short) 2)));
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
        for (int orgRow = 0; orgRow <= orgRows; orgRow++) {
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(orgInfoSheet.getRow(orgRow)
                            .getCell((short) 0)))) {
                leadOrg = getCellData(orgInfoSheet.getRow(orgRow).getCell((short) 1))
                                .equalsIgnoreCase("Lead") ? true : false;
                // organization =
                // orgdao.getByNCIcode(getCellData(orgInfoSheet.getRow(orgRow).getCell((short)3)));
                organization = orgdao.getByNCIcode(formatNCIcode(getCellData(orgInfoSheet.getRow(
                                orgRow).getCell((short) 3))));
                if (leadOrg) {
                    studyOrganization = new StudyCoordinatingCenter();
                }
                else {
                    studyOrganization = new StudySite();
                }
                studyOrganization.setOrganization(organization);
                study.addStudyOrganization(studyOrganization);
                System.out.println("\n study:" + primaryIdentifierString + " org:"
                                + studyOrganization.getRoleName() + " : "
                                + studyOrganization.getOrganization().getName());
            }

        }

    }

    private void setStudyTherapies(Study study) {
        int therapyRows = therapyInfoSheet.getLastRowNum();
        StudyTherapy studyTherapy;
        for (int therapyRow = 0; therapyRow <= therapyRows; therapyRow++) {
            if (primaryIdentifierString.equalsIgnoreCase(getCellData(therapyInfoSheet.getRow(
                            therapyRow).getCell((short) 0)))) {
                studyTherapy = new StudyTherapy();
                studyTherapy.setStudyTherapyType(getStudyTherapyType(getCellData(therapyInfoSheet
                                .getRow(therapyRow).getCell((short) 1))));
                study.addStudyTherapy(studyTherapy);
                //System.out.println("\n study:" + primaryIdentifierString + " therapy:"
                               // + studyTherapy.getStudyTherapyType().getDisplayName());
            }
        }
    }

    private void setStudyInvestigators(Study study) {
        syncInvestigators();
       // int investigatorRows = investigatorInfoSheet.getLastRowNum();
        // StudyTherapy studyTherapy;

    }

    private void saveStudy(Study study) {
        study = new Study();
        study.setShortTitle("xxxx");
        study.setLongTitle("Long Title Inserted");
        //study.setAeTerminology();
        study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
        study.setMultiInstitutionIndicator(Boolean.FALSE);
        study.setAdeersReporting(Boolean.TRUE);
        studydao.save(study);

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
    public String getCellData(HSSFCell cell) {
        int cellType = cell.getCellType();
        if (cellType == 0) return Integer.toString((int) cell.getNumericCellValue());

        if (cellType == 1) return cell.getStringCellValue();

        return "";

    }

    // Converts 4 digit nci codes to 5 digits to correctly map to DB. Does not apply to alphanumeric
    // strings.
    private String formatNCIcode(String nciCode) {

            if (nciCode.length()<5) {
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
        /*for (int invRow = 0; invRow <= invRows; invRow++) {
            iq = new InvestigatorQuery();
            iq.filterByNciIdentifierExactMatch(getCellData(investigatorInfoSheet.getRow(invRow).getCell((short)3)));
            invList = investigatordao.searchInvestigator(iq);
            if(invList.size()==0){
                inv = new Investigator();
                inv.setNciIdentifier(nciIdentifier);
                inv.setFirstName(firstName);
                inv.setLastName(lastName);
                inv.setEmailAddress(emailAddress);
                inv.setFaxNumber(faxNumber); 
          
                investigatordao.save(inv);
                System.out.println("\n created inv:"+inv.getId());
                
            }
                
        }*/

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
}
