/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum ReportStatus.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 15, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public enum ReportStatus implements CodedEnum<Integer> {
    
    /** The PENDING. */
    PENDING(1), 
 /** The COMPLETED. */
 COMPLETED(2), 
 /** The WITHDRAWN. */
 WITHDRAWN(3), 
 /** The INPROCESS. */
 INPROCESS(4), 
 /** The FAILED. */
 FAILED(5), 
 /** The REPLACED. */
 REPLACED(6), 
 /** The AMENDED. */
 AMENDED(7), 
 /** The WITHDRA w_ failed. */
 WITHDRAW_FAILED(8);
    
    /** The code. */
    private int code;

    /**
     * Instantiates a new report status.
     *
     * @param code the code
     */
    private ReportStatus(int code) {
        this.code = code;
        register(this);
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
        return name();
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name();
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static ReportStatus getByCode(int code) {
        return getByClassAndCode(ReportStatus.class, code);
    }
}
