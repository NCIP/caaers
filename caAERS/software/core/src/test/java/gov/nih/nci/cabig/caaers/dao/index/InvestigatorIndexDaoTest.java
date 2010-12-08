package gov.nih.nci.cabig.caaers.dao.index;

import junit.framework.TestCase;

/**
 * @author: Biju Joseph
 */
public class InvestigatorIndexDaoTest extends TestCase {
    InvestigatorIndexDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = new InvestigatorIndexDao();

    }

    public void testEntityIdColumnName() throws Exception {
        assertEquals("participant_id", dao.entityIdColumnName());
    }

    public void testIndexTableName() throws Exception {
        assertEquals("participant_index", dao.indexTableName());
    }

    public void testSequenceName() throws Exception {
        assertEquals("seq_participant_index_id", dao.sequenceName());
    }

}
