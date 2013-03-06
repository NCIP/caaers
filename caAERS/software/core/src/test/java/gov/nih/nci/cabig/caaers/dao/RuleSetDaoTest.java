/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    
    public void testGetRuleSetForSafetySignalling(){
        Organization org = orgDao.getById(-1001);

        Study s = studyDao.getById(-1);
        {
            RuleSet rs0 = new RuleSet();
            rs0.setRuleBindURI("x0");
            rs0.setSponsor(org);
            rs0.setRuleLevel(RuleLevel.Institution);
            rs0.setRuleType(RuleType.FIELD_LEVEL_RULES);
            rs0.setStatus(RuleSet.STATUS_ENABLED);
            rs0.setStudy(s);
            dao.save(rs0);
        }
        interruptSession();
        {
            RuleSet rs1 = new RuleSet();
            rs1.setRuleBindURI("x1");
            rs1.setSponsor(org);
            rs1.setRuleLevel(RuleLevel.Institution);
            rs1.setRuleType(RuleType.SAFETY_SIGNALLING_RULES);
            rs1.setStatus(RuleSet.STATUS_ENABLED);
            rs1.setStudy(s);
            dao.save(rs1);
        }
        interruptSession();
        {
            RuleSet rs2 = new RuleSet();
            rs2.setRuleBindURI("x2");
            rs2.setSponsor(org);
            rs2.setRuleLevel(RuleLevel.Institution);
            rs2.setRuleType(RuleType.SAFETY_SIGNALLING_RULES);
            rs2.setStatus(RuleSet.STATUS_DISABLED);
            rs2.setStudy(s);
            dao.save(rs2);
        }
        interruptSession();
        {
            RuleSet rs3 = new RuleSet();
            rs3.setRuleBindURI("x3");
            rs3.setSponsor(org);
            rs3.setRuleLevel(RuleLevel.Institution);
            rs3.setRuleType(RuleType.SAFETY_SIGNALLING_RULES);
            rs3.setStatus(RuleSet.STATUS_ENABLED);
            dao.save(rs3);
        }
        interruptSession();
        {
        	List<RuleSet> ruleSets= dao.getRuleSetForSafetySignalling();
            System.out.println(ruleSets);
            assertEquals(1, ruleSets.size());
            assertEquals("x1", ruleSets.get(0).getRuleBindURI());
        }
    }

}
