/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 * @author Ion C. Olaru
 */
public class PriorTherapyQueryTest extends TestCase {

    public void testFilterByMeddraCode() throws Exception {
        PriorTherapyQuery q = new PriorTherapyQuery();
        q.filterByMeddraCode("99");
        assertEquals("select p from PriorTherapy p WHERE p.meddraCode = :mc", q.getQueryString());
    }

    public void testFilterByRetiredIndicator() throws Exception {
        PriorTherapyQuery q = new PriorTherapyQuery();
        q.filterByMeddraCode("99");
        q.filterByRetiredStatus(true);
        assertEquals("select p from PriorTherapy p WHERE p.meddraCode = :mc AND p.retiredIndicator = :value", q.getQueryString());
    }

}
