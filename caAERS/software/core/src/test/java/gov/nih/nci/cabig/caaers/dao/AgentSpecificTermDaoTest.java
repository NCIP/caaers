package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;

import java.util.List;

/**
 * @author Ion C. Olaru
 * 
 */
public class AgentSpecificTermDaoTest extends DaoTestCase<AgentSpecificTermDao> {

	public void testGetAll() throws Exception {
		List<AgentSpecificTerm> list = getDao().getAll();
		assertNotNull(list);
		assertEquals(6, list.size());
	}
    
	public void testGetByAgentId() throws Exception {
		List<AgentSpecificTerm> list = getDao().getByAgentID(1003);
		assertNotNull(list);
		assertEquals(5, list.size());
		assertEquals(1003, list.get(0).getAgent().getId().intValue() );

		list = getDao().getByAgentID(1002);
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(1002, list.get(0).getAgent().getId().intValue() );
	}

    public void testGetByAgentIdAndTerminology() {
        List<? extends AgentSpecificTerm> list = getDao().getCtcTermsByAgentID(1003);
        assertNotNull(list);
        assertEquals(4, list.size());

        list = getDao().getCtcTermsByAgentID(1002);
        assertNotNull(list);
        assertEquals(0, list.size());

        list = getDao().getMeddraTermsByAgentID(1003);
        assertNotNull(list);
        assertEquals(1, list.size());

        list = getDao().getMeddraTermsByAgentID(1002);
        assertNotNull(list);
        assertEquals(1, list.size());

    }

    public void testGetByAgentIdAndTerminologyVersion() {
        List<? extends AgentSpecificTerm> list = getDao().getCtcTermsByAgentID(1003, 3);
        assertNotNull(list);
        assertEquals(2, list.size());

        list = getDao().getCtcTermsByAgentID(1003, 4);
        assertNotNull(list);
        assertEquals(2, list.size());

        list = getDao().getCtcTermsByAgentID(1002, 3);
        assertNotNull(list);
        assertEquals(0, list.size());

        list = getDao().getMeddraTermsByAgentID(1003, 10);
        assertNotNull(list);
        assertEquals(1, list.size());

        list = getDao().getMeddraTermsByAgentID(1002, 10);
        assertNotNull(list);
        assertEquals(1, list.size());

        list = getDao().getMeddraTermsByAgentID(1002, 11);
        assertNotNull(list);
        assertEquals(0, list.size());

    }

    public void testGetCtcWithOtherSpecified() {
        AgentSpecificTerm a = getDao().getById(-4);
        System.out.println(a.getAgent().getId());
        System.out.println(a.getTerm().getId());
        System.out.println(a.isOtherRequired());
        if (a.isOtherRequired()) {
            AgentSpecificCtcTerm m = (AgentSpecificCtcTerm)a;
            System.out.println(m.getOtherMeddraTerm());
        }
    }

    public void testGetCtcTermByAgentAndTerm() {
        List<AgentSpecificCtcTerm> l = getDao().getCtcTerm(1003, 4013);
        assertNotNull(l);
        assertEquals(1, l.size());
        assertEquals(-4, l.get(0).getId().intValue());
        assertEquals(1003, l.get(0).getAgent().getId().intValue());
        assertEquals(4013, l.get(0).getTerm().getId().intValue());

        // meddra term
        l = getDao().getCtcTerm(1003, -11);
        assertEquals(0, l.size());
    }
}