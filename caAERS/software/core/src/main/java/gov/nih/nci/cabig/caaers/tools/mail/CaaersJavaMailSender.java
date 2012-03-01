package gov.nih.nci.cabig.caaers.tools.mail;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author Rhett Sutphin
 * @author Jared Flatow
 * @author Biju Joseph
 */
public class CaaersJavaMailSender extends JavaMailSenderImpl implements InitializingBean {
	
	public static boolean SUPRESS_MAIL_SEND_EXCEPTION = false;
    private static final Log logger = LogFactory.getLog(CaaersJavaMailSender.class);
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
    
    public String getProtocol(){
        String protocolValue = configuration.get(Configuration.SMTP_PROTOCOL);
        return protocolValue == null ? "smtp" : protocolValue;
    }

    public void setProtocol(){
        throw unsupported("mail protocol");
    }
    
    /**
     * We will check the configuration, and populate the propertes in the JavaMailSenderImpl bean.
     */
    public void afterPropertiesSet() throws Exception {
    	Properties properties = new Properties();
        boolean hasUsername = StringUtils.isNotBlank(getUsername());
        boolean hasPassword = StringUtils.isNotBlank(getPassword());
        boolean isSSL = configuration.get(Configuration.SMTP_SSL_ENABLED) != null && configuration.get(Configuration.SMTP_SSL_ENABLED);
        String baseMailPropertyName = "mail.smtp.";
        if(isSSL){
            baseMailPropertyName = "mail.smtps.";
            properties.setProperty( baseMailPropertyName + "starttls.enable", "true");
            properties.setProperty( baseMailPropertyName + "timeout", "8000");
        }
    	if(hasUsername || hasPassword) {
            properties.setProperty(baseMailPropertyName + "auth", "true");
        }

        if(logger.isDebugEnabled()){
            properties.put(baseMailPropertyName + "debug", "true");
        }

    	if (!properties.isEmpty()) {
    		setJavaMailProperties(properties);
    	}
        if(logger.isDebugEnabled()){
            logger.debug("Mail properties : " + String.valueOf(properties));
            logger.debug("Mail host : " + String.valueOf(getHost()));
            logger.debug("Mail username : " + String.valueOf(getUsername()));
            logger.debug("Mail password : " + String.valueOf(getPassword()));
            logger.debug("Mail port : " + String.valueOf(getPort()));
            logger.debug("Mail protocol : " + String.valueOf(getProtocol()));
        }
    }
    
    
    @Override
    public void send(SimpleMailMessage message) {
        String fromAddress = configuration.get(Configuration.SYSTEM_FROM_EMAIL);
        if (!StringUtils.isBlank(fromAddress)) message.setFrom(fromAddress);
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
    
    /**
     * This method is used to send an email
     */
	public void sendMail(String[] to, String subject, String content, String[] attachmentFilePaths){
		try {		
		    MimeMessage message = createMimeMessage();
		    message.setSubject(subject);
		
		    // use the true flag to indicate you need a multipart message
		    MimeMessageHelper helper = new MimeMessageHelper(message, true);
		    helper.setTo(to);
		    helper.setText(content);
		    
		    for(String attachmentPath : attachmentFilePaths){
		    	if(StringUtils.isNotEmpty(attachmentPath)){
		    		File f = new File(attachmentPath);
		    		FileSystemResource file = new FileSystemResource(f);
				    helper.addAttachment(file.getFilename(), file);
		    	}
		    }
			send(message);
		    
		} catch (Exception e) {
			 if(SUPRESS_MAIL_SEND_EXCEPTION) return; //supress the excetion related to email sending
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
