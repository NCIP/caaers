package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * This enumeration represents the Therapies.
 * 
 * @author Biju Joseph
 */
public enum StudyTherapyType implements CodedEnum<Integer> {

    /** The DRU g_ administration. */
    DRUG_ADMINISTRATION(1, "Drug Administration" , "Drug"),
    
    /** The RADIATION. */
    RADIATION(2, "Radiation", "Radiation"),
    
    /** The SURGERY. */
    SURGERY(3, "Surgery", "Procedure/Surgery"),
    
    /** The DEVICE. */
    DEVICE(4, "Device", "Device"),
    
    /** The BEHAVIORAL. */
    BEHAVIORAL(5, "Behavioral","Behavioral"),
    
    /** The BIOLOGICA l_ vaccine. */
    BIOLOGICAL_VACCINE(6, "Biological/Vaccine", "Biological/Vaccine"),
    
    /** The GENETIC. */
    GENETIC(7,"Genetic","Genetic"),
    
    /** The DIETAR y_ supplement. */
    DIETARY_SUPPLEMENT(8,"Dietary Supplement","Dietary Supplement"),
    
    /** The OTHER. */
    OTHER(9,"Other", "Other");

    /** The study therapy type. */
    private String studyTherapyType;
    
    /** The code. */
    private int code;
    
    /** The coppa name. */
    private String coppaName;

    /**
     * Instantiates a new study therapy type.
     *
     * @param code the code
     * @param studyTherapyType the study therapy type
     * @param coppaName the coppa name
     */
    private StudyTherapyType(final int code, final String studyTherapyType, String coppaName) {
        this.code = code;
        this.studyTherapyType = studyTherapyType;
        this.coppaName = coppaName;
        register(this);
    }

    /**
     * Gets the study therapy type.
     *
     * @return the study therapy type
     */
    public String getStudyTherapyType() {
        return studyTherapyType;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
     */
    public String getDisplayName() {
        return studyTherapyType;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
        return code;
    }
    
    /**
     * Gets the coppa name.
     *
     * @return the coppa name
     */
    public String getCoppaName() {
		return coppaName;
	}
    

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static StudyTherapyType getByCode(final int code) {
        return getByClassAndCode(StudyTherapyType.class, code);
    }
    
    /**
     * Gets the by coppa name.
     *
     * @param coppaName the coppa name
     * @return the by coppa name
     */
    public static StudyTherapyType getByCoppaName(String coppaName){
    	for(StudyTherapyType therapyType : values()){
    		if(therapyType.coppaName.equals(coppaName)) return therapyType;
    	}
    	return null;
    }
}
