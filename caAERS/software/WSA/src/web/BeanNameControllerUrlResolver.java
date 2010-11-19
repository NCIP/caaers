package web;

import gov.nih.nci.cabig.ctms.CommonsSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.mvc.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class BeanNameControllerUrlResolver implements BeanFactoryPostProcessor, Ordered {
    private Logger log = LoggerFactory.getLogger(getClass());

    private String servletName;
    // maps bean IDs to resolved refs
    private Map<String, ResolvedControllerReference> controllers = new HashMap<String, ResolvedControllerReference>();

    public int getOrder() { return 0; }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] controllerNames = beanFactory.getBeanNamesForType(Controller.class, false, false);
        for (String controllerName : controllerNames) {
            controllers.put(controllerName, createResolvedReference(controllerName, beanFactory));
        }
    }

    public ResolvedControllerReference resolve(String controllerBeanName) {
        ResolvedControllerReference controllerReference = controllers.get(controllerBeanName);
        if (controllerReference == null) {
            throw new CommonsSystemException("Could not find a ref for " + controllerBeanName);
        }
        return controllerReference;
    }

    protected ResolvedControllerReference createResolvedReference(String controllerName, ConfigurableListableBeanFactory beanFactory) {
        if (log.isDebugEnabled()) log.debug("Resolving URL for controller " + controllerName);
        BeanDefinition def = beanFactory.getBeanDefinition(controllerName);
        String url = resolveUrl(controllerName, beanFactory);
        if (url != null) {
            if (log.isDebugEnabled()) log.debug("URL for " + controllerName + " is " + url);
            return new ResolvedControllerReference(
                controllerName, def.getBeanClassName(), servletName, url);
        } else {
            log.warn("Could not find a URL mapping for controller bean " + controllerName);
            return null;
        }
    }

    /**
     * Uses this first alias for this bean that starts with '/'.   This is based on the behavior
     * of {@link org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping}.
     */
    private String resolveUrl(String controllerName, ConfigurableListableBeanFactory beanFactory) {
        String[] aliases = beanFactory.getAliases(controllerName);
        for (String alias : aliases) {
            if (alias.startsWith("/")) return alias;
        }
        return null;
    }

    ////// CONFIGURATION

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }
}
