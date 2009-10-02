package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAEMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

public class StudyExpectedAEMigratorTest extends AbstractTestCase{

	private CtcTermDao ctcTermDao;
	private LowLevelTermDao lowLevelTermDao;
	
	private Study xmlStudy;
    private DomainObjectImportOutcome<Study> outcome;
    private Study dest;
	private StudyExpectedAEMigrator migrator;
	
	@Override
    protected void setUp() throws Exception {
		migrator = new StudyExpectedAEMigrator();
		xmlStudy = Fixtures.createStudy("StudyEvaluationPeriodsMigrator");
		dest = new LocalStudy();
		outcome = new DomainObjectImportOutcome<Study>();
		
		ctcTermDao = registerDaoMockFor(CtcTermDao.class);
		lowLevelTermDao = registerDaoMockFor(LowLevelTermDao.class);
		migrator.setCtcTermDao(ctcTermDao);
		migrator.setLowLevelTermDao(lowLevelTermDao);
	}
	
	public void testMigrateExpectedAE_CtcTerm(){
		AeTerminology aeTerminology = Fixtures.createCtcV3Terminology(xmlStudy);
		MeddraVersion otherMeddra = new MeddraVersion();
		otherMeddra.setId(4);
		otherMeddra.setName("MedDRA v9");
        xmlStudy.setOtherMeddra(otherMeddra);
        xmlStudy.setAeTerminology(aeTerminology);
        
        dest.setAeTerminology(aeTerminology);
        dest.setOtherMeddra(otherMeddra);
        
        ExpectedAECtcTerm expectedAECtcTerm_1 = new ExpectedAECtcTerm();
        CtcTerm ctcTerm_1 = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        expectedAECtcTerm_1.setCtcTerm(ctcTerm_1);
        LowLevelTerm llt_1 = new LowLevelTerm();
        llt_1.setMeddraTerm("meddraTerm_1");
        llt_1.setMeddraCode("meddraCode_1");
        expectedAECtcTerm_1.setOtherMeddraTerm(llt_1);
        
        
        ExpectedAECtcTerm expectedAECtcTerm_2 = new ExpectedAECtcTerm();
        CtcTerm ctcTerm_2 = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        expectedAECtcTerm_2.setCtcTerm(ctcTerm_2);
        LowLevelTerm llt_2 = new LowLevelTerm();
        llt_2.setMeddraTerm("meddraTerm_2");
        llt_2.setMeddraCode("meddraCode_2");
        expectedAECtcTerm_2.setOtherMeddraTerm(llt_2);
        
        xmlStudy.getExpectedAECtcTerms().add(expectedAECtcTerm_1);
        xmlStudy.getExpectedAECtcTerms().add(expectedAECtcTerm_2);
        
      //For EasyMock Return 
        List<CtcTerm> ctcTerms_1 = new ArrayList<CtcTerm>();
        ctcTerms_1.add(ctcTerm_1);
      //For EasyMock Return 
        List<LowLevelTerm> llts_1 = new ArrayList<LowLevelTerm>();
        llts_1.add(llt_1);
        
      //For EasyMock Return 
        List<CtcTerm> ctcTerms_2 = new ArrayList<CtcTerm>();
        ctcTerms_2.add(ctcTerm_2);
      //For EasyMock Return 
        List<LowLevelTerm> llts_2 = new ArrayList<LowLevelTerm>();
        llts_2.add(llt_2);
        
        EasyMock.expect(lowLevelTermDao.getByMeddraCodeandVersion(llt_1.getMeddraCode(),dest.getOtherMeddra().getId())).andReturn(llts_1).anyTimes();
        EasyMock.expect(ctcTermDao.getByCtepCodeandVersion(ctcTerm_1.getCtepCode(),aeTerminology.getCtcVersion().getId())).andReturn(ctcTerms_1).anyTimes();
        
        EasyMock.expect(lowLevelTermDao.getByMeddraCodeandVersion(llt_2.getMeddraCode(),dest.getOtherMeddra().getId())).andReturn(llts_2).anyTimes();
        EasyMock.expect(ctcTermDao.getByCtepCodeandVersion(ctcTerm_2.getCtepCode(),aeTerminology.getCtcVersion().getId())).andReturn(ctcTerms_2).anyTimes();
        
        replayMocks();
        
        migrator.migrate(xmlStudy, dest, outcome);
        
        verifyMocks();
        
        assertNotNull(dest.getExpectedAECtcTerms());
        assertEquals(2, dest.getExpectedAECtcTerms().size());
        assertEquals(0, dest.getExpectedAEMeddraLowLevelTerms().size());
        assertNotNull(dest.getExpectedAECtcTerms().get(0));
        assertNotNull(dest.getExpectedAECtcTerms().get(1));
	}
	
