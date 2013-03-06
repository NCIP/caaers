/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

/**
 * @author Rhett Sutphin
 */
public class DefaultTextArea extends AbstractInputField {
    public DefaultTextArea() {
    }

    public DefaultTextArea(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    @Override
    public Category getCategory() {
        return Category.TEXTAREA;
    }
}
