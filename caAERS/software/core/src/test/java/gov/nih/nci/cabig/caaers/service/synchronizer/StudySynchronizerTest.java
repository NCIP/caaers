package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Design;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;

/**
 * Tests the Synch of Study Attributes.
 * @author Monish Dombla
 *
 */
public class StudySynchronizerTest extends AbstractTestCase{
	
	Study dbStudy;
	Study xmlStudy;
	StudySynchronizer studySynchronizer;
	DomainObjectImportOutcome<Study> outcome;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studySynchronizer = new StudySynchronizer();
		outcome = new DomainObjectImportOutcome<Study>();
		dbStudy = Fixtures.createStudy("abcd");
		xmlStudy = Fixtures.createStudy("abcd");
	}
	
	public void testPreMigrateStudySynchronizer() {
		xmlStudy.setLongTitle("UpdatedLongTitle");
		
		xmlStudy.setDescription("UpdatedDescription");
		xmlStudy.setPrecis("UdatedPrecis");
		xmlStudy.setPhaseCode("UpdatedPhaseCode");
		xmlStudy.setStatus("UpdatedStatus");
		xmlStudy.setMultiInstitutionIndicator(new Boolean("false"));
		xmlStudy.setAdeersReporting(new Boolean("false"));
		xmlStudy.setDesign(Design.BLIND);
		xmlStudy.setDrugAdministrationTherapyType(new Boolean("false"));
		xmlStudy.setRadiationTherapyType(new Boolean("false"));
		xmlStudy.setSurgeryTherapyType(new Boolean("false"));
		xmlStudy.setBehavioralTherapyType(new Boolean("false"));
		
		studySynchronizer.migrate(dbStudy, xmlStudy, outcome);
		assertTrue(outcome.getMessages().isEmpty());
		assertEquals("Updated Long Title", "UpdatedLongTitle", dbStudy.getLongTitle());
		assertEquals("Updated Precis", "UdatedPrecis", dbStudy.getPrecis());
		assertEquals("Updated PhaseCode", "UpdatedPhaseCode", dbStudy.getPhaseCode());
		assertEquals("Updated Status", "UpdatedStatus", dbStudy.getStatus());
		assertFalse(dbStudy.getMultiInstitutionIndicator());
		assertFalse(dbStudy.getAdeersReporting());
	}
	
	public void testNullAttributesPreMigrateStudySynchronizer() {
		xmlStudy.setLongTitle("UpdatedLongTitle");
		
		xmlStudy.setDescription("UpdatedDescription");
		xmlStudy.setPrecis("UdatedPrecis");
		xmlStudy.setPhaseCode("UpdatedPhaseCode");
		xmlStudy.setStatus("UpdatedStatus");
		xmlStudy.setMultiInstitutionIndicator(new Boolean("false"));
		xmlStudy.setAdeersReporting(new Boolean("false"));
		xmlStudy.setDesign(Design.BLIND);
		xmlStudy.setDrugAdministrationTherapyType(new Boolean("false"));
		xmlStudy.setRadiationTherapyType(new Boolean("false"));
		xmlStudy.setSurgeryTherapyType(new Boolean("false"));
		xmlStudy.setBehavioralTherapyType(new Boolean("false"));
		
		studySynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		assertTrue(outcome.getMessages().isEmpty());
		assertEquals("Updated Long Title", "UpdatedLongTitle", dbStudy.getLongTitle());
		assertEquals("Updated Precis", "UdatedPrecis", dbStudy.getPrecis());
		assertEquals("Updated PhaseCode", "UpdatedPhaseCode", dbStudy.getPhaseCode());
		assertEquals("Updated Status", "UpdatedStatus", dbStudy.getStatus());
		assertFalse(dbStudy.getMultiInstitutionIndicator());
		assertFalse(dbStudy.getAdeersReporting());
		
		xmlStudy.setDescription("");
		xmlStudy.setPrecis("");
		xmlStudy.setPhaseCode("UpdatedPhaseCode");
		xmlStudy.setStatus("UpdatedStatus");
		xmlStudy.setMultiInstitutionIndicator(new Boolean("false"));
		xmlStudy.setAdeersReporting(new Boolean("false"));
		xmlStudy.setDesign(null);
		xmlStudy.setDrugAdministrationTherapyType(new Boolean("false"));
		xmlStudy.setRadiationTherapyType(new Boolean("false"));
		xmlStudy.setSurgeryTherapyType(new Boolean("false"));
		xmlStudy.setBehavioralTherapyType(new Boolean("false"));
		
		studySynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		assertEquals("UpdatedDescription", dbStudy.getDescription());
		assertEquals("UdatedPrecis", dbStudy.getPrecis());
		assertEquals(Design.BLIND, dbStudy.getDesign());
		
		
	}
	
}
