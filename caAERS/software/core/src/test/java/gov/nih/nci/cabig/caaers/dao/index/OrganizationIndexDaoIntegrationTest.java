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


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = (OrganizationIndexDao) getDeployedApplicationContext().getBean("organizationIndexDao");

    }
    public void testUpdateIndex(){
            	
        {
            IndexEntry i0 = new IndexEntry(-1);
            i0.addRole(UserGroupType.business_administrator);
            i0.addRole(UserGroupType.ae_reporter);


            IndexEntry i1 = new IndexEntry(-2);
            i1.addRole(UserGroupType.ae_study_data_reviewer);
            List<IndexEntry> iList = new ArrayList<IndexEntry>();
            iList.add(i0);
            iList.add(i1);



            dao.updateIndex("test", iList);
        }
        interruptSession();
        {
            List<IndexEntry> entries = dao.queryAllIndexEntries("test");
            assertEquals(2, entries.size());
            IndexEntry i0 = entries.get(0);
            IndexEntry i1 = entries.get(1);
            System.out.println(entries) ;
            if(i1.getEntityId() == -2){
                assertTrue((i1.getPrivilege() & UserGroupType.ae_study_data_reviewer.getPrivilege()) > 0);
            }
            if(i0.getEntityId() == -1){
                System.out.println("AeReporter : " + UserGroupType.ae_reporter.getPrivilege() + " business admin : " + UserGroupType.business_administrator.getPrivilege() + " OR : " + (UserGroupType.ae_reporter.getPrivilege() | UserGroupType.business_administrator.getPrivilege()));

                assertTrue((i0.getPrivilege() & UserGroupType.ae_reporter.getPrivilege()) > 0);
                assertTrue((i0.getPrivilege() & UserGroupType.business_administrator.getPrivilege()) > 0);
            }

        }


    }

        
}
