/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    
    /** The B t_ hdr. */
    BT_HDR(1, "Brachytherapy HDR"), 
 /** The B t_ ldr. */
 BT_LDR(2, "Brachytherapy LDR"), 
 /** The B t_ nos. */
 BT_NOS(3, "Brachytherapy NOS"), 
 /** The E b_ nos. */
 EB_NOS(
                    4, "External Beam NOS"), 
 /** The E b_2 d. */
 EB_2D(5, "External Beam, 2D"), 
 /** The E b_3 d. */
 EB_3D(6,
                    "External Beam, 3D"), 
 /** The E b_ imrt. */
 EB_IMRT(7, "External Beam, IMRT"), 
 /** The E b_ proton. */
 EB_PROTON(8,
                    "External Beam, Proton"), 
 /** The SYSTEMI c_ radiotherapy. */
 SYSTEMIC_RADIOTHERAPY(9, "Systemic radiotherapy");

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new radiation administration.
     *
     * @param code the code
     */
    RadiationAdministration(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new radiation administration.
     *
     * @param code the code
     * @param longName the long name
     */
    RadiationAdministration(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static RadiationAdministration getByCode(int code) {
        return getByClassAndCode(RadiationAdministration.class, code);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
        return code;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
     */
    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }

    public static RadiationAdministration findByDisplayName(String displayName) {
        RadiationAdministration result = null;
        for (RadiationAdministration r : values()) {
            if ( r.getDisplayName().equals(displayName) ) {
                result = r;
                break;
            }
        }
       return result;
    }
}
