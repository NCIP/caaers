package gov.nih.nci.cabig.open2caaers.binding;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.camel.component.http.DefaultHttpBinding;
import org.apache.camel.component.http.HttpEndpoint;

public class ParticipantODMMessageBinding extends DefaultHttpBinding {
    public ParticipantODMMessageBinding(HttpEndpoint ep) {
        super();
    }
    
    public ParticipantODMMessageBinding() {
        super();
    }
    @Override
    public void doWriteExceptionResponse(Throwable exception, HttpServletResponse response) throws IOException {
        // we override the doWriteExceptionResponse as we only want to alter the binding how exceptions is
        // written back to the client. 

        // we just return HTTP 200 so the client thinks its okay
    //	System.out.println("______________________In Participant binding_________________________________");
        response.setStatus(200);
        // and we return this fixed text
        response.getWriter().write("Something went wrong but we dont care");
    }
}
