package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;


/**
 * @author krikor krumlian
 */
public class MeddraStudyDiseaseDao extends GridIdentifiableDao<MeddraStudyDisease> {
    /**
	 * Get the Class representation of the domain object that this DAO is
	 * representing.
	 * 
	 * @return Class representation of the domain object that this DAO is
	 *         representing.
	 */
	public Class<MeddraStudyDisease> domainClass() {
        return MeddraStudyDisease.class;
    }    
    
}
