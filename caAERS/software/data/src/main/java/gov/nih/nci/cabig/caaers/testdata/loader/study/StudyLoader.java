package gov.nih.nci.cabig.caaers.testdata.loader.study;

import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.loader.DataLoader;
import gov.nih.nci.cabig.caaers.utils.XmlValidator;
import gov.nih.nci.cabig.caaers.webservice.Studies;
import gov.nih.nci.cabig.caaers.webservice.Study;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileInputStream;

/**
 * This class will load study.
 * Used to load test data.
 * @author: Biju Joseph
 */
public class StudyLoader extends DataLoader {
     StudyProcessorImpl processor ;

    public StudyLoader(ApplicationContext appContext) throws Exception{
      this(appContext, TestDataFileUtils.getStudyTestDataFolder().getPath());
    }

    public StudyLoader(ApplicationContext appContext, String loc) throws Exception{
        super(appContext, loc, "gov.nih.nci.cabig.caaers.webservice");
        processor = (StudyProcessorImpl) appContext.getBean("studyProcessor");
    }

    /**
     * Will load each study in the target db. 
     * @throws Exception
     */
    @Override
    public boolean loadFile(File f, StringBuffer detailsBuffer) throws Exception{

        //validate file.
       boolean valid = XmlValidator.validateAgainstSchema(TestDataFileUtils.getContent(f), "classpath:schema/integration/StudySchema.xsd", detailsBuffer);
       if(!valid) return false;
       Studies studies = getStudies(f);
       boolean loadStatus = true;
       for(Study s : studies.getStudy()){
           DomainObjectImportOutcome<gov.nih.nci.cabig.caaers.domain.Study> outcome =  processor.processStudy(s);
           loadStatus &= outcome.hasErrors();
           if(outcome.hasErrors()) detailsBuffer.append(outcome.toString()).append("\n");
       }    

       return loadStatus;

    }


	/**
	 *
	 * @return StudyType, template read from the file.
	 * @throws Exception
	 */
	private Studies getStudies(File f) throws Exception{
		return (Studies)unmarshaller.unmarshal(new FileInputStream(f));

    }



}
