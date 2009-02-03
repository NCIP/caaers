package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author Sameer Sawant
 * @author Biju Joseph
 */
public enum ReviewStatus implements CodedEnum<Integer> {
	
    DRAFT_INCOMPLETE (1, "Draft/Incomplete"), 
    PHYSICIAN_REVIEW (2, "Physician Review"), 
    PHYSICIAN_ADDITIONAL_INFO (3, "Additional Info Requested by Physician"),
    PHYSICIAN_APPROVED (4, "Approved by Physician "), 
    CENTRAL_OFFICE_REVIEW (5, "Central Office SAE Coordinator Review"), 
    CENTRAL_OFFICE_ADDITIONAL_INFO (6, "Additional Info Request by Central Office"), 
    SUBMIT_TO_SPONSOR (7, "Ready for Submission to Sponsor "),
    DATA_COORDINATOR_REVIEW(8, "Data Coordinator Review"),
    DATA_COORDINATOR_ADDITIONAL_INFO(9,"Additional Info Requested By Data Coordinator"),
    APPROVED(11, "Approved"),
    SUBMITTED_TO_SPONSOR(12, "Submitted to Sponsor");
    
    private Integer code;
    private String displayName;
    
    
    private ReviewStatus(Integer code) {
        this.code = code;
        register(this);
    }
    
    private ReviewStatus(Integer code, String longName){
    	this(code);
    	this.displayName = longName;
    	
    }
    
    public static ReviewStatus getByCode(int code){
    	return getByClassAndCode(ReviewStatus.class, code);
    }
    

    public Integer getCode() {
        return code;
    }
    
    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }

    public String getName() {
        return name();
    }
    
    public String getString() {
        return toString();
    }
    
    @Override
    public String toString() {
        return toStringHelper(this);
    }

}
