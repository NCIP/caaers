package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffRoleType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffType;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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

	public void testResearchStaffByLoginIdSave() throws Exception{
		try {
			//Create or update , whatever it is new data will be populated ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateResearchStaffTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveResearchStaff(staff);	
			
			//update with modified data ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/UpdateResearchStaffTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveResearchStaff(staff);
			
			updatedResearchStaff = fetchResearchStaff("jchapman");
			
			assertNotNull(updatedResearchStaff);
			
			assertEquals("111-345-0983", updatedResearchStaff.getFaxNumber());
			assertEquals("111-678-0098", updatedResearchStaff.getPhoneNumber());
			assertEquals("caaers.app2@gmail.com",updatedResearchStaff.getEmailAddress());
			assertNotNull(updatedResearchStaff.getAddress());
			assertEquals("13921 Park Center Road", updatedResearchStaff.getAddress().getStreet());
			assertEquals("Herndon", updatedResearchStaff.getAddress().getCity());
			assertNotNull(updatedResearchStaff.getSiteResearchStaffs());
			assertEquals(1,updatedResearchStaff.getSiteResearchStaffs().size());
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}		
	}
	
	public void testResearchStaffByEmailSave() throws Exception{
		try {
			//Create or update , whatever it is new data will be populated ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateResearchStaffTest2.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveResearchStaff(staff);	
			
			//update with modified data ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/UpdateResearchStaffTest2.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveResearchStaff(staff);
			
			updatedResearchStaff = fetchResearchStaff("caaers.rock@gmail.com");
			
			assertNotNull(updatedResearchStaff);
			
			assertEquals("980-090-0983", updatedResearchStaff.getFaxNumber());
			assertEquals("657-093-0098", updatedResearchStaff.getPhoneNumber());
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}		
	}
	
	
	public void testSiteRsAdd() throws Exception{
		
		try {
			//Create or update , whatever it is new data will be populated ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateResearchStaffTest.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveResearchStaff(staff);	
			
			//update with modified data ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/UpdateResearchStaffSiteRsAdd.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveResearchStaff(staff);
			
			updatedResearchStaff = fetchResearchStaff("jchapman");
			
			assertNotNull(updatedResearchStaff);
			
			assertNotNull(updatedResearchStaff.getSiteResearchStaffs());
			assertEquals(2,updatedResearchStaff.getSiteResearchStaffs().size());
			for(SiteResearchStaff siteResearchStaff : updatedResearchStaff.getSiteResearchStaffs()){
				assertNotNull(siteResearchStaff.getSiteResearchStaffRoles());
				assertEquals(2, siteResearchStaff.getSiteResearchStaffRoles().size());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
			fail("Error running test: " + e.getMessage());
		}
		
	}
	
	
	public void testSiteRsRemove() throws Exception{
		
		try {
			//Create or update , whatever it is new data will be populated ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateResearchStaffWithTwoSiteRs.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveResearchStaff(staff);	
			
			//update with modified data ..
			xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/UpdateResearchStaffRemoveSiteRs.xml")[0].getFile();
			staff = (gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff)unmarshaller.unmarshal(xmlFile);
			modifyDates(staff);
			svc.saveResearchStaff(staff);
			
			updatedResearchStaff = fetchResearchStaff("jchapman");
			
			assertNotNull(updatedResearchStaff);
			
			assertNotNull(updatedResearchStaff.getSiteResearchStaffs());
			assertEquals(2,updatedResearchStaff.getSiteResearchStaffs().size());
			for(SiteResearchStaff siteResearchStaff : updatedResearchStaff.getSiteResearchStaffs()){
				assertNotNull(siteResearchStaff.getSiteResearchStaffRoles());
				assertEquals(2, siteResearchStaff.getSiteResearchStaffRoles().size());
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
	
	private void modifyDates(gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff staff) throws Exception{
		
		DatatypeFactory df = DatatypeFactory.newInstance();
		Calendar gcNow = GregorianCalendar.getInstance();
		int year = gcNow.get(Calendar.YEAR); 
		int month = gcNow.get(Calendar.MONTH)+1;
		int day = gcNow.get(Calendar.DAY_OF_MONTH);
		int tz = DatatypeConstants.FIELD_UNDEFINED;
		
		XMLGregorianCalendar currXmlCal = df.newXMLGregorianCalendarDate(year, month, day, tz);
		XMLGregorianCalendar furXmlCal = df.newXMLGregorianCalendarDate(year+1, month, day, tz);
		
		List<ResearchStaffType> researchStaffList = staff.getResearchStaff();
		List<SiteResearchStaffType> siteRsTypeList;
		List<SiteResearchStaffRoleType> siteRsRoleTypeList;
		for (ResearchStaffType researchStaffType:researchStaffList) {
			siteRsTypeList = researchStaffType.getSiteResearchStaffs().getSiteResearchStaff();
			for(SiteResearchStaffType sRsType : siteRsTypeList){
				siteRsRoleTypeList = sRsType.getSiteResearchStaffRoles().getSiteResearchStaffRole();
				for(SiteResearchStaffRoleType sRsRoleType : siteRsRoleTypeList){
					sRsRoleType.setStartDate(currXmlCal);
					sRsRoleType.setEndDate(furXmlCal);
				}
			}
		}
	}

}
