package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathFactory;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.migrator.StudyConverter;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperty;
import org.apache.commons.lang.RandomStringUtils;
import org.easymock.EasyMock;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.CommonsHttpMessageSender;
import org.xml.sax.InputSource;

public class ProxyWebServiceFacadeUnitTest extends AbstractTestCase {

	private ProxyWebServiceFacade proxyWebServiceFacade;
    WebServiceTemplate webServiceTemplate;

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

    public void testSearchStudy(){
        proxyWebServiceFacade = new ProxyWebServiceFacade(){
            @Override
            public String simpleSendAndReceive(String message) {
                return mockSearchStudyResponse();
            }
        };
        proxyWebServiceFacade.setStudyConverter(new StudyConverter());

        List<Study> studyList = proxyWebServiceFacade.searchStudies("5876")  ;
        assertEquals(1, studyList.size());
        Study s = studyList.get(0);
        assertEquals("Phase II Trial of Flavopiridol and Cisplatin in Advanced Epithelial Ovarian and Primary Peritoneal Carcinomas", s.getShortTitle());
        assertEquals(1, s.getStudyOrganizations().size());
        assertEquals("CTEP", s.getStudyFundingSponsors().get(0).getOrganization().getNciInstituteCode());
    }
    
    private String mockSearchStudyResponse(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<payload xmlns:stud=\"http://schema.integration.caaers.cabig.nci.nih.gov/study\">\n" +
                "    <system>caaers</system>\n" +
                "    <response>\n" +
                "        <operation name=\"searchStudy\">\n" +
                "            <data>\n" +
                "                <stud:studies>\n" +
                "                    <stud:study>\n" +
                "                        <shortTitle>Phase II Trial of Flavopiridol and Cisplatin in Advanced Epithelial Ovarian and Primary Peritoneal Carcinomas</shortTitle>\n" +
                "                        <fundingSponsor>\n" +
                "                            <organizationAssignedIdentifier>\n" +
                "                                <value>5876</value>\n" +
                "                            </organizationAssignedIdentifier>\n" +
                "                            <stud:studyFundingSponsor>\n" +
                "                                <stud:organization>\n" +
                "                                    <name>Cancer Therapy Evaluation Program</name>\n" +
                "                                    <nciInstituteCode>CTEP</nciInstituteCode>\n" +
                "                                </stud:organization>\n" +
                "                            </stud:studyFundingSponsor>\n" +
                "                        </fundingSponsor>\n" +
                "                    </stud:study>\n" +
                "                </stud:studies>\n" +
                "            </data>\n" +
                "        </operation>\n" +
                "    </response>\n" +
                "</payload>";
    }

//	public void testSimpleSendAndReceive() throws Exception {
//		StringBuffer sb = new StringBuffer();
//		sb.append("<gen:GenericRequest xmlns:gen=\"http://webservice.caaers.cabig.nci.nih.gov/GenericProcessor/\">");
//		sb.append("<payload correlationId=\"KRUTTIK_02\">");
//		sb.append("<system>adeers</system>");
//		sb.append("<request>");
//		sb.append("<!--Optional:-->");
//		sb.append("<entity>agent</entity>");
//		sb.append("<operation name=\"getAgentsLOV\" mode=\"async\">");
//		sb.append("<criteria>");
//		sb.append("<!--1 or more repetitions:-->");
//		sb.append("<criterion name=\"createdDate\">12-02-2002</criterion>");
//		sb.append("</criteria>");
//		sb.append("</operation>");
//		sb.append("</request>");
//		sb.append("</payload>");
//		sb.append("</gen:GenericRequest>");
//		System.out.println(proxyWebServiceFacade.simpleSendAndReceive(sb.toString()));
//	}
//	
//	public void testSyncAgents() throws Exception{
//		System.out.println(proxyWebServiceFacade.syncAgents());
//	}
	
//	public void testConcurrency() throws Exception{
//		ExecutorService executor = Executors.newFixedThreadPool(10);
//		List<Future<String>> list = new ArrayList<Future<String>>();
//		List<String> correlationIDs = new ArrayList<String>();
//		for (EntityOperation entityOperation : EntityOperation.values()) {
//			String correlationId = RandomStringUtils.randomAlphanumeric(10);
//			correlationIDs.add(correlationId);
//			System.out.println(entityOperation+" - "+correlationId);
//			Callable<String> worker = new ProxyWebServiceCallable(correlationId, entityOperation.getQualifiedName(), entityOperation.getOperationName(), proxyWebServiceFacade);
//			Future<String> submit = executor.submit(worker);
//			list.add(submit);
//		}
//		//System.out.println(list.size());
//		// Now retrieve the result
//		for (int i=0 ; i<list.size() ; i++) {
//			try {
//				Matcher m = Pattern.compile("coorelationId=\"(.*)\"><system").matcher(list.get(i).get());
//				String correlationId="";
//				try {
//					correlationId = m.group(1);
//				} catch (Exception e) {
//					System.out.println("no correlation id found");
//				}
//				System.out.println("\n[expected, actual] - ["+correlationIDs.get(i)+", "+correlationId+"]");
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
//		executor.shutdown();
//	}
	
