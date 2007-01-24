package gov.nih.nci.cabig.ctms.grid.ae.service;

import gov.nih.nci.cabig.ctms.grid.ae.common.AdverseEventConsumer;

import java.rmi.RemoteException;

import org.globus.wsrf.config.ContainerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class AdverseEventConsumerImpl extends AdverseEventConsumerImplBase {

    private static final String SPRING_CLASSPATH_EXPRESSION = "springClasspathExpression";

    private static final String DEFAULT_SPRING_CLASSPATH_EXPRESSION = "classpath:applicationContext-grid-ae.xml";

    private static final String CONSUMER_BEAN_NAME = "adverseEventConsumerBeanName";

    private static final String DEFAULT_CONSUMER_BEAN_NAME = "adverseEventConsumer";

    private AdverseEventConsumer consumer;

    public AdverseEventConsumerImpl() throws RemoteException {
        super();
        String exp = ContainerConfig.getConfig().getOption(SPRING_CLASSPATH_EXPRESSION,
                        DEFAULT_SPRING_CLASSPATH_EXPRESSION);
        String bean = ContainerConfig.getConfig().getOption(CONSUMER_BEAN_NAME,
                        DEFAULT_CONSUMER_BEAN_NAME);
        ApplicationContext ctx = new ClassPathXmlApplicationContext(exp);
        this.consumer = (AdverseEventConsumer) ctx.getBean(bean);
    }

    public void register(gov.nih.nci.cabig.ctms.grid.ae.beans.AENotificationType aeNotification)
                    throws RemoteException,
                    gov.nih.nci.cabig.ctms.grid.ae.stubs.types.InvalidRegistration,
                    gov.nih.nci.cabig.ctms.grid.ae.stubs.types.RegistrationFailed {
        this.consumer.register(aeNotification);
    }

}
