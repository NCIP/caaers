package gov.nih.nci.cabig.caaers2adeers;

import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.apache.commons.collections.map.LinkedMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class MessageAdapter extends DefaultMessage{

    Map<String, Object> headers = new LinkedHashMap<String, Object>();

    @Override
    public Map<String, Object> getHeaders() {
        return headers;
    }

    @Override
    public Object getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public void setHeader(String name, Object value){
       headers.put(name, value);
    }

}
