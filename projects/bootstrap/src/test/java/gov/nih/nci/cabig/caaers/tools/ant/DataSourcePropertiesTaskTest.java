package gov.nih.nci.cabig.caaers.tools.ant;

import junit.framework.TestCase;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;

import java.util.Properties;

/**
 * @author Rhett Sutphin
 */
public class DataSourcePropertiesTaskTest extends TestCase {
    private DataSourcePropertiesTask task;
    private Project project;
    private static final Properties PROPERTIES = new Properties();

    static {
        PROPERTIES.setProperty("datasource.foo", "bar");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        project = new Project();
        Target target = new Target();
        project.addTarget("init", target);
        task = new DataSourcePropertiesTask() {
            @Override protected Properties getProperties() {
                return PROPERTIES;
            }
        };
        task.setOwningTarget(target);
    }
    
    public void testPropertiesAppliedToProject() throws Exception {
        assertNull(project.getProperty("datasource.foo"));
        task.execute();
        assertEquals("bar", project.getProperty("datasource.foo"));
    }
}
