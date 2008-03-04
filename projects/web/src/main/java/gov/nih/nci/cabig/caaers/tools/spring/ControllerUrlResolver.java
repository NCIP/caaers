package gov.nih.nci.cabig.caaers.tools.spring;

/**
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC. Refactor into a shared library. */
/* TODO: this class has been copied into CTMS commons. Remove it from here. */
public interface ControllerUrlResolver {
    ResolvedControllerReference resolve(String controllerBeanName);
}
