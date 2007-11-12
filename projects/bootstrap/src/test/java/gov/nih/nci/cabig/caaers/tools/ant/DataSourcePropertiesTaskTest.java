package gov.nih.nci.cabig.caaers.tools.ant;

import junit.framework.TestCase;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;

import java.util.Properties;
import java.io.File;

import gov.nih.nci.cabig.caaers.tools.TestDataSourcePropertiesFactoryBean;

/**
 * @author Rhett Sutphin
 */
public class DataSourcePropertiesTaskTest extends TestCase {
    private Project project;
    private static final Properties PROPERTIES = new Properties();
    private Target target;
    private DataSourcePropertiesTask task;

    static {
        PROPERTIES.setProperty("datasource.foo", "bar");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        File thisDir = new File(getClass().getResource("/").toURI());
        System.setProperty("catalina.home", thisDir.getCanonicalPath());

        project = new Project();
        target = new Target();
        project.addTarget("init", target);
        task = new DataSourcePropertiesTask();
        task.setOwningTarget(target);
    }
    
    public void testPropertiesAppliedToProject() throws Exception {
        DataSourcePropertiesTask stubbedTask = new DataSourcePropertiesTask() {
            @Override protected Properties getProperties() {
                return PROPERTIES;
            }
        };
        stubbedTask.setOwningTarget(target);

        assertNull(project.getProperty("datasource.foo"));
        stubbedTask.execute();
        assertEquals("bar", project.getProperty("datasource.foo"));
    }

    public void testPropertiesLoadedViaConfiguredFactoryClass() throws Exception {
        task.setFactoryBeanClass(TestDataSourcePropertiesFactoryBean.class.getName());

        assertNull(project.getProperty("computed"));
        task.execute();
        assertEquals("42", project.getProperty("computed"));
    }

    public void testApplicationDirectoryNamePassedOnToFactory() throws Exception {
        task.setApplicationDirectoryName("etna");

        assertNull(project.getProperty("datasource.url"));
        task.execute();
        assertEquals("jdbc:db:etna", project.getProperty("datasource.url"));
    }
}
