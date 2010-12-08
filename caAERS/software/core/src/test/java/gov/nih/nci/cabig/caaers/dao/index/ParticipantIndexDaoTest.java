package gov.nih.nci.cabig.caaers.dao.index;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * ParticipantIndexDao Tester.
 *
 * @author Biju Joseph
 * @since <pre>12/08/2010</pre>
 * 
 */
public class ParticipantIndexDaoTest extends TestCase {
    ParticipantIndexDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = new ParticipantIndexDao();

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
