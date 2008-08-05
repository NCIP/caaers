package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LabLoad;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import java.util.List;

public class LabLoadDaoTest extends DaoTestCase<LabLoadDao> {
	
	private LabLoadDao labLoadDao;
	//private LabViewerLabRepository labViewerLabRepository;
	private StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	private ParticipantDao participantDao;
	private StudyDao studyDao;
	
	//private TransactionTemplate transactionTemplate;
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        labLoadDao = getDao();

        //transactionTemplate = (TransactionTemplate) getApplicationContext().getBean(
          //              "transactionTemplate");
        
        studyParticipantAssignmentDao = (StudyParticipantAssignmentDao) getApplicationContext().getBean(
                        "studyParticipantAssignmentDao");
        participantDao = (ParticipantDao) getApplicationContext().getBean("participantDao");
        
        studyDao = (StudyDao)getApplicationContext().getBean("studyDao");
        
        //labViewerLabRepository = (LabViewerLabRepository)getApplicationContext().getBean("labViewerLabRepository");
        //labViewerLabRepository
        
    }
	
	public void testTest(){
		assertTrue(true);
	}

	
//	Commented by Biju (close : CAAERS-490 after fixing this)
	
//	public void testSave() {
//
//		LabLoad toCreate = new LabLoad();
//		//expectCreate(toCreate);
//		toCreate.setName("TEST NAME XYZ");
//		toCreate.setResult("900.2");
//		toCreate.setUnits("cpq");
//		Identifier i = new Identifier();
//		i.setType("MRN");
//		i.setValue("mrn001");
//
//		Identifier i2 = new Identifier();
//		i2.setType("Protocol Authority Identifier");
//		i2.setValue("RTOG-0330");
//		
//		Participant p = participantDao.getByIdentifier(i);
//        Study s = studyDao.getByIdentifier(i2);
//        
//       // System.out.println(p.getFirstName());
//        //System.out.println(s.getShortTitle());
//		
//		StudyParticipantAssignment assignment = studyParticipantAssignmentDao.getAssignment(p,s);
//
//		toCreate.setAssignment(assignment);
//		labLoadDao.save(toCreate);
//		
//		
//
//        assertNotNull("lab viewer id is null", toCreate.getId());
//        
//    }

//	Commented by Biju (close : CAAERS-490 after fixing this)
	
//	public void testSelect() {
//		Identifier i = new Identifier();
//		i.setType("MRN");
//		i.setValue("mrn001");
//
//		Identifier i2 = new Identifier();
//		i2.setType("Protocol Authority Identifier");
//		i2.setValue("RTOG-0330");
//		
//		Participant p = participantDao.getByIdentifier(i);
//        Study s = studyDao.getByIdentifier(i2);
//        
//        System.out.println(p.getFirstName());
//        System.out.println(s.getShortTitle());
//		
//		StudyParticipantAssignment assignment = studyParticipantAssignmentDao.getAssignment(p,s);
//		List<LabLoad> l = labLoadDao.getByAssignment(assignment);
//		
//		System.out.println(l.size());
//		for (LabLoad labLoad:l) {
//			System.out.println(labLoad.getName() + " :: " + labLoad.getLabDate());
//		}
//		
//	}

}
