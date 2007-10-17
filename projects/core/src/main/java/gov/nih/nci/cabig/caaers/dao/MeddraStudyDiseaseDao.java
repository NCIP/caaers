package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.MeddraStudyDisease;


/**
 * @author krikor krumlian
 */
public class MeddraStudyDiseaseDao extends GridIdentifiableDao<MeddraStudyDisease> {
    public Class<MeddraStudyDisease> domainClass() {
        return MeddraStudyDisease.class;
    }    
    
}
