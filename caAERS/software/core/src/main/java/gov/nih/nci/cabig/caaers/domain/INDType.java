package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.CodedEnum;

public enum INDType implements CodedEnum<Integer> {
    NA(0, "NA"), NA_COMMERCIAL(3, "N/A-Commercial Agent"), IND_EXEMPT(4, "IND-Exempt"), DCP_IND(5, "DCP IND"), OTHER(2, "Other IND Holder"), CTEP_IND(1, "CTEP IND");

    private int code;

    private String displayName;

    private INDType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

}
