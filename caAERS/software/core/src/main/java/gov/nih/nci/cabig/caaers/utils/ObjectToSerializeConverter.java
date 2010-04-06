package gov.nih.nci.cabig.caaers.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.engine.StatefulPersistenceContext;
import org.hibernate.impl.SessionImpl;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * This is a custom converter used to marshal contents in ObjectToSerialize.
 * Contents are HttpServeltRequest,HttpSession,HibernateSession & Exception.
 * @author Monish
 *
 */
public class ObjectToSerializeConverter implements Converter{
	
	@SuppressWarnings("unchecked")
	public boolean canConvert(Class clazz) {
		return ObjectToSerialize.class == clazz;	
	}

	public void marshal(Object object, HierarchicalStreamWriter writer,MarshallingContext context) {
		if(object != null){
			ObjectToSerialize objectToSerialize = (ObjectToSerialize)object;
			
			if (objectToSerialize.getException() != null){
				writer.startNode("Exception");
				context.convertAnother(objectToSerialize.getException());
				writer.endNode();
			}
			if(objectToSerialize.getHttpRequest() != null){
				writer.startNode("HttpRequest");
				writeHttpRequest(objectToSerialize.getHttpRequest(),writer,context);
				writer.endNode();
			}
			if(objectToSerialize.getHttpSession() != null){
				writer.startNode("HttpSession");
				writeHttpSession(objectToSerialize.getHttpSession(),writer,context);
				writer.endNode();
			}
			if(objectToSerialize.getHibernateSession() != null){
				writer.startNode("HibernateSession");
				writeHibernateSession(objectToSerialize.getHibernateSession(), writer, context);
				writer.endNode();
			}
		}
	}

	public Object unmarshal(HierarchicalStreamReader reader,UnmarshallingContext context) {
		return null;
	}

	/**
	 * This method creates a Map of the session attributes & uses xstream to serialize the map.
	 * @param httpSession
	 * @param writer
	 * @param context
	 */
	@SuppressWarnings("unchecked")
	private void writeHttpSession(HttpSession httpSession,HierarchicalStreamWriter writer,MarshallingContext context){
		
		Map<String,String> attributeMap = new HashMap<String,String>();
		Enumeration<String> attributes = httpSession.getAttributeNames();
        while(attributes.hasMoreElements()){
        	String attributeName  = (String) attributes.nextElement();
        	attributeMap.put(attributeName, String.valueOf(httpSession.getAttribute(attributeName)));
        }
        writer.startNode("SessionAttributes");
        context.convertAnother(attributeMap);
        writer.endNode();

	}
	
	/**
	 * This method creates 2 Maps one for request headers & another for request attributes.
	 * @param httpRequest
	 * @param writer
	 * @param context
	 */
	@SuppressWarnings("unchecked")
	private void writeHttpRequest(HttpServletRequest httpRequest,HierarchicalStreamWriter writer,MarshallingContext context){
		
		Map<String,String> headerMap = new HashMap<String,String>();
		Map<String,String> attributeMap = new HashMap<String,String>();
		Enumeration<String> headers = httpRequest.getHeaderNames();
		Enumeration<String> attributes = httpRequest.getAttributeNames();
		
        while(headers.hasMoreElements()){
        	String headerName  = (String) headers.nextElement();
        	headerMap.put((String) headers.nextElement(), httpRequest.getHeader(headerName));
        }
        while(attributes.hasMoreElements()){
        	String attributeName  = (String) attributes.nextElement();
        	attributeMap.put(attributeName, String.valueOf(httpRequest.getAttribute(attributeName)));
        }
        writer.startNode("RequestHeaders");
        context.convertAnother(headerMap);
        writer.endNode();
        writer.startNode("RequestAttributes");
        context.convertAnother(attributeMap);
        writer.endNode();
	}
	
	/**
	 * This method will serialize all the entities in the hibernate session.
	 * @param session
	 * @param writer
	 * @param context
	 */
	private void writeHibernateSession(Session session,HierarchicalStreamWriter writer,MarshallingContext context){
		SessionImpl sessionImpl = (SessionImpl)session;
		StatefulPersistenceContext sPersistenceContext = (StatefulPersistenceContext)sessionImpl.getPersistenceContext();
		if(sPersistenceContext != null){
			writer.startNode("HibernateSession");
			context.convertAnother(sPersistenceContext.getEntitiesByKey());
			writer.endNode();
		}
	}
}
