package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.CodedEnum;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;

/**
 * Captures the reprocessing statues for a {@link gov.nih.nci.cabig.caaers.domain.MedicalDevice}
 * @author Biju Joseph
 */
public enum ReprocessedDevice implements CodedEnum<Integer> {
    YES(1), NO(2), UNKNOWN(3);

    private int code;

    private String displayName;

    ReprocessedDevice(int code){
      this(code, null);
    }

    ReprocessedDevice(int code, String displayName)  {
      this.code = code;
      this.displayName = displayName;
      register(this);
    }

    public static ReprocessedDevice getByCode(int code){
        return getByClassAndCode(ReprocessedDevice.class, code);
    }

   public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
