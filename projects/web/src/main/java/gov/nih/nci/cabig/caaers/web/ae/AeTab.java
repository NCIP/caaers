package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.service.ErrorMessages;
import gov.nih.nci.cabig.caaers.service.ReportService;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public abstract class AeTab extends TabWithFields<ExpeditedAdverseEventInputCommand> {

	private ExpeditedReportTree expeditedReportTree;
	protected ReportService reportService;

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

	   boolean mandatory;
	   for(InputFieldGroup group : groupMap.values()){
		  for(InputField field : group.getFields()){
			 mandatory = fetchMandatoryValue(mandatoryFields, field);
			 if(mandatory) field.setMandatory(mandatory);
		  }
	   }
    }
    /**
     * Tells whether the given field is mandatory.
     * Incase of Composite fields, the given field (parent) will be marked mandatory if any of its subfields
     * are mandatory.
     * @param mandatoryFieldMap
     * @param field
     * @return
     */
    public boolean fetchMandatoryValue(Map<String, Boolean> mandatoryFieldMap , InputField field){
    	boolean mandatory;
    	Boolean objMandatoryFlag;
    	String propertyName = field.getPropertyName().replaceAll("(\\[\\d+\\])", "[]");
    	if(propertyName.indexOf('.') > 0){
		  objMandatoryFlag = mandatoryFieldMap.get(propertyName.split("\\.", 2)[1]);
    	}else{
		  objMandatoryFlag = mandatoryFieldMap.get(propertyName);
    	}
    	mandatory = (objMandatoryFlag == null) ? false : objMandatoryFlag.booleanValue();
    	if(field.getCategory() == InputField.Category.COMPOSITE){
    		for(InputField subfield : CompositeField.getSubfields(field))
    			mandatory |= fetchMandatoryValue(mandatoryFieldMap, subfield);
    	}
    	return mandatory;
    }

   /**
    * Check's whether this tab is mandatory
    */
    public boolean isMandatory(ExpeditedAdverseEventInputCommand command){
    	//TODO: change to Enums
    	List<String> sections = command.getMandatorySections();
    	if(sections == null || sections.isEmpty()) return false;
    	return sections.contains(section().displayName());
    }

    public boolean hasEmptyMandatoryFields(ExpeditedAdverseEventInputCommand command){
    	Map<String, Boolean> mandatoryFields = command.getMandatoryFieldMap();
    	if(mandatoryFields.isEmpty()) return false;

    	BeanWrapper wrappedCommand = new BeanWrapperImpl(command.getAeReport());
    	ErrorMessages messages = new ErrorMessages();
    	TreeNode node = expeditedReportTree.getNodeForSection(section());
    	if(node == null) return false;
    	reportService.validate(wrappedCommand, mandatoryFields, node, messages);

    	return messages.hasErrors();
    }

    public abstract ExpeditedReportSection section();

	public ExpeditedReportTree getExpeditedReportTree() {
		return expeditedReportTree;
	}

	public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
		this.expeditedReportTree = expeditedReportTree;
	}

	public void setReportService(ReportService reportService) {
	    this.reportService = reportService;
	}


}
