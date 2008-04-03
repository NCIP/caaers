package gov.nih.nci.cabig.caaers.service.migrator;

import org.easymock.classextension.EasyMock;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import gov.nih.nci.cabig.caaers.service.migrator.StudyTerminologyMigrator;

public class StudyTerminologyMigratorTest extends AbstractTestCase {
	
	StudyTerminologyMigrator migrator;
	Study xstreamStudy;
	DomainObjectImportOutcome<Study> outcome;
	Study dest;
	
    private CtcDao ctcDao;
    private MeddraVersionDao meddraVersionDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dest = new Study();
		xstreamStudy = Fixtures.createStudy("short title");
		outcome = new DomainObjectImportOutcome<Study>();
		ctcDao = registerDaoMockFor(CtcDao.class);
		meddraVersionDao = registerDaoMockFor(MeddraVersionDao.class);
		migrator = new StudyTerminologyMigrator();
		migrator.setCtcDao(ctcDao);
		migrator.setMeddraVersionDao(meddraVersionDao);
	}
	
	public void testMigrate_WithCorrectCtcVersion() {
		AeTerminology ctcV3Terminology = Fixtures.createCtcV3Terminology(xstreamStudy);
        EasyMock.expect(ctcDao.getById(Integer.parseInt(ctcV3Terminology.getCtcVersion().getName()))).andReturn(ctcV3Terminology.getCtcVersion());
        replayMocks();
        
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();
        
        Ctc ctcVersion = dest.getAeTerminology().getCtcVersion(); 
        assertNotNull("Ctc Version should not be null", ctcVersion);
        assertEquals("Ctc version name should be same", ctcV3Terminology.getCtcVersion().getName(),ctcVersion.getName());
        assertTrue("No error when ctc version is correct", outcome.getMessages().isEmpty());

	}
	
	public void testMigrate_WithNoAeTerminology() {
        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();
        assertEquals("One error message complaing missing terminology", 1, outcome.getMessages().size());
        Message msg = (Message) outcome.getMessages().get(0);
        assertEquals("Incorrect error message","AeTerminology is either Empty or Not Valid", msg.getMessage());
	}
	
	public void testMigrate_WithIncorrectCtcVersion() {
		AeTerminology ctcV3Terminology = Fixtures.createCtcV3Terminology(xstreamStudy);
        EasyMock.expect(ctcDao.getById(Integer.parseInt(ctcV3Terminology.getCtcVersion().getName()))).andReturn(null);
        replayMocks();
        
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();
        
        Ctc ctcVersion = dest.getAeTerminology().getCtcVersion(); 
        assertNull("Ctc Version should  be null", ctcVersion);
        System.out.println(outcome.getMessages());
        assertEquals("error when ctc version is not correct",  1, outcome.getMessages().size());
        Message msg = (Message) outcome.getMessages().get(0);
        assertEquals("Incorrect error message","CTC is either Empty or Not Valid", msg.getMessage());
	}
	
	public void testMigrate_WithCorrectMeddraVersion() {
		 AeTerminology medDRATerminology = Fixtures.createMedDRATerminology(xstreamStudy);
	     EasyMock.expect(meddraVersionDao.getById(Integer.parseInt(medDRATerminology.getMeddraVersion().getName()))).andReturn(medDRATerminology.getMeddraVersion());
	     replayMocks();
        
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();
        MeddraVersion meddraVersion = dest.getAeTerminology().getMeddraVersion();
        assertNotNull(meddraVersion);
        assertEquals("Meddra Version Name should be same", meddraVersion.getName(), medDRATerminology.getMeddraVersion().getName());
        assertTrue(outcome.getMessages().isEmpty());
	}
	
	public void testMigrate_WithIncorrectMeddraVersion() {
		 AeTerminology medDRATerminology = Fixtures.createMedDRATerminology(xstreamStudy);
	     EasyMock.expect(meddraVersionDao.getById(Integer.parseInt(medDRATerminology.getMeddraVersion().getName()))).andReturn(null);
	     replayMocks();
        
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();
        MeddraVersion meddraVersion = dest.getAeTerminology().getMeddraVersion();
        assertNull("Meddra Version should be null",meddraVersion);
        assertEquals("Error when meddra version is not correct", 1, outcome.getMessages().size());
		
	}
	
}
