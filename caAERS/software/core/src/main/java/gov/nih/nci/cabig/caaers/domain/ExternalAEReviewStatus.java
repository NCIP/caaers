package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum ExternalAEReviewStatus.
 *
 * @author Ramakrishna
 */
public enum ExternalAEReviewStatus implements CodedEnum<Integer> {
	
	PENDING(1, "Pending"), 
    
    REJECTED(2, "Rejected"), 
    
    MERGED(3, "Merged"),
    
    IGNORED(4, "Ignored"), 
    
    REVIEWED(5, "Reviewed"), 
    
    ADDITIONAL_INFO(6, "Additional Info");
    
    /** The code. */
    private Integer code;
    
    /** The display name. */
    private String displayName;
    
    
    /**
     * Instantiates a new review status.
     *
     * @param code the code
     */
    private ExternalAEReviewStatus(Integer code) {
        this.code = code;
        register(this);
    }
    
    /**
     * Instantiates a new review status.
     *
     * @param code the code
     * @param longName the long name
     */
    private ExternalAEReviewStatus(Integer code, String longName){
    	this(code);
    	this.displayName = longName;
    	
    }
    
    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static ExternalAEReviewStatus getByCode(int code){
    	return getByClassAndCode(ExternalAEReviewStatus.class, code);
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
     * Gets the string.
     *
     * @return the string
     */
    public String getString() {
        return toString();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return toStringHelper(this);
    }

}
