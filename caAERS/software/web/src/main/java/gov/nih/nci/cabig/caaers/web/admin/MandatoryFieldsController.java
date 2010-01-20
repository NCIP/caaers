package gov.nih.nci.cabig.caaers.web.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.CaaersFieldDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.CaaersFieldsTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.CaaersTab;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;
import gov.nih.nci.cabig.caaers.web.CaaersFieldConfigurationManager;
import gov.nih.nci.cabig.caaers.web.CaaersFieldConfigurationManagerFactory;
import gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Sameer Sawant
 */
public class MandatoryFieldsController extends SimpleFormController {

	private CaaersFieldsTree caaersFieldsTree;
	private CaaersFieldDefinitionDao caaersFieldDefinitionDao;
	private CaaersFieldConfigurationManagerFactory caaersFieldConfigurationManagerFactory;
	
    public MandatoryFieldsController() {
        setFormView("admin/mandatory_fields");
        setBindOnNewForm(true);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	MandatoryFieldsCommand command = new MandatoryFieldsCommand(caaersFieldDefinitionDao);
    	//reconcileMandatoryFields(command.getMandatoryFields(), caaersFieldsTree.getNodeForTab(CaaersTab.CAPTURE_ADVERSE_EVENTS_TAB));
    	//command.initializeMandatoryFieldMap();
        return command;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
                    Object cmd, BindException errors) throws Exception {
    	// If there are no binding errors then save the updated values of CaaersFieldDefinition
    	MandatoryFieldsCommand command = (MandatoryFieldsCommand) cmd;
    	// If there are no erros then save the CaaersFieldsDefinitions list
    	if(!errors.hasErrors()){
    		for(CaaersFieldDefinition cfd: command.getMandatoryFields())
    			caaersFieldDefinitionDao.save(cfd);
    		// reinitialize caaersFieldConfigurationManager
    		CaaersFieldConfigurationManager caaersFieldConfigurationManager = caaersFieldConfigurationManagerFactory.getCaaersFieldConfigurationManager();
    		caaersFieldConfigurationManager.initializeConfigurationManager();
    	}
    	Map map = this.referenceData(request, command, errors);
        map.putAll(errors.getModel());
    	
        ModelAndView modelAndView = new ModelAndView(getFormView(), map);
        return modelAndView.addObject("updated", true);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object cmd,
                    final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        MandatoryFieldsCommand command = (MandatoryFieldsCommand) cmd;
        reconcileMandatoryFields(command.getMandatoryFields(), caaersFieldsTree.getNodeForTab(CaaersTab.CAPTURE_ADVERSE_EVENTS_TAB));
    	command.initializeMandatoryFieldMap();
        
        Map<String, InputFieldGroup> fieldMap;
        fieldMap = new LinkedHashMap<String, InputFieldGroup>();
        populateFieldMap(command, fieldMap, caaersFieldsTree.getNodeForTab(CaaersTab.CAPTURE_ADVERSE_EVENTS_TAB));
        refDataMap.put("fieldGroups", fieldMap);
        return refDataMap;
    }
    
    /**
     * Does the following:- Remove orphaned fields from existing mandatory field list. Add newly
     * added fields to existing mandatory field list.
     */
    public void reconcileMandatoryFields(List<CaaersFieldDefinition> mfList, TreeNode node) {
        // map consisting values from existing mandatory fields
        HashMap<String, CaaersFieldDefinition> existingFieldMap = new HashMap<String, CaaersFieldDefinition>();
        for (CaaersFieldDefinition mf : mfList) {
            existingFieldMap.put(mf.getFieldPath(), mf);
        }
        // newly calculated list
        List<CaaersFieldDefinition> mfListNew = new ArrayList<CaaersFieldDefinition>();

        populateMandatoryFields(mfListNew, node);
        for (CaaersFieldDefinition mf : mfListNew) {
            if (existingFieldMap.remove(mf.getFieldPath()) == null) {
                mfList.add(mf); // add this, this is a new field (may be a new release, not existed
                // while saving report definition)
            }
        }
        for (CaaersFieldDefinition mf : existingFieldMap.values()) {
            // these fields got removed (in new release)
            mfList.remove(mf);
        }
    }
    
    protected void populateMandatoryFields(List<CaaersFieldDefinition> mfList, TreeNode node) {
        if (StringUtils.isNotEmpty(node.getPropertyPath())) {
            CaaersFieldDefinition mf = new CaaersFieldDefinition(AdverseEventCaptureTab.class.getName(), node.getPropertyPath());
            mfList.add(mf);
        }
        if (node.getChildren() != null) {
            for (TreeNode n : node.getChildren())
                populateMandatoryFields(mfList, n);
        }
    }
    
    /**
     * Populates the fields, the key of the map will be qualified name of the parent. Display name
     * of the field will be display name of the node. In case if the node does not have a display
     * name, the display name of the parent will be used instead.
     */
    //protected void populateFieldMap(MandatoryFieldsCommand command, Map<String, InputFieldGroup> map, TreeNode node) {
    protected void populateFieldMap(MandatoryFieldsCommand command, Map<String, InputFieldGroup> map, TreeNode node){
        // only add leaf nodes in the filed map. (others are just sections)
    	if(node.isLeaf()){
            String key = node.getParent().getQualifiedDisplayName();
            InputFieldGroup group = map.get(key);
            if (group == null) {
                group = new DefaultInputFieldGroup(key);
                map.put(key, group);
            }
            List<InputField> fields = group.getFields();
            
            String displayName = node.getDisplayName();
            String path = node.getPropertyPath();
            if (StringUtils.isEmpty(path)) return;
            int index = command.getMandatoryFieldMap().get(path);
            if (StringUtils.isEmpty(displayName)) displayName = node.getParent().getDisplayName();

            fields.add(InputFieldFactory.createSelectField("mandatoryFields["+ index + "].mandatory", displayName, false, WebUtils.collectOptions(Arrays.asList(Mandatory.values()), "name", "displayName")));
        } else {
            // add children of this node in the map
            for (TreeNode n : node.getChildren())
                populateFieldMap(command, map, n);
        }

    }

    @Required
    public void setCaaersFieldsTree(CaaersFieldsTree caaersFieldsTree){
    	this.caaersFieldsTree = caaersFieldsTree;
    }
    
    @Required
    public void setCaaersFieldDefinitionDao(CaaersFieldDefinitionDao caaersFieldDefinitionDao){
    	this.caaersFieldDefinitionDao = caaersFieldDefinitionDao;
    }
    
    @Required 
    public void setCaaersFieldConfigurationManagerFactory(CaaersFieldConfigurationManagerFactory caaersFieldConfigurationManagerFactory){
    	this.caaersFieldConfigurationManagerFactory = caaersFieldConfigurationManagerFactory;
    }
}
