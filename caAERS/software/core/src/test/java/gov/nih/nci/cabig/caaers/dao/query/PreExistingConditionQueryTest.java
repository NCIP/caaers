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
public class PreExistingConditionQueryTest extends TestCase {

    public void testFilterByMeddraCode() throws Exception {
        PreExistingConditionQuery q = new PreExistingConditionQuery();
        q.filterByMeddraCode("99");
        assertEquals("select p from PreExistingCondition p WHERE p.meddraLltCode = :mc", q.getQueryString());
    }

    public void testFilterByRetiredIndicator() throws Exception {
        PreExistingConditionQuery q = new PreExistingConditionQuery();
        q.filterByMeddraCode("99");
        q.filterByRetiredStatus(true);
        assertEquals("select p from PreExistingCondition p WHERE p.meddraLltCode = :mc AND p.retiredIndicator = :value", q.getQueryString());
    }
}
