package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * @author Rhett Sutphin
 */

public class CaaersConnectionFactory implements ConnectionFactory {
    private ConnectionFactory delegate;
    private Configuration configuration;
    private static final Log log = LogFactory.getLog(CaaersConnectionFactory.class);
    
    public CaaersConnectionFactory(Configuration configuration) throws Exception{
    	this.configuration = configuration;
    	createConnection(null, null);
    }
    
    public Connection createConnection() throws JMSException {
        return createConnection(null, null);
    }

    public Connection createConnection(String username, String password) throws JMSException {
        if (delegate == null) {
            delegate = createDelegate(configuration.get(Configuration.ESB_URL));
        }
        return delegate.createConnection(username, password);
    }

    protected ConnectionFactory createDelegate(final String url) {
    	String newUrl = (StringUtils.isEmpty(url))? "tcp://10.10.10.2:61616" : url;
    	log.info("ESB url :" + newUrl);
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(newUrl);
        return factory;
    }

    ////// CONFIGURATION

}
