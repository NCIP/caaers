package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * @author Monish Dombla
 */
public class ImportCommandTest extends AbstractTestCase {
	
	private ImportCommand command;
	
    protected void setUp() throws Exception {
        super.setUp();
        command = new ImportCommand();
    }
	
	public void test_AddUpdateableAgent(){
		
		DomainObjectImportOutcome<Agent> importOutcome = new DomainObjectImportOutcome<Agent>();
		Agent agent = new Agent();
		agent.setNscNumber("54321");
		agent.setName("Viola");
		
		importOutcome.setImportedDomainObject(agent);
		command.addUpdateableAgent(importOutcome);
		assertNotNull(command.getUpdateableAgents());
		assertEquals(1, command.getUpdateableAgents().size());
	}
	
	public void test_AddImportableAgent(){
		
		DomainObjectImportOutcome<Agent> importOutcome1 = new DomainObjectImportOutcome<Agent>();
		DomainObjectImportOutcome<Agent> importOutcome2 = new DomainObjectImportOutcome<Agent>();
		Agent agent1 = new Agent();
		agent1.setNscNumber("54321");
		agent1.setName("Viola");
		
		Agent agent2 = new Agent();
		agent2.setNscNumber("12345");
		agent2.setName("MinusY");
		
		importOutcome1.setImportedDomainObject(agent1);
		importOutcome2.setImportedDomainObject(agent2);
		command.addImportableAgent(importOutcome1);
		command.addImportableAgent(importOutcome2);
		assertNotNull(command.getImportableAgents());
		assertEquals(2, command.getImportableAgents().size());
	}
	
	public void test_AddNonImportableAgent(){
		
		DomainObjectImportOutcome<Agent> importOutcome = new DomainObjectImportOutcome<Agent>();
		Agent agent = new Agent();
		agent.setNscNumber("54321");
		agent.setName("Viola");
		
		importOutcome.setImportedDomainObject(agent);
		command.addNonImportableAgent(importOutcome);
		assertNotNull(command.getNonImportableAgents());
		assertEquals(1, command.getNonImportableAgents().size());
	}
	
	public void test_AddUpdateableOrganization(){
		
		DomainObjectImportOutcome<Organization> importOutcome = new DomainObjectImportOutcome<Organization>();
		Organization organization = Fixtures.createOrganization("Org", "12345");
		importOutcome.setImportedDomainObject(organization);
		command.addUpdateableOrganization(importOutcome);
		assertNotNull(command.getUpdateableOrganizations());
		assertEquals(1, command.getUpdateableOrganizations().size());
		
	}
	
	public void test_AddImportableOrganization(){
		
		DomainObjectImportOutcome<Organization> importOutcome1 = new DomainObjectImportOutcome<Organization>();
		DomainObjectImportOutcome<Organization> importOutcome2 = new DomainObjectImportOutcome<Organization>();
		
		Organization organization1 = Fixtures.createOrganization("Org-1", "12345");
		Organization organization2 = Fixtures.createOrganization("Org-2", "54321");
		importOutcome1.setImportedDomainObject(organization1);
		importOutcome2.setImportedDomainObject(organization2);
		command.addImportableOrganization(importOutcome1);
		command.addImportableOrganization(importOutcome2);
		assertNotNull(command.getImportableOrganizations());
		assertEquals(2, command.getImportableOrganizations().size());
	}
	
	public void test_AddNonImportableOrganization(){
		DomainObjectImportOutcome<Organization> importOutcome = new DomainObjectImportOutcome<Organization>();
		Organization organization = Fixtures.createOrganization("Org", "12345");
		importOutcome.setImportedDomainObject(organization);
		command.addNonImportableOrganization(importOutcome);
		assertNotNull(command.getNonImportableOrganizations());
		assertEquals(1, command.getNonImportableOrganizations().size());
	}

}
