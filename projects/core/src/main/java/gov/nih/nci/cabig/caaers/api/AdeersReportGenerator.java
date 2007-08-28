package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.XsltTransformer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;



public class AdeersReportGenerator  {

	//TO-DO set in spring config 
	private String xmlXsltFile = "xslt/Caaers2Adeers-xml-AEReport.xslt";
	private String xslFOXsltFile = "xslt/Caaers2Adeers-pdf-AEReport.xslt";
	private String pdfOutFile = "/tmp/aeReport.pdf";
	protected Configuration configuration;
	
	
	public AdeersReportGenerator () { };
	

	   
	public String getAdeersXml(String  adverseEventReportXml) throws Exception{
		XsltTransformer xsltTrans = new XsltTransformer();
		String transformedToAdeers = xsltTrans.toXml(adverseEventReportXml , xmlXsltFile);
	
		return transformedToAdeers;
	}

	public void genatePdf(String  adverseEventReportXml) throws Exception{
		
		XsltTransformer xsltTrans = new XsltTransformer();
		xsltTrans.toPdf(adverseEventReportXml, pdfOutFile, xslFOXsltFile);
	}
	
	public void generateAndSendPdfReport(ExpeditedAdverseEventReport adverseEventReportDataObject , Integer reportIndex) throws Exception{
		AdverseEventReportSerializer aeser = new AdverseEventReportSerializer();
		String tempDir = System.getProperty("java.io.tmpdir");
		pdfOutFile = tempDir+"/expeditedAdverseEventReport-"+adverseEventReportDataObject.getId()+".pdf";
		String xml = aeser.serialize(adverseEventReportDataObject);
	//	System.out.println(xml);
		genatePdf(xml);
		
		List<String> emails = new ArrayList<String>();

		Report report = adverseEventReportDataObject.getReports().get(((int)reportIndex));
		
		for (ReportDelivery delivery: report.getReportDeliveries()) {
			if (delivery.getReportDeliveryDefinition().getEndPointType().equals(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL)) {
				String ep = delivery.getEndPoint();
				emails.add(ep);
			}
		}
		
		// CCs
		String[] emailAddresses = report.getEmailAsArray();
		if (emailAddresses != null) {
			for (String email : emailAddresses) {
				emails.add(email.trim());
			}
		}
		
		if (emails.size()>0 ){
			sendMail(configuration.get(Configuration.SMTP_ADDRESS), configuration.get(Configuration.SMTP_USER), 
					configuration.get(Configuration.SMTP_PASSWORD) , configuration.get(Configuration.SYSTEM_FROM_EMAIL), 
					pdfOutFile,emails.toArray(new String[0]));
		}
	}
	
	private void sendMail(String mailHost, String user, String pwd, String from,String attachment, String[] to) throws Exception {
			try {	
				JavaMailSenderImpl sender = new JavaMailSenderImpl();
				//sender.setHost("smtp.comcast.net");
				sender.setUsername(user);
				sender.setPassword(pwd);
				sender.setHost(mailHost);
				MimeMessage message = sender.createMimeMessage();
				//message.setFrom(new InternetAddress(from));
				message.setSubject("Expedited Adverse Event Report");
				message.setFrom(new InternetAddress(from));
				
//				 use the true flag to indicate you need a multipart message
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(to);

				helper.setText("report ... ");
				File f = new File(attachment);
				FileSystemResource file = new FileSystemResource(f);
				helper.addAttachment(file.getFilename(), file);
				
				/*
				for (int i=0 ; i<attachments.length; i++) {
					File f = new File(attachments[i]);
					FileSystemResource file = new FileSystemResource(f);
					helper.addAttachment(file.getFilename(), file);
				}
				*/

				sender.send(message);
				
				System.out.println("sent . . ");
			} catch (Exception e ) {
				throw new Exception (" Error in sending email , please check the confiuration " + e);
			}

	}


	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdeersReportGenerator aeg = new AdeersReportGenerator();
		List<String> emails = new ArrayList<String>();
		emails.add("srakkala@yahoo.com");
		emails.add("srini.akkala@semanticbits.com");
		//sendMail("support@caaers.gov", pdfOutFile,emails.toArray(new String[0]));
		aeg.sendMail("support@semanticbits.com", "/tmp/aeReport.pdf", emails.toArray(new String[0]));

	}
	*/
	

}
