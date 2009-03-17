package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class StudyTest extends TestCase {
	Study study;
	protected void setUp() throws Exception {
		super.setUp();
		study = new Study();
		
	}
	
	/**
	 * This method tests {@link Study#isExpectedAdverseEventTerm(gov.nih.nci.cabig.ctms.domain.DomainObject)}
	 */
	public void testIsExpectedAdverseEventTerm() {
		ExpectedAECtcTerm eTerm1 = Fixtures.createExpectedAECtcTerm(1, "abcd", "abcd");
		ExpectedAECtcTerm eTerm2 = Fixtures.createExpectedAECtcTerm(2, "efg", "efg");
		study.addExpectedAECtcTerm(eTerm1);
		study.addExpectedAECtcTerm(eTerm2);
		
		CtcTerm ctcTerm = Fixtures.createCtcTerm("efg", "efg");
		ctcTerm.setId(2);
		
		assertTrue(study.isExpectedAdverseEventTerm(ctcTerm));
		
		ctcTerm.setId(3);
		assertFalse(study.isExpectedAdverseEventTerm(ctcTerm));
	}
	/**
	 * This method tests {@link Study#isExpectedAdverseEventTerm(gov.nih.nci.cabig.ctms.domain.DomainObject)}
	 */
	public void testIsExpectedAdverseEventTermPassingMedDRATerm() {
		ExpectedAECtcTerm eTerm1 = Fixtures.createExpectedAECtcTerm(1, "abcd", "abcd");
		ExpectedAECtcTerm eTerm2 = Fixtures.createExpectedAECtcTerm(2, "efg", "efg");
		study.addExpectedAECtcTerm(eTerm1);
		study.addExpectedAECtcTerm(eTerm2);
		
		LowLevelTerm llt = new LowLevelTerm();
		
		assertFalse(study.isExpectedAdverseEventTerm(llt));
	}
	/**
	 * This method tests {@link Study#isExpectedAdverseEventTerm(gov.nih.nci.cabig.ctms.domain.DomainObject)}
	 */
	public void testIsExpectedAdverseEventTermPassingNullTerm() {
		ExpectedAECtcTerm eTerm1 = Fixtures.createExpectedAECtcTerm(1, "abcd", "abcd");
		ExpectedAECtcTerm eTerm2 = Fixtures.createExpectedAECtcTerm(2, "efg", "efg");
		study.addExpectedAECtcTerm(eTerm1);
		study.addExpectedAECtcTerm(eTerm2);
		
		assertFalse(study.isExpectedAdverseEventTerm(null));
	}
}
