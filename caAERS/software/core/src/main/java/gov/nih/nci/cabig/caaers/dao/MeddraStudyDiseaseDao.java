package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;

/**
 * This class implements the Data access related operations for the MeddraStudyDisease domain
 * object.
 * 
 * @author krikor krumlian
 */
public class MeddraStudyDiseaseDao extends GridIdentifiableDao<MeddraStudyDisease> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    public Class<MeddraStudyDisease> domainClass() {
        return MeddraStudyDisease.class;
    }

}
