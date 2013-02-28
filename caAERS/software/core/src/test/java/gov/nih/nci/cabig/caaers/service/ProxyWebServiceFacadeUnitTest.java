/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.beans.PropertyDescriptor;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathFactory;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.migrator.StudyConverter;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperty;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.easymock.EasyMock;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.CommonsHttpMessageSender;
import org.xml.sax.InputSource;

public class ProxyWebServiceFacadeUnitTest extends AbstractTestCase {

	private ProxyWebServiceFacade proxyWebServiceFacade;
    private WebServiceTemplate webServiceTemplate;
    private Configuration configuration;
    private StudyDao studyDao;
    

	// private StudyParticipantAssignmentDao studyParticipantAssignmentDao;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		proxyWebServiceFacade = new ProxyWebServiceFacade();
		SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory();
		saajSoapMessageFactory.afterPropertiesSet();
		webServiceTemplate = new WebServiceTemplate(saajSoapMessageFactory);
		webServiceTemplate.setMessageSender(new CommonsHttpMessageSender());
		configuration = registerMockFor(Configuration.class);
        studyDao = registerDaoMockFor(StudyDao.class);
		proxyWebServiceFacade.setConfiguration(configuration);
		proxyWebServiceFacade.setWebServiceTemplate(webServiceTemplate);
		proxyWebServiceFacade
				.setDefaultUri("http://localhost:8196/GenericProcessorService");
		
	}

    public void testSearchStudyThrowingException(){
        proxyWebServiceFacade = new ProxyWebServiceFacade(){
            @Override
            public String simpleSendAndReceive(String message) {
                if(true) throw new RuntimeException("Unable to import study :No method");
                return mockSearchStudyResponse();
            }
        };
        proxyWebServiceFacade.setStudyConverter(new StudyConverter());

        try{
            List<Study> studyList = proxyWebServiceFacade.searchStudies("5876")  ;
            fail("Must throw caaers exception");
        }catch(CaaersSystemException e){
           assertContains( e.getMessage(),"Unable to import study :No method");
        }
        
        
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
        assertNotNull(s.getFundingSponsorIdentifier());
        assertEquals("5876",s.getFundingSponsorIdentifierValue());
    }
    
    public void testSyncStudyNoAdEERSCallNeeded(){
        proxyWebServiceFacade = new ProxyWebServiceFacade(){
            @Override
            public String simpleSendAndReceive(String message) {
                return mockStudyDetailsResponse();
            }
        };
        
        proxyWebServiceFacade.setConfiguration(configuration);
        final List<Study> studyList = new ArrayList<Study>();
        Study s1 = Fixtures.createStudy("test");
        s1.setId(99);
        s1.setLastSynchedDate(new Date());
        studyList.add(s1);
        proxyWebServiceFacade.setStudyDao(studyDao);
        
        EasyMock.expect(studyDao.getById(1)).andReturn(s1).anyTimes();
        EasyMock.expect(configuration.get(Configuration.STUDY_SYNC_DELAY)).andReturn(10).anyTimes();
        replayMocks();
        
        String retVal = proxyWebServiceFacade.updateStudy(1, false);
        assertEquals("99", retVal);
        verifyMocks(); 
    }
    public void testSyncStudyError(){
        proxyWebServiceFacade = new ProxyWebServiceFacade(){
            @Override
            public String simpleSendAndReceive(String message) {
                return mockStudyDetailsResponse();
            }
        };

        proxyWebServiceFacade.setConfiguration(configuration);

        proxyWebServiceFacade.setStudyDao(studyDao);
        EasyMock.expect(studyDao.getById(1)).andReturn(null).anyTimes();
        EasyMock.expect(configuration.get(Configuration.STUDY_SYNC_DELAY)).andReturn(null).anyTimes();
        replayMocks();

        String retVal = proxyWebServiceFacade.updateStudy(1, false);
        assertEquals("Unable to find the study (1)", retVal);
        verifyMocks();
    }
    public void testSyncStudyValid(){
        proxyWebServiceFacade = new ProxyWebServiceFacade(){
            @Override
            public String simpleSendAndReceive(String message) {
                return mockStudyDetailsResponse();
            }
        };

        proxyWebServiceFacade.setConfiguration(configuration);
        final List<Study> studyList = new ArrayList<Study>();
        Study s1 = Fixtures.createStudy("test");
        s1.setId(99);
        s1.setLastSynchedDate(new Date());
        studyList.add(s1);

        proxyWebServiceFacade.setStudyDao(studyDao);
        EasyMock.expect(studyDao.getById(1)).andReturn(s1).anyTimes();
        EasyMock.expect(configuration.get(Configuration.STUDY_SYNC_DELAY)).andReturn(null).anyTimes();
        replayMocks();

        String retVal = proxyWebServiceFacade.updateStudy(1, false);
        assertContains("1", retVal);
        verifyMocks();
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
    private String mockStudyDetailsResponse(){
        return "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <soap:Body>\n" +
                "      <payload correlationId=\"1336138727601\" xmlns:com=\"http://schema.integration.caaers.cabig.nci.nih.gov/common\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "         <system>caaers</system>\n" +
                "         <response>\n" +
                "            <entity xmlns:stud=\"http://schema.integration.caaers.cabig.nci.nih.gov/study\">study</entity>\n" +
                "            <operation name=\"updateStudyResponse\" xmlns:stud=\"http://schema.integration.caaers.cabig.nci.nih.gov/study\">\n" +
                "               <status>Processed</status>\n" +
                "               <data>\n" +
                "                  <ns3:entityProcessingOutcome xmlns:ns1=\"http://schema.integration.caaers.cabig.nci.nih.gov/study\" xmlns:ns3=\"http://schema.integration.caaers.cabig.nci.nih.gov/common\" xmlns:ns4=\"http://schema.integration.caaers.cabig.nci.nih.gov/investigator\" xmlns:ns5=\"http://schema.integration.caaers.cabig.nci.nih.gov/researchstaff\" xmlns:ns6=\"http://schema.integration.caaers.cabig.nci.nih.gov/participant\">\n" +
                "                     <klassName>gov.nih.nci.cabig.caaers.domain.Study</klassName>\n" +
                "                     <businessIdentifier>N027D</businessIdentifier>\n" +
                "                     <message>Study with Short Title  \"A Phase I Study of CCI-779 and Temozolomide in Combination with Radiation Therapy in Glioblastoma Multiforme\" updated in caAERS</message>\n" +
                "                     <dataBaseId>1</dataBaseId>\n" +
                "                     <failed>false</failed>\n" +
                "                  </ns3:entityProcessingOutcome>\n" +
                "               </data>\n" +
                "            </operation>\n" +
                "         </response>\n" +
                "      </payload>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
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
//		sb.append("<criterion name=\"createdDate\">05-08-2012</criterion>");
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
	
	public void testConcurrency() throws Exception{
		EasyMock.expect(configuration.get(Configuration.ESB_WS_URL)).andReturn("http://localhost:8196/GenericProcessorService").anyTimes();
		replayMocks();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<String>> list = new ArrayList<Future<String>>();
		List<String> correlationIDs = new ArrayList<String>();
		int NUM_OF_LOOPS = 0;
		for(int i = 0 ; i<NUM_OF_LOOPS ; i++){
			for (EntityOperation entityOperation : EntityOperation.values()) {
				String correlationId = RandomStringUtils.randomAlphanumeric(10);
				correlationIDs.add(correlationId);
				System.out.println(entityOperation+" - "+correlationId);
				Callable<String> worker = new ProxyWebServiceCallable(correlationId, entityOperation.getQualifiedName(), entityOperation.getOperationName(), proxyWebServiceFacade);
				Future<String> submit = executor.submit(worker);
				list.add(submit);
			}
			Thread.sleep(10000);
		}
		//System.out.println(list.size());
		// Now retrieve the result
		for (int i=0 ; i<list.size() ; i++) {
			try {
				Matcher m = Pattern.compile("coorelationId=\"(.*)\"><system").matcher(list.get(i).get());
				String correlationId="";
				try {
					correlationId = m.group(1);
				} catch (Exception e) {
					System.out.println("no correlation id found");
				}
				System.out.println("\n[expected, actual] - ["+correlationIDs.get(i)+", "+correlationId+"]");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		verifyMocks();
	}
	
//	private static final String tns = "http://webservice.caaers.cabig.nci.nih.gov/GenericProcessor/";
//
//	private static final QName SERVICE_NAME = new QName(tns, "GenericProcessor");
//	private static final QName OP_NAME = new QName(tns, "Process");
//	
//	private static final String packageName="gov.nih.nci.cabig.caaers.webservice.genericprocessor";
//	private static final String genericRequestClassName="GenericRequest";
//	private static final String payloadClassName="GenericRequest$Payload";
//	private static final String requestClassName="Request";
//	private static final String operationClassName="Request$Operation";
//	private static final String criteriaClassName="Request$Operation$Criteria";
//	private static final String criterionClassName="Request$Operation$Criteria$Criterion";
//	
//	private static final String wsdlURLStr = "http://localhost:8196/GenericProcessorService?wsdl";
////	private static final String wsdlURLStr = "http://10.10.10.41:8196/GenericProcessorService?wsdl";
//
//	public void testDynaClient() throws Exception{
//		URL wsdlURL = new URL(wsdlURLStr);
//		System.out.println(wsdlURL.toExternalForm());
//
//		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory
//				.newInstance();
//		Client client = factory.createClient(wsdlURL.toExternalForm(),
//				SERVICE_NAME);
//		client.getInInterceptors().add(new LoggingInInterceptor());
//		client.getOutInterceptors().add(new LoggingOutInterceptor());
//		
//		Object genericRequest = Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+genericRequestClassName).newInstance();
//		Object payload = Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+payloadClassName).newInstance();
//		Object request = Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+requestClassName).newInstance();
//		Object operation = Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+operationClassName).newInstance();
//		Object criteria = Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+criteriaClassName).newInstance();
//		Object criterion = Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+criterionClassName).newInstance();
//		
//		new PropertyDescriptor("payload", genericRequest.getClass()).getWriteMethod().invoke(genericRequest, payload);
//		new PropertyDescriptor("request", payload.getClass()).getWriteMethod().invoke(payload, request);
//		new PropertyDescriptor("operation", request.getClass()).getWriteMethod().invoke(request, operation);
//		new PropertyDescriptor("criteria", operation.getClass()).getWriteMethod().invoke(operation, criteria);
//		List criterions = (List)criteria.getClass().getMethod("getCriterions").invoke(criteria);
//		criterions.add(criterion);
//		
//		String coorelationId = RandomStringUtils.randomAlphabetic(5);
//		new PropertyDescriptor("correlationId", payload.getClass()).getWriteMethod().invoke(payload, coorelationId);
//		new PropertyDescriptor("system", payload.getClass()).getWriteMethod().invoke(payload, "adeers");
//		new PropertyDescriptor("entity", request.getClass()).getWriteMethod().invoke(request, "agent");
//		new PropertyDescriptor("name", operation.getClass()).getWriteMethod().invoke(operation, "getAgentsLOV");
//		new PropertyDescriptor("mode", operation.getClass()).getWriteMethod().invoke(operation, "async");
//		new PropertyDescriptor("name", criterion.getClass()).getWriteMethod().invoke(criterion, "createdDate");
//		new PropertyDescriptor("value", criterion.getClass()).getWriteMethod().invoke(criterion, "12-02-2011");
//		
//		System.out.println("Invoking client with coorrelation id- "+coorelationId);
//		Object[] res = client.invoke(OP_NAME, genericRequest);
//		System.out.println("Client invocation complete ");
//		for(Object returnObj : res){
//			System.out.println(returnObj.getClass());
//		}
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
