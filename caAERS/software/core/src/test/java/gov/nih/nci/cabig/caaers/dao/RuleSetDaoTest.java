package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.query.RuleSetQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import junit.framework.TestCase;

import java.util.List;

/**
 *@author Biju Joseph.
 */
public class RuleSetDaoTest extends CaaersDbTestCase {
    RuleSetDao dao;
    OrganizationDao orgDao;
    StudyDao studyDao;

    public void setUp() throws Exception{
       super.setUp();
       dao = (RuleSetDao) getApplicationContext().getBean("ruleSetDao");
       orgDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
       studyDao = (StudyDao) getApplicationContext().getBean("studyDao");
    }
    public void testDomainClass() throws Exception {
       assertTrue(dao.domainClass().getName().equals(RuleSet.class.getName()));
    }
    
    public void testSaveAndRetrieve(){
        Organization org = orgDao.getById(-1001);

        Study s = studyDao.getById(-1);
        {
            RuleSet rs1 = new RuleSet();
            rs1.setRuleBindURI("x1");
            rs1.setSponsor(org);
            rs1.setRuleLevel(RuleLevel.Institution);
            rs1.setRuleType(RuleType.FIELD_LEVEL_RULES);
            rs1.setStatus(RuleSet.STATUS_ENABLED);
            dao.save(rs1);
        }
        interruptSession();
        {
            RuleSetQuery ruleSetQuery = new RuleSetQuery();
            List<RuleSet> ruleSets = (List<RuleSet>) dao.search(ruleSetQuery);
            System.out.println(ruleSets);
            assertEquals(1, ruleSets.size());
            assertEquals("x1", ruleSets.get(0).getRuleBindURI());
        }
        interruptSession();
        {
            RuleSetQuery ruleSetQuery = new RuleSetQuery();
            ruleSetQuery.filterByRuleBindURI("x1");
            List<RuleSet> ruleSets = (List<RuleSet>) dao.search(ruleSetQuery);
            System.out.println(ruleSets);
            assertEquals(1, ruleSets.size());
            assertEquals("x1", ruleSets.get(0).getRuleBindURI());
        }

        interruptSession();
        {
            RuleSetQuery ruleSetQuery = new RuleSetQuery();
            ruleSetQuery.filterByRuleBindURI("x2");
            List<RuleSet> ruleSets = (List<RuleSet>) dao.search(ruleSetQuery);
            assertEquals(0, ruleSets.size());
        }


    }
    public void testDelete(){
        Organization org = orgDao.getById(-1001);

        Study s = studyDao.getById(-1);
        {
            RuleSet rs1 = new RuleSet();
            rs1.setRuleBindURI("x1");
            rs1.setSponsor(org);
            rs1.setRuleLevel(RuleLevel.Institution);
            rs1.setRuleType(RuleType.FIELD_LEVEL_RULES);
            rs1.setStatus(RuleSet.STATUS_ENABLED);
            dao.save(rs1);
        }
        interruptSession();
        {
            RuleSetQuery ruleSetQuery = new RuleSetQuery();
            List<RuleSet> ruleSets = (List<RuleSet>) dao.search(ruleSetQuery);
            System.out.println(ruleSets);
            assertEquals(1, ruleSets.size());
            assertEquals("x1", ruleSets.get(0).getRuleBindURI());
        }
        interruptSession();
        {
            dao.deleteRuleSet("x1");

        }

        interruptSession();
        {
            RuleSetQuery ruleSetQuery = new RuleSetQuery();
            List<RuleSet> ruleSets = (List<RuleSet>) dao.search(ruleSetQuery);
            System.out.println(ruleSets);
            assertEquals(0, ruleSets.size());
        }


    }


}
