package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.StudyEvaluationPeriodsMigrator; 

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

public class StudyEvaluationPeriodsMigratorTest extends AbstractTestCase {

	private CtcTermDao ctcTermDao;
	private LowLevelTermDao lowLevelTermDao;
	
	private Study xmlStudy;
    private DomainObjectImportOutcome<Study> outcome;
    private Study dest;
    private StudyEvaluationPeriodsMigrator migrator;
    
	
	@Override
    protected void setUp() throws Exception {
		migrator = new StudyEvaluationPeriodsMigrator();
		xmlStudy = Fixtures.createStudy("StudyEvaluationPeriodsMigrator");
		dest = new Study();
		outcome = new DomainObjectImportOutcome<Study>();
		
		ctcTermDao = registerDaoMockFor(CtcTermDao.class);
		lowLevelTermDao = registerDaoMockFor(LowLevelTermDao.class);
		migrator.setCtcTermDao(ctcTermDao);
		migrator.setLowLevelTermDao(lowLevelTermDao);
	}
	
	public void testMigrateEvaluationPeriods_CtcTerm(){
		AeTerminology aeTerminology = Fixtures.createCtcV3Terminology(xmlStudy);
		MeddraVersion otherMeddra = new MeddraVersion();
		otherMeddra.setId(4);
		otherMeddra.setName("MedDRA v9");
        xmlStudy.setOtherMeddra(otherMeddra);
        xmlStudy.setAeTerminology(aeTerminology);
        
        dest.setAeTerminology(aeTerminology);
        dest.setOtherMeddra(otherMeddra);
        
        Epoch epoch = new Epoch();
        epoch.setName("EPOCH_BASELINE");
        epoch.setDescriptionText("EPOCH_BASELINE_DESC");
        epoch.setEpochOrder(1);
        Arm arm1 = new Arm();
        arm1.setName("ARM_BASELINE");
        arm1.setDescriptionText("ARM_BASELINE_DESC");
        SolicitedAdverseEvent sAE1 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE1.setCtcterm(ctcTerm);
        arm1.getSolicitedAdverseEvents().add(sAE1);
        epoch.getArms().add(arm1);
        xmlStudy.getEpochs().add(epoch);
        
        //For EasyMock Return 
        List<CtcTerm> ctcTerms = new ArrayList<CtcTerm>();
        ctcTerms.add(ctcTerm);
        
        EasyMock.expect(ctcTermDao.getByCtepCodeandVersion(ctcTerm.getCtepCode(),aeTerminology.getCtcVersion().getId())).andReturn(ctcTerms).anyTimes();
        replayMocks();
        
        migrator.migrate(xmlStudy, dest, outcome);
        
        verifyMocks();
        
        assertNotNull(dest.getEpochs());
        assertEquals(1, dest.getEpochs().size());
        assertEquals("EPOCH_BASELINE", dest.getEpochs().get(0).getName());
        assertEquals("EPOCH_BASELINE_DESC", dest.getEpochs().get(0).getDescriptionText());
        assertEquals(new Integer(1), dest.getEpochs().get(0).getEpochOrder());
        assertEquals(1, dest.getEpochs().get(0).getArms().size());
        assertEquals("ARM_BASELINE", dest.getEpochs().get(0).getArms().get(0).getName());
        assertEquals("ARM_BASELINE_DESC", dest.getEpochs().get(0).getArms().get(0).getDescriptionText());
        assertEquals(1, dest.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().size());
        assertEquals("ctepTerm_1", dest.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(0).getCtcterm().getCtepTerm());
        assertEquals("ctepCode_1", dest.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(0).getCtcterm().getCtepCode());
        
	}
	
