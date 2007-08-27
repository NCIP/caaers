package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
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
	   Object fieldGroups = refData.get("fieldGroups");
	   populateMandatoryFlag(fieldGroups, command, refData);
	   return refData;
   }



   /**
    * Will populate the mandatory flag.
    */
    @SuppressWarnings("unchecked")
    public void populateMandatoryFlag(Object fieldGroups, ExpeditedAdverseEventInputCommand command, Map<String, Object> refData){
      //TODO: need to see how to manage (this or that) kind mandatory fields
      //TODO: Why not this we handle in createFields() of every tab, so that the looping through the fields
      // here can be avoided.
	   if(!isMandatory(command)) return;

	   Map<String, InputFieldGroup> groupMap = (Map<String, InputFieldGroup>)fieldGroups;
	   if(groupMap == null ) return;
	   Map<String, Boolean> mandatoryFields = command.getMandatoryFieldMap();

	   Boolean mandatory;
	   for(InputFieldGroup group : groupMap.values()){
		  for(InputField field : group.getFields()){
		    mandatory = mandatoryFields.get(field.getPropertyName().split("\\.", 2)[1]);
			if(mandatory != null && mandatory){
				field.setMandatory(true); //there may exist 2 reportdefs contradicting each other.
			}
		  }
	   }
    }

   /**
    * Check's whether this tab is mandatory
    */
    public boolean isMandatory(ExpeditedAdverseEventInputCommand command){
    	//TODO: change to Enums
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

	public abstract ExpeditedReportSection section();
}
