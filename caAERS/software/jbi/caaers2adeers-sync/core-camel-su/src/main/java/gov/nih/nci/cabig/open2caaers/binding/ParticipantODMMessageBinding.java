package gov.nih.nci.cabig.open2caaers.binding;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.component.http.DefaultHttpBinding;
import org.apache.camel.component.http.HttpEndpoint;
import org.apache.log4j.Logger;

public class ParticipantODMMessageBinding extends DefaultHttpBinding{
	private static Logger log = Logger.getLogger(ParticipantODMMessageBinding.class);
    public ParticipantODMMessageBinding(HttpEndpoint ep) {
        super();
    }
    
    public ParticipantODMMessageBinding() {
        super();
    }
    
    @Override
    public void doWriteResponse(Message message, HttpServletResponse response,
    		Exchange exchange) throws IOException {
    	// TODO Auto-generated method stub
    	super.doWriteResponse(message, response, exchange);
    //	 String caaersErrorCode = XPathBuilder.xpath("//errMessage/Response/@ReasonCode").evaluate(exchange,  String.class);
    //	 response.setStatus(getStatus(caaersErrorCode));
     //  String code = (String)exchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE);
    	 exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 308);
    	log.debug("writing response to Participant ODM request....");
    	 
    }
    
    @Override
    public void doWriteExceptionResponse(Throwable exception, HttpServletResponse response) throws IOException {
    	
    	// return HTTP 500 to signify something went wrong
        response.setStatus(500);
        
        log.debug("something went wrong with the Participant service. Returning with an exception..");
        
        // and return this fixed text
        response.getWriter().write("Unexpected internal error");
    }
}
