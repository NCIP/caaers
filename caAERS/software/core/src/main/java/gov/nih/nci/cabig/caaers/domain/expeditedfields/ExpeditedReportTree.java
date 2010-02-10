package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.*;

import static gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection.*;

/**
 * Tree representing most of the properties in the
 * {@link gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport} model. <p/> Internal nodes in
 * the tree may represent a subproperty of their parent node, or may indicate a logical grouping
 * (section) of their children. In the latter case, the {@link #getPropertyName propertyName}
 * property will be null.
 * 
 * @author Rhett Sutphin
 * @author Ion C. Olaru
 */
public class ExpeditedReportTree extends PropertylessNode {
    private Map<ExpeditedReportSection, TreeNode> sections;
    private MessageSource messageSource;

    public ExpeditedReportTree() {
        this(null);
    }

    public ExpeditedReportTree(MessageSource messageSource) {
        setMessageSource(messageSource);

        sections = new LinkedHashMap<ExpeditedReportSection, TreeNode>();
        add(
                        section(BASICS_SECTION),
                        section(STUDY_INTERVENTIONS),
                        section(ADVERSE_EVENT_SECTION,
                        // TODO: figure out how to handle the MedDRA alternative here
                                list("adverseEvents",
                                        new AdverseEventsDisplayNameCreator(),
                                        property("grade", getMessage("LBL_aeReport.adverseEvents.grade", "Grade")),
                                        property("adverseEventCtcTerm", property("term", getMessage("LBL_aeReport.adverseEvents.ctcTerm", "CTC term"))),
                                        property("detailsForOther", getMessage("LBL_aeReport.adverseEvents.detailsForOther", "Verbatim")),
                                        property("startDate", getMessage("LBL_aeReport.adverseEvents.startDate", "Start date")),
                                        property("endDate", getMessage("LBL_aeReport.adverseEvents.endDate", "End date")), 
                                        property("attributionSummary", getMessage("LBL_aeReport.adverseEvents.attributionSummary", "Attribution to study intervention")),
                                        property("hospitalization", getMessage("LBL_aeReport.adverseEvents.hospitalization", "Hospitalization")),
                                        property("expected", getMessage("LBL_aeReport.adverseEvents.expected", "Expected")),
                                        property("participantAtRisk", getMessage("LBL_aeReport.adverseEvents.participantAtRisk", "Does this place participant at increased risk?")),
                                        property("eventLocation", getMessage("LBL_aeReport.adverseEvents.eventLocation", "Where was the patient when the event occurred?")),
                                        property("eventApproximateTime.hourString", getMessage("LBL_aeReport.adverseEvents.eventApproximateTime.hourString", "Time of event")),
                                        property("outcomes", getMessage("LBL_aeReport.adverseEvents.outcomes", "Outcomes")),
                                        property("comments", getMessage("LBL_aeReport.adverseEvents.comments", "Comments"))
                                 )
                        ),
                        section(REPORTER_INFO_SECTION, 
                        		createPersonBlock("reporter"),
                                createPersonBlock("physician")
                        ),
                        section(RADIATION_INTERVENTION_SECTION,
                              list("radiationInterventions", "RadiationIntervention",
                                property("administration", getMessage("LBL_aeReport.radiationInterventions.administration", "Type of radiation administration")),
                                property("dosage", getMessage("LBL_aeReport.radiationInterventions.dosage", "Total dose (to date)")), 
                                property("dosageUnit", getMessage("LBL_aeReport.radiationInterventions.dosageUnit", "Unit of measure")),
                                property("lastTreatmentDate", getMessage("LBL_aeReport.radiationInterventions.lastTreatmentDate", "Date of last treatment")),
                                property("fractionNumber", getMessage("LBL_aeReport.radiationInterventions.fractionNumber", "Schedule number of fractions")),
                                property("daysElapsed", getMessage("LBL_aeReport.radiationInterventions.daysElapsed", "Number of elapsed days")),
                                property("adjustment", getMessage("LBL_aeReport.radiationInterventions.adjustment", "Adjustment")))
                        ),
                        section(SURGERY_INTERVENTION_SECTION, 
//                        	list("surgeryInterventions", getMessage("LBL_aeReport.surgeryInterventions.title", "Surgeries"),
                        	list("surgeryInterventions", "SurgeryIntervention",
                                        property("interventionSite", getMessage("LBL_aeReport.surgeryInterventions.interventionSite", "Intervention site")),
                                        property("interventionDate", getMessage("LBL_aeReport.surgeryInterventions.interventionDate", "Date of intervention"))
                            )
                        ),
                        section(MEDICAL_DEVICE_SECTION, 
//                        	list("medicalDevices", getMessage("LBL_aeReport.medicalDevices.title", "MedicalDevice"),
                        	list("medicalDevices", "MedicalDevice",
                                        property("brandName", getMessage("LBL_aeReport.medicalDevices.brandName", "Brand name")),
                                        property("commonName", getMessage("LBL_aeReport.medicalDevices.commonName", "Common name")),
                                        property("deviceType", getMessage("LBL_aeReport.medicalDevices.deviceType", "Device type")),
                                        property("manufacturerName", getMessage("LBL_aeReport.medicalDevices.manufacturerName", "Manufacturer name")),
                                        property("manufacturerCity", getMessage("LBL_aeReport.medicalDevices.manufacturerCity", "Manufacturer city")),
                                        property("manufacturerState", getMessage("LBL_aeReport.medicalDevices.manufacturerState", "Manufacturer state")),
                                        property("modelNumber", getMessage("LBL_aeReport.medicalDevices.modelNumber", "Model number")),
                                        property("lotNumber", getMessage("LBL_aeReport.medicalDevices.lotNumber", "Lot number")),
                                        property("catalogNumber", getMessage("LBL_aeReport.medicalDevices.catalogNumber", "Catalog number")),
                                        property("expirationDate", getMessage("LBL_aeReport.medicalDevices.expirationDate", "Expiration date")),
                                        property("serialNumber", getMessage("LBL_aeReport.medicalDevices.serialNumber", "Serial number")),
                                        property("otherNumber", getMessage("LBL_aeReport.medicalDevices.otherNumber", "Other number")),
                                        property("deviceOperator", getMessage("LBL_aeReport.medicalDevices.deviceOperator", "Device operator")),
                                        property("otherDeviceOperator", getMessage("LBL_aeReport.medicalDevices.otherDeviceOperator", "Other device operator")),
                                        property("implantedDate", getMessage("LBL_aeReport.medicalDevices.implantedDate", "If implanted, enter a date")),
                                        property("explantedDate", getMessage("LBL_aeReport.medicalDevices.explantedDate", "IF explanted, enter a date")),
                                        property("deviceReprocessed", getMessage("LBL_aeReport.medicalDevices.deviceReprocessed", "Device reprocessed")),
                                        property("reprocessorName", getMessage("LBL_aeReport.medicalDevices.reprocessorName", "Reprocessor name")),
                                        property("reprocessorAddress", getMessage("LBL_aeReport.medicalDevices.reprocessorAddress", "Reprocessor address")),
                                        property("evaluationAvailability", getMessage("LBL_aeReport.medicalDevices.evaluationAvailability", "Evaluation availability")),
                                        property("returnedDate", getMessage("LBL_aeReport.medicalDevices.returnedDate", "Returned date"))
                            )
                        ),
                        section(DESCRIPTION_SECTION,
                            property("responseDescription",
                                        property("eventDescription", getMessage("LBL_aeReport.responseDescription.eventDescription", "Description and treatment of event(s)")),
                                        property("dateRemovedFromProtocol", getMessage("LBL_aeReport.responseDescription.dateRemovedFromProtocol", "Date removed from protocol")),
                                        property("primaryTreatmentApproximateTime.hourString", getMessage("LBL_aeReport.responseDescription.primaryTreatmentApproximateTime.hourString", "Event treatment, approximate time")),
                                        property("presentStatus", getMessage("LBL_aeReport.responseDescription.presentStatus", "Present status")),
                                        property("recoveryDate", getMessage("LBL_aeReport.responseDescription.recoveryDate", "Date of recovery or death")),
                                        property("retreated", getMessage("LBL_aeReport.responseDescription.retreated", "Has the participant been re-treated?")),
                                        property("blindBroken", getMessage("LBL_aeReport.responseDescription.blindBroken", "Was blind broken due to event?")),
                                        property("studyDrugInterrupted", getMessage("LBL_aeReport.responseDescription.studyDrugInterrupted", "Was Study Drug stopped/interrupted/reduced in response to event?")),
                                        property("reducedDose", getMessage("LBL_aeReport.responseDescription.reducedDose", "If reduced, specify: New dose")),
                                        property("reducedDate", getMessage("LBL_aeReport.responseDescription.reducedDate", "Date dose reduced")),
                                        property("daysNotGiven", getMessage("LBL_aeReport.responseDescription.daysNotGiven", "If interrupted, specify total number of days not given")),
                                        property("autopsyPerformed", getMessage("LBL_aeReport.responseDescription.autopsyPerformed", "Autopsy performed?")),
                                        property("causeOfDeath", getMessage("LBL_aeReport.responseDescription.causeOfDeath", "Cause of death") ),
                                        property("eventAbate", getMessage("LBL_aeReport.responseDescription.eventAbate", "Did event abate after study drug was stopped or dose reduced?")),
                                        property("eventReappear", getMessage("LBL_aeReport.responseDescription.eventReappear", "Did event reappear after study drug was reintroduced?"))
                            )
                        ),
                        section(AGENTS_INTERVENTION_SECTION,
                                property("treatmentInformation",
        							property("investigationalAgentAdministered", getMessage("LBL_aeReport.treatmentInformation.investigationalAgentAdministered", "Was an investigational agent administered on this protocol?")),
                                    list("courseAgents", "Study Agent",
                                            property("studyAgent", getMessage("LBL_aeReport.treatmentInformation.courseAgents.studyAgent", "Study Agent Name")),
                                            property("formulation", getMessage("LBL_aeReport.treatmentInformation.courseAgents.formulation", "Formulation")),
                                            property("lotNumber", getMessage("LBL_aeReport.treatmentInformation.courseAgents.lotNumber", "Lot # (if known)")),
                                            property("dose",
                                                    property("amount", getMessage("LBL_aeReport.treatmentInformation.courseAgents.dose.amount", "Total dose administered this course")),
                                                    property("units", getMessage("LBL_aeReport.treatmentInformation.courseAgents.dose.units", "Unit of measure"))
                                            ),
                                            property("lastAdministeredDate", getMessage("LBL_aeReport.treatmentInformation.courseAgents.lastAdministeredDate", "Date last administered")),
                                            property("administrationDelayAmount", getMessage("LBL_aeReport.treatmentInformation.courseAgents.administrationDelayAmount", "Administration Delay Amount")),
                                            property("administrationDelayUnits", getMessage("LBL_aeReport.treatmentInformation.courseAgents.administrationDelayUnits", "Administration Delay Units")),
                                            property("agentAdjustment", getMessage("LBL_aeReport.treatmentInformation.courseAgents.agentAdjustment", "Dose Modification?")),
                                            property("comments", getMessage("LBL_aeReport.treatmentInformation.courseAgents.comments", "Comments"))
                                            // dosage("modifiedDose", "Modified dose")
                                    )
                                )
                        ),
                        section(TREATMENT_INFO_SECTION,
                            property("treatmentInformation",
                                codedOrOther("treatmentAssignment", getMessage("LBL_aeReport.treatmentInformation.treatmentAssignment", "Treatment assignment code"), "treatmentDescription", getMessage("LBL_aeReport.treatmentInformation.treatmentAssignmentDescription", "Description of treatment assignment or dose level")),
								property("firstCourseDate", getMessage("LBL_aeReport.treatmentInformation.firstCourseDate", "Start date of first course/cycle")),
								property("adverseEventCourse",
                                            property("date", getMessage("LBL_aeReport.treatmentInformation.adverseEventCourse.date", "Start date of this course/cycle")),
                                            property("number", getMessage("LBL_aeReport.treatmentInformation.adverseEventCourse.number", "Course number on which event occurred")),
                                            property("totalCourses", getMessage("LBL_aeReport.treatmentInformation.totalCourses", "Total number of courses to date"))
							            )
						        )
                            ),
						section(LABS_SECTION, 
							list("labs", new LabsDisplayNameCreator(),
                                 codedOrOther("labTerm", getMessage("LBL_aeReport.labs.labTerm", "Lab test name"), "other", getMessage("LBL_aeReport.labs.other", "Other test name")),
                                 property("units", getMessage("LBL_aeReport.labs.units", "Units")),
                                 labValue("baseline", "Baseline"),
                                 labValue("nadir", "Worst"),
                                 labValue("recovery", "Recovery"), 
                                 property("site", getMessage("LBL_aeReport.labs.site", "Site")),
                                 property("labDate", getMessage("LBL_aeReport.labs.labDate", "date")), 
                                 property("infectiousAgent", getMessage("LBL_aeReport.labs.infectiousAgent", "Infectious agent"))
                           )
                        ),
                        section(OTHER_CAUSE_SECTION,
                               list("otherCauses", "OtherCauses", property("text", getMessage("LBL_aeReport.otherCauses.text", "Cause"))
                               )
                        ),

                        section(ATTRIBUTION_SECTION), // TODO: how to fill this??

                        section(ADDITIONAL_INFO_SECTION,
                        		property("additionalInformation",
                        				property("autopsyReport", getMessage("LBL_aeReport.additionalInformation.autopsyReport", "Autopsy Report")),
                        				property("pathologyReport", getMessage("LBL_aeReport.additionalInformation.pathologyReport", "Pathology Report")),
                        				property("consults", getMessage("LBL_aeReport.additionalInformation.consults", "Consults")),
                        				property("progressNotes", getMessage("LBL_aeReport.additionalInformation.progressNotes", "Progress Notes")),
                        				property("dischargeSummary", getMessage("LBL_aeReport.additionalInformation.dischargeSummary", "Discharge Summary")),
                        				property("radiologyReports", getMessage("LBL_aeReport.additionalInformation.radiologyReports", "Radiology Report")),
                        				property("flowCharts", getMessage("LBL_aeReport.additionalInformation.flowCharts", "Flow Sheets/Case Report Forms")),
                        				property("labReports", getMessage("LBL_aeReport.additionalInformation.labReports", "Laboratory Reports")),
                        				property("irbReport", getMessage("LBL_aeReport.additionalInformation.irbReport", "Summary Report Sent to IRB")),
                        				property("obaForm", getMessage("LBL_aeReport.additionalInformation.obaForm", "OBA Form")),
                        				property("other", getMessage("LBL_aeReport.additionalInformation.other", "Other")),
                        				property("otherInformation", getMessage("LBL_aeReport.additionalInformation.otherInformation", "Other Information"))
                        		)
                        ),// TODO: additional info section

                        section(SUBMIT_REPORT_SECTION),// TODO: just a space filler section

                        section(OUTCOME_SECTION),// TODO: just a space filler section
                
                        section(PRIOR_THERAPIES_SECTION,
                            	list("saeReportPriorTherapies","Prior Therapy", 
                        				property("priorTherapy", "Prior therapy"),
                                        property("other", "Comments (prior therapy)"),
                                        property("startDate", "Therapy start date",
                                        		property("year", "Year"), property("month", "Month"), property("day", "Day")),
                                        property("endDate", "Therapy end date",
                                        		property("year", "Year"), property("month", "Month"), property("day", "Day")),
                                        list("priorTherapyAgents", "PriorTherapyAgent", property("chemoAgent", "Agent")
                                        )
                                )
                            ),

                        section(PRE_EXISTING_CONDITION_SECTION, 
                        		list("saeReportPreExistingConditions","Pre-existing condition", codedOrOther("preExistingCondition","Pre-existing condition", "other","Other (pre-existing)"))
                        ),
                
                        section(CONCOMITANT_MEDICATION_SECTION, 
                        		list("concomitantMedications","Medication",
                                        property("agentName", "Medication"),
                                        property("stillTakingMedications","Continued?"),
                                        property("startDate","Start date",
                                        		property("year", "Year"), property("month", "Month"), property("day", "Day")),
                                        property("endDate","End date",
                                        		property("year", "Year"), property("month", "Month"), property("day", "Day"))
                                       
                                )
                        ),
                                
                        section(MEDICAL_INFO_SECTION,
                        		//fields - general
                        		 property("participantHistory",
                        				 participantMeasure("height"),
                                         participantMeasure("weight"),
                        				 property("baselinePerformanceStatus","Baseline performance")
                        		 ),
                        		//fields related to diseases history
                        		 property("diseaseHistory",
                                         	codedOrOther("abstractStudyDisease", "Disease name","otherPrimaryDisease","Other (disease)"),
                                         	codedOrOther("codedPrimaryDiseaseSite", "Primary site of disease", "otherPrimaryDiseaseSite", "Other (site of primary disease)"),
                                         	property("diagnosisDate","Date of initial diagnosis",
                                         			property("year", "Year"), property("month", "Month"), property("day", "Day")),
			                        		//fields related to metastatic diseases
			                        		list("metastaticDiseaseSites","Metastatic disease site",
			                        				codedOrOther("codedSite","Site name","otherSite","Other(site of metastatic disease)")
			                        		)
			                    )
                        		
                        )
        );
    }

