package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;


import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.web.fields.AbstractInputField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

/**
 * @author Rhett Sutphin
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public abstract class AeTab extends TabWithFields<ExpeditedAdverseEventInputCommand> {

	private ExpeditedReportTree expeditedReportTree;

    public AeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

  /**
   * Will also update the InputField mandatory flag.
   */
   @Override
   public Map<String, Object> referenceData(ExpeditedAdverseEventInputCommand command) {
	   Map <String, Object> refData = super.referenceData(command);
	   populateSetMandatory(command, refData);
	   return refData;
   }

   /**
    * Will populate the mandatory flag.
    */
    @SuppressWarnings("unchecked")
    public void populateSetMandatory(ExpeditedAdverseEventInputCommand command, Map<String, Object> refData){

	   if(!isMandatory(command)) return;

	   Map<String, InputFieldGroup> groupMap = (Map<String, InputFieldGroup>)refData.get("fieldGroups");
	   if(groupMap == null ) return;
	   List<String> sections = command.getMandatorySections();
	   if(sections == null || sections.isEmpty()) return;
	   List<Report> reports = command.getAeReport().getReports();
	   if(reports ==  null || reports.isEmpty()) return;
	   ReportDefinition reportDef;
	   boolean mandatory;
	   for(InputFieldGroup group : groupMap.values()){
		  for(InputField field : group.getFields()){
			  for(Report report : reports){
				  reportDef = report.getReportDefinition();
				  mandatory = reportDef.isFieldMandatory(field.getPropertyName().split("\\.", 2)[1]);
				  if(mandatory){
					  field.setMandatory(true); //there may exist 2 reportdefs contradicting each other.
				  }
			  }
		  }
	   }
    }

   /**
    * Check's whether this tab is mandatory
    */
    public boolean isMandatory(ExpeditedAdverseEventInputCommand command){
    	List<String> sections = command.getMandatorySections();
    	if(sections == null || sections.isEmpty()) return false;
    	return sections.contains(getLongTitle());
    }


	public ExpeditedReportTree getExpeditedReportTree() {
		return expeditedReportTree;
	}

	public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
		this.expeditedReportTree = expeditedReportTree;
	}


}
