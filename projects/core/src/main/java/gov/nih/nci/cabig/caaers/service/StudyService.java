package gov.nih.nci.cabig.caaers.service;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * Interface for Services on Study related domain object
 * @author Krikor Krumlian
 */
public interface StudyService {

	/**
	 * Saves a study object
	 * @param study the study object
  	 * @throws Exception runtime exception object
  	 */
	public void save(Study study) throws Exception;
	  
	/**
	 * Search using a sample populate Study object
	 * @param study the study object
	 * @return List of Study objects based on the sample study object
	 * @throws Exception runtime exception object
	 */
	public List <Study> search (Study study) throws Exception;
}