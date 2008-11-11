package gov.nih.nci.cabig.caaers.dao.query.ajax;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class StudyAjaxableDomainObjectQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        AbstractAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();

        assertEquals("wrong parsing for constructor",
                "Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator from Study study left join study.identifiers as identifier order by study.shortTitle", query
                        .getQueryString());

    }

   

}