package gov.nih.nci.cabig.caaers.security.passwordpolicy.xml;

import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

import java.io.File;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ObjectFormatter {
	
	public Object formatObject(String xmlFileName) throws Exception{
		Object obj = null;
		try {
            JAXBContext jc = JAXBContext.newInstance ("gov.nih.nci.cabig.caaers.security.passwordpolicy");

            Unmarshaller u = jc.createUnmarshaller ();
            
            URL url = this.getClass().getClassLoader().getResource(xmlFileName);

           File f = new File (url.getFile());
            obj =  u.unmarshal (f);

          // obj= (Object) element.getValue ();
          
       } catch (Exception e) {
          throw e;
       }
		return obj;
	}
	
	public void saveObject(PasswordPolicy policy, String xmlFileName) throws Exception{
		try {
            JAXBContext jc = JAXBContext.newInstance ("gov.nih.nci.cabig.caaers.security.passwordpolicy");

           Marshaller marshaller = jc.createMarshaller();
           
           
            
           // URL url = this.getClass().getClassLoader().getResource(xmlFileName);

           File f = new File (xmlFileName);
           marshaller.marshal(policy, f);

          // obj= (Object) element.getValue ();
          
       } catch (Exception e) {
          throw e;
       }
	}

}

