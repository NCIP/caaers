package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.utils.XmlMarshaller;

public class AdverseEventSerializer {

	   private AdverseEvent adverseEventDataObject;

	   
	   public String serialize (AdverseEvent adverseEventDataObject) throws Exception{
		   this.adverseEventDataObject = adverseEventDataObject;
		   return serialize();
	   }

	  
	   /**
	    *
	    * @return
	    * @throws Exception
	    */
	   private String serialize() throws Exception{


		   String xml = "";

			XmlMarshaller marshaller = new XmlMarshaller();
			AdverseEventReportSerializer aes = new AdverseEventReportSerializer();

			AdverseEvent ae = aes.getAdverseEvent(adverseEventDataObject,0);
			xml = marshaller.toXML(ae,aes.getMappingFile());
		

			return xml;
	   }


}
