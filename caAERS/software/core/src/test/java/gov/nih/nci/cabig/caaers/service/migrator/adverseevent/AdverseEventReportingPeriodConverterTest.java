/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventCtepTermType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventMeddraLowLevelTermType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.HospitalizationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.AMPM;
import gov.nih.nci.cabig.caaers.integration.schema.common.TimeType;
import gov.nih.nci.cabig.caaers.integration.schema.manageae.AdverseEvents;
import gov.nih.nci.cabig.caaers.integration.schema.manageae.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CourseType;
import gov.nih.nci.cabig.caaers.integration.schema.manageae.Criteria;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.TestCase;

/**
 * User: Biju Joseph
 * Date: 1/29/13
 */
public class AdverseEventReportingPeriodConverterTest extends TestCase {

    Date startDate;
    Date endDate;
    AdverseEventReportingPeriodConverter converter;
    public void setUp() throws Exception{
        startDate = DateUtils.parseDate("04/11/2011");
        endDate = DateUtils.parseDate("05/11/2011");
        converter = new AdverseEventReportingPeriodConverter();
        converter.setAeConverter(new AdverseEventConverter());
    }
    public void testConvert() throws Exception {
        AdverseEventsInputMessage ctcAeim = createReportingPeriod(true);
        AdverseEventReportingPeriod rp = converter.convert(ctcAeim);
        assertEquals(2, rp.getAdverseEvents().size());
        assertEquals(0, DateUtils.compareDate(startDate, rp.getStartDate()));
        assertEquals(0, DateUtils.compareDate(endDate, rp.getEndDate()));
        assertEquals(5, rp.getCycleNumber().intValue());
        assertEquals("Treatment", rp.getEpoch().getName());
        assertEquals("other tac", rp.getTreatmentAssignmentDescription());
        assertNull(rp.getTreatmentAssignment());
        assertEquals(0, DateUtils.compareDate(rp.getAdverseEvents().get(0).getStartDate(), startDate));
        assertEquals(0, DateUtils.compareDate(rp.getAdverseEvents().get(0).getEndDate(), endDate));
        assertEquals(0, DateUtils.compareDate(rp.getAdverseEvents().get(1).getStartDate(), startDate));
        assertEquals(0, DateUtils.compareDate(rp.getAdverseEvents().get(1).getEndDate(), endDate));
        assertEquals("111", rp.getStudy().getFundingSponsorIdentifierValue());
        assertEquals("111", rp.getAssignment().getStudySubjectIdentifier());

    }

    public AdverseEventsInputMessage createReportingPeriod(boolean useCTC) throws Exception {
        AdverseEventsInputMessage aeim = new AdverseEventsInputMessage();
        AdverseEvents adverseEvents = new AdverseEvents();
        adverseEvents.setAdverseEvent(new ArrayList<AdverseEventType>());
        aeim.setAdverseEvents(adverseEvents);
        aeim.setCriteria(new Criteria());

        GregorianCalendar cStart = (GregorianCalendar)GregorianCalendar.getInstance();
        cStart.setTime(startDate);
        XMLGregorianCalendar xmlStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cStart);

        GregorianCalendar cEnd = (GregorianCalendar)GregorianCalendar.getInstance();
        cEnd.setTime(endDate);
        XMLGregorianCalendar xmlEndDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cEnd);


        aeim.getCriteria().setCourse(new CourseType());
        aeim.getCriteria().setStudyIdentifier("111");
        aeim.getCriteria().setStudySubjectIdentifier("111");
        CourseType course = aeim.getCriteria().getCourse();
        course.setStartDateOfFirstCourse(xmlStartDate);
        course.setStartDateOfThisCourse(xmlStartDate);
        course.setEndDateOfThisCourse(xmlEndDate);
        course.setCycleNumber(new BigInteger("5"));
        course.setOtherTreatmentAssignmentDescription("other tac");
        course.setTreatmentType("Treatment");

        if(useCTC){
            aeim.getAdverseEvents().getAdverseEvent().add(createCtcAE(xmlStartDate, xmlEndDate, HospitalizationType.YES, false));
            aeim.getAdverseEvents().getAdverseEvent().add(createCtcAE(xmlStartDate, xmlEndDate, HospitalizationType.YES, true));
        }
        return aeim;
    }

    public AdverseEventType createCtcAE(XMLGregorianCalendar xmlStartDate, XMLGregorianCalendar xmlEndDate, HospitalizationType hospitalization, boolean useOtherMeddra){

        AdverseEventType ae1 = new AdverseEventType();
        ae1.setExternalId("x-33");
        ae1.setExpected(true);
        ae1.setAttributionSummary(AttributionType.DEFINITE);
        ae1.setDateFirstLearned(xmlStartDate);
        ae1.setStartDate(xmlStartDate);
        ae1.setEndDate(xmlEndDate);
        ae1.setGrade(1);
        ae1.setHospitalization(hospitalization);
        ae1.setVerbatim("my verbatim");
        ae1.setEventLocation("my location");
        ae1.setIncreasedRisk(true);
        ae1.setEventApproximateTime(new TimeType());
        ae1.getEventApproximateTime().setAmpm(AMPM.AM);
        ae1.getEventApproximateTime().setHour(BigInteger.TEN);
        ae1.getEventApproximateTime().setMinute(BigInteger.ONE);

        ae1.setAdverseEventCtepTerm(new AdverseEventCtepTermType());
        ae1.getAdverseEventCtepTerm().setCtepCode("111");
        if(!useOtherMeddra) {
            ae1.getAdverseEventCtepTerm().setOtherSpecify("my other specify");
        }else{
            ae1.getAdverseEventCtepTerm().setOtherMeddra(new AdverseEventMeddraLowLevelTermType());
            ae1.getAdverseEventCtepTerm().getOtherMeddra().setMeddraCode("111");
        }


        return ae1;
    }
}
