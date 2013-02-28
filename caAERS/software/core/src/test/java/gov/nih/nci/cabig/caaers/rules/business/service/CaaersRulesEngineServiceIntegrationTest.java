/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.utils.RuleUtil;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
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
        super.setUp();
        service = (CaaersRulesEngineService) getDeployedApplicationContext().getBean("caaersRulesEngineService") ;

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

        service.importRules(f.getAbsolutePath());
        f.delete();
        List<RuleSet> ruleSets = findRuleSets();
        assertFalse(ruleSets.isEmpty());
        
        RuleSet rs = ruleSets.get(0);
        
        assertTrue(rs.isEnabled());
        
        String xml2 = service.exportRules(rs.getRuleBindURI());
        
        assertNotNull(xml2);



        f = File.createTempFile("r_"+ System.currentTimeMillis(), "sae.xml");
        fw = new FileWriter(f);
        IOUtils.write(xml, fw);
        IOUtils.closeQuietly(fw);
        assertTrue(f.exists());


        List<String> outList = service.importRules(f.getAbsolutePath());
        f.delete();
        assertEquals(rs.getRuleBindURI(), outList.get(0));

        String xml3 = service.exportRules(rs.getRuleBindURI());
        assertNotNull(xml3);
        
        

    }
    
    public void testConstructSubject(){
       String subject =  service.constructSubject(RuleType.REPORT_SCHEDULING_RULES, RuleLevel.Sponsor,"0","1");
       assertEquals("SAE Reporting Rules||Sponsor||0||0||1", subject);
       subject =  service.constructSubject(RuleType.SAFETY_SIGNALLING_RULES,null,null,"1");
       assertEquals("Safety Signalling Rules|| || || ||1", subject);
        subject =  service.constructSubject(RuleType.SAFETY_SIGNALLING_RULES,null,null,null);
        assertEquals("Safety Signalling Rules|| || || || ", subject);
    }
    
    public List<RuleSet> findRuleSets(){
        RuleSetQuery ruleSetQuery = new RuleSetQuery();
        ruleSetQuery.filterByRuleType(RuleType.REPORT_SCHEDULING_RULES);
        ruleSetQuery.filterByRuleLevel(RuleLevel.Sponsor);
        ruleSetQuery.filterByOrganizationId(6);
        ruleSetQuery.filterByStatus(RuleSet.STATUS_ENABLED);
        return (List<RuleSet>)service.getRuleSetDao().search(ruleSetQuery);
    }
}
