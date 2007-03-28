package gov.nih.nci.cabig.caaers.tools.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import java.util.Map;
import java.util.Properties;

import gov.nih.nci.cabig.caaers.tools.DataSourceSelfDiscoveringPropertiesFactoryBean;

/**
 * @author Rhett Sutphin
 */
public class DataSourcePropertiesTask extends Task {
    private String databaseConfigurationName;

    @Override
    public void execute() throws BuildException {
        Properties props = getProperties();
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            getOwningTarget().getProject()
                .setProperty((String) entry.getKey(), (String) entry.getValue());
        }
    }

    protected Properties getProperties() {
        DataSourceSelfDiscoveringPropertiesFactoryBean factory
            = new DataSourceSelfDiscoveringPropertiesFactoryBean();
        factory.setDatabaseConfigurationName(getDatabaseConfigurationName());
        return factory.getProperties();
    }

    ////// CONFIGURATION

    public String getDatabaseConfigurationName() {
        return databaseConfigurationName;
    }

    public void setDatabaseConfigurationName(String databaseConfigurationName) {
        this.databaseConfigurationName = databaseConfigurationName;
    }
}
