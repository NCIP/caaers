package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Epoch;

import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class EpochDaoTest extends DaoNoSecurityTestCase<EpochDao> {

    public void testGetCountReportingPeriodsByEpochId() {
        int c1 = getDao().getCountReportingPeriodsByEpochId(-10);
        assertEquals(3, c1);

        int c2 = getDao().getCountReportingPeriodsByEpochId(-11);
        assertEquals(1, c2);
    }

    public void testSave() {
        Epoch e = getDao().getById(-10);
        e.setVersion(7);
        e.setName("A");
        getDao().save(e);
        interruptSession();
        e = getDao().getById(-10);
        assertEquals(1, e.getVersion().intValue());
        assertEquals("A", e.getName());
    }
    
}