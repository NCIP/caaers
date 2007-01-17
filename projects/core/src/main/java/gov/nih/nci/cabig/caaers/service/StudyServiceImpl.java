package gov.nih.nci.cabig.caaers.service;

import java.util.List;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Study;

public class StudyServiceImpl implements StudyService {
	
	StudyDao studyDao;
	//TODO hook esb call
	// ProtocolBroadcastService esbCreateProtocol;

	/**
	 * Search using a sample populate Study object
	 * @param study the study object
	 * @return List of Study objects based on the sample study object
	 * @throws Exception runtime exception object
	 */
	public List<Study> search(Study study) throws Exception {		
		return studyDao.searchByExample(study, true);
	}

	/**
	 * Saves a study object
	 * @param study the study object
  	 * @throws Exception runtime exception object
  	 */
	public void save(Study study) throws Exception {
		//TODO call esb to broadcast protocol, POC
		studyDao.save(study);		
	}
	
	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
}