/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.OutComeEnumType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.OutcomeType;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.context.MessageSource;

/**
 * 
 * @author medaV
 *
 */
public class SAEEvaluationAdverseEventConverterTest extends AbstractTestCase {
	protected MessageSource messageSource;
	
	SAEEvaluationAdverseEventConverter converter;
	
	protected void setUp() throws Exception {
		super.setUp();
		converter = new SAEEvaluationAdverseEventConverter();
		
		messageSource = registerMockFor(MessageSource.class);
		converter.setMessageSource(messageSource);
	
	}
	
	public void testPopulateOutcomes() throws Exception{
		
		AdverseEventType dto = new AdverseEventType();
		
		OutcomeType outcomeType = new OutcomeType();
		outcomeType.setOther("Vomiting");
		outcomeType.setOutComeEnumType(OutComeEnumType.OTHER_SERIOUS);
		List<OutcomeType> outcomes = new ArrayList<OutcomeType>();
		outcomes.add(outcomeType);
		dto.setOutcome(outcomes);
		
		AdverseEvent advEvent = new AdverseEvent();
		converter.populateOutcomes(dto, advEvent);
		
		assertEquals(1,advEvent.getOutcomes().size());
		assertEquals("Vomiting",advEvent.getOutcomes().get(0).getOther());
	}
	
	public void testConvertAdverseEventDtoToAdverseEventDomain() throws Exception{
		
		AdverseEventType dto = new AdverseEventType();
		
		dto.setGrade(0);
		OutcomeType outcomeType = new OutcomeType();
		outcomeType.setOther("Vomiting");
		outcomeType.setOutComeEnumType(OutComeEnumType.OTHER_SERIOUS);
		List<OutcomeType> outcomes = new ArrayList<OutcomeType>();
		outcomes.add(outcomeType);
		dto.setOutcome(outcomes);
		
		Date date = new Date();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);

		XMLGregorianCalendar endDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		dto.setEndDate(endDate);

		// Set Date First Incident reported. 
		Date dateFirst = DateUtils.parseDate("12/21/2012");
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dateFirst);

		XMLGregorianCalendar dateFirstReported = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		dto.setDateFirstLearned(dateFirstReported);

		AeTerminology terminology = Fixtures.createAeTerminology(Term.CTC);
		
		AdverseEvent advEvent = converter.convertAdverseEventDtoToAdverseEventDomain(dto,terminology);
		
		assertEquals(1,advEvent.getOutcomes().size());
		assertEquals("Vomiting",advEvent.getOutcomes().get(0).getOther());
		assertEquals(0,advEvent.getGrade().getCode().intValue());
	}
	
	
	public void testGetOutcomeTypes() throws Exception{
		
		AdverseEvent advEvent = new AdverseEvent();
		
		Outcome outCome = new Outcome();
		outCome.setOutcomeType(gov.nih.nci.cabig.caaers.domain.OutcomeType.OTHER_SERIOUS);
		outCome.setOther("Vomiting");
		advEvent.addOutcome(outCome);
		
		List<OutcomeType> types = converter.getOutcomeTypes(advEvent);
		assertEquals(1,types.size());
	}
	
}
