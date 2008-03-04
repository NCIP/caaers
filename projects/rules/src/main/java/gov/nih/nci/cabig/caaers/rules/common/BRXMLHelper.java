package gov.nih.nci.cabig.caaers.rules.common;

import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;

/**
 * @author Sujith Vellat Thayyilthodi
 */
public class BRXMLHelper {

    public static Condition newCondition() {
        Condition condition = new Condition();
        Column column = newColumn();
        condition.getColumn().add(column);
        return condition;
    }

    public static Column newColumn() {
        Column column = new Column();
        FieldConstraint fieldConstraint = newFieldConstraint();
        column.getFieldConstraint().add(fieldConstraint);
        return column;
    }

    public static FieldConstraint newFieldConstraint() {
        FieldConstraint fieldConstraint = new FieldConstraint();
        LiteralRestriction literalRestriction = new LiteralRestriction();
        fieldConstraint.getLiteralRestriction().add(literalRestriction);
        return fieldConstraint;
    }
}
