package gov.nih.nci.cabig.caaers.tools.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import java.util.Map;
import java.util.Properties;

import gov.nih.nci.cabig.caaers.tools.CaaersDataSourcePropertiesFactoryBean;
import gov.nih.nci.cabig.caaers.tools.DataSourceSelfDiscoveringPropertiesFactoryBean;

/**
 * @author Rhett Sutphin
 */
public class DataSourcePropertiesTask extends Task {
    private String databaseConfigurationName;
    private String factoryBeanClass;
    private String applicationDirectoryName;

    public DataSourcePropertiesTask() {
        factoryBeanClass = DataSourceSelfDiscoveringPropertiesFactoryBean.class.getName();
    }

    @Override
    public void execute() throws BuildException {
        Properties props = getProperties();
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            getOwningTarget().getProject()
                .setProperty((String) entry.getKey(), (String) entry.getValue());
        }
    }

    protected Properties getProperties() {
        DataSourceSelfDiscoveringPropertiesFactoryBean factory = createFactory();
        if (getDatabaseConfigurationName() != null) factory.setDatabaseConfigurationName(getDatabaseConfigurationName());
        if (getApplicationDirectoryName() != null) factory.setApplicationDirectoryName(getApplicationDirectoryName());
        return factory.getProperties();
    }

    private DataSourceSelfDiscoveringPropertiesFactoryBean createFactory() {
        try {
            Class<?> factoryClass = Class.forName(getFactoryBeanClass());
            Object instance = factoryClass.newInstance();
            if (instance instanceof DataSourceSelfDiscoveringPropertiesFactoryBean) {
                return (DataSourceSelfDiscoveringPropertiesFactoryBean) instance;
            } else {
                throw new BuildException(getFactoryBeanClass() + " is not a subclass of "
                    + DataSourceSelfDiscoveringPropertiesFactoryBean.class.getName());
            }
        } catch (ClassNotFoundException e) {
            throw new BuildException("Could not load factory class " + getFactoryBeanClass(), e);
        } catch (IllegalAccessException e) {
            throw new BuildException("Unable to access factory bean class " + getFactoryBeanClass(), e);
        } catch (InstantiationException e) {
            throw new BuildException("Unable to instantiate factory bean class " + getFactoryBeanClass(), e);
        }
    }

    ////// CONFIGURATION

    public String getDatabaseConfigurationName() {
        return databaseConfigurationName;
    }

    public void setDatabaseConfigurationName(String databaseConfigurationName) {
        this.databaseConfigurationName = databaseConfigurationName;
    }

    public String getFactoryBeanClass() {
        return factoryBeanClass;
    }

    public void setFactoryBeanClass(String factoryBeanClass) {
        this.factoryBeanClass = factoryBeanClass;
    }

    public String getApplicationDirectoryName() {
        return applicationDirectoryName;
    }

    public void setApplicationDirectoryName(String applicationDirectoryName) {
        this.applicationDirectoryName = applicationDirectoryName;
    }
}
