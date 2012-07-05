package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class StudySitesQueryTest extends TestCase {

    public void testFilterByOrgId() {
        StudySitesQuery q = new StudySitesQuery();
        q.filterByOrganizationId(5);
        assertEquals("SELECT distinct ss FROM StudySite ss join ss.study AS study left join study.identifiers as identifier join ss.organization AS o WHERE ss.retiredIndicator = :retiredIndicator AND o.id = :orgId", q.getQueryString());
    }
}