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


	public void saveResearchStaff() {
		ResearchStaffMigratorService svc = (ResearchStaffMigratorService) getApplicationContext()
        .getBean("researchStaffMigratorServiceAPI");
		
		try {
			File xmlFile = getResources("/schema/integration/research-staff.xml")[0].getFile();
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Staff staff = (Staff)unmarshaller.unmarshal(xmlFile);
			
			svc.saveResearchStaff(staff);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
}
