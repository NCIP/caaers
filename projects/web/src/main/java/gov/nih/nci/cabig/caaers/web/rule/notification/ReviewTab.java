package gov.nih.nci.cabig.caaers.web.rule.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;

public class ReviewTab extends TabWithFields<ReportDefinitionCommand> {

    private InputFieldGroupMap map;

    public ReviewTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public ReviewTab() {
        this("Review", "Review", "rule/notification/reviewTab");
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.ctms.web.tabs.Tab#postProcess(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object, org.springframework.validation.Errors)
     */
    @Override
    public void postProcess(HttpServletRequest req, ReportDefinitionCommand nfCmd, Errors errors) {
        super.postProcess(req, nfCmd, errors);
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.web.fields.TabWithFields#createFieldGroups(java.lang.Object)
     */
    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ReportDefinitionCommand command) {
        map = new InputFieldGroupMap();
        return map;
    }

    public Pair fetchFieldValue(InputField field, BeanWrapper command) {
        Object value = command.getPropertyValue(field.getPropertyName());
        String strValue = (value == null) ? null : String.valueOf(value);
        return new Pair(field.getDisplayName(), strValue);
    }

    /**
     * This will return a list of Pair objects, with each pair consiting of the
     * <code>displayName</code> of the field and its associated <code>value</code>.
     * 
     * @param fieldGroup -
     *                An InputFieldGroup
     * @param command -
     *                The BeanWrapper instance wrapping {@link ReportDefinitionCommand}
     * @param exclusions -
     *                A list of <code>displayName</code> to be eliminated from the display.
     * @return List&lt;Pair&gt; objects.
     */
    public List<Pair> fetchFieldValues(InputFieldGroup fieldGroup, BeanWrapper command,
                    String... exclusions) {
        List<Pair> fieldList = new ArrayList<Pair>();
        for (InputField field : fieldGroup.getFields()) {
            // do not add if the display name is included in the exclusion list
            if (exclusions != null && ArrayUtils.contains(exclusions, field.getDisplayName())) continue;
            fieldList.add(fetchFieldValue(field, command));
        }
        return fieldList;
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ReportDefinitionCommand command) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        BeanWrapper wrappedCommand = new BeanWrapperImpl(command);

        // basic details tab fields
        TabWithFields<ReportDefinitionCommand> tab = (TabWithFields<ReportDefinitionCommand>) getFlow()
                        .getTab(0);
        Map<String, InputFieldGroup> fieldGroupMap = tab.createFieldGroups(command);
        InputFieldGroup fieldGroup = fieldGroupMap.get("reportDefinitionOrganization");
        List<Pair> fieldList = fetchFieldValues(fieldGroup, wrappedCommand);
        fieldGroup = fieldGroupMap.get("reportDefinitionFieldGroup"); // the name of the
                                                                        // fieldgroup
        fieldList.addAll(fetchFieldValues(fieldGroup, wrappedCommand));
        map.put(tab.getShortTitle(), fieldList);

        // report definition tab

        List<ReportDeliveryDefinition> deliveries = command.getReportDefinition()
                        .getDeliveryDefinitions();
        if (deliveries != null) {
            List<Pair> pairs = new ArrayList<Pair>();
            for (ReportDeliveryDefinition rdd : deliveries) {
                if (rdd.getEntityType() == ReportDeliveryDefinition.ENTITY_TYPE_PERSON) {
                    Pair p = new Pair("Person", rdd.getEntityName());
                    p.setAttribute1(rdd.getEndPoint());
                    p.setAttribute2(rdd.getFormat().getDisplayName());
                    pairs.add(p);
                }
                if (rdd.getEntityType() == ReportDeliveryDefinition.ENTITY_TYPE_ROLE) {
                    Pair p = new Pair("Role", rdd.getEntityName());
                    p.setAttribute1(String.valueOf(command.getRoles().get(rdd.getEndPoint())));
                    p.setAttribute2(rdd.getFormat().getDisplayName());
                    pairs.add(p);
                }
            }
            for (ReportDeliveryDefinition rdd : deliveries) {
                if (rdd.getEntityType() == ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM) {
                    Pair p = new Pair("System", rdd.getEntityName());
                    p.setAttribute1(rdd.getEndPoint());
                    p.setAttribute2(rdd.getFormat().getDisplayName());
                    pairs.add(p);
                }
            }
            map.put("rdd", pairs);
        }

        // Mandatory Field Definition Tab
        tab = (TabWithFields<ReportDefinitionCommand>) getFlow().getTab(2);
        fieldGroupMap = tab.createFieldGroups(command);

        Map<String, List<Pair>> sectionMap = new LinkedHashMap<String, List<Pair>>();
        Map<String, String> keyMap = new LinkedHashMap<String, String>();
        keyMap.put("Adverse Events", "ADVERSE_EVENT_SECTION~Adverse events");
        
        keyMap.put("Reporter Details", "REPORTER_INFO_SECTION~Reporter details");
        keyMap.put("Reporter Details - Address", "REPORTER_INFO_SECTION~Reporter details~Address");
        keyMap.put("Physician details", "REPORTER_INFO_SECTION~Physician details");
        keyMap.put("Physician details - Address", "REPORTER_INFO_SECTION~Physician details~Address");

        keyMap.put("Treatment Information", "TREATMENT_INFO_SECTION");
        keyMap.put("Treatment Information - Study agents", "TREATMENT_INFO_SECTION~Study Agents");
        keyMap.put("Treatment Information - Study agents", "TREATMENT_INFO_SECTION~Study Agents~Modified dose");
        
        keyMap.put("Event and response description", "DESCRIPTION_SECTION");
        
        keyMap.put("Patient details", "MEDICAL_INFO_SECTION");
        keyMap.put("Patient details - Weight", "MEDICAL_INFO_SECTION~Weight");
        keyMap.put("Patient details - Height", "MEDICAL_INFO_SECTION~Height");
        keyMap.put("Patient details - Metastatic disease sites",
                        "MEDICAL_INFO_SECTION~Metastatic disease sites");
        keyMap.put("Patient details - Prior Therapys", "MEDICAL_INFO_SECTION~Prior Therapys");
        keyMap.put("Patient details - Pre-Existing Conditions",
                        "MEDICAL_INFO_SECTION~Pre-existing conditions");
        keyMap.put("Patient details- Concomitant Medications",
                        "MEDICAL_INFO_SECTION~Medications");
        keyMap.put("Other contributing causes", "OTHER_CAUSE_SECTION~OtherCausess");
        keyMap.put("Radiation intervention","RADIATION_INTERVENTION_SECTION~RadiationInterventions");
        keyMap.put("Surgery intervention", "SURGERY_INTERVENTION_SECTION~SurgeryInterventions");
        
        keyMap.put("Medical devices", "MEDICAL_DEVICE_SECTION~MedicalDevices");
        keyMap.put("Labs", "LABS_SECTION~Labs");
        keyMap.put("Labs - Baseline", "LABS_SECTION~Labs~Baseline");
        keyMap.put("Labs - Worst", "LABS_SECTION~Labs~Worst");
        keyMap.put("Labs - Recovery", "LABS_SECTION~Labs~Recovery");

        for (String key : keyMap.keySet()) {
            InputFieldGroup fieldGrp = fieldGroupMap.get(keyMap.get(key));
            if (sectionMap.containsKey(key)) {
                sectionMap.get(key).addAll(fetchFieldValues(fieldGrp, wrappedCommand));
            } else {
                sectionMap.put(key, fetchFieldValues(fieldGrp, wrappedCommand));
            }
        }
        map.put("mandatoryFields", sectionMap);

        // Notification details tab
        tab = (TabWithFields<ReportDefinitionCommand>) getFlow().getTab(3);
        fieldGroupMap = tab.createFieldGroups(command);
        Map<String, Object> pnfMap = new LinkedHashMap<String, Object>();
        int i = 0;
        for (PlannedNotification nf : command.getReportDefinition().getPlannedNotifications()) {
            PlannedEmailNotification penf = (PlannedEmailNotification) nf;
            List<Pair> pairs = new ArrayList<Pair>();
            i++;

            // - recipient
            StringBuilder sb = new StringBuilder();
            for (Recipient r : penf.getRecipients()) {
                if (sb.length() > 0) sb.append(",");
                if (r instanceof RoleBasedRecipient) {
                    sb.append(command.getRoles().get(r.getContact()));
                } else {
                    sb.append(r.getContact());
                }

            }

            pairs.add(new Pair("Recipients", sb.toString()));

            // - subject
            pairs.add(new Pair("Subject", penf.getSubjectLine()));
            // - body
            pairs.add(new Pair("Message", penf.getNotificationBodyContent().getBody()));

            pnfMap.put("Notification " + i + " of "
                            + command.getReportDefinition().getTimeScaleUnitType().getDisplayName()
                            + " " + penf.getIndexOnTimeScale(), pairs);
        }

        map.put("PENF", pnfMap);

        Map<String, Object> refDataMap = super.referenceData(command);
        refDataMap.put("FIELDS", map);
        return refDataMap;

    }

}
