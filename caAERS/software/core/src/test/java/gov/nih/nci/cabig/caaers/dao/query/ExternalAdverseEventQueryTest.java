package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.ExternalAEReviewStatus;
import junit.framework.TestCase;

import java.util.Date;

/**
 * @author Biju Joseph
 */
public class ExternalAdverseEventQueryTest extends TestCase {
    
    public void testFilterByStatus() throws Exception {
        ExternalAdverseEventQuery q = new ExternalAdverseEventQuery();
        q.filterByStatus(ExternalAEReviewStatus.PENDING);
        assertEquals("select eae from ExternalAdverseEvent eae WHERE eae.status = :st", q.getQueryString());

    }

    public void testFilterByCreatedOnAfter() throws Exception {
        ExternalAdverseEventQuery q = new ExternalAdverseEventQuery();
        q.filterByCreatedOnAfter(new Date());
        assertEquals("select eae from ExternalAdverseEvent eae WHERE eae.creationDate > :cdt", q.getQueryString());

    }
}
