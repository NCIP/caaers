package gov.nih.nci.cabig.caaers.domain.comparator;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventCompratorTest extends TestCase {
	
	AdverseEventComprator comparator;
	protected void setUp() throws Exception {
		super.setUp();
		comparator = new AdverseEventComprator();
	}

	public void testCompareNullCheck() {
		AdverseEvent ae1 = null;
		AdverseEvent ae2 = null;
		assertEquals(0, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_FirstAENull() {
		AdverseEvent ae1 = null;
		AdverseEvent ae2 = new AdverseEvent();
		assertEquals(1, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_SecondAENull() {
		AdverseEvent ae2 = null;
		AdverseEvent ae1 = new AdverseEvent();
		assertEquals(-1, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_FirstAEGraded() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setGrade(Grade.MILD);
		assertEquals(-1, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_FirstAEGradedAndRequiresReporting() {
		AdverseEvent ae2 = new AdverseEvent();
		
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setRequiresReporting(true);
		ae1.setGrade(Grade.MILD);
		assertEquals(-1, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_FirstAEGradedAndBothRequiresReporting() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setRequiresReporting(true);
		ae2.setRequiresReporting(true);
		ae1.setGrade(Grade.MILD);
		assertEquals(-1, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_FirstAEGradedAndSecondRequiresReporting() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae2.setRequiresReporting(true);
		ae1.setGrade(Grade.MILD);
		assertEquals(1, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_SameReference() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae2.setRequiresReporting(true);
		ae1.setGrade(Grade.MILD);
		assertEquals(0, comparator.compare(ae1, ae1));
	}
	
	public void testCompare_FirstAEAttributedToStudy() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setAttributionSummary(Attribution.DEFINITE);
		assertEquals(-1, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_BothAttributedToStudy() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setAttributionSummary(Attribution.DEFINITE);
		ae2.setAttributionSummary(Attribution.DEFINITE);
		assertEquals(0, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_BothAttributedToStudy_SecondAE_Possible() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setAttributionSummary(Attribution.DEFINITE);
		ae2.setAttributionSummary(Attribution.POSSIBLE);
		assertEquals(-1, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_FirstOneExpected() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setExpected(true);
		assertEquals(-1, comparator.compare(ae1, ae2));
	}

	public void testCompare_BothOneExpected() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setExpected(true);
		ae2.setExpected(true);
		assertEquals(0, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_BothHospitalized() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setHospitalization(Hospitalization.YES);
		ae2.setHospitalization(Hospitalization.NO);
		assertEquals(-1, comparator.compare(ae1, ae2));
	}
	
	public void testCompare_BothHospitalized_ButSecondToNONE() {
		AdverseEvent ae2 = new AdverseEvent();
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setHospitalization(Hospitalization.YES);
		ae2.setHospitalization(Hospitalization.NONE);
		assertEquals(1, comparator.compare(ae1, ae2));
	}
	
}
