/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers.exchnage;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * This bean will pre-process the messages
 */
public class ODMExchangePreProcessor implements Processor {

    public static final String SYNC_HEADER = "sync_mode";
    public static final String CORRELATION_ID = "c2a_correlation_id";
    public static final String OPERATION_NAME = "c2a_operation";
    public static final String ENTITY_NAME = "c2a_entity";
    public static final String CAAERS_WS_USERNAME = "c2a_caaers_ws_username";
    public static final String CAAERS_WS_PASSWORD = "c2a_caaers_ws_password";

    
    private String caaersWSUser;
    private String caaersWSPassword;

    protected static final Log log = LogFactory.getLog(ODMExchangePreProcessor.class);

    public void process(Exchange exchange) throws Exception {
        log.debug("Processing message headers [caaersWSUser :"  + caaersWSUser
                + "caaersWSPassword :"  + caaersWSPassword);

        //set the properties in the exchange
        Map<String,Object> properties = exchange.getProperties();
        properties.put(CAAERS_WS_USERNAME, caaersWSUser);
        properties.put(CAAERS_WS_PASSWORD, caaersWSPassword);
        properties.put(OPERATION_NAME, "createParticipant");
        properties.put(ENTITY_NAME, "participant");
        properties.put(SYNC_HEADER,"sync");

        //add corelationId if it is not alreay present.
        //Object correlationId = exchange.getIn().getHeader(CORRELATION_ID);
    	log.debug("adding correlationId.");
    	Object correlationId = System.currentTimeMillis()+"";
        properties.put(CORRELATION_ID, correlationId);

        if(log.isDebugEnabled()) log.debug("Exchange properties :" + String.valueOf(properties));

    }

    public String getCaaersWSUser() {
        return caaersWSUser;
    }

    public void setCaaersWSUser(String caaersWSUser) {
        this.caaersWSUser = caaersWSUser;
    }

    public String getCaaersWSPassword() {
        return caaersWSPassword;
    }

    public void setCaaersWSPassword(String caaersWSPassword) {
        this.caaersWSPassword = caaersWSPassword;
    }

}
