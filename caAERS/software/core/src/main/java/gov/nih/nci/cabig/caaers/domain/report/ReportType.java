package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;
/**
 * 
 * @author Biju Joseph
 *
 */
public enum ReportType implements CodedEnum<Integer>{
	
	NOTIFICATION(1, "Notification"), REPORT(2, "Report");
	
	private int code;
	private String displayName;
	
	private ReportType(int code, String displayName){
		this.code = code;
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public Integer getCode() {
		return code;
	}
	
	public static ReportType getByCode(int code){
		 return getByClassAndCode(ReportType.class, code); 
	}
	
	@Override
	public String toString() {
		return displayName;
	}
	
	
}
