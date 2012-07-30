package gov.nih.nci.cabig.caaers.web;

import java.util.List;

import junit.framework.TestCase;

public class ListValuesTest extends TestCase {

	public void testGetStudySearchType() {
		List<ListValues> values = new ListValues().getStudySearchType();
		assertEquals(2, values.size());
	}

	public void testGetParticipantSearchType() {
		List<ListValues> values = new ListValues().getParticipantSearchType();
		assertEquals(3, values.size());
	}

	public void testGetParticipantGender() {
		List<ListValues> values = new ListValues().getParticipantGender();
		assertEquals(4, values.size());
	}

	public void testGetParticipantEthnicity() {
		List<ListValues> values = new ListValues().getParticipantEthnicity();
		assertEquals(5, values.size());
	}

	public void testGetParticipantRace() {
		List<ListValues> values = new ListValues().getParticipantRace();
		assertEquals(8, values.size());
	}

	public void testGetParticipantIdentifierType() {
		List<ListValues> values = new ListValues().getParticipantIdentifierType();
		assertEquals(2, values.size());
	}

}
