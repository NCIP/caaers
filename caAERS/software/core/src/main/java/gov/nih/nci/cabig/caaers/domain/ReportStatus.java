package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
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
 PENDING(1, "Not Submitted"), 
 /** The COMPLETED. */
 COMPLETED(2, "Successful"), 
 /** The WITHDRAWN. */
 WITHDRAWN(3, "Withdrawn"), 
 /** The INPROCESS. */
 INPROCESS(4, "In process"), 
 /** The FAILED. */
 FAILED(5, "Failed"), 
 /** The REPLACED. */
 REPLACED(6, "Replaced"), 
 /** The AMENDED. */
 AMENDED(7, "Amended"), 
 /** The WITHDRA w_ failed. */
 WITHDRAW_FAILED(8, "Withdraw Failed");
    
    /** The code. */
    private int code;
    
    private String displayName;

    /**
     * Instantiates a new report status.
     *
     * @param code the code
     */
    private ReportStatus(int code, String name) {
        this.code = code;
        this.displayName = name;
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
    	 return displayName == null ? sentenceCasedName(this) : displayName;
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
