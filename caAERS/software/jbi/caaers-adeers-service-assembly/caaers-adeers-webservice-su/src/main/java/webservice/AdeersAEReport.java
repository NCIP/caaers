/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package webservice;

import java.io.IOException;
import java.io.StringReader;


import javax.jbi.messaging.ExchangeStatus;
import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.MessagingException;
import javax.jbi.messaging.NormalizedMessage;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.servicemix.MessageExchangeListener;
import org.apache.servicemix.components.util.ComponentSupport;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.xml.sax.SAXException;

public class AdeersAEReport extends ComponentSupport implements MessageExchangeListener {
    
	private static final Log log = LogFactory.getLog(AdeersAEReport.class); 
    private AdeersWebService adeersWebService;
    private String caaersAeReportId = "";
    private String reportId = "";
    private String submitterEmail = "";
    private StringBuilder exceptionXmlBuilder = null;
    
    public void setAdeersWebService(AdeersWebService adeersWebService) {
		this.adeersWebService = adeersWebService;
	}

	public AdeersAEReport() {
        super(new QName(Constants.CAAERS_ADEERS_NS, Constants.CAAERS_LOG), "input");
    }
    
    public void onMessageExchange(MessageExchange exchange) throws MessagingException {
    		
    		log.debug("Received Request");
    		log.debug("Status :: ["+ exchange.getStatus()+"]");
    		log.debug("Active :: ["+ exchange.getStatus()== ExchangeStatus.ACTIVE + "]");
    		log.debug("Done   :: ["+ exchange.getStatus()== ExchangeStatus.DONE + "]");
    		
    		if(exchange.getStatus() == ExchangeStatus.ACTIVE){    			
    			log.info("Current Status  :: "+ exchange.getStatus());
    			processInputRequest(exchange);
    		}else{
    			//Exchange Already Processed    			
    			log.debug("Current Status  :: "+ exchange.getStatus());
    		}
    }

    private void processInputRequest(MessageExchange exchange) throws MessagingException{
    	String inXml="";
    	try {
    		inXml=new SourceTransformer().contentToString(exchange.getMessage("in"));
    		log.debug("------XML to be processed  Begin--------- ");
    		log.debug( inXml);
    		log.debug("------XML to be processed End--------- "); 
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
		//String outXml=this.adeersWebService.callWebService(inXml);
		
		String aeReportJobInfoStr="";
		
		try {
			aeReportJobInfoStr = this.adeersWebService.callWebService(inXml);
			log.info("------------Response From Adeers Begin---------");
			log.info(aeReportJobInfoStr);
			log.info("------------Response From Adeers End---------");
		} catch (Exception e) {
			//CAAERS-101 - Monish Dombla
			log.error("--Exception While Invoking adEERS WebService--", e);			
			aeReportJobInfoStr = constructCommunicationExceptionXml(inXml,e);
		}				
    	NormalizedMessage response = exchange.createMessage();
        response.setContent(new StreamSource(new StringReader(aeReportJobInfoStr)));
        exchange.setMessage(response, "out");        
        log.info("Sending Reply to caAERS");
        send(exchange);               
    }
    
    /**This method constructs an xml message similar to adEERS response xml.
     * Used to handle the Webservice communication exception flow. 
     * CAAERS-101 - Monish Dombla
     * @param inXml
     * @param e
     * @return String
     */
    private String constructCommunicationExceptionXml(String inXml,Exception e){
    	
    	int si = inXml.indexOf("<CAEERS_AEREPORT_ID>");
		int ei = inXml.indexOf("</CAEERS_AEREPORT_ID>");
		caaersAeReportId = inXml.substring(si+20, ei);
		
		si = inXml.indexOf("<REPORT_ID>");
		ei = inXml.indexOf("</REPORT_ID>");
		reportId = inXml.substring(si+11, ei);
		
		si = inXml.indexOf("<SUBMITTER_EMAIL>");
		ei = inXml.indexOf("</SUBMITTER_EMAIL>");
		submitterEmail = inXml.substring(si+17, ei);
    	
		exceptionXmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("\n");
		exceptionXmlBuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"").append("\n");
							exceptionXmlBuilder.append("xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"").append("\n");
							exceptionXmlBuilder.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">").append("\n");
		exceptionXmlBuilder.append("<soapenv:Body>").append("\n");
		exceptionXmlBuilder.append("<submitAEDataXMLAsAttachmentResponse xmlns=\"\">").append("\n");		
		exceptionXmlBuilder.append("<ns1:AEReportJobInfo xmlns:ns1=\"http://localhost:8080/AdEERSWSMap/services/AEReportXMLService\">").append("\n");
		exceptionXmlBuilder.append("<jobExceptions>").append("\n");
			exceptionXmlBuilder.append("<code>").append("caAERS-adEERS : COMM_ERR").append("</code>").append("\n");
			exceptionXmlBuilder.append("<description>").append("adEERS WebService Communication Failure").append("</description>").append("\n");
		exceptionXmlBuilder.append("</jobExceptions>").append("\n");
			exceptionXmlBuilder.append("<reportStatus>").append("ERROR").append("</reportStatus>").append("\n");
			exceptionXmlBuilder.append("<comments>").append(e.toString()).append("</comments>").append("\n");			
			exceptionXmlBuilder.append("<REPORT_ID>").append(reportId).append("</REPORT_ID>").append("\n");
			exceptionXmlBuilder.append("<CAEERS_AEREPORT_ID>").append(caaersAeReportId).append("</CAEERS_AEREPORT_ID>").append("\n");
			exceptionXmlBuilder.append("<SUBMITTER_EMAIL>").append(submitterEmail).append("</SUBMITTER_EMAIL>").append("\n");
		exceptionXmlBuilder.append("</ns1:AEReportJobInfo>").append("\n");		
		exceptionXmlBuilder.append("</submitAEDataXMLAsAttachmentResponse>").append("\n");
		exceptionXmlBuilder.append("</soapenv:Body>").append("\n");
		exceptionXmlBuilder.append("</soapenv:Envelope>");
		log.debug("-------Exception XML Start--------------");
		log.debug(exceptionXmlBuilder.toString());
		log.debug("-------Exception XML End----------------");		
		return  exceptionXmlBuilder.toString();    	
    }
}
