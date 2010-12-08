package gov.nih.nci.cabig.caaers.dao.index;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * AdverseEventIndexDao Tester.
 *
 * @author Biju Joseph
 * @since <pre>12/08/2010</pre>
 * 
 */
public class AdverseEventIndexDaoTest extends TestCase {
        AdverseEventIndexDao dao;

        @Override
        protected void setUp() throws Exception {
            super.setUp();
            dao = new AdverseEventIndexDao();
        }

        public void testEntityIdColumnName() throws Exception {
            assertEquals("adverseevent_id", dao.entityIdColumnName());
        }

        public void testIndexTableName() throws Exception {
            assertEquals("adverseevent_index", dao.indexTableName());
        }

        public void testSequenceName() throws Exception {
            assertEquals("seq_adverseevent_index_id", dao.sequenceName());
        }


}
