package gov.nih.nci.cabig.caaers.dataimport;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyAgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.TerminologyRepository;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.*;

/*
* @author Ion C. Olaru
*
* */
public class AgentSpecificTermsImporter {

    private static final Log logger = LogFactory.getLog(AgentSpecificTermsImporter.class);
    private static final String KEY_MISSING_TERMS = "missingTerms";
    private static final String KEY_PROCESSED_AGENTS = "processedAgents";
    private static final String KEY_PROCESSED_AGENTTERMS = "processedAgentTerms";
    private static final String KEY_MISSING_AGENTS = "missingAgents";
    private static final String KEY_DUPLICATE_AGENT_TERMS = "duplicateAgentTerms";

	private AgentDao agentDao;
    private TerminologyRepository terminologyRepository;
    private AgentSpecificTermDao agentSpecificTermDao;
    private AgentSpecificAdverseEventListService asaelService;
    private StudyAgentDao studyAgentDao;
    private StudyDao studyDao;

    private Map<String, Short> headers = new HashMap() {{
        put("NSC", -1);
        put("CTCAE_VERSION", -1);
        put("CTCAE_CATEGORY", -1);
        put("AE_TERM", -1);
        put("OTHER_TOXICITY", -1);
    }};

    public static File file = new File("/home/dell/Desktop/asael.xls");

    public AgentSpecificTermsImporter() {
    }

    public AgentSpecificTermsImporter(File f) {
        file = f;
    }

    public static void main(String[] args) throws Exception {
        AgentSpecificTermsImporter im = new AgentSpecificTermsImporter(file);
        im.importFile();
    }

    public Map<String, Object> importFile() throws Exception {

        POIFSFileSystem poifs;
        HSSFWorkbook wb;
        HSSFSheet sh = null;

        boolean isExcel = file.getName().endsWith(".xls");
        boolean isCSV = file.getName().endsWith(".csv");

        Map<String, Object> results = new HashMap<String, Object>();
        int rowCount = 0;
        int columnsCount = 0;
        Map<String, Agent> agents = new HashMap<String, Agent>();
        Map<String, Agent> missingAgents = new HashMap<String, Agent>();
        Set<String> missingTerms = new HashSet<String>();
        Map<String, String> asaelCache = new HashMap<String, String>();
        int asael;

        // System.out.println("Starting...");

        // wipe out the table
        agentSpecificTermDao.deleteAll();
        studyDao.deleteAllExpectedTerms();
        // if (true) return null;

        // get needed headers
        if (isExcel) {
            poifs = new POIFSFileSystem(new FileInputStream(file));
            wb = new HSSFWorkbook(poifs);
            sh = wb.getSheetAt(0);
            rowCount = sh.getLastRowNum();
            columnsCount = sh.getRow(0).getLastCellNum();

            for (byte i = 0; i<columnsCount; i++) {
                HSSFCell cell = sh.getRow(0).getCell(i);
                if (headers.containsKey(cell.getStringCellValue())) {
                    headers.remove(cell.getStringCellValue());
                    headers.put(cell.getStringCellValue(), Short.valueOf(i));
                }
            }
        }

        InputStream ir = null;
        Reader r = null;
        BufferedReader br = null;
        
        if (isCSV) {
            // readLines
            rowCount = 0;
            ir = new FileInputStream(file);
            r = new InputStreamReader(ir);
            br = new BufferedReader(r);
            String s = br.readLine();
            while(s != null) {
                if (rowCount == 0) {
                    String[] _s = s.split("[\\|]{1}");
                    for (byte j=0; j<_s.length; j++) {
                        // System.out.println(_s[j]);
                        if (headers.containsKey(_s[j])) {
                            headers.remove(_s[j]);
                            headers.put(_s[j], Short.valueOf(j));
                        }
                    }
                }
                rowCount++;
                s = br.readLine();
            }
            br.close();
            r.close();
            ir.close();

            ir = new FileInputStream(file);
            r = new InputStreamReader(ir);
            br = new BufferedReader(r);
        }

/*
        System.out.println(rowCount);
        for (Map.Entry e : headers.entrySet()) {
            System.out.println(e.getKey() + "=>" + e.getValue());
        }
*/

        agents.clear();
        missingTerms.clear();
        missingAgents.clear();
        asael = 0;
        int duplicateAgentTerms = 0;

        //
        String nsc = "";
        String ctcae_category = "";
        int ctcae_version = 0;
        String ae_term = "";
        String other_toxicity = "";

        // Loading ASAE list
        // if (true) {  return null; }

        int i = 1;
        while (i <= rowCount) {

            nsc = "";
            
                if (isExcel) {
                    HSSFRow row = sh.getRow(i);
                    if (row != null) {
                        nsc = getCellData("", i, row.getCell((short)headers.get("NSC")));
                        ctcae_category= getCellData("", i, row.getCell((short)headers.get("CTCAE_CATEGORY")));
                        ctcae_version = Integer.parseInt(getCellData("", i, row.getCell((short)headers.get("CTCAE_VERSION"))));
                        ae_term = getCellData("", i, row.getCell((short)headers.get("AE_TERM")));
                        other_toxicity = getCellData("", i, row.getCell((short)headers.get("OTHER_TOXICITY")));
                    }
                } else {
                    String s;
                    s = br.readLine();
                    if (s != null) {
                        String[] _s = s.split("[\\|]{1}");
                        if (i >1 && _s.length > 1) { 
                            nsc = _s[headers.get("NSC")];
                            ctcae_category = _s[headers.get("CTCAE_CATEGORY")];
                            try {
                                ctcae_version = _s[headers.get("CTCAE_VERSION")].trim() != "" ? Integer.valueOf(_s[headers.get("CTCAE_VERSION")]).intValue() : 0;
                            } catch (NumberFormatException e) {
//                                System.out.println(s);
                                return null;
                            }
                            ae_term = _s[headers.get("AE_TERM")];
                            if (_s.length -1 >= headers.get("OTHER_TOXICITY")) other_toxicity = _s[headers.get("OTHER_TOXICITY")]; else other_toxicity = "";
                        }
                    }
                }

                if (nsc.trim().equals("")) { i++; continue; } else {
                    // System.out.println(String.format("%s). NSC:%s,   V:%s,   C:%s,   T:%s", i, nsc, ctcae_version, ctcae_category, ae_term));
                }

                Agent a = agents.get(nsc);
                if (a == null) {
                    a = agentDao.getByNscNumber(nsc);
                    // System.out.println(asael + ". OK. Found agent [" + a.getName() + "] for NSC: [" + nsc + "]");
                    agents.put(nsc, a);
                }
                
                if (a != null) {
                    AgentSpecificCtcTerm t = new AgentSpecificCtcTerm();
                    t.setAgent(a);
                    t.setOtherToxicity(other_toxicity);

                    List<CtcTerm> list = terminologyRepository.getCtcTerm(ctcae_category, ctcae_version, ae_term);
                    if (list.size() == 0) {
                        // System.out.println("<ERROR>: Term not found: " + ae_term + ", Category: " + ctcae_category + ", CTCAE Version: " + ctcae_version);
                        missingTerms.add("Term not found: " + ae_term + ", Category: " + ctcae_category + ", CTCAE Version: " + ctcae_version);
                    } else {
                        t.setCtcTerm(list.get(0));
                        if (persistASAE(t)) asael++; else duplicateAgentTerms++;
                    }

                    agentSpecificTermDao.evict(t);

                } else {
                    if (!missingAgents.containsKey(nsc)) {
                        // System.out.println("<ERROR>: The agent was not found by its NSC: " + nsc);
                        missingAgents.put(nsc, null);
                    }
                }

            i++;
        }

        if (isCSV) {
            br.close();
            r.close();
            ir.close();
        }
        
        results.put(KEY_MISSING_TERMS, missingTerms);
        results.put(KEY_PROCESSED_AGENTS, agents.size() - missingAgents.size());
        results.put(KEY_PROCESSED_AGENTTERMS, asael);
        results.put(KEY_MISSING_AGENTS, missingAgents);
        results.put(KEY_DUPLICATE_AGENT_TERMS, duplicateAgentTerms);

        return results;
    }

