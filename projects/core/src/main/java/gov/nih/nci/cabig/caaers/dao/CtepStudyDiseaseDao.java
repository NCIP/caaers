package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;


/**
 * @author kulasekaran
 */
public class CtepStudyDiseaseDao extends GridIdentifiableDao<CtepStudyDisease> {
    public Class<CtepStudyDisease> domainClass() {
        return CtepStudyDisease.class;
    }    
    
}
