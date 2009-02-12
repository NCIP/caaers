package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyCondition;

/**
 * @author Ion C. Olaru
 */

@CaaersUseCases( { CREATE_STUDY })
public class StudyConditionDaoTest extends DaoTestCase<StudyConditionDao> {
    public void testGetDomainClass(){
        Object obj = getDao().domainClass();
        assertNotNull(obj);
    }
}