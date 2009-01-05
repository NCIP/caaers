package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

public class StudyEvaluationPeriodsSynchronizerTest extends AbstractTestCase{

	private Study xmlStudy;
    private DomainObjectImportOutcome<Study> outcome;
    private Study dbStudy;
    private StudyEvaluationPeriodsSynchronizer synchronizer;
	
	@Override
    protected void setUp() throws Exception {
		synchronizer = new StudyEvaluationPeriodsSynchronizer();
		dbStudy  = Fixtures.createStudy("StudyEvaluationPeriodsSynchronizer");
		xmlStudy = Fixtures.createStudy("StudyEvaluationPeriodsSynchronizer");
		outcome = new DomainObjectImportOutcome<Study>();
	}
	
	public void testAddEpochs(){
        Epoch dbEpoch = new Epoch();
        dbEpoch.setName("Baseline");
        dbEpoch.setDescriptionText("Baseline Epoch");
        dbEpoch.setEpochOrder(1);
        Arm arm = new Arm();
        arm.setName("Baseline");
        arm.setDescriptionText("Baseline Arm");
        SolicitedAdverseEvent sAE = new SolicitedAdverseEvent();
        CtcTerm ctcTerm = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE.setCtcterm(ctcTerm);
        sAE.setId(1);
        arm.getSolicitedAdverseEvents().add(sAE);
        dbEpoch.getArms().add(arm);
        dbEpoch.setId(1);
        dbStudy.getEpochs().add(dbEpoch);
        
        Epoch epoch1 = new Epoch();
        epoch1.setName("Baseline");
        epoch1.setDescriptionText("Baseline Epoch");
        epoch1.setEpochOrder(1);
        Arm arm1 = new Arm();
        arm1.setName("Baseline");
        arm1.setDescriptionText("Baseline Arm");
        SolicitedAdverseEvent sAE1 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm1 = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE1.setCtcterm(ctcTerm1);
        arm1.getSolicitedAdverseEvents().add(sAE1);
        epoch1.getArms().add(arm1);
        xmlStudy.getEpochs().add(epoch1);
        
        Epoch epoch2 = new Epoch();
        epoch2.setName("Baseline-2");
        epoch2.setDescriptionText("Baseline-2 Epoch");
        epoch2.setEpochOrder(1);
        Arm arm2 = new Arm();
        arm2.setName("Baseline-2");
        arm2.setDescriptionText("Baseline-2 Arm");
        SolicitedAdverseEvent sAE2 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm2 = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        sAE2.setCtcterm(ctcTerm2);
        arm2.getSolicitedAdverseEvents().add(sAE2);
        epoch2.getArms().add(arm2);
        xmlStudy.getEpochs().add(epoch2);
        
        synchronizer.migrate(dbStudy, xmlStudy, outcome);
        
        assertEquals(2, dbStudy.getEpochs().size());
	}
	

