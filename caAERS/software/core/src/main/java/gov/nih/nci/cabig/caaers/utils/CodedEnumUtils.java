package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.ctms.domain.CodedEnum;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CodedEnumUtils {
	/**
	 * Compares two {@link CodedEnum}
	 * @param e1
	 * @param e2
	 * @return -1 , 0 or 1 when e1 is greater , equal or less than e2.
	 */
	public static int compare(CodedEnum<Integer> e1, CodedEnum<Integer> e2){
		if(e1 == null && e2 == null) return 0;
		if(e1 == null) return 1;
		if(e2 == null) return -1;
		
		if(e1.getCode() == null && e2.getCode() == null) return 0;
		if(e1.getCode() == null) return 1;
		if(e2.getCode() == null) return -1;
		
		if(e1.getCode() > e2.getCode()) return -1;
		if(e1.getCode() < e2.getCode()) return 1;
		
		return 0;
		
	}
}
