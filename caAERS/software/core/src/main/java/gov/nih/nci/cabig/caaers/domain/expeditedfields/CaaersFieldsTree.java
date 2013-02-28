/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import org.springframework.context.MessageSource;

import static gov.nih.nci.cabig.caaers.domain.expeditedfields.TabSection.*;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

 
/**
 * The Class CaaersFieldsTree.
 *
 * @author Sameer Sawant
 * @author Ion C. Olaru
 * @author Biju Joseph
 * This tree represents the fields (attributes) in various pages of tabs of the caAERS UI.
 * Its used in the page under admin to configure the various fields to make it Mandatory/
 * Optional/Not applicable on the page.
 */
public class CaaersFieldsTree extends PropertylessNode {

	/** The sections. */
	private Map<TabSection, TreeNode> sections;
    
    /** The message source. */
    private MessageSource messageSource;
    
    /** The expedited report tree. */
    private ExpeditedReportTree expeditedReportTree;

	/**
	 * Instantiates a new caaers fields tree.
	 *
	 * @param messageSource the message source
	 * @param expeditedReportTree the expedited report tree
	 */
	public CaaersFieldsTree(MessageSource messageSource, ExpeditedReportTree expeditedReportTree) {
        this.messageSource = messageSource;
        this.expeditedReportTree = expeditedReportTree;
        
		sections = new LinkedHashMap<TabSection, TreeNode>();
        add(
                section(CAPTURE_AE_TAB_SECTION,
                                list("adverseEvents", new AdverseEventsDisplayNameCreator(),
                                        property("grade", getMessage("LBL_aeReport.adverseEvents.grade", "Grade_")),
                                        property("adverseEventCtcTerm", property("term", getMessage("LBL_aeReport.adverseEvents.ctcTerm", "CTC term"))),
                                        property("detailsForOther", getMessage("LBL_aeReport.adverseEvents.detailsForOther", "Verbatim")),
                                        property("gradedDate", getMessage("LBL_aeReport.adverseEvents.gradedDate", "Awareness  date")),
                                        property("startDate", getMessage("LBL_aeReport.adverseEvents.startDate", "Start date")),
                                        property("endDate", getMessage("LBL_aeReport.adverseEvents.endDate", "End date")),
                                        property("attributionSummary", getMessage("LBL_aeReport.adverseEvents.attributionSummary", "Attribution to study intervention")),
                                        property("hospitalization", getMessage("LBL_aeReport.adverseEvents.hospitalization", "Hospitalization")),
                                        property("expected", getMessage("LBL_aeReport.adverseEvents.expected", "Expected")),
                                        property("participantAtRisk", getMessage("LBL_aeReport.adverseEvents.participantAtRisk", "Does this place participant at increased risk?")),
                                        property("eventLocation", getMessage("LBL_aeReport.adverseEvents.eventLocation", "Where was the patient when the event occurred?")),
                                        property("eventApproximateTime.hourString", getMessage("LBL_aeReport.adverseEvents.eventApproximateTime.hourString", "Time of event")),
                                        property("outcomes", getMessage("LBL_aeReport.adverseEvents.outcomes", "Outcomes"))
                                 )
                        ),
                section(COURSE_CYCLE_SECTION,
                                    property("assignment",
                                                property("startDateOfFirstCourse", getMessage("LBL_assignment.startDateOfFirstCourse", ""))),
                                    property("reportingPeriod",
                                                property("startDate", getMessage("LBL_reportingPeriod.startDate", "")),
                                                property("endDate", getMessage("LBL_reportingPeriod.endDate", "")),
                                                property("epoch", getMessage("LBL_reportingPeriod.epoch", "")),
                                                property("cycleNumber", getMessage("LBL_reportingPeriod.cycleNumber", "")),
                                                property("treatmentAssignment", getMessage("LBL_reportingPeriod.treatmentAssignment", ""))
                                            )
						        //)
                        )
        );
	}
	
    /**
     * Re-initializes the fields in the tree. 
     */
    public synchronized void initialize() {
       if(expeditedReportTree != null) expeditedReportTree.reinitialize();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode#add(gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode[])
     */
    @Override
    public TreeNode add(TreeNode... subnodes) {
        super.add(subnodes);
        for (TreeNode subnode : subnodes) {
            if (subnode instanceof TabSectionNode) {
                sections.put(((TabSectionNode) subnode).getSection(), subnode);
            }
        }
        return this;
    }

    /**
     * Section.
     *
     * @param section the section
     * @param children the children
     * @return the tree node
     */
    public static TreeNode section(TabSection section, TreeNode... children) {
        return new TabSectionNode(section).add(children);
    }

    /**
     * Gets the node for section.
     *
     * @param section the section
     * @return the node for section
     */
    public TreeNode getNodeForSection(TabSection section) {
        TreeNode node = sections.get(section);
        if (node == null && log.isDebugEnabled()) {
            log.debug("No node in the expedited report tree for " + section);
        }
        return node;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.expeditedfields.PropertylessNode#getPropertyName()
     */
    @Override
    public String getPropertyName() {
        return null;
    }

    /**
     * Gets the message.
     *
     * @param label the label
     * @param defaultMessage the default message
     * @return the message
     */
    public String getMessage(String label, String defaultMessage) {
        if (getMessageSource() == null) return defaultMessage;
        return getMessageSource().getMessage(label, null, defaultMessage, Locale.getDefault());
    }

    /**
     * Gets the message source.
     *
     * @return the message source
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * Sets the message source.
     *
     * @param messageSource the new message source
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Gets the expedited report tree.
     *
     * @return the expedited report tree
     */
    public ExpeditedReportTree getExpeditedReportTree() {
        return expeditedReportTree;
    }

    /**
     * Sets the expedited report tree.
     *
     * @param expeditedReportTree the new expedited report tree
     */
    public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
        this.expeditedReportTree = expeditedReportTree;
    }
}
