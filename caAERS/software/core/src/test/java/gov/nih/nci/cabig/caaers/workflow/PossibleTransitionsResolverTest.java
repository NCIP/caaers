/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.workflow;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import org.easymock.EasyMock;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class PossibleTransitionsResolverTest extends AbstractTestCase {
    PossibleTransitionsResolver resolver;
    WorkflowConfig cfg;
    ProcessInstance p;
    public void setUp() throws Exception {
       resolver = new PossibleTransitionsResolver();
        cfg = registerMockFor(WorkflowConfig.class);
        p = registerMockFor(ProcessInstance.class);
    }

    public void testFetchNextTransitions() throws Exception {
        Token token = registerMockFor(Token.class);
        Node rootNode = registerMockFor(Node.class);

        Transition t1 = registerMockFor(Transition.class);
        Node t1ToNode = registerMockFor(Node.class);

        Transition lt1 = registerMockFor(Transition.class);
        Transition lt2 = registerMockFor(Transition.class);
        List<Transition> leavingTransitions = new ArrayList<Transition>();
        leavingTransitions.add(lt1);
        leavingTransitions.add(lt2);

        Node ltN1 = registerMockFor(Node.class);
        Node ltN2 = registerMockFor(Node.class);

        EasyMock.expect(t1ToNode.getName()).andReturn("N0").anyTimes();
        EasyMock.expect(ltN1.getName()).andReturn("N1").anyTimes();
        EasyMock.expect(ltN2.getName()).andReturn("N2").anyTimes();

        EasyMock.expect(lt1.getTo()).andReturn(ltN1).anyTimes();
        EasyMock.expect(lt2.getTo()).andReturn(ltN2).anyTimes();

        EasyMock.expect(cfg.isTaskActive("N1")).andReturn(true).anyTimes();
        EasyMock.expect(cfg.isTaskActive("N2")).andReturn(true).anyTimes();
        EasyMock.expect(cfg.isTaskActive("N0")).andReturn(true).anyTimes();



        EasyMock.expect(token.getNode()).andReturn(rootNode).anyTimes();
        EasyMock.expect(p.getRootToken()).andReturn(token).times(2);
        EasyMock.expect(rootNode.getDefaultLeavingTransition()).andReturn(t1).anyTimes();

        EasyMock.expect(t1.getTo()).andReturn(t1ToNode).anyTimes();


        EasyMock.expect(rootNode.getLeavingTransitions()).andReturn(leavingTransitions).anyTimes();

        replayMocks();
        List<Transition> transitions = resolver.fetchNextTransitions(cfg, p);
        assertFalse(transitions.isEmpty());
        assertEquals(3, transitions.size());
        assertEquals("N1", transitions.get(0).getTo().getName());
        assertEquals("N2", transitions.get(1).getTo().getName());
        assertEquals("N0", transitions.get(2).getTo().getName());

        verifyMocks();
    }

    public void testFetchNextTransitionsNoTransition() throws Exception {
        Token token = registerMockFor(Token.class);
        Node rootNode = registerMockFor(Node.class);

        Transition t1 = registerMockFor(Transition.class);
        EasyMock.expect(token.getNode()).andReturn(rootNode).anyTimes();
        EasyMock.expect(p.getRootToken()).andReturn(token).times(2);
        EasyMock.expect(rootNode.getDefaultLeavingTransition()).andReturn(null).anyTimes();
        
        replayMocks();
        List<Transition> transitions = resolver.fetchNextTransitions(cfg, p);
        assertTrue(transitions.isEmpty());
        verifyMocks();
    }


    public void testFetchNextTransitionsEmptyRootToken() throws Exception {
        EasyMock.expect(p.getRootToken()).andReturn(null);
        replayMocks();
        List<Transition> transitions = resolver.fetchNextTransitions(cfg, p);
        assertTrue(transitions.isEmpty());
        verifyMocks();
    }
}
