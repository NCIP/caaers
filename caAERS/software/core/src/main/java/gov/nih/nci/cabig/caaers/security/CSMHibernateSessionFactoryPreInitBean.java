package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.system.ApplicationSessionFactory;
import org.hibernate.SessionFactory;

/**
 * Will initialize the session factory under the application context.
 * This is to get rid of invalid session from configuration and datasource configuration
 */
public class CSMHibernateSessionFactoryPreInitBean {

    public CSMHibernateSessionFactoryPreInitBean(String appName, SessionFactory csmSessionFactory) {
        if(ApplicationSessionFactory.appSessionFactories.get(appName) != null) throw new RuntimeException("CSMHibernateSessionFactoryPreInitBean must be initialized before CSM application context");
        ApplicationSessionFactory.appSessionFactories.put(appName, csmSessionFactory);
    }
}
