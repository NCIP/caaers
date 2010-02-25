package gov.nih.nci.cabig.caaers.web.admin;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepositoryImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 *		Input String has to have 5 tokens.Valid samples as given below
 *		Institution Code,Institution Name,City,State,Country                                                                              
 *		"02001","Catedra De Oncologia Univ-Salvador","Buenos Aires","","Argentina"                                                        
 *		"02002","Hospital Militar Central","Buenos Aires","","Argentina"                                                                  
 *		"02003","Hospitales Privado Guemes","Buenos Aires","","Argentina"                                                                 
 *		"02004","Hosp. Rawson","Buenos Aires","","Argentina"                                                                              
 *		"02005","Hosp. Teodoro Alvarez","Buenos Aires","","Argentina"                                                                     
 *		"02006","Hosp. Ramos Mejia, Sala 18","Buenos Aires","","Argentina" 
 * 
 * @author Monish Dombla
 *
 */
public class OrganizationImporterTest extends WebTestCase {

	private OrganizationRepository organizationRepository;
	private OrganizationImporter importer;
	private DomainObjectImportOutcome<Organization> organizationImportOutcome;
	private Map<String,Organization> organizationMap = null;
	private ImportCommand command;
	
	protected void setUp() throws Exception {
		super.setUp();
		importer = new OrganizationImporter(); 
		command = new ImportCommand();
		organizationRepository = registerMockFor(OrganizationRepositoryImpl.class);
	}
		
	public void testProcessOrganization_ValidRecord(){
		
		String organizationRecord = "\"02001\",\"Catedra De Oncologia Univ-Salvador\",\"Buenos Aires\",\"\",\"Argentina\"";
		organizationImportOutcome = importer.processOrganization(organizationRecord, 1);
		assertNotNull(organizationImportOutcome);
		Organization organization = organizationImportOutcome.getImportedDomainObject();
		assertNotNull(organization);
		assertTrue(organization instanceof LocalOrganization);
		
		assertEquals("02001", organization.getNciInstituteCode());
		assertEquals("Catedra De Oncologia Univ-Salvador",organization.getName());
		assertEquals("Buenos Aires",organization.getCity());
		assertEquals("",organization.getState());
		assertEquals("Argentina",organization.getCountry());
		
	}
	
	public void testProcessOrganization_InValidRecord(){
		String organizationRecord = "\"11008\",\"QEII Health Sciences Centre - Nova Scotia Cancer Centre\",\"Halifax\"";
		organizationImportOutcome = importer.processOrganization(organizationRecord, 1090);
		assertNotNull(organizationImportOutcome);
		Organization organization = organizationImportOutcome.getImportedDomainObject();
		assertNull(organization);
		assertNotNull(organizationImportOutcome.getMessages());
		assertEquals(1, organizationImportOutcome.getMessages().size());
		assertEquals("Invalid organization record found at line ::: 1090", organizationImportOutcome.getMessages().get(0).getMessage());
	}	
	
	public void testProcessEntities(){
		
		try {
			File ctepOrganizationsFile = getResources("classpath*:gov/nih/nci/cabig/caaers/web/admin/testdata/Organization_Codes.txt")[0].getFile();
			assertTrue(ctepOrganizationsFile.exists());
			
			organizationMap = new HashMap<String,Organization>();
			
			LocalOrganization localOrg1 = new LocalOrganization();
			localOrg1.setNciInstituteCode("ABCD01");
			localOrg1.setName("Change Me");
			organizationMap.put("ABCD01", localOrg1);
			
			importer.setOrganizationMap(organizationMap);
			
			importer.processEntities(ctepOrganizationsFile, command);
			assertNotNull(command);
			
			assertNotNull(command.getImportableOrganizations());
			assertEquals(6,command.getImportableOrganizations().size());
			
			assertNotNull(command.getUpdateableOrganizations());
			assertEquals(1,command.getUpdateableOrganizations().size());
			
			assertNotNull(command.getNonImportableOrganizations());
			assertEquals(3,command.getNonImportableOrganizations().size());
			
		}catch(Exception e){
			fail("No Exception is expected");
		}
	}
	
	public void testGetMap(){
		
		//When Map is null.
		importer.setOrganizationMap(null);
		importer.setOrganizationRepository(organizationRepository);
		Organization Org1 = Fixtures.createOrganization("LocalOrg-1", "1234");
		Organization Org2 = Fixtures.createOrganization("RemoteOrg-1", "4321");
		List<Organization> allOrganizations = new ArrayList<Organization>();
		allOrganizations.add(Org1);
		allOrganizations.add(Org2);
		
		expect(organizationRepository.getAllOrganizations()).andReturn(allOrganizations).anyTimes();
		replayMocks();
		organizationMap = importer.getOrganizationMap();
		verifyMocks();
		assertNotNull(organizationMap);
		
		//When Map is NOT null.
		organizationMap = new HashMap<String,Organization>();
		Map returnedMap = null;
		organizationMap.put("1234", Org1);
		organizationMap.put("4321", Org2);
		importer.setOrganizationMap(organizationMap);
		returnedMap = importer.getOrganizationMap();
		assertNotNull(returnedMap);
		
	}
	
	public void testSave(){
		ImportCommand command = new ImportCommand();
		
		LocalOrganization localOrg1 = new LocalOrganization();
		localOrg1.setNciInstituteCode("ABCD01");
		localOrg1.setName("Change Me");
		
		DomainObjectImportOutcome<Organization> importOutcome1 = new DomainObjectImportOutcome<Organization>();
		importOutcome1.setImportedDomainObject(localOrg1);
		importOutcome1.setSavable(true);
		command.addImportableOrganization(importOutcome1);
		
		LocalOrganization localOrg2 = new LocalOrganization();
		localOrg2.setNciInstituteCode("XYWER");
		localOrg2.setName("Nothing");
		DomainObjectImportOutcome<Organization> importOutcome2 = new DomainObjectImportOutcome<Organization>();
		importOutcome2.setImportedDomainObject(localOrg2);
		importOutcome2.setSavable(true);
		command.addUpdateableOrganization(importOutcome2);
		
		importer.setOrganizationRepository(organizationRepository);
		
		organizationRepository.saveImportedOrganization((Organization)EasyMock.anyObject());
		expectLastCall().anyTimes();
		
		replayMocks();
		importer.save(command, null);
		verifyMocks();
		
		
	}
	
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
}
