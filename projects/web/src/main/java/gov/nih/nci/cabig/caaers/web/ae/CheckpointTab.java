package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin
 */
public class CheckpointTab extends AeTab {
    private EvaluationService evaluationService;
    private static final Log log = LogFactory.getLog(CheckpointTab.class);
    
    public CheckpointTab() {
        super("Is expedited reporting necessary?", "Select Report", "ae/checkpoint");
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.CHECKPOINT_SECTION;
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputFieldGroup optional = new DefaultInputFieldGroup("optionalReports");
        for (ReportDefinition reportDefinition : command.getOptionalReportDefinitionsMap().keySet()) {
            optional.getFields().add(InputFieldFactory.createCheckboxField(
                "optionalReportDefinitionsMap[" + reportDefinition.getId() + ']',
                reportDefinition.getName() + " (" + reportDefinition.getOrganization().getName() + ')'
            ));
        }

        creator.addUnprocessedFieldGroup(optional);
    }

    @Override
    public void onDisplay(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
    	//evalutate available report definitions per session.
    	if(command.getAllReportDefinitions() == null || command.getAllReportDefinitions().isEmpty()){
    		command.setAllReportDefinitions(evaluationService.applicableReportDefinitions(command.getAssignment()));
    	}
    	
    	//identify the report definitions mandated by Rules engine
    	if(command.getRequiredReportDeifnitions().isEmpty()){
    		command.setRequiredReportDefinition(evaluationService.findRequiredReportDefinitions(command.getAeReport()));
    	}
        
    	//already AE report is saved.
    	if(command.getAeReport().getId() != null){
        	//set up selected reports
    		command.refreshSelectedReportDefinitionsMap(command.getInstantiatedReportDefinitions());
    		//set up the optional reports
        	command.setOptionalReportDefinitions(createOptionalReportDefinitionsList(command));
     	}else{
     		//set up the optional reports
        	command.setOptionalReportDefinitions(createOptionalReportDefinitionsList(command));
    		//new, so no reports are associated with this yet. 
        	command.setSelectedReportDefinitions(command.getRequiredReportDeifnitions());
    	}
    }

    private List<ReportDefinition> createOptionalReportDefinitionsList(ExpeditedAdverseEventInputCommand command) {
    	if(command.getSelectedReportDefinitions() == null) return command.getAllReportDefinitions();
    	
    	return ListUtils.subtract(command.getAllReportDefinitions(), command.getSelectedReportDefinitions());
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors ) {
        boolean anyReports = command.getAeReport().getReports().size() > 0;
        for (ReportDefinition def : command.getOptionalReportDefinitionsMap().keySet()) {
            anyReports |= reportSelected(command, def);
        }
        if (!anyReports) {
            errors.reject("AT_LEAST_ONE_REPORT", "At least one expedited report must be selected to proceed");
        }
    }
    
    @Override
    public void onBind(HttpServletRequest request,ExpeditedAdverseEventInputCommand command, Errors errors) {
    	super.onBind(request, command, errors);
    	//explicitly call setSelectedReportDefinitionNames
    	command.setSelectedReportDefinitionNames(request.getParameter("selectedReportDefinitionNames"));
    }
    
    @Override
    public void postProcess(HttpServletRequest request, ExpeditedAdverseEventInputCommand command, Errors errors) {
    	
    	List<ReportDefinition> newlySelectedDefs = newlySelectedReportDefinitions(command);
    	removeUnselectedReports(command);
    	
    	if(newlySelectedDefs != null){
    		evaluationService.addOptionalReports(command.getAeReport(), newlySelectedDefs);
    	}
    	
        if (command.getAeReport().getReports().size() > 0) {
            command.save();
        }
        //find the mandatory sections.
        if (command.getMandatoryProperties() == null) {
            command.setMandatorySections(evaluationService.mandatorySections(command.getAeReport()));
            command.refreshMandatoryProperties();
        }
        
        if(command.getMandatorySections() != null){
        	//pre-initialize lazy fields in mandatory sections.
        	BeanWrapper wrapper = new BeanWrapperImpl(command.getAeReport());
            for(ExpeditedReportSection section : command.getMandatorySections()){
            	assert (section != null) : "A section is null in command.getManatorySections()";
            	TreeNode sectionNode = getExpeditedReportTree().getNodeForSection(section);
            	if(sectionNode == null) log.warn("Unable to fetch TreeNode for section" + section.name());
            	assert (sectionNode != null) : section.toString() + ", is not available in ExpeditedReportTree.";
            	for(TreeNode node : sectionNode.getChildren()){
            		if(node.isList()){
            			wrapper.getPropertyValue(node.getPropertyName()+"[0]");
            		}
            	}
            }
        }
    }
    
    private List<ReportDefinition> newlySelectedReportDefinitions(ExpeditedAdverseEventInputCommand command){
    	List<ReportDefinition> selectedReportDefs = command.getSelectedReportDefinitions();
    	List<ReportDefinition> instantiatedReportDefs = command.getInstantiatedReportDefinitions();
    	List<ReportDefinition> difference =  ListUtils.subtract(selectedReportDefs, instantiatedReportDefs);
    	return difference;
    }
    
   private void removeUnselectedReports(ExpeditedAdverseEventInputCommand command){
	  List<Report> reports = command.getAeReport().getReports();
	  for(Report report : reports){
		  if(report.getStatus() == ReportStatus.WITHDRAWN) continue;
		  if(!reportSelected(command, report.getReportDefinition())){
			  reportService.deleteReport(report);
		  }
	  }
   }

    private boolean reportSelected(ExpeditedAdverseEventInputCommand command, ReportDefinition def) {
        Boolean val = command.getOptionalReportDefinitionsMap().get(def);
        return val == null ? false : val;
    }

    ////// CONFIGURATION

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
}
