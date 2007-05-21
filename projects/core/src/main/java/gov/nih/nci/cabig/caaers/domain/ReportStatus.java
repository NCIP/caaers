package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.domain.CodedEnumHelper.*;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 15, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public enum ReportStatus implements CodedEnum{
	PENDING(1),
	COMPLETED(2);

	private int code;
	
	private ReportStatus(int code){
		this.code = code;
		register(this);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.CodedEnum#getCode()
	 */
	public int getCode() {
		return code;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.CodedEnum#getDisplayName()
	 */
	public String getDisplayName() {
		return name();
	}
	public String getName(){
		return name();
	}
	
	public static ReportStatus getByCode(int code){
		return getByClassAndCode(ReportStatus.class, code);
	}
}