    private boolean isAgentSpecificTermPersisted(AgentSpecificTerm at) {
        List<AgentSpecificCtcTerm> l = agentSpecificTermDao.getCtcTerm(at.getAgent().getId(), at.getTerm().getId());
        if (l.size() > 0) return true;
        return false;
    }

    /*
    * Persisting ASAE list
    * */
    private boolean persistASAE(AgentSpecificTerm t) {
        if (!isAgentSpecificTermPersisted(t)) {
            agentSpecificTermDao.save(t);
            List<StudyAgent> l = getStudyAgentDao().getByAgentID(t.getAgent().getId());
            // System.out.println("Agent is associated to " + l.size() + "studies");
            for (StudyAgent s : l) {
                asaelService.synchronizeStudyWithAgentTerm(s.getStudy(), t);
                //System.out.println("Study has " + s.getStudy().getExpectedAECtcTerms().size() + "Expected AEs");
                studyDao.save(s.getStudy());
                studyAgentDao.evict(s);
            }
            return true;
        }
        return false;
    }

    private String getCellData(String sheetname, int rowNum, HSSFCell cell) {
        if (cell == null) return "";
        int cellType = cell.getCellType();
        if (cellType == 0) {
            if (cell.getNumericCellValue() == 0) return "";
            return (Integer.toString((int) cell.getNumericCellValue())).trim();
        } else if (cellType == 1) {
            return cell.getStringCellValue().trim();
        } else {
            return "";
        }
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public void setTerminologyRepository(TerminologyRepository tRepository) {
        this.terminologyRepository = tRepository;
    }

    public AgentSpecificTermDao getAgentSpecificTermDao() {
        return agentSpecificTermDao;
    }

    public void setAgentSpecificTermDao(AgentSpecificTermDao agentSpecificTermDao) {
        this.agentSpecificTermDao = agentSpecificTermDao;
    }

    public AgentSpecificAdverseEventListService getAsaelService() {
        return asaelService;
    }

    public void setAsaelService(AgentSpecificAdverseEventListService asaelService) {
        this.asaelService = asaelService;
    }

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        AgentSpecificTermsImporter.file = file;
    }

    public StudyAgentDao getStudyAgentDao() {
        return studyAgentDao;
    }

    public void setStudyAgentDao(StudyAgentDao studyAgentDao) {
        this.studyAgentDao = studyAgentDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }
}
