package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.MedDRADao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 
 * @author Krikor Krumlian
 */
public class MeddraAjaxFacade {
	
	private MeddraVersionDao meddraVersionDao;
	
	private MeddraVersion meddraVersion;
	
    private MedDRADao meddraDao;

    private LowLevelTermDao lltDao;
    
    private TransactionTemplate txTemplate;
    
    private PlatformTransactionManager transactionManager;
    
    private Map<String,Integer> socCodeToIdMap;
    
    private Map<String,Integer> ptCodeToIdMap;
    
    private Map<String,Integer> hlgtCodeToIdMap;
    
    private Map<String,Integer> hltCodeToIdMap;
     
    private String[] files = { "soc.asc", "hlgt.asc", "soc_hlgt.asc", "hlt.asc", "hlgt_hlt.asc", "pt.asc", "hlt_pt.asc", "llt.asc"};
    
    private static final String MEDDRA_SOC_TABLE = "meddra_soc";
    
    private static final String MEDDRA_HLGT_TABLE = "meddra_hlgt";
    
    private static final String MEDDRA_HLT_TABLE = "meddra_hlt";
    
    private static final String MEDDRA_PT_TABLE = "meddra_pt";

    public String handleMedDRADelete(final int version_id){
    	// Transaction handling code
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setName("deleteTransaction");
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	
    	TransactionStatus status = transactionManager.getTransaction(def);
    	try{
    		meddraDao.deleteMeddraConcepts(version_id);
    	}catch(Exception e){
    		transactionManager.rollback(status);
    		return "Failed";
    	}
    	transactionManager.commit(status);
    	
    	return "Success";
    }
    
