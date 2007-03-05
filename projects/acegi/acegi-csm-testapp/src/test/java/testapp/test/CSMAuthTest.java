package testapp.test;

import gov.nih.nci.security.acegi.csm.AbstractCSMTestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import testapp.bean.Person;
import testapp.dao.PersonDao;

public class CSMAuthTest extends AbstractCSMTestCase {

    public CSMAuthTest() {
        super();
    }

    public CSMAuthTest(String name) {
        super(name);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new CSMAuthTest("testDomainObjectSecurity"));
        return suite;
    }

    public String[] getConfigFileLocations(){
        return new String[]{"applicationContext*-test.xml"};
    }

    public void setUp() throws Exception {
        super.setUp();

        ApplicationContext appCtx = getApplicationContext();
        HibernateTransactionManager txMgr = (HibernateTransactionManager) appCtx
                        .getBean("transactionManager");
        Session session = SessionFactoryUtils.getSession(txMgr.getSessionFactory(), true);
        if (session != null) {
            session.clear();
        }
        LocalSessionFactoryBean sessionFactoryBean = (LocalSessionFactoryBean) appCtx
                        .getBean("&sessionFactory");
        sessionFactoryBean.createDatabaseSchema();

    }

    public void testDomainObjectSecurity() {
        try {

            ApplicationContext applicationContext = getApplicationContext();

            PersonDao dao = (PersonDao) applicationContext.getBean("personDao");

            switchUser("user2", new String[] { "ROLE_normal_user" });
            Person p = new Person();
            p.setName("user2_one");
            Long user2person1Id = null;
            try {
                user2person1Id = dao.save(p);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Should not have prevented save operation (1)");
            }

            p = dao.findById(user2person1Id);
            p.setName("user2_one_updated");
            try {
                dao.update(p);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Should not have prevented udpate operation (1)");
            }

            switchUser("user1", new String[] { "ROLE_admin_user" });
            p = new Person();
            p.setName("user1_one");
            Long user1person1Id = null;
            try {
                user1person1Id = dao.save(p);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Should not have prevented save operation (2)");
            }

            p = dao.findById(user1person1Id);
            p.setName("user1_one_updated");
            try {
                dao.update(p);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Should not have prevented udpate operation (2)");
            }

            p = dao.findById(user2person1Id);
            p.setName("user2_one_updated_again");
            try {
                dao.update(p);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Should not have prevented udpate operation (3)");
            }

            switchUser("user2", new String[] { "ROLE_normal_user" });
            p = dao.findById(user1person1Id);
            p.setName("user1_one_updated_again");
            try {
                dao.update(p);
                fail("Should have prevented udpate operation (1)");
            } catch (Exception ex) {
                assertTrue(true);
            }

            switchUser("user3", new String[] { "ROLE_restricted_user" });
            p = new Person();
            p.setName("user3_one");
            Long user3person1Id = null;
            try {
                user3person1Id = dao.save(p);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Should not have prevented save operation (4)");
            }

            p = dao.findById(user3person1Id);
            p.setName("user3_one_updated");
            try {
                dao.update(p);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Should not have prevented udpate operation (5)");
            }
            p = dao.findById(user2person1Id);
            assertNull("Should not have retrieved person (1)", p);

        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error testing domain object authorization: " + ex.getMessage());
        }
    }

    private void switchUser(String userName, String[] roles) {
        GrantedAuthority[] authorities = new GrantedAuthority[roles.length];
        for (int i = 0; i < roles.length; i++) {
            authorities[i] = new GrantedAuthorityImpl(roles[i]);
        }
        Authentication auth = new TestingAuthenticationToken(userName, "ignored", authorities);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
