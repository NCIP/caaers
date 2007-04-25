package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyDisease;


/**
 * @author kulasekaran
 */
public class StudyDiseaseDao extends GridIdentifiableDao<StudyDisease> {
    public Class<StudyDisease> domainClass() {
        return StudyDisease.class;
    }    
    
}
