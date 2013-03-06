/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields;

/**
 * @author: Ion C. Olaru
 */
public interface FieldDecorator {
    /*
    *   Decorate the field appropriately filling some attributes.
    * */
    public void decorate(InputField f);
}

