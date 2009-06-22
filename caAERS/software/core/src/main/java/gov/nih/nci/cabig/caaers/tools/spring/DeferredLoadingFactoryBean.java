package gov.nih.nci.cabig.caaers.tools.spring;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * This class iterates over a list of bean names, attempting to load each one from the application
 * context. It returns the first one that isn't null.
 * 
 * The purpose of this is to allow beans later in the list to be configured with lazy-init="true"
 * and never be loaded unless they are needed.
 * 
 * @author Rhett Sutphin
 */
public class DeferredLoadingFactoryBean implements FactoryBean, BeanNameAware, ApplicationContextAware {
    private static final Log log = LogFactory.getLog(DeferredLoadingFactoryBean.class);
    private ApplicationContext applicationContext;
    private List<String> beanNames;
    private String beanName;

    public Object getObject() throws Exception {
        for (String candidateBeanName : beanNames) {
            log.debug("Attempting to load " + candidateBeanName);
            Object value = applicationContext.getBean(candidateBeanName);
            if (value != null) {
                log.info("Using bean " + candidateBeanName + " for " + beanName);
                return value;
            }
        }
        log.warn("No non-null candidate found for " + beanName);
        return null;
    }

    public Class getObjectType() {
        return Object.class;
    }

    public boolean isSingleton() {
        return true;
    }

    // //// CONFIGURATION

    public List<String> getBeanNames() {
        return beanNames;
    }

    public void setBeanNames(List<String> beanNames) {
        this.beanNames = beanNames;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
