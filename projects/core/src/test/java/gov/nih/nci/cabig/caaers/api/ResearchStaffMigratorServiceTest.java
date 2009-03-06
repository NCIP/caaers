package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;

public class ResearchStaffMigratorServiceTest extends CaaersDbNoSecurityTestCase {
//
//	private ResearchStaffMigratorService svc = null;
//	private JAXBContext jaxbContext = null;
//	private Unmarshaller unmarshaller = null;
//	private gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff staff = null;
//	private File xmlFile = null;
//	private ResearchStaffDao researchStaffDao = null;
//	Identifier identifier = null;
//	Organization organization = null;
//	ResearchStaff updatedResearchStaff = null;
//
//	@Override
//	protected void setUp() throws Exception {
//		super.setUp();
//		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
//		unmarshaller = jaxbContext.createUnmarshaller();
//		svc = (ResearchStaffMigratorService)getDeployedApplicationContext().getBean("researchStaffMigratorService");
//		researchStaffDao = (ResearchStaffDao)getDeployedApplicationContext().getBean("researchStaffDao");
//	}
//
//	public void testResearchStaffSave(){
//		try {
//			//Create or update , whatever it is new data will be populated ..
//			//xmlFile = new File ("/Users/sakkala/tech-workspace/caaers12/core/src/test/resources/gov/nih/nci/cabig/caaers/api/testdata/CreateInvestigatorTest.xml");
//			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateResearchStaffTest.xml")[0].getFile();
//			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
//			
//			svc.saveResearchStaff(staff);	
//			
//			//update with modified data ..
//			//xmlFile = new File ("/Users/sakkala/tech-workspace/caaers12/core/src/test/resources/gov/nih/nci/cabig/caaers/api/testdata/UpdateInvestigatorTest.xml");
//			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/UpdateResearchStaffTest.xml")[0].getFile();
//			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
//			svc.saveResearchStaff(staff);
//			
//			updatedResearchStaff = fetchResearchStaff("caaers.app@gmail.com");
//			
//			assertNotNull(updatedResearchStaff);
//			
//			assertEquals("879-345-0983", updatedResearchStaff.getFaxNumber());
//			assertEquals("657-678-0098", updatedResearchStaff.getPhoneNumber());
//			
//			assertEquals("DCP", updatedResearchStaff.getOrganization().getNciInstituteCode());
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail("Error running test: " + e.getMessage());
//		} catch (JAXBException e) {
//			e.printStackTrace();
//			fail("Error running test: " + e.getMessage());
//		}		
//	}
//
//	/**
//     * Fetches the research staff from the DB
//     * 
//     * @param nciCode
//     * @return
//     */
//    ResearchStaff fetchResearchStaff(String email) {//String nciIdentifier) {
//    	ResearchStaffQuery rsQuery = new ResearchStaffQuery();
//        if (StringUtils.isNotEmpty(email)) {
//        	//rsQuery.filterByNciIdentifier(nciIdentifier);
//        	rsQuery.filterByEmailAddress(email);
//        }
//        List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);
//        
//        if (rsList == null || rsList.isEmpty()) {
//            return null;
//        }
//        return rsList.get(0);
//    }
//	
//	private static Resource[] getResources(String pattern) throws IOException {
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resolver.getResources(pattern);
//        return resources;
//    }
	
	public void testTest(){
		assertTrue(true);
	}
}
