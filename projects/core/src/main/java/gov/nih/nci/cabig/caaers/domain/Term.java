package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.*;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author Krikor Krumlian
 */
public enum Term implements CodedEnum<Integer> {
    CTC(1,"Ctc"),
    MEDDRA(2,"MedDRA")
    ;

    private int code;
    private String displayName;

    Term(int code) {
        this(code, null);
    }

    Term(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static Term getByCode(int code) {
        return getByClassAndCode(Term.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