	public void testMigrateExpectedAE_MeddraTerm(){
		AeTerminology aeTerminology = Fixtures.createMedDRATerminology(xmlStudy);
		MeddraVersion meddra = new MeddraVersion();
		meddra.setId(4);
		meddra.setName("MedDRA v9");
		aeTerminology.setMeddraVersion(meddra);
        xmlStudy.setOtherMeddra(meddra);
        xmlStudy.setAeTerminology(aeTerminology);
        
        dest.setAeTerminology(aeTerminology);
        dest.setOtherMeddra(meddra);
        
        LowLevelTerm llt_1 = new LowLevelTerm();
        llt_1.setMeddraTerm("meddraTerm_1");
        llt_1.setMeddraCode("meddraCode_1");
        ExpectedAEMeddraLowLevelTerm expectedAEMeddraLowLevelTerm_1 = new ExpectedAEMeddraLowLevelTerm();
        expectedAEMeddraLowLevelTerm_1.setLowLevelTerm(llt_1);
        
        LowLevelTerm llt_2 = new LowLevelTerm();
        llt_2.setMeddraTerm("meddraTerm_2");
        llt_2.setMeddraCode("meddraCode_2");
        ExpectedAEMeddraLowLevelTerm expectedAEMeddraLowLevelTerm_2 = new ExpectedAEMeddraLowLevelTerm();
        expectedAEMeddraLowLevelTerm_2.setLowLevelTerm(llt_2);
        
        xmlStudy.getExpectedAEMeddraLowLevelTerms().add(expectedAEMeddraLowLevelTerm_1);
        xmlStudy.getExpectedAEMeddraLowLevelTerms().add(expectedAEMeddraLowLevelTerm_2);
        
      //For EasyMock Return 
        List<LowLevelTerm> llts_1 = new ArrayList<LowLevelTerm>();
        llts_1.add(llt_1);
      //For EasyMock Return 
        List<LowLevelTerm> llts_2 = new ArrayList<LowLevelTerm>();
        llts_2.add(llt_2);
        
        EasyMock.expect(lowLevelTermDao.getByMeddraCodeandVersion(llt_1.getMeddraCode(),dest.getAeTerminology().getMeddraVersion().getId())).andReturn(llts_1).anyTimes();
        EasyMock.expect(lowLevelTermDao.getByMeddraCodeandVersion(llt_2.getMeddraCode(),dest.getAeTerminology().getMeddraVersion().getId())).andReturn(llts_2).anyTimes();
        
        replayMocks();
        
        migrator.migrate(xmlStudy, dest, outcome);
        
        verifyMocks();
        
        assertNotNull(dest.getExpectedAEMeddraLowLevelTerms());
        assertEquals(2, dest.getExpectedAEMeddraLowLevelTerms().size());
        assertEquals(0, dest.getExpectedAECtcTerms().size());
        assertNotNull(dest.getExpectedAEMeddraLowLevelTerms().get(0));
        assertNotNull(dest.getExpectedAEMeddraLowLevelTerms().get(1));
        
	}
	
