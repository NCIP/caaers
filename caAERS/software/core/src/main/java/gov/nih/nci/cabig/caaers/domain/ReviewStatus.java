package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum ReviewStatus.
 *
 * @author Sameer Sawant
 * @author Biju Joseph
 */
public enum ReviewStatus implements CodedEnum<Integer> {
	
    /** The DRAF t_ incomplete. */
    DRAFT_INCOMPLETE (1, "Draft/Incomplete"), 
    
    /** The PHYSICIA n_ review. */
    PHYSICIAN_REVIEW (2, "Physician Review"), 
    
    /** The PHYSICIA n_ additiona l_ info. */
    PHYSICIAN_ADDITIONAL_INFO (3, "Additional Info Requested by Physician"),
    
    /** The PHYSICIA n_ approved. */
    PHYSICIAN_APPROVED (4, "Reviewed by Physician "), 
    
    /** The CENTRA l_ offic e_ review. */
    CENTRAL_OFFICE_REVIEW (5, "Central Office Report Review"), 
    
    /** The CENTRA l_ offic e_ additiona l_ info. */
    CENTRAL_OFFICE_ADDITIONAL_INFO (6, "Additional Info Request by Central Office"), 
    
    /** The SUBMI t_ t o_ sponsor. */
    SUBMIT_TO_SPONSOR (7, "Ready for Submission to Sponsor "),
    
    /** The DAT a_ coordinato r_ review. */
    DATA_COORDINATOR_REVIEW(8, "Data Coordinator Review"),
    
    /** The DAT a_ coordinato r_ additiona l_ info. */
    DATA_COORDINATOR_ADDITIONAL_INFO(9,"Additional Info Requested By Data Coordinator"),
    
    /** The APPROVED. */
    APPROVED(11, "Reviewed"),
    
    /** The SUBMITTE d_ t o_ sponsor. */
    SUBMITTED_TO_SPONSOR(12, "Submitted to Sponsor");
    
    /** The code. */
    private Integer code;
    
    /** The display name. */
    private String displayName;
    
    
    /**
     * Instantiates a new review status.
     *
     * @param code the code
     */
    private ReviewStatus(Integer code) {
        this.code = code;
        register(this);
    }
    
    /**
     * Instantiates a new review status.
     *
     * @param code the code
     * @param longName the long name
     */
    private ReviewStatus(Integer code, String longName){
    	this(code);
    	this.displayName = longName;
    	
    }
    
    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static ReviewStatus getByCode(int code){
    	return getByClassAndCode(ReviewStatus.class, code);
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
