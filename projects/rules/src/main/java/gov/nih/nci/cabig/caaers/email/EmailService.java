package gov.nih.nci.cabig.caaers.email;

import javax.jws.WebService;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
@WebService(
        serviceName = "EmailService", endpointInterface = "gov.nih.nci.cabig.caaers.rules.runtime.EmailService"
)
public interface EmailService {

	public abstract void send(EmailInfo emailInfo, SmtpConfig smtpConfig);

}