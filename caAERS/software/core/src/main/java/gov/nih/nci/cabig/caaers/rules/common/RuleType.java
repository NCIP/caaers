/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.common;

public enum RuleType {

    REPORT_SCHEDULING_RULES("SAE Reporting Rules", "The rules regarding identifying the reporting periods"),
    MANDATORY_SECTIONS_RULES("Mandatory Sections Rules", "The rules regarding identifying the mandatory sections"),
    FIELD_LEVEL_RULES("Field Rules" , "The rules regarding requiredness of UI fields");

    private String name;
    private String desc;

    RuleType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

}
