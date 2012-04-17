package gov.nih.nci.cabig.caaers2adeers;

import org.apache.camel.*;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.spi.UnitOfWork;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: BJW7
 * Date: 4/17/12
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExchangeAdapter implements Exchange {
    Message in = new MessageAdapter();
    public ExchangePattern getPattern() {
        return null;  
    }

    public void setPattern(ExchangePattern exchangePattern) {
        
    }

    public Object getProperty(String s) {
        return null;  
    }

    public Object getProperty(String s, Object o) {
        return null;  
    }

    public <T> T getProperty(String s, Class<T> tClass) {
        return null;  
    }

    public <T> T getProperty(String s, Object o, Class<T> tClass) {
        return null;  
    }

    public void setProperty(String s, Object o) {
        
    }

    public Object removeProperty(String s) {
        return null;  
    }

    public Map<String, Object> getProperties() {
        return null;  
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
        return null;  
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
