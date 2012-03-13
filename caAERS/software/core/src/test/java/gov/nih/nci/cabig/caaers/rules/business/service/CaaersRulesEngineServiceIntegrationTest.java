package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.utils.RuleUtil;
import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.query.RuleSetQuery;
import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;

/**
 * @author Biju Joseph
 */
public class CaaersRulesEngineServiceIntegrationTest extends CaaersDbTestCase {
    CaaersRulesEngineService service;
    
    public void setUp() throws Exception {
         service = (CaaersRulesEngineService) getDeployedApplicationContext().getBean("caaersRulesEngineService") ;

    }

    public void testExportRules() throws Exception {

    }

    public void testImportRules() throws Exception {
        
        InputStream in = RuleUtil.getResouceAsStream("sae_reporting_rules_sponsor_org_ctep.xml");
        String xml = RuleUtil.getFileContext(in);
        System.out.println(xml);
        File f = File.createTempFile("r_"+ System.currentTimeMillis(), "sae.xml");
        System.out.println(f.getAbsolutePath());
        FileWriter fw = new FileWriter(f);
        IOUtils.write(xml, fw);
        IOUtils.closeQuietly(fw);
        assertTrue(f.exists());
        
        assertTrue(findRuleSets().isEmpty());
        interruptSession();
        List<String> reportDefNames = service.importRules(f.getAbsolutePath());
        f.delete();
        List<RuleSet> ruleSets = findRuleSets();
        assertFalse(ruleSets.isEmpty());
        
        RuleSet rs = ruleSets.get(0);
        
        assertTrue(rs.isEnabled());
        
        String xml2 = service.exportRules(rs.getRuleBindURI());
        
        assertNotNull(xml2);
        
        System.out.println(xml);
        System.out.println(xml2);
        assertEquals(xml, xml2);
        

    }
    
    public List<RuleSet> findRuleSets(){
        RuleSetQuery ruleSetQuery = new RuleSetQuery();
        ruleSetQuery.filterByRuleType(RuleType.REPORT_SCHEDULING_RULES);
        ruleSetQuery.filterByRuleLevel(RuleLevel.Sponsor);
        ruleSetQuery.filterByOrganizationId(-1001);
        ruleSetQuery.filterByStatus(RuleSet.STATUS_ENABLED);
        return (List<RuleSet>)service.getRuleSetDao().search(ruleSetQuery);
    }
}
