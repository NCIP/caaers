package gov.nih.nci.cabig.caaers2adeers.hibernate;

import org.springframework.beans.factory.FactoryBean;

import java.util.Properties;

/**
 * Creates Hibernate properties safely when the hibernate.dialect property
 * (determined at runtime) could be null.
 *
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
