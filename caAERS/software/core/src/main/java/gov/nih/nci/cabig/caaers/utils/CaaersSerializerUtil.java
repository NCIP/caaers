package gov.nih.nci.cabig.caaers.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;

/**
 * This class uses XStream & serialize's contents of ObjectToSerialize class.
 * @author Monish
 *
 */
public class CaaersSerializerUtil {
	
	private static Log logger = LogFactory.getLog(CaaersSerializerUtil.class);
	public static final String CATALINA_HOME = System.getenv("CATALINA_HOME");
	public static final String USER_HOME = System.getProperty("user.home");
	
	/**
	 * This method serializes Entities in the ObjectToSerialize. This class contains HttpRequest,HttpSession,HibernateSession & Exception.
	 * @param objectToSerialize
	 * @return
	 */
	public static String serialize(ObjectToSerialize objectToSerialize){
		String serializedContent = "";
		
		try{
			if(objectToSerialize != null){
				XStream xstream = new XStream();
				xstream.registerConverter(new ObjectToSerializeConverter());
				xstream.setMode(XStream.ID_REFERENCES);
				serializedContent = xstream.toXML(objectToSerialize); 
				if(!StringUtils.isEmpty(serializedContent)){
					dumpContentToFile(serializedContent);
				}
			}
		}catch(Exception e){
			logger.error("Exception while serializing --",e);
		}
		return serializedContent;
	}
	
	/**
	 * This method writes given content to a xml file. Location of the file is $CATALINA_HOME/logs/serializedfiles/
	 * @param serializedContent
	 */
	public static void dumpContentToFile(String content){
		BufferedWriter out = null;
		StringBuilder sb = null;
		try{
			if(StringUtils.isEmpty(CATALINA_HOME)){
				sb = new StringBuilder(USER_HOME);
			}else{
				sb = new StringBuilder(System.getenv("CATALINA_HOME"));
				sb.append("/logs");
			}
			sb.append("/serializedfiles");
			File file = new File(sb.toString());
			if(!file.isDirectory()){
				file.mkdir();
			}
			sb.append("/session_").append(System.currentTimeMillis()).append(".xml");
			out = new BufferedWriter(new FileWriter(sb.toString()));
			out.write(content);
		}catch(Exception e){
			logger.error("Exception while writing contect to file -- ", e);
		}finally{
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}
}
