package gov.nih.nci.cabig.caaers.email;

import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbType;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.ServiceFactory;

public class EmailWebServiceClient {

	private static final String DEFAULT_SERVER = "localhost";
	private static final String DEFAULT_PORT = "8080";
	private static final String DEFAULT_CONTEXT_NAME = "rules";
	
	private static final String EMAIL_SERVICE_URL = "http://"
			+ DEFAULT_SERVER + ":" + DEFAULT_PORT + "/" + DEFAULT_CONTEXT_NAME
			+ "/services/EmailServiceImpl";
	
	//private static final String DEFAULT_SMTP_HOST = "localhost"; //"smtp.1and1.com"; //"smtp.east.cox.net";
	private static final String DEFAULT_SMTP_HOST = "smtp.comcast.net"; //"smtp.east.cox.net";
	
	private EmailService emailService;
	
	private SmtpConfig smtpConfig;

	public static void main(String[] args) throws Exception {
		new EmailWebServiceClient().testSendSMTPMAil();
	}
	
	public void testSendSMTPMAil() {
		this.emailService = new EmailServiceImpl();
		smtpConfig = new SmtpConfig();
		smtpConfig.setHost(DEFAULT_SMTP_HOST);
		
		smtpConfig.setUser("biju.joseph@semanticbits.com");
		smtpConfig.setAuth("true");
		smtpConfig.setPassword("biju1234");
		smtpConfig.setPort("25");
		EmailInfo emailInfo = new EmailInfo();

		emailInfo.setFrom("biju.joseph@semanticbits.com");
		emailInfo.getTo().add("biju.joseph@semanticbits.com");
		emailInfo.setSubject("Test Mail");
		emailInfo.setContent("There is not much content.1111..");

		try {
			this.emailService.send(emailInfo, smtpConfig);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	private RuleExecutionService getRemoteRuleExecutionService()
			throws MalformedURLException {

		ServiceFactory factory = new AnnotationServiceFactory();

		Service serviceModel = factory.create(RuleExecutionService.class);
		ArrayList packages = new ArrayList();
		packages.add("gov.nih.nci.cabig.caaers.rules.domain");
		serviceModel.setProperty(JaxbType.SEARCH_PACKAGES, packages);
		RuleExecutionService service = (RuleExecutionService) new XFireProxyFactory()
				.create(serviceModel, EMAIL_SERVICE_URL);
		return service;
	}
}
