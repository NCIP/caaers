package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;


/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({ MAPPING_VOCAB })
public class MeddraVersionDaoTest extends DaoTestCase<MeddraVersionDao> {
    public void testGetV3() throws Exception {
        MeddraVersion v9 = getDao().getMeddraV9();
        assertNotNull("V9 not found", v9);
    }
}
