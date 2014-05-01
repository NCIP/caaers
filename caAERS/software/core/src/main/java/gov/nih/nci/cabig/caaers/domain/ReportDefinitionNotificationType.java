/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;
import gov.nih.nci.cabig.ctms.domain.EnumHelper;

/**
 * @author Ramakrishna Gundala
 */

public enum ReportDefinitionNotificationType implements CodedEnum<Integer> {
	REPORT_REMINDER(0,"Report Reminder"), UNREPORTERD_SAE(1,"Unreported SAE");
	
	private int code;

	private String name;
	
	ReportDefinitionNotificationType(int code, String name){
		this.code = code;
		this.name = name;
		register(this);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	  /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static ReportDefinitionNotificationType getByCode(int code) {
        return getByClassAndCode(ReportDefinitionNotificationType.class, code);
    }
    
    public static ReportDefinitionNotificationType getByShortName(String name){
        for(ReportDefinitionNotificationType g: values()){
            if(g.getShortName().equals(name)) return g;
        }
        return null;
    }
    
    public String getShortName() {
        return  sentenceCasedName(this) ;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		 return name == null ? EnumHelper.sentenceCasedName(this) : name;
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
