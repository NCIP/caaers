package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventInputCommand;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.ReadableRule;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.utils.RuleUtil;

/**
 * This tab will display all the Rules. User will be crreating / editing / deleting rules from this
 * tab.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class RuleTab extends DefaultTab {
    private static final Log logger = LogFactory.getLog(RuleTab.class);

    public RuleTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public RuleTab() {
        super("Rules", "Rules", "rule/author/authorRules");
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
    
    @Override
    public void validate(RuleInputCommand cmd, Errors errors) {
    	CreateRuleCommand command = (CreateRuleCommand) cmd;
    	if(command.getRuleSet().getRule() == null || command.getRuleSet().getRule().size() < 1)
    		errors.reject("RUL_015");
    }
    
    @Override
    public void postProcess(HttpServletRequest request, RuleInputCommand cmd, Errors errors) {
    	logger.debug("In RuleTab post process");
        super.postProcess(request, cmd, errors);
        
        //CreateRuleCommand command = (CreateRuleCommand) cmd;
        //if(!errors.hasErrors())
        //	command.save();
    }

    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) {
        CreateRuleCommand createRuleCommand = ((CreateRuleCommand) command);

        String studyShortTitle = createRuleCommand.getCategoryIdentifier();

        if (!"".equals(studyShortTitle)) {
            Study study = createRuleCommand.getStudyDao().getByShortTitle(studyShortTitle);
            if (study != null) {
                createRuleCommand.setTerminology(study.getAeTerminology().getTerm()
                                .getDisplayName());
            }
        } else {
            createRuleCommand.setTerminology("");
        }

        createRuleCommand.setRuleUi(createRuleCommand.getTerminology());

        RuleSet ruleSet = createRuleCommand.getRuleSet();

        // Return if the rules are already retrieved
        if (ruleSet != null && ruleSet.getDescription() != null
                        && ruleSet.getDescription().equals(createRuleCommand.getRuleSetName())
                        && !createRuleCommand.isDataChanged()) {
            return super.referenceData(command);
        }
        createRuleCommand.setDataChanged(false);

        // Retrieve RuleSet based on the one choosen by the user
        try {
            System.out.println("----- LEVEL in RuleTab ----" + createRuleCommand.getLevel());
            CaaersRulesEngineService rulesEngineService = createRuleCommand.getCaaersRulesEngineService();

            if (CreateRuleCommand.SPONSOR_LEVEL.equals(createRuleCommand.getLevel())) {
                System.out.println("Getting sponsor level rules ....");
                ruleSet = rulesEngineService.getRuleSetForSponsor(createRuleCommand
                                .getRuleSetName(), createRuleCommand.getSponsorName(), false);
                createRuleCommand.setOrganizationName(createRuleCommand.getSponsorName());
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
            } else if (CreateRuleCommand.SPONSOR_DEFINED_STUDY_LEVEL.equals(createRuleCommand
                            .getLevel())) {
                createRuleCommand.setOrganizationName(createRuleCommand.getSponsorName());

                String packageName = createRuleCommand.constructPackageName(createRuleCommand
                                .getLevel());

                ruleSet = rulesEngineService.getRuleSetForSponsorDefinedStudy(createRuleCommand
                                .getRuleSetName(), createRuleCommand.getCategoryIdentifier(),
                                createRuleCommand.getSponsorName(), false);

                boolean areSponsorRules = false;
                // Check whether ruleset exists? Otherwise retrieve sponsor ruleset
                if (ruleSet == null) {

                    RuleSet rs = rulesEngineService.getRuleSetForSponsor(createRuleCommand
                                    .getRuleSetName(), createRuleCommand.getSponsorName(), false);

                    ruleSet = new RuleSet();
                    ruleSet.setDescription(createRuleCommand.getRuleSetName());
                    ruleSet.setRule(rs.getRule());

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
            } else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equals(createRuleCommand.getLevel())) {
                createRuleCommand.setOrganizationName(createRuleCommand.getInstitutionName());
                String packageName = createRuleCommand.constructPackageName(createRuleCommand
                                .getLevel());

                ruleSet = rulesEngineService.getRuleSetForInstitution(createRuleCommand
                                .getRuleSetName(), createRuleCommand.getInstitutionName(), false);

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

            else if (CreateRuleCommand.INSTITUTION_DEFINED_STUDY_LEVEL.equals(createRuleCommand
                            .getLevel())) {
                String packageName = createRuleCommand.constructPackageName(createRuleCommand
                                .getLevel());
                createRuleCommand.setOrganizationName(createRuleCommand.getInstitutionName());
                ruleSet = rulesEngineService.getRuleSetForInstitutionDefinedStudy(createRuleCommand
                                .getRuleSetName(), createRuleCommand.getCategoryIdentifier(),
                                createRuleCommand.getInstitutionName(), false);

                boolean areSponsorRules = false;
                // Check whether ruleset exists? Otherwise retrieve inst ruleset
                if (ruleSet == null) {
                    RuleSet rs = rulesEngineService.getRuleSetForInstitution(createRuleCommand
                                    .getRuleSetName(), createRuleCommand.getInstitutionName(),
                                    false);

                    ruleSet = new RuleSet();
                    ruleSet.setDescription(createRuleCommand.getRuleSetName());
                    ruleSet.setRule(rs.getRule());

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
                ruleSet.setDescription(createRuleCommand.getRuleSetName());
            }
            createRuleCommand.setRuleSet(ruleSet);
            Organization org = createRuleCommand.getOrganizationDao().getByName(
                            createRuleCommand.getOrganizationName());
            
            List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
            reportDefs = getReportDefinitions(org);
            
            /**
             * Get REport definitions of CTEP for DCP studies , because DCP uses CTEP 
             * report definitions also . TEMP fix
             */
            
            if (createRuleCommand.getOrganizationName().equals("Division of Cancer Prevention")) {
            	org = createRuleCommand.getOrganizationDao().getByName("Cancer Therapy Evaluation Program");
            	reportDefs.addAll(getReportDefinitions(org));
            }           
            
            
            createRuleCommand.setReportDefinitions(reportDefs);

        } catch (Exception e) {
            logger.error("Exception while retrieving the Rule Set", e);
            if (ruleSet == null) {
                ruleSet = new RuleSet();
                ruleSet.setDescription(createRuleCommand.getRuleSetName());
            }
            createRuleCommand.setRuleSet(ruleSet);
            Organization org = createRuleCommand.getOrganizationDao().getByName(
                            createRuleCommand.getOrganizationName());

            createRuleCommand.setReportDefinitions(getReportDefinitions(org));
        }

        return super.referenceData(command);
    }
    
    /**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
    
}
