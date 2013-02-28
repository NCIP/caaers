/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.index;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Biju Joseph
 */
public class OrganizationIndexDaoIntegrationTest extends CaaersDbTestCase {

    OrganizationIndexDao dao;
    JdbcTemplate template;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = (OrganizationIndexDao) getDeployedApplicationContext().getBean("organizationIndexDao");
        template = (JdbcTemplate) getDeployedApplicationContext().getBean("jdbcTemplate");
    }

  

    public void testUpdateIndex(){
            	
        IndexEntry i0 = new IndexEntry(-1);
        i0.addRole(UserGroupType.business_administrator);
        i0.addRole(UserGroupType.ae_reporter);
        

        IndexEntry i1 = new IndexEntry(-2);
        i1.addRole(UserGroupType.ae_study_data_reviewer);
        List<IndexEntry> iList = new ArrayList<IndexEntry>();
        iList.add(i0);
        iList.add(i1);
        

        
        dao.updateIndex("test", iList);

        

        assertTrue( iList.size() == 2 );
        assertTrue(i0.getRoles().size() == 2);
        assertTrue ( i0.getRoles().contains(UserGroupType.ae_reporter));
        assertNotNull ( findRole(UserGroupType.ae_reporter, i0));

        
        final Set<Integer> orgList = new HashSet<Integer>(); // Just to make sure not adding duplicate values. 
        template.query("select organization_id from organization_index where login_id = 'test' and r_102 = '1' or r_119 = '1'", new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                int i = rs.getInt(1);
                orgList.add(i);
                return null; 
            }
        });
        
        assertTrue(orgList.size() == 2);
    }


    public UserGroupType findRole(UserGroupType ug, IndexEntry entries){
        for(UserGroupType e : entries.getRoles()) if(e == ug) return e;
        return null;
    }
        
}
