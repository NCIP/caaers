package gov.nih.nci.cabig.caaers.tools.hibernate;

/**
 * Updated the dialect to use 4000 characters for "string" data types in bering.
 * @see : https://github.com/rsutphin/bering/blob/master/core/src/main/java/edu/northwestern/bioinformatics/bering/dialect/Oracle.java#L96
 */
import edu.northwestern.bioinformatics.bering.TypeQualifiers;
import edu.northwestern.bioinformatics.bering.dialect.Oracle;
import static java.sql.Types.*;
public class ImprovedBeringOracleDialect extends Oracle {

    private static int VARCHAR2_CHAR_LIMIT = 4000;

    @Override
    protected TypeQualifiers getDefaultTypeQualifiers(int typeCode) {

        switch (typeCode) {
            case VARCHAR: return new TypeQualifiers(VARCHAR2_CHAR_LIMIT, null, null);
            default: return super.getDefaultTypeQualifiers(typeCode);
        }
    }
}
