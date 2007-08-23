package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.domain.ExpeditedReportPerson;
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
            section("Adverse events",
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
            section("Reporter info",
                createPersonBlock("reporter"),
                createPersonBlock("physician")
            ),
            section("Checkpoint"), // so that ordering lines up
            section("Radiation intervention",
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
            section("Surgery intervention",
                property("surgeryIntervention",
                    property("treatmentArm", "Treatment arm"),
                    property("description", "Treatment arm description"),
                    property("anatomicSite", "Intervention site"),
                    property("interventionDate", "Intervention date")
                )
            ),
            section("Medical device",
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
            section("Event and response description",
            	property("responseDescription",
            		property("eventDescription","Description"),
            		property("presentStatus","Present status"),
            		property("recoveryDate","Date of recovery or death"),
            		property("retreated","Has the particpant been re-treated?"),
            		property("dateRemovedFromProtocol","Date removed from protocol")
            	)
            ),
            section("Medical info",
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
            section("Treatment Information",
               property("treatmentInformation",
            	   property("treatmentAssignmentCode","Assignment code"),
            	   property("firstCourseDate","First course start date"),
                   // TODO: these should be a component instead
                   property("adverseEventCourse.date","Adverse event course start date"),
                   property("adverseEventCourse.number","Adverse event course number")
               )
            )
        );
    }

    private static TreeNode createPersonBlock(String person) {
        return property(
            person, StringUtils.capitalize(person) + " details",
            property("firstName", "First name"),
            property("middleName", "Middle name"),
            property("lastName", "Last name"),
            contactField(ExpeditedReportPerson.EMAIL, "E-mail address"),
            contactField(ExpeditedReportPerson.PHONE),
            contactField(ExpeditedReportPerson.FAX)
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
            property("quantity", ""),
            property("unit", "units")
        );
    }

    // DONOT MODIFY NAMES EVEN IF THEY CHANGES....this is tied up with rules ....
    public static String[] getAllSectionNames() {

    	String[] sectionNames = {"Radiation intervention","Surgery intervention",
    					"Medical device","Event and response description","Medical info"};
    	return sectionNames;
    }

}
