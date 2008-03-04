package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

public enum ReportFormat implements CodedEnum<Integer> {
    TEXT("Text"), XML("XML"), PDF("PDF");
    private String displayName;

    private ReportFormat(String displayName) {
        this.displayName = displayName;
        register(this);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
        return ordinal();
    }

    public static ReportFormat getByCode(int code) {
        return getByClassAndCode(ReportFormat.class, code);
    }
}
