package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.MedDRADao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sameer Sawant
 */
public class MeddraImporter extends Importer{

	private MedDRADao medDRADao;
	private MeddraVersionDao meddraVersionDao;
	private static Logger logger = Logger.getLogger(MeddraImporter.class);
	
	private static final String MEDDRA_SOC_TABLE = "meddra_soc";
    private static final String MEDDRA_HLGT_TABLE = "meddra_hlgt";
    private static final String MEDDRA_HLT_TABLE = "meddra_hlt";
    private static final String MEDDRA_PT_TABLE = "meddra_pt";
    
    private static final String SOC = "soc";
    private static final String HLGT = "hlgt";
    private static final String HLT = "hlt";
    private static final String PT = "pt";
    private static final String LLT = "llt";
    private static final String SOCxHLGT = "socxhlgt";
    private static final String HLGTxHLT = "hlgtxhlt";
    private static final String HLTxPT = "hltxpt";
	
	private Map<String,Integer> socCodeToIdMap;
    private Map<String,Integer> ptCodeToIdMap;
    private Map<String,Integer> hlgtCodeToIdMap;
    private Map<String,Integer> hltCodeToIdMap;
    
	
	public void MeddraImporter(){
		
	}
	
	public void processEntities(File xmlFile,ImportCommand command){
		//NOTE. This is the place to add any validation that needs to be done on the 
		//meddra concept files imported to the system.
		//Here we will copy the contents of the form files to the dataFiles.
		command.setSocDataFileName(copyFiles(command.getSocFile(), SOC));
		command.setHlgtDataFileName(copyFiles(command.getHlgtFile(), HLGT));
		command.setSocHlgtDataFileName(copyFiles(command.getSocHlgtFile(), SOCxHLGT));
		command.setHltDataFileName(copyFiles(command.getHltFile(), HLT));
		command.setHlgtHltDataFileName(copyFiles(command.getHlgtHltFile(), HLGTxHLT));
		command.setPtDataFileName(copyFiles(command.getPtFile(), PT));
		command.setHltPtDataFileName(copyFiles(command.getHltPtFile(), HLTxPT));
		command.setLltDataFileName(copyFiles(command.getLltFile(), LLT));
	}
	
	private String copyFiles(MultipartFile multipartFile, String conceptName){
		try{
			File dataFile = File.createTempFile(conceptName + "prefix", "uploaded");
			FileCopyUtils.copy(multipartFile.getInputStream(),
			               new FileOutputStream(dataFile));
			return dataFile.getAbsolutePath();
		}catch(IOException e){
			logger.debug(e.getMessage());
			return null;
		}
	}
	
	public void save(ImportCommand command, HttpServletRequest request){
		
		// First of all save the MeddraVersion name in the MeddraVersions table
        medDRADao.createMeddraVersion(command.getMeddraVersionName());

        // We need to get the version_id of the new meddra_version created.
        // This is needed for loading other concepts like SOC, HLGT, HLT, PT, LLT.
        List<MeddraVersion> meddraVersionsList = new ArrayList<MeddraVersion>();
        meddraVersionsList = meddraVersionDao.getMeddraByName(command.getMeddraVersionName());
        MeddraVersion meddraVersion = meddraVersionsList.get(0);
        int version_id = 0;
        if(meddraVersion != null)
        	version_id = meddraVersion.getId();
        
        //TODO - if version_id == 0, then the meddraVersion was not saved(created). Need to handle this condition.
        //We will insert the concepts in the right order.
        insertConcept(SOC, command.getSocDataFileName(), version_id);
        insertConcept(HLGT, command.getHlgtDataFileName(), version_id);
        insertConcept(SOCxHLGT, command.getSocHlgtDataFileName(), version_id);
        insertConcept(HLT, command.getHltDataFileName(), version_id);
        insertConcept(HLGTxHLT, command.getHlgtHltDataFileName(), version_id);
        insertConcept(PT, command.getPtDataFileName(), version_id);
        insertConcept(HLTxPT, command.getHltPtDataFileName(), version_id);
        insertConcept(LLT, command.getLltDataFileName(), version_id);
	}
	
