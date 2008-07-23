package gov.nih.nci.ccts.grid.service;

import gov.nih.nci.ccts.grid.common.LabConsumerServiceI;

import java.rmi.RemoteException;

import org.globus.wsrf.config.ContainerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * TODO:I am the service side implementation class.  IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.2
 * 
 */
public class LabConsumerServiceImpl extends LabConsumerServiceImplBase {

	
	private static final String SPRING_CLASSPATH_EXPRESSION = "springClasspathExpression";

    private static final String DEFAULT_SPRING_CLASSPATH_EXPRESSION = "classpath:applicationContext-labviewer.xml";

	private static final String LAB_CONSUMER_BEAN_NAME = "labViewerConsumerBeanName";

	private static final String DEFAULT_LAB_CONSUMER_BEAN_NAME = "labViewerConsumer";
	
	private LabConsumerServiceI consumer;
	    
	public LabConsumerServiceImpl() throws RemoteException {
		super();
        String exp = ContainerConfig.getConfig().getOption(SPRING_CLASSPATH_EXPRESSION,DEFAULT_SPRING_CLASSPATH_EXPRESSION);
        String bean = ContainerConfig.getConfig().getOption(LAB_CONSUMER_BEAN_NAME,DEFAULT_LAB_CONSUMER_BEAN_NAME);
        ApplicationContext ctx = new ClassPathXmlApplicationContext(exp);
        consumer = (LabConsumerServiceI) ctx.getBean(bean);
	}

	public services.Acknowledgement loadLabs(services.LoadLabsRequest loadLabsRequest) throws RemoteException {
		//System.out.println("calling Implementation .....");
		return consumer.loadLabs(loadLabsRequest);

	}

}

