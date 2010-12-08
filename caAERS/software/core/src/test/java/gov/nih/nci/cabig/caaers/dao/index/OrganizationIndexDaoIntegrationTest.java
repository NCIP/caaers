package gov.nih.nci.cabig.caaers.dao.index;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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

    public void testQueryAllIndexEntries(){
        List<IndexEntry> entries = dao.queryAllIndexEntries("test");
        assertEquals(3, entries.size());
        IndexEntry e = findEntry(UserGroupType.supplemental_study_information_manager, entries);
        assertEquals(9, e.getEntityIds().size());
        IndexEntry e2 = findEntry(UserGroupType.study_site_participation_administrator, entries);
        assertEquals(4, e2.getEntityIds().size());
        IndexEntry e3 = findEntry(UserGroupType.subject_manager, entries);
        assertEquals(3, e3.getEntityIds().size());
        assertTrue(e3.getEntityIds().contains(-1));
        assertTrue(e3.getEntityIds().contains(-2));
        assertTrue(e3.getEntityIds().contains(-3));
    }

    public void testUpdateIndex(){
        
        IndexEntry e1 = new IndexEntry(UserGroupType.ae_reporter);
        e1.addEntityId(-5);
        e1.addEntityId(-6);
        
        dao.updateIndex("test", UserGroupType.ae_reporter.getCode(),e1 , null);
        List<IndexEntry> entries = dao.queryAllIndexEntries("test");
        assertEquals(4, entries.size());
        IndexEntry e2 = findEntry(UserGroupType.ae_reporter, entries);
        assertEquals(2, e2.getEntityIds().size());
        assertTrue(e2.getEntityIds().contains(-5));
        assertTrue(e2.getEntityIds().contains(-6));
        assertNotNull(template);
        template.query("select id from organization_index where login_id = 'test' and role_code=-110 order by id", new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                int i = rs.getInt(1);
                assertTrue(Arrays.asList(new Integer[]{-10,-11,-12,-13}).contains(i));
                return null; 
            }
        });

    }


    public void testUpdateIndex_DeletingOne(){
        IndexEntry e1 = new IndexEntry(UserGroupType.study_site_participation_administrator);
        e1.addEntityId(-1);
        e1.addEntityId(-2);
        e1.addEntityId(-4);
        IndexEntry e2 = new IndexEntry(UserGroupType.study_site_participation_administrator);
        e2.addEntityId(-1);
        e2.addEntityId(-2);
        e2.addEntityId(-3);
        e2.addEntityId(-4);
        dao.updateIndex("test", UserGroupType.study_site_participation_administrator.getCode(),e1 , e2);

        template.query("select id from organization_index where login_id = 'test' and role_code=-110 order by id", new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                int i = rs.getInt(1);
                assertTrue(Arrays.asList(new Integer[]{-10,-11,-13}).contains(i));
                return null;
            }
        });

    }


    public void testUpdateIndex_DeletingOneAddingOne(){
        IndexEntry e1 = new IndexEntry(UserGroupType.study_site_participation_administrator);
        e1.addEntityId(-1);
        e1.addEntityId(-2);
        e1.addEntityId(-4);
        e1.addEntityId(-5);
        IndexEntry e2 = new IndexEntry(UserGroupType.study_site_participation_administrator);
        e2.addEntityId(-1);
        e2.addEntityId(-2);
        e2.addEntityId(-3);
        e2.addEntityId(-4);
        dao.updateIndex("test", UserGroupType.study_site_participation_administrator.getCode(),e1 , e2);

        template.query("select id from organization_index where login_id = 'test' and role_code=-110 order by id", new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                int i = rs.getInt(1);
                assertTrue(i > 1 || Arrays.asList(new Integer[]{-10,-11,-13}).contains(i));
                return null;
            }
        });

    }

    public void testUpdateIndex_DeletingAll(){
        IndexEntry e2 = new IndexEntry(UserGroupType.study_site_participation_administrator);
        e2.addEntityId(-1);
        e2.addEntityId(-2);
        e2.addEntityId(-3);
        e2.addEntityId(-4);
        dao.updateIndex("test", UserGroupType.study_site_participation_administrator.getCode(),null , e2);

        template.query("select id from organization_index where login_id = 'test' and role_code=-110 order by id", new RowMapper(){
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                fail("should have deleted everything for this role");
                return null;
            }
        });

        List<IndexEntry> entries = dao.queryAllIndexEntries("test");
        assertEquals(2, entries.size());

    }


    public IndexEntry findEntry(UserGroupType ug, List<IndexEntry> entries){
        for(IndexEntry e : entries) if(e.getRole() == ug) return e;
        return null;
    }
    
}
