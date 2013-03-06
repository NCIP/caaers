/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.validation.fields.validators.FieldValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public abstract class QualifiedPropertyNameInputField implements InputField {

    private InputField src;
    private Map<String, Object> attributes;

    
    public QualifiedPropertyNameInputField(InputField src) {
        this.src = src;
        setAttributes(src.getAttributes());
    }

    protected InputField getSourceField() {
        return src;
    }

    /**
     * This method will validate against the Source field validators.
     */
    public void validate(BeanWrapper commandBean, Errors errors) {
        AbstractInputField.validate(this, commandBean, errors);
    }

    public FieldValidator[] getValidators() {
        return src.getValidators();
    }

    public Category getCategory() {
        return getSourceField().getCategory();
    }

    public String getCategoryName() {
        return getSourceField().getCategoryName();
    }

    public String getDisplayName() {
        return getSourceField().getDisplayName();
    }

    public boolean isRequired() {
        return getSourceField().isRequired();
    }

    public String getPropertyName() {
        return qualifyPropertyName(getSourceField().getPropertyName());
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    protected abstract String qualifyPropertyName(String propertyName);

    @SuppressWarnings("unchecked")
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = new LinkedHashMap<String, Object>(attributes);
        if (attributes.containsKey(SUBFIELDS)) {
            List<InputField> subfields = (List<InputField>) attributes.get(SUBFIELDS);
            List<InputField> newSubfields = new ArrayList<InputField>(subfields.size());
            for (InputField subfield : subfields) {
                newSubfields.add(qualifySubfield(subfield));
            }
            this.attributes.put(SUBFIELDS, newSubfields);
        }
    }

    protected abstract InputField qualifySubfield(InputField subfield);

    public FieldValidator getValidatorOfType(Class<? extends FieldValidator> klass) {
        return src.getValidatorOfType(klass);
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append("[propertyName=").append(
                getPropertyName()).append("; source propertyName=").append(
                getSourceField().getPropertyName()).append(']').toString();
    }

    public String getValidatorClassName() {
        return src.getValidatorClassName();
    }



    public String getDisplayTextProperty() {
        return src.getDisplayTextProperty();
    }

    

    /**
     * Will be true, if the property represented by this field can be modified.
     *
     * @return
     */
    public boolean isModifiable() {
        return src.isModifiable();
    }

    public void setModifiable(boolean modifiable){
        src.setModifiable(modifiable);
    }

    /**
     * Will be true if the property represented by this field can be read
     *
     * @return
     */
    public boolean isReadable() {
        return src.isReadable();
    }

    public void setReadable(boolean readable){
        src.setReadable(readable);
    }

    /**
     * Will return true, if the property represented by this field can be validated.
     *
     * @return
     */
    public boolean isValidateable() {
        return src.isModifiable();
    }

    /**
     * The privilege required to read this field.
     *
     * @return
     */
    public String getPrivilegeToRead() {
        return src.getPrivilegeToRead();
    }

    public void setPrivilegeToRead(String privilege) {
        src.setPrivilegeToRead(privilege);
    }


    /**
    * The security privilege needed to Modify this field
    *
    * @return
    */
    public String getPrivilegeToModify() {
        return src.getPrivilegeToModify();
    }

    public void setPrivilegeToModify(String privilege) {
       this.src.setPrivilegeToModify(privilege);
    }
    

}
