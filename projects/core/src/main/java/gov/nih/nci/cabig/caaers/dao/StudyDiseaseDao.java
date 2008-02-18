package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyDisease;


/**
 * @author kulasekaran
 */
public class StudyDiseaseDao extends GridIdentifiableDao<StudyDisease> {
    /**
	 * Get the Class representation of the domain object that this DAO is
	 * representing.
	 * 
	 * @return Class representation of the domain object that this DAO is
	 *         representing.
	 */
	public Class<StudyDisease> domainClass() {
        return StudyDisease.class;
    }    
    
}
