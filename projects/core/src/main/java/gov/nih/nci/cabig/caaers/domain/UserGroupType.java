package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;
import gov.nih.nci.cabig.ctms.domain.EnumHelper;

/**
 * This enumeration represents the user group types
 * 
 * @author Biju Joseph
 */
public enum UserGroupType implements CodedEnum<Integer> {
    caaers_user(-2, "caaers_user"),
    caaers_admin(-1, "caaers_admin"), 
    caaers_super_user(-3,"caaers_super_user"), 
    caaers_study_cd(-4, "caaers_study_cd"), 
    caaers_participant_cd(-5, "caaers_participant_cd"), 
    caaers_ae_cd(-13, "caaers_ae_cd"), 
    caaers_site_cd(-14, "caaers_site_cd"), 
    caaers_physician(-8, "caaers_physician");

    private String csmName;

    private int code;

    UserGroupType(final int code, final String name) {
        this.code = code;
        this.csmName = name;
        register(this);

    }

    public String getCsmName() {
        return csmName;
    }

    public String getDisplayName() {
        return EnumHelper.sentenceCasedName(this);
    }

    public Integer getCode() {
        return code;
    }

    public static UserGroupType getByCode(final int code) {
        return getByClassAndCode(UserGroupType.class, code);
    }

    public String toString() {
        return csmName;
    }

    public static final String[] strValues() {
        UserGroupType[] groupTypes = UserGroupType.values();
        String[] strUserGroupTypes = new String[groupTypes.length];
        for (int i = 0; i < groupTypes.length; i++) {
            strUserGroupTypes[i] = groupTypes[i].toString();
        }
        return strUserGroupTypes;
    }
}
