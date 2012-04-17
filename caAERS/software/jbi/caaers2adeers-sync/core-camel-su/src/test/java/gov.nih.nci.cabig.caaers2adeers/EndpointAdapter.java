package gov.nih.nci.cabig.caaers2adeers;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * Created by IntelliJ IDEA.
 * User: BJW7
 * Date: 4/17/12
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class EndpointAdapter extends DefaultEndpoint {
    public boolean isSingleton() {
        return false;
    }

    public Producer createProducer() throws Exception {
        return null;
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return null;
    }
}
