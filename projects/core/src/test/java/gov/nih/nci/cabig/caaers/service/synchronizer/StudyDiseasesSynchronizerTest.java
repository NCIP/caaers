package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Condition;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCondition;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
/**
 * @author Monish Domla
 * @author Biju Joseph (refactored)
 *
 */
public class StudyDiseasesSynchronizerTest  extends AbstractTestCase{

	Study dbStudy;
	Study xmlStudy;
	StudyDiseasesSynchronizer studyDiseasesSynchronizer;
	DomainObjectImportOutcome<Study> outcome;
	CtepStudyDisease ctepStudyDisease1,ctepStudyDisease2,ctepStudyDisease1a,ctepStudyDisease2a,ctepStudyDisease3a;
	DiseaseTerm diseaseTerm1,diseaseTerm2,diseaseTerm1a,diseaseTerm2a,diseaseTerm3a ;
	MeddraStudyDisease meddraStudyDisease1,meddraStudyDisease2,meddraStudyDisease1a,meddraStudyDisease2a,meddraStudyDisease3a;
	LowLevelTerm lowLevelTerm1,lowLevelTerm2,lowLevelTerm1a,lowLevelTerm2a,lowLevelTerm3a;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studyDiseasesSynchronizer = new StudyDiseasesSynchronizer();
		outcome = new DomainObjectImportOutcome<Study>();
		
		
		dbStudy = Fixtures.createStudy("abcd");
		xmlStudy = Fixtures.createStudy("abcd");
		
	}
	
	public void testMigrate_WhenXMLStudyIsNotHavingDiseases(){
		dbStudy.setDiseaseTerminology(Fixtures.createDiseaseTerminology(dbStudy));
		xmlStudy.setDiseaseTerminology(Fixtures.createDiseaseTerminology(xmlStudy));
		
		diseaseTerm1 = new DiseaseTerm();
		diseaseTerm1.setCtepTerm("ctepTerm1");
		
		diseaseTerm2 = new DiseaseTerm();
		diseaseTerm2.setCtepTerm("ctepTerm2");
		
		ctepStudyDisease1 = Fixtures.createCtepStudyDisease(dbStudy,diseaseTerm1);
		ctepStudyDisease1.setId(1);
		ctepStudyDisease2 = Fixtures.createCtepStudyDisease(dbStudy,diseaseTerm2);
		ctepStudyDisease2.setId(2);
		

		studyDiseasesSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		assertEquals(2, dbStudy.getCtepStudyDiseases().size());
		
	}
	
	public void testCtepStudyDiseasesAddedSynch() {
		
		dbStudy.setDiseaseTerminology(Fixtures.createDiseaseTerminology(dbStudy));
		xmlStudy.setDiseaseTerminology(Fixtures.createDiseaseTerminology(xmlStudy));
		
		diseaseTerm1 = new DiseaseTerm();
		diseaseTerm1.setCtepTerm("ctepTerm1");
		
		diseaseTerm2 = new DiseaseTerm();
		diseaseTerm2.setCtepTerm("ctepTerm2");
		
		ctepStudyDisease1 = Fixtures.createCtepStudyDisease(dbStudy,diseaseTerm1);
		ctepStudyDisease1.setId(1);
		ctepStudyDisease2 = Fixtures.createCtepStudyDisease(dbStudy,diseaseTerm2);
		ctepStudyDisease2.setId(2);
		
		diseaseTerm1a = new DiseaseTerm();
		diseaseTerm1a.setCtepTerm("ctepTerm1");
		
		diseaseTerm2a = new DiseaseTerm();
		diseaseTerm2a.setCtepTerm("ctepTerm2");
		
		diseaseTerm3a = new DiseaseTerm();
		diseaseTerm3a.setCtepTerm("ctepTerm3a");
		
		ctepStudyDisease1a = Fixtures.createCtepStudyDisease(xmlStudy,diseaseTerm1a);
		ctepStudyDisease2a = Fixtures.createCtepStudyDisease(xmlStudy,diseaseTerm2a);
		ctepStudyDisease3a = Fixtures.createCtepStudyDisease(xmlStudy,diseaseTerm3a);
		
		
		studyDiseasesSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		assertEquals(3, dbStudy.getCtepStudyDiseases().size());
		
	}
	
	public void testMedraStudyDiseasesAddedSynch() {
		

		dbStudy.setDiseaseTerminology(Fixtures.createDiseaseTerminology(dbStudy));
		dbStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);
		
		xmlStudy.setDiseaseTerminology(Fixtures.createDiseaseTerminology(xmlStudy));
		xmlStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);
		
		meddraStudyDisease1 = new MeddraStudyDisease();
		meddraStudyDisease1.setId(1);
		meddraStudyDisease1.setMeddraCode("meddraCode1");
		lowLevelTerm1 = new LowLevelTerm();
		lowLevelTerm1.setMeddraCode("meddraCode1");
		meddraStudyDisease1.setTerm(lowLevelTerm1);
		
		meddraStudyDisease2 = new MeddraStudyDisease();
		meddraStudyDisease2.setId(2);
		meddraStudyDisease2.setMeddraCode("meddraCode2");
		lowLevelTerm2 = new LowLevelTerm();
		lowLevelTerm2.setMeddraCode("meddraCode2");
		meddraStudyDisease2.setTerm(lowLevelTerm2);
		
		dbStudy.addMeddraStudyDisease(meddraStudyDisease1);
		dbStudy.addMeddraStudyDisease(meddraStudyDisease2);
		
		
		meddraStudyDisease1a = new MeddraStudyDisease();
		meddraStudyDisease1a.setMeddraCode("meddraCode1");
		lowLevelTerm1a = new LowLevelTerm();
		lowLevelTerm1a.setMeddraCode("meddraCode1");
		meddraStudyDisease1a.setTerm(lowLevelTerm1a);
		
		meddraStudyDisease2a = new MeddraStudyDisease();
		meddraStudyDisease2a.setMeddraCode("meddraCode2");
		lowLevelTerm2a = new LowLevelTerm();
		lowLevelTerm2a.setMeddraCode("meddraCode2");
		meddraStudyDisease2a.setTerm(lowLevelTerm2a);
		
		
		meddraStudyDisease3a = new MeddraStudyDisease();
		meddraStudyDisease3a.setMeddraCode("meddraCode3a");
		lowLevelTerm3a = new LowLevelTerm();
		lowLevelTerm3a.setMeddraCode("meddraCode3a");
		meddraStudyDisease3a.setTerm(lowLevelTerm3a);
		
		
		xmlStudy.addMeddraStudyDisease(meddraStudyDisease1a);
		xmlStudy.addMeddraStudyDisease(meddraStudyDisease2a);
		xmlStudy.addMeddraStudyDisease(meddraStudyDisease3a);
		
		studyDiseasesSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		assertEquals(3, dbStudy.getMeddraStudyDiseases().size());
		
	}
	
	
	public void testMigrateStudyCondition(){

		dbStudy.setDiseaseTerminology(Fixtures.createDiseaseTerminology(dbStudy));
		dbStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.OTHER);
		
		xmlStudy.setDiseaseTerminology(Fixtures.createDiseaseTerminology(xmlStudy));
		xmlStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.OTHER);
		
		Condition c1 = new Condition();
		c1.setConditionName("c1");
		c1.setId(1);
		StudyCondition sc1 = Fixtures.createStudyCondition(dbStudy, c1);
		
		Condition c2 = new Condition();
		c2.setConditionName("c2");
		c2.setId(2);
		StudyCondition sc2 = Fixtures.createStudyCondition(dbStudy, c2);
		
		assertEquals(2, dbStudy.getStudyConditions().size());
		assertSame(sc2, dbStudy.getActiveStudyDiseases().get(1));
		
		Condition c3 = new Condition();
		c3.setConditionName("c1");
		c3.setId(1);
		StudyCondition sc3 = Fixtures.createStudyCondition(xmlStudy, c3);
		
		
		Condition c4 = new Condition();
		c4.setId(3);
		c4.setConditionName("c3");
		StudyCondition sc4 = Fixtures.createStudyCondition(xmlStudy, c4);
		
		studyDiseasesSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		assertEquals(2, dbStudy.getActiveStudyDiseases().size());
		assertSame(sc4, dbStudy.getActiveStudyDiseases().get(1));
	}
}
