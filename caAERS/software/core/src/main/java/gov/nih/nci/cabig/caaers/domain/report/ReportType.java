package gov.nih.nci.cabig.caaers.domain.report;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;
 

/**
 * The Enum ReportType.
 *
 * @author Biju Joseph
 */
public enum ReportType implements CodedEnum<Integer>{
	
	/** The NOTIFICATION. */
	NOTIFICATION(1, "Notification"), /** The REPORT. */
 REPORT(2, "Report");
	
	/** The code. */
	private int code;
	
	/** The display name. */
	private String displayName;
	
	/**
	 * Instantiates a new report type.
	 *
	 * @param code the code
	 * @param displayName the display name
	 */
	private ReportType(int code, String displayName){
		this.code = code;
		this.displayName = displayName;
		register(this);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getCode()
	 */
	public Integer getCode() {
		return code;
	}
	
	/**
	 * Gets the by code.
	 *
	 * @param code the code
	 * @return the by code
	 */
	public static ReportType getByCode(int code){
		 return getByClassAndCode(ReportType.class, code); 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return toStringHelper(this);
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name();
	}
	
}
