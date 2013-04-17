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
        codeMap.put("WS_GEN_003",200);
        codeMap.put("WS_GEN_005",200);
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
       // code for something went wrong
        codeMap.put("500",500);
    }
    
    @Override
    public void writeResponse(Exchange exchange, HttpServletResponse response)
    		throws IOException {
    // get the status code from the mapping and set it here
    	String body = exchange.getOut().getBody(String.class);
        String xPathExpression = "/Response/@ReasonCode";
        XPath xPath = XPathFactory.newInstance().newXPath();
        String reasonCode = "500";
		try {
			reasonCode = xPath.evaluate(xPathExpression, new InputSource(new StringReader(body)));
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
       exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE,codeMap.get(reasonCode));
    	
    	super.writeResponse(exchange, response);
    }
    
    
    @Override
    public void doWriteExceptionResponse(Throwable exception, HttpServletResponse response) throws IOException {
    	
    	// return HTTP 500 to signify something went wrong
        response.setStatus(500);
        
        log.debug("Something went wrong with the Participant service. Returning with an exception..");
        
        // and return this fixed text
        response.getWriter().write("Unexpected internal error");
    }
}
