package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ReportMandatoryFieldDefinitionTab extends TabWithFields<ReportDefinitionCommand> {
	private ExpeditedReportTree reportTree;
    public ReportMandatoryFieldDefinitionTab(){
    	super("Mandatory Fields", "Mandatory Fields Configuration","rule/notification/reportMandatoryFieldTab");
    	reportTree = new ExpeditedReportTree();

    }

    @Override
    public Map<String, Object> referenceData(ReportDefinitionCommand command) {
    	Map<String, Object> refdata =  super.referenceData(command);
    	refdata.put("reportTree", reportTree);
    	return refdata;
    }

    /**
     * Populates the fields, the key of the map will be qualified name of the parent.
     * Display name of the field will be display name of the node. In case if the node does not have
     * a display name, the display name of the parent will be used instead.
     */
    public void populateFieldMap(ReportDefinitionCommand command,
    		Map<String, InputFieldGroup> map, TreeNode node) {
    	//only add leaf nodes in the filed map. (others are just sections)
    	if (node.isLeaf()) {
			String key = node.getParent().getQualifiedDisplayName();
			InputFieldGroup group = map.get(key);
			if (group == null) {
				group = new DefaultInputFieldGroup(key);
				map.put(key, group);
			}
			List<InputField> fields = group.getFields();

			String displayName = node.getDisplayName();
			String path = node.getPropertyPath();
			if(StringUtils.isEmpty(path)) return;
			int index = command.getMandatoryFieldMap().get(path);
			if (StringUtils.isEmpty(displayName))	displayName = node.getParent().getDisplayName();
			fields.add(InputFieldFactory.createCheckboxField("reportDefinition.mandatoryFields["+index+"].mandatory",displayName));
		} else {
			//add children of this node in the map
			for (TreeNode n : node.getChildren())
				populateFieldMap(command, map, n);
		}

    }

    @Override
	public Map<String, InputFieldGroup> createFieldGroups(ReportDefinitionCommand command) {
    	Map<String, InputFieldGroup> fieldMap;
    	fieldMap = new LinkedHashMap<String, InputFieldGroup>();
    	populateFieldMap(command, fieldMap, reportTree);
		return fieldMap;
	}
}