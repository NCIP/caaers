package gov.nih.nci.cabig.open2caaers.binding;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.camel.Exchange;
import org.apache.camel.component.http.DefaultHttpBinding;
import org.apache.camel.component.http.HttpEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParticipantODMMessageBinding extends DefaultHttpBinding{
	private static Log log = LogFactory.getLog(ParticipantODMMessageBinding.class);
    public ParticipantODMMessageBinding(HttpEndpoint ep) {
        super();
    }
    
    public ParticipantODMMessageBinding() {
        super();
    }
    
    @Override
    public void writeResponse(Exchange exchange, HttpServletResponse response)
    		throws IOException {
    // get the status code from the mapping and set it here
    //	exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 308);
    	super.writeResponse(exchange, response);
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
