/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * This is a unit TestCase for RulesEngineServiceImpl class.
 * @author Monish Dombla
 * @author Biju Joseph
 *
 */
public class CaaersRulesEngineServiceIntegrationTest_BROKEN extends CaaersTestCase{
//
//	public static final String SPONSOR_LEVEL = "Sponsor";
//    public static final String INSTITUTIONAL_LEVEL = "Institution";
//    public static final String SPONSOR_DEFINED_STUDY_LEVEL = "SponsorDefinedStudy";
//    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = "InstitutionDefinedStudy";
//	private CaaersRulesEngineService caaersRulesEngineService;
//	private RulesEngineService rulesEngineService;
//	private RepositoryService repositoryService;
//
//    RulesPropertiesFileLoader propertiesBean;
//
//    private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
//        InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
//        return testDataStream;
//    }
//
//    private File createTmpFileFromResource(String testDataFileName) throws FileNotFoundException {
//        try{
//                long l = System.currentTimeMillis();
//                File f = File.createTempFile("t_" + l , "_import.xml");
//                FileCopyUtils.copy(createInputStream(testDataFileName), new FileOutputStream(f));
//                return f;
//        }catch(IOException ioe){
//            throw new FileNotFoundException(ioe.getMessage());
//        }
//
//    }
//
//	@Override
//	protected void setUp() throws Exception {
//		super.setUp();
//
//		//cleans the rules repository
//        cleanRulesRepo();
//
//		caaersRulesEngineService = (CaaersRulesEngineService)getDeployedApplicationContext().getBean("caaersRulesEngineService");
//		rulesEngineService = (RulesEngineService)getDeployedApplicationContext().getBean("ruleEngineService");
//		repositoryService = (RepositoryService)getDeployedApplicationContext().getBean("jcrService");
//
//	}
//
//    @Override
//    protected void tearDown() throws Exception {
//        cleanRulesRepo();//cleans the rules repository
//        super.tearDown();
//    }
//
//    private void cleanRulesRepo(){
//       try{
//
//		propertiesBean = RulesPropertiesFileLoader.getLoadedInstance();
//        String url = propertiesBean.getProperties().getProperty("rules.repository");
//        new RepositoryCleaner(url);
//
//       }catch(Exception ignore){
//           System.out.println("Ignore below exception");
//           ignore.printStackTrace();
//       }
//    }
//
//
//    private RuleSet loadRuleSetFromFile(String fileName) throws RuntimeException{
//        RuleSet ruleSet = null;
//
//        try{
//
//            JAXBContext jaxbContext = null;
//            Unmarshaller unmarshaller = null;
//            jaxbContext = JAXBContext.newInstance("com.semanticbits.rules.brxml");
//            unmarshaller = jaxbContext.createUnmarshaller();
//            InputStream xmlInputDataStream = createInputStream(fileName);
//            if(xmlInputDataStream != null){
//                ruleSet = (RuleSet) unmarshaller.unmarshal(xmlInputDataStream);
//                ruleSet.getRule().get(0).setId(null);
//            }
//
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }
//
//        return ruleSet;
//    }
//
//    private Organization createAndSaveOrganziation(String nciCode){
//        Organization org = Fixtures.createOrganization("test", nciCode);
//        caaersRulesEngineService.getOrganizationDao().save(org);
//        return org;
//    }
//
//	/**
//	 * Tests method createRuleForSponsor(...) in RulesEngineServiceImpl
//	 * New RuleSet and New Rule
//	 */
//	public void testSaveRuleSetForSponsor() throws Exception{
//
//        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");
//
//        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
//        Organization org = createAndSaveOrganziation(nciCode);
//        Integer orgId = org.getId().intValue();
//        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.sponsor.ORG_" + orgId + ".sae_reporting_rules";
//        String modifiedSubject = "SAE Reporting Rules||Sponsor||" +  nciCode + "|| || ";
//        caaersRulesEngineService.saveRuleSet(rs, SPONSOR_LEVEL, "SAE Reporting Rules", org, null,null);
//
//        RuleSet loadedRs = caaersRulesEngineService.getRuleSetByPackageName(modifiedPackageName);
//        assertEquals(1, loadedRs.getRule().size());
//        assertEquals("karthik test report 1", loadedRs.getRule().get(0).getAction().get(0));
//        assertEquals(modifiedSubject,loadedRs.getSubject());
//
//	}
//
//    /**
//	 * Tests method create rule for institution(...) in RulesEngineServiceImpl
//	 * New RuleSet and New Rule
//	 */
//	public void testSaveRuleSetForInstitution() throws Exception{
//
//        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");
//
//        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
//        Organization org = createAndSaveOrganziation(nciCode);
//        Integer orgId = org.getId().intValue();
//        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.institution.ORG_" + orgId + ".sae_reporting_rules";
//        String modifiedSubject = "SAE Reporting Rules||Institution|| ||" +  nciCode + "|| ";
//        caaersRulesEngineService.saveRuleSet(rs, INSTITUTIONAL_LEVEL, "SAE Reporting Rules", null, org,null);
//
//        RuleSet loadedRs = caaersRulesEngineService.getRuleSetByPackageName(modifiedPackageName);
//        assertEquals(1, loadedRs.getRule().size());
//        assertEquals("karthik test report 1", loadedRs.getRule().get(0).getAction().get(0));
//        assertEquals(modifiedSubject,loadedRs.getSubject());
//
//	}
//
//
//    /**
//     * Tests method createRuleForSponsorStudy(...) in RulesEngineServiceImpl
//     * New RuleSet and New Rule
//     */
//    public void testSaveRuleSetForSponsorDefinedStudy() throws Exception{
//
//        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");
//
//        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
//        Organization org = createAndSaveOrganziation(nciCode);
//        Integer orgId = org.getId().intValue();
//        Study study = Fixtures.createStudy("test");
//        study.setId(12);
//        SystemAssignedIdentifier sysId1 = Fixtures.createSystemAssignedIdentifier("9876");
//        sysId1.setPrimaryIndicator(false);
//        study.addIdentifier(sysId1);
//
//        SystemAssignedIdentifier sysId2 = Fixtures.createSystemAssignedIdentifier("6789");
//        sysId2.setPrimaryIndicator(true);
//        study.addIdentifier(sysId2);
//
//
//        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.sponsor.study.ORG_" + orgId  +".STU_12.sae_reporting_rules";
//        String modifiedSubject = "SAE Reporting Rules||SponsorDefinedStudy||" +  nciCode + "|| ||value:6789";
//        caaersRulesEngineService.saveRuleSet(rs, SPONSOR_DEFINED_STUDY_LEVEL, "SAE Reporting Rules", org, null,study);
//
//        RuleSet loadedRs = caaersRulesEngineService.getRuleSetByPackageName(modifiedPackageName);
//        assertEquals(1, loadedRs.getRule().size());
//        assertEquals("karthik test report 1", loadedRs.getRule().get(0).getAction().get(0));
//        assertEquals(modifiedSubject,loadedRs.getSubject());
//    }
//
//    /**
//     * Tests method create rule for institutionStudy(...) in RulesEngineServiceImpl
//     * New RuleSet and New Rule
//     */
//    public void testSaveRuleSetForInstitutionStudy() throws Exception{
//
//        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");
//
//        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
//        Organization org = createAndSaveOrganziation(nciCode);
//        Integer orgId = org.getId().intValue();
//        Study study = Fixtures.createStudy("test");
//        study.setId(12);
//        SystemAssignedIdentifier sysId1 = Fixtures.createSystemAssignedIdentifier("9876");
//        sysId1.setPrimaryIndicator(false);
//        study.addIdentifier(sysId1);
//
//        SystemAssignedIdentifier sysId2 = Fixtures.createSystemAssignedIdentifier("6789");
//        sysId2.setPrimaryIndicator(true);
//        study.addIdentifier(sysId2);
//
//
//        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.institution.study.ORG_" + orgId  +".STU_12.sae_reporting_rules";
//        String modifiedSubject = "SAE Reporting Rules||InstitutionDefinedStudy|| ||" +  nciCode + "||value:6789";
//        caaersRulesEngineService.saveRuleSet(rs, INSTITUTION_DEFINED_STUDY_LEVEL, "SAE Reporting Rules", null, org,study);
//
//        RuleSet loadedRs = caaersRulesEngineService.getRuleSetByPackageName(modifiedPackageName);
//        assertEquals(1, loadedRs.getRule().size());
//        assertEquals("karthik test report 1", loadedRs.getRule().get(0).getAction().get(0));
//        assertEquals(modifiedSubject,loadedRs.getSubject());
//    }
//
//
//
//
//	/**
//	 * Tests deployRuleSet(ruleSet)
//	 */
//	public void testDeployRuleSet() throws Exception{
//        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");
//
//        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
//        Organization org = createAndSaveOrganziation(nciCode);
//        Integer orgId = org.getId().intValue();
//        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.sponsor.ORG_" + orgId + ".sae_reporting_rules";
//        String modifiedSubject = "SAE Reporting Rules||Sponsor||" +  nciCode + "|| || ";
//        caaersRulesEngineService.saveRuleSet(rs, SPONSOR_LEVEL, "SAE Reporting Rules", org, null,null);
//
//        caaersRulesEngineService.deployRuleSet(modifiedPackageName);
//
//        //make sure the rule is enabled.
//        PackageItem packageItem =  repositoryService.getRulesRepository().loadPackage(modifiedPackageName);
//        String coverage = packageItem.getCoverage();
//        assertEquals("Enabled", coverage);
//
//	}
//
//	/**
//	 * Tests undeployRuleSet(ruleSet)
//	 */
//	public void testUnDeployRuleSet() throws Exception{
//      RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");
//
//        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
//        Organization org = createAndSaveOrganziation(nciCode);
//        Integer orgId = org.getId().intValue();
//        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.sponsor.ORG_" + orgId + ".sae_reporting_rules";
//        String modifiedSubject = "SAE Reporting Rules||Sponsor||" +  nciCode + "|| || ";
//        caaersRulesEngineService.saveRuleSet(rs, SPONSOR_LEVEL, "SAE Reporting Rules", org, null,null);
//
//        caaersRulesEngineService.deployRuleSet(modifiedPackageName);
//
//        caaersRulesEngineService.unDeployRuleSet(modifiedPackageName);
//
//        //make sure the rule is enabled.
//        PackageItem packageItem =  repositoryService.getRulesRepository().loadPackage(modifiedPackageName);
//        String coverage = packageItem.getCoverage();
//        assertEquals("Not Enabled", coverage);
//	}
//
//    public void _BROKEN_testCreateRuleSet() throws Exception{
//        long curTime = System.currentTimeMillis();
//        RuleSet ruleSet = caaersRulesEngineService.createRuleset("a" + curTime,"b", "c" , "d");
//        assertEquals("a" + curTime, ruleSet.getName());
//        assertEquals("b", ruleSet.getDescription());
//        assertEquals("c", ruleSet.getSubject());
//        assertEquals("d", ruleSet.getCoverage());
//
//
//        RuleSet ruleSet2 = caaersRulesEngineService.getRuleSetByPackageName("a");
//        assertNotNull(ruleSet2);
//
//
//    }
//
//    public void testCreateOrUpdateRuleSet() throws Exception{
//        long curTime = System.currentTimeMillis();
//        RuleSet ruleSet = caaersRulesEngineService.createRuleset("a" + curTime,"b", "c" , "d");
//        assertEquals("a" + curTime, ruleSet.getName());
//        assertEquals("b", ruleSet.getDescription());
//        assertEquals("c", ruleSet.getSubject());
//        assertEquals("d", ruleSet.getCoverage());
//
//        caaersRulesEngineService.createOrUpdateRuleSet(ruleSet, "a" + curTime, "x", "k", "c", "d");
//        RuleSet ruleSet2 = caaersRulesEngineService.getRuleSetByPackageName("a"+curTime);
//        assertNotNull(ruleSet2);
//        assertEquals("b", ruleSet2.getDescription());  //update flow - rule set should not be modified.
//
//
//    }
//
//    public void testGetAllRulesets() throws Exception{
//
//        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");
//
//        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
//        Organization org = createAndSaveOrganziation(nciCode);
//        Integer orgId = org.getId().intValue();
//        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.institution.ORG_" + orgId + ".sae_reporting_rules";
//        String modifiedSubject = "SAE Reporting Rules||Institution|| ||" +  nciCode + "|| ";
//        caaersRulesEngineService.saveRuleSet(rs, INSTITUTIONAL_LEVEL, "SAE Reporting Rules", null, org,null);
//
//        List<RuleSet> ruleSets = caaersRulesEngineService.getAllRuleSets();
//        for(RuleSet ruleSet : ruleSets){
//           if(ruleSet.getName().equals(modifiedPackageName)) return;
//        }
//
//        fail("Unable to find the ruleset");
//    }
	
}
