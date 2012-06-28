package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import org.easymock.classextension.EasyMock;
/**
 * 
 * @author Biju Joseph
 *
 */
public class StudyAgentTest extends AbstractTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testEqualsObject() {
		StudyAgent sa1 = Fixtures.createStudyAgent("test");
		StudyAgent sa2 = Fixtures.createStudyAgent("test");
		assertTrue(sa1.equals(sa2));
	}
	
	public void testEqualsObject_WhenOneIsRetired() {
		StudyAgent sa1 = Fixtures.createStudyAgent("test");
		StudyAgent sa2 = Fixtures.createStudyAgent("test");
		sa2.retire();
		assertFalse(sa1.equals(sa2));
	}
	
	public void testEqualsObject_WhenBothAreRetired() {
		StudyAgent sa1 = Fixtures.createStudyAgent("test");
		sa1.retire();
		StudyAgent sa2 = Fixtures.createStudyAgent("test");
		sa2.retire();
		assertFalse(sa1.equals(sa2));
	}
	
	public void testEquals_AddingInSet_NoneRetired(){
		List<StudyAgent> dummyList = new ArrayList<StudyAgent>();
		Agent a1 = Fixtures.createAgent("abcd", dummyList);
		Agent a2 = Fixtures.createAgent("efg",dummyList);
		StudyAgent sa1 = Fixtures.createStudyAgent("abcd");
		sa1.setAgent(a1);
		StudyAgent sa2 = Fixtures.createStudyAgent("abcd");
		sa2.setAgent(a1);
		StudyAgent sa3 = Fixtures.createStudyAgent("efg");
		sa3.setAgent(a2);
		StudyAgent sa4 = Fixtures.createStudyAgent("efg");
		sa4.setAgent(a2);
		
		List<StudyAgent> agents = new ArrayList<StudyAgent>();
		agents.add(sa1);
		agents.add(sa2);
		agents.add(sa3);
		agents.add(sa4);
		
		assertEquals(4, agents.size());
		
		HashSet<StudyAgent> set = new HashSet<StudyAgent>(agents);
		assertEquals(2, set.size());
		
	}
	
	public void testEquals_AddingInSet_OneRetired(){
		List<StudyAgent> dummyList = new ArrayList<StudyAgent>();
		Agent a1 = Fixtures.createAgent("abcd", dummyList);
		Agent a2 = Fixtures.createAgent("efg",dummyList);
		StudyAgent sa1 = Fixtures.createStudyAgent("abcd");
		sa1.setAgent(a1);
		sa1.retire();
		StudyAgent sa2 = Fixtures.createStudyAgent("abcd");
		sa2.setAgent(a1);
		StudyAgent sa3 = Fixtures.createStudyAgent("efg");
		sa3.setAgent(a2);
		StudyAgent sa4 = Fixtures.createStudyAgent("efg");
		sa4.setAgent(a2);
		
		List<StudyAgent> agents = new ArrayList<StudyAgent>();
		agents.add(sa1);
		agents.add(sa2);
		agents.add(sa3);
		agents.add(sa4);
		
		assertEquals(4, agents.size());
		
		HashSet<StudyAgent> set = new HashSet<StudyAgent>(agents);
		assertEquals(3, set.size());
		
	}
	

	public void testEquals_AddingInSet_TwoRetired(){
		List<StudyAgent> dummyList = new ArrayList<StudyAgent>();
		Agent a1 = Fixtures.createAgent("abcd", dummyList);
		Agent a2 = Fixtures.createAgent("efg",dummyList);
		StudyAgent sa1 = Fixtures.createStudyAgent("abcd");
		sa1.setAgent(a1);
		sa1.retire();
		StudyAgent sa2 = Fixtures.createStudyAgent("abcd");
		sa2.setAgent(a1);
		sa2.retire();
		StudyAgent sa3 = Fixtures.createStudyAgent("efg");
		sa3.setAgent(a2);
		sa3.retire();
		StudyAgent sa4 = Fixtures.createStudyAgent("efg");
		sa4.setAgent(a2);
		
		List<StudyAgent> agents = new ArrayList<StudyAgent>();
		agents.add(sa1);
		agents.add(sa2);
		agents.add(sa3);
		agents.add(sa4);
		
		assertEquals(4, agents.size());
		
		HashSet<StudyAgent> set = new HashSet<StudyAgent>(agents);
		assertEquals(4, set.size());
		
	}
	
	public void testIsCTEPLead(){
		StudyAgent sa = Fixtures.createStudyAgent("abcd");
        sa.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("111", "CTEP"));

		
		sa.setRetiredIndicator(false);
		sa.setIndType(INDType.CTEP_IND);
		sa.setPartOfLeadIND(true);

		assertTrue(sa.isCTEPLead());
		
		sa.setRetiredIndicator(true);
		sa.setIndType(INDType.CTEP_IND);
		sa.setPartOfLeadIND(true);
		assertFalse(sa.isCTEPLead());
		
		sa.setRetiredIndicator(false);
		sa.setIndType(null);
		sa.setPartOfLeadIND(false);
		assertFalse(sa.isCTEPLead());

        sa = Fixtures.createStudyAgent("abcd");
        sa.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("111", "DCP"));
		sa.setRetiredIndicator(false);
		sa.setIndType(INDType.DCP_IND);
		sa.setPartOfLeadIND(true);

		assertFalse(sa.isCTEPLead());
		
		sa.setRetiredIndicator(false);
		sa.setIndType(INDType.CTEP_IND);
		sa.setPartOfLeadIND(false);
		assertFalse(sa.isCTEPLead());
	}
	
	public void testShouldHonor1(){
		Study study = registerMockFor(Study.class);
		StudyAgent sa = Fixtures.createStudyAgent("abcd");
        sa.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("111", "CTEP"));
		sa.setStudy(study);
		
		sa.setRetiredIndicator(false);
		sa.setIndType(INDType.CTEP_IND);
		sa.setPartOfLeadIND(true);		
		replayMocks();
		assertTrue(sa.shouldHonor());
		verifyMocks();
		
	}
	
	public void testShouldHonor2(){
		Study study = registerMockFor(Study.class);
		StudyAgent sa = Fixtures.createStudyAgent("abcd");
        sa.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("111", "CTEP"));
		sa.setStudy(study);
		
		sa.setRetiredIndicator(false);
		sa.setIndType(INDType.DCP_IND);
		sa.setPartOfLeadIND(true);		
		replayMocks();
		assertTrue(sa.shouldHonor());
		verifyMocks();
	}
	
	public void testShouldHonor3(){
		Study study = registerMockFor(Study.class);
		StudyAgent sa = Fixtures.createStudyAgent("abcd");
		sa.setStudy(study);
		
		sa.setRetiredIndicator(false);
		sa.setIndType(INDType.DCP_IND);
		sa.setPartOfLeadIND(true);		
		EasyMock.expect(study.hasLeadCTEPInds()).andReturn(true);
		replayMocks();
		assertFalse(sa.shouldHonor());
		verifyMocks();
	}
    
    
    public void testGetInvestigationalNewDrugInactive(){
        StudyAgent sa1 = Fixtures.createStudyAgent("test");
        assertFalse(sa1.getInvestigationalNewDrugInactive());

        sa1.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("1", "CTEP"));
        assertFalse(sa1.getInvestigationalNewDrugInactive());
        {
        StudyAgent sa2 = Fixtures.createStudyAgent("test");

        StudyAgentINDAssociation ass1 =     Fixtures.createStudyAgentIndAssociation("1", "CTEP");
        sa2.addStudyAgentINDAssociation(ass1);
        ass1.getInvestigationalNewDrug().setStatus(ActiveInactiveStatus.IN.getCode());


        StudyAgentINDAssociation ass2 = Fixtures.createStudyAgentIndAssociation("1", "CTEP");
        ass2.getInvestigationalNewDrug().setStatus(ActiveInactiveStatus.IN.getCode());
        sa2.addStudyAgentINDAssociation(ass2);
        assertTrue(sa2.getInvestigationalNewDrugInactive());
        }

        {
            StudyAgent sa2 = Fixtures.createStudyAgent("test");

            StudyAgentINDAssociation ass1 =     Fixtures.createStudyAgentIndAssociation("1", "CTEP");
            sa2.addStudyAgentINDAssociation(ass1);
            ass1.getInvestigationalNewDrug().setStatus(ActiveInactiveStatus.AC.getCode());


            StudyAgentINDAssociation ass2 = Fixtures.createStudyAgentIndAssociation("1", "CTEP");
            ass2.getInvestigationalNewDrug().setStatus(ActiveInactiveStatus.AC.getCode());
            sa2.addStudyAgentINDAssociation(ass2);
            assertFalse(sa2.getInvestigationalNewDrugInactive());
        }

    }



	public void testGetHasIndHeldByNci(){
        {
            StudyAgent sa1 = Fixtures.createStudyAgent("test");
            sa1.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("1", "CTEP"));
            assertTrue(sa1.getHasIndHeldByNci());
            sa1.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("1", "UX"));
            assertTrue(sa1.getHasIndHeldByNci());
        }
        {
            StudyAgent sa1 = Fixtures.createStudyAgent("test");
            assertFalse(sa1.getHasIndHeldByNci());
            sa1.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("1", "UX"));
            assertFalse(sa1.getHasIndHeldByNci());
        }
    }

}
