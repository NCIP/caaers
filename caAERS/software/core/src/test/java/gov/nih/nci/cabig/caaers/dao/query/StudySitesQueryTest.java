package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class StudySitesQueryTest extends TestCase {

    public void testOne() {
        StudySitesQuery q = new StudySitesQuery();
        q.filterByOrganizationId(5);
        assertEquals("SELECT ss FROM StudySite ss left join ss.study.identifiers as identifier join ss.organization AS o WHERE o.id = :orgId", q.getQueryString());
    }
}