    public String handleMedDRA(String path, int step, String meddra_name) {
        File theFile = getFile(path, step);
        List<String[]> al = new ArrayList<String[]>();
        String message = "Done";
        
        // Before beginning check if all the files exist
        if(step == 0){
        	String fileCheckStatus = testFiles(path);
        	if(!fileCheckStatus.equals("Success"))
        		return "Meddra concept file " + fileCheckStatus + " doesn't exist. Please verify";
        }
        
        
        // Check for duplicate
        // Create a new meddra_version in meddra_versions table with name = meddra_name
        // The id will come from the meddra_versions_id_seq next value.
        // Meddra Version will be created only for the first call.
        List<MeddraVersion> meddraVersionsList = new ArrayList<MeddraVersion>();
        if(step == 0){
        	meddraVersionsList = meddraVersionDao.getMeddraByName(meddra_name);
            if(meddraVersionsList.size() > 0)
            	return "Duplicate version name.";
        	meddraDao.createMeddraVersion(meddra_name);
        }
        // We need to get the version_id of the new meddra_version created.
        // This is needed for loading other concepts like SOC, HLGT, HLT, PT, LLT.
        meddraVersionsList = meddraVersionDao.getMeddraByName(meddra_name);
        MeddraVersion meddraVersion = meddraVersionsList.get(0);
        int version_id = 0;
        if(meddraVersion != null)
        	version_id = meddraVersion.getId();
        
        if(version_id == 0)
        	return "Importing Meddra Version: " + meddra_name + " failed.";
        
        // declared here only to make visible to finally clause
        BufferedReader input = null;
        try {
            // use buffering, reading one line at a time
            // FileReader always assumes default encoding is OK!
            input = new BufferedReader(new FileReader(theFile));
            String line;

            while ((line = input.readLine()) != null) {
                // al.add(line.split("\\$"));
                if (line.length() > 0) al.add(line.split("\\$"));
            }

            int start = 0;
            int loopEnd = 0;
            int end = al.size();
            int increment = 5000;

            while (true) {
                loopEnd = start + increment < end ? start + increment : start + (end - start);
                if (step == 0) {
                	int[] kk = meddraDao.insertSystemOrganClasses(al.subList(start, loopEnd), start, version_id);
                }
                if (step == 1) {
                	int[] kk = meddraDao.insertHighLevelGroupTerms(al.subList(start, loopEnd), start, version_id);
                }
                if (step == 2) {
                	int[] kk = meddraDao.insertSOCxHLGT(al.subList(start, loopEnd), start, version_id, socCodeToIdMap, hlgtCodeToIdMap);
                }
                if (step == 3) {
                	int[] kk = meddraDao.insertHighLevelTerms(al.subList(start, loopEnd), start, version_id);
                }
                if (step == 4) {
                	int[] kk = meddraDao.insertHLGTxHLT(al.subList(start, loopEnd), start, version_id,  hlgtCodeToIdMap, hltCodeToIdMap);
                }
                if (step == 5) {
                	int[] kk = meddraDao.insertPreferredTerms(al.subList(start, loopEnd), start, version_id, socCodeToIdMap);
                }
                if (step == 6) {
                	int[] kk = meddraDao.insertHLTxPT(al.subList(start, loopEnd), start, version_id, hltCodeToIdMap, ptCodeToIdMap);
                }
                if (step == 7) {
                	int[] kk = meddraDao.insertLowLevelTerms(al.subList(start, loopEnd), start, version_id, ptCodeToIdMap);
                }
                start = start + increment;
                if (loopEnd == end) {
                    break;
                }
            }
             
            // Populate the SOCCodeToIdMap and keep it ready before loading PT
            if(step == 0){
            	socCodeToIdMap = meddraDao.populateCodeToIdMap(MEDDRA_SOC_TABLE,version_id);
            }
            
            // Populate the hlgtCodeToIdMap and keep it ready before loading other tables
            if(step == 1){
            	hlgtCodeToIdMap = meddraDao.populateCodeToIdMap(MEDDRA_HLGT_TABLE, version_id);
            }
            
            // Populate the hltCodeToIdMap and keep it ready before loading other tables
            if(step == 3){
            	hltCodeToIdMap = meddraDao.populateCodeToIdMap(MEDDRA_HLT_TABLE, version_id);
            }
            
            // Populate the PTCodeToIdMap and keep it ready before loading LLT
            if(step == 5){
            	ptCodeToIdMap = meddraDao.populateCodeToIdMap(MEDDRA_PT_TABLE,version_id);
            }
        } catch (EOFException ex) {
            System.out.println("EOF Reached");
            return "There was an error while importing the Meddra Version.";
        } catch (FileNotFoundException ex) {
        	return "There was an error while imporintg the Meddra Version.";
            //throw new RuntimeException("File Not found Exception", ex);
        } catch (IOException ex) {
        	return "There was an error while importing the Meddra Version.";
            //throw new RuntimeException("IO Exception", ex);
        } finally {
            try {
                if (input != null) {
                    // flush and close both "input" and its underlying FileReader
                    input.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException("IO Exception", ex);
            }
        }
        return (message);
    }

    // //// CONFIGURATION

    private File getFile(String path, int step) {
        path = path.trim();
        File file = path.contains("/") ? new File(path + "/" + files[step]) : new File(path + "\\"
                        + files[step]);
        return file;
    }
    
    private String testFiles(String path){
    	path = path.trim();
    	for(int step = 0; step < files.length; step++){
    		File file = path.contains("/") ? new File(path + "/" + files[step]) : new File(path + "\\"
                + files[step]);
    		if(!file.exists())
    			return files[step];
    	}
    	return "Success";
    }

    @Required
    public MedDRADao getMeddraDao() {
        return meddraDao;
    }

    public void setMeddraDao(MedDRADao meddraDao) {
        this.meddraDao = meddraDao;
    }

    public LowLevelTermDao getLltDao() {
        return lltDao;
    }

    public void setLltDao(LowLevelTermDao lltDao) {
        this.lltDao = lltDao;
    }

    public MeddraVersionDao getMeddraVersionDao(){
    	return meddraVersionDao;
    }
    
    public void setMeddraVersionDao(MeddraVersionDao meddraVersionDao){
    	this.meddraVersionDao = meddraVersionDao;
    }
    
    public MeddraVersion getMeddraVersion(){
    	return meddraVersion;
    }
    
    public void setMeddraVersion(MeddraVersion meddraVersion){
    	this.meddraVersion = meddraVersion;
    }
    
    /*public void setDsTransactionManager(DataSourceTransactionManager dsTransactionManager){
    	this.dsTransactionManager = dsTransactionManager;
    }*/
    
    public void setTransactionTemplate(TransactionTemplate txTemplate){
    	DataSourceTransactionManager dsTransactionManager = new DataSourceTransactionManager();
    	this.txTemplate = new TransactionTemplate(dsTransactionManager);
    	this.txTemplate.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
    }
    
    public void setTransactionManager(PlatformTransactionManager transactionManager){
    	this.transactionManager = transactionManager;
    }
}
