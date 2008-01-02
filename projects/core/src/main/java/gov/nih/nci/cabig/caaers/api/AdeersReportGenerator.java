package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.esb.client.impl.CaaersAdeersMessageBroadcastServiceImpl;
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
	private String xslFOMedWatchXsltFile = "xslt/Caaers2Medwatch-pdf-AEReport.xslt";
	private String pdfOutFile = "/tmp/aeReport.pdf";
	protected Configuration configuration;
	protected CaaersAdeersMessageBroadcastServiceImpl messageBroadcastService;
	
	
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

	public void genatePdf(String  adverseEventReportXml, String pdfOutFileName) throws Exception{
		
		XsltTransformer xsltTrans = new XsltTransformer();
		xsltTrans.toPdf(adverseEventReportXml, pdfOutFileName, xslFOXsltFile);
	}
	
	public void genateMedwatchPdf(String  adverseEventReportXml) throws Exception{
		
		XsltTransformer xsltTrans = new XsltTransformer();
		xsltTrans.toPdf(adverseEventReportXml, pdfOutFile, xslFOMedWatchXsltFile);
	}
	public void genateMedwatchPdf(String  adverseEventReportXml, String pdfOutFileName) throws Exception{
		
		XsltTransformer xsltTrans = new XsltTransformer();
		xsltTrans.toPdf(adverseEventReportXml, pdfOutFileName, xslFOMedWatchXsltFile);
	}
	@Deprecated
	public void generateAndSendPdfReport(ExpeditedAdverseEventReport adverseEventReportDataObject , Integer reportIndex) throws Exception{
		
		List<String> emails = new ArrayList<String>();

		Report report = adverseEventReportDataObject.getReports().get(((int)reportIndex));
		
		for (ReportDelivery delivery: report.getReportDeliveries()) {
			if (delivery.getReportDeliveryDefinition().getEndPointType().equals(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL)) {
				String ep = delivery.getEndPoint();
				emails.add(ep);
			}
		}
		
		// CCs
		String[] emailAddresses = report.getLastVersion().getEmailAsArray();
		if (emailAddresses != null) {
			for (String email : emailAddresses) {
				emails.add(email.trim());
			}
		} 
		
		if (emails.size()>0 ){
			AdverseEventReportSerializer aeser = new AdverseEventReportSerializer();
			String tempDir = System.getProperty("java.io.tmpdir");
			pdfOutFile = tempDir+"/expeditedAdverseEventReport-"+adverseEventReportDataObject.getId()+".pdf";
			String xml = aeser.serialize(adverseEventReportDataObject);
			System.out.println(xml);
			genatePdf(xml);
			
			sendMail(configuration.get(Configuration.SMTP_ADDRESS), configuration.get(Configuration.SMTP_USER), 
					configuration.get(Configuration.SMTP_PASSWORD) , configuration.get(Configuration.SYSTEM_FROM_EMAIL), 
					pdfOutFile,emails.toArray(new String[0]));
		}
	}
	
	public void generateAndNotify(String aeReportId, Report report, String xml) throws Exception {
		List<String> emails = new ArrayList<String>();
		List<String> eprs = new ArrayList<String>();
		
		int reportId = report.getId();
		
		//Report report = adverseEventReportDataObject.getReports().get(((int)reportIndex));
		StringBuilder sb = new StringBuilder();
		sb.append("<EXTERNAL_SYSTEMS>");
		for (ReportDelivery delivery: report.getReportDeliveries()) {
			ReportDeliveryDefinition rdd = delivery.getReportDeliveryDefinition();
			if (rdd.getEndPointType().equals(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL)) {
				String ep = delivery.getEndPoint();
				emails.add(ep);
			}
			if (rdd.getEndPointType().equals(ReportDeliveryDefinition.ENDPOINT_TYPE_URL)) {
				String ep = delivery.getEndPoint();
				String uid = rdd.getUserName();
				String pwd = rdd.getPassword();
				sb.append(ep+"::"+uid+"::"+pwd + ",");
				eprs.add(ep);
			}
		}
		sb.append("</EXTERNAL_SYSTEMS>");
		sb.append("<REPORT_ID>" + reportId +"</REPORT_ID>");
		
		// CCs
		String[] emailAddresses = report.getLastVersion().getEmailAsArray();
		if (emailAddresses != null) {
			for (String email : emailAddresses) {
				emails.add(email.trim());
			}
		} 
		
		if (emails.size()>0 ){
			String tempDir = System.getProperty("java.io.tmpdir");
			pdfOutFile = tempDir+"/expeditedAdverseEventReport-"+aeReportId+".pdf";

			genatePdf(xml);
			
			sendMail(configuration.get(Configuration.SMTP_ADDRESS), configuration.get(Configuration.SMTP_USER), 
					configuration.get(Configuration.SMTP_PASSWORD) , configuration.get(Configuration.SYSTEM_FROM_EMAIL), 
					pdfOutFile,emails.toArray(new String[0]));
		}	
		//sb.append("<EXTERNAL_SYSTEMS>https://eapps.ctisinc.com/adeersws10gtest/services/AEReportXMLService</EXTERNAL_SYSTEMS>");
		
		if (eprs.size()>0) {
		/*
			StringBuilder sb = new StringBuilder();
			sb.append("<EXTERNAL_SYSTEMS>");
			for (String epr:eprs) {
				sb.append(epr + ",");
			}
			//hard code for testing ... 
			sb.append("https://eapps.ctisinc.com/adeersws10gtest/services/AEReportXMLService");
			sb.append("</EXTERNAL_SYSTEMS>");
			*/
			
			xml = xml.replaceAll("<AdverseEventReport>", "<AdverseEventReport>"+sb.toString());
			messageBroadcastService.initialize();
			messageBroadcastService.broadcast(xml);				
		}
		
		
		
		//
		
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
	
	public void setMessageBroadcastService(
			CaaersAdeersMessageBroadcastServiceImpl messageBroadcastService) {
		this.messageBroadcastService = messageBroadcastService;
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
