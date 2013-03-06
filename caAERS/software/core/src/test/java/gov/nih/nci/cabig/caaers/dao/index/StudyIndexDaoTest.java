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
 * StudyIndexDao Tester.
 *
 * @author Biju Joseph
 * @since <pre>12/08/2010</pre>
 * 
 */
public class StudyIndexDaoTest extends TestCase {

    StudyIndexDao dao;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = new StudyIndexDao();

    }

    public void testEntityIdColumnName() throws Exception {
        assertEquals("study_id", dao.entityIdColumnName());
    }

    public void testIndexTableName() throws Exception {
        assertEquals("study_index", dao.indexTableName());
    }

    public void testSequenceName() throws Exception {
        assertEquals("seq_study_index_id", dao.sequenceName());
    }
}
