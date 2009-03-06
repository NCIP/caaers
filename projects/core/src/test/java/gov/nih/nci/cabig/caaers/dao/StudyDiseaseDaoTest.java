package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;

public class StudyDiseaseDaoTest extends DaoTestCase<StudyDiseaseDao> {
    public void testGetDomainClass(){
        Object obj = getDao().domainClass();
        assertNotNull(obj);
    }

}