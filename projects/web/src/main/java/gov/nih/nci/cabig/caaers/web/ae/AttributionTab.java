package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.SelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.CollectionSelectField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * @author Rhett Sutphin
 */
public class AttributionTab extends AeTab {
    private static final SelectField BASE_FIELD = new CollectionSelectField(
        null, null, true, Arrays.asList(Attribution.values()), "name", null);

    protected AttributionTab() {
        super("Attribution", "Attribution", "ae/attribution");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        List<AttributionBlock> blocks = createBlocks(command.getAeReport());
        for (AttributionBlock block : blocks) {
            for (InputFieldGroup group : block.getRows()) {
                map.addInputFieldGroup(group);
            }
        }
        return map;
    }

    private List<AttributionBlock> createBlocks(AdverseEventReport report) {
        List<AttributionBlock> blocks = new ArrayList<AttributionBlock>();
        blocks.add(new AttributionBlock("Course", "Course",
            createGroups(CauseAndAttributionAccessor.COURSE_AGENT, report)));
        blocks.add(new AttributionBlock("Concomitant medication",
            createGroups(CauseAndAttributionAccessor.CONCOMITANT_MEDICATION, report)));
        return blocks;
    }

    @Override
    public Map<String, Object> referenceData(AdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        refdata.put("blocks", createBlocks(command.getAeReport()));
        return refdata;
    }

    public class AttributionBlock {
        private String displayName;
        private List<InputFieldGroup> rows;

        public AttributionBlock(String singularName, String pluralName, List<InputFieldGroup> rows) {
            this.displayName = rows.size() == 1 ? singularName : pluralName;
            this.rows = rows;
        }

        public AttributionBlock(String displayName, List<InputFieldGroup> rows) {
            this(displayName, displayName + 's', rows);
        }

        public String getDisplayName() {
            return displayName;
        }

        public List<InputFieldGroup> getRows() {
            return rows;
        }
    }

    private static <C extends DomainObject, A extends AdverseEventAttribution<C>>
    List<InputFieldGroup> createGroups(
        CauseAndAttributionAccessor<C, A> accessor, AdverseEventReport report
    ) {
        List<InputFieldGroup> groups = new ArrayList<InputFieldGroup>();
        List<C> causes = accessor.getCauseList(report);
        for (int c = 0 ; c < causes.size(); c++) {
            DefaultInputFieldGroup newGroup
                = new DefaultInputFieldGroup(accessor.getKey() + c);
            newGroup.setDisplayName(accessor.getDisplayName(causes.get(c)));
            for (int a = 0; a < report.getAdverseEvents().size(); a++) {
                newGroup.getFields().add(
                    new AttributionField(accessor.getKey(), a, c));
            }
            groups.add(newGroup);
        }
        return groups;
    }

    private static class AttributionField extends DefaultSelectField {
        public AttributionField(String groupKey, int aeIndex, int causeIndex) {
            super(null, null, BASE_FIELD.isRequired(), BASE_FIELD.getOptions());
            setPropertyName(createPropertyName(groupKey, aeIndex, causeIndex));
        }

        private String createPropertyName(String groupKey, int aeIndex, int causeIndex) {
            return new StringBuilder()
                .append("attributionMap[").append(groupKey).append("][")
                .append(aeIndex).append("][")
                .append(causeIndex).append(']').toString();
        }
    }
}
