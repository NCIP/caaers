package gov.nih.nci.cabig.caaers.tools.hibernate;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.IntegerType;

/**
 * Created with IntelliJ IDEA.
 * User: Biju
 * Date: 5/13/13
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImprovedOracle10gDialect extends Oracle10gDialect {
    public ImprovedOracle10gDialect() {
        registerFunction("bitand", new BitAndSqlFunction("bitand", IntegerType.INSTANCE, "oracle"));
    }
}
