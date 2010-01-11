package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.impl.DefaultInvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.DefaultResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ImportTab extends Tab<ImportCommand>{
	
	private static final Log logger = LogFactory.getLog(ImportTab.class);
	private ImporterFactory importerFactory;
	private MeddraVersionDao meddraVersionDao;
	private static final String STUDY_IMPORT = "study";
	private static final String SUBJECT_IMPORT = "participant";
	private static final String RESEARCH_STAFF_IMPORT = "researchStaff";
	private static final String INVESTIGATOR_IMPORT = "investigator";
	private static final String ORGANIZATION_IMPORT = "organization";
	private static final String AGENT_IMPORT = "agent";
	private static final String MEDDRA_IMPORT = "medDRA";
	private static final String SOC_EXPECTED_FILE = "soc.asc";
	private static final String HLGT_EXPECTED_FILE = "hlgt.asc";
	private static final String HLT_EXPECTED_FILE = "hlt.asc";
	private static final String PT_EXPECTED_FILE = "pt.asc";
	private static final String LLT_EXPECTED_FILE = "llt.asc";
	private static final String SOC_HLGT_EXPECTED_FILE = "soc_hlgt.asc";
	private static final String HLGT_HLT_EXPECTED_FILE = "hlgt_hlt.asc";
	private static final String HLT_PT_EXPECTED_FILE = "hlt_pt.asc";
	
	public ImportTab(String longTitle, String shortTitle, String viewName){
		super(longTitle, shortTitle, viewName);
	}
	
    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        refdata.put("action", "New");
        // refdata.put("willSave", false);
        return refdata;
    }

    @Override
    public void validate(ImportCommand command, Errors errors) {
    	if(command.getType() == null || command.getType().equals("")){
    		errors.rejectValue("type", "IMP_002");
    		return;
    	}else{
    		if(!command.getType().equals(MEDDRA_IMPORT)){
    			if(command.getDataFile() == null || command.getDataFile().isEmpty()){
    				errors.rejectValue("dataFile", "IMP_002");
    				return;
    			}
    		}else{
    			if(fileInputMissing(command.getSocFile()) || fileInputMissing(command.getHlgtFile()) ||
    					fileInputMissing(command.getHltFile()) || fileInputMissing(command.getPtFile()) ||
    					fileInputMissing(command.getLltFile()) || fileInputMissing(command.getHlgtHltFile()) ||
    					fileInputMissing(command.getHltPtFile()) || fileInputMissing(command.getSocHlgtFile())){
    				errors.rejectValue("dataFile", "IMP_002");
    				return;
    			}
    			
    			// Check for duplicate
    	        // Create a new meddra_version in meddra_versions table with name = meddra_name
    	        // The id will come from the meddra_versions_id_seq next value.
    			if(command.getMeddraVersionName() == null || command.getMeddraVersionName().equals("")){
    				errors.rejectValue("dataFile", "IMP_002");
    				return;
    			}else{
    				List<MeddraVersion> meddraVersionsList = new ArrayList<MeddraVersion>();
    				meddraVersionsList = meddraVersionDao.getMeddraByName(command.getMeddraVersionName());
    					if(meddraVersionsList.size() > 0)
    						errors.rejectValue("dataFile", "IMP_003");
    			}
    			
    			// Check for valid file names
    			if(!command.getSocFile().getOriginalFilename().equals(SOC_EXPECTED_FILE) ||
    					!command.getHlgtFile().getOriginalFilename().equals(HLGT_EXPECTED_FILE) ||
    					!command.getHltFile().getOriginalFilename().equals(HLT_EXPECTED_FILE) ||
    					!command.getPtFile().getOriginalFilename().equals(PT_EXPECTED_FILE) ||
    					!command.getLltFile().getOriginalFilename().equals(LLT_EXPECTED_FILE) ||
    					!command.getSocHlgtFile().getOriginalFilename().equals(SOC_HLGT_EXPECTED_FILE) ||
    					!command.getHlgtHltFile().getOriginalFilename().equals(HLGT_HLT_EXPECTED_FILE) ||
    					!command.getHltPtFile().getOriginalFilename().equals(HLT_PT_EXPECTED_FILE))
    				errors.rejectValue("dataFile", "IMP_004");
    		}
    	}
    }
    
    private boolean fileInputMissing(MultipartFile file){
    	if(file == null || file.isEmpty())
    		return true;
    	else
    		return false;
    }
    
    @Override
    public void postProcess(HttpServletRequest request, ImportCommand command, Errors errors) {
        // TODO: see why the command variable type has a comma attached to it
    	if(!errors.hasErrors())
    		handleLoad(command, command.getType().replace(',', ' ').trim());
    }

    private void handleLoad(ImportCommand command, String type) {

        BufferedReader input = null;
        try {
            File xmlFile = File.createTempFile("file", "uploaded");
            FileCopyUtils.copy(command.getDataFile().getInputStream(),
                            new FileOutputStream(xmlFile));
            Importer importer = importerFactory.createImporterInstance(type);
        	importer.processEntities(xmlFile, command);
            
        } catch (EOFException ex) {
            System.out.println("EndOfFile Reached");
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File Not found Exception", ex);
        } catch (IOException ex) {
            throw new RuntimeException("IO Exception", ex);
        } finally {
            try {
                if (input != null) {
                    // flush and close both "input" and its underlying FileReader
                    input.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException("IO Exception", ex);
            }

            logger.debug("Study List size " + command.getImportableStudies().size());
            logger.debug("Participant List size " + command.getImportableParticipants().size());
        }
    }
        
	public ImporterFactory getImporterFactory(){
		return importerFactory;
	}
	
	public void setImporterFactory(ImporterFactory importerFactory){
		this.importerFactory = importerFactory;
	}
	
	public void setMeddraVersionDao(MeddraVersionDao meddraVersionDao){
		this.meddraVersionDao = meddraVersionDao;
	}
	
	public MeddraVersionDao getMeddraVersionDao(){
		return meddraVersionDao;
	}
}
