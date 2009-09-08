package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.UnsatisfiedProperty;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.SchedulerService;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.web.fields.BasePropertyInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public abstract class AeTab extends TabWithFields<ExpeditedAdverseEventInputCommand> {

    private static final Log logger = LogFactory.getLog(AeTab.class);
    
    protected static final String MANDATORY_FIELD_ATTR = "mandatory";
    protected ExpeditedReportTree expeditedReportTree;
    protected ReportRepository reportRepository;
    protected EvaluationService evaluationService;
    protected SchedulerService schedulerService;
    protected HashMap<String, Boolean> emptyFieldNameMap;

    public AeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    @Override
    public final InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        AeInputFieldCreator creator = new AeInputFieldCreator(command);
        createFieldGroups(creator, command);
        return creator.getMap();
    }
    

    /**
     * Template method for subclasses to instantiate their fields via the
     * {@link AeInputFieldCreator}.
     */
    protected abstract void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command);

    /**
     * Will also update the InputField mandatory flag.
     */
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request , ExpeditedAdverseEventInputCommand command) {
        Map<String, Object> refData = super.referenceData( request,command);
        Object fieldGroups = refData.get("fieldGroups");
        populateMandatoryFlag(fieldGroups, command, refData);
        populateEmptyMandatoryFieldMap(fieldGroups, command);
        request.setAttribute("empties", emptyFieldNameMap);
        WebUtils.synchronzeErrorFields(emptyFieldNameMap, command.getRulesErrors());
        String warning = generateWarningMessage(command); 
        //put the warning message.
        if(warning != null){
        	refData.put("warningMessage", warning);
        }
        return refData;
    }
    
    /**
     * Check if adverse event is modified, if so, generate warning message.
     * @param command
     * @return
     */
    public String generateWarningMessage(ExpeditedAdverseEventInputCommand command){
    	if(!command.getAeReport().getModifiedAdverseEvents().isEmpty()){
    		return getMessage("instruction_ae_modification_detected", "Adverse events modified, please got to reveiwe and report page", new Object[]{command.getStudy().getId().toString(), command.getParticipant().getId().toString(), command.getAdverseEventReportingPeriod().getId().toString()});
    	}
    	return null;
    }
    
    /**
     * This method will populate the empty mandatory field details, so that the boxes
     * containing those fields can be opened in the UI.
     */
    protected void populateEmptyMandatoryFieldMap(Object fieldGroups, ExpeditedAdverseEventInputCommand command){

        Map<String, InputFieldGroup> groupMap = (Map<String, InputFieldGroup>) fieldGroups;
        if (groupMap == null) return;
        
        emptyFieldNameMap = new HashMap<String, Boolean>();
        
        for (InputFieldGroup group : groupMap.values()) {
            for (InputField field : group.getFields()) {
            	
            	//for every required or mandatory field, check if value is provided.
            	if(field.isRequired() || field.getAttributes().get(MANDATORY_FIELD_ATTR) != null){
            		 List<UnsatisfiedProperty> unsatisfiedProps = expeditedReportTree.verifyPropertiesPresent(field.getPropertyName().substring(9), command.getAeReport());
            		 for(UnsatisfiedProperty unsatisfiedProperty : unsatisfiedProps){
            			 String unsatisfiedPropertyName = unsatisfiedProperty.getBeanPropertyName();
            			 emptyFieldNameMap.put("aeReport." + unsatisfiedPropertyName.substring(0, unsatisfiedPropertyName.indexOf("].") + 1), Boolean.TRUE);
            		 }
            	}
            }
        }
    }
    
    /**
     * Will populate the mandatory flag.
     */
    @SuppressWarnings("unchecked")
    protected void populateMandatoryFlag(Object fieldGroups, ExpeditedAdverseEventInputCommand command, Map<String, Object> refData) {
        // TODO: need to see how to manage (this or that) kind mandatory fields
        // TODO: Why not this we handle in createFields() of every tab, so that the looping through
        // the fields
        // here can be avoided.


        Map<String, InputFieldGroup> groupMap = (Map<String, InputFieldGroup>) fieldGroups;
        if (groupMap == null) return;

        
        for (InputFieldGroup group : groupMap.values()) {
            for (InputField field : group.getFields()) {
                if (isMandatory(command.getMandatoryProperties(), field)) {
                    field.getAttributes().put(MANDATORY_FIELD_ATTR, true);

                    if (field.getCategory() == InputField.Category.COMPOSITE) {
                        for (InputField subField : CompositeField.getSubfields(field)) {
                            if (isMandatory(command.getMandatoryProperties(), subField)) {
                                subField.getAttributes().put(MANDATORY_FIELD_ATTR, true);
                            }
                        }
                    }
                }
            }

        }

    }

    /**
     * Tells whether the given field is mandatory. In case of Composite fields, the given field
     * (parent) will be marked mandatory if any of its subfields are mandatory.
     *
     * @param field
     * @return
     */
    private boolean isMandatory(MandatoryProperties mandatoryProps, InputField field) {
        if (mandatoryProps == null) return false;
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
        for(ExpeditedReportSection section : section()){
        	if(sections.contains(section)) return true;
        }
        return false;
    }

    public boolean hasEmptyMandatoryFields(ExpeditedAdverseEventInputCommand command, HttpServletRequest request) {
        MandatoryProperties props = command.getMandatoryProperties();
        if (props == null) return false;

        for(ExpeditedReportSection section : section()){
        	TreeNode node = expeditedReportTree.getNodeForSection(section);
            if (node == null) continue;
            List<UnsatisfiedProperty> unsatisfied = props.getUnsatisfied(node, command.getAeReport());
            if(!unsatisfied.isEmpty()) return true;
        }
        return false;
    }
    
    public boolean hasMandatoryFields(ExpeditedAdverseEventInputCommand command , HttpServletRequest request){
    	 MandatoryProperties props = command.getMandatoryProperties();
         if (props == null) return false;
         for(ExpeditedReportSection section : section()){
         	TreeNode node = expeditedReportTree.getNodeForSection(section);
            if (node == null) continue;
            if(props.isAnyMandatory(node)) return true;
         }
         return false;
    }

    public abstract ExpeditedReportSection[] section();

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        command.setRulesErrors(new HashMap<String, Boolean>());
        super.validate(command, commandBean, fieldGroups, errors);
        
        if (!errors.hasErrors() && isAssociatedToBusinessRules()) {
            ValidationErrors validationErrors = evaluationService.validateReportingBusinessRules(command.getAeReport(), section());

            for (ValidationError vError : validationErrors.getErrors()) {
                
                if (command.isErrorApplicable(vError.getFieldNames())) {
                    
                    if (vError.getFieldNames() == null) {
                        errors.reject(vError.getCode(), vError.getMessage());
                        continue;
                    }
                    
                    // command.setRulesErrors(new HashMap<String, Boolean>());
                    WebUtils.rejectErrors(errors, vError);
                    WebUtils.populateErrorFieldNames(command.getRulesErrors(), vError.getFieldNames());
                    WebUtils.populateErrorFieldNames(command.getRulesErrors(), errors);
                }
            }
        }
    }

    public CompositeField createTimeField(String baseProperty, String displayName){
    	InputField hrField = InputFieldFactory.createTextField("hourString", "", FieldValidator.HOUR_VALIDATOR);
    	InputField mmField = InputFieldFactory.createTextField("minuteString"," ", FieldValidator.MINUTE_VALIDATOR);
    	LinkedHashMap< Object, Object> amPmOption = new LinkedHashMap<Object, Object>();
    	amPmOption.put("0", "AM");
    	amPmOption.put("1", "PM");
    	InputField amPmField = InputFieldFactory.createSelectField("type", "",false, amPmOption);
    	InputFieldAttributes.setSize(hrField, 2);
    	InputFieldAttributes.setSize(mmField, 2);

    	return new CompositeField(baseProperty, new DefaultInputFieldGroup(null,displayName).addField(hrField).addField(mmField).addField(amPmField));
    }

    public boolean isAssociatedToBusinessRules(){
    	for(ExpeditedReportSection section : section()){
    		if(section.isAssociatedToBusinessRules()) return true;
    	}
    	return false;
    }

    // //// CONFIGURATION

    public ExpeditedReportTree getExpeditedReportTree() {
        return expeditedReportTree;
    }

    public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
        this.expeditedReportTree = expeditedReportTree;
    }

    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void setSchedulerService(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
    
    
    // ////

    protected class AeInputFieldCreator {
        protected final ExpeditedAdverseEventInputCommand command;

        private BeanWrapper wrappedReport;

        private InputFieldGroupMap map;

        protected AeInputFieldCreator(ExpeditedAdverseEventInputCommand command) {
            this.command = command;
            this.wrappedReport = new BeanWrapperImpl(command.getAeReport());
            this.map = new InputFieldGroupMap();
        }

        /**
         * Add a RepeatingFieldGroup to the groups for this tab. Note that the listProperty should
         * be relative to {@link gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport}
         * <em>not</em> the command. That is, it should not begin with <code>aeReport.</code>.
         */
        public final AeInputFieldCreator createRepeatingFieldGroup(String basename, String listProperty, InputField... fields) {
            return createRepeatingFieldGroup(basename, listProperty, null, fields);
        }

        public final AeInputFieldCreator createRepeatingFieldGroup(String basename, String listProperty, RepeatingFieldGroupFactory.DisplayNameCreator nameCreator, InputField... fields) {           RepeatingFieldGroupFactory factory = new RepeatingFieldGroupFactory(basename, "aeReport." + listProperty);
            TreeNode listNode = expeditedReportTree.find(listProperty);
            if (listNode == null) {
                throw new CaaersSystemException(listProperty + " does not appear in the expedited report tree");
            }
            for (InputField field : fields) {
                List<String> props = field.getCategory() == InputField.Category.COMPOSITE ? CompositeField.getEffectivePropertyNames(field): Arrays.asList(field.getPropertyName());
                for (String prop : props) {
                    setMandatoryAttribute(listNode.find(prop), field);
                    setHelpKeyAttribute(field);
                }
                factory.addField(field);
            }

            Collection<?> list = (Collection<?>) wrappedReport.getPropertyValue(listProperty);
            int initialCount = list == null ? 0 : list.size();

            if (nameCreator != null) {
                factory.setDisplayNameCreator(nameCreator);
            }

            map.addRepeatingFieldGroupFactory(factory, initialCount);
            return this;
        }

        /**
         * Add a normal, single group of fields to this tab. Note that the fields' propertyNames
         * should be relative to {@link gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport}
         * <em>not</em> the command. That is, they should not begin with <code>aeReport.</code>.
         */
        public final AeInputFieldCreator createFieldGroup(String name, InputField... fields) {
            return createFieldGroup(name, null, fields);
        }

        public final AeInputFieldCreator createFieldGroup(String name, String displayName, InputField... fields) {
            return createFieldGroup(name, displayName, null, fields);
        }

        public final AeInputFieldCreator createFieldGroup(String name, String displayName, String baseProperty, InputField... fields) {
            BasePropertyInputFieldGroup group = new BasePropertyInputFieldGroup(name, displayName, "aeReport" + (baseProperty == null ? "" : '.' + baseProperty));
            for (InputField field : fields) {
                String treePropName = (baseProperty == null ? "" : baseProperty + '.') + field.getPropertyName();
                setMandatoryAttribute(expeditedReportTree.find(treePropName), field);
                setHelpKeyAttribute(field);
                group.addField(field);
            }
            map.addInputFieldGroup(group);
            return this;
        }

        public InputFieldGroupMap getMap() {
            return map;
        }

        private void setMandatoryAttribute(TreeNode fieldNode, InputField field) {
            if (command.getMandatoryProperties() != null) {
                if (command.getMandatoryProperties().isMandatory(fieldNode)) {
                    field.getAttributes().put(MANDATORY_FIELD_ATTR, true);
                }
            }
        }

        /**
         * Directly add an input field group. This group should not contain any fields which
         * represent properties in the command's aeReport.
         *
         * @param group
         */
        public void addUnprocessedFieldGroup(InputFieldGroup group) {
            map.addInputFieldGroup(group);
        }
    }

    protected final class SimpleNumericDisplayNameCreator implements RepeatingFieldGroupFactory.DisplayNameCreator {
        private String heading;

        public SimpleNumericDisplayNameCreator(String heading) {
            this.heading = heading;
        }

        public String createDisplayName(int index) {
            return new StringBuilder(heading).append(' ').append(index + 1).toString();
        }
    }
}
