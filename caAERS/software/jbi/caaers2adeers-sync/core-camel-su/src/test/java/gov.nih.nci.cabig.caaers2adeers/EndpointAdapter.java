/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * @author Biju Joseph
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
