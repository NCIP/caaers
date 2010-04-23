package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import gov.nih.nci.cabig.caaers.service.StudyImportServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* @author Ion C. Olaru
* 
* */
public class AgentSpecificTermsImporter {


	private POIFSFileSystem poifs;
	private HSSFWorkbook wb;
	private HSSFSheet sh;

	private CtcDao ctcdao;
	private StudyDao studydao;
	private AgentDao agentdao;
	private InvestigationalNewDrugDao investigationalnewdrugdao;
	private DiseaseTermDao diseasetermdao;
	private InvestigatorDao investigatordao;
	private InvestigatorRepository investigatorRepository;
	private StudyImportServiceImpl studyImportService;
	private String primaryIdentifierString;
	private String localDocumentNumber;
	private String studyTitle;
	private String phaseCode;
	private DiseaseTerminology diseaseTerminology;
	private AeTerminology aeTerminology;
	private DomainObjectImportOutcome<Study> studyImportOutcome;

    private int rowCount = 0;
    private int columnsCount = 0;
    
    private final String STUDY_SHEET_NAME = "admin info";
    private final String AGENT_SHEET_NAME = "agent info";

    private Map<String, Short> headers = new HashMap() {{
        put("NSC", -1);
        put("CTCAE_VERSION", -1);
        put("CTCAE_CATEGORY", -1);
        put("AE_TERM", -1);
    }};

    public static File file = new File("/home/dell/Desktop/asael.xls");

    public AgentSpecificTermsImporter(File f) {
        file = f;
    }

    public static void main(String[] args) throws Exception {
        AgentSpecificTermsImporter im = new AgentSpecificTermsImporter(file);
        im.importFile();
    }

    public void importFile() throws Exception {
        bootstrap(file);

        int i = 1;
        while (i <= rowCount) {

            HSSFRow row = sh.getRow(i);
            if (row != null) {
                String nsc = getCellData("", i, row.getCell((short)headers.get("NSC")));
                String ctcae_version = getCellData("", i, row.getCell((short)headers.get("CTCAE_VERSION")));
                String ctcae_category = getCellData("", i, row.getCell((short)headers.get("CTCAE_CATEGORY")));
                String ae_term = getCellData("", i, row.getCell((short)headers.get("AE_TERM")));
                System.out.println(String.format("NSC:%s,   V:%s,   C:%s,   T:%s", nsc, ctcae_version, ctcae_category, ae_term));
            }
            i++;
        }
        
        System.out.println("Excel import Complete");
    }

    private void bootstrap(File inputFile) throws Exception {
        poifs = new POIFSFileSystem(new FileInputStream(inputFile));

        // create a workbook out of the input stream
        wb = new HSSFWorkbook(poifs);
        sh = wb.getSheetAt(0);
        rowCount = sh.getLastRowNum();
        columnsCount = sh.getRow(0).getLastCellNum();

        // get needed headers
        for (byte i = 0; i<columnsCount; i++) {
            HSSFCell cell = sh.getRow(0).getCell(i);
            if (headers.containsKey(cell.getStringCellValue())) {
                headers.remove(cell.getStringCellValue());
                headers.put(cell.getStringCellValue(), Short.valueOf(i));
            }
        }

        for (Map.Entry e : headers.entrySet()) {
            System.out.println(String.format("Header: %s, Position: %d", e.getKey(), Integer.parseInt(e.getValue().toString())));
        }

    }

