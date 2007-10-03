package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.UnsatisfiedProperty;
import gov.nih.nci.cabig.caaers.service.ReportService;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * @author Rhett Sutphin
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public abstract class AeTab extends TabWithFields<ExpeditedAdverseEventInputCommand> {

    private ExpeditedReportTree expeditedReportTree;
    protected ReportService reportService;

    public AeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    /**
     * Will also update the InputField mandatory flag.
     */
    @Override
    public Map<String, Object> referenceData(ExpeditedAdverseEventInputCommand command) {
        Map<String, Object> refData = super.referenceData(command);
        Object fieldGroups = refData.get("fieldGroups");
        populateMandatoryFlag(fieldGroups, command, refData);
        return refData;
    }

    /**
     * Will populate the mandatory flag.
     */
    @SuppressWarnings("unchecked")
    private void populateMandatoryFlag(Object fieldGroups, ExpeditedAdverseEventInputCommand command, Map<String, Object> refData) {
        //TODO: need to see how to manage (this or that) kind mandatory fields
        //TODO: Why not this we handle in createFields() of every tab, so that the looping through the fields
        // here can be avoided.
        if (!isMandatory(command)) return;

        Map<String, InputFieldGroup> groupMap = (Map<String, InputFieldGroup>) fieldGroups;
        if (groupMap == null) return;

        for (InputFieldGroup group : groupMap.values()) {
            for (InputField field : group.getFields()) {
                field.setMandatory(isMandatory(command.getMandatoryProperties(), field));
            }
        }
    }

    /**
     * Tells whether the given field is mandatory.
     * In case of Composite fields, the given field (parent) will be marked mandatory if any of its subfields
     * are mandatory.
     *
     * @param field
     * @return
     */
    private boolean isMandatory(MandatoryProperties mandatoryProps, InputField field) {
        boolean mandatory = mandatoryProps.isMandatory(field.getPropertyName().replace("aeReport.", ""));
        if (field.getCategory() == InputField.Category.COMPOSITE) {
            for (InputField subfield : CompositeField.getSubfields(field))
                mandatory |= isMandatory(mandatoryProps, subfield);
        }
        return mandatory;
    }

    /**
     * Check's whether this tab is mandatory
     */
    public boolean isMandatory(ExpeditedAdverseEventInputCommand command) {
        Collection<ExpeditedReportSection> sections = command.getMandatorySections();
        if (sections == null || sections.isEmpty()) return false;
        return sections.contains(section());
    }

    public boolean hasEmptyMandatoryFields(ExpeditedAdverseEventInputCommand command) {
        MandatoryProperties props = command.getMandatoryProperties();
        if (props == null) return false;

        TreeNode node = expeditedReportTree.getNodeForSection(section());
        if (node == null) return false;

        List<UnsatisfiedProperty> unsatisfied = props.getUnsatisfied(node, command.getAeReport());
        return !unsatisfied.isEmpty();
    }

    public abstract ExpeditedReportSection section();

    public ExpeditedReportTree getExpeditedReportTree() {
        return expeditedReportTree;
    }

    ////// CONFIGURATION

    public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
        this.expeditedReportTree = expeditedReportTree;
    }

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }
}
