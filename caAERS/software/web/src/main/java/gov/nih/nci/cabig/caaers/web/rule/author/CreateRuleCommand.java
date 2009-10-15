package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.repository.PackageItem;

import com.semanticbits.rules.api.RepositoryService;
import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.api.RuleDeploymentService;
import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.FieldConstraint;
import com.semanticbits.rules.brxml.LiteralRestriction;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.ui.DomainObject;
import com.semanticbits.rules.ui.Field;
import com.semanticbits.rules.ui.RuleUi;
import com.semanticbits.rules.utils.BRXMLHelper;
import com.semanticbits.rules.utils.RuleUtil;

/**
 * Command Object holding information for Rule authoring
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class CreateRuleCommand implements RuleInputCommand {

    private static final Log logger = LogFactory.getLog(CreateRuleCommand.class);

    public static final String SPONSOR_LEVEL = "Sponsor";

    public static final String INSTITUTIONAL_LEVEL = "Institution";

    public static final String SPONSOR_DEFINED_STUDY_LEVEL = "SponsorDefinedStudy";

    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = "InstitutionDefinedStudy";
    
    public static final String CREATE_MODE = "create";
    
    public static final String EDIT_MODE = "edit";

    // public static final String STUDY_LEVEL="StudyLevel";

    private RuleAuthoringService ruleAuthoringService;

    private CaaersRulesEngineService caaersRulesEngineService;

    private NotificationDao notificationDao;

    private ReportDefinitionDao reportDefinitionDao;

    private OrganizationDao organizationDao;

    private CtcDao ctcDao;

    private StudyDao studyDao;

    private RuleSet ruleSet;

    private String categoryIdentifier; // Study Short Title

    private String level;

    private String sponsorName;

    private String ruleSetName; // Ruleset selected by the user

    private List<RuleSet> existingRuleSets; // These are the rule sets retrieved based on the
                                            // Sponsor or Institution or Study

    private String institutionName;

    private String organizationName;

    private boolean isDataChanged;

    private RuleUi ruleUi;

    private String terminology;

    private List<ReportDefinition> reportDefinitions;
    
    private String mode;
    
    private RuleDeploymentService ruleDeploymentService;
    
    private RepositoryService repositoryService;

    public List<ReportDefinition> getReportDefinitions() {

        return reportDefinitions;
    }

    public void setReportDefinitions(List<ReportDefinition> reportDefinitions) {

        this.reportDefinitions = reportDefinitions;

    }

    public CreateRuleCommand(RuleAuthoringService ruleAuthoringService, StudyDao studyDao,
                    NotificationDao notificationDao, CaaersRulesEngineService caaersRulesEngineService,
                    ReportDefinitionDao reportDefinitionDao, OrganizationDao organizationDao,
                    CtcDao ctcDao, RuleDeploymentService ruleDeploymentService, RepositoryService repositoryService) {
        setRuleAuthoringService(ruleAuthoringService);
        setStudyDao(studyDao);
        setNotificationDao(notificationDao);
        setCaaersRulesEngineService(caaersRulesEngineService);
        ruleSet = new RuleSet();
        existingRuleSets = new ArrayList<RuleSet>();
        setReportDefinitionDao(reportDefinitionDao);
        setOrganizationDao(organizationDao);
        this.setCtcDao(ctcDao);
        setMode(EDIT_MODE);
        // reportDefinitions = reportDefinitionDao.getAll();
    }

    /*
     * This method saves the RuleSet
     */
    public void save() {
    	try{
    		caaersRulesEngineService.saveRuleSet(ruleSet, level, sponsorName, institutionName, categoryIdentifier, ruleSetName);
    	}catch (Exception ex) {
            logger.error("Exception while creating Rule:", ex);
        }
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public String getCategoryIdentifier() {
        return categoryIdentifier;
    }

    public void setCategoryIdentifier(String categoryIdentifier) {

        if (categoryIdentifier != null
                        && !categoryIdentifier.equalsIgnoreCase(this.categoryIdentifier)) {
            isDataChanged = true;
        }

        this.categoryIdentifier = categoryIdentifier;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        if (level != null && !level.equalsIgnoreCase(this.level)) {
            isDataChanged = true;
        }

        this.level = level;
    }

    public RuleAuthoringService getRuleAuthoringService() {
        return ruleAuthoringService;
    }

    public void setRuleAuthoringService(RuleAuthoringService ruleAuthoringService) {
        this.ruleAuthoringService = ruleAuthoringService;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public NotificationDao getNotificationDao() {
        return notificationDao;
    }

    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        if (sponsorName != null && !sponsorName.equalsIgnoreCase(this.sponsorName)) {
            isDataChanged = true;
        }

        this.sponsorName = sponsorName;
    }

    public List<RuleSet> getExistingRuleSets() {
        return existingRuleSets;
    }

    public void setExistingRuleSets(List<RuleSet> existingRuleSets) {
        this.existingRuleSets = existingRuleSets;
    }

    public String getRuleSetName() {
        return ruleSetName;
    }

    public void setRuleSetName(String ruleSetName) {
        if (ruleSetName != null && !ruleSetName.equalsIgnoreCase(this.ruleSetName)) {
            isDataChanged = true;
        }

        this.ruleSetName = ruleSetName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        if (institutionName != null && !institutionName.equalsIgnoreCase(this.institutionName)) {
            isDataChanged = true;
        }

        this.institutionName = institutionName;
    }

    /*
     * This method cpnstructs the package name based on the Command object
     */
    public String constructPackageName(String level) {
        final String SPONSOR_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.sponsor";
        final String INSTITUTION_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.institution";

        String packageName = null;

        if (SPONSOR_LEVEL.equalsIgnoreCase(level)) {
            packageName = SPONSOR_BASE_PACKAGE + "."
                            + RuleUtil.getStringWithoutSpaces(getSponsorName()) + "."
                            + RuleUtil.getStringWithoutSpaces(getRuleSetName());
        } else if (INSTITUTIONAL_LEVEL.equalsIgnoreCase(level)) {
            packageName = INSTITUTION_BASE_PACKAGE + "."
                            + RuleUtil.getStringWithoutSpaces(getInstitutionName()) + "."
                            + RuleUtil.getStringWithoutSpaces(getRuleSetName());
        } else if (SPONSOR_DEFINED_STUDY_LEVEL.equalsIgnoreCase(level)) {
            packageName = SPONSOR_BASE_PACKAGE + ".study."
                            + RuleUtil.getStringWithoutSpaces(getSponsorName()) + "."
                            + RuleUtil.getStringWithoutSpaces(getCategoryIdentifier()) + "."
                            + RuleUtil.getStringWithoutSpaces(getRuleSetName());
        } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equalsIgnoreCase(level)) {
            packageName = INSTITUTION_BASE_PACKAGE + ".study."
                            + RuleUtil.getStringWithoutSpaces(getInstitutionName()) + "."
                            + RuleUtil.getStringWithoutSpaces(getCategoryIdentifier()) + "."
                            + RuleUtil.getStringWithoutSpaces(getRuleSetName());
        }

        // System.out.println("Package name is : " + packageName);
        return packageName;

    }

    public CaaersRulesEngineService getCaaersRulesEngineService() {
        return caaersRulesEngineService;
    }

    public void setCaaersRulesEngineService(CaaersRulesEngineService caaersRulesEngineService) {
        this.caaersRulesEngineService = caaersRulesEngineService;
    }

    public boolean isDataChanged() {
        return isDataChanged;
    }

    public void setDataChanged(boolean isDataChanged) {
        this.isDataChanged = isDataChanged;
    }


    public void setRuleUi(String terminology) {

        // System.out.println("termonilogy is " + terminology);
        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("rules-ui.xml");

        Unmarshaller unmarshaller;
        try {
            unmarshaller = JAXBContext.newInstance("com.semanticbits.rules.ui")
                            .createUnmarshaller();
            ruleUi = (RuleUi) unmarshaller.unmarshal(inputStream);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Error in reading rules-ui xml file ");
            e.printStackTrace();
        }

        // ServletContext servletContext = WebContextFactory.get().getServletContext();

        // ruleUi = (RuleUi) servletContext.getAttribute("ruleUi");

        // System.out.println("ui is " + ruleUi.getCondition().size());

        for (DomainObject domainObject : ruleUi.getCondition().get(0).getDomainObject()) {
            List<Field> fields = new ArrayList<Field>();
            List<Field> fields2 = new ArrayList<Field>();
            for (Field field : domainObject.getField()) {
                if (field.getFilter().equals("") || field.getFilter().equals(terminology)) {
                    fields.add(field);
                } else {
                    fields2.add(field);
                }
            }
            fields.addAll(fields2);

            domainObject.setField(fields);
        }

    }
    
    
    public void retrieveRuleSet(){
    	try {
            CaaersRulesEngineService rulesEngineService = this.getCaaersRulesEngineService();

            if (CreateRuleCommand.SPONSOR_LEVEL.equals(this.getLevel())) {
                System.out.println("Getting sponsor level rules ....");
                ruleSet = rulesEngineService.getRuleSetForSponsor(this
                                .getRuleSetName(), this.getSponsorName(), false);
                this.setOrganizationName(this.getSponsorName());
                if (ruleSet != null && ruleSet.getRule().size() > 0) {
                    List<Rule> rules = ruleSet.getRule();

                    for (Rule rule : rules) {
                        List<Column> columns = rule.getCondition().getColumn();

                        for (int i = 0; i < columns.size(); i++) {
                            if ("studySDO".equals(columns.get(i).getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }

                            if ("adverseEventEvaluationResult".equals(columns.get(i)
                                            .getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                            if ("factResolver".equals(columns.get(i).getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                        }
                    }
                }
                // }
            } else if (CreateRuleCommand.SPONSOR_DEFINED_STUDY_LEVEL.equals(this
                            .getLevel())) {
                this.setOrganizationName(this.getSponsorName());

                String packageName = this.constructPackageName(this
                                .getLevel());

                ruleSet = rulesEngineService.getRuleSetForSponsorDefinedStudy(this
                                .getRuleSetName(), this.getCategoryIdentifier(),
                                this.getSponsorName(), false);

                boolean areSponsorRules = false;
                // Check whether ruleset exists? Otherwise retrieve sponsor ruleset
                if (ruleSet == null) {

                    RuleSet rs = rulesEngineService.getRuleSetForSponsor(this
                                    .getRuleSetName(), this.getSponsorName(), false);

                    ruleSet = new RuleSet();
                    ruleSet.setDescription(this.getRuleSetName());
                    ruleSet.setRule(rs.getRule());
                    setMode(CREATE_MODE);
                    ruleSet.setName(packageName);
                    // ruleSet.setSubject(item.getSubject());
                    // ruleSet.setCoverage(item.getCoverage());

                    // dont get from cache ...

                    areSponsorRules = true;
                }

                if (ruleSet != null && ruleSet.getRule().size() > 0) {
                    // ruleSet.setName(packageName);
                    List<Rule> rules = ruleSet.getRule();

                    for (Rule rule : rules) {
                        rule.getMetaData().setPackageName(packageName);
                        // rule.setId(null);
                        List<Column> columns = rule.getCondition().getColumn();

                        for (int i = 0; i < columns.size(); i++) {
                            if ("studySDO".equals(columns.get(i).getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                            if ("adverseEventEvaluationResult".equals(columns.get(i)
                                            .getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                            if ("factResolver".equals(columns.get(i).getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                        }

                        // Remove category from sponsor rules
                        if (areSponsorRules) {
                            rule.setId(null);
                            if (rule.getMetaData() != null) {
                                rule.getMetaData().setCategory(null);
                            }
                        }

                    }
                }
            } else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equals(this.getLevel())) {
                this.setOrganizationName(this.getInstitutionName());
                String packageName = this.constructPackageName(this
                                .getLevel());

                ruleSet = rulesEngineService.getRuleSetForInstitution(this
                                .getRuleSetName(), this.getInstitutionName(), false);

                if (ruleSet != null && ruleSet.getRule().size() > 0) {
                    // ruleSet.setName(packageName);
                    List<Rule> rules = ruleSet.getRule();

                    for (Rule rule : rules) {
                        rule.getMetaData().setPackageName(packageName);
                        // rule.setId(null);
                        List<Column> columns = rule.getCondition().getColumn();

                        // System.out.println("size ..." + columns.size());

                        for (int i = 0; i < columns.size(); i++) {
                            if ("organizationSDO".equals(columns.get(i).getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                            if ("adverseEventEvaluationResult".equals(columns.get(i)
                                            .getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                            if ("factResolver".equals(columns.get(i).getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                        }
                    }
                }
            }

            else if (CreateRuleCommand.INSTITUTION_DEFINED_STUDY_LEVEL.equals(this
                            .getLevel())) {
                String packageName = this.constructPackageName(this
                                .getLevel());
                this.setOrganizationName(this.getInstitutionName());
                ruleSet = rulesEngineService.getRuleSetForInstitutionDefinedStudy(this
                                .getRuleSetName(), this.getCategoryIdentifier(),
                                this.getInstitutionName(), false);

                boolean areSponsorRules = false;
                // Check whether ruleset exists? Otherwise retrieve inst ruleset
                if (ruleSet == null) {
                    RuleSet rs = rulesEngineService.getRuleSetForInstitution(this
                                    .getRuleSetName(), this.getInstitutionName(),
                                    false);

                    ruleSet = new RuleSet();
                    ruleSet.setDescription(this.getRuleSetName());
                    ruleSet.setRule(rs.getRule());
                    setMode(CREATE_MODE);
                    ruleSet.setName(packageName);

                    areSponsorRules = true;
                }

                if (ruleSet != null && ruleSet.getRule().size() > 0) {
                    // ruleSet.setName(packageName);
                    List<Rule> rules = ruleSet.getRule();

                    for (Rule rule : rules) {
                        rule.getMetaData().setPackageName(packageName);
                        // rule.setId(null);
                        List<Column> columns = rule.getCondition().getColumn();

                        for (int i = 0; i < columns.size(); i++) {
                            if ("studySDO".equals(columns.get(i).getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                            if ("organizationSDO".equals(columns.get(i).getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                            if ("adverseEventEvaluationResult".equals(columns.get(i)
                                            .getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                            if ("factResolver".equals(columns.get(i).getIdentifier())) {
                                columns.remove(i);
                                i = -1;
                                continue;
                            }
                        }

                        // Remove category from sponsor rules
                        if (areSponsorRules) {
                            rule.setId(null);
                            if (rule.getMetaData() != null) {
                                rule.getMetaData().setCategory(null);
                            }
                        }

                    }
                }
            }

            if (ruleSet == null) {
                ruleSet = new RuleSet();
                ruleSet.setDescription(this.getRuleSetName());
                setMode(CREATE_MODE);
            }
            this.setRuleSet(ruleSet);
            Organization org = this.getOrganizationDao().getByName(
                            this.getOrganizationName());
            
            List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
            reportDefs = getReportDefinitions(org);
            
            /**
             * Get REport definitions of CTEP for DCP studies , because DCP uses CTEP 
             * report definitions also . TEMP fix
             */
            
            if (this.getOrganizationName().equals("Division of Cancer Prevention")) {
            	org = this.getOrganizationDao().getByName("Cancer Therapy Evaluation Program");
            	reportDefs.addAll(getReportDefinitions(org));
            }           
            
            
            this.setReportDefinitions(reportDefs);

        } catch (Exception e) {
            logger.error("Exception while retrieving the Rule Set", e);
            if (ruleSet == null) {
                ruleSet = new RuleSet();
                ruleSet.setDescription(this.getRuleSetName());
            }
            this.setRuleSet(ruleSet);
            Organization org = this.getOrganizationDao().getByName(
                            this.getOrganizationName());

            this.setReportDefinitions(getReportDefinitions(org));
        }
    }
    
    private List<ReportDefinition> getReportDefinitions(Organization org) {
        // System.out.println("getting report definitions ....");
        // get report defnitions
    	
        List<ReportDefinition> reportDefinitions = org.getReportDefinitions();

        // cut down objects for serialization
        List<ReportDefinition> reducedReportDefinitions = new ArrayList<ReportDefinition>(
                        reportDefinitions.size());
        for (ReportDefinition reportDefinition : reportDefinitions) {
            // reportDefinition.setPlannedNotifications(null);
            // reportDefinition.setTimeScaleUnitType(null);
            reducedReportDefinitions.add(reportDefinition);
        }

        return reducedReportDefinitions;
    }
    
    public void unDeployRuleSet() throws RemoteException {
        caaersRulesEngineService.unDeployRuleSet(ruleSet.getName());
    }
    
    public void deployRuleSet() throws RemoteException {
        caaersRulesEngineService.deployRuleSet(ruleSet.getName());
    }
    
    public void saveAndDeploy(){
    	save();
    	try{
    		deployRuleSet();
    	}catch (RemoteException e){
			logger.warn("Error while deployting the ruleSet ", e);
		}
    }
/*
    public List<CtcCategory> getCategories() {
        List<CtcCategory> categories = ctcDao.getById(3).getCategories();
        // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }
*/
    
    public List<CtcCategory> getCategories() {
    	
    	List<CtcCategory> categories;
    	
    	if (!getCategoryIdentifier().equals("") ) {
    		Study s = getStudyDao().getByShortTitle(getCategoryIdentifier());
        	Ctc ctc = s.getAeTerminology().getCtcVersion();
        	categories = ctc.getCategories();
    	} else {
    		//return an emply list
    		categories = new ArrayList<CtcCategory>();
    	}
    	
         // cut down objects for serialization
        for (CtcCategory category : categories) {
            category.setTerms(null);
        }
        return categories;
    }
    
    public ExpeditedReportSection[] getReportSectionNames() {
        ExpeditedReportSection[] expeditedReportSections = ExpeditedReportSection.values();
        /*
         * String[] sectionNames = new String[expeditedReportSections.length]; for (int i=0;i<expeditedReportSections.length;i++ ){
         * sectionNames[i] = expeditedReportSections[i].name(); }
         */
        return expeditedReportSections;
    }

    public RuleUi getRuleUi() {
        return ruleUi;
    }

    public ReportDefinitionDao getReportDefinitionDao() {
        return reportDefinitionDao;
    }

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public String getLevelDescription() {
        return RuleLevel.valueOf(level).getDescription();

    }

    public String getTerminology() {
        return terminology;
    }

    public void setTerminology(String terminology) {
        this.terminology = terminology;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }
    
    public void setMode(String mode){
    	this.mode = mode;
    }
    
    public String getMode(){
    	return this.mode;
    }
    
    public RuleDeploymentService getRuleDeploymentService() {
        return ruleDeploymentService;
    }

    public void setRuleDeploymentService(RuleDeploymentService ruleDeploymentService) {
        this.ruleDeploymentService = ruleDeploymentService;
    }
    
    public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

}
