package gov.nih.nci.cabig.caaers.testdata.study;

import gov.nih.nci.cabig.caaers.webservice.Studies;
import gov.nih.nci.cabig.caaers.webservice.Study;

public class StudyXMLGenerator {
	
	public static String templateXML = "exportedstudy_6307.xml";
	public static Integer noOfXmls = 500;
	public static Integer studiesPerFile = 1;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//load studies
		Studies studies = loadStudies();
		
		//load the first study
		Study study = studies.getStudy().get(0);
		
		//change and seralize the xml
		for(int i = 0; i<= noOfXmls; i++){
			Study changedStudy = changeStudy(study, 
					String.format("Study Test - %3d", new Integer[]{i}),
					String.format("CTEP_TEST_%3d", new Integer[]{i}), 
					String.format("MAYO_TEST_%3d", new Integer[]{i}));
			
			
			
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
