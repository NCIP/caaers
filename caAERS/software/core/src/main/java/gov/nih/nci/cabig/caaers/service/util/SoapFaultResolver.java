package gov.nih.nci.cabig.caaers.service.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.client.core.SoapFaultMessageResolver;

public class SoapFaultResolver extends SoapFaultMessageResolver {

	protected final Log log = LogFactory.getLog(getClass());
	
	public SoapFaultResolver() {
		
	}
	
	
	@Override
	public void resolveFault(WebServiceMessage message) throws IOException {
		try {
			super.resolveFault(message);
		} catch (SoapFaultClientException sfce) {
			log.error("Got a client Webservice fault, for reason: '" + sfce.getFaultStringOrReason() + "', detail; '" + sfce.getSoapFault().getFaultDetail() + "'; throwing it up the stack.");
			throw sfce;
		}
	}

}
