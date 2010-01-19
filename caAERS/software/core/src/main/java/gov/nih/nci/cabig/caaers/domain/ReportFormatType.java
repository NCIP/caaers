package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

public enum ReportFormatType implements CodedEnum<Integer> {

    CAAERSXML(1, "CaAERS XML"), 
    ADEERSPDF(2, "AdEERS Expedited Adverse Event Report"), 
    MEDWATCHPDF(3, "Medwatch Form FDA 3500A"), 
    DCPSAEFORM(4, "DCP SAE Form"), 
    CIOMSFORM(5, "CIOMS Form"), 
    CIOMSSAEFORM(6, "CIOMS SAE Form"),
    CUSTOM_REPORT(7, "caAERS Custom Report PDF");

    private int code;

    private String displayName;
    
    ReportFormatType(int code) {
        this(code, null);
    }
    
    ReportFormatType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        register(this);
    }

    public static ReportFormatType getByCode(int code) {
        return getByClassAndCode(ReportFormatType.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        if (displayName == null) {
            return sentenceCasedName(this);
        } else {
            return displayName;
        }
    }

    // for bean-property access
    public String getName() {
        return name();
    }

    public String toString() {
        return toStringHelper(this);
    }
}
