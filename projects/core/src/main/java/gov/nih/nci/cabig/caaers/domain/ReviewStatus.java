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
	
    DRAFTINCOMPLETE(1, "Draft/Incomplete", "Publish Report For Review"), 
    READYFORREVIEW(2, "Ready for Review", "Ready For Review"), 
    LEVEL1REVIEW(3, "Level 1 Review", "Perform Level 1 Review"), 
    LEVEL2REVIEW(4, "Level 2 Review", "Perform Level 2 Review"), 
    INFO1REVIEW(5, "Additional Info for 1st Review" , "Provide Additional Info for First Review"), 
    INFO2REVIEW(6, "Additional Info for 2nd Review", "Provide Additional Info for Second Review"),
    REVIEWCOMPLETE(7, "Review Completed","Review Completed");
    
    private Integer code;
    private String displayName;
    private String nodeName;
    
    
    private ReviewStatus(Integer code) {
        this.code = code;
        register(this);
    }
    
    private ReviewStatus(Integer code, String longName, String nodeName){
    	this(code);
    	this.displayName = longName;
    	this.nodeName = nodeName;
    	
    }
    
    public static ReviewStatus getByCode(int code){
    	return getByClassAndCode(ReviewStatus.class, code);
    }
    
    public static ReviewStatus getByNodeName(String nodeName){
    	for(ReviewStatus rs : values()){
    		if(nodeName.equals(rs.getNodeName())){
    			return rs;
    		}
    	}
    	
    	return null;
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
    
    public String getNodeName() {
		return nodeName;
	}
    
    
    @Override
    public String toString() {
        return toStringHelper(this);
    }

}