	public class ProxyWebServiceCallable implements Callable<String>{
		
		private String coorelationId;
		private String entity;
		private String operation;
		
		private ProxyWebServiceFacade proxyWebServiceFacade; 
		
		public ProxyWebServiceCallable(String coorelationId, String entity, String operation, ProxyWebServiceFacade proxyWebServiceFacade) {
			super();
			this.coorelationId = coorelationId;
			this.entity = entity;
			this.operation = operation;
			this.proxyWebServiceFacade = proxyWebServiceFacade;
		}

		public String call() throws Exception {
			StringBuffer sb = new StringBuffer();
			sb.append("<gen:GenericRequest xmlns:gen=\"http://webservice.caaers.cabig.nci.nih.gov/GenericProcessor/\">");
			sb.append("<payload correlationId=\""+coorelationId+"\">");
			sb.append("<system>adeers</system>");
			sb.append("<request>");
			sb.append("<!--Optional:-->");
			sb.append("<entity>"+entity+"</entity>");
			sb.append("<operation name=\""+operation+"\" mode=\"async\">");
			sb.append("<criteria>");
			sb.append("<!--1 or more repetitions:-->");
			sb.append("<criterion name=\"createdDate\">03-30-2012</criterion>");
			sb.append("</criteria>");
			sb.append("</operation>");
			sb.append("</request>");
			sb.append("</payload>");
			sb.append("</gen:GenericRequest>");
			System.out.println("sending request----"+sb.toString());
			return proxyWebServiceFacade.simpleSendAndReceive(sb.toString());
		}
	}
	
	public enum EntityOperation{
	    AGENT("agent", "getAgentsLOV", "0+0+1+*+*+?"),
	    ASAEL("asael", "getASAEL", "0+0+1+*+*+?"),
	    DEVICE("device", "getDevicesLOV", "0+0+1+*+*+?"),
	    PRIOR_THERAPY("priortherapy", "getTherapiesLOV", "0+0+1+*+*+?"),
	    PRE_EXISTING_CONDITION("preexistingcondition", "getPreExistingConditionsLOV", "0+0+1+*+*+?"),
	    ORGANIZATION("organization", "getOrganizationsLOV", "0+0+1+*+*+?"),

	    ;
	    private String qualifiedName;
	    private String operationName;
	    private Boolean async = true;
	    private String cronJobExpression;

	     private EntityOperation(String qualifiedName, String operationName, String cronJobExpression, boolean async) {
	        this.qualifiedName = qualifiedName;
	        this.operationName = operationName;
	        this.cronJobExpression = cronJobExpression;
	        this.async = async;
	    }
	     
	     private EntityOperation(String qualifiedName, String operationName, String cronJobExpression) {
	         this(qualifiedName, operationName, cronJobExpression , true);
	     }

		public String getQualifiedName() {
			return qualifiedName;
		}

		public void setQualifiedName(String qualifiedName) {
			this.qualifiedName = qualifiedName;
		}

		public String getOperationName() {
			return operationName;
		}

		public void setOperationName(String operationName) {
			this.operationName = operationName;
		}

		public Boolean getAsync() {
			return async;
		}

		public void setAsync(Boolean async) {
			this.async = async;
		}
	     
	    public String getMode(){
	    	return async ? "async" : "sync" ;
	    }

		public String getCronJobExpression() {
			return cronJobExpression;
		}

		public void setCronJobExpression(String cronJobExpression) {
			this.cronJobExpression = cronJobExpression;
		}

	}
}
