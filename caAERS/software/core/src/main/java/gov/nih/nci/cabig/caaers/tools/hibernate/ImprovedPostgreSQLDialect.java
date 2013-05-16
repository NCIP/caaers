/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.tools.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.type.IntegerType;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph - added bitwise operator support
 */
/* TODO: this class is shared with PSC. Refactor into a shared library. */
public class ImprovedPostgreSQLDialect extends PostgreSQLDialect {

    public ImprovedPostgreSQLDialect() {
        registerFunction("bitand", new BitAndSqlFunction("bitand", IntegerType.INSTANCE, "postgresql"));
    }

    public Class getNativeIdentifierGeneratorClass() {
        return IdentityGenerator.class;
    }
}
