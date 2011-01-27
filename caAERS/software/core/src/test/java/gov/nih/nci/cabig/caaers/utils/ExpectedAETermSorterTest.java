package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import junit.framework.TestCase;
import junit.framework.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 1/25/11
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExpectedAETermSorterTest extends TestCase {


    public void testExpectedCTCAESorting() {

        ExpectedAECtcTerm ctcT1 = new ExpectedAECtcTerm();
        CtcTerm ct1 = new CtcTerm();
        ct1.setTerm("nikhil");
        ctcT1.setCtcTerm(ct1);


        ExpectedAECtcTerm ctcT2 = new ExpectedAECtcTerm();
        CtcTerm ct2 = new CtcTerm();
        ct2.setTerm("pingili");
        ct2.setOtherRequired(true);
        ctcT2.setCtcTerm(ct2);

        LowLevelTerm l = new LowLevelTerm();
        l.setMeddraTerm("B");
        ctcT2.setOtherMeddraTerm(l);


        ExpectedAEMeddraLowLevelTerm meddraT1 = new ExpectedAEMeddraLowLevelTerm();
        LowLevelTerm l1 = new LowLevelTerm();
        l1.setMeddraTerm("pingiliA");
        meddraT1.setLowLevelTerm(l1);

        List<AbstractExpectedAE> list = new ArrayList<AbstractExpectedAE>();
        list.add(ctcT1);
        list.add(ctcT2);
        list.add(meddraT1);

        Collections.sort(list, new ExpectedAETermSorter());

        assertEquals(3, list.size());
        assertEquals("nikhil", ((ExpectedAECtcTerm) (list.get(0))).getTerm().getTerm());
        assertEquals("pingiliA", ((ExpectedAEMeddraLowLevelTerm) (list.get(1))).getTerm().getMeddraTerm());
        assertEquals("pingiliB", ((ExpectedAECtcTerm) (list.get(2))).getTerm().getTerm() + ((ExpectedAECtcTerm) (list.get(2))).getOtherMeddraTerm().getMeddraTerm());
    }

 /**
     *
     * test at least Two CTC terms with other required = true and both with differrent other meddra term
     * */
    public void testB() {
        ExpectedAECtcTerm ctcT1 = new ExpectedAECtcTerm();
        CtcTerm ct1 = new CtcTerm();
        ct1.setOtherRequired(true);
        ct1.setTerm("nikhil");
        ctcT1.setCtcTerm(ct1);

        LowLevelTerm l1 = new LowLevelTerm();
        l1.setMeddraTerm("A");
        ctcT1.setOtherMeddraTerm(l1);

        ExpectedAECtcTerm ctcT2 = new ExpectedAECtcTerm();
        CtcTerm ct2 = new CtcTerm();
        ct2.setTerm("nikhil");
        ct2.setOtherRequired(true);
        ctcT2.setCtcTerm(ct2);

        LowLevelTerm l2 = new LowLevelTerm();
        l2.setMeddraTerm("B");
        ctcT2.setOtherMeddraTerm(l2);

        ExpectedAECtcTerm ctcT3 = new ExpectedAECtcTerm();
        CtcTerm ct3 = new CtcTerm();
        ct3.setTerm("nikhil");
        ct3.setOtherRequired(true);
        ctcT3.setCtcTerm(ct3);

        LowLevelTerm l3 = new LowLevelTerm();
        l3.setMeddraTerm("C");
        ctcT3.setOtherMeddraTerm(l3);

        List<AbstractExpectedAE> list = new ArrayList<AbstractExpectedAE>();
        list.add(ctcT3);
        list.add(ctcT2);
        list.add(ctcT1);
        Collections.sort(list, new ExpectedAETermSorter());

         assertEquals("nikhilC", ((ExpectedAECtcTerm) (list.get(2))).getTerm().getTerm() + ((ExpectedAECtcTerm) (list.get(2))).getOtherMeddraTerm().getMeddraTerm());
        assertEquals("nikhilB", ((ExpectedAECtcTerm) (list.get(1))).getTerm().getTerm() + ((ExpectedAECtcTerm) (list.get(1))).getOtherMeddraTerm().getMeddraTerm());





    }

 /**
     *
     *  test at least Two Meddra terms
     * */
    public void testC() {


        ExpectedAEMeddraLowLevelTerm meddraT1 = new ExpectedAEMeddraLowLevelTerm();
        LowLevelTerm l1 = new LowLevelTerm();
        l1.setMeddraTerm("pingiliA");
        meddraT1.setLowLevelTerm(l1);

        ExpectedAEMeddraLowLevelTerm meddraT2 = new ExpectedAEMeddraLowLevelTerm();
        LowLevelTerm l2 = new LowLevelTerm();
        l2.setMeddraTerm("pingiliB");
        meddraT2.setLowLevelTerm(l2);

        List<AbstractExpectedAE> list = new ArrayList<AbstractExpectedAE>();
        list.add(meddraT2);
        list.add(meddraT1);
        Collections.sort(list, new ExpectedAETermSorter());

        assertEquals("pingiliA", ((ExpectedAEMeddraLowLevelTerm) (list.get(0))).getTerm().getMeddraTerm());
        assertEquals("pingiliB", ((ExpectedAEMeddraLowLevelTerm) (list.get(1))).getTerm().getMeddraTerm());

    }

/*
    ExpectedAEMeddraLowLevelTerm e1 = new ExpectedAEMeddraLowLevelTerm();
    LowLevelTerm l1 = new LowLevelTerm();
        l1.setMeddraTerm("pingili");
        e1.setLowLevelTerm(l1);
*/


}
