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

    DRUG_ADMINISTRATION(1, "Drug Administration" , "Drug"),
    RADIATION(2, "Radiation", "Radiation"),
    SURGERY(3, "Surgery", "Procedure/Surgery"),
    DEVICE(4, "Device", "Device"),
    BEHAVIORAL(5, "Behavioral","Behavioral"),
    BIOLOGICAL_VACCINE(6, "Biological/Vaccine", "Biological/Vaccine"),
    GENETIC(7,"Genetic","Genetic"),
    DIETARY_SUPPLEMENT(8,"Dietary Supplement","Dietary Supplement"),
    OTHER(9,"Other", "Other");

    private String studyTherapyType;
    private int code;
    private String coppaName;

    private StudyTherapyType(final int code, final String studyTherapyType, String coppaName) {
        this.code = code;
        this.studyTherapyType = studyTherapyType;
        this.coppaName = coppaName;
        register(this);
    }

    public String getStudyTherapyType() {
        return studyTherapyType;
    }

    public String getDisplayName() {
        return studyTherapyType;
    }

    public String getName() {
        return name();
    }

    public Integer getCode() {
        return code;
    }
    
    public String getCoppaName() {
		return coppaName;
	}
    

    public static StudyTherapyType getByCode(final int code) {
        return getByClassAndCode(StudyTherapyType.class, code);
    }
    
    public static StudyTherapyType getByCoppaName(String coppaName){
    	for(StudyTherapyType therapyType : values()){
    		if(therapyType.coppaName.equals(coppaName)) return therapyType;
    	}
    	return null;
    }
}
