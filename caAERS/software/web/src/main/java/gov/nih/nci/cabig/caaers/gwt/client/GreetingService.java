package gov.nih.nci.cabig.caaers.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

/**
 * @author: Biju Joseph
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

    String greetServer(String name) throws IllegalArgumentException;

    /**
     * Utility/Convenience class.
     * Use GreetingService.App.getInstance() to access static instance of GreetingServiceAsync
     */
    public static class App {
        private static final GreetingServiceAsync ourInstance = (GreetingServiceAsync) GWT.create(GreetingService.class);

        public static GreetingServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
