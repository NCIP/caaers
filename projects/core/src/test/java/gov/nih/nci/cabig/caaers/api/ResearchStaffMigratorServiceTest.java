package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ResearchStaffMigratorServiceTest extends CaaersDbTestCase {


    public String getTestDataFileName() {
        String fileName = "testdata/ResearchStaffMigratorServiceTest.xml";
        return fileName;
    }
    
	public void testSaveResearchStaff() {
		ResearchStaffMigratorService svc = (ResearchStaffMigratorService) getApplicationContext()
        .getBean("researchStaffMigratorService");
		
		try {
			File xmlFile = new File ("/Users/sakkala/tech/caaers/ResearchStaffMigratorServiceTest.xml");//getResources("/schema/integration/investigator.xml")[0].getFile();
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Staff staff = (Staff)unmarshaller.unmarshal(xmlFile);
			
			svc.saveResearchStaff(staff);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
	}
	/*
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }*/
}
