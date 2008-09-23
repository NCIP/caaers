package gov.nih.nci.cabig.caaers.tools.mail;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Rhett Sutphin
 * @author Jared Flatow
 * @author Biju Joseph
 */
public class CaaersJavaMailSender extends JavaMailSenderImpl implements InitializingBean {
    private Configuration configuration;

    public String getHost() {
        return configuration.get(Configuration.SMTP_ADDRESS);
    }

    public void setHost(String host) {
        throw unsupported("host");
    }
    
   @Override
   public String getUsername() {
	   return configuration.get(Configuration.SMTP_USER);
   }
   
   @Override
   public void setUsername(String username) {
	   throw unsupported("username");
   }
   
   public int getPort(){
	   return configuration.get(Configuration.SMTP_PORT);
   }
   
    public void setPort(int port) {
        throw unsupported("port");
    }

    public String getPassword() {
        return configuration.get(Configuration.SMTP_PASSWORD);
    }

    public void setPassword(String password) {
        throw unsupported("password");
    }
    
    /**
     * We will check the configuration, and populate the propertes in the JavaMailSenderImpl bean.
     */
    public void afterPropertiesSet() throws Exception {
    	Properties properties = new Properties();
    	if(configuration.get(Configuration.SMTP_SSL_ENABLED) != null && configuration.get(Configuration.SMTP_SSL_ENABLED)){
    		
//    		properties.setProperty("mail.smtp.auth", "true");
//    		properties.setProperty("mail.smtp.starttls.enable", "true");
    		
    		properties.put("mail.transport.protocol", "smtps");
    		properties.put("mail.smtps.host", configuration.get(Configuration.SMTP_ADDRESS));
    		properties.put("mail.smtps.auth", "true");
    		properties.setProperty("mail.smtp.timeout", "8500");
        	setJavaMailProperties(properties);	
    	}
    }
    
    
    @Override
    public void send(SimpleMailMessage message) {
        String fromAddress = configuration.get(Configuration.SYSTEM_FROM_EMAIL);
        if (!fromAddress.equals("")) message.setFrom(fromAddress);
        super.send(message);
    }
    
    @Override
    public void send(MimeMessage message) {
        try {
			String fromAddress = configuration.get(Configuration.SYSTEM_FROM_EMAIL);
			if (!fromAddress.equals("")) message.setFrom(new InternetAddress(fromAddress));
			super.send(message);
		
		} catch (MessagingException e) {
			 throw new CaaersSystemException("Error while sending email", e);
		}
    }
    
    @Required
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    private UnsupportedOperationException unsupported(String prop) {
        return new UnsupportedOperationException(prop
                        + " is set through the application configuration");
    }
}
