/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.api.impl.AdverseEventManagementServiceImpl;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.HospitalizationType;
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
 * @author Ramakrishna
 *
 */
public class AdverseEventConverterTest extends AbstractTestCase {
	protected CtcTermDao ctcTermDao = null;
	protected LowLevelTermDao lowLevelTermDao = null;
	protected MessageSource messageSource;
	
	AdverseEventConverter converter;
	
	protected void setUp() throws Exception {
		super.setUp();
		converter = new AdverseEventConverter();
		
		//create the base studyDto
		ctcTermDao = registerDaoMockFor(CtcTermDao.class);
		lowLevelTermDao = registerDaoMockFor(LowLevelTermDao.class);
		messageSource = registerMockFor(MessageSource.class);
		converter.setCtcTermDao(ctcTermDao);
		converter.setLowLevelTermDao(lowLevelTermDao);
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
		
		OutcomeType outcomeType = new OutcomeType();
		outcomeType.setOther("Vomiting");
		outcomeType.setOutComeEnumType(OutComeEnumType.OTHER_SERIOUS);
		List<OutcomeType> outcomes = new ArrayList<OutcomeType>();
		outcomes.add(outcomeType);
		dto.setOutcome(outcomes);
		
		Date date = DateUtils.parseDate("05/11/2011");
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);

		XMLGregorianCalendar endDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		dto.setEndDate(endDate);
		
		AdverseEvent advEvent = new AdverseEvent();
		
		AeTerminology terminology = new AeTerminology();
		Ctc ctc = new Ctc();
		ctc.setName("ctc");
		terminology.setCtcVersion(ctc);
		
		Date startDateOfFirstCourse = DateUtils.parseDate("05/11/2007");
		converter.convertAdverseEventDtoToAdverseEventDomain(dto, advEvent, terminology, startDateOfFirstCourse, AdverseEventManagementServiceImpl.CREATE);
		
		assertEquals(1,advEvent.getOutcomes().size());
		assertEquals("Vomiting",advEvent.getOutcomes().get(0).getOther());
	}
	
	public void testConvertAdverseEventDtoToAdverseEventDomain1() throws Exception{
		
		AdverseEventType dto = new AdverseEventType();
		
		OutcomeType outcomeType = new OutcomeType();
		outcomeType.setOther("Vomiting");
		outcomeType.setOutComeEnumType(OutComeEnumType.OTHER_SERIOUS);
		List<OutcomeType> outcomes = new ArrayList<OutcomeType>();
		outcomes.add(outcomeType);
		dto.setOutcome(outcomes);
		
		dto.setHospitalization(HospitalizationType.NO);
		dto.setAttributionSummary(AttributionType.PROBABLE);
		
		Date date0 = DateUtils.parseDate("04/27/2011");
		GregorianCalendar c0 = new GregorianCalendar();
		c0.setTime(date0);

		XMLGregorianCalendar startDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c0);
		
		
		dto.setStartDate(startDate);
		
		Date date1 = DateUtils.parseDate("05/11/2011");
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(date1);

		XMLGregorianCalendar endDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
		
		dto.setEndDate(endDate);
		
		AeTerminology terminology = new AeTerminology();
		Ctc ctc = new Ctc();
		ctc.setName("ctc");
		terminology.setCtcVersion(ctc);
		
		Date startDateOfFirstCourse = DateUtils.parseDate("05/11/2007");
		converter.convertAdverseEventDtoToAdverseEventDomain(dto, null, terminology, startDateOfFirstCourse, AdverseEventManagementServiceImpl.CREATE);
	}
	
}