    private void initializeStudy(Study study) {

    	primaryIdentifierString = getCellData(STUDY_SHEET_NAME, rowCount,
                sh.getRow(rowCount).getCell((short) 0));

        localDocumentNumber = getCellData(STUDY_SHEET_NAME, rowCount,
                sh.getRow(rowCount).getCell((short) 1));
        studyTitle = getCellData(STUDY_SHEET_NAME, rowCount, sh
                .getRow(rowCount).getCell((short) 2));
        phaseCode = getCellData(STUDY_SHEET_NAME, rowCount, sh
                .getRow(rowCount).getCell((short) 3));

        FundingSponsor fs = new FundingSponsor();
        StudyFundingSponsor sfs = new StudyFundingSponsor();
        Organization fo = new LocalOrganization();
        fo.setName("Cancer Therapy Evaluation Program");
        sfs.setOrganization(fo);
        fs.setStudyFundingSponsor(sfs);
        OrganizationAssignedIdentifier sfi = new OrganizationAssignedIdentifier();
        sfi.setValue(getCellData(STUDY_SHEET_NAME, rowCount, sh
                .getRow(rowCount).getCell((short) 0)));
        sfi.setPrimaryIndicator(true);
        fs.setOrganizationAssignedIdentifier(sfi);
        study.setFundingSponsor(fs);

        study.setShortTitle(localDocumentNumber);
        study.setLongTitle(studyTitle);
        aeTerminology = study.getAeTerminology();
        Ctc ctc = new Ctc();
        ctc.setName(getCellData(STUDY_SHEET_NAME, rowCount, sh
                .getRow(rowCount).getCell((short) 4)));
        aeTerminology.setCtcVersion(ctc);
        study.setAeTerminology(aeTerminology);
        study.setDescription(studyTitle);
        diseaseTerminology = study.getDiseaseTerminology();
        diseaseTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.getByCode(1));
        study.setDiseaseTerminology(diseaseTerminology);
        study.setMultiInstitutionIndicator(true);
        study.setStatus(Study.STATUS_ACTIVE);
        study.setAdeersReporting(Boolean.TRUE);
        study.setPhaseCode(phaseCode);

    }

   private void saveStudy(Study study) {
        study.setDescription(study.getDescription() + "xxxx");
        if(fetchStudy(study) == null){
        	System.out.println("Study with Short Title " + study.getShortTitle() + " Created in caAERS ");
        	studydao.save(study);
        }else{
        	System.out.println("Study with Short Title " + study.getShortTitle() + " Exists in caAERS ");
        }
    }


    // utility method to get contents of cell irrespective of cell type.
    private String getCellData(String sheetname, int rowNum, HSSFCell cell) {
        if (cell == null)
            throw new CaaersSystemException(
                    "Invalid data or Blank cell at following location: \n Sheet: "
                            + sheetname + "\n Row: " + (rowNum + 1));

        int cellType = cell.getCellType();
        if (cellType == 0) {
            if (cell.getNumericCellValue() == 0)
                throw new CaaersSystemException(
                        "Invalid data or Blank cell at following location: \n Sheet: "
                                + sheetname + "\n Row: " + (rowNum + 1)
                                + "\n Cell: " + ((cell.getCellNum()) + 1));
            return (Integer.toString((int) cell.getNumericCellValue())).trim();
        } else if (cellType == 1) {
            if (cell.getStringCellValue().equals(""))
                throw new CaaersSystemException(
                        "Invalid data or Blank cell at following location: \n Sheet: "
                                + sheetname + "\n Row: " + (rowNum + 1)
                                + "\n Cell: " + ((cell.getCellNum()) + 1));
            return cell.getStringCellValue().trim();
        } else {
            throw new CaaersSystemException(
                    "Invalid data or Blank cell at following location: \n Sheet: "
                            + sheetname + "\n Row: " + (rowNum + 1)
                            + "\n Cell: " + ((cell.getCellNum()) + 1));
        }

    }

    // Converts 4 digit nci codes to 5 digits to correctly map to DB. Does not
    // apply to alphanumeric
    // strings.
    private String formatNCIcode(String nciCode) {

        if (nciCode.length() < 5) {
            try {
                int i = Integer.parseInt(nciCode);
                if (i / 10000 == 0)
                    nciCode = '0' + nciCode;

            } catch (NumberFormatException nfe) {
            }

        }
        return nciCode;
    }

    // utility method to map therapy type values from excel sheet to
    // StudyTherapyType enum
    private StudyTherapyType getStudyTherapyType(String studyTherapyString) {
        StudyTherapyType type = null;
        if (studyTherapyString.equalsIgnoreCase("Drug and/or Immunotherapy"))
            type = StudyTherapyType.DRUG_ADMINISTRATION;
        if (studyTherapyString.equalsIgnoreCase("Radiation Therapy"))
            type = StudyTherapyType.RADIATION;
        return type;
    }


    private HSSFSheet getSheet(String sheetName) {
        HSSFSheet sheet = wb.getSheet(sheetName);
        if (sheet == null)
            throw new CaaersSystemException("\n Unable to find sheet named: " + sheetName + "\n Please fix the error and try again.");
        else
            return sheet;
    }

    /**
	 * This method fetches a Study from the DB based identifiers.
	 * @param importedStudy
	 * @return
	 */
	private Study fetchStudy(Study importedStudy){
		Study dbStudy = null;
		for (Identifier identifier : importedStudy.getIdentifiers()) {
            dbStudy = studydao.getStudyDesignByIdentifier(identifier);
            if(dbStudy != null){
            	break;
            }
            studydao.evict(dbStudy);
        }
		return dbStudy;
	}

    /*
      * // bringing back DB to original state to facilitate testing private void
      * cleanStudies() { for (int i = 1; i < sh.getLastRowNum(); i++) {
      * Study s = studydao.getByShortTitle(getCellData(STUDY_SHEET_NAME,
      * i,(sh.getRow(i) .getCell((short) 1)))); if (s != null)
      * studydao.delete(s); } }
      */
    public void setStudydao(StudyDao studydao) {
        this.studydao = studydao;
    }

    public void setAgentdao(AgentDao agentdao) {
        this.agentdao = agentdao;
    }

    public void setInvestigationalnewdrugdao(
            InvestigationalNewDrugDao investigationalnewdrugdao) {
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