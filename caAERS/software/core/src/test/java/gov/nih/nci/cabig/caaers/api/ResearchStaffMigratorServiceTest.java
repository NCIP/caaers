package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


public class ResearchStaffMigratorServiceTest extends CaaersDbNoSecurityTestCase {

	private ResearchStaffMigratorService svc = null;
	private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff staff = null;
	private File xmlFile = null;

	private ResearchStaffRepository researchStaffRepository= null;
	Identifier identifier = null;
	Organization organization = null;
	ResearchStaff updatedResearchStaff = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
		unmarshaller = jaxbContext.createUnmarshaller();
		svc = (ResearchStaffMigratorService)getDeployedApplicationContext().getBean("researchStaffMigratorService");
		researchStaffRepository = (ResearchStaffRepository)getDeployedApplicationContext().getBean("researchStaffRepository");
	}

	public void testResearchStaffByLoginIdSave(){
		try {
			//Create or update , whatever it is new data will be populated ..
			//xmlFile = new File ("/Users/sakkala/tech-workspace/caaers12/core/src/test/resources/gov/nih/nci/cabig/caaers/api/testdata/CreateInvestigatorTest.xml");
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateResearchStaffTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			
			svc.saveResearchStaff(staff);	
			
			//update with modified data ..
			//xmlFile = new File ("/Users/sakkala/tech-workspace/caaers12/core/src/test/resources/gov/nih/nci/cabig/caaers/api/testdata/UpdateInvestigatorTest.xml");
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/UpdateResearchStaffTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			svc.saveResearchStaff(staff);
			
			updatedResearchStaff = fetchResearchStaff("jchapman");
			
			assertNotNull(updatedResearchStaff);
			
			assertEquals("879-345-0983", updatedResearchStaff.getFaxNumber());
			assertEquals("657-678-0098", updatedResearchStaff.getPhoneNumber());
			assertEquals("caaers.app2@gmail.com",updatedResearchStaff.getEmailAddress());
			assertEquals("DCP", updatedResearchStaff.getOrganization().getNciInstituteCode());
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}		
	}
	
	public void testResearchStaffByEmailSave(){
		try {
			//Create or update , whatever it is new data will be populated ..
			//xmlFile = new File ("/Users/sakkala/tech-workspace/caaers12/core/src/test/resources/gov/nih/nci/cabig/caaers/api/testdata/CreateInvestigatorTest.xml");
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateResearchStaffTest2.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			
			svc.saveResearchStaff(staff);	
			
			//update with modified data ..
			//xmlFile = new File ("/Users/sakkala/tech-workspace/caaers12/core/src/test/resources/gov/nih/nci/cabig/caaers/api/testdata/UpdateInvestigatorTest.xml");
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/UpdateResearchStaffTest2.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			svc.saveResearchStaff(staff);
			
			updatedResearchStaff = fetchResearchStaff("caaers.rock@gmail.com");
			
			assertNotNull(updatedResearchStaff);
			
			assertEquals("980-090-0983", updatedResearchStaff.getFaxNumber());
			assertEquals("657-093-0098", updatedResearchStaff.getPhoneNumber());
			assertEquals("DCP", updatedResearchStaff.getOrganization().getNciInstituteCode());
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}		
	}

	/**
     * Fetches the research staff from the DB
     * 
     * @param nciCode
     * @return
     */
    ResearchStaff fetchResearchStaff(String loginId) {//String nciIdentifier) {
    	ResearchStaffQuery rsQuery = new ResearchStaffQuery();
        if (StringUtils.isNotEmpty(loginId)) {
        	//rsQuery.filterByNciIdentifier(nciIdentifier);
        	rsQuery.filterByExactLoginId(loginId);
        }
        List<ResearchStaff> rsList = researchStaffRepository.searchResearchStaff(rsQuery);
        
        if (rsList == null || rsList.isEmpty()) {
            return null;
        }
        return rsList.get(0);
    }
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }


}
