/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class DefaultInputFieldGroup extends AbstractInputFieldGroup {
    private List<InputField> fields = new LinkedList<InputField>();

    public DefaultInputFieldGroup() {
    }

    public DefaultInputFieldGroup(String name) {
        super(name);
    }

    public List<InputField> getFields() {
        return fields;
    }

    public void setFields(List<InputField> fields) {
        this.fields = fields;
    }
}
