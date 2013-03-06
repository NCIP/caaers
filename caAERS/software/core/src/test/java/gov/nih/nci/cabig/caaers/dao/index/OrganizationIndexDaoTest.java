/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.index;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import junit.framework.TestCase;
import org.easymock.classextension.EasyMock;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: Biju Joseph
 */
public class OrganizationIndexDaoTest extends AbstractTestCase {

    OrganizationIndexDao dao;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = new OrganizationIndexDao();

    }

    public void testEntityIdColumnName() throws Exception {
        assertEquals("organization_id", dao.entityIdColumnName());
    }

    public void testIndexTableName() throws Exception {
        assertEquals("organization_index", dao.indexTableName());
    }

    public void testSequenceName() throws Exception {
        assertEquals("seq_organization_index_id", dao.sequenceName());
    }

}
