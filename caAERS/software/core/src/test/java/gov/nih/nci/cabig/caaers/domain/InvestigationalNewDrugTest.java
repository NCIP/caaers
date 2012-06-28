package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

public class InvestigationalNewDrugTest extends TestCase {

    public void testGetterAndSetter() {
        InvestigationalNewDrug ind = new InvestigationalNewDrug();


        ind.setStrINDNo("544");
        assertEquals("544", ind.getStrINDNo());

        ind.setStrINDNo("-44");
        assertEquals("-44", ind.getStrINDNo());


    }

    public void testIsHeldByNCI(){
        InvestigationalNewDrug ind = new InvestigationalNewDrug();
        assertFalse(ind.isHeldByNCI());
        ind.setINDHolder(Fixtures.createOrganizationINDHolder(Fixtures.createOrganization("x")));
        assertFalse(ind.isHeldByNCI());
        ind.setINDHolder(Fixtures.createOrganizationINDHolder(Fixtures.createOrganization("CTEP", "CTEP")));
        assertTrue(ind.isHeldByNCI());
    }

}