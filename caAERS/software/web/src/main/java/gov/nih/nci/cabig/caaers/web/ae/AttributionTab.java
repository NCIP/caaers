package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rhett Sutphin
 */
public class AttributionTab extends AeTab {
    private static final Map<Object, Object> ATTRIBUTION_OPTIONS = collectAttributionOptions();

    protected AttributionTab() {
        super("Attribution", ExpeditedReportSection.ATTRIBUTION_SECTION.getDisplayName(),"ae/attribution");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        List<AttributionBlock> blocks = createBlocks(command.getAeReport());
        for (AttributionBlock block : blocks) {
            for (InputFieldGroup group : block.getRows()) {
                creator.addUnprocessedFieldGroup(group);
            }
        }
    }

    private List<AttributionBlock> createBlocks(ExpeditedAdverseEventReport report) {
        List<AttributionBlock> blocks = new ArrayList<AttributionBlock>();
        blocks.add(new AttributionBlock("Disease", createGroups(CauseAndAttributionAccessor.DISEASE, report)));
        blocks.add(new AttributionBlock("Study Agent", "Agents", createGroups(CauseAndAttributionAccessor.COURSE_AGENT, report)));
        blocks.add(new AttributionBlock("Surgery", createGroups(CauseAndAttributionAccessor.SURGERY, report)));
        blocks.add(new AttributionBlock("Radiation", createGroups(CauseAndAttributionAccessor.RADIATION, report)));
        blocks.add(new AttributionBlock("Medical device", createGroups(CauseAndAttributionAccessor.DEVICE, report)));
        blocks.add(new AttributionBlock("Concomitant medication", createGroups(CauseAndAttributionAccessor.CONCOMITANT_MEDICATION, report)));
        blocks.add(new AttributionBlock("Other cause", createGroups(CauseAndAttributionAccessor.OTHER_CAUSE, report)));
        return blocks;
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
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

    private static <D extends DomainObject, A extends AdverseEventAttribution<D>> List<InputFieldGroup> createGroups(CauseAndAttributionAccessor<D, A> accessor, ExpeditedAdverseEventReport report) {
        List<InputFieldGroup> groups = new ArrayList<InputFieldGroup>();
        List<D> causes = accessor.getCauseList(report);
        for (int c = 0; c < causes.size(); c++) {
            DefaultInputFieldGroup newGroup = new DefaultInputFieldGroup(accessor.getKey() + c);
            newGroup.setDisplayName(accessor.getDisplayName(causes.get(c)));

            if (accessor.getDisplayName(causes.get(c)) == CauseAndAttributionAccessor.DEFAULT_NAME) continue;
            for (int a = 0; a < report.getAdverseEvents().size(); a++) {
                newGroup.getFields().add(createAttributionField(" attribution for " + newGroup.getDisplayName() , 
                		accessor.getKey(), a, c, report.isAttributionRequired()));
            }
            groups.add(newGroup);
        }
        return groups;
    }

    private static InputField createAttributionField(String displayName, String groupKey, int aeIndex, int causeIndex, boolean required) {
        String propertyName = new StringBuilder().append("attributionMap[").append(groupKey).append("][").append(aeIndex).append("][").append(causeIndex).append(']').toString();
        return InputFieldFactory.createSelectField(propertyName, displayName, required, ATTRIBUTION_OPTIONS);
    }

    private static Map<Object, Object> collectAttributionOptions() {
        Map<Object, Object> map = new LinkedHashMap<Object, Object>();
        map.put("", "Please select");
        map.putAll(WebUtils.collectOptions(Arrays.asList(Attribution.values()), "name",null));
        return map;
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.ATTRIBUTION_SECTION};
    }
    
    
    
    @Override
    public boolean hasEmptyMandatoryFields(ExpeditedAdverseEventInputCommand command,HttpServletRequest request) {
    	boolean hasEmptyFields = super.hasEmptyMandatoryFields(command, request);
    	if(command.getAeReport().getId() != null && (request.getParameter("subview") == null)){
    		try {
				ValidationErrors validationErrors = evaluationService.validateReportingBusinessRules(command.getAeReport(), section());
				hasEmptyFields |= (validationErrors.containsErrorWithCode("ATT_BR1_ERR") || validationErrors.containsErrorWithCode("AER_BR7_ERR"));
			} catch (Exception e) {
				
			}
    	}
    	
    	return hasEmptyFields;
    }
}
