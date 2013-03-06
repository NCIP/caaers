/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class StudySitesQueryTest extends TestCase {

    public void testFilterByOrgId() {
        StudySitesQuery q = new StudySitesQuery();
        q.filterByOrganizationId(5);
        assertEquals("SELECT distinct ss FROM StudySite ss left join ss.study.identifiers as identifier join ss.organization AS o WHERE o.id = :orgId", q.getQueryString());
    }
}
