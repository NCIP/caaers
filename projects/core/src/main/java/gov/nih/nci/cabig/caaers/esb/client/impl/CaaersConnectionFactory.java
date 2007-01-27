package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Required;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 *
 *
 * @author Rhett Sutphin
 */
public class CaaersConnectionFactory implements ConnectionFactory {
    private ConnectionFactory delegate;
    private Configuration configuration;

    public Connection createConnection() throws JMSException {
        return createConnection(null, null);
    }

    public Connection createConnection(String username, String password) throws JMSException {
        if (delegate == null) {
            delegate = createDelegate(configuration.get(Configuration.ESB_URL));
        }
        return delegate.createConnection(username, password);
    }

    protected ConnectionFactory createDelegate(String url) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(url);
        return factory;
    }

    ////// CONFIGURATION

    @Required
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
