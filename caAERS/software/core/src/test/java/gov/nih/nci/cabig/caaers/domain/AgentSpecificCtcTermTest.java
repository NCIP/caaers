/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class AgentSpecificCtcTermTest extends TestCase {


    public void testIsOfSameTerm() throws Exception {
        Agent a = Fixtures.createAgent("test");
        CtcTerm t = Fixtures.createCtcTerm("x","x");

        AgentSpecificTerm ast1 = Fixtures.createAgentSpecificCtcTerm(a, t);
        assertTrue(ast1.isOfSameTerm("x", null, null, null, null));
    }
}
