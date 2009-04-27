package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

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
	
	public void testCtepStudyDiseasesAddedSynch() {
		
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
		meddraStudyDisease2a.setTerm(lowLevelTerm3a);
		
		
		xmlStudy.addMeddraStudyDisease(meddraStudyDisease1a);
		xmlStudy.addMeddraStudyDisease(meddraStudyDisease2a);
		xmlStudy.addMeddraStudyDisease(meddraStudyDisease3a);
		
		studyDiseasesSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		assertEquals(3, dbStudy.getMeddraStudyDiseases().size());
		
	}
}
