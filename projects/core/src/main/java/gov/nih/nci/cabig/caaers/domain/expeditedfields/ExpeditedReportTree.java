package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.*;
import org.apache.commons.lang.StringUtils;

/**
 * Tree representing most of the properties in the
 * {@link gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport} model.
 * <p/>
 * Internal nodes in the tree may represent a subproperty of their parent node,
 * or may indicate a logical grouping (section) of their children.  In the latter case,
 * the {@link #getPropertyName propertyName} property will be null.
 *
 * @author Rhett Sutphin
 */
public class ExpeditedReportTree extends PropertylessNode {
    public ExpeditedReportTree() {
        add(
            section(ADVERSE_EVENT_SECTION,
                // TODO: figure out how to handle the MedDRA alternative here
                list("adverseEvents", new AdverseEventsDisplayNameCreator(),
                    property("grade", "Grade"),
                    property("startDate", "Start date"),
                    property("endDate", "End date"),
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
            section(REPORTER_INFO_SECTION,
                createPersonBlock("reporter"),
                createPersonBlock("physician")
            ),
            section(CHECKPOINT_SECTION), // so that ordering lines up
            section(RADIATION_INTERVENTION_SECTION,
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
            section(SURGERY_INTERVENTION_SECTION,
                property("surgeryIntervention",
                    property("treatmentArm", "Treatment arm"),
                    property("description", "Treatment arm description"),
                    property("anatomicSite", "Intervention site"),
                    property("interventionDate", "Intervention date")
                )
            ),
            section(MEDICAL_DEVICE_SECTION,
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
            section(DESCRIPTION_SECTION,
                property("responseDescription",
                    property("eventDescription", "Description"),
                    property("presentStatus", "Present status"),
                    property("recoveryDate", "Date of recovery or death"),
                    property("retreated", "Has the particpant been re-treated?"),
                    property("dateRemovedFromProtocol", "Date removed from protocol")
                )
            ),
            section(MEDICAL_INFO_SCECTION,
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
            section(TREATMENT_INFO_SECTION,
                property("treatmentInformation",
                    property("treatmentAssignment", "Treatment assignment code"),
                    property("treatmentAssignmentDescription", "Description of treatment assignment or dose level"),
                    property("firstCourseDate", "Start date of first course"),
                    // TODO: these should be a component instead
                    property("adverseEventCourse",
                        property("date", "Start date of course associated with expedited report"),
                        property("number", "Course number on which event occurred")
                    ),
                    property("totalCourses", "Total number of courses to date"),
                    //TODO : Need a display name creator????
                    list("courseAgents", "Course Agent",
                        property("studyAgent", "Study Agent"),
                        property("dose",
                        		property("amount", "Total dose administered this course"),
                        		property("units", "Unit of measure")),
                        //property("durationAndSchedule", "Duration and schedule"),
                        property("lastAdministeredDate", "Date last administered"),
                        //dosage("dose", "Dose"), //old Dose
                        //TODO: this is a component
                        property("administrationDelayAmount", "Administration Delay Amount"),
                        property("administrationDelayUnits", "Administration Delay Units"),
                        property("comments", "Comments"),
                        dosage("modifiedDose", "Modified dose")

                    )
                )
            ),
            section(LABS_SECTION,
                list("labs", "Lab",
                    property("name", "Lab test name"),
                    property("other", "Other test name"),
                    property("units", "Units"),
                    labValue("baseline", "Baseline"),
                    labValue("nadir", "Worst"),
                    labValue("recovery", "Recovery")
                )
            ),
            section(PRIOR_THERAPIES_SECTION,
                list("adverseEventPriorTherapies", "Prior Therapy",
                    property("priorTherapy", "Therapy"),
                    property("other", "Other"),
                    property("startDate", "Start Date"),
                    property("endDate", "End Date")
                )
            ),
            section(PRE_EXISTING_CONDITION_SECTION,
                list("adverseEventPreExistingConds", "AdverseEventPreExistingCond",
                    property("preExistingCondition", "Pre-existing condition"),
                    property("other", "Other")
                )
            ),
            section(CONCOMITANT_MEDICATION_SECTION,
                list("concomitantMedications", "ConcomitantMedication",
                    property("agentName", "Known medication")
                )
            ),
            section(OTHER_CAUSE_SECTION,
                list("otherCauses", "OtherCauses",
                    property("text", "Cause")
                )
            ),
            section(ATTRIBUTION_SECTION), //TODO: how to fill this??
            section(ADDITIONAL_INFO_SECTION)//TODO: additional info section
        );
    }

    @Override
    public String getPropertyName() {
        return null;
    }

    public TreeNode getNodeForSection(ExpeditedReportSection section) {
        for (TreeNode node : getChildren()) {
            if (((SectionNode) node).getSection() == section) return node;
        }
        if (log.isDebugEnabled()) {
            log.debug("No node in the expedited report tree for " + section);
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

    private static TreeNode dosage(String baseName, String displayName) {
        return property(baseName, displayName,
            property("amount", "Amount"),
            property("units", "Units"),
            property("route", "Route")
        );
    }

    private static TreeNode labValue(String baseName, String displayName) {
        return property(baseName, displayName,
            property("value", "Value"),
            property("date", "Date")
        );
    }
}
