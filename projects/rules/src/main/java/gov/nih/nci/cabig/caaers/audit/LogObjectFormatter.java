package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogObjectFormatter {

    public String toTextTable(DecisionContext dc) throws Exception {
        String offSet = "\t\t\t";
        StringBuilder builder = new StringBuilder();
        int headerWidth = this.getTableWidth(dc);

        /**
         * Build header line
         */
        builder.append(offSet);
        builder.append(line(headerWidth));
        builder.append("\n");

        /**
         * Build Rule Context Line
         */
        int k = "Rule Context Information".length();
        int x = (headerWidth - k) / 2;

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(x - 1));
        builder.append("Rule Context Information");
        int rest = headerWidth - (x + k);
        builder.append(this.blank(rest - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Build Bottom line
         */
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Build RuleContext Table Row 1
         */
        int colWidth = headerWidth / 2;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.RULE_SET_NAME.getTitle());
        rest = colWidth - 2 - LogTitle.RULE_SET_NAME.getTitle().length();
        builder.append(this.blank(rest - 2));
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(dc.getFiredRuleSetInfo().getRuleSetName());
        rest = headerWidth - (colWidth + 2 + dc.getFiredRuleSetInfo().getRuleSetName().length());
        builder.append(this.blank(rest - 4));
        builder.append(columnSeperator());
        builder.append("\n");
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");
        /**
         * Build RuleContext Table Row 2
         */
        colWidth = headerWidth / 2;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.RULE_SET_TYPE.getTitle());
        rest = colWidth - 2 - LogTitle.RULE_SET_TYPE.getTitle().length();
        builder.append(this.blank(rest - 2));
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(dc.getFiredRuleSetInfo().getRuleSetType());
        rest = headerWidth - (colWidth + 2 + dc.getFiredRuleSetInfo().getRuleSetType().length());
        builder.append(this.blank(rest - 4));
        builder.append(columnSeperator());
        builder.append("\n");
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Build RuleContext Table Row 3
         */
        colWidth = headerWidth / 2;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.STUDY_NAME.getTitle());
        rest = colWidth - 2 - LogTitle.STUDY_NAME.getTitle().length();
        builder.append(this.blank(rest - 2));
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(dc.getFiredRuleSetInfo().getStudyName());
        rest = headerWidth - (colWidth + 2 + dc.getFiredRuleSetInfo().getStudyName().length());
        builder.append(this.blank(rest - 4));
        builder.append(columnSeperator());
        builder.append("\n");
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Build RuleContext Table Row 4
         */
        colWidth = headerWidth / 2;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.ORGANIZATION_NAME.getTitle());
        rest = colWidth - 2 - LogTitle.ORGANIZATION_NAME.getTitle().length();
        builder.append(this.blank(rest - 2));
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(dc.getFiredRuleSetInfo().getOrganizationName());
        rest = headerWidth
                        - (colWidth + 2 + dc.getFiredRuleSetInfo().getOrganizationName().length());
        builder.append(this.blank(rest - 4));
        builder.append(columnSeperator());
        builder.append("\n");
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Build RuleContext Table Row 5
         */
        colWidth = headerWidth / 2;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.ORGANIZATION_ROLE.getTitle());
        rest = colWidth - 2 - LogTitle.ORGANIZATION_ROLE.getTitle().length();
        builder.append(this.blank(rest - 2));
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(dc.getFiredRuleSetInfo().getRole());
        rest = headerWidth - (colWidth + 2 + dc.getFiredRuleSetInfo().getRole().length());
        builder.append(this.blank(rest - 4));
        builder.append(columnSeperator());
        builder.append("\n");
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Build RuleContext Table Row 6
         */
        colWidth = headerWidth / 2;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.TIME_OF_EXECUTION.getTitle());
        rest = colWidth - 2 - LogTitle.TIME_OF_EXECUTION.getTitle().length();
        builder.append(this.blank(rest - 2));
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(dc.getFiredRuleSetInfo().getExecutionDate().toString());
        rest = headerWidth
                        - (colWidth + 2 + dc.getFiredRuleSetInfo().getExecutionDate().toString()
                                        .length());
        builder.append(this.blank(rest - 4));
        builder.append(columnSeperator());
        builder.append("\n");
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Build Asserted Objects Line
         */
        /**
         * k = "Asserted Objects".length(); x = (headerWidth-k)/2;
         * 
         * builder.append(offSet); builder.append(columnSeperator());
         * builder.append(this.blank(x-1)); builder.append("Asserted Objects"); rest =
         * headerWidth-(x+k); builder.append(this.blank(rest-2)); builder.append(columnSeperator());
         * builder.append("\n"); builder.append(offSet); builder.append(columnSeperator());
         * builder.append(line(headerWidth-2)); builder.append(columnSeperator());
         * builder.append("\n");
         */

        /**
         * Build Asserted Objects Table Row 1
         */
        /**
         * List<Object> objects = dc.getAssertedObjects();
         * 
         * for(int s=0;s<objects.size();s++){ Object obj = objects.get(s); if(obj instanceof
         * AdverseEvent){ drawAdverseEvent(builder,offSet,headerWidth,(AdverseEvent)obj); } if(obj
         * instanceof Study){ drawStudy(builder,offSet,headerWidth,(Study)obj); } if(obj instanceof
         * StudyAgent){ drawStudyAgent(builder,offSet,headerWidth,(StudyAgent)obj); } }
         */
        drawRuleExecutionSummary(builder, offSet, headerWidth, dc);

        return builder.toString();

    }

    public String toXML(DecisionContext dc) {
        return null;
    }

    public void drawAdverseEvent(StringBuilder builder, String offSet, int headerWidth,
                    AdverseEvent ae) {
        int colWidth = headerWidth / 3;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.ADVERSE_EVENT.getTitle());
        int rest = colWidth - 2 - LogTitle.ADVERSE_EVENT.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.ID.getTitle());
        rest = colWidth - 2 - LogTitle.ID.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getIdFromAdverseEvent(ae));
        rest = colWidth - 2 - AttributeValueGetter.getIdFromAdverseEvent(ae).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Row 2
         */

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(blank(LogTitle.ADVERSE_EVENT.getTitle().length() - 1));
        rest = colWidth - 2 - LogTitle.ADVERSE_EVENT.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.GRADE.getTitle());
        rest = colWidth - 2 - LogTitle.GRADE.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getGradeFromAdverseEvent(ae));
        rest = colWidth - 2 - AttributeValueGetter.getGradeFromAdverseEvent(ae).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Row 3
         */

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(blank(LogTitle.ADVERSE_EVENT.getTitle().length() - 1));
        rest = colWidth - 2 - LogTitle.ADVERSE_EVENT.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.TERM.getTitle());
        rest = colWidth - 2 - LogTitle.TERM.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getTermFromAdverseEvent(ae));
        rest = colWidth - 2 - AttributeValueGetter.getTermFromAdverseEvent(ae).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Row 4
         */

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(blank(LogTitle.ADVERSE_EVENT.getTitle().length() - 1));
        rest = colWidth - 2 - LogTitle.ADVERSE_EVENT.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.CATEGORY.getTitle());
        rest = colWidth - 2 - LogTitle.CATEGORY.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getCategoryFromAdverseEvent(ae));
        rest = colWidth - 2 - AttributeValueGetter.getCategoryFromAdverseEvent(ae).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Row 5
         */

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(blank(LogTitle.ADVERSE_EVENT.getTitle().length() - 1));
        rest = colWidth - 2 - LogTitle.ADVERSE_EVENT.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.HOSPITALIZATION.getTitle());
        rest = colWidth - 2 - LogTitle.HOSPITALIZATION.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getHospitalizationFromAdverseEvent(ae));
        rest = colWidth - 2 - AttributeValueGetter.getHospitalizationFromAdverseEvent(ae).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Row 6
         */

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(blank(LogTitle.ADVERSE_EVENT.getTitle().length() - 1));
        rest = colWidth - 2 - LogTitle.ADVERSE_EVENT.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.EXPECTED.getTitle());
        rest = colWidth - 2 - LogTitle.EXPECTED.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getExpectedFromAdverseEvent(ae));
        rest = colWidth - 2 - AttributeValueGetter.getExpectedFromAdverseEvent(ae).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Row 7
         */

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(blank(LogTitle.ADVERSE_EVENT.getTitle().length() - 1));
        rest = colWidth - 2 - LogTitle.ADVERSE_EVENT.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.ATTRRIBUTION.getTitle());
        rest = colWidth - 2 - LogTitle.ATTRRIBUTION.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getAttributionFromAdverseEvent(ae));
        rest = colWidth - 2 - AttributeValueGetter.getAttributionFromAdverseEvent(ae).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

    }

    public void drawStudy(StringBuilder builder, String offSet, int headerWidth, Study study) {
        int colWidth = headerWidth / 3;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.STUDY.getTitle());
        int rest = colWidth - 2 - LogTitle.STUDY.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.ID.getTitle());
        rest = colWidth - 2 - LogTitle.ID.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getIdFromStudy(study));
        rest = colWidth - 2 - AttributeValueGetter.getIdFromStudy(study).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Row 2
         */

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(blank(LogTitle.STUDY.getTitle().length() - 1));
        rest = colWidth - 2 - LogTitle.STUDY.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.PHASE.getTitle());
        rest = colWidth - 2 - LogTitle.PHASE.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getPhaseFromStudy(study));
        rest = colWidth - 2 - AttributeValueGetter.getPhaseFromStudy(study).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

    }

    public void drawStudyAgent(StringBuilder builder, String offSet, int headerWidth,
                    StudyAgent studyAgent) {
        int colWidth = headerWidth / 3;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.STUDY_AGENT.getTitle());
        int rest = colWidth - 2 - LogTitle.STUDY_AGENT.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.ID.getTitle());
        rest = colWidth - 2 - LogTitle.ID.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getIdFromStudyAgent(studyAgent));
        rest = colWidth - 2 - AttributeValueGetter.getIdFromStudyAgent(studyAgent).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Row 2
         */

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(blank(LogTitle.STUDY_AGENT.getTitle().length() - 1));
        rest = colWidth - 2 - LogTitle.STUDY_AGENT.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.INVESTIGATIONAL_NEW_DRUG_INDICATOR.getTitle());
        rest = colWidth - 2 - LogTitle.INVESTIGATIONAL_NEW_DRUG_INDICATOR.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(AttributeValueGetter.getIndicatorFromStudyAgent(studyAgent));
        rest = colWidth - 2 - AttributeValueGetter.getIndicatorFromStudyAgent(studyAgent).length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

    }

    public void drawRuleExecutionSummary(StringBuilder builder, String offSet, int headerWidth,
                    DecisionContext dc) throws Exception {
        int k = "Exeution Summary".length();
        int x = (headerWidth - k) / 2;

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(x - 1));
        builder.append("Exeution Summary");
        int rest = headerWidth - (x + k);
        builder.append(this.blank(rest - 2));
        builder.append(columnSeperator());
        builder.append("\n");
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        int colWidth = headerWidth / 3;
        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.RULE_NAME.getTitle());
        rest = colWidth - 2 - LogTitle.RULE_NAME.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.CONDITION_MET.getTitle());
        rest = colWidth - 2 - LogTitle.CONDITION_MET.getTitle().length();
        builder.append(this.blank(rest - 2));

        builder.append(columnSeperator());
        builder.append(this.blank(2));
        builder.append(LogTitle.FIRED.getTitle());
        rest = colWidth - 2 - LogTitle.FIRED.getTitle().length();
        builder.append(this.blank(rest - 5));
        builder.append(columnSeperator());
        builder.append("\n");

        builder.append(offSet);
        builder.append(columnSeperator());
        builder.append(line(headerWidth - 2));
        builder.append(columnSeperator());
        builder.append("\n");

        /**
         * Row 2
         */
        dc.buildExecutionSummary();
        List<RuleExecutionStatus> res = dc.getExecutionSummary();
        for (int w = 0; w < res.size(); w++) {
            RuleExecutionStatus r = res.get(w);

            builder.append(offSet);
            builder.append(columnSeperator());
            builder.append(this.blank(2));
            builder.append(r.getRuleName());
            rest = colWidth - 2 - r.getRuleName().length();
            builder.append(this.blank(rest - 2));

            builder.append(columnSeperator());
            builder.append(this.blank(2));
            builder.append(r.isConditionMet());
            rest = colWidth - 2 - String.valueOf(r.isConditionMet()).length();
            builder.append(this.blank(rest - 2));

            builder.append(columnSeperator());
            builder.append(this.blank(2));
            builder.append(r.isFired());
            rest = colWidth - 2 - String.valueOf(r.isFired()).length();
            builder.append(this.blank(rest - 5));
            builder.append(columnSeperator());
            builder.append("\n");

            builder.append(offSet);
            builder.append(columnSeperator());
            builder.append(line(headerWidth - 2));
            builder.append(columnSeperator());
            builder.append("\n");

        }

    }

    /**
     * ________________________________________________________
     * 
     * @param dc
     * @return ________________________________________________________
     */

    private int getTableWidth(DecisionContext dc) {
        AssertedObjectTableWidthFinder aw = new AssertedObjectTableWidthFinder();
        int i = 0;
        int j = dc.getFiredRuleSetInfo().getMaxWidth();
        // int k = aw.maxWidth(dc.getAssertedObjects());

        if (j > i) {
            i = j;
        }
        /**
         * if(k>i){ i=k; }
         */
        return i * 3;
    }

    private String line(int width) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < width + 1; i++) {
            builder.append("_");
        }
        return builder.toString();
    }

    private String columnSeperator() {
        return "|";
    }

    private String blank(int width) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < width + 1; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        LogObjectFormatter lof = new LogObjectFormatter();
        DecisionContext dc = new DecisionContext();
        /**
         * Build Fired RuleSetInfo Object
         */
        FiredRuleSetInfo firedRuleSetInfo = new FiredRuleSetInfo("XYZ");
        firedRuleSetInfo.setOrganizationName("Duke Cancer Center");
        firedRuleSetInfo.setRole("Sponsor");
        firedRuleSetInfo.setRuleSetName("Asses AE Rules");
        firedRuleSetInfo.setRuleSetType("Sponsor defined study level rules");
        firedRuleSetInfo.setStudyName("My test case Study");

        List<String> list = new ArrayList<String>();
        list.add("Rule 1");
        list.add("Rule 2");
        list.add("Rule 3");
        list.add("Rule 4");

        firedRuleSetInfo.setRuleNames(list);

        dc.setFiredRuleSetInfo(firedRuleSetInfo);

        /**
         * Build the list of the fired rules
         */
        List<String> firedRuleNames = new ArrayList<String>();
        firedRuleNames.add("Rule 3");

        dc.setFiredRuleNames(firedRuleNames);

        /**
         * Now Build the TO Be Asserted Objects
         */

        AdverseEvent ae = new AdverseEvent();
        ae.setGrade(Grade.SEVERE);
        ae.setId(67890);
        ae.setHospitalization(Hospitalization.NO);
        ae.setAttributionSummary(Attribution.POSSIBLE);
        CtcTerm ctcTerm = new CtcTerm();
        ctcTerm.setTerm("Some term");
        CtcCategory cc = new CtcCategory();
        cc.setName("version 3.0");
        ctcTerm.setCategory(cc);
        ae.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
        ae.setExpected(new Boolean(true));

        Study study = new Study();
        study.setId(567);
        study.setPhaseCode("Phase I");

        StudyAgent sa = new StudyAgent();
        // sa.setInvestigationalNewDrugIdentifier("yes");

        Object[] objects = new Object[] { ae, study, sa };

        List<Object> objs = Arrays.asList(objects);

        dc.setAssertedObjects(objs);

        try {
            System.out.println(lof.toTextTable(dc));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
