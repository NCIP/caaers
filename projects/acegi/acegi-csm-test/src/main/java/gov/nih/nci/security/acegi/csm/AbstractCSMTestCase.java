package gov.nih.nci.security.acegi.csm;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractCSMTestCase extends DBTestCase {
    
    public AbstractCSMTestCase(){
        super();
        init();
    }
    
    public AbstractCSMTestCase(String name){
        super(name);
        init();
    }
    
    public ApplicationContext getApplicationContext() {
        return new ClassPathXmlApplicationContext(getConfigLocations());
    }

    protected String[] getConfigLocations() {
        return new String[] { "classpath:applicationContext-*.xml" };
    }
    
    public void setUp() throws Exception {

        Configuration cfg = new Configuration().configure(new File("src/test/resources/hibernate.cfg.xml"));
        SchemaExport se = new SchemaExport(cfg);
        try {
             se.drop(false, true);
        } catch (Exception ex) {

        }
        se.create(false, true);
        super.setUp();


    }    

    

    @Override
    protected IDataSet getDataSet() throws Exception {
        String fileName = "src/test/resources/CSM_policy.xml";
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

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/hibernate.properties"));
        } catch (Exception ex) {
            throw new RuntimeException("Error loading properties: " + ex.getMessage(), ex);
        }

        String driver = props.getProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        String url = props.getProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testapp");
        String usr = props.getProperty("hibernate.connection.username", "testapp");
        String pwd = props.getProperty("hibernate.connection.password", "testapp");

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, driver);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, url);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, usr);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, pwd);

    }    

}
