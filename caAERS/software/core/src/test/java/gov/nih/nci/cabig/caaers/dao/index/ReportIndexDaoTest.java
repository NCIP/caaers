package gov.nih.nci.cabig.caaers.dao.index;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * ReportIndexDao Tester.
 *
 * @author Biju Joseph
 * @since <pre>12/08/2010</pre>
 * 
 */
public class ReportIndexDaoTest extends TestCase {

      ReportIndexDao dao;

      @Override
      protected void setUp() throws Exception {
          super.setUp();
          dao = new ReportIndexDao();
      }

      public void testEntityIdColumnName() throws Exception {
          assertEquals("report_id", dao.entityIdColumnName());
      }

      public void testIndexTableName() throws Exception {
          assertEquals("report_index", dao.indexTableName());
      }

      public void testSequenceName() throws Exception {
          assertEquals("seq_report_index_id", dao.sequenceName());
      }


}
