package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.api.RepositoryService;
import com.semanticbits.rules.api.RulesEngineService;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.utils.RepositoryCleaner;
import com.semanticbits.rules.utils.RulesPropertiesFileLoader;
import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Ion C. Olaru 
 *
 */
public class CaaersRulesEngineServiceRuleableFieldsTest extends CaaersTestCase{

	public static final String SPONSOR_LEVEL = "Sponsor";
    public static final String INSTITUTIONAL_LEVEL = "Institution";
    public static final String SPONSOR_DEFINED_STUDY_LEVEL = "SponsorDefinedStudy";
    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = "InstitutionDefinedStudy";
	private CaaersRulesEngineService caaersRulesEngineService;
	private RulesEngineService rulesEngineService;
	private RepositoryService repositoryService;
	private JAXBContext jaxbContext = null;
    private Unmarshaller unmarshaller = null;
    private RuleSet ruleSet;
    RulesPropertiesFileLoader propertiesBean;

    private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
        return testDataStream;
    }

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		propertiesBean = RulesPropertiesFileLoader.getLoadedInstance();
        String url = propertiesBean.getProperties().getProperty("rules.repository");
        new RepositoryCleaner(url);

		caaersRulesEngineService = (CaaersRulesEngineService)getDeployedApplicationContext().getBean("caaersRulesEngineService");
		rulesEngineService = (RulesEngineService)getDeployedApplicationContext().getBean("ruleEngineService");
		repositoryService = (RepositoryService)getDeployedApplicationContext().getBean("jcrService");
		jaxbContext = JAXBContext.newInstance("com.semanticbits.rules.brxml");
		unmarshaller = jaxbContext.createUnmarshaller();
		InputStream xmlInputDataStream = createInputStream("testRuleableFields_rules.xml");
		if(xmlInputDataStream != null){
			ruleSet = (RuleSet) unmarshaller.unmarshal(xmlInputDataStream);
		}
	}

    public void testGetRuleableFieldsForAE() throws Exception {

        String ruleSetName = "sae_reporting_rules";
        String sponsorName = "Aalborg University Hospital";
        String subject = "Sponsor rules";
        String state = "Draft";

        caaersRulesEngineService.createRuleForSponsor(ruleSet.getRule().get(0), ruleSetName, sponsorName, subject, state);

        ExpeditedAdverseEventReport r = new ExpeditedAdverseEventReport();
        r.setReportingPeriod(new AdverseEventReportingPeriod());
        r.setAssignment(new StudyParticipantAssignment());
        r.getAssignment().setStudySite(new StudySite());
        r.getAssignment().getStudySite().setStudy(new LocalStudy());
        Organization o = new LocalOrganization();
        o.setName(sponsorName);
        r.getAssignment().getStudySite().setOrganization(o);
        r.getAssignment().getStudySite().getStudy().addStudyFundingSponsor(new StudyFundingSponsor());
        r.getAssignment().getStudySite().getStudy().getPrimaryFundingSponsor().setRetiredIndicator(false);
        r.getAssignment().getStudySite().getStudy().getPrimaryFundingSponsor().setOrganization(o);

        List<String> fields = caaersRulesEngineService.getRuleableFieldsForAE(r);
        assertNotNull(fields);
        assertEquals(6, fields.size());
        assertEquals("grade", fields.get(0));

    }

}