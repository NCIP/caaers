package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

public class InvestigationalNewDrugTest extends TestCase {

    public void testGetterAndSetter() {
        InvestigationalNewDrug ind = new InvestigationalNewDrug();
        ind.setStrINDNo(InvestigationalNewDrug.STRING_CTEP_IND);
        assertEquals(InvestigationalNewDrug.STRING_CTEP_IND, ind.getStrINDNo());

        ind.setStrINDNo("544");
        assertEquals("544", ind.getStrINDNo());

        ind.setStrINDNo("-44");
        assertEquals("-44", ind.getStrINDNo());

        ind.setStrINDNo(InvestigationalNewDrug.STRING_DCP_IND);
        assertEquals(InvestigationalNewDrug.STRING_DCP_IND, ind.getStrINDNo());
    }

}