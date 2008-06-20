package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * Test case to test convrsion of jaxb study object to domain study object and call to studymigrator with study domain object.
 * @author Monish Dombla
 *
 */
public class StudyProcessorTest extends CaaersTestCase {
	/*
	private StudyProcessor studyProcessor = null;
	
	public void testProcessStudy(){
		studyProcessor = (StudyProcessor)getDeployedApplicationContext().getBean("studyProcessor");
		File xmlFile;
		gov.nih.nci.cabig.caaers.webservice.Studies studies;
		
		try {
			xmlFile = getResources("/schema/integration/NewFile1.xml")[0].getFile();
			
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				
			studies = (gov.nih.nci.cabig.caaers.webservice.Studies)unmarshaller.unmarshal(xmlFile);
			
			List<gov.nih.nci.cabig.caaers.webservice.Study> studyList = studies.getStudy();
			
			if(studyList!=null && !studyList.isEmpty()){
				Iterator<gov.nih.nci.cabig.caaers.webservice.Study> iterator = studyList.iterator();
				while(iterator.hasNext()){
					gov.nih.nci.cabig.caaers.webservice.Study studyDto = iterator.next();
					studyProcessor.syncStudy(studyDto);
				}
			}
			
			//studyProcessor.processStudy(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
    */
	
}
