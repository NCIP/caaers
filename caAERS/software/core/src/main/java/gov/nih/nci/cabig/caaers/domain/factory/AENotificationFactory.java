/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.factory;

import gme.ccts_cabig._1_0.gov_nih_nci_cabig_ccts_ae.AeNotification;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

 
/**
 * A factory for creating AENotification objects.
 *
 * @author Biju Joseph
 */
public class AENotificationFactory {

    /**
     * Creates a new AENotification object.
     *
     * @param aeReport the ae report
     * @return the ae notification
     * @throws Exception the exception
     */
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

    
    /**
     * Gets the xml date.
     *
     * @param date the date
     * @return the xml date
     * @throws Exception the exception
     */
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
