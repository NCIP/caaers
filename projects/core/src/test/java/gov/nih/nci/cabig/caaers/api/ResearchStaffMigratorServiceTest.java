package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;

public class ResearchStaffMigratorServiceTest extends CaaersDbTestCase {


//    public String getTestDataFileName() {
//        String fileName = "testdata/ResearchStaffMigratorServiceTest.xml";
//        return fileName;
//    }
    
	public void testSaveResearchStaff() {
		ResearchStaffMigratorService svc = (ResearchStaffMigratorService) getApplicationContext()
        .getBean("researchStaffMigratorService");
		
//		try {
////            String fileName = "src/test/resources/gov/nih/nci/cabig/caaers/api/testdata/ResearchStaffMigratorServiceTest.xml";
////
////            File xmlFile = new File (fileName);//getResources("/schema/integration/investigator.xml")[0].getFile();
////			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
////			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
////			Staff staff = (Staff)unmarshaller.unmarshal(xmlFile);
////
////			svc.saveResearchStaff(staff);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Error running test: " + e.getMessage());
//		}
	}
	/*
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }*/
}