	public void testRemoveEpoch(){
		
		Epoch epoch1 = new Epoch();
        epoch1.setName("Baseline");
        epoch1.setDescriptionText("Baseline Epoch");
        epoch1.setEpochOrder(1);
        Arm arm1 = new Arm();
        arm1.setName("Baseline");
        arm1.setDescriptionText("Baseline Arm");
        SolicitedAdverseEvent sAE1 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm1 = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE1.setCtcterm(ctcTerm1);
        sAE1.setId(1);
        arm1.getSolicitedAdverseEvents().add(sAE1);
        epoch1.getArms().add(arm1);
        epoch1.setId(1);
        dbStudy.getEpochs().add(epoch1);
        
        Epoch epoch2 = new Epoch();
        epoch2.setName("Baseline-2");
        epoch2.setDescriptionText("Baseline-2 Epoch");
        epoch2.setEpochOrder(1);
        Arm arm2 = new Arm();
        arm2.setName("Baseline-2");
        arm2.setDescriptionText("Baseline-2 Arm");
        SolicitedAdverseEvent sAE2 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm2 = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        sAE2.setCtcterm(ctcTerm2);
        sAE2.setId(2);
        arm2.getSolicitedAdverseEvents().add(sAE2);
        epoch2.getArms().add(arm2);
        epoch2.setId(2);
        dbStudy.getEpochs().add(epoch2);
		
        Epoch xmlEpoch = new Epoch();
        xmlEpoch.setName("Baseline-2");
        xmlEpoch.setDescriptionText("Baseline-2 Epoch");
        xmlEpoch.setEpochOrder(1);
        Arm arm = new Arm();
        arm.setName("Baseline-2");
        arm.setDescriptionText("Baseline-2 Arm");
        SolicitedAdverseEvent sAE = new SolicitedAdverseEvent();
        CtcTerm ctcTerm = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        sAE.setCtcterm(ctcTerm);
        arm.getSolicitedAdverseEvents().add(sAE);
        xmlEpoch.getArms().add(arm);
        xmlStudy.getEpochs().add(xmlEpoch);
        
        synchronizer.migrate(dbStudy, xmlStudy, outcome);
        
        assertEquals(1, dbStudy.getEpochs().size());
	}
	
	
	public void testAddSAE(){
		
        Epoch dbEpoch = new Epoch();
        dbEpoch.setName("Baseline");
        dbEpoch.setDescriptionText("Baseline Epoch");
        dbEpoch.setEpochOrder(1);
        Arm arm = new Arm();
        arm.setName("Baseline");
        arm.setDescriptionText("Baseline Arm");
        SolicitedAdverseEvent sAE = new SolicitedAdverseEvent();
        CtcTerm ctcTerm = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE.setCtcterm(ctcTerm);
        sAE.setId(1);
        arm.getSolicitedAdverseEvents().add(sAE);
        dbEpoch.getArms().add(arm);
        dbEpoch.setId(1);
        dbStudy.getEpochs().add(dbEpoch);
        
        Epoch epoch1 = new Epoch();
        epoch1.setName("Baseline");
        epoch1.setDescriptionText("Baseline Epoch");
        epoch1.setEpochOrder(1);
        
        Arm arm1 = new Arm();
        arm1.setName("Baseline");
        arm1.setDescriptionText("Baseline Arm");
        
        SolicitedAdverseEvent sAE1 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm1 = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE1.setCtcterm(ctcTerm1);
        arm1.getSolicitedAdverseEvents().add(sAE1);
        
        SolicitedAdverseEvent sAE2 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm2 = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        sAE2.setCtcterm(ctcTerm2);
        arm1.getSolicitedAdverseEvents().add(sAE2);
        
        SolicitedAdverseEvent sAE3 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm3 = Fixtures.createCtcTerm("ctepTerm_3", "ctepCode_3");
        sAE2.setCtcterm(ctcTerm3);
        arm1.getSolicitedAdverseEvents().add(sAE3);
        
        epoch1.getArms().add(arm1);
        xmlStudy.getEpochs().add(epoch1);
		
        synchronizer.migrate(dbStudy, xmlStudy, outcome);
        
        assertEquals(3, dbStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().size());
        
	}
	
	
	public void testRemoveSAE(){
		
		Epoch epoch1 = new Epoch();
        epoch1.setName("Baseline");
        epoch1.setDescriptionText("Baseline Epoch");
        epoch1.setEpochOrder(1);
        
        Arm arm1 = new Arm();
        arm1.setName("Baseline");
        arm1.setDescriptionText("Baseline Arm");
        
        SolicitedAdverseEvent sAE1 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm1 = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE1.setCtcterm(ctcTerm1);
        arm1.getSolicitedAdverseEvents().add(sAE1);
        
        SolicitedAdverseEvent sAE2 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm2 = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        sAE2.setCtcterm(ctcTerm2);
        arm1.getSolicitedAdverseEvents().add(sAE2);
        
        SolicitedAdverseEvent sAE3 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm3 = Fixtures.createCtcTerm("ctepTerm_3", "ctepCode_3");
        sAE2.setCtcterm(ctcTerm3);
        arm1.getSolicitedAdverseEvents().add(sAE3);
        
        epoch1.getArms().add(arm1);
        dbStudy.getEpochs().add(epoch1);
        
        Epoch epoch1a = new Epoch();
        epoch1a.setName("Baseline");
        epoch1a.setDescriptionText("Baseline Epoch");
        epoch1a.setEpochOrder(1);
        
        Arm arm1a = new Arm();
        arm1a.setName("Baseline");
        arm1a.setDescriptionText("Baseline Arm");
        
        SolicitedAdverseEvent sAE1a = new SolicitedAdverseEvent();
        CtcTerm ctcTerm1a = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE1a.setCtcterm(ctcTerm1a);
        arm1a.getSolicitedAdverseEvents().add(sAE1a);
        
        SolicitedAdverseEvent sAE2a = new SolicitedAdverseEvent();
        CtcTerm ctcTerm2a = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        sAE2a.setCtcterm(ctcTerm2a);
        arm1a.getSolicitedAdverseEvents().add(sAE2a);
        
        epoch1a.getArms().add(arm1a);
        xmlStudy.getEpochs().add(epoch1a);
        
        synchronizer.migrate(dbStudy, xmlStudy, outcome);
        
        assertEquals(2, dbStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().size());
		
	}
	
