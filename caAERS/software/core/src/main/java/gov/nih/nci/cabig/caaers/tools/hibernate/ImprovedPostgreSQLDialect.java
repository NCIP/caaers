/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
