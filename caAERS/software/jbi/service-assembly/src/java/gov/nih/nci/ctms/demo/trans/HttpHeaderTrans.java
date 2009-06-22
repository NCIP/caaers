/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.jbi.messaging.ExchangeStatus;
import javax.jbi.messaging.InOut;
import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.MessagingException;
import javax.jbi.messaging.NormalizedMessage;
import javax.jbi.messaging.MessageExchange.Role;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;

import org.apache.servicemix.MessageExchangeListener;
import org.apache.servicemix.components.util.ComponentSupport;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.xml.sax.SAXException;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class HttpHeaderTrans extends ComponentSupport implements MessageExchangeListener {

    private static final String CORRELATION_ID = "jp.props.corr_id";

    private static Map exchanges = new HashMap();
    
    private String targetServiceQName;

    public String getTargetServiceQName() {
        return targetServiceQName;
    }

    public void setTargetServiceQName(String targetServiceQName) {
        this.targetServiceQName = targetServiceQName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.servicemix.MessageExchangeListener#onMessageExchange(javax.jbi.messaging.MessageExchange)
     */
    public void onMessageExchange(MessageExchange exchange) throws MessagingException {

        if (exchange.getRole() == Role.PROVIDER) {
            processRequest(exchange);
        } else {
            processResponse(exchange);
        }

    }

    private void processResponse(MessageExchange exchange) throws MessagingException {
        
        

        if (exchange.getStatus() == ExchangeStatus.ACTIVE) {
            
            System.out.println("Endpoint: " + exchange.getEndpoint().getServiceName());
            
            String id = (String) exchange.getProperty(CORRELATION_ID);
            MessageExchange request = (MessageExchange) this.exchanges.remove(id);
            if (request == null) {
                throw new MessagingException("No exchange found for ID: " + id);
            }

            NormalizedMessage response = request.createMessage();
            String xml = null;
            try {
                xml = new SourceTransformer().contentToString(exchange.getMessage("out"));
            } catch (Exception ex) {
                throw new MessagingException("Error getting content: " + ex.getMessage(), ex);
            }
            System.out.println("XML FROM SVC: " + xml);
            response.setContent(new StreamSource(new ByteArrayInputStream(xml.getBytes())));
            
            done(exchange);
            request.setMessage(response, "out");
            send(request);
        }

    }

    private void processRequest(MessageExchange exchange) throws MessagingException {
        if (exchange.getStatus() == ExchangeStatus.ACTIVE) {

            String id = exchange.getExchangeId();
            this.exchanges.put(id, exchange);

            InOut inOut = createInOutExchange(QName.valueOf(getTargetServiceQName()), null, null);
            inOut.setProperty(CORRELATION_ID, id);

            NormalizedMessage inMsg = getInMessage(exchange);
            removeHeader(inMsg);
            
//            NormalizedMessage msg = inOut.createMessage();
//            String xml = null;
//            try {
//                xml = new SourceTransformer().contentToString(inMsg);
//            } catch (Exception ex) {
//                throw new MessagingException("Error getting content: " + ex.getMessage(), ex);
//            }
//            System.out.println("XML TO SVC: " + xml);
//            msg.setContent(new StreamSource(new ByteArrayInputStream(xml.getBytes())));
            
//            inOut.setInMessage(msg);
            inOut.setInMessage(inMsg);
            send(inOut);

        }
    }

    private void removeHeader(NormalizedMessage msg) {
        for (Iterator i = msg.getPropertyNames().iterator(); i.hasNext();) {
            String name = (String) i.next();
            Object value = msg.getProperty(name);

            if ("javax.jbi.messaging.protocol.headers".equals(name)) {
                Map headers = (Map) value;
                headers.remove("Transfer-Encoding");
            }

            if("org.apache.servicemix.soap.headers".equals(name)){
                Map headers = (Map)value;
                for(Iterator j = headers.entrySet().iterator(); j.hasNext();){
                    Entry entry = (Entry)j.next();
                    Object entryKey = entry.getKey();
                    Object entryValue = entry.getValue();
                    String out = entryKey.toString();
                    if(entryValue != null){
                        out += "=" + entryValue.getClass().getName();
                    }else{
                        out += "=null";
                    }
                    System.out.println("SOAP HEADER: " + out);
                }
            }
            
//            System.out.println("### " + name + "=" + value + " ###");
        }
    }

}
