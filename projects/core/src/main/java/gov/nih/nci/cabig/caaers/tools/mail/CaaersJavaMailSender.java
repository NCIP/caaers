package gov.nih.nci.cabig.caaers.tools.mail;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Rhett Sutphin
 * @author Jared Flatow
 */
public class CaaersJavaMailSender extends JavaMailSenderImpl {
    private Configuration configuration;

    public String getHost() {
        return configuration.get(Configuration.SMTP_ADDRESS);
    }

    public void setHost(String host) {
        throw unsupported("host");
    }

    public int getPort() {
        return configuration.get(Configuration.SMTP_PORT);
    }

    public void setPort(int port) {
        throw unsupported("port");
    }

    @Required
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    private UnsupportedOperationException unsupported(String prop) {
        return new UnsupportedOperationException(prop + " is set through the application configuration");
    }
}
