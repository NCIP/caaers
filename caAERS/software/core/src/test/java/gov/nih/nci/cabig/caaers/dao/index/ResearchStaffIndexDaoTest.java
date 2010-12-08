package gov.nih.nci.cabig.caaers.dao.index;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * ResearchStaffIndexDao Tester.
 *
 * @author Biju Joseph
 * @since <pre>12/08/2010</pre>
 * 
 */
public class ResearchStaffIndexDaoTest extends TestCase {

    ResearchStaffIndexDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = new ResearchStaffIndexDao();

    }

    public void testEntityIdColumnName() throws Exception {
        assertEquals("researchstaff_id", dao.entityIdColumnName());
    }

    public void testIndexTableName() throws Exception {
        assertEquals("researchstaff_index", dao.indexTableName());
    }

    public void testSequenceName() throws Exception {
        assertEquals("seq_researchstaff_index_id", dao.sequenceName());
    }

}
