package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

/**
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@CaaersUseCases( { MAPPING_VOCAB })
public class MedDRADaoTest extends CaaersDbNoSecurityTestCase {
	

	public void testCreateMeddraVersion() throws Exception{
		//String meddra_name = "TestMeddra";
		//getDao().createMeddraVersion(meddra_name);
	}
	
	/*public void testInsertSystemOrganClasses() throws Exception {
        // id,meddra_code,meddra_term
        String[] soc1 = { "122", "122", "322" };
        List<String[]> socs = new ArrayList<String[]>();
        socs.add(soc1);
        int version_id = 2;
        getDao().insertSystemOrganClasses(socs, 0, version_id);
    }

	   public void testInsertHighLevelGroupTerms() throws Exception {
	        // id,meddra_code,meddra_term
	        String[] hlgt = { "122", "122", "322" };
	        List<String[]> hlgts = new ArrayList<String[]>();
	        hlgts.add(hlgt);
	        int version_id = 1;
	        getDao().insertHighLevelGroupTerms(hlgts, 0, version_id);

	    }
	
	   public void testInsertSOCxHLGT() throws Exception {
	        // id,meddra_code,meddra_term
	        String[] sochlgt = { "122", "122", "322" };
	        List<String[]> sochlgts = new ArrayList<String[]>();
	        sochlgts.add(sochlgt);
	        int version_id = 1;
	        Map<String,Integer> socCodeToIdMap = new HashMap<String,Integer>();
	        Map<String,Integer> hlgtCodeToIdMap = new HashMap<String,Integer>();
	        socCodeToIdMap.put("122", 1);
	        hlgtCodeToIdMap.put("122", 1);
	        
	        getDao().insertSOCxHLGT(sochlgts, 0, version_id, socCodeToIdMap, hlgtCodeToIdMap);

	    }

	    public void testInsertHighLevelTerms() throws Exception {
	        // id,meddra_code,meddra_term
	        String[] highLevelTerm = { "122", "122", "322" };
	        List<String[]> hlts = new ArrayList<String[]>();
	        hlts.add(highLevelTerm);
	        int version_id = 1;
	        getDao().insertHighLevelTerms(hlts, 0, version_id);

	    }

	    public void testInsertHLGTxHLT() throws Exception {
	        // id,meddra_code,meddra_term
	        String[] hlgthlt = { "122", "122", "322" };
	        List<String[]> hlgtlts = new ArrayList<String[]>();
	        hlgtlts.add(hlgthlt);
	        int version_id = 1;
	        Map<String,Integer> hlgtCodeToIdMap = new HashMap<String,Integer>();
	        Map<String,Integer> hltCodeToIdMap = new HashMap<String,Integer>();
	        hlgtCodeToIdMap.put("122", 1);
	        hltCodeToIdMap.put("122", 1);
	        getDao().insertHLGTxHLT(hlgtlts, 0, version_id, hlgtCodeToIdMap, hltCodeToIdMap);

	    }

	    public void testInsertPreferredTerms() throws Exception {
	        // id,meddra_code,meddra_term,meddra_soc_id
	        String[] preferredTerm = { "122", "122", "322", "122" };
	        List<String[]> pts = new ArrayList<String[]>();
	        pts.add(preferredTerm);
	        int version_id = 1;
	        Map<String,Integer> codeToIdMap = new HashMap<String,Integer>();
	        codeToIdMap.put("122", 1);
	        getDao().insertPreferredTerms(pts, 0, version_id, codeToIdMap);

	    }
	    
	    public void testInsertHLTxPT() throws Exception {
	        // id,meddra_hlt_id ,meddra_pt_id
	        String[] hltpt = { "122", "122", "322" };
	        List<String[]> hltpts = new ArrayList<String[]>();
	        hltpts.add(hltpt);
	        int version_id = 1;
	        Map<String,Integer> hltCodeToIdMap = new HashMap<String,Integer>();
	        Map<String,Integer> ptCodeToIdMap = new HashMap<String,Integer>();
	        hltCodeToIdMap.put("122", 1);
	        ptCodeToIdMap.put("122", 1);
	        getDao().insertHLTxPT(hltpts, 0, version_id, hltCodeToIdMap, ptCodeToIdMap);

	    }

    public void testInsertLowLevelTerms() throws Exception {
        // id,meddra_code,meddra_term,meddra_pt_id
        String[] lowLevelTerm = { "122", "122", "322", "122" };
        List<String[]> llts = new ArrayList<String[]>();
        llts.add(lowLevelTerm);
        int version_id = 1;
        Map<String,Integer> codeToIdMap = new HashMap<String,Integer>();
        codeToIdMap.put("122", 1);
        getDao().insertLowLevelTerms(llts, 0, version_id, codeToIdMap);

    }*/
}
