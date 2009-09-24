package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class StudySitesQueryTest extends TestCase {

    public void testOne() {
        StudySitesQuery query = new StudySitesQuery("SELECT o2.nciInstituteCode FROM StudyCoordinatingCenter so");
        query.joinStudies();
        query.joinStudyOrganizations();
        query.joinOrganizations();
        query.filterBySuperOrganizationCode(new ArrayList(Arrays.asList("CALGB")));
    }
}