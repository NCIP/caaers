package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.ADDITIONAL_INFO_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.ADVERSE_EVENT_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.ATTRIBUTION_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.CHECKPOINT_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.CONCOMITANT_MEDICATION_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.DESCRIPTION_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.LABS_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.MEDICAL_DEVICE_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.MEDICAL_INFO_SCECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.OTHER_CAUSE_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.PRE_EXISTING_CONDITION_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.PRIOR_THERAPIES_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.RADIATION_INTERVENTION_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.REPORTER_INFO_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.SURGERY_INTERVENTION_SECTION;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.TREATMENT_INFO_SECTION;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;

import org.apache.commons.lang.StringUtils;
/**
 * Tree representing most of the properties in the
 * {@link gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport} model.
 * <p>
 * Internal nodes in the tree may represent a subproperty of their parent node,
 * or may indicate a logical grouping (section) of their children.  In the latter case,
 * the {@link #getPropertyName propertyName} property will be null.
 *
 *
 * @author Rhett Sutphin
 */
public class ExpeditedReportTree extends TreeNode {
    public ExpeditedReportTree() {
        add(
            section(ADVERSE_EVENT_SECTION.name(),
                // TODO: figure out how to handle the MedDRA alternative here
                property("detectionDate", "Detection date"),
                list("adverseEvents", new AdverseEventsDisplayNameCreator(),
                    property("grade", "Grade"),
                    property("attributionSummary", "Attribution to study"),
                    property("hospitalization", "Hospitalization"),
                    property("expected", "Expected"),
                    property("comments", "Comments"),
                    property("adverseEventCtcTerm",
                        property("term", "CTC term")
                    ),
                    property("detailsForOther", "Other (specify)")
                )
            ),
            section(REPORTER_INFO_SECTION.name(),
                createPersonBlock("reporter"),
                createPersonBlock("physician")
            ),
            section(CHECKPOINT_SECTION.name()), // so that ordering lines up
            section(RADIATION_INTERVENTION_SECTION.name(),
                property("radiationIntervention",
                    property("treatmentArm", "Treatment arm"),
                    property("description", "Treatment arm description"),
                    property("administration", "Type of radiation administration"),

                    // TODO: these should be a component instead
                    property("dosage", "Dosage"),
                    property("dosageUnit", "Dosage unit"),

                    property("lastTreatmentDate", "Last treatment date"),
                    property("fractionNumber", "Schedule number of fractions"),
                    property("daysElapsed", "Elapsed days"),
                    property("adjustment", "Adjustment")
                )
            ),
            section(SURGERY_INTERVENTION_SECTION.name(),
                property("surgeryIntervention",
                    property("treatmentArm", "Treatment arm"),
                    property("description", "Treatment arm description"),
                    property("anatomicSite", "Intervention site"),
                    property("interventionDate", "Intervention date")
                )
            ),
            section(MEDICAL_DEVICE_SECTION.name(),
            	property("medicalDevice",
            		property("brandName", "Brand name"),
            		property("commonName", "Common name"),
            		property("deviceType", "Device type"),
            		property("manufacturerName", "Manufacturer name"),
            		property("manufacturerCity", "Manufacturer city"),
            		property("manufacturerState", "Manufacturer state"),
            		property("modelNumber", "Model number"),
            		property("lotNumber", "Lot number"),
            		property("catalogNumber", "Catalog number"),
            		property("expirationDate", "Expiration date"),
            		property("serialNumber", "Serial number"),
            		property("otherNumber", "Other number"),
            		property("deviceOperator", "Device operator"),
            		property("otherDeviceOperator", "Other device operator"),
            		property("implantedDate", "If implanted give a date"),
            		property("explantedDate", "IF explanted give a date"),
            		property("deviceReprocessed", "Device reprocessed"),
            		property("reprocessorName", "Reprocessor name"),
            		property("reprocessorAddress", "Reprocessor address"),
            		property("evaluationAvailability", "Evaluation availability"),
            		property("returnedDate", "Returned date")
            	)
            ),
            section(DESCRIPTION_SECTION.name(),
            	property("responseDescription",
            		property("eventDescription","Description"),
            		property("presentStatus","Present status"),
            		property("recoveryDate","Date of recovery or death"),
            		property("retreated","Has the particpant been re-treated?"),
            		property("dateRemovedFromProtocol","Date removed from protocol")
            	)
            ),
            section(MEDICAL_INFO_SCECTION.name(),
                property("participantHistory",
                    participantMeasure("height"),
                    participantMeasure("weight"),
                    property("baselinePerformanceStatus", "Baseline performance")
                ),
                property("diseaseHistory",
                    property("ctepStudyDisease", "Disease from study"),
                    property("otherPrimaryDisease", "Other disease"),
                    property("codedPrimaryDiseaseSite", "Primary disease site"),
                    property("otherPrimaryDiseaseSite", "Other primary disease site"),
                    property("diagnosisDate", "Diagnosis date"),
                    list("metastaticDiseaseSites", "Metastatic disease site",
                        property("codedSite", "Site name"),
                        property("otherSite", "Other site")
                    )
                )
            ),
            section(TREATMENT_INFO_SECTION.name(),
               property("treatmentInformation",
            	   property("treatmentAssignmentCode","Assignment code"),
            	   property("firstCourseDate","First course start date"),
                   // TODO: these should be a component instead
            	   property("adverseEventCourse",
            			   property("date","Adverse event course start date"),
            			   property("number","Adverse event course number")
            	   ),
            	   //TODO : Need a display name creator????
            	   list("courseAgents", "Course Agent",
            			property("studyAgent", "Study Agent"),
            			dosage("dose","Dosage"),
            			property("durationAndSchedule", "Duration and schedule"),
            			//TODO: this is a component
       					property("administrationDelayAmount","Administration Delay Amount"),
       					property("administrationDelayUnits","Administration Delay Units"),
                 	    dosage("modifiedDose","Modified dose"),
                 	    property("totalDoseAdministeredThisCourse", "Total dose administered this course"),
                 	    property("lastAdministeredDate", "Date last administered")
            	   )
               )
            ),
            section(LABS_SECTION.name(),
            		list("labs", "Lab",
            			property("name", "Name"),
            			property("units", "Units"),
            			inference("baseline", "Baseline"),
            			inference("nadir", "Worst"),
            			inference("recovery","Recovery")
            		)
            ),
            section(PRIOR_THERAPIES_SECTION.name(),
            		list("adverseEventPriorTherapies","Prior Therapy",
            				property("priorTherapy", "Therapy"),
            				property("other", "Other"),
            				property("startDate", "Start Date"),
            				property("endDate", "End Date")
            		)
            ),
            section(PRE_EXISTING_CONDITION_SECTION.name(),
            		list("adverseEventPreExistingConds", "AdverseEventPreExistingCond",
            				property("preExistingCondition","Pre-Existing condition"),
            				property("other","Other")
            		)
            ),
            section(CONCOMITANT_MEDICATION_SECTION.name(),
            		list("concomitantMedications", "ConcomitantMedication",
            				property("agent","Known medication"),
            				property("other", "Other")
            		)
            ),
            section(OTHER_CAUSE_SECTION.name(),
            		list("otherCauses","OtherCauses",
            				property("text", "Cause")
            		)
            ),
            section(ATTRIBUTION_SECTION.name()), //TODO: how to fill this??
            section(ADDITIONAL_INFO_SECTION.name())//TODO: additional info section
        );
    }

