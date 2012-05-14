package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Ion C. Olaru
 *         Date: 5/14/12 -12:24 PM
 */
public class AgentQueryTest extends TestCase {

    public void testDefault() throws Exception {
        AgentQuery q = new AgentQuery();
        assertEquals("SELECT a FROM Agent a", q.getQueryString());
    }

    public void testFilterByName() throws Exception {
        AgentQuery q = new AgentQuery();
        q.filterByName("agentName");
        assertEquals("SELECT a FROM Agent a WHERE lower(a.name) LIKE :name", q.getQueryString());
    }

    public void testFilterByNSC() throws Exception {
        AgentQuery q = new AgentQuery();
        q.filterByNSC("agentName");
        assertEquals("SELECT a FROM Agent a WHERE lower(a.nscNumber) LIKE :nsc", q.getQueryString());
    }

    public void testFilterByNameOrNSC() throws Exception {
        AgentQuery q = new AgentQuery();
        q.filterByNameOrNSC("agentName", "agentNsc");
        assertEquals("SELECT a FROM Agent a WHERE (lower(a.name) LIKE :name OR lower(a.nscNumber) LIKE :nsc)", q.getQueryString());
    }
}
