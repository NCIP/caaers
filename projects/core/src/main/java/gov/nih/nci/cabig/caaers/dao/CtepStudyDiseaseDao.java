package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;

/**
 * This class implements the Data access related operations for the CtepStudyDisease domain object.
 * 
 * @author kulasekaran
 */
public class CtepStudyDiseaseDao extends GridIdentifiableDao<CtepStudyDisease> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    public Class<CtepStudyDisease> domainClass() {
        return CtepStudyDisease.class;
    }

}
