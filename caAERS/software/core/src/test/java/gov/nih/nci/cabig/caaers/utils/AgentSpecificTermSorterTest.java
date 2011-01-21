package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.AgentSpecificCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificMeddraLowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 1/20/11
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class AgentSpecificTermSorterTest extends TestCase {

    public void testSortASAEL() {

        AgentSpecificCtcTerm c1 = new AgentSpecificCtcTerm();
        CtcTerm ct1 = new CtcTerm();
        ct1.setTerm("nikhil");
        c1.setTerm(ct1);

        AgentSpecificCtcTerm c2 = new AgentSpecificCtcTerm();
        CtcTerm ct2 = new CtcTerm();
        ct2.setTerm("reddy");
        c2.setTerm(ct2);


        AgentSpecificMeddraLowLevelTerm asll = new AgentSpecificMeddraLowLevelTerm();
        LowLevelTerm l1 = new LowLevelTerm();
        l1.setMeddraTerm("pingili");
        asll.setTerm(l1);

        List<AgentSpecificTerm> ctcList = new ArrayList<AgentSpecificTerm>();
        ctcList.add(asll);
        ctcList.add(c2);
        ctcList.add(c1) ;
        Collections.sort(ctcList, new AgentSpecificTermSorter());

        assertEquals(3, ctcList.size());
        assertEquals("gov.nih.nci.cabig.caaers.domain.AgentSpecificCtcTerm", ctcList.get(0).getClass().getName());
        assertEquals("nikhil", ((AgentSpecificCtcTerm)(ctcList.get(0))).getTerm().getTerm());
        assertEquals("pingili", ((AgentSpecificMeddraLowLevelTerm)(ctcList.get(1))).getTerm().getMeddraTerm());
        assertEquals("reddy", ((AgentSpecificCtcTerm)(ctcList.get(2))).getTerm().getTerm());
    }



}