    @Override
    public TreeNode add(TreeNode... subnodes) {
        super.add(subnodes);
        for (TreeNode subnode : subnodes) {
            if (subnode instanceof SectionNode) {
                sections.put(((SectionNode) subnode).getSection(), subnode);
            }
        }
        return this;
    }

    @Override
    public String getPropertyName() {
        return null;
    }

    public List<UnsatisfiedProperty> verifyPropertiesPresent(String nodePropertyName, ExpeditedAdverseEventReport report) {
        return verifyPropertiesPresent(Collections.singleton(nodePropertyName), report);
    }

    public List<UnsatisfiedProperty> verifyPropertiesPresent(Collection<String> nodePropertyNames, ExpeditedAdverseEventReport report) {
        List<TreeNode> propertyNodes = new LinkedList<TreeNode>();
        for (String propertyName : nodePropertyNames) {
            TreeNode node = find(propertyName);
            // HACK - if there is a property mismatch, node will be null.
            if (node == null) continue; // continue with next property.
            propertyNodes.add(node);
        }
        return verifyNodesSatisfied(propertyNodes, report);
    }

    public List<UnsatisfiedProperty> verifyNodesSatisfied(Collection<TreeNode> propertyNodes, ExpeditedAdverseEventReport report) {
        if (log.isDebugEnabled()) {
            log.debug("Examining report for satisfaction of " + propertyNodes);
        }
        List<UnsatisfiedProperty> unsatisfied = new LinkedList<UnsatisfiedProperty>();
        for (TreeNode node : propertyNodes) {
            PropertyValues values = node.getPropertyValuesFrom(report);
            for (PropertyValue pv : values.getPropertyValues()) {
                if (pv.getValue() == null) {
                    unsatisfied.add(new UnsatisfiedProperty(node, pv.getName()));
                }
            }
        }
        return unsatisfied;
    }

