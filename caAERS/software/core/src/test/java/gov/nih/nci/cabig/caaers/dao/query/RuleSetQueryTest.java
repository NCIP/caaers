package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class RuleSetQueryTest extends TestCase {
    RuleSetQuery q = new RuleSetQuery();
    public void testFilterByNCICode() throws Exception {
       q.filterByNCICode("z");
       assertEquals("select r from RuleSet r join r.organization as o WHERE o.nciInstituteCode = :nciCode", q.getQueryString());
    }

    public void testFilterByOrganizationId() throws Exception {
        q.filterByOrganizationId(1);
        assertEquals("select r from RuleSet r join r.organization as o WHERE o.id = :orgId",q.getQueryString());
    }

    public void testFilterByStudyId() throws Exception {
        q.filterByStudyId(2);
        assertEquals("select r from RuleSet r join r.study as s WHERE s.id = :sId", q.getQueryString());
    }

    public void testFilterByRuleType() throws Exception {
        q.filterByRuleType(RuleType.FIELD_LEVEL_RULES);
        assertEquals("select r from RuleSet r WHERE r.ruleTypeName = :rType", q.getQueryString());
    }

    public void testFilterByRuleLevel() throws Exception {
        q.filterByRuleLevel(RuleLevel.Institution);
        assertEquals("select r from RuleSet r WHERE r.ruleLevelName = :l", q.getQueryString());
    }

    public void testFilterByStatusAndBindURI() throws Exception {
        q.filterByStatus(RuleSet.STATUS_ENABLED);
        q.filterByRuleBindURI("x");
        assertEquals("select r from RuleSet r WHERE r.status = :st AND r.ruleBindURI = :uri" , q.getQueryString());
    }
}