    public  TreeNode fecthNode4Section(ExpeditedReportSection section){
    	for(TreeNode node : getChildren()){
    		if(StringUtils.equals(node.getDisplayName(), section.name()))
    			return node;
    	}
    	return null;
    }

    private static TreeNode createPersonBlock(String person) {
        return property(
            person, StringUtils.capitalize(person) + " details",
            property("firstName", "First name"),
            property("middleName", "Middle name"),
            property("lastName", "Last name"),
            contactField(ReportPerson.EMAIL, "E-mail address"),
            contactField(ReportPerson.PHONE),
            contactField(ReportPerson.FAX)
        );
    }

    private static TreeNode contactField(String contactType) {
        return contactField(contactType, StringUtils.capitalize(contactType));
    }

    private static TreeNode contactField(
        String contactType, String displayName
    ) {
        return property("contactMechanisms[" + contactType + ']', displayName);
    }

    private static TreeNode participantMeasure(String baseName) {
        return property(baseName, StringUtils.capitalize(baseName),
            property("quantity", "Quantity"),
            property("unit", "Units")
        );
    }

    private static TreeNode dosage(String baseName, String displayName){
    	return property(baseName, StringUtils.capitalize(baseName),
    			property("amount","Amount"),
    			property("units","Units"),
    			property("route","Route")
    	);
    }

    private static TreeNode inference(String baseName, String displayName){
    	return property(baseName, displayName,
    			property("value","Value"),
    			property("date","Date")
    	);
    }



}
