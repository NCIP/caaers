package gov.nih.nci.cabig.caaers.dao.index;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * ReportingPeriodIndexDao Tester.
 *
 * @author Biju Joseph
 * @since <pre>12/08/2010</pre>
 * 
 */
public class ReportingPeriodIndexDaoTest extends TestCase {
        ReportingPeriodIndexDao dao;

         @Override
         protected void setUp() throws Exception {
             super.setUp();
             dao = new ReportingPeriodIndexDao();
         }

         public void testEntityIdColumnName() throws Exception {
             assertEquals("reportingperiod_id", dao.entityIdColumnName());
         }

         public void testIndexTableName() throws Exception {
             assertEquals("reportingperiod_index", dao.indexTableName());
         }

         public void testSequenceName() throws Exception {
             assertEquals("seq_reportingperiod_index_id", dao.sequenceName());
         }
}