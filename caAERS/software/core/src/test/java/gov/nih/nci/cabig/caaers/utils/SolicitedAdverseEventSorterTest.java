/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.utils.SolicitedAdverseEventSorter;


import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import junit.framework.TestCase;

import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Author: nikhil
 * Date: 1/27/11
 * Time: 4:01 PM
 */
public class SolicitedAdverseEventSorterTest extends TestCase {

    public void testSolicitedSorting() {

        SolicitedAdverseEvent sae1 = new SolicitedAdverseEvent();
        CtcTerm ct1 = new CtcTerm();
        ct1.setTerm("nikhil");
        sae1.setCtcterm(ct1);

        SolicitedAdverseEvent sae2 = new SolicitedAdverseEvent();
        CtcTerm ct2 = new CtcTerm();
        ct2.setTerm("pingili");
        ct2.setOtherRequired(true);
        sae2.setCtcterm(ct2);

        LowLevelTerm l1 = new LowLevelTerm();
        l1.setMeddraTerm("B");
        sae2.setLowLevelTerm(l1);

        SolicitedAdverseEvent sae3 = new SolicitedAdverseEvent();

        LowLevelTerm l2 = new LowLevelTerm();
        l2.setMeddraTerm("A");
        sae3.setLowLevelTerm(l2);

        List<SolicitedAdverseEvent> list = new ArrayList<SolicitedAdverseEvent>();
        list.add(sae1);
        list.add(sae2);
        list.add(sae3);
        Collections.sort(list, new SolicitedAdverseEventSorter());

        assertEquals(3, list.size());
        assertEquals("A", ((SolicitedAdverseEvent) (list.get(0))).getLowLevelTerm().getMeddraTerm());
        assertEquals("nikhil", ((SolicitedAdverseEvent) (list.get(1))).getCtcterm().getTerm());

    }


}
