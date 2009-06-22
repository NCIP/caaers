package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.semanticbits.rules.api.RuleAuthoringService;
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

    public List<ReportDefinition> getReportDefinitions() {

        return reportDefinitions;
    }

    public void setReportDefinitions(List<ReportDefinition> reportDefinitions) {

        this.reportDefinitions = reportDefinitions;

    }

    public CreateRuleCommand(RuleAuthoringService ruleAuthoringService, StudyDao studyDao,
                    NotificationDao notificationDao, CaaersRulesEngineService caaersRulesEngineService,
                    ReportDefinitionDao reportDefinitionDao, OrganizationDao organizationDao,
                    CtcDao ctcDao) {
        setRuleAuthoringService(ruleAuthoringService);
        setStudyDao(studyDao);
        setNotificationDao(notificationDao);
        setCaaersRulesEngineService(caaersRulesEngineService);
        ruleSet = new RuleSet();
        existingRuleSets = new ArrayList<RuleSet>();
        setReportDefinitionDao(reportDefinitionDao);
        setOrganizationDao(organizationDao);
        this.setCtcDao(ctcDao);
        // reportDefinitions = reportDefinitionDao.getAll();
    }

    /*
     * This method saves the RuleSet
     */
    public void save() {
        try {
            List<Rule> rules = ruleSet.getRule();
            // delete columns which are marked as delete .
            for (Rule rule : rules) {
                boolean termSelected = false;

                List<Column> cols = new ArrayList<Column>();
                for (Column col : rule.getCondition().getColumn()) {
                    if (col.isMarkedDelete()) {
                        // System.out.println("is marked delete .. " + col.getIdentifier());
                        cols.add(col);
                    }
                    /*
                     * if (col.getFieldConstraint().get(0).getFieldName().equals("term")) {
                     * termSelected = true; }
                     */

                }

                for (Column col : cols) {
                    rule.getCondition().getColumn().remove(col);
                }

                for (Column col : rule.getCondition().getColumn()) {
                    if (col.getFieldConstraint().get(0).getFieldName().equals("term")) {
                        termSelected = true;
                    }
                }

                // modify category if term selecetd
                for (Column col : rule.getCondition().getColumn()) {
                    if (col.getFieldConstraint().get(0).getFieldName().equals("category")) {
                        if (termSelected) {
                            // System.out.println(col.getExpression());
                            if (col
                                            .getExpression()
                                            .equals(
                                                            "factResolver.assertFact(adverseEvent,'gov.nih.nci.cabig.caaers.domain.CtcCategory','id','0','>')")) {
                                // System.out.println("THIS SCENARIO-A");
                                String expr = col.getExpression();
                                String eval = col.getFieldConstraint().get(0)
                                                .getLiteralRestriction().get(0).getEvaluator();
                                String value = col.getFieldConstraint().get(0)
                                                .getLiteralRestriction().get(0).getValue().get(0);
                                expr = expr.replaceAll("'0'", "'" + value + "'");
                                expr = expr.replaceAll("'>'", "'" + eval + "'");
                                // System.out.println(expr);
                                col.setExpression(expr);
                            } else {
                                col
                                                .setExpression("factResolver.assertFact(adverseEvent,'gov.nih.nci.cabig.caaers.domain.CtcCategory','id','0','>')");
                            }
                        } else {
                            if (col
                                            .getExpression()
                                            .equals(
                                                            "factResolver.assertFact(adverseEvent,'gov.nih.nci.cabig.caaers.domain.CtcCategory','id','0','>')")) {
                                // System.out.println("THIS SCENARIO-B");
                                String expr = col.getExpression();
                                String eval = col.getFieldConstraint().get(0)
                                                .getLiteralRestriction().get(0).getEvaluator();
                                String value = col.getFieldConstraint().get(0)
                                                .getLiteralRestriction().get(0).getValue().get(0);
                                expr = expr.replaceAll("'0'", "'" + value + "'");
                                expr = expr.replaceAll("'>'", "'" + eval + "'");
                                // System.out.println(expr);
                                col.setExpression(expr);
                            }
                        }
                    }
                }

                // get comma seperated values ....

                for (Column col : rule.getCondition().getColumn()) {
                    String value = col.getFieldConstraint().get(0).getLiteralRestriction().get(0)
                                    .getValue().get(0);
                    if (value.contains(",")) {
                        List<String> values = RuleUtil.charSeparatedStringToStringList(value, ",");
                        col.getFieldConstraint().get(0).getLiteralRestriction().get(0).setValue(
                                        values);
                    }

                }

                rule.getCondition().getColumn().add(createCriteriaForFactResolver());

            }

            ruleSet.setCoverage("Not Enabled");

            String subject = "";

            System.out.println("------- LEVEL ----- " + getLevel());

            if (SPONSOR_DEFINED_STUDY_LEVEL.equals(getLevel())) {
                subject = "Sponsor defined rules for a study||" + getSponsorName() + "||"
                                + getCategoryIdentifier();
            } else if (SPONSOR_LEVEL.equals(getLevel())) {
                subject = "Sponsor rules||" + getSponsorName();
            } else if (INSTITUTIONAL_LEVEL.equals(getLevel())) {
                subject = "Institution rules||" + getInstitutionName();
            } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equals(getLevel())) {

                subject = "Institution defined rules for a study||" + getInstitutionName() + "||"
                                + getCategoryIdentifier();
            }

            ruleSet.setDescription(ruleSetName);
            ruleSet.setSubject(subject);

            // Set the Package name and categoryIdentifier for all rules before saving them.
            for (Rule rule : rules) {
                rule.getMetaData().setPackageName(constructPackageName(getLevel()));
                rule
                                .getMetaData()
                                .setDescription(
                                                "Setting Description since its mandatory by JBoss Repository config");

                populateCategoryBasedColumns(rule);
            }

            if (CreateRuleCommand.SPONSOR_LEVEL.equalsIgnoreCase(this.getLevel())) {
            	caaersRulesEngineService.saveRulesForSponsor(ruleSet, sponsorName);
            } else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equalsIgnoreCase(this.getLevel())) {
            	caaersRulesEngineService.saveRulesForInstitution(ruleSet, institutionName);
            } else if (CreateRuleCommand.SPONSOR_DEFINED_STUDY_LEVEL.equalsIgnoreCase(this
                            .getLevel())) {
            	caaersRulesEngineService.saveRulesForSponsorDefinedStudy(ruleSet, categoryIdentifier,
                                sponsorName);

            } else if (CreateRuleCommand.INSTITUTION_DEFINED_STUDY_LEVEL.equalsIgnoreCase(this
                            .getLevel())) {
            	caaersRulesEngineService.saveRulesForInstitutionDefinedStudy(ruleSet, categoryIdentifier,
                                institutionName);

            }

            // deploy and undeploy

        } catch (Exception ex) {
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

    private void populateCategoryBasedColumns(Rule rule) {
        if (SPONSOR_DEFINED_STUDY_LEVEL.equals(getLevel())) {
            rule.getCondition().getColumn().add(createCriteriaForSponsor(getSponsorName()));
            rule.getCondition().getColumn().add(
                            createCriteriaForStudy(getCategoryIdentifier(),
                                            SPONSOR_DEFINED_STUDY_LEVEL));
        } else if (SPONSOR_LEVEL.equals(getLevel())) {
            rule.getCondition().getColumn().add(createCriteriaForSponsor(getSponsorName()));
        } else if (INSTITUTIONAL_LEVEL.equals(getLevel())) {
            rule.getCondition().getColumn().add(createCriteriaForInstitute(getInstitutionName()));
        } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equals(getLevel())) {
            rule.getCondition().getColumn().add(createCriteriaForInstitute(getInstitutionName()));
            rule.getCondition().getColumn().add(
                            createCriteriaForStudy(getCategoryIdentifier(),
                                            INSTITUTION_DEFINED_STUDY_LEVEL));
        }
    }

    /*
     * This method returns the attribute to be used for creating the criteria
     */
    private String getFieldNameBasedOnLevel(String level) {
        String fieldName = "shortTitle";

        if (SPONSOR_LEVEL.equals(level)) {
            fieldName = "primarySponsorCode";
        } else if (INSTITUTIONAL_LEVEL.equals(level)) {
            fieldName = "name";
        } else if (SPONSOR_DEFINED_STUDY_LEVEL.equals(level)) {
            fieldName = "shortTitle";
        } else if (INSTITUTION_DEFINED_STUDY_LEVEL.equals(level)) {
            fieldName = "shortTitle";
        }

        return fieldName;
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

    /*
     * This method creates criteria column with study short title as the criteria
     */
    private Column createCriteriaForStudy(String criteriaValue, String level) {
        Column column = BRXMLHelper.newColumn();
        column.setObjectType(gov.nih.nci.cabig.caaers.domain.Study.class.getName());
        column.setIdentifier("studySDO");

        String expression = "factResolver.assertFact(studySDO,null," + "\"shortTitle" + "\","
                        + "\"" + criteriaValue + "\",\"==\"" + ")";

        column.setExpression(expression);

        List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();

        FieldConstraint fieldConstraint = new FieldConstraint();
        fieldConstraint.setFieldName(getFieldNameBasedOnLevel(level));
        fieldConstraints.add(fieldConstraint);
        ArrayList<LiteralRestriction> literalRestrictions = new ArrayList<LiteralRestriction>();
        LiteralRestriction literalRestriction = new LiteralRestriction();
        literalRestriction.setEvaluator("==");

        literalRestriction.getValue().add(criteriaValue);
        literalRestrictions.add(literalRestriction);
        fieldConstraint.setLiteralRestriction(literalRestrictions);

        column.setFieldConstraint(fieldConstraints);

        return column;

    }

    /*
     * This method creates criteria column with institute name as the criteria
     */
    private Column createCriteriaForInstitute(String criteriaValue) {
        Column column = BRXMLHelper.newColumn();
        column.setObjectType(gov.nih.nci.cabig.caaers.domain.Organization.class.getName());
        column.setIdentifier("organizationSDO");
        String expression = "factResolver.assertFact(organizationSDO,null," + "\"name" + "\","
                        + "\"" + criteriaValue + "\",\"==\"" + ")";

        column.setExpression(expression);

        List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();

        FieldConstraint fieldConstraint = new FieldConstraint();
        fieldConstraint
                        .setFieldName(getFieldNameBasedOnLevel(CreateRuleCommand.INSTITUTIONAL_LEVEL));
        fieldConstraints.add(fieldConstraint);
        ArrayList<LiteralRestriction> literalRestrictions = new ArrayList<LiteralRestriction>();
        LiteralRestriction literalRestriction = new LiteralRestriction();
        literalRestriction.setEvaluator("==");

        literalRestriction.getValue().add(criteriaValue);
        literalRestrictions.add(literalRestriction);
        fieldConstraint.setLiteralRestriction(literalRestrictions);

        column.setFieldConstraint(fieldConstraints);

        return column;

    }

    /*
     * THis method is used to create criteria for sponsor based on the sponsor name
     */
    private Column createCriteriaForSponsor(String criteriaValue) {
        Column column = BRXMLHelper.newColumn();
        column.setObjectType(gov.nih.nci.cabig.caaers.domain.Study.class.getName());
        column.setIdentifier("studySDO");
        String expression = "factResolver.assertFact(studySDO,"
                        + "\"gov.nih.nci.cabig.caaers.domain.Organization" + "\"," + "\"name"
                        + "\"," + "\"" + criteriaValue + "\",\"==\"" + ")";

        column.setExpression(expression);

        List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();

        FieldConstraint fieldConstraint = new FieldConstraint();
        fieldConstraint.setFieldName(getFieldNameBasedOnLevel(CreateRuleCommand.SPONSOR_LEVEL));
        fieldConstraints.add(fieldConstraint);
        ArrayList<LiteralRestriction> literalRestrictions = new ArrayList<LiteralRestriction>();
        LiteralRestriction literalRestriction = new LiteralRestriction();
        literalRestriction.setEvaluator("==");

        literalRestriction.getValue().add(criteriaValue);
        literalRestrictions.add(literalRestriction);
        fieldConstraint.setLiteralRestriction(literalRestrictions);

        column.setFieldConstraint(fieldConstraints);

        return column;

    }

    private Column createCriteriaForFactResolver() {
        Column column = BRXMLHelper.newColumn();
        column.setObjectType("com.semanticbits.rules.objectgraph.FactResolver");
        column.setIdentifier("factResolver");

        return column;

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

}
