/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.util.HashMap;

/**
 * DefaultObjectPrivilegeGenerator Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/23/2010</pre>
 * 
 */
public class DefaultObjectPrivilegeGeneratorTest extends AbstractTestCase {
    DefaultObjectPrivilegeGenerator gen;
    public void testResolve(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("/x","y");
        gen = new DefaultObjectPrivilegeGenerator();
        gen.setObjectPrivilegeMap(map);
        assertEquals("y", gen.resolve("/x"));
        assertNull(gen.resolve("xksksk"));

    }

}
