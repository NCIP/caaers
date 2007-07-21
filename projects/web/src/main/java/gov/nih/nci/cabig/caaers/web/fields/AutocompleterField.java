package gov.nih.nci.cabig.caaers.web.fields;

/**
 * @author Rhett Sutphin
 */
public class AutocompleterField extends AbstractInputField {
    public AutocompleterField() { }

    public AutocompleterField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    @Override
    public Category getCategory() {
        return Category.AUTOCOMPLETER;
    }
    
    public void setSize(int size){
    	getAttributes().put(InputField.SIZE, size);
    }
    public int getSize(){
    	Integer i = (Integer)getAttributes().get(InputField.SIZE);
    	return (i == null) ? 0 : i.intValue();
    }
    public void setEnableClearButton(boolean b){
    	getAttributes().put(InputField.ENABLE_CLEAR , b);
    }
    public boolean isEnableClearButton(){
    	Boolean b = (Boolean)getAttributes().get(InputField.ENABLE_CLEAR);
    	return (b == null)? false : b.booleanValue();
    }
}
