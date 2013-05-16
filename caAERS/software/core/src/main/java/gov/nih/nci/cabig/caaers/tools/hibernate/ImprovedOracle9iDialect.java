package gov.nih.nci.cabig.caaers.tools.hibernate;

import org.hibernate.type.IntegerType;

/**
 * Created with IntelliJ IDEA.
 * User: Biju
 * Date: 5/13/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImprovedOracle9iDialect extends org.hibernate.dialect.Oracle9iDialect {
    public ImprovedOracle9iDialect() {
        registerFunction("bitand", new BitAndSqlFunction("bitand", IntegerType.INSTANCE, "oracle"));
    }
}
