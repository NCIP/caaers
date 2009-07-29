package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.SiteInvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class InvestigatorMigratorServiceTest extends CaaersDbNoSecurityTestCase {

	private InvestigatorMigratorService svc = null;
	private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff = null;
	private File xmlFile = null;
	private InvestigatorDao investigatorDao = null;
	private InvestigatorRepository investigatorRepository;
	Identifier identifier = null;
	Organization organization = null;
	Investigator updatedInvestigator = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.investigator");
		unmarshaller = jaxbContext.createUnmarshaller();
		svc = (InvestigatorMigratorService)getDeployedApplicationContext().getBean("investigatorMigratorService");
		investigatorRepository = (InvestigatorRepository)getDeployedApplicationContext().getBean("investigatorRepository");
		investigatorDao = (InvestigatorDao)getDeployedApplicationContext().getBean("investigatorDao");

	}

	public void testInvestigatorSave() throws Exception{
		try {
			//Create or update , whatever it is new data will be populated ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateInvestigatorTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveInvestigator(staff);	
			
			//update with modified data ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/UpdateInvestigatorTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveInvestigator(staff);
			
			updatedInvestigator = fetchInvestigator("l1");
			
			assertNotNull(updatedInvestigator);
			assertEquals("111-098-0989", updatedInvestigator.getFaxNumber());
			assertEquals("111-098-0099", updatedInvestigator.getPhoneNumber());

		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}		
	}
	
	
	public void testSiAdd() throws Exception{
		try {
			//Create or update , whatever it is new data will be populated ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateInvestigatorTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveInvestigator(staff);
	
//			update site investigators data ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/UpdateSiteInvestigatorsTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveInvestigator(staff);
			

			updatedInvestigator = fetchInvestigator("l1");
			assertNotNull(updatedInvestigator);
			
			//get site investigators.
			List<SiteInvestigator> siteInvestigators = updatedInvestigator.getSiteInvestigatorsInternal();
			for (SiteInvestigator siteInvestigator:siteInvestigators) {
				if (siteInvestigator.getEmailAddress().equals("jb@nci.gov")) {
					assertEquals("NCI", siteInvestigator.getOrganization().getNciInstituteCode());
				}
//				newly added site investigator
				if (siteInvestigator.getEmailAddress().equals("jb@ctep.gov")) {
					assertEquals("CTEP", siteInvestigator.getOrganization().getNciInstituteCode());
				}
			}

	
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
	Investigator fetchInvestigator(String loginId) {
    	InvestigatorQuery invQuery = new InvestigatorQuery();
        if (StringUtils.isNotEmpty(loginId)) {
        	invQuery.filterByExactLoginId(loginId);
        	
        }
        List<Investigator> rsList = investigatorRepository.searchInvestigator(invQuery);
        
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
	
	private void modifyDates(Staff staff) throws Exception{
		
		DatatypeFactory df = DatatypeFactory.newInstance();
		Calendar gcNow = GregorianCalendar.getInstance();
		int year = gcNow.get(Calendar.YEAR); 
		int month = gcNow.get(Calendar.MONTH)+1;
		int day = gcNow.get(Calendar.DAY_OF_MONTH);
		int tz = DatatypeConstants.FIELD_UNDEFINED;
		
		XMLGregorianCalendar currXmlCal = df.newXMLGregorianCalendarDate(year, month, day, tz);
		XMLGregorianCalendar furXmlCal = df.newXMLGregorianCalendarDate(year+1, month, day, tz);
		
		if(staff != null){
			List<InvestigatorType> investigatorTypeList = staff.getInvestigator();
			for (InvestigatorType investigatorType:investigatorTypeList) {
				for(SiteInvestigatorType siType : investigatorType.getSiteInvestigator()){
					siType.setStartDate(currXmlCal);
					siType.setEndDate(furXmlCal);
				}
			}
		}
		
	}
}
