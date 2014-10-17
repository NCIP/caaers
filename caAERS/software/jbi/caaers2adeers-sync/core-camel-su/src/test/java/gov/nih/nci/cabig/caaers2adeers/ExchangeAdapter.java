/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import org.apache.camel.*;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.spi.UnitOfWork;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Biju Joseph
 */
public class ExchangeAdapter implements Exchange {
    Message in = new MessageAdapter();
    Message out = new MessageAdapter();
    Map<String, Object> properties = new HashMap<String, Object>() ;
    public ExchangePattern getPattern() {
        return null;  
    }

    public void setPattern(ExchangePattern exchangePattern) {
        
    }

    public Object getProperty(String s) {
        return properties.get(s);
    }

    public Object getProperty(String s, Object o) {
        return properties.get(o);
    }

    public <T> T getProperty(String s, Class<T> tClass) {
        return (T) getProperty(s);
    }

    public <T> T getProperty(String s, Object o, Class<T> tClass) {
        return null;  
    }

    public void setProperty(String s, Object o) {
        properties.put(s, o);
    }

    public Object removeProperty(String s) {
        return null;  
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public boolean hasProperties() {
        return false;  
    }

    public Message getIn() {
        return in;
    }

    public <T> T getIn(Class<T> tClass) {
        return null;  
    }

    public void setIn(Message message) {
        
    }

    public Message getOut() {
        return out;
    }

    public <T> T getOut(Class<T> tClass) {
        return null;  
    }

    public boolean hasOut() {
        return false;  
    }

    public void setOut(Message message) {
        
    }

    public Exception getException() {
        return null;  
    }

    public <T> T getException(Class<T> tClass) {
        return null;  
    }

    public void setException(Exception e) {
        
    }

    public boolean isFailed() {
        return false;  
    }

    public boolean isTransacted() {
        return false;  
    }

    public boolean isRollbackOnly() {
        return false;  
    }

    public CamelContext getContext() {
        return new DefaultCamelContext(){
            @Override
            public TypeConverter getTypeConverter() {
                return super.getTypeConverter();
            }
        };
    }

    public Exchange copy() {
        return null;  
    }

    public Endpoint getFromEndpoint() {
        return null;  
    }

    public void setFromEndpoint(Endpoint endpoint) {
        
    }

    public UnitOfWork getUnitOfWork() {
        return null;  
    }

    public void setUnitOfWork(UnitOfWork unitOfWork) {
        
    }

    public String getExchangeId() {
        return null;  
    }

    public void setExchangeId(String s) {
        
    }

    public void addOnCompletion(Synchronization synchronization) {
        
    }
}
