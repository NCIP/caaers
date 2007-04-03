package gov.nih.nci.cabig.caaers.tools.spring;

import org.springframework.beans.factory.FactoryBean;

import java.util.Properties;

/**
 * @author Rhett Sutphin
 */
public class HibernatePropertiesFactoryBean implements FactoryBean {
    private String dialectName;
    private Properties properties;

    public HibernatePropertiesFactoryBean() {
        properties = new Properties();
    }

    public Object getObject() throws Exception {
        if (dialectName != null) {
            properties.setProperty("hibernate.dialect", dialectName);
        }
        return properties;
    }

    public Class getObjectType() {
        return Properties.class;
    }

    public boolean isSingleton() {
        return true;
    }

    ////// CONFIGURATION

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getDialectName() {
        return dialectName;
    }

    public void setDialectName(String dialectName) {
        this.dialectName = dialectName;
    }
}
