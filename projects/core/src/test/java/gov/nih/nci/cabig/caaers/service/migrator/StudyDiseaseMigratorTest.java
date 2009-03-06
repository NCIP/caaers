package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.DiseaseTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerminology;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

public class StudyDiseaseMigratorTest extends
		gov.nih.nci.cabig.caaers.AbstractTestCase {

	private DiseaseTermDao diseaseTermDao;
	private LowLevelTermDao lowLevelTermDao;
	Study xstreamStudy;
	DomainObjectImportOutcome<Study> outcome;
	Study dest;
	StudyDiseaseMigrator migrator;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		outcome = new  DomainObjectImportOutcome<Study>();
		dest = new Study();
		xstreamStudy = Fixtures.createStudy("abcd");
		migrator = new StudyDiseaseMigrator();
		diseaseTermDao = registerDaoMockFor(DiseaseTermDao.class);
		lowLevelTermDao = registerDaoMockFor(LowLevelTermDao.class);
		migrator.setDiseaseTermDao(diseaseTermDao);
		migrator.setLowLevelTermDao(lowLevelTermDao);
	}
	
	public void testMigrate() {
		DiseaseCategory dc = new DiseaseCategory();
		dc.setName("xxx");
		DiseaseTerm ctepTerm = new DiseaseTerm();
		ctepTerm.setCategory(dc);
		ctepTerm.setCtepTerm("ctep");
		ctepTerm.setMeddraCode("medra");
		ctepTerm.setTerm("abcd");
		
		CtepStudyDisease ctepStudyDisease = Fixtures.createCtepStudyDisease(xstreamStudy, ctepTerm);
		DiseaseTerminology diseaseTerminology =Fixtures.createDiseaseTerminology(dest);
		dest.setDiseaseTerminology(diseaseTerminology);
		
		
		EasyMock.expect(diseaseTermDao.getByTermName(ctepTerm.getTerm())).andReturn(ctepTerm);
		//EasyMock.expect(diseaseTermDao.getByMeddra(ctepTerm.getMeddraCode())).andReturn(ctepTerm);
		replayMocks();
		
		migrator.migrate(xstreamStudy, dest, outcome);
		System.out.println(outcome.getMessages());
		assertTrue(outcome.getMessages().isEmpty());
		assertEquals(1, dest.getCtepStudyDiseases().size() );
		CtepStudyDisease newCtepStudyDisease = dest.getCtepStudyDiseases().get(0);
		
		assertEquals("Worong Term",ctepTerm.getFullName(),newCtepStudyDisease.getTermName());
		
	}
	
	public void testMigrate_CtepTermHavingMeddraCode() {
		DiseaseCategory dc = new DiseaseCategory();
		dc.setName("xxx");
		DiseaseTerm ctepTerm = new DiseaseTerm();
		ctepTerm.setCategory(dc);
		ctepTerm.setMeddraCode("medra");
		
		CtepStudyDisease ctepStudyDisease = Fixtures.createCtepStudyDisease(xstreamStudy, ctepTerm);
		DiseaseTerminology diseaseTerminology =Fixtures.createDiseaseTerminology(dest);
		dest.setDiseaseTerminology(diseaseTerminology);
		
		
		//EasyMock.expect(diseaseTermDao.getByTermName(ctepTerm.getTerm())).andReturn(ctepTerm);
		EasyMock.expect(diseaseTermDao.getByMeddra(ctepTerm.getMeddraCode())).andReturn(ctepTerm);
		replayMocks();
		
		migrator.migrate(xstreamStudy, dest, outcome);
		System.out.println(outcome.getMessages());
		assertTrue(outcome.getMessages().isEmpty());
		assertEquals(1, dest.getCtepStudyDiseases().size() );
		CtepStudyDisease newCtepStudyDisease = dest.getCtepStudyDiseases().get(0);
		
		assertEquals("Worong Term",ctepTerm.getFullName(),newCtepStudyDisease.getTermName());
		
	}
	
	public void testMigrate_UnIdentifiedCtepTerm_AndHavingMeddraCode() {
		DiseaseCategory dc = new DiseaseCategory();
		dc.setName("xxx");
		DiseaseTerm ctepTerm = new DiseaseTerm();
		ctepTerm.setCategory(dc);
		ctepTerm.setMeddraCode("medra");
		ctepTerm.setTerm("kkk");
		CtepStudyDisease ctepStudyDisease = Fixtures.createCtepStudyDisease(xstreamStudy, ctepTerm);
		DiseaseTerminology diseaseTerminology =Fixtures.createDiseaseTerminology(dest);
		dest.setDiseaseTerminology(diseaseTerminology);
		
		
		EasyMock.expect(diseaseTermDao.getByTermName(ctepTerm.getTerm())).andReturn(null);
		EasyMock.expect(diseaseTermDao.getByMeddra(ctepTerm.getMeddraCode())).andReturn(ctepTerm);
		replayMocks();
		
		migrator.migrate(xstreamStudy, dest, outcome);
		System.out.println(outcome.getMessages());
		assertTrue(outcome.getMessages().isEmpty());
		assertEquals(1, dest.getCtepStudyDiseases().size() );
		CtepStudyDisease newCtepStudyDisease = dest.getCtepStudyDiseases().get(0);
		
		assertEquals("Worong Term",ctepTerm.getFullName(),newCtepStudyDisease.getTermName());
		
	}
	
	
	public void testMigrate_MeddraStudyDisease() {
		
		LowLevelTerm lowLevelTerm = new LowLevelTerm();
		lowLevelTerm.setMeddraCode("medra");
		lowLevelTerm.setMeddraTerm("meddra");
		
		List<LowLevelTerm> termList = new ArrayList<LowLevelTerm>();
		termList.add(lowLevelTerm);
		
		MeddraStudyDisease meddraStudyDisease = Fixtures.createMeddraStudyDisease(xstreamStudy, lowLevelTerm);
		xstreamStudy.addMeddraStudyDisease(meddraStudyDisease);
		
		DiseaseTerminology diseaseTerminology =Fixtures.createDiseaseTerminology(dest);
		diseaseTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);
		dest.setDiseaseTerminology(diseaseTerminology);
		
		
		EasyMock.expect(lowLevelTermDao.getByMeddraCode(meddraStudyDisease.getMeddraCode())).andReturn(termList);
		
		replayMocks();
		
		migrator.migrate(xstreamStudy, dest, outcome);
		System.out.println(outcome.getMessages());
		assertTrue(outcome.getMessages().isEmpty());
		assertEquals(1, dest.getMeddraStudyDiseases().size() );
		MeddraStudyDisease newMeddraStudyDisease = dest.getMeddraStudyDiseases().get(0);
		
		assertEquals("Worong Term",meddraStudyDisease.getTermName(), newMeddraStudyDisease.getTermName());
		
	}
	
	public void testMigrate_HavingUnIdentified_MeddraStudyDisease() {
		
		LowLevelTerm lowLevelTerm = new LowLevelTerm();
		lowLevelTerm.setMeddraCode("medra");
		lowLevelTerm.setMeddraTerm("meddra");
		
		List<LowLevelTerm> termList = new ArrayList<LowLevelTerm>();
		//termList.add(lowLevelTerm);
		
		MeddraStudyDisease meddraStudyDisease = Fixtures.createMeddraStudyDisease(xstreamStudy, lowLevelTerm);
		xstreamStudy.addMeddraStudyDisease(meddraStudyDisease);
		
		DiseaseTerminology diseaseTerminology =Fixtures.createDiseaseTerminology(dest);
		diseaseTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);
		dest.setDiseaseTerminology(diseaseTerminology);
		
		
		EasyMock.expect(lowLevelTermDao.getByMeddraCode(meddraStudyDisease.getMeddraCode())).andReturn(termList);
		
		replayMocks();
		
		migrator.migrate(xstreamStudy, dest, outcome);
		System.out.println(outcome.getMessages());
		assertEquals(1,outcome.getMessages().size());
		
	}

}
