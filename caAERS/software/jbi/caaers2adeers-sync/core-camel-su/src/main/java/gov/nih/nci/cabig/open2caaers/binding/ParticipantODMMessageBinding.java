/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/

package gov.nih.nci.cabig.open2caaers.binding;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Exchange;
import org.apache.camel.component.http.DefaultHttpBinding;
import org.apache.camel.component.http.HttpEndpoint;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;

public class ParticipantODMMessageBinding extends DefaultHttpBinding{
	private static Log log = LogFactory.getLog(ParticipantODMMessageBinding.class);
	
	private Map<String,Integer> codeMap = new HashMap<String,Integer>();
    public ParticipantODMMessageBinding(HttpEndpoint ep) {
        super();
    }
    
    public ParticipantODMMessageBinding() {
        super();
        codeMap.put("WS_GEN_003",401);
        codeMap.put("WS_GEN_005",401);
        codeMap.put("WS_GEN_007",400); // invalid xml
        codeMap.put("WS_GEN_008",401);  // authentication not found
        codeMap.put("WS_GEN_009",401);  // wrong username/password
        codeMap.put("WS_GEN_010",500);  // code for something went wrong
        codeMap.put("WS_PMS_002",404);
        codeMap.put("WS_PMS_003",200);
        codeMap.put("WS_PMS_004",409);
        codeMap.put("WS_PMS_005",200);
        codeMap.put("WS_PMS_006",200);
        codeMap.put("WS_PMS_007",200);
        codeMap.put("WS_PMS_013",200);
        codeMap.put("WS_PMS_014",400);
        codeMap.put("WS_PMS_015",200);
        codeMap.put("WS_PMS_016",200);
        codeMap.put("WS_PMS_017",200);
    }
    
    @Override
    public void writeResponse(Exchange exchange, HttpServletResponse response)
    		throws IOException {
    	// get the status code from the mapping and set it here
    	String body = exchange.getOut().getBody(String.class);
    	
    	if (StringUtils.isBlank(body)) {
    		// if body is null do the default processing and return
    		 super.writeResponse(exchange, response);
    		 return;
    	}
    	XPath xPath = XPathFactory.newInstance().newXPath();
        String errorMessage = "";
        try {
        	errorMessage = xPath.evaluate("/Response/@ErrorClientResponseMessage", new InputSource(new StringReader(body)));
		} catch (XPathExpressionException e) {
			log.debug(e);
		}
        
        String reasonCode = "";
        
		try {
			reasonCode = xPath.evaluate("/Response/@ReasonCode", new InputSource(new StringReader(body)));
		} catch (XPathExpressionException e) {
			log.debug(e);
		}
		
		if(reasonCode.equals("soap:Client")){
			// signifies XML validation failed
			reasonCode="WS_GEN_007";
			long referenceNumber = System.currentTimeMillis();
			response.setStatus(codeMap.get(reasonCode));
			response.getWriter().write("<Response ReferenceNumber=\"" + referenceNumber + "\" IsTransactionSuccessful=\"0\" ReasonCode=\"" + reasonCode + "\" ErrorClientResponseMessage=\"" + errorMessage + "\"/>");
			
		} else {
	       exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE,codeMap.get(reasonCode));
	       super.writeResponse(exchange, response);
		}
		
    }
    
    
    @Override
    public void doWriteExceptionResponse(Throwable exception, HttpServletResponse response) throws IOException {
    	
    	long referenceNumber = System.currentTimeMillis();
    	 // and return this fixed text
        if(exception.getMessage().contains("No Authentication found")){
        	response.setStatus(401);
        	response.getWriter().write("<Response ReferenceNumber=\"" + referenceNumber + "\" IsTransactionSuccessful=\"0\" ReasonCode=\"WS_GEN_008\" ErrorClientResponseMessage=\"No Authentication found in request\"/>");
        	return;
        } 
        
        
        if(exception.getMessage().contains("Invalid Authentication")){
        	response.setStatus(401);
        	response.getWriter().write("<Response ReferenceNumber=\"" + referenceNumber + "\" IsTransactionSuccessful=\"0\" ReasonCode=\"WS_GEN009\" ErrorClientResponseMessage=\"Wrong Username/Password\"/>");
        	return;
        } 
    	
    	// return HTTP 500 to signify something went wrong
        response.setStatus(500);
        response.getWriter().write("<Response ReferenceNumber=\"" + referenceNumber + "\" IsTransactionSuccessful=\"0\" ReasonCode=\"WS_GEN0010\" ErrorClientResponseMessage=\"Unexpected internal error\"/>");
        log.debug("exception in ODM participant service " + exception.getMessage());
    }
}
