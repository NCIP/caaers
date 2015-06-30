package gov.nih.nci.cabig.caaers.api.impl;

import static org.junit.Assert.*;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.SaveAndEvaluateAEsOutputMessage;

import org.junit.Test;

public class SaveAndProcessOutputTest {

	@Test
	public void testDefaultConstructor() {
		SaveAndProcessOutput test = new SaveAndProcessOutput();
		assertNull(test.getMsg());
		assertNull(test.getPeriod());
	}

	@Test
	public void testConstructor2() {
		SaveAndEvaluateAEsOutputMessage a = new SaveAndEvaluateAEsOutputMessage();
		AdverseEventReportingPeriod b = new AdverseEventReportingPeriod();
		SaveAndProcessOutput test = new SaveAndProcessOutput(a, b);
		
		assertSame("The Message should be the same as was passed in.", a, test.getMsg());
		assertSame("The Period should be the same as was passed in.", b, test.getPeriod());
	}

	@Test
	public void testGetSet() {
		SaveAndEvaluateAEsOutputMessage a = new SaveAndEvaluateAEsOutputMessage();
		AdverseEventReportingPeriod b = new AdverseEventReportingPeriod();
		SaveAndProcessOutput test = new SaveAndProcessOutput();
		assertNull(test.getMsg());
		assertNull(test.getPeriod());
		
		test.setMsg(a);
		test.setPeriod(b);
		
		assertSame("The Message should be the same as was passed in.", a, test.getMsg());
		assertSame("The Period should be the same as was passed in.", b, test.getPeriod());
	}
}