	public void testNoEpochs(){
		
		Epoch epoch1 = new Epoch();
        epoch1.setName("Baseline");
        epoch1.setDescriptionText("Baseline Epoch");
        epoch1.setEpochOrder(1);
        Arm arm1 = new Arm();
        arm1.setName("Baseline");
        arm1.setDescriptionText("Baseline Arm");
        SolicitedAdverseEvent sAE1 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm1 = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE1.setCtcterm(ctcTerm1);
        sAE1.setId(1);
        arm1.getSolicitedAdverseEvents().add(sAE1);
        epoch1.getArms().add(arm1);
        epoch1.setId(1);
        dbStudy.getEpochs().add(epoch1);
        
        Epoch epoch2 = new Epoch();
        epoch2.setName("Baseline-2");
        epoch2.setDescriptionText("Baseline-2 Epoch");
        epoch2.setEpochOrder(1);
        Arm arm2 = new Arm();
        arm2.setName("Baseline-2");
        arm2.setDescriptionText("Baseline-2 Arm");
        SolicitedAdverseEvent sAE2 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm2 = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        sAE2.setCtcterm(ctcTerm2);
        sAE2.setId(2);
        arm2.getSolicitedAdverseEvents().add(sAE2);
        epoch2.getArms().add(arm2);
        epoch2.setId(2);
        dbStudy.getEpochs().add(epoch2);
        
        synchronizer.migrate(dbStudy, xmlStudy, outcome);
        
        assertEquals(0,dbStudy.getEpochs().size());
		
	}
	
public void testNoSAE(){
		
		Epoch epoch1 = new Epoch();
        epoch1.setName("Baseline");
        epoch1.setDescriptionText("Baseline Epoch");
        epoch1.setEpochOrder(1);
        
        Arm arm1 = new Arm();
        arm1.setName("Baseline");
        arm1.setDescriptionText("Baseline Arm");
        
        SolicitedAdverseEvent sAE1 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm1 = Fixtures.createCtcTerm("ctepTerm_1", "ctepCode_1");
        sAE1.setCtcterm(ctcTerm1);
        arm1.getSolicitedAdverseEvents().add(sAE1);
        
        SolicitedAdverseEvent sAE2 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm2 = Fixtures.createCtcTerm("ctepTerm_2", "ctepCode_2");
        sAE2.setCtcterm(ctcTerm2);
        arm1.getSolicitedAdverseEvents().add(sAE2);
        
        SolicitedAdverseEvent sAE3 = new SolicitedAdverseEvent();
        CtcTerm ctcTerm3 = Fixtures.createCtcTerm("ctepTerm_3", "ctepCode_3");
        sAE2.setCtcterm(ctcTerm3);
        arm1.getSolicitedAdverseEvents().add(sAE3);
        
        epoch1.getArms().add(arm1);
        dbStudy.getEpochs().add(epoch1);
        
        Epoch epoch1a = new Epoch();
        epoch1a.setName("Baseline");
        epoch1a.setDescriptionText("Baseline Epoch");
        epoch1a.setEpochOrder(1);
        
        Arm arm1a = new Arm();
        arm1a.setName("Baseline");
        arm1a.setDescriptionText("Baseline Arm");
        
        epoch1a.getArms().add(arm1a);
        xmlStudy.getEpochs().add(epoch1a);
        
        synchronizer.migrate(dbStudy, xmlStudy, outcome);
        
        assertEquals(0, dbStudy.getEpochs().get(0).getArms().get(0).getSolicitedAdverseEvents().size());
		
	}

}
