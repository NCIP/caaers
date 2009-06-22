package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

public enum AgentAdjustment implements CodedEnum<Integer> {
    DOSE_INCREASED(1, "Dose increased"), DOSE_NOTCHANGED(2, "Dose not changed"), DOSE_REDUCED(3, "Dose reduced"), DRUG_WITHDRAWN(4, "Drug withdrawn"), NA(5, "Not applicable");
 
    private int code;

    private String displayName;

    AgentAdjustment(int code) {
        this(code, null);
    }

    AgentAdjustment(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static AgentAdjustment getByCode(int code) {
        return getByClassAndCode(AgentAdjustment.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
    
}
