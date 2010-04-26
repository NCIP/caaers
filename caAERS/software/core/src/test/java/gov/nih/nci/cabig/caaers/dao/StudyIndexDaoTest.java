package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.index.StudyIndexDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.index.StudyIndex;

public class StudyIndexDaoTest extends DaoNoSecurityTestCase<StudyIndexDao> {
	StudyDao studyDao ; 
	@Override
	protected void setUp() throws Exception {
		// change the security interceptor with stub.
		super.setUp();
		studyDao = (StudyDao)getApplicationContext().getBean("studyDao");
	}
	
	
	public void testSave() throws Exception {
        Study study = studyDao.getById(-2);
        String userName = "srakkala";
        StudyIndex studyIndex = new StudyIndex();
        studyIndex.setLoginId(userName);
        studyIndex.setStudy(study);
        getDao().save(studyIndex);
    }
}
