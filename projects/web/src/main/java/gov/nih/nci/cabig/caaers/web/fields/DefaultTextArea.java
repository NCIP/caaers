package gov.nih.nci.cabig.caaers.web.fields;

/**
 * @author Rhett Sutphin
 */
public class DefaultTextArea extends AbstractInputField {
    public DefaultTextArea() { }

    public DefaultTextArea(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    @Override
    public Category getCategory() {
        return Category.TEXTAREA;
    }
    
    public void setColumns(int cols){
    	getAttributes().put(InputField.COLS, cols);
    }
    public int getColumns(){
    	Integer i = (Integer)getAttributes().get(InputField.COLS);
    	return (i == null) ? 0 : i.intValue();
    }
}
