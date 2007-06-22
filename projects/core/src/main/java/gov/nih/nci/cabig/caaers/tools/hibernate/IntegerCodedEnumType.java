package gov.nih.nci.cabig.caaers.tools.hibernate;

import java.sql.Types;

/**
 * @author Rhett Sutphin
 */
public class IntegerCodedEnumType extends CodedEnumType {
    @Override
    protected int codeSqlType() {
        return Types.INTEGER;
    }

    @Override
    @SuppressWarnings("RawUseOfParameterizedType")
    protected Class codeJavaType() {
        return Integer.TYPE;
    }
}
