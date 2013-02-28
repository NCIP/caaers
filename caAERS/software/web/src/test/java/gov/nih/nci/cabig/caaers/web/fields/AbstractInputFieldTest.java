/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.validation.fields.validators.FieldValidator;
import junit.framework.TestCase;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ion C. Olaru
 * @author Biju Joseph
 * 
 */
public class AbstractInputFieldTest extends TestCase {
    protected Errors errors;

    public void testRequiredTogetherWithOtherValidators() {
        AbstractInputField field = new AbstractInputField("propertyName","displayName",  "labelProperty", true, FieldValidator.EMAIL_VALIDATOR, FieldValidator.DATE_VALIDATOR) {
            @Override
            public Category getCategory() {
                return Category.TEXT;
            }
        };

        assertEquals(3, field.getValidators().length);
        assertEquals(FieldValidator.EMAIL_VALIDATOR, field.getValidators()[0]);
        assertEquals(FieldValidator.DATE_VALIDATOR, field.getValidators()[1]);
        assertEquals(FieldValidator.NOT_NULL_VALIDATOR, field.getValidators()[2]);
    }


    public void testIsValidatable(){
        AbstractInputField field = new AbstractInputField("propertyName","displayName",  "labelProperty", true, FieldValidator.EMAIL_VALIDATOR, FieldValidator.DATE_VALIDATOR) {
            @Override
            public Category getCategory() {
                return Category.TEXT;
            }
        };
        field.setReadable(false);
        field.setModifiable(true);

        assertFalse(field.isValidateable());
        
        field.setReadable(true);
        field.setModifiable(false);
        
        assertTrue(field.isReadable());
        assertFalse(field.isModifiable());

        assertFalse(field.isValidateable());


        field.setReadable(true);
        field.setModifiable(true);
        assertTrue(field.isValidateable());


    }


    public void testValidate(){

       class B {
           String name;
           public String getName(){
               return name;
           }
           public void setName(String name){
               this.name = name;
           }

       }

        B b = new B();

       errors = new BindException(b, "command");
       InputField field = InputFieldFactory.createTextField("name", "name", true);
       field.validate(new BeanWrapperImpl(b), errors);
       assertTrue(errors.hasErrors());
       assertNotNull(errors.getFieldError("name"));

       field.setReadable(false);
       errors = new BindException(b, "command");
       field.validate(new BeanWrapperImpl(b), errors);
       assertFalse(errors.hasErrors()); 
    }

}
