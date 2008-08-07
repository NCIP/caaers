package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.utils.XmlMarshaller;

import java.util.ArrayList;
import java.util.List;

public class AdverseEventSerializer {

	
	   private AdverseEventDao adverseEventDao;
	   private AdverseEvent adverseEventDataObject;

	   
	   //TO-DO set in spring config
	   private String mappingFile = "xml-mapping/ae-report-xml-mapping.xml";

	   
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

			AdverseEvent ae = this.getAdverseEvent(adverseEventDataObject,0);
			xml = marshaller.toXML(ae,getMappingFile());
		

			return xml;
	   }


	  
	    private AdverseEvent getAdverseEvent(AdverseEvent ae , int seq) throws Exception {
	    	AdverseEvent adverseEvent = new AdverseEvent();
	    	try {
		    	adverseEvent.setDetailsForOther(ae.getDetailsForOther());
		    	adverseEvent.setExpected(ae.getExpected());
		    	adverseEvent.setComments(ae.getComments());
		    	adverseEvent.setStartDate(ae.getStartDate());
		    	adverseEvent.setEndDate(ae.getEndDate());
		    	adverseEvent.setConcomitantMedicationAttributions(ae.getConcomitantMedicationAttributions());
	
		    	List<OtherCauseAttribution> otList = new ArrayList<OtherCauseAttribution>();
	
		    	for (OtherCauseAttribution ot : ae.getOtherCauseAttributions()) {
		    		otList.add(getOtherCauseAttribution(ot));
		    	}
	
		    	adverseEvent.setOtherCauseAttributions(otList);
		    	adverseEvent.setCourseAgentAttributions(ae.getCourseAgentAttributions());
	
		    	
		    	adverseEvent.setDiseaseAttributions(ae.getDiseaseAttributions());
		    	adverseEvent.setSurgeryAttributions(ae.getSurgeryAttributions());
		    	adverseEvent.setRadiationAttributions(ae.getRadiationAttributions());
		    	adverseEvent.setDeviceAttributions(ae.getDeviceAttributions());
	
	
				if (ae.getAdverseEventTerm().getClass().getName().equals("gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm")) {
					adverseEvent.setAdverseEventMeddraLowLevelTerm(ae.getAdverseEventMeddraLowLevelTerm());
				} else {
					adverseEvent.getAdverseEventCtcTerm().setCtcTerm(ae.getAdverseEventCtcTerm().getCtcTerm());
				}
				adverseEvent.setLowLevelTerm(ae.getLowLevelTerm());
	
				
		    	adverseEvent.setHospitalization(ae.getHospitalization());
		    	adverseEvent.setGrade(ae.getGrade());
		    	adverseEvent.setAttributionSummary(ae.getAttributionSummary());
		    	adverseEvent.setExpected(ae.getExpected());
		    	/*
		    	if (seq == 0 ) {
		    		adverseEvent.setGridId("PRY"+ae.getGridId());
		    	} else {
		    		adverseEvent.setGridId("PRN"+ae.getGridId());
		    	}
		    	*/
		    	
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getAdverseEvent() "+e.getMessage() , e);
	    	}

	    	return adverseEvent;
	    }


	    private OtherCauseAttribution getOtherCauseAttribution(OtherCauseAttribution oca) throws Exception {
	    	OtherCauseAttribution otherCauseAttribution = new OtherCauseAttribution();
	    	try {
	    		otherCauseAttribution.setAttribution(oca.getAttribution());
	    		otherCauseAttribution.setCause(getOtherCause(oca.getCause()));
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getOtherCauseAttribution() "+e.getMessage() , e);
	    	}
	    	return otherCauseAttribution;
	    }


	    private OtherCause getOtherCause(OtherCause oc) throws Exception {
	    	OtherCause otherCause = new OtherCause();
		    try {
		    	otherCause.setText(oc.getText());
	    	} catch (Exception e) {
	    		throw new Exception ("Error building getOtherCause() "+e.getMessage() , e);
	    	}
	    	return otherCause;

	    }

	
		public String getMappingFile() {
			return mappingFile;
		}

//		public void setMappingFile(String mappingFile) {
	//		this.mappingFile = mappingFile;
		//}
		
		public static void main (String[] args) {
			//
			AdverseEventReportSerializer aes = new AdverseEventReportSerializer();
			ExpeditedAdverseEventReport aer = new ExpeditedAdverseEventReport();
			aer.setId(123);
			
			try {
				XmlMarshaller marshaller = new XmlMarshaller();
				String	xml = marshaller.toXML(aer,aes.getMappingFile());
				System.out.print(xml);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public AdverseEventDao getAdverseEventDao() {
			return adverseEventDao;
		}

		public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
			this.adverseEventDao = adverseEventDao;
		}
}
