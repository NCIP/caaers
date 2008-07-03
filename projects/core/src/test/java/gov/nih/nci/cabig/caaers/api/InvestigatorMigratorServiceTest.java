package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;

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

public class InvestigatorMigratorServiceTest extends CaaersDbTestCase {

	private InvestigatorMigratorService svc = null;
	private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff = null;
	private File xmlFile = null;
	private InvestigatorDao investigatorDao = null;
	Identifier identifier = null;
	Organization organization = null;
	Investigator updatedInvestigator = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.investigator");
		unmarshaller = jaxbContext.createUnmarshaller();
		svc = (InvestigatorMigratorService)getDeployedApplicationContext().getBean("InvestigatorMigratorService");
		investigatorDao = (InvestigatorDao)getDeployedApplicationContext().getBean("investigatorDao");
		/*
		updatedInvestigator = fetchInvestigator("nci_identifier");
		if(updatedInvestigator != null){
			investigatorDao.delete(updatedInvestigator);
		}
		createInvestigator();
		*/
	}

	/**
     * Fetches the research staff from the DB
     * 
     * @param nciCode
     * @return
     */
	Investigator fetchInvestigator(String nciIdentifier) {
    	InvestigatorQuery invQuery = new InvestigatorQuery();
        if (StringUtils.isNotEmpty(nciIdentifier)) {
        	invQuery.filterByNciIdentifier(nciIdentifier);
        	
        }
        List<Investigator> rsList = investigatorDao.searchInvestigator(invQuery);
        
        if (rsList == null || rsList.isEmpty()) {
            return null;
        }
        return rsList.get(0);
    }
	
	public void testInvestigatorSave(){
		try {
			//Create or update , whatever it is new data will be populated ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateInvestigatorTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff)unmarshaller.unmarshal(xmlFile);
			svc.saveInvestigator(staff);	
			
			//update with modified data ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/UpdateInvestigatorTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff)unmarshaller.unmarshal(xmlFile);
			svc.saveInvestigator(staff);
			
			updatedInvestigator = fetchInvestigator("sr-1");
			
			assertNotNull(updatedInvestigator);
			
			assertEquals("101-202-3333", updatedInvestigator.getFaxNumber());
			assertEquals("101-202-4444", updatedInvestigator.getPhoneNumber());
			
			updatedInvestigator = fetchInvestigator("nci-id-2");
			
			assertNotNull(updatedInvestigator);
			
			assertEquals("870-098-0000", updatedInvestigator.getFaxNumber());
			assertEquals("908-098-0000", updatedInvestigator.getPhoneNumber());
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}		
	}
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
}