    public TreeNode getNodeForSection(ExpeditedReportSection section) {
        TreeNode node = sections.get(section);
        if (node == null && log.isDebugEnabled()) {
            log.debug("No node in the expedited report tree for " + section);
        }
        return node;
    }

    public ExpeditedReportSection getSectionForNode(TreeNode node) {
        if (node == null) throw new NullPointerException("No node provided");
        if (node instanceof SectionNode) return ((SectionNode) node).getSection();
        if (node.getParent() == null) throw new CaaersSystemException(node + " doesn't belong to a section");
        return getSectionForNode(node.getParent());
    }

    // //// TREE CONSTRUCTION HELPERS

    private TreeNode createPersonBlock(String person) {
        return property(person, StringUtils.capitalize(person) + " details", 
        		property("title", getMessage("LBL_aeReport." + person + ".title", "Position title")),
        		property("firstName", getMessage("LBL_aeReport." + person + ".firstName", "First name")),
        		property("middleName", getMessage("LBL_aeReport." + person + ".middleName", "Middle name")),
        		property("lastName", getMessage("LBL_aeReport." + person + ".lastName", "Last name")),
        		contactField(ReportPerson.EMAIL, getMessage("LBL_aeReport." + person + ".contactMechanisms[e-mail]", "E-mail address")),
        		contactField(ReportPerson.PHONE, getMessage("LBL_aeReport." + person + ".contactMechanisms[phone]", "Phone")),
        		contactField(ReportPerson.FAX, getMessage("LBL_aeReport." + person + ".contactMechanisms[fax]", "Fax")),
        		property("address", getMessage("LBL_aeReport." + person + ".address", "Address"),
        				property("street", getMessage("LBL_aeReport." + person + ".address.street", "Street")),
        				property("city", getMessage("LBL_aeReport." + person + ".address.city", "City")),
        				property("state", getMessage("LBL_aeReport." + person + ".address.state", "State")),
        				property("zip", getMessage("LBL_aeReport." + person + ".address.zip", "Zip"))));
    }

/*
    private static TreeNode contactField(String contactType) {
        return contactField(contactType, StringUtils.capitalize(contactType));
    }

*/
    private static TreeNode contactField(String contactType, String displayName) {
        return property("contactMechanisms[" + contactType + ']', displayName);
    }

    private TreeNode participantMeasure(String baseName) {
        return property(baseName, StringUtils.capitalize(baseName),
                property("quantity",  getMessage("LBL_aeReport.participantHistory." + baseName + ".quantity", "Quantity")),
                property("unit", getMessage("LBL_aeReport.participantHistory.measure.units", "Units")));
    }

    private static TreeNode dosage(String baseName, String displayName) {
        return property(baseName, displayName, property("amount", "Amount"), property("units","Units")
        // ,property("route", "Route")
        );
    }

    private static TreeNode labValue(String baseName, String displayName) {
        return property(baseName, displayName, property("value", "Value"), property("date", "Date"));
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String label, String defaultMessage) {
        if (getMessageSource() == null) return defaultMessage;
        try {
            return getMessageSource().getMessage(label, null, Locale.getDefault());
//            return "&lt;" + getMessageSource().getMessage(label, null, Locale.getDefault()) + "&gt;";
        } catch (NoSuchMessageException e) {
            log.debug(e.getMessage());
            return defaultMessage;
        }
    }
}
