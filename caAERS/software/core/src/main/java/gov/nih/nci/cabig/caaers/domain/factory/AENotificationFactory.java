package gov.nih.nci.cabig.caaers.domain.factory;

import gme.ccts_cabig._1_0.gov_nih_nci_cabig_ccts_ae.AeNotification;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author Biju Joseph
 */
public class AENotificationFactory {

    public AeNotification createAENotificationForExpeditedAdverseEventReport(final ExpeditedAdverseEventReport aeReport) throws Exception {
        AeNotification aeNotification = new AeNotification();
        aeNotification.setRegistrationGridId(aeReport.getAssignment().getGridId());
        Date detectionDate = aeReport.getAdverseEvents().get(0).getStartDate();
        if (detectionDate == null) detectionDate = aeReport.getCreatedAt();
        //aeNotification.setDetectionDate(new java.sql.Date(detectionDate.getTime()));
        aeNotification.setDetectionDate(getXmlDate(detectionDate));		
        aeNotification.setDescription(aeReport.getNotificationMessage());
        return aeNotification;

    }

    public AeNotification createAENotificationForRoutineAdverseEventReport(final RoutineAdverseEventReport roReport) throws Exception {
        AeNotification aeNotification = new AeNotification();
        aeNotification.setRegistrationGridId(roReport.getAssignment().getGridId());
        Date detectionDate = roReport.getAdverseEvents().get(0).getStartDate();
        if (detectionDate == null) detectionDate = roReport.getStartDate();
        //aeNotification.setDetectionDate(new java.sql.Date(detectionDate.getTime()));
        aeNotification.setDetectionDate(getXmlDate(detectionDate));
        aeNotification.setDescription(roReport.getNotificationMessage());
        return aeNotification;
    }
    
    private XMLGregorianCalendar getXmlDate(Date date) throws Exception {
    	GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        XMLGregorianCalendar calendar =  
            DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(gc);
        
		XMLGregorianCalendar calendar2 =  
		    DatatypeFactory.newInstance().newXMLGregorianCalendar();
		
		calendar2.setDay(calendar.getDay());
		calendar2.setMonth(calendar.getMonth());
		calendar2.setYear(calendar.getYear());
		
		return calendar2;
    }
}