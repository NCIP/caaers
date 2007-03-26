package gov.nih.nci.cabig.caaers.rules.common.spring;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbType;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.ServiceFactory;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * This class is a simplified version of <code>org.codehaus.xfire.spring.remoting.XFireClientFactoryBean</code>
 * The difference being that, here we default the binding mechanism to JaxB2 binding. We statically register the
 * JaxbTypeRegistry.
 * 
 * This class also does not try to create the service from the WSDL. Instead it just uses the interface class and the
 * annottions on it to create the Proxy class for you. In short the createClient method in this class is the most
 * simplified version of creating the client.
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class CaaersXFireClientFactoryBean implements FactoryBean, InitializingBean {

	public CaaersXFireClientFactoryBean() {
		xFire = XFireFactory.newInstance().getXFire();
		serviceFactory = new AnnotationServiceFactory(
				new Jsr181WebAnnotations(), XFireFactory.newInstance().getXFire().getTransportManager(), 
				new AegisBindingProvider(new JaxbTypeRegistry()));
	}
	
	private XFire xFire;
	
	private Object _serviceProxy;
	
	private Class serviceClass;
	
	private String serviceName;
	
    private String serviceUrl;
	
	private ServiceFactory serviceFactory = new ObjectServiceFactory();

    private boolean lookupServiceOnStartup = true;
	
	public void afterPropertiesSet() throws Exception {
		if(isLookupServiceOnStartup()) {
			_serviceProxy = createClient();
		} else {
			//TODO : If required delay using Proxy
			_serviceProxy = createClient();
		}
	}

	public Object getObject() throws Exception {
		return _serviceProxy;
	}

	public Class getObjectType() {
		return (_serviceProxy != null) ? _serviceProxy.getClass()
				: getServiceClass();
	}

	public boolean isSingleton() {
		return true;
	}

	public Class getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(Class serviceClass) {
		this.serviceClass = serviceClass;
	}

	public Class getServiceInterface() {
		return serviceClass;
	}

	
	public boolean isLookupServiceOnStartup() {
		return lookupServiceOnStartup;
	}

	public void setLookupServiceOnStartup(boolean lookupServiceOnStartup) {
		this.lookupServiceOnStartup = lookupServiceOnStartup;
	}
	
    /**
     * Creates actual XFire client proxy that this interceptor will delegate to.
     * 
     * @throws Exception
     *             if the client proxy could not be created.
     */
    private Object createClient()
        throws Exception
    {
        Object serviceClient = makeClient();
        return serviceClient;
    }	
    
    /**
     * Performs actual creation of XFire client proxy.
     * 
     * @return XFire proxy to the service
     * @throws java.net.MalformedURLException
     *             if {@link XFireProxyFactory#create} threw one
     */
    private Object makeClient()
        throws Exception
    {
        Service serviceModel = getServiceFactory().create(getServiceInterface());
        serviceModel.setProperty(JaxbType.ENABLE_VALIDATION, "false");
        return new XFireProxyFactory().create(serviceModel, getServiceUrl());
    }

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public XFire getXFire() {
		return xFire;
	}

	public void setXFire(XFire fire) {
		xFire = fire;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String url) {
		this.serviceUrl = url;
	}
}
