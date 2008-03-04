package gov.nih.nci.cabig.caaers.email;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 */

public interface EmailService {

    public abstract void send(EmailInfo emailInfo, SmtpConfig smtpConfig);

}