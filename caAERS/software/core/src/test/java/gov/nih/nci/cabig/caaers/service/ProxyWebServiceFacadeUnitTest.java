package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.CommonsHttpMessageSender;

public class ProxyWebServiceFacadeUnitTest extends AbstractTestCase {

	private ProxyWebServiceFacade proxyWebServiceFacade;

	// private StudyParticipantAssignmentDao studyParticipantAssignmentDao;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		proxyWebServiceFacade = new ProxyWebServiceFacade();
		SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory();
		saajSoapMessageFactory.afterPropertiesSet();
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate(saajSoapMessageFactory);
		webServiceTemplate.setMessageSender(new CommonsHttpMessageSender());
		proxyWebServiceFacade.setWebServiceTemplate(webServiceTemplate);
		proxyWebServiceFacade
				.setDefaultUri("http://localhost:8196/GenericProcessorService");
		
	}

	public void testSimpleSendAndReceive() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<gen:GenericRequest xmlns:gen=\"http://webservice.caaers.cabig.nci.nih.gov/GenericProcessor/\">");
		sb.append("<payload correlationId=\"KRUTTIK_02\">");
		sb.append("<system>adeers</system>");
		sb.append("<request>");
		sb.append("<!--Optional:-->");
		sb.append("<entity>agent</entity>");
		sb.append("<operation name=\"getAgentsLOV\" mode=\"async\">");
		sb.append("<criteria>");
		sb.append("<!--1 or more repetitions:-->");
		sb.append("<criterion name=\"createdDate\">12-02-2002</criterion>");
		sb.append("</criteria>");
		sb.append("</operation>");
		sb.append("</request>");
		sb.append("</payload>");
		sb.append("</gen:GenericRequest>");
		System.out.println(proxyWebServiceFacade.simpleSendAndReceive(sb.toString()));
	}
	
	public void testSyncAgents() throws Exception{
		System.out.println(proxyWebServiceFacade.syncAgents());
	}
}
