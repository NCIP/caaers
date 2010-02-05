package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

	private OrganizationImporter importer;
	private DomainObjectImportOutcome<Organization> organizationImportOutcome;
	private Map<String,Organization> organizationMap = null;
	private ImportCommand command;
	
	protected void setUp() throws Exception {
		super.setUp();
		importer = new OrganizationImporter(); 
		command = new ImportCommand();
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
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
}
