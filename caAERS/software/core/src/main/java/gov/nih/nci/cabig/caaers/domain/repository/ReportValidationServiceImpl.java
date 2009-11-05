package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.UnsatisfiedProperty;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Sameer Sawant
 */
public class ReportValidationServiceImpl implements ReportValidationService{
	
	private ExpeditedReportTree expeditedReportTree;
	
	public ReportSubmittability isSubmittable(Report report) {
        return validate(report, Arrays.asList(ExpeditedReportSection.values()));
    }
	
	/**
     * Will tell whether all the mandatory field for this report is duly filled.
     * Internally this will call the validate method for each element having children in the {@link ExpeditedReportTree}
     *
     * @param mandatorySections
     * @return ErrorMessages
     */
    public ReportSubmittability validate(Report report, Collection<ExpeditedReportSection> mandatorySections) {
        // TODO: should validate against complex rules

        ReportSubmittability messages = new ReportSubmittability();
        List<String> mandatoryFields = report.getMandatoryFieldList();

        for (ExpeditedReportSection section : mandatorySections) {
            if (section == null)
                throw new NullPointerException("The mandatory sections collection must not contain nulls");
            validate(report.getAeReport(), mandatoryFields, section, messages);
        }

        //biz rule - Attribution validation should be done if the ReportDefinition says that it is attributable
        if( report.getReportDefinition().getAttributionRequired()){
        	ExpeditedAdverseEventReport aeReport = report.getAeReport();
        	for (AdverseEvent ae : aeReport.getAdverseEvents()) {
        		Attribution max = null;
        		for (AdverseEventAttribution<?> attribution : ae.getAdverseEventAttributions()) {
        			if(attribution.getAttribution() == null) {max = null; break;} //special case when people click save again (after an error).
        			if (max == null || attribution.getAttribution().getCode() > max.getCode()) {
        				max = attribution.getAttribution();
		    		}
		    	}
        		if (max == null || max.getCode() < Attribution.POSSIBLE.getCode()) {
        			messages.addValidityMessage(ExpeditedReportSection.ATTRIBUTION_SECTION,
		    		String.format(
		    			"The adverse event, '%s, ' is not attributed to a cause. " +
		    			"An attribution of possible or higher must be selected for at least one of the causes.",
		    			ae.getAdverseEventTerm().getUniversalTerm()));
        		}
		    }
        }
        
        //biz rule - Physician Sign-Off should be true if the ReportDefinition says that Physician Sign-Off is needed.
        if(report.getReportDefinition().getPhysicianSignOff()){
        	if(report.getPhysicianSignoff() == null || !report.getPhysicianSignoff()){
        		messages.addValidityMessage(ExpeditedReportSection.SUBMIT_REPORT_SECTION, 
        				"Physician sign-off is mandatory for this report.");
        	}
        }
       return messages;
    }
    
    @SuppressWarnings("unchecked")
    private void validate(
            ExpeditedAdverseEventReport aeReport, List<String> mandatoryFields, ExpeditedReportSection section,
            ReportSubmittability messages
    ) {
        TreeNode sectionNode = expeditedReportTree.getNodeForSection(section);
        if (sectionNode == null)
            throw new CaaersSystemException("There is no section node in the report tree for " + section.name() + ".  This shouldn't be possible.");

        List<String> applicableFields = new LinkedList<String>();
        for (String field : mandatoryFields) {
            TreeNode n = sectionNode.find(field);
            if (n == null) continue;
            applicableFields.add(field);
        }
        List<UnsatisfiedProperty> unsatisfied = expeditedReportTree.verifyPropertiesPresent(
                applicableFields, aeReport);
        for (UnsatisfiedProperty uProp : unsatisfied) {
            TreeNode unsatisfiedNode = uProp.getTreeNode();

            messages.addMissingField(
                    section,
                    uProp.getDisplayName(),
                    uProp.getBeanPropertyName());
        }
    }
    
    @Required
    public void setExpeditedReportTree(final ExpeditedReportTree expeditedReportTree) {
        this.expeditedReportTree = expeditedReportTree;
    }
}