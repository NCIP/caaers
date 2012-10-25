package gov.nih.nci.cabig.caaers.domain;


import gov.nih.nci.cabig.ctms.domain.CodedEnum;
import org.apache.commons.lang.StringUtils;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;

/**
 * This class represents the documents type associated with {@link gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument }
 *
 * @author Saurabh Agrawal
 * @since 10/25/2012
 */

public enum AdditionalInformationDocumentType implements CodedEnum<String> {
    AUTOPSY_REPORT("autopsyReport"),

    CONSULTS("consults"),

    DISCHARGE_SUMMARY("dischargeSummary"),

    FLOW_CHARTS("flowCharts"),

    LAB_REPORTS("labReports"),

    OBA_FORM("obaForm"),

    OTHER("other"),

    PATHOLOGY_REPORT("pathologyReport"),

    PROGRESS_NOTES("progressNotes"),

    RADIOLOGY_REPORTS("radiologyReports"),

    REFERRAL_LETTERS("referralLetters"),

    IRB_REPORT("irbReport"),

    OTHER_INFORMATION("otherInformation");

    private String code;

    private AdditionalInformationDocumentType(String code) {
        this.code = code;
        register(this);
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return sentenceCasedName(this);
    }

    public String getName() {
        return name();
    }

    public static AdditionalInformationDocumentType getByCode(String code) {
        return getByClassAndCode(AdditionalInformationDocumentType.class, code);
    }


    public static AdditionalInformationDocumentType getAdditionalInformationDocumentType(String additionalInformationDocumentType) {
        //other must not match with other_information
        if (StringUtils.containsIgnoreCase(additionalInformationDocumentType, OTHER_INFORMATION.getCode())) {
            return OTHER_INFORMATION;
        } else if (StringUtils.containsIgnoreCase(additionalInformationDocumentType, OTHER.getCode())) {
            return OTHER;
        } else {
            for (AdditionalInformationDocumentType documentType : AdditionalInformationDocumentType.values()) {
                if (StringUtils.containsIgnoreCase(additionalInformationDocumentType, documentType.getCode())) {
                    return documentType;
                }
            }
            return null;
        }
    }
}



