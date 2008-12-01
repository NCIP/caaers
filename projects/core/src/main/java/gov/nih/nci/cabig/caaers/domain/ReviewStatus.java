package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author Sameer Sawant
 */
public enum ReviewStatus implements CodedEnum<Integer> {
    DRAFTINCOMPLETE(1, "Draft/Incomplete"), READYFORREVIEW(2, "Ready for Review"), LEVEL1REVIEW(3, "Level 1 Review"), 
    LEVEL2REVIEW(4, "Level 2 Review"), INFO1REVIEW(5, "Additional Info for 1st Review"), INFO2REVIEW(6, "Additional Info for 2nd Review"),
    REVIEWCOMPLETE(7, "Review Completed");
    private Integer code;
    
    private String displayName;

    private ReviewStatus(Integer code) {
        this.code = code;
        register(this);
    }
    
    private ReviewStatus(Integer code, String longName){
    	this.code = code;
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
