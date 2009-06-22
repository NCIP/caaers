package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * This class represents the RadiationAdministration domain object associated with the Adverse event
 * report.
 * 
 * @author Krikor Krumlian
 */
public enum RadiationAdministration implements CodedEnum<Integer> {
    BT_HDR(1, "Brachytherapy HDR"), BT_LDR(2, "Brachytherapy LDR"), BT_NOS(3, "Brachytherapy NOS"), EB_NOS(
                    4, "External Beam NOS"), EB_2D(5, "External Beam, 2D"), EB_3D(6,
                    "External Beam, 3D"), EB_IMRT(7, "External Beam, IMRT"), EB_PROTON(8,
                    "External Beam, Proton"), SYSTEMIC_RADIOTHERAPY(9, "Systemic radiotherapy");

    private int code;

    private String displayName;

    RadiationAdministration(int code) {
        this(code, null);
    }

    RadiationAdministration(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static RadiationAdministration getByCode(int code) {
        return getByClassAndCode(RadiationAdministration.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
