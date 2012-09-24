package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum ReconciliationSystem.
 *
 * @author Ramakrishna
 */
public enum ReconciliationSystem implements CodedEnum<Integer> {
	
	CAAERS(1, "caAERS"), 
    
    FORCE(2, "Force");
    
    /** The code. */
    private Integer code;
    
    /** The display name. */
    private String displayName;
    
    
    /**
     * Instantiates a new reconciliation system.
     *
     * @param code the code
     */
    private ReconciliationSystem(Integer code) {
        this.code = code;
        register(this);
    }
    
    /**
     * Instantiates a new reconciliation system.
     *
     * @param code the code
     * @param longName the long name
     */
    private ReconciliationSystem(Integer code, String longName){
    	this(code);
    	this.displayName = longName;
    	
    }
    
    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static ReconciliationSystem getByCode(int code){
    	return getByClassAndCode(ReconciliationSystem.class, code);
    }
    
    public static ReconciliationSystem getByDisplayName(String name){
        for(ReconciliationSystem s: values()){
            if(s.getDisplayName().equals(name)){
                return s;
            }
        }
        return null;
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
