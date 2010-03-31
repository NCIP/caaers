package gov.nih.nci.cabig.caaers.tools.hibernate;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.engine.StatefulPersistenceContext;
import org.hibernate.impl.SessionImpl;

import com.thoughtworks.xstream.XStream;

/**
 * This util class is used to serialize the hibernate session. We serialize only what is of interest to us.
 * @author Monish Dombla
 *
 */
public class HibernateSessionSerializer {

	private static Log logger = LogFactory.getLog(HibernateSessionSerializer.class);
	
	public static String serialize(Session session){
		String sessionContent = "";
		try{
			if(session != null){
				SessionImpl sessionImpl = (SessionImpl)session;
				StatefulPersistenceContext sPersistenceContext = (StatefulPersistenceContext)sessionImpl.getPersistenceContext();
				if(sPersistenceContext != null){
					XStream xstream = new XStream();
					xstream.setMode(XStream.ID_REFERENCES);
					sessionContent = xstream.toXML(sPersistenceContext.getEntitiesByKey());
					
					StringBuilder fileName = new StringBuilder("/Users/Moni/tempo/serializedfiles/session_");
					fileName.append(System.currentTimeMillis()).append(".xml");
					BufferedWriter out = new BufferedWriter(new FileWriter(fileName.toString())); 
					out.write(sessionContent);
					out.close(); 
					logger.debug(sessionContent);
				}
			}
		}catch(Exception e){
			logger.error("Exception while serializing hibernate session -- ", e);
		}
		return sessionContent;
	}
}