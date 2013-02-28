/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.index;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * ExpeditedAdverseEventReportIndexDao Tester.
 *
 * @author Biju Joseph
 * @since <pre>12/08/2010</pre>
 * 
 */
public class ExpeditedAdverseEventReportIndexDaoTest extends TestCase {
    
           ExpeditedAdverseEventReportIndexDao dao;

           @Override
           protected void setUp() throws Exception {
               super.setUp();
               dao = new ExpeditedAdverseEventReportIndexDao();
           }

           public void testEntityIdColumnName() throws Exception {
               assertEquals("expedited_ae_id", dao.entityIdColumnName());
           }

           public void testIndexTableName() throws Exception {
               assertEquals("expedited_ae_index", dao.indexTableName());
           }

           public void testSequenceName() throws Exception {
               assertEquals("seq_expedited_ae_index_id", dao.sequenceName());
           }


}