	public void insertConcept(String conceptName, String dataFileName, int version_id){
	
		// declared here only to make visible to finally clause
        BufferedReader input = null;
        List<String[]> al = new ArrayList<String[]>();
        try {
            // use buffering, reading one line at a time
            // FileReader always assumes default encoding is OK!
        	//File conceptFile = File.createTempFile(conceptName, "uploaded");
            //FileCopyUtils.copy(dataFile.getInputStream(),
             //               new FileOutputStream(conceptFile));
            //input = new BufferedReader(new InputStreamReader(dataFile.getInputStream()));
            input = new BufferedReader(new FileReader(new File(dataFileName)));
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
                if (conceptName.equals(SOC)) {
                	int[] kk = medDRADao.insertSystemOrganClasses(al.subList(start, loopEnd), start, version_id);
                }
                if (conceptName.equals(HLGT)) {
                	int[] kk = medDRADao.insertHighLevelGroupTerms(al.subList(start, loopEnd), start, version_id);
                }
                if (conceptName.equals(SOCxHLGT)) {
                	int[] kk = medDRADao.insertSOCxHLGT(al.subList(start, loopEnd), start, version_id, socCodeToIdMap, hlgtCodeToIdMap);
                }
                if (conceptName.equals(HLT)) {
                	int[] kk = medDRADao.insertHighLevelTerms(al.subList(start, loopEnd), start, version_id);
                }
                if (conceptName.equals(HLGTxHLT)) {
                	int[] kk = medDRADao.insertHLGTxHLT(al.subList(start, loopEnd), start, version_id,  hlgtCodeToIdMap, hltCodeToIdMap);
                }
                if (conceptName.equals(PT)) {
                	int[] kk = medDRADao.insertPreferredTerms(al.subList(start, loopEnd), start, version_id, socCodeToIdMap);
                }
                if (conceptName.equals(HLTxPT)) {
                	int[] kk = medDRADao.insertHLTxPT(al.subList(start, loopEnd), start, version_id, hltCodeToIdMap, ptCodeToIdMap);
                }
                if (conceptName.equals(LLT)) {
                	int[] kk = medDRADao.insertLowLevelTerms(al.subList(start, loopEnd), start, version_id, ptCodeToIdMap);
                }
                start = start + increment;
                if (loopEnd == end) {
                    break;
                }
            }
            
         // Populate the SOCCodeToIdMap and keep it ready before loading PT
            if(conceptName.equals(SOC)){
            	socCodeToIdMap = medDRADao.populateCodeToIdMap(MEDDRA_SOC_TABLE,version_id);
            }
            
            // Populate the hlgtCodeToIdMap and keep it ready before loading other tables
            if(conceptName.equals(HLGT)){
            	hlgtCodeToIdMap = medDRADao.populateCodeToIdMap(MEDDRA_HLGT_TABLE, version_id);
            }
            
            // Populate the hltCodeToIdMap and keep it ready before loading other tables
            if(conceptName.equals(HLT)){
            	hltCodeToIdMap = medDRADao.populateCodeToIdMap(MEDDRA_HLT_TABLE, version_id);
            }
            
            // Populate the PTCodeToIdMap and keep it ready before loading LLT
            if(conceptName.equals(PT)){
            	ptCodeToIdMap = medDRADao.populateCodeToIdMap(MEDDRA_PT_TABLE,version_id);
            }
            
        }catch (EOFException ex) {
            //return "There was an error while importing the Meddra Version.";
        } catch (FileNotFoundException ex) {
        	//return "There was an error while imporintg the Meddra Version.";
            //throw new RuntimeException("File Not found Exception", ex);
        } catch (IOException ex) {
        	//return "There was an error while importing the Meddra Version.";
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

	}

	public MedDRADao getmedDRADao() {
		return medDRADao;
	}

	public void setmedDRADao(MedDRADao medDRADao) {
		this.medDRADao = medDRADao;
	}

	public MeddraVersionDao getMeddraVersionDao() {
		return meddraVersionDao;
	}

	public void setMeddraVersionDao(MeddraVersionDao meddraVersionDao) {
		this.meddraVersionDao = meddraVersionDao;
	}

}