package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * This class represents the DiseaseCodeTerm domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
public enum DiseaseCodeTerm implements CodedEnum<Integer> {

    CTEP(1, "CTEP"), MEDDRA(2, "MedDRA"), OTHER(3, "Other - Specify");

    private int code;
    private String displayName;

    DiseaseCodeTerm(int code) {
        this(code, null);
    }

    DiseaseCodeTerm(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static DiseaseCodeTerm getByCode(int code) {
        return getByClassAndCode(DiseaseCodeTerm.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
