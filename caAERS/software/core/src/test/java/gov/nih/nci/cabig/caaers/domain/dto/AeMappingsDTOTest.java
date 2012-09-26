package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 * */
public class AeMappingsDTOTest extends TestCase {
    public void testSeralize() throws Exception {
        AeMappingsDTO d = new AeMappingsDTO();
        String s = AeMappingsDTO.seralize(d);
        AeMappingsDTO x = AeMappingsDTO.deseralize(s);
        System.out.println(s);
        assertTrue(x.getRejectedExternalAeIds() == null || x.getRejectedExternalAeIds().length == 0);
        assertTrue(x.getRejectedInternalAeIds() == null || x.getRejectedInternalAeIds().length == 0);
        assertTrue(x.getRelations() == null || x.getRelations().length == 0);

        AeMergeDTO m = Fixtures.createAeMergeDTO();
        m.setExternalAeId(111);
        m.setInteralAeId(222);

        AeMergeDTO n = Fixtures.createAeMergeDTO();
        n.setExternalAeId(33);
        n.setInteralAeId(32);
        n.getMerges()[0] = 1;
        n.getMerges()[2] = 1;
        n.getMerges()[3] = 1;
        d.setRejectedExternalAeIds(new int[]{11,13});
        d.setRelations(new AeMergeDTO[]{m, n});

        s = AeMappingsDTO.seralize(d);
        System.out.println(s);
        AeMappingsDTO y = AeMappingsDTO.deseralize(s);

        assertTrue(y.getRejectedInternalAeIds() == null || y.getRejectedInternalAeIds().length == 0);
        assertEquals(11, y.getRejectedExternalAeIds()[0]);
        assertEquals(13, y.getRejectedExternalAeIds()[1]);
        
        assertEquals(m.getExternalAeId() , y.getRelations()[0].getExternalAeId());
        assertEquals(m.getInteralAeId() , y.getRelations()[0].getInteralAeId());  
        assertEquals(m.getMerges()[0], y.getRelations()[0].getMerges()[0]);
        assertEquals(m.getMerges()[1], y.getRelations()[0].getMerges()[1]);
        assertEquals(m.getMerges()[2], y.getRelations()[0].getMerges()[2]);
        assertEquals(m.getMerges()[3], y.getRelations()[0].getMerges()[3]);
        assertEquals(m.getMerges()[4], y.getRelations()[0].getMerges()[4]);
        assertEquals(m.getMerges()[5], y.getRelations()[0].getMerges()[5]);
        assertEquals(m.getMerges()[6], y.getRelations()[0].getMerges()[6]);

        assertEquals(n.getExternalAeId() , y.getRelations()[1].getExternalAeId());
        assertEquals(n.getInteralAeId() , y.getRelations()[1].getInteralAeId());
        assertEquals(n.getMerges()[0], y.getRelations()[1].getMerges()[0]);
        assertEquals(n.getMerges()[1], y.getRelations()[1].getMerges()[1]);
        assertEquals(n.getMerges()[2], y.getRelations()[1].getMerges()[2]);
        assertEquals(n.getMerges()[3], y.getRelations()[1].getMerges()[3]);
        assertEquals(n.getMerges()[4], y.getRelations()[1].getMerges()[4]);
        assertEquals(n.getMerges()[5], y.getRelations()[1].getMerges()[5]);
        assertEquals(n.getMerges()[6], y.getRelations()[1].getMerges()[6]);

    }
}
