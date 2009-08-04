package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

/**
 *
 * @author Ion C. Olaru
 *
 */
public class StudyInvestigatorTest extends TestCase {

    protected StudyInvestigator s1;
    protected StudyInvestigator s2;

	protected void setUp() throws Exception {
		super.setUp();

        s1 = new StudyInvestigator();
        s1.setStartDate(DateUtils.yesterday());
        s1.setEndDate(DateUtils.tomorrow());

        s2 = new StudyInvestigator();
        s2.setStartDate(DateUtils.yesterday());
        s2.setEndDate(DateUtils.tomorrow());

        SiteInvestigator si = new SiteInvestigator();
        si.setStartDate(DateUtils.yesterday());
        si.setEndDate(DateUtils.yesterday());

        s1.setSiteInvestigator(si);
        s2.setSiteInvestigator(si);
	}

	public void testEqualsObject() {
	}

    public void testDeactivate() {

    }


}