/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class AeMergeDTOTest extends TestCase {
    public void testSeralize() throws Exception {
        AeMergeDTO d = Fixtures.createAeMergeDTO();
        String s = AeMergeDTO.seralize(d);

        AeMergeDTO x = AeMergeDTO.deseralize(s);

        assertEquals(new Integer(11), x.getExternalAeId());
        assertEquals(new Integer(15), x.getInteralAeId());
        assertEquals(x.getMerges()[6], 1);
        assertEquals(x.getMerges()[0], 1);
        assertEquals(x.getMerges()[1], 2);
    }

}
