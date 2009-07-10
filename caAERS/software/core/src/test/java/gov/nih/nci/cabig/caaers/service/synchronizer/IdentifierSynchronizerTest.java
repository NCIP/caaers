package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;

public class IdentifierSynchronizerTest extends AbstractTestCase{
	
	Study dbStudy;
	Study xmlStudy;
	IdentifierSynchronizer identifierSynchronizer;
	DomainObjectImportOutcome<Study> outcome;
	Organization organization1;
	Organization organization2;
	OrganizationAssignedIdentifier orgIdentifier1,orgIdentifier2,orgIdentifier1a,orgIdentifier2a,orgIdentifier3,orgIdentifier4;
	String orgName1 = "SemanticBits1";
	String orgName2 = "SemanticBits2";
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		identifierSynchronizer = new IdentifierSynchronizer();
		outcome = new DomainObjectImportOutcome<Study>();
		
		organization1 = Fixtures.createOrganization(orgName1);
		organization2 = Fixtures.createOrganization(orgName2);
		
		dbStudy = Fixtures.createStudy("abcd");
		xmlStudy = Fixtures.createStudy("abcd");
		
	}
	
	
	public void testIdentifiersAddedSynch() {
		orgIdentifier1 = Fixtures.createOrganizationAssignedIdentifier("111", organization1);
		orgIdentifier1.setId(1);
		orgIdentifier2 = Fixtures.createOrganizationAssignedIdentifier("222", organization1);
		orgIdentifier2.setId(2);
		List<Identifier> dbIdentifiers = new ArrayList<Identifier>();
		dbIdentifiers.add(orgIdentifier1);
		dbIdentifiers.add(orgIdentifier2);
		dbStudy.setIdentifiers(dbIdentifiers);
		
		orgIdentifier1a = Fixtures.createOrganizationAssignedIdentifier("111", organization1);
		orgIdentifier2a = Fixtures.createOrganizationAssignedIdentifier("222", organization1);
		orgIdentifier3 = Fixtures.createOrganizationAssignedIdentifier("333", organization2);
		orgIdentifier4 = Fixtures.createOrganizationAssignedIdentifier("444", organization2);
		List<Identifier> xmlIdentifiers = new ArrayList<Identifier>();
		xmlIdentifiers.add(orgIdentifier1a);
		xmlIdentifiers.add(orgIdentifier2a);
		xmlIdentifiers.add(orgIdentifier3);
		xmlIdentifiers.add(orgIdentifier4);
		xmlStudy.setIdentifiers(xmlIdentifiers);
		
		identifierSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		assertEquals(4, dbStudy.getIdentifiers().size());
	}
	
	public void testIdentifiersUpdateForSFSAndSCS() {
		orgIdentifier1 = Fixtures.createOrganizationAssignedIdentifier("111", organization1);
		orgIdentifier1.setId(1);
		orgIdentifier1.setType("Protocol Authority Identifier");
		orgIdentifier2 = Fixtures.createOrganizationAssignedIdentifier("222", organization1);
		orgIdentifier2.setId(2);
		orgIdentifier2.setType("Coordinating Center Identifier");
		List<Identifier> dbIdentifiers = new ArrayList<Identifier>();
		dbIdentifiers.add(orgIdentifier1);
		dbIdentifiers.add(orgIdentifier2);
		dbStudy.setIdentifiers(dbIdentifiers);
		
		orgIdentifier1a = Fixtures.createOrganizationAssignedIdentifier("111a", organization1);
		orgIdentifier1a.setType("Protocol Authority Identifier");
		orgIdentifier2a = Fixtures.createOrganizationAssignedIdentifier("222a", organization1);
		orgIdentifier2a.setType("Coordinating Center Identifier");
		orgIdentifier3 = Fixtures.createOrganizationAssignedIdentifier("333", organization2);
		orgIdentifier4 = Fixtures.createOrganizationAssignedIdentifier("444", organization2);
		List<Identifier> xmlIdentifiers = new ArrayList<Identifier>();
		xmlIdentifiers.add(orgIdentifier1a);
		xmlIdentifiers.add(orgIdentifier2a);
		xmlIdentifiers.add(orgIdentifier3);
		xmlIdentifiers.add(orgIdentifier4);
		xmlStudy.setIdentifiers(xmlIdentifiers);
		
		identifierSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		assertEquals(4, dbStudy.getIdentifiers().size());
	}	

}
