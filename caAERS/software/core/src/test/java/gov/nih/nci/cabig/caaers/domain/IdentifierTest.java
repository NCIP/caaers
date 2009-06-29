package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */

public class IdentifierTest extends CaaersTestCase {
    private Identifier identifier;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        identifier = new Identifier();
    }

    public void testIsPrimaryWhenIndicatorNull() throws Exception {
        identifier.setPrimaryIndicator(null);
        assertFalse(identifier.isPrimary());
    }

    public void testIsPrimaryWhenIndicatorFalse() throws Exception {
        identifier.setPrimaryIndicator(false);
        assertFalse(identifier.isPrimary());
    }

    public void testIsPrimaryWhenIndicatorTrue() throws Exception {
        identifier.setPrimaryIndicator(true);
        assertTrue(identifier.isPrimary());
    }
    
    
    public void testEquals(){
    	Identifier id1 = new OrganizationAssignedIdentifier();
    	id1.setType("SFC");
    	id1.setValue("SFC-001");
    	id1.setPrimaryIndicator(true);
    	Identifier id2 = new OrganizationAssignedIdentifier();
    	id2.setType("SFC");
    	id2.setValue("SFC-001");
    	id2.setPrimaryIndicator(false);
    	
    	assertTrue(id1.equals(id2));
    	
    	Identifier id3 = new OrganizationAssignedIdentifier();
    	id3.setType("SFC");
    	id3.setValue("SFC-001");
    	id3.setPrimaryIndicator(true);
    	Identifier id4 = new OrganizationAssignedIdentifier();
    	id4.setType("SFC");
    	id4.setValue("SFC-001");
    	id4.setPrimaryIndicator(true);
    	
    	assertTrue(id3.equals(id4));
    	
    }
    
    public void testNotEquals(){
    	Identifier id1 = new OrganizationAssignedIdentifier();
    	id1.setType("SFC");
    	id1.setValue("SFC-001");
    	id1.setPrimaryIndicator(true);
    	Identifier id2 = new OrganizationAssignedIdentifier();
    	id2.setType("SCC");
    	id2.setValue("SCC-001");
    	id2.setPrimaryIndicator(false);
    	
    	assertFalse(id1.equals(id2));
    }
    
    
}
