package gov.nih.nci.cabig.caaers.web.fields;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class MultipleFieldGroupFactory extends RepeatingFieldGroupFactory{
	private List<InputFieldGroup> inputFieldGroup = new LinkedList<InputFieldGroup>();

    public MultipleFieldGroupFactory(String basename, String listPropertyName) {
    	super(basename, listPropertyName);
    }

    
    public void addFieldGroup(InputFieldGroup fieldGroup){
    	inputFieldGroup.add(fieldGroup);
    }
    
    public void clearFields(){
    	baseFields = new LinkedList<InputField>();
    }
    
    public List<InputFieldGroup> getInputFieldGroup() {
		return inputFieldGroup;
	}
    
    public void setInputFieldGroup(List<InputFieldGroup> inputFieldGroup) {
		this.inputFieldGroup = inputFieldGroup;
	}
    public void setUpFieldGroup(){
    	addFieldGroup(super.createGroup(inputFieldGroup.size()));
    	clearFields();
    }
}