	public void testMigrateEvaluationPeriods_LowLevelTerm(){
		AeTerminology aeTerminology = Fixtures.createMedDRATerminology(xmlStudy);
		MeddraVersion meddra = new MeddraVersion();
		meddra.setId(4);
		meddra.setName("MedDRA v9");
		aeTerminology.setMeddraVersion(meddra);
        xmlStudy.setOtherMeddra(meddra);
        xmlStudy.setAeTerminology(aeTerminology);
        
        dest.setAeTerminology(aeTerminology);
        dest.setOtherMeddra(meddra);
        
        Epoch epoch = new Epoch();
        epoch.setName("EPOCH_BASELINE");
        epoch.setDescriptionText("EPOCH_BASELINE_DESC");
        epoch.setEpochOrder(1);
        Arm arm1 = new Arm();
        arm1.setName("ARM_BASELINE");
        arm1.setDescriptionText("ARM_BASELINE_DESC");
        SolicitedAdverseEvent sAE1 = new SolicitedAdverseEvent();
        LowLevelTerm llt = new LowLevelTerm();
        llt.setMeddraTerm("meddraTerm_1");
        llt.setMeddraCode("meddraCode_1");
        sAE1.setLowLevelTerm(llt);
        arm1.getSolicitedAdverseEvents().add(sAE1);
        epoch.getArms().add(arm1);
        xmlStudy.getEpochs().add(epoch);
        
        //For EasyMock Return 
        List<LowLevelTerm> llts = new ArrayList<LowLevelTerm>();
        llts.add(llt);
        
        EasyMock.expect(lowLevelTermDao.getByMeddraCodeandVersion(llt.getMeddraCode(),aeTerminology.getMeddraVersion().getId())).andReturn(llts).anyTimes();
        replayMocks();
        
        migrator.migrate(xmlStudy, dest, outcome);
        
        verifyMocks();
        
        assertNotNull(dest.getEpochs());
        assertEquals(1, dest.getEpochs().size());
        assertEquals("EPOCH_BASELINE", dest.getEpochs().get(0).getName());
        assertEquals("EPOCH_BASELINE_DESC", dest.getEpochs().get(0).getDescriptionText());
        assertEquals(new Integer(1), dest.getEpochs().get(0).getEpochOrder());
        assertEquals(1, dest.getEpochs().get(0).getArms().size());
        assertEquals("ARM_BASELINE", dest.getEpochs().get(0).getArms().get(0).getName());
        assertEquals("ARM_BASELINE_DESC", dest.getEpochs().get(0).getArms().get(0).getDescriptionText());
        assertEquals(1, dest.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().size());
        assertEquals("meddraTerm_1", dest.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(0).getLowLevelTerm().getMeddraTerm());
        assertEquals("meddraCode_1", dest.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(0).getLowLevelTerm().getMeddraCode());
        
	}
	
	
	public void testMigrateEvaluationPeriods_OtherTerm(){
		AeTerminology aeTerminology = Fixtures.createMedDRATerminology(xmlStudy);
		MeddraVersion meddra = new MeddraVersion();
		meddra.setId(4);
		meddra.setName("MedDRA v9");
		aeTerminology.setMeddraVersion(meddra);
        xmlStudy.setOtherMeddra(meddra);
        xmlStudy.setAeTerminology(aeTerminology);
        
        dest.setAeTerminology(aeTerminology);
        dest.setOtherMeddra(meddra);
        
        Epoch epoch = new Epoch();
        epoch.setName("EPOCH_BASELINE");
        epoch.setDescriptionText("EPOCH_BASELINE_DESC");
        epoch.setEpochOrder(1);
        Arm arm1 = new Arm();
        arm1.setName("ARM_BASELINE");
        arm1.setDescriptionText("ARM_BASELINE_DESC");
        SolicitedAdverseEvent sAE1 = new SolicitedAdverseEvent();
        LowLevelTerm llt = new LowLevelTerm();
        llt.setMeddraTerm("otherTerm_1");
        llt.setMeddraCode("otherCode_1");
        sAE1.setOtherTerm(llt);
        arm1.getSolicitedAdverseEvents().add(sAE1);
        epoch.getArms().add(arm1);
        xmlStudy.getEpochs().add(epoch);
        
        //For EasyMock Return 
        List<LowLevelTerm> llts = new ArrayList<LowLevelTerm>();
        llts.add(llt);
        
        EasyMock.expect(lowLevelTermDao.getByMeddraCodeandVersion(llt.getMeddraCode(),aeTerminology.getMeddraVersion().getId())).andReturn(llts).anyTimes();
        replayMocks();
        
        migrator.migrate(xmlStudy, dest, outcome);
        
        verifyMocks();
        
        assertNotNull(dest.getEpochs());
        assertEquals(1, dest.getEpochs().size());
        assertEquals("EPOCH_BASELINE", dest.getEpochs().get(0).getName());
        assertEquals("EPOCH_BASELINE_DESC", dest.getEpochs().get(0).getDescriptionText());
        assertEquals(new Integer(1), dest.getEpochs().get(0).getEpochOrder());
        assertEquals(1, dest.getEpochs().get(0).getArms().size());
        assertEquals("ARM_BASELINE", dest.getEpochs().get(0).getArms().get(0).getName());
        assertEquals("ARM_BASELINE_DESC", dest.getEpochs().get(0).getArms().get(0).getDescriptionText());
        assertEquals(1, dest.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().size());
        assertEquals("otherTerm_1", dest.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(0).getOtherTerm().getMeddraTerm());
        assertEquals("otherCode_1", dest.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().get(0).getOtherTerm().getMeddraCode());
        
	}
	
}
