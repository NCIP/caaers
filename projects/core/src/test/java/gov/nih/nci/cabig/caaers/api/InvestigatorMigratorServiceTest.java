package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class InvestigatorMigratorServiceTest extends CaaersDbTestCase {
	public void saveInvestigator() {
		InvestigatorMigratorService svc = (InvestigatorMigratorService) getApplicationContext()
        .getBean("investigatorMigratorServiceAPI");
		
		try {
			File xmlFile = getResources("/schema/integration/investigator.xml")[0].getFile();
			JAXBContext jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.investigator");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Staff staff = (Staff)unmarshaller.unmarshal(xmlFile);
			
			svc.saveInvestigator(staff);
			
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
