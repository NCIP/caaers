package testapp.test;

import java.io.File;
import java.io.FileInputStream;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import testapp.bean.Person;
import testapp.dao.PersonDao;

public class CSMAuthTest extends DBTestCase {

	private static final String APP = "testapp";

	public CSMAuthTest() {
		super();
		init();
	}

	public CSMAuthTest(String name) {
		super(name);
		init();
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new CSMAuthTest("testDomainObjectSecurity"));
		return suite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dbunit.DatabaseTestCase#getDataSet()
	 */
	@Override
	protected IDataSet getDataSet() throws Exception {
		String fileName = "etc/CSM_policy.xml";
		File testFile = new File(fileName);
		if (!testFile.exists()) {
			throw new RuntimeException(fileName + " not found.");
		}

		return new FlatXmlDataSet(new FileInputStream(testFile));
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE_ALL;
	}

	private void init() {

		String driver = System.getProperty("test.db.driver",
				"com.mysql.jdbc.Driver");
		String url = System.getProperty("test.db.url",
				"jdbc:mysql://localhost:3306/testapp");
		String usr = System.getProperty("test.db.usr", "testapp");
		String pwd = System.getProperty("test.db.pwd", "testapp");

		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, driver);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, url);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				usr);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				pwd);

	}

	public void testDomainObjectSecurity() {
		try {
			
			System.setProperty("gov.nih.nci.security.configFile", "etc/ApplicationSecurityConfig.xml");

			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					new String[] {
							"classpath:applicationContext-*.xml"});
			
			PersonDao dao = (PersonDao) applicationContext.getBean("personDao");
			
			switchUser("user2", new String[]{"ROLE_normal_user"});
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
			
			switchUser("user1", new String[]{"ROLE_admin_user"});
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
			
			switchUser("user2", new String[]{"ROLE_normal_user"});
			p = dao.findById(user1person1Id);
			p.setName("user1_one_updated_again");
			try {
				dao.update(p);
				fail("Should have prevented udpate operation (1)");
			} catch (Exception ex) {
				assertTrue(true);
			}
			
			
			switchUser("user3", new String[]{"ROLE_restricted_user"});
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
			fail("Error testing domain object authorization: "
					+ ex.getMessage());
		}
	}

	private void switchUser(String userName, String[] roles) {
		GrantedAuthority[] authorities = new GrantedAuthority[roles.length];
		for(int i = 0; i < roles.length; i++){
			authorities[i] = new GrantedAuthorityImpl(roles[i]);
		}
		Authentication auth = new TestingAuthenticationToken(userName,
				"ignored", authorities);
		auth.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
