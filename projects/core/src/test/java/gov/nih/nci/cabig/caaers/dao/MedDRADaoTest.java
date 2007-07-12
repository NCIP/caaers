package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.JdbcDaoTestCase;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
// @CaaersUseCases({ })
public class MedDRADaoTest extends JdbcDaoTestCase<MedDRADao>{

    public void testInsertLowLevelTerms() throws Exception {
    	//id,meddra_code,meddra_term,meddra_pt_id
    	String[] lowLevelTerm = {"122","122","322","122"};
    	List<String[]> llts = new ArrayList<String[]>();
    	llts.add(lowLevelTerm);
    	int[] result = getDao().insertLowLevelTerms(llts, 0);
    	assertEquals("Wrong Result Set", 1, result[0]);
        
    }
    
    public void testInsertPreferredTerms() throws Exception {
    	//id,meddra_code,meddra_term,meddra_soc_id
    	String[] preferredTerm = {"122","122","322","122"};
    	List<String[]> pts = new ArrayList<String[]>();
    	pts.add(preferredTerm);
    	int[] result = getDao().insertPreferredTerms(pts, 0);
    	assertEquals("Wrong Result Set", 1, result[0]);
        
    }
    
    public void testInsertHighLevelTerms() throws Exception {
    	//id,meddra_code,meddra_term
    	String[] highLevelTerm = {"122","122","322"};
    	List<String[]> hlts = new ArrayList<String[]>();
    	hlts.add(highLevelTerm);
    	int[] result = getDao().insertHighLevelTerms(hlts, 0);
    	assertEquals("Wrong Result Set", 1, result[0]);
        
    }
    
    public void testInsertHighLevelGroupTerms() throws Exception {
    	//id,meddra_code,meddra_term
    	String[] hlgt = {"122","122","322"};
    	List<String[]> hlgts = new ArrayList<String[]>();
    	hlgts.add(hlgt);
    	int[] result = getDao().insertHighLevelGroupTerms(hlgts, 0);
    	assertEquals("Wrong Result Set", 1, result[0]);
        
    }
    
    public void testInsertSystemOrganClasses() throws Exception {
    	//id,meddra_code,meddra_term
    	String[] soc = {"122","122","322"};
    	List<String[]> socs = new ArrayList<String[]>();
    	socs.add(soc);
    	int[] result = getDao().insertSystemOrganClasses(socs, 0);
    	assertEquals("Wrong Result Set", 1, result[0]);
        
    }
    
    public void testInsertHLTxPT() throws Exception {
    	//id,meddra_hlt_id ,meddra_pt_id
    	String[] hltpt = {"122","122","322"};
    	List<String[]> hltpts = new ArrayList<String[]>();
    	hltpts.add(hltpt);
    	int[] result = getDao().insertHLTxPT(hltpts, 0);
    	assertEquals("Wrong Result Set", 1, result[0]);
        
    }
    
    public void testInsertHLGTxHLT() throws Exception {
    	//id,meddra_code,meddra_term
    	String[] hlgthlt = {"122","122","322"};
    	List<String[]> hlgtlts = new ArrayList<String[]>();
    	hlgtlts.add(hlgthlt);
    	int[] result = getDao().insertHLGTxHLT(hlgtlts, 0);
    	assertEquals("Wrong Result Set", 1, result[0]);
        
    }
    public void testInsertSOCxHLGT() throws Exception {
    	//id,meddra_code,meddra_term
    	String[] sochlgt = {"122","122","322"};
    	List<String[]> sochlgts = new ArrayList<String[]>();
    	sochlgts.add(sochlgt);
    	int[] result = getDao().insertSOCxHLGT(sochlgts, 0);
    	assertEquals("Wrong Result Set", 1, result[0]);
        
    }
}
