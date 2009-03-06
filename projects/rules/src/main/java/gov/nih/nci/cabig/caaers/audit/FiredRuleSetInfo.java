package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.business.service.RulesEngineService;
import gov.nih.nci.cabig.caaers.rules.business.service.RulesEngineServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FiredRuleSetInfo {
    private String ruleSetType;

    private String organizationName;

    private String role;

    private String studyName;

    private String ruleSetName;

    private List<String> ruleNames;

    private String bindUri;

    private java.util.Date exeDate = new Date();

    public FiredRuleSetInfo(String bindUri) {
        this.bindUri = bindUri;
    }

    public List<String> getRuleNames() throws Exception {
        if (ruleNames == null) {
            ruleNames = new ArrayList<String>();
            RulesEngineService res = new RulesEngineServiceImpl();
            RuleSet rs = res.getRuleSet(bindUri);

            if (rs == null) return ruleNames;

            List<Rule> rules = rs.getRule();
            for (int i = 0; i < rules.size(); i++) {
                Rule rule = (Rule) rules.get(i);
                ruleNames.add(rule.getMetaData().getName());
            }

        }
        return ruleNames;
    }

    public void setRuleNames(List<String> ruleNames) {
        this.ruleNames = ruleNames;
    }

    public String getRuleSetName() {
        String str = AuditStringUtils.isEmpty(ruleSetName) == true ? "null" : ruleSetName;
        return str;
    }

    public void setRuleSetName(String ruleSetName) {
        this.ruleSetName = ruleSetName;
    }

    public String getOrganizationName() {
        String str = AuditStringUtils.isEmpty(organizationName) == true ? "null" : organizationName;
        return str;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getRole() {
        String str = AuditStringUtils.isEmpty(role) == true ? "null" : role;
        return str;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRuleSetType() {
        String str = AuditStringUtils.isEmpty(ruleSetType) == true ? "null" : ruleSetType;
        return str;
    }

    public void setRuleSetType(String ruleSetType) {
        this.ruleSetType = ruleSetType;
    }

    public String getStudyName() {
        String str = AuditStringUtils.isEmpty(studyName) == true ? "null" : studyName;
        return str;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public void setExecutionDate(Date date) {
        this.exeDate = date;
    }

    public Date getExecutionDate() {
        return exeDate;
    }

    public int getMaxWidth() {
        int i = 0;

        int j = 5 + LogTitle.RULE_SET_NAME.getTitle().length();

        if (j > i) {
            i = j;
        }
        j = 5 + this.getRuleSetName().length();

        if (j > i) {
            i = j;
        }
        j = 5 + LogTitle.RULE_SET_TYPE.getTitle().length();

        if (j > i) {
            i = j;
        }
        j = 5 + this.getRuleSetType().length();

        if (j > i) {
            i = j;
        }

        j = 5 + LogTitle.STUDY_NAME.getTitle().length();

        if (j > i) {
            i = j;
        }
        j = 5 + this.getStudyName().length();

        if (j > i) {
            i = j;
        }
        j = 5 + LogTitle.ORGANIZATION_NAME.getTitle().length();

        if (j > i) {
            i = j;
        }

        j = 5 + this.getOrganizationName().length();

        if (j > i) {
            i = j;
        }

        j = 5 + LogTitle.ORGANIZATION_ROLE.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + this.getRole().length();
        if (j > i) {
            i = j;
        }

        j = 5 + LogTitle.TIME_OF_EXECUTION.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + exeDate.toString().length();
        if (j > i) {
            i = j;
        }
        return i;
    }
}
