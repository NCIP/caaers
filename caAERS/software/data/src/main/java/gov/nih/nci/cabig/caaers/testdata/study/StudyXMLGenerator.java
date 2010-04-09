package gov.nih.nci.cabig.caaers.testdata.study;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.webservice.Studies;
import gov.nih.nci.cabig.caaers.webservice.Study;

public class StudyXMLGenerator{
	
	public static String templateXML = "exportedstudy_6307.xml";
	public static Integer noOfXmls = 500;
	public static Integer studiesPerFile = 1;
	
    private static JAXBContext jaxbContext = null;
    private static Unmarshaller unmarshaller = null;

    /**
     * This method initialize's the Jaxbcontext & the unmarshaller
     */
    private static void initialize(){
    	try{
            jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
            unmarshaller = jaxbContext.createUnmarshaller();
    	}catch(Exception e){
    		
    	}
    }
    
	/**
	 * This method creates a InputStream for a given fileName
	 * @param testDataFileName
	 * @return
	 * @throws FileNotFoundException
	 */
    private static InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(StudyXMLGenerator.class.getPackage(), testDataFileName);
        return testDataStream;
    }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			//Initialize stuff
			initialize();
			
			//change and seralize the xml
			for(int i = 0; i<= noOfXmls; i++){
				
				gov.nih.nci.cabig.caaers.webservice.Studies studies = (Studies)unmarshaller.unmarshal(createInputStream(templateXML));
				Study study = studies.getStudy().get(0);
				
				Study newStudy = changeStudy(study, 
						String.format("Study Test - %3d", new Integer[]{i}),
						String.format("CTEP_TEST_%3d", new Integer[]{i}), 
						String.format("MAYO_TEST_%3d", new Integer[]{i}));
				studies.getStudy().add(newStudy);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static Study changeStudy(Study study , String title, String sponsorIdentifierValue, String ccIdentifierValue){
		return null;
	}
	
	/**
	 * This method should load the xml ({@link StudyXMLGenerator#templateXML}) to {@link Studies}
	 * @return
	 */
	public static Studies loadStudies(){
		return null;
	}
}
