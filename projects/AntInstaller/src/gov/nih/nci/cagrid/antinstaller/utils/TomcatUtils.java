package gov.nih.nci.cagrid.antinstaller.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.tp23.antinstaller.InstallerContext;


public class TomcatUtils {
	
	
	public static boolean httpsEnabled(String tomcatRoot){
		
		
		
		boolean httpsEnabled = false;
		
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(getServerFileName(tomcatRoot));
			Element root = doc.getRootElement();
			System.out.println(root.getName());
			Element service = root.getChild("Service");
			
			List connectors = service.getChildren("Connector");
			Iterator it = connectors.iterator();
			while(it.hasNext()){
				Element el = (Element)it.next();
				String str  = el.getAttributeValue("scheme");
				if(!StringUtilities.isBlank(str)){
					if(str.equalsIgnoreCase("https")){
						httpsEnabled= true;
					}
				}
				
			}
			
			
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return httpsEnabled;
		
	}
	
	public static String getServerFileName(String tomcatRoot){
		String str = tomcatRoot+File.separator+"conf"+File.separator+"server.xml";
		return str;
	}
	
	

}
