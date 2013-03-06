/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    
    /** The YES. */
    YES(1), 
 /** The NO. */
 NO(2), 
 /** The UNKNOWN. */
 UNKNOWN(3);

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new reprocessed device.
     *
     * @param code the code
     */
    ReprocessedDevice(int code){
      this(code, null);
    }

    /**
     * Instantiates a new reprocessed device.
     *
     * @param code the code
     * @param displayName the display name
     */
    ReprocessedDevice(int code, String displayName)  {
      this.code = code;
      this.displayName = displayName;
      register(this);
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static ReprocessedDevice getByCode(int code){
        return getByClassAndCode(ReprocessedDevice.class, code);
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
}
