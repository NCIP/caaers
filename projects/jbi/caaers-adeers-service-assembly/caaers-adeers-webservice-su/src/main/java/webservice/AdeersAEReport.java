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

import caaers.client.AEReportJobInfo;

public class AdeersAEReport extends ComponentSupport implements MessageExchangeListener {
    
    private static final Log log = LogFactory.getLog(AdeersAEReport.class); 
    private AdeersWebService adeersWebService;

    public void setAdeersWebService(AdeersWebService adeersWebService) {
		this.adeersWebService = adeersWebService;
	}

	public AdeersAEReport() {
        super(new QName(Constants.CAAERS_ADEERS_NS, Constants.CAAERS_LOG), "input");
    }
    
    public void onMessageExchange(MessageExchange exchange) throws MessagingException {
    	System.out.println("recieved request");
        processInputRequest(exchange);
    }

    private void processInputRequest(MessageExchange exchange) throws MessagingException{
    	String inXml="";
    	try {
    		inXml=new SourceTransformer().contentToString(exchange.getMessage("in"));
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
    	//System.out.println("XML to be processed: "+inXML);
		
		//String outXml=this.adeersWebService.callWebService(inXml);
		
		String aeReportJobInfoStr="";
		
		try {
			aeReportJobInfoStr = this.adeersWebService.callWebService(inXml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

    	NormalizedMessage response = exchange.createMessage();
        response.setContent(new StreamSource(new StringReader(aeReportJobInfoStr)));
        exchange.setMessage(response, "out");
        System.out.println("sending reply...");
        send(exchange);
    }
}
