package gov.nih.nci.cabig.caaers.web.validation.validator;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;

/**
 * This class tests the AdverseEventReportingPeriodValidator class
 * @author Sameer Sawant
 * @author Biju Joseph
 */
public class AdverseEventReportingPeriodValidatorTest extends AbstractTestCase {
	private static final Log log = LogFactory.getLog(AdverseEventReportingPeriodValidator.class);
	
	private AdverseEventReportingPeriodValidator adverseEventReportingPeriodValidator;
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	private Errors errors;
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		adverseEventReportingPeriodValidator = new AdverseEventReportingPeriodValidator();
		adverseEventReportingPeriod = new AdverseEventReportingPeriod();
		errors = new BindException(adverseEventReportingPeriod, "adverseEventReportingPeriod");
		setupAdverseEventReportingPeriod();
	}
	
	private void setupAdverseEventReportingPeriod(){
		adverseEventReportingPeriod = Fixtures.createReportingPeriod();
		adverseEventReportingPeriod.setStartDate(new Timestamp(100));
		adverseEventReportingPeriod.setEndDate(new Timestamp(200));
		adverseEventReportingPeriod.addAdverseEvent(new AdverseEvent());
		adverseEventReportingPeriod.addAdverseEvent(new AdverseEvent());
		
		AdverseEventCtcTerm aeCtcTerm = new AdverseEventCtcTerm();
		aeCtcTerm.setCtcTerm(Fixtures.createCtcTerm("testTerm", "testCode"));
		adverseEventReportingPeriod.getAdverseEvents().get(0).setAdverseEventCtcTerm(aeCtcTerm);
		aeCtcTerm.setCtcTerm(Fixtures.createCtcTerm("testTerm2", "testCode2"));
		adverseEventReportingPeriod.getAdverseEvents().get(1).setAdverseEventCtcTerm(aeCtcTerm);
	}

    //2nd observed ae not graded
	public void testGradeNotNull(){
		adverseEventReportingPeriod.getAdverseEvents().get(0).setGrade(Grade.NORMAL);
		adverseEventReportingPeriodValidator.validate(adverseEventReportingPeriod, errors);
		assertTrue(errors.hasErrors());
		assertEquals("Grade Not Null validation not working.", 1, errors.getErrorCount());
	}

    // making sure that solicited can have grade not evaluated.
    public void testGradeSolicitedHavingNotEvaluated(){
		adverseEventReportingPeriod.getAdverseEvents().get(0).setGrade(Grade.NOT_EVALUATED);
        adverseEventReportingPeriod.getAdverseEvents().get(0).setSolicited(true);
        adverseEventReportingPeriod.getAdverseEvents().get(1).setGrade(Grade.NORMAL);
        adverseEventReportingPeriod.getAdverseEvents().get(1).setSolicited(true);
		adverseEventReportingPeriodValidator.validate(adverseEventReportingPeriod, errors);
		assertFalse(errors.hasErrors());
	}


    // making sure that solicited not evaluated
    public void testGradeSolicitedNotGraded(){
		adverseEventReportingPeriod.getAdverseEvents().get(0).setGrade(null);
        adverseEventReportingPeriod.getAdverseEvents().get(0).setSolicited(true);
        adverseEventReportingPeriod.getAdverseEvents().get(1).setGrade(Grade.NORMAL);
        adverseEventReportingPeriod.getAdverseEvents().get(1).setSolicited(true);
		adverseEventReportingPeriodValidator.validate(adverseEventReportingPeriod, errors);
		assertTrue(errors.hasErrors());
	}


    // making sure that retired ae can be not graded
    public void testNotGradedRetiredAE(){
		adverseEventReportingPeriod.getAdverseEvents().get(0).setGrade(null);
        adverseEventReportingPeriod.getAdverseEvents().get(0).retire();

        adverseEventReportingPeriod.getAdverseEvents().get(1).setGrade(Grade.NORMAL);
        adverseEventReportingPeriod.getAdverseEvents().get(1).setSolicited(true);
		adverseEventReportingPeriodValidator.validate(adverseEventReportingPeriod, errors);
		assertFalse(errors.hasErrors());
	}
	
	public void testValidAttribution(){
		adverseEventReportingPeriod.getAdverseEvents().get(0).setGrade(Grade.MILD);
		adverseEventReportingPeriod.getAdverseEvents().get(1).setGrade(Grade.MILD);
		adverseEventReportingPeriodValidator.validate(adverseEventReportingPeriod, errors);
		assertTrue(errors.hasErrors());
		assertEquals("Attribution needed when grade >= 1 validation not working.", 1, errors.getErrorCount());
	}
	
	public void testValidHospitalization(){
		adverseEventReportingPeriod.getAdverseEvents().get(0).setAttributionSummary(Attribution.POSSIBLE);
		adverseEventReportingPeriod.getAdverseEvents().get(1).setAttributionSummary(Attribution.POSSIBLE);
		adverseEventReportingPeriod.getAdverseEvents().get(0).setGrade(Grade.LIFE_THREATENING);
		adverseEventReportingPeriod.getAdverseEvents().get(1).setGrade(Grade.LIFE_THREATENING);
		adverseEventReportingPeriodValidator.validate(adverseEventReportingPeriod, errors);
		assertTrue(errors.hasErrors());
		assertEquals("Hospitalization needed when Grade >= 2 validation not working.", 1, errors.getErrorCount());
	}
	
	public void testEndDateNotNull(){
		adverseEventReportingPeriod.getAdverseEvents().get(0).setGrade(Grade.NORMAL);
		adverseEventReportingPeriod.getAdverseEvents().get(1).setGrade(Grade.NORMAL);
		adverseEventReportingPeriod.setEndDate(null);
		adverseEventReportingPeriodValidator.validate(adverseEventReportingPeriod, errors);
		assertTrue(errors.hasErrors());
		assertEquals("End date cant be null validation not working", 1, errors.getErrorCount());
	}
}