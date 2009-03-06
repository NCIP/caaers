package gov.nih.nci.cabig.caaers.service.migrator;

import static org.easymock.EasyMock.isA;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

/**
 * 
 * @author Biju Joseph
 *
 */
public class IdentifierMigratorTest extends AbstractTestCase {
	
	OrganizationDao organizationDao;
	IdentifierMigrator migrator;
	
	String orgName = "SemanticBits";
	Organization organization;
	OrganizationAssignedIdentifier orgIdentifier;
	SystemAssignedIdentifier sysIdentifier1, sysIdentifier2;
	DomainObjectImportOutcome outcome;
	
	Study src;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		organizationDao = registerDaoMockFor(OrganizationDao.class);
		
		migrator = new IdentifierMigrator();
		migrator.setOrganizationDao(organizationDao);
		
		outcome = new DomainObjectImportOutcome();
		
		organization = Fixtures.createOrganization(orgName);
		orgIdentifier = Fixtures.createOrganizationAssignedIdentifier("111", organization);
		orgIdentifier.getOrganization().setNciInstituteCode(null);
		sysIdentifier1 = Fixtures.createSystemAssignedIdentifier("abcd");
		sysIdentifier2 = Fixtures.createSystemAssignedIdentifier("mmmm");
		src = Fixtures.createStudy("Test");
	}
	
	public void testMigrate() {
		
		Study dest = new Study();
		
		src.addIdentifier(orgIdentifier);
		src.addIdentifier(sysIdentifier1);
		src.addIdentifier(sysIdentifier2);
		
		List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);
        
        EasyMock.expect(organizationDao.getByName(orgName)).andReturn(organization);
        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations).anyTimes();
		
		replayMocks();
		migrator.migrate(src, dest, outcome);
		verifyMocks();
		
		assertEquals("No error message when migrating identifiers", 0,outcome.getMessages().size());
		assertEquals("Identifiers size should be same", src.getIdentifiers().size(), dest.getIdentifiers().size());
		
		assertTrue("Src - Identifiers size should be 3", src.getIdentifiers().size() == 3 );
		assertTrue("Dest - Identifiers size should be 3", dest.getIdentifiers().size() == 3 );
	}

	public void testMigrate_OnlySystemAssignedIdentifier() {
		
		Study dest = new Study();
		src.addIdentifier(sysIdentifier1);
		src.addIdentifier(sysIdentifier2);
		
		replayMocks();
		migrator.migrate(src, dest, outcome);
		verifyMocks();
		
		assertEquals("No error message when migrating identifiers", 0,outcome.getMessages().size());
		assertEquals("Identifiers size should be same", src.getIdentifiers().size(), dest.getIdentifiers().size());
		
		assertTrue("Src - Identifiers size should be 2", src.getIdentifiers().size() == 2 );
		assertTrue("Dest - Identifiers size should be 2", dest.getIdentifiers().size() == 2 );
		
		assertEquals("Identifier value should be 'value:abcd'", "value:abcd", dest.getIdentifiers().get(0).getValue());
	}
	
	
	public void testMigrate_NoIdentifier() {
		
		Study dest = new Study();
		
		replayMocks();
		migrator.migrate(src, dest, outcome);
		verifyMocks();
		
		assertEquals("No error message when migrating identifiers", 1,outcome.getMessages().size());
		Message msg = (Message)outcome.getMessages().get(0);
		assertEquals("Incorrect error message", "Identifiers are either Empty or Not Valid", msg.getMessage());
	}
	
	public void testMigrate_Identifier_Having_InvalidOrganization() {
		
		Study dest = new Study();
		src.addIdentifier(orgIdentifier);
		src.addIdentifier(sysIdentifier1);
		src.addIdentifier(sysIdentifier2);
	
		EasyMock.expect(organizationDao.getByName(orgName)).andReturn(null);
		replayMocks();
		migrator.migrate(src, dest, outcome);
		verifyMocks();
		
		assertEquals("No error message when migrating identifiers", 1,outcome.getMessages().size());
		assertEquals("Identifiers size should be same", src.getIdentifiers().size(), dest.getIdentifiers().size());
		Message msg = (Message)outcome.getMessages().get(0);
		assertEquals("The organization specified in identifier is invalid", msg.getMessage());
	}
}
