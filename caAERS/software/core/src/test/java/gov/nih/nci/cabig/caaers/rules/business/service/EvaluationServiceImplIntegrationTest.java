package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.utils.RuleUtil;
import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.query.RuleSetQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.dto.SafetyRuleEvaluationResultDTO;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;

/**
 * @author Biju Joseph
 * @date 3/30/12
 */
public class EvaluationServiceImplIntegrationTest extends CaaersDbTestCase {

    CaaersRulesEngineService caaersRulesEngineService;
    EvaluationServiceImpl evaluationService;

    public void setUp() throws Exception {
        super.setUp();
        caaersRulesEngineService = (CaaersRulesEngineService) getDeployedApplicationContext().getBean("caaersRulesEngineService") ;
        evaluationService = (EvaluationServiceImpl) getDeployedApplicationContext().getBean("evaluationService") ;

    }

    /**
     * Deployed rules :
     * Rule 1 - Grade &gt;= and significance &gt;= 0.8 NOTIFY
     * Rule 2 - Grade &lt;= 1 DO_NOT_NOTIFY
     * Rule 3 - Grade == 3 and significance &lt;= 0.3 DO_NOT_NOTIFY
     * @throws Exception
     */
    public void testEvaluateSafetySignallingRules() throws Exception {
        InputStream in = RuleUtil.getResouceAsStream("safety_signalling_rules_study_7211.xml");
        String xml = RuleUtil.getFileContext(in);
        System.out.println(xml);
        File f = File.createTempFile("r_"+ System.currentTimeMillis(), "sae.xml");
        System.out.println(f.getAbsolutePath());
        FileWriter fw = new FileWriter(f);
        IOUtils.write(xml, fw);
        IOUtils.closeQuietly(fw);
        assertTrue(f.exists());

        assertTrue(findRuleSets().isEmpty());

        caaersRulesEngineService.importRules(f.getAbsolutePath());
        f.delete();
        List<RuleSet> ruleSets = findRuleSets();
        assertFalse(ruleSets.isEmpty());

        RuleSet rs = ruleSets.get(0);

        assertTrue(rs.isEnabled());


        TreatmentAssignment ta = Fixtures.createTreatmentAssignment();
        Study s = Fixtures.createStudy("x");
        s.setId(2);
        s.addTreatmentAssignment(ta);


        //Rule 1 - Grade &gt;= and significance &gt;= 0.8 NOTIFY
        {
        ObservedAdverseEventProfile observedAE1 = new ObservedAdverseEventProfile();
        observedAE1.setLowLevelTerm(Fixtures.createLowLevelTerm("abcd","efg"));
        observedAE1.setGrade(Grade.LIFE_THREATENING);
        observedAE1.setObservedSignificance(0.9);
        observedAE1.setpValue(0.9);
        observedAE1.setTreatmentAssignment(ta);


        SafetyRuleEvaluationResultDTO result = evaluationService.evaluateSafetySignallingRules(observedAE1);
        assertEquals("Rule-1", result.getRulesMatched().get(0));
        assertEquals(NotificationStatus.NOTIFY, result.getNotificationStatus());
        }

        //Rule 1 - Grade &gt;= and significance &gt;= 0.8 NOTIFY
        {
            ObservedAdverseEventProfile observedAE1 = new ObservedAdverseEventProfile();
            observedAE1.setLowLevelTerm(Fixtures.createLowLevelTerm("abcd","efg"));
            observedAE1.setGrade(Grade.SEVERE);
            observedAE1.setObservedSignificance(0.9);
            observedAE1.setpValue(0.9);
            observedAE1.setTreatmentAssignment(ta);


            SafetyRuleEvaluationResultDTO result = evaluationService.evaluateSafetySignallingRules(observedAE1);
            assertEquals("Rule-1", result.getRulesMatched().get(0));
            assertEquals(NotificationStatus.NOTIFY, result.getNotificationStatus());
        }

        //Rule 2 - Grade &lt;= 1 DO_NOT_NOTIFY
        {
            ObservedAdverseEventProfile observedAE1 = new ObservedAdverseEventProfile();
            observedAE1.setLowLevelTerm(Fixtures.createLowLevelTerm("abcd","efg"));
            observedAE1.setGrade(Grade.NORMAL);
            observedAE1.setObservedSignificance(0.9);
            observedAE1.setpValue(0.9);
            observedAE1.setTreatmentAssignment(ta);


            SafetyRuleEvaluationResultDTO result = evaluationService.evaluateSafetySignallingRules(observedAE1);
            assertEquals("Rule-2", result.getRulesMatched().get(0));
            assertEquals(NotificationStatus.DO_NOT_NOTIFY, result.getNotificationStatus());
        }

        //Rule 3 - Grade == 3 and significance &lt;= 0.3 DO_NOT_NOTIFY
        {
            ObservedAdverseEventProfile observedAE1 = new ObservedAdverseEventProfile();
            observedAE1.setLowLevelTerm(Fixtures.createLowLevelTerm("abcd","efg"));
            observedAE1.setGrade(Grade.SEVERE);
            observedAE1.setObservedSignificance(0.3);
            observedAE1.setpValue(0.3);
            observedAE1.setTreatmentAssignment(ta);


            SafetyRuleEvaluationResultDTO result = evaluationService.evaluateSafetySignallingRules(observedAE1);
            assertEquals("Rule-3", result.getRulesMatched().get(0));
            assertEquals(NotificationStatus.DO_NOT_NOTIFY, result.getNotificationStatus());
        }


    }

    public List<RuleSet> findRuleSets(){
        RuleSetQuery ruleSetQuery = new RuleSetQuery();
        ruleSetQuery.filterByRuleType(RuleType.SAFETY_SIGNALLING_RULES);
        ruleSetQuery.filterByStatus(RuleSet.STATUS_ENABLED);
        ruleSetQuery.filterByStudyId(2);
        return (List<RuleSet>)caaersRulesEngineService.getRuleSetDao().search(ruleSetQuery);
    }
}
