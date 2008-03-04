package gov.nih.nci.cabig.caaers.tools.hibernate;

import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.id.IdentityGenerator;

/**
 * @author Rhett Sutphin
 */
/* TODO: this class is shared with PSC. Refactor into a shared library. */
public class ImprovedPostgreSQLDialect extends PostgreSQLDialect {
    public Class getNativeIdentifierGeneratorClass() {
        return IdentityGenerator.class;
    }
}
