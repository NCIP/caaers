/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * This class represents the Term domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
public enum Term implements CodedEnum<Integer> {
    CTC(1, "CTCAE"), MEDDRA(2, "MedDRA");

    private int code;

    private String displayName;

    Term(int code) {
        this(code, null);
    }

    Term(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static Term getByCode(int code) {
        return getByClassAndCode(Term.class, code);
    }
    
    /**
     * The same as {@link Enum#valueOf}, but returns null instead of throwing {@link IllegalArgumentException}.
     * @param name
     * @return
     */
    public static Term getByName(String name) {
        try {
			return valueOf(name);
		} catch (IllegalArgumentException e) {
			return null;
		}
    }
    

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
