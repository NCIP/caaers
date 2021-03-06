/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.api.RulesEngineService;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.utils.RepositoryCleaner;
import com.semanticbits.rules.utils.RulesPropertiesFileLoader;
import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.CaaersTestCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Ignore;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Ion C. Olaru 
 *
 */
@Ignore
public class CaaersRulesEngineServiceRuleableFieldsTest_BROKEN_ extends CaaersTestCase {

	public static final String SPONSOR_LEVEL = "Sponsor";
    public static final String INSTITUTIONAL_LEVEL = "Institution";
    public static final String SPONSOR_DEFINED_STUDY_LEVEL = "SponsorDefinedStudy";
    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = "InstitutionDefinedStudy";
	private CaaersRulesEngineService caaersRulesEngineService;
	private RulesEngineService rulesEngineService;
	private JAXBContext jaxbContext = null;
    private Unmarshaller unmarshaller = null;
    private RuleSet ruleSet;
    RulesPropertiesFileLoader propertiesBean;

    private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
        return testDataStream;
    }
//
//	@Override
//	protected void setUp() throws Exception {
//		super.setUp();
//
//		propertiesBean = RulesPropertiesFileLoader.getLoadedInstance();
//        String url = propertiesBean.getProperties().getProperty("rules.repository");
//        new RepositoryCleaner(url);
//
//		caaersRulesEngineService = (CaaersRulesEngineService)getDeployedApplicationContext().getBean("caaersRulesEngineService");
//		rulesEngineService = (RulesEngineService)getDeployedApplicationContext().getBean("ruleEngineService");
//		jaxbContext = JAXBContext.newInstance("com.semanticbits.rules.brxml");
//		unmarshaller = jaxbContext.createUnmarshaller();
//		InputStream xmlInputDataStream = createInputStream("testRuleableFields_rules.xml");
//		if(xmlInputDataStream != null){
//			ruleSet = (RuleSet) unmarshaller.unmarshal(xmlInputDataStream);
//		}
//        try {
//            List<RuleSet> ruleSetListBeforeDelete = rulesEngineService.getAllRuleSets();
//            for (RuleSet ruleSet : ruleSetListBeforeDelete){
//                rulesEngineService.deleteRuleSet(ruleSet.getName());
//            }
//            List<RuleSet> ruleSetListAfterDelete = rulesEngineService.getAllRuleSets();
//            assertEquals(1, ruleSetListAfterDelete.size());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//	}

    public void testGetFieldsUsedInSAERules() throws Exception {

        fail("Need to implement");
//
//        String ruleSetName = "sae_reporting_rules";
//        String sponsorName = "Cancer Therapy Evaluation Program";
//        String subject = "Sponsor rules";
//        String state = "Draft";
//
//        caaersRulesEngineService.createRuleForSponsor(ruleSet.getRule().get(0), ruleSetName, sponsorName, subject, state);
//
//        ExpeditedAdverseEventReport r = new ExpeditedAdverseEventReport();
//        r.setReportingPeriod(new AdverseEventReportingPeriod());
//        r.setAssignment(new StudyParticipantAssignment());
//        r.getAssignment().setStudySite(new StudySite());
//        r.getAssignment().getStudySite().setStudy(new LocalStudy());
//        r.getAssignment().getStudySite().getStudy().setShortTitle("Study Short Title");
//        Organization o = new LocalOrganization();
//        o.setName(sponsorName);
//        r.getAssignment().getStudySite().setOrganization(o);
//        r.getAssignment().getStudySite().getStudy().addStudyFundingSponsor(new StudyFundingSponsor());
//        r.getAssignment().getStudySite().getStudy().getPrimaryFundingSponsor().setRetiredIndicator(false);
//        r.getAssignment().getStudySite().getStudy().getPrimaryFundingSponsor().setOrganization(o);
//
//        List<String> fields = caaersRulesEngineService.getFieldsUsedInSAERules(r);
//        assertNotNull(fields);
//        assertEquals(6, fields.size());
//        assertEquals("grade", fields.get(0));

    }

}