	public void testMigrateExpectedAE_OnlyOtherMeddraTerm(){
		AeTerminology aeTerminology = Fixtures.createCtcV3Terminology(xmlStudy);
		MeddraVersion otherMeddra = new MeddraVersion();
		otherMeddra.setId(4);
		otherMeddra.setName("MedDRA v9");
        xmlStudy.setOtherMeddra(otherMeddra);
        xmlStudy.setAeTerminology(aeTerminology);
        
        dest.setAeTerminology(aeTerminology);
        dest.setOtherMeddra(otherMeddra);
        
        ExpectedAECtcTerm expectedAECtcTerm_1 = new ExpectedAECtcTerm();
        LowLevelTerm llt_1 = new LowLevelTerm();
        llt_1.setMeddraTerm("meddraTerm_1");
        llt_1.setMeddraCode("meddraCode_1");
        expectedAECtcTerm_1.setOtherMeddraTerm(llt_1);
        
        
        ExpectedAECtcTerm expectedAECtcTerm_2 = new ExpectedAECtcTerm();
        CtcTerm ctcTerm_2 = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        expectedAECtcTerm_2.setCtcTerm(ctcTerm_2);
        LowLevelTerm llt_2 = new LowLevelTerm();
        llt_2.setMeddraTerm("meddraTerm_2");
        llt_2.setMeddraCode("meddraCode_2");
        expectedAECtcTerm_2.setOtherMeddraTerm(llt_2);
        
        xmlStudy.getExpectedAECtcTerms().add(expectedAECtcTerm_1);
        xmlStudy.getExpectedAECtcTerms().add(expectedAECtcTerm_2);
        
      //For EasyMock Return 
        List<LowLevelTerm> llts_1 = new ArrayList<LowLevelTerm>();
        llts_1.add(llt_1);
        
      //For EasyMock Return 
        List<CtcTerm> ctcTerms_2 = new ArrayList<CtcTerm>();
        ctcTerms_2.add(ctcTerm_2);
      //For EasyMock Return 
        List<LowLevelTerm> llts_2 = new ArrayList<LowLevelTerm>();
        llts_2.add(llt_2);
        
        EasyMock.expect(lowLevelTermDao.getByMeddraCodeandVersion(llt_1.getMeddraCode(),dest.getOtherMeddra().getId())).andReturn(llts_1).anyTimes();
        
        EasyMock.expect(lowLevelTermDao.getByMeddraCodeandVersion(llt_2.getMeddraCode(),dest.getOtherMeddra().getId())).andReturn(llts_2).anyTimes();
        EasyMock.expect(ctcTermDao.getByCtepCodeandVersion(ctcTerm_2.getCtepCode(),aeTerminology.getCtcVersion().getId())).andReturn(ctcTerms_2).anyTimes();
        
        replayMocks();
        
        migrator.migrate(xmlStudy, dest, outcome);
        
        verifyMocks();
        
        assertNotNull(outcome);
        assertEquals(1, outcome.getMessages().size());
	}
	
	public void testMigrateExpectedAE_NoOtherMeddra(){
		AeTerminology aeTerminology = Fixtures.createCtcV3Terminology(xmlStudy);
		MeddraVersion otherMeddra = new MeddraVersion();
        xmlStudy.setOtherMeddra(otherMeddra);
        xmlStudy.setAeTerminology(aeTerminology);
        
        dest.setAeTerminology(aeTerminology);
        
        ExpectedAECtcTerm expectedAECtcTerm_1 = new ExpectedAECtcTerm();
        CtcTerm ctcTerm_1 = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        expectedAECtcTerm_1.setCtcTerm(ctcTerm_1);
        LowLevelTerm llt_1 = new LowLevelTerm();
        llt_1.setMeddraTerm("meddraTerm_1");
        llt_1.setMeddraCode("meddraCode_1");
        expectedAECtcTerm_1.setOtherMeddraTerm(llt_1);
        
        
        ExpectedAECtcTerm expectedAECtcTerm_2 = new ExpectedAECtcTerm();
        CtcTerm ctcTerm_2 = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        expectedAECtcTerm_2.setCtcTerm(ctcTerm_2);
        LowLevelTerm llt_2 = new LowLevelTerm();
        llt_2.setMeddraTerm("meddraTerm_2");
        llt_2.setMeddraCode("meddraCode_2");
        expectedAECtcTerm_2.setOtherMeddraTerm(llt_2);
        
        xmlStudy.getExpectedAECtcTerms().add(expectedAECtcTerm_1);
        xmlStudy.getExpectedAECtcTerms().add(expectedAECtcTerm_2);
        
      //For EasyMock Return 
        List<CtcTerm> ctcTerms_1 = new ArrayList<CtcTerm>();
        ctcTerms_1.add(ctcTerm_1);
      //For EasyMock Return 
        List<LowLevelTerm> llts_1 = new ArrayList<LowLevelTerm>();
        llts_1.add(llt_1);
        
      //For EasyMock Return 
        List<CtcTerm> ctcTerms_2 = new ArrayList<CtcTerm>();
        ctcTerms_2.add(ctcTerm_2);
      //For EasyMock Return 
        List<LowLevelTerm> llts_2 = new ArrayList<LowLevelTerm>();
        llts_2.add(llt_2);
        
        
        migrator.migrate(xmlStudy, dest, outcome);
        
        
        assertNotNull(outcome.getMessages());

	}
	
}
