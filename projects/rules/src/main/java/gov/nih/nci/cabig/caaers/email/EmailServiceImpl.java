package gov.nih.nci.cabig.caaers.email;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class EmailServiceImpl implements EmailService {

	public void send(EmailInfo ei, SmtpConfig smtpConfig) {
		Session mailSession = getSMTPSession(smtpConfig);
		MimeMessage mesg = new MimeMessage(mailSession);
		
		String addr = ei.getFrom();
		if (addr != null && addr.trim().length() > 0)
			try {
				mesg.setFrom(new InternetAddress(addr));

				if (addr != null && addr.trim().length() > 0)
					mesg.setRecipients(javax.mail.Message.RecipientType.TO,
							getAddresses(ei.getTo()));

				if (ei.getCc() != null && ei.getCc().size() > 0)
					mesg.setRecipients(javax.mail.Message.RecipientType.CC,
							getAddresses(ei.getCc()));

				if (ei.getBcc() != null && ei.getBcc().size() > 0)
					mesg.setRecipients(javax.mail.Message.RecipientType.BCC,
							getAddresses(ei.getBcc()));

				addr = ei.getReplyTo();
				if (ei.getReplyTo() != null
						&& ei.getReplyTo().trim().length() > 0) {
					mesg.setReplyTo(getAddresses(ei.getReplyTo()));
				}

				String sub = ei.getSubject();
				if (sub != null && sub.trim().length() > 0)
					mesg.setSubject(sub, "UTF-8");

				String body = ei.getContent();
				if (body != null && body.trim().length() > 0)
					mesg.setContent(body,  "text/html");

				mesg.setSentDate(new Date());

				System.out.println(ei);
				
				javax.mail.Transport.send(mesg);
			} catch (AddressException e) {
				throw new EmailException(e.getMessage(), e);
			} catch (MessagingException e) {
				throw new EmailException(e.getMessage(), e);
			}
	}

	private InternetAddress[] getAddresses(List<String> addresses)
			throws AddressException {
		InternetAddress[] internetAddress = new InternetAddress[addresses
				.size()];
		for (int count = 0; count < addresses.size(); count++) {
			String emailAddress = addresses.get(count);
			internetAddress[count] = new InternetAddress(emailAddress);
		}
		return internetAddress;
	}

	private InternetAddress[] getAddresses(String emailAddress)
			throws AddressException {
		return new InternetAddress[] { new InternetAddress(emailAddress) };
	}

	private Session getSMTPSession(SmtpConfig smtpConfig) {
		Properties sessionProps = System.getProperties();
		sessionProps.put("mail.smtp.host", smtpConfig.getHost());
		sessionProps.put("mail.smtp.user", smtpConfig.getUser());
		sessionProps.put("mail.smtp.password", smtpConfig.getPassword());
		sessionProps.put("mail.smtp.auth", Boolean.valueOf(smtpConfig.getAuth()).booleanValue());
		sessionProps.put("mail.smtp.port", smtpConfig.getPort());
		sessionProps.put("mail.transport.protocol","smtp");
		return javax.mail.Session.getInstance(sessionProps, new Auth(smtpConfig));
	}

	 class Auth extends Authenticator {
		 
		 private SmtpConfig smtpConfig = null;
		 Auth(SmtpConfig smtpConfig) {
			 this.smtpConfig = smtpConfig;
		 }
		 
	     protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication(smtpConfig.getUser(),smtpConfig.getPassword());
	     }
	 }

}