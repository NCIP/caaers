package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createSelectField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createPastDateField;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import static gov.nih.nci.cabig.caaers.web.utils.WebUtils.collectOptions;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ion C. Olaru
 */

public class StudyInterventionsTab extends AeTab {
    private static final Log log = LogFactory.getLog(StudyInterventionsTab.class);
    protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    Map<String, String> methodNameMap = new HashMap<String, String>();
    private ConfigProperty configurationProperty;
    private static final String STUDY_INTERVENTION_SURGERY = "surgery";
    private static final String STUDY_INTERVENTION_DEVICE = "device";
    private static final String STUDY_INTERVENTION_RADIATION = "radiation";
    private static final String STUDY_INTERVENTION_AGENT = "agent";

    public StudyInterventionsTab() {
        super("Study Interventions", ExpeditedReportSection.STUDY_INTERVENTIONS.getDisplayName(), "ae/studyInterventions");
        methodNameMap.put("add" + STUDY_INTERVENTION_SURGERY, "addSurgery");
        methodNameMap.put("remove" + STUDY_INTERVENTION_SURGERY, "removeSurgery");        
        methodNameMap.put("add" + STUDY_INTERVENTION_RADIATION, "addRadiation");
        methodNameMap.put("remove" + STUDY_INTERVENTION_RADIATION, "removeRadiation");        
        methodNameMap.put("add" + STUDY_INTERVENTION_DEVICE, "addDevice");
        methodNameMap.put("remove" + STUDY_INTERVENTION_DEVICE, "removeDevice");        
        methodNameMap.put("add" + STUDY_INTERVENTION_AGENT, "addAgent");
        methodNameMap.put("remove" + STUDY_INTERVENTION_AGENT, "removeAgent");        
    }
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request,ExpeditedAdverseEventInputCommand command) {
    	Map<String, Object> refData =  super.referenceData(request, command);
    	refData.put("agentMandatorySection", command.isSectionMandatory(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION));
    	refData.put("radiationMandatorySection", command.isSectionMandatory(ExpeditedReportSection.RADIATION_INTERVENTION_SECTION));
    	refData.put("surgeryMandatorySection", command.isSectionMandatory(ExpeditedReportSection.SURGERY_INTERVENTION_SECTION));
    	refData.put("deviceMandatorySection", command.isSectionMandatory(ExpeditedReportSection.MEDICAL_DEVICE_SECTION));
    	return refData;
    }
    
    

    private void createRadiationFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command){
        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(collectOptions(Arrays.asList(RadiationAdministration.values()), null, "displayName"));
        InputField doseUOMField = InputFieldFactory.createSelectField("dosageUnit", "Unit of measure", false, WebUtils.sortMapByKey(WebUtils.collectOptions(configurationProperty.getMap().get("radiationDoseUMORefData"), "code", "desc", "Please Select"), true));
        InputField fractionNumberField = createTextField("fractionNumber", "Schedule number of fractions", false);
        fractionNumberField.getAttributes().put(InputField.HELP, "ae.radiationIntervention.aeReport.radiationInterventions.fractionNumber");

        creator.createRepeatingFieldGroup("radiationIntervention", "radiationInterventions", new SimpleNumericDisplayNameCreator("Radiation"),
                createSelectField("administration", "Type of radiation administration", false, statusOpts),
                createTextField("dosage", "Total dose (to date)", FieldValidator.NUMBER_VALIDATOR),
                doseUOMField,
                createPastDateField("lastTreatmentDate", "Date of last treatment", false),
                fractionNumberField,
                createTextField("daysElapsed", " Number of elapsed days", false),
                createSelectField("adjustment", "Adjustment", false, WebUtils.collectOptions(configurationProperty.getMap().get("radiationAdjustmentRefData"), "code","desc", "Please Select")));
    }

    private void createSurgeryFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command){
        String code = command.getAeReport().getTreatmentInformation().getTreatmentAssignment() != null ? command .getAeReport().getTreatmentInformation().getTreatmentAssignment().getCode() : null;
        String description = code != null ? command.getAeReport().getTreatmentInformation().getTreatmentAssignmentDescription() : command.getAeReport().getTreatmentInformation().getTreatmentDescription();
        InputField descField = InputFieldFactory.createTextArea("description", "Treatment arm description", false);
        InputFieldAttributes.setColumns(descField, 45);
        InputFieldAttributes.setDetails(descField, description);
        InputField codeField = createTextField("treatmentArm", "Treatment arm", false);
        InputFieldAttributes.setDetails(codeField, code);
        creator.createRepeatingFieldGroup("surgeryIntervention", "surgeryInterventions", new SimpleNumericDisplayNameCreator("Surgery"), codeField, descField, InputFieldFactory.createAutocompleterField("interventionSite", "Intervention site", false), InputFieldFactory.createPastDateField("interventionDate", "Date of intervention", false));
    }

    private void createAgentFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command){
        InputField agentField = InputFieldFactory.createSelectField("studyAgent", "Study agent", false, WebUtils.collectOptions(command.getStudy().getStudyAgents(), "id", "agentName", "Please select"));
        InputField totalDoseField = InputFieldFactory.createTextField("dose.amount", "Total dose administered this course", false);
        InputFieldAttributes.setSize(totalDoseField, 4);
        InputField totalUOMField = InputFieldFactory.createSelectField("dose.units","Unit of measure", false, WebUtils.sortMapByKey(WebUtils.collectOptions(configurationProperty.getMap().get("agentDoseUMORefData"),"code", "desc", "Please Select"), true));
        CompositeField adminDelayField = new CompositeField(null, new DefaultInputFieldGroup(null,"Administration delay").addField(InputFieldFactory.createTextField("administrationDelayAmount", "", false)).addField(InputFieldFactory.createSelectField("administrationDelayUnits", "", false,WebUtils.collectOptions(Arrays.asList(DelayUnits.values()), null, "displayName"))));
        InputField commentsField = InputFieldFactory.createTextArea("comments", "Comments", false);
        InputFieldAttributes.setColumns(commentsField, 70);
        InputFieldAttributes.setRows(commentsField, 4);
        //InputField modifiedDoseField = createDoseField("modifiedDose", "Modified dose", false, true);
        InputField modifiedDoseField = InputFieldFactory.createSelectField("agentAdjustment", "Dose Modification?", false, WebUtils.collectOptions(Arrays.asList(AgentAdjustment.values()), null, "displayName","Please Select"));
       // modifiedDoseField.getAttributes().put(InputField.HELP,"ae.treatment.aeReport.treatmentInformation.courseAgents.modifiedDose");

        creator.createRepeatingFieldGroup("courseAgent", "treatmentInformation.courseAgents",
                new SimpleNumericDisplayNameCreator("Study Agent"), agentField, InputFieldFactory.createTextField("formulation", "Formulation"),
                InputFieldFactory.createTextField("lotNumber", "Lot # (if known)"),
                totalDoseField,
                totalUOMField,
                InputFieldFactory.createPastDateField("lastAdministeredDate", "Date last administered", false),
                adminDelayField,
                commentsField,
                modifiedDoseField);
    }

    private void createDeviceFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command){
        InputField brandName = InputFieldFactory.createTextField("brandName", "Brand name", false);
        InputFieldAttributes.setSize(brandName, 45);
        InputField commonName = InputFieldFactory.createTextField("commonName", "Common name", false);
        InputFieldAttributes.setSize(commonName, 45);
        InputField deviceType = InputFieldFactory.createTextField("deviceType", "Device type", false);
        InputFieldAttributes.setSize(deviceType, 45);
        InputField manName = InputFieldFactory.createTextField("manufacturerName", "Manufacturer name", false);
        InputFieldAttributes.setSize(manName, 45);
        InputField manCity = InputFieldFactory.createTextField("manufacturerCity", "Manufacturer city", false);
        InputFieldAttributes.setSize(manCity, 45);
        InputField manState = InputFieldFactory.createSelectField("manufacturerState", "Manufacturer state", false, WebUtils.collectOptions(configurationProperty.getMap().get("stateRefData"), "code", "desc", "Please Select"));
        InputFieldAttributes.setSize(manState, 45);
        InputField modelNumber = InputFieldFactory.createTextField("modelNumber", "Model number", false);
        InputField otherDeviceOperator = InputFieldFactory.createTextField("otherDeviceOperator", "Other device operator", false);
        InputFieldAttributes.setSize(otherDeviceOperator, 45);
        InputField reprocessorName = InputFieldFactory.createTextField("reprocessorName", " Reprocessor name", false);
        InputFieldAttributes.setSize(reprocessorName, 45);
        reprocessorName.getAttributes().put(InputField.HELP, "ae.medicalDevice.aeReport.medicalDevices.reprocessorName");
        InputField reprocessorAddress = InputFieldFactory.createTextField("reprocessorAddress", " Reprocessor address", false);
        InputFieldAttributes.setSize(reprocessorAddress, 45);
        InputField deviceReprocessedField = InputFieldFactory.createSelectField("deviceReprocessed", "Device reprocessed", false, WebUtils.collectOptions(Arrays.asList(Availability.values()), null, "displayName"));
        deviceReprocessedField.getAttributes().put(InputField.HELP, "ae.medicalDevice.aeReport.medicalDevices.deviceReprocessed");
        InputField evaluationAvailabilityField = InputFieldFactory.createSelectField("evaluationAvailability", "Evaluation availability", false, WebUtils.collectOptions(Arrays.asList(Availability.values()), null, "displayName"));
        evaluationAvailabilityField.getAttributes().put(InputField.HELP, "ae.medicalDevice.aeReport.medicalDevices.evaluationAvailability");

        creator.createRepeatingFieldGroup("medicalDevice", "medicalDevices", new SimpleNumericDisplayNameCreator("Medical device"),
                        brandName,
                        commonName,
                        deviceType,
                        manName,
                        manCity,
                        manState,
                        modelNumber,
                        InputFieldFactory.createTextField("lotNumber", "Lot number", false),
                        InputFieldFactory.createTextField("catalogNumber", "Catalog number", false),
                        InputFieldFactory.createDateField("expirationDate", "Expiration date", false),
                        InputFieldFactory.createTextField("serialNumber", "Serial number", false),
                        InputFieldFactory.createTextField("otherNumber", "Other number", false),
                        InputFieldFactory.createSelectField("deviceOperator", "Device operator", false, WebUtils.collectOptions(Arrays.asList(DeviceOperator.values()),null, "displayName")),
                        otherDeviceOperator,
                        InputFieldFactory.createPastDateField("implantedDate", "If implanted, enter a date", false),
                        InputFieldFactory.createPastDateField("explantedDate", "IF explanted, enter a date", false),
                        deviceReprocessedField,
                        reprocessorName,
                        reprocessorAddress,
                        evaluationAvailabilityField,
                        InputFieldFactory.createPastDateField("returnedDate", "Returned date", false));
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        createDeviceFieldGroups(creator, command);
        createRadiationFieldGroups(creator, command);
        createSurgeryFieldGroups(creator, command);
        createAgentFieldGroups(creator, command);
    }

    @Override
    protected boolean methodInvocationRequest(HttpServletRequest request) {
    	return org.springframework.web.util.WebUtils.hasSubmitParameter(request, "currentItem") && org.springframework.web.util.WebUtils.hasSubmitParameter(request, "task");
    }

    @Override
    public String getMethodName(HttpServletRequest request) {
    	String currentItem = request.getParameter("currentItem");
    	String task = request.getParameter("task");
    	return methodNameMap.get(task + currentItem);
    }
    
    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {
                ExpeditedReportSection.STUDY_INTERVENTIONS,
                ExpeditedReportSection.AGENTS_INTERVENTION_SECTION,
    			ExpeditedReportSection.RADIATION_INTERVENTION_SECTION,
    			ExpeditedReportSection.SURGERY_INTERVENTION_SECTION,
    			ExpeditedReportSection.MEDICAL_DEVICE_SECTION};
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public ModelAndView addSurgery(HttpServletRequest request, Object command, Errors errors) {
        ExpeditedAdverseEventInputCommand cmd = (ExpeditedAdverseEventInputCommand)command;
        SurgeryIntervention si = new SurgeryIntervention();
        List<SurgeryIntervention> surgeries = cmd.getAeReport().getSurgeryInterventions();
        cmd.getAeReport().addSurgeryIntervention(si);
        si.setReport(cmd.getAeReport());
        ModelAndView modelAndView = new ModelAndView("ae/ajax/surgeryInterventionFormSection");
        modelAndView.getModel().put("surgeries", surgeries);
        modelAndView.getModel().put("indexes", new Integer[]{surgeries.size() - 1});
        return modelAndView;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public ModelAndView addRadiation(HttpServletRequest request, Object command, Errors errors) {
        ExpeditedAdverseEventInputCommand cmd = (ExpeditedAdverseEventInputCommand)command;
        RadiationIntervention ri = new RadiationIntervention();
        List<RadiationIntervention> radiations = cmd.getAeReport().getRadiationInterventions();
        cmd.getAeReport().addRadiationIntervention(ri);
        ri.setReport(cmd.getAeReport());
        ModelAndView modelAndView = new ModelAndView("ae/ajax/radiationInterventionFormSection");
        modelAndView.getModel().put("radiations", radiations);
        modelAndView.getModel().put("indexes", new Integer[]{radiations.size() - 1});
        return modelAndView;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public ModelAndView addDevice(HttpServletRequest request, Object command, Errors errors) {
        ExpeditedAdverseEventInputCommand cmd = (ExpeditedAdverseEventInputCommand)command;
        MedicalDevice md = new MedicalDevice();
        List<MedicalDevice> devices = cmd.getAeReport().getMedicalDevices();
        cmd.getAeReport().addMedicalDevice(md);
        md.setReport(cmd.getAeReport());
        ModelAndView modelAndView = new ModelAndView("ae/ajax/medicalDeviceFormSection");
        modelAndView.getModel().put("devices", devices);
        modelAndView.getModel().put("indexes", new Integer[]{devices.size() - 1});
        return modelAndView;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public ModelAndView addAgent(HttpServletRequest request, Object command, Errors errors) {
        ExpeditedAdverseEventInputCommand cmd = (ExpeditedAdverseEventInputCommand)command;
        CourseAgent ca = new CourseAgent();
        List<CourseAgent> agents = cmd.getAeReport().getTreatmentInformation().getCourseAgents();
        cmd.getAeReport().getTreatmentInformation().addCourseAgent(ca);
        ca.getTreatmentInformation().setReport(cmd.getAeReport());
        ModelAndView modelAndView = new ModelAndView("ae/ajax/courseAgentFormSection");
        modelAndView.getModel().put("agents", agents);
        modelAndView.getModel().put("indexes", new Integer[]{agents.size() - 1});
        return modelAndView;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public ModelAndView removeSurgery(HttpServletRequest request, Object command, Errors errors) {
        ExpeditedAdverseEventInputCommand cmd = (ExpeditedAdverseEventInputCommand)command;
        List<SurgeryIntervention> surgeries = cmd.getAeReport().getSurgeryInterventions();

        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Wrong <index> for <surgeries> list: " + e.getMessage());
        }

        if (surgeries.size() - 1 < index) {
            log.debug("Wrong <index> for <surgeries> list.");
        } else if (index >=0) {
            SurgeryIntervention object = (SurgeryIntervention)surgeries.get(index);
            surgeries.remove(object);
            deleteAttributions(object, (ExpeditedAdverseEventInputCommand)command);        
        }

        int size = surgeries.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
        ModelAndView modelAndView = new ModelAndView("ae/ajax/surgeryInterventionFormSection");
        modelAndView.getModel().put("surgeries", surgeries);
        modelAndView.getModel().put("indexes", indexes);
        
        return modelAndView;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public ModelAndView removeRadiation(HttpServletRequest request, Object command, Errors errors) {
        ExpeditedAdverseEventInputCommand cmd = (ExpeditedAdverseEventInputCommand)command;
        List<RadiationIntervention> radiations = cmd.getAeReport().getRadiationInterventions();

        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Wrong <index> for <radiations> list: " + e.getMessage());
        }

        if (radiations.size() - 1 < index) {
            log.debug("Wrong <index> for <radiations> list.");
        } else if (index >=0) {
            RadiationIntervention object = (RadiationIntervention)radiations.get(index);
            radiations.remove(object);
            deleteAttributions(object, (ExpeditedAdverseEventInputCommand)command);
        }

        int size = radiations.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
        ModelAndView modelAndView = new ModelAndView("ae/ajax/radiationInterventionFormSection");
        modelAndView.getModel().put("radiations", radiations);
        modelAndView.getModel().put("indexes", indexes);

        return modelAndView;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public ModelAndView removeDevice(HttpServletRequest request, Object command, Errors errors) {
        ExpeditedAdverseEventInputCommand cmd = (ExpeditedAdverseEventInputCommand)command;
        List<MedicalDevice> devices = cmd.getAeReport().getMedicalDevices();

        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Wrong <index> for <devices> list: " + e.getMessage());
        }

        if (devices.size() - 1 < index) {
            log.debug("Wrong <index> for <devices> list.");
        } else if (index >=0) {
            MedicalDevice object = (MedicalDevice)devices.get(index);
            devices.remove(object);
            deleteAttributions(object, (ExpeditedAdverseEventInputCommand)command);
        }

        int size = devices.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
        ModelAndView modelAndView = new ModelAndView("ae/ajax/medicalDeviceFormSection");
        modelAndView.getModel().put("devices", devices);
        modelAndView.getModel().put("indexes", indexes);

        return modelAndView;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public ModelAndView removeAgent(HttpServletRequest request, Object command, Errors errors) {
        AbstractExpeditedAdverseEventInputCommand cmd = (AbstractExpeditedAdverseEventInputCommand)command;
        List<CourseAgent> agents = cmd.getAeReport().getTreatmentInformation().getCourseAgents();

        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Wrong <index> for <agents> list: " + e.getMessage());
        }

        if (agents.size() - 1 < index) {
            log.debug("Wrong <index> for <agents> list.");
        } else if (index >=0) {
        	cmd.deleteAttribution(agents.get(index));
            agents.remove(agents.get(index));
        };

        int size = agents.size();
    	Integer[] indexes = new Integer[size];
    	for(int i = 0 ; i < size ; i++){
    		indexes[i] = size - (i + 1);
    	}
        ModelAndView modelAndView = new ModelAndView("ae/ajax/courseAgentFormSection");
        modelAndView.getModel().put("agents", agents);
        modelAndView.getModel().put("indexes", indexes);

        return modelAndView;
    }

    public void deleteAttributions(ExpeditedAdverseEventReportChild child, ExpeditedAdverseEventInputCommand command) {
        expeditedAdverseEventReportDao.cascaeDeleteToAttributions((DomainObject) child, command.getAeReport());
        child.setReport(null);
    }

    public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
        return expeditedAdverseEventReportDao;
    }

    public void setExpeditedAdverseEventReportDao(ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }

}