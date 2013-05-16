/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;
import gov.nih.nci.cabig.ctms.domain.EnumHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 
/**
 * This enumeration represents the user group types.
 *
 * @author Biju Joseph
 */
public enum UserGroupType implements CodedEnum<Integer> {
	// check *SecurityFilterer classes and RoutingAndReviewResolverController when adding new group . Need to change those classes accordingly
    /** The caaers_super_user. */
	caaers_super_user(-3,0, "caaers_super_user"),  //DO not throw away super user entry
    /** The caaers_study_cd. */
  caaers_study_cd(-4, 0, "caaers_study_cd"),
    
    /** The caaers_participant_cd. */
    caaers_participant_cd(-5, 0,"caaers_participant_cd"),
    
    /** The caaers_ae_cd. */
    caaers_ae_cd(-13,0, "caaers_ae_cd"),
    
    /** The caaers_site_cd. */
    caaers_site_cd(-14,0, "caaers_site_cd"),
    
    /** The caaers_physician. */
    caaers_physician(-8, 0,"caaers_physician"),
    
    /** The caaers_central_office_sae_cd. */
    caaers_central_office_sae_cd(-7943, 2^23, "caaers_central_office_sae_cd"),
    
    /** The caaers_data_cd. */
    caaers_data_cd(-7942, 2^24, "caaers_data_cd"),
    
    /** The system_administrator. */
    system_administrator(-101, 1,"system_administrator"),
    
    /** The business_administrator. */
    business_administrator(-102, 2, "business_administrator"),
    
    /** The person_and_organization_information_manager. */
    person_and_organization_information_manager(-103, 2^2, "person_and_organization_information_manager"),
    
    /** The data_importer. */
    data_importer(-104, 2^3, "data_importer"),
    
    /** The user_administrator. */
    user_administrator(-105, 2^4, "user_administrator"),
    
    /** The study_qa_manager. */
    study_qa_manager(-106, 2^5,"study_qa_manager"),
    
    /** The study_creator. */
    study_creator(-107, 2^6, "study_creator"),
    
    /** The supplemental_study_information_manager. */
    supplemental_study_information_manager(-108, 2^7,"supplemental_study_information_manager"),
    
    /** The study_team_administrator. */
    study_team_administrator(-109, 2^8, "study_team_administrator"),
    
    /** The study_site_participation_administrator. */
    study_site_participation_administrator(-110, 2^9, "study_site_participation_administrator"),
    
    /** The ae_rule_and_report_manager. */
    ae_rule_and_report_manager(-111, 2^10, "ae_rule_and_report_manager"),
    
    /** The study_calendar_template_builder. */
    study_calendar_template_builder(-112, 2^11, "study_calendar_template_builder"),
    
    /** The registration_qa_manager. */
    registration_qa_manager(-113, 2^12,"registration_qa_manager"),
    
    /** The subject_manager. */
    subject_manager(-114, 2^13, "subject_manager"),
    
    /** The study_subject_calendar_manager. */
    study_subject_calendar_manager(-115,2^14, "study_subject_calendar_manager"),
    
    /** The registrar. */
    registrar(-116, 2^15,"registrar"),
    
    /** The ae_reporter. */
    ae_reporter(-117, 2^16,"ae_reporter"),
    
    /** The ae_expedited_report_reviewer. */
    ae_expedited_report_reviewer(-118, 2^17,"ae_expedited_report_reviewer"),
    
    /** The ae_study_data_reviewer. */
    ae_study_data_reviewer(-119,2^18, "ae_study_data_reviewer"),
    
    /** The lab_impact_calendar_notifier. */
    lab_impact_calendar_notifier(-120, 2^19,"lab_impact_calendar_notifier"),
    
    /** The lab_data_user. */
    lab_data_user(-121, 2^20, "lab_data_user"),
    
    /** The data_reader. */
    data_reader(-122, 2^21,"data_reader"),
    
    /** The data_analyst. */
    data_analyst(-123, 2^22,"data_analyst");
	    
    /** The study_medical_monitor. */
    private String csmName;
    
    /** The acronyms. */
    private static String[] acronyms = {"AE", "QA"};

    /** The code. */
    private int code;

    private int privilege;

    /**
     * Instantiates a new user group type.
     *
     * @param code the code
     * @param name the name
     */
    UserGroupType(final int code, int privilege, final String name) {
        this.code = code;
        this.csmName = name;
        this.privilege = privilege;
        register(this);

    }

    public boolean isSelected(int bitCode){
       return (this.privilege & bitCode) > 0;
    }

    //will return all the roles that are not scoped. 
    /**
     * Gets the un scoped roles.
     *
     * @return the un scoped roles
     */
    public static List<UserGroupType> getUnScopedRoles(){
        return Arrays.asList(new UserGroupType[]{
                system_administrator, business_administrator, data_importer,ae_rule_and_report_manager 
        });
    }

    //will return all the roles that are site scoped.
    /**
     * Gets the site scoped roles.
     *
     * @return the site scoped roles
     */
    public static List<UserGroupType> getSiteScopedRoles(){
        return Arrays.asList(new UserGroupType[]{
             subject_manager, registration_qa_manager, study_site_participation_administrator,
             study_team_administrator, supplemental_study_information_manager, study_creator, study_qa_manager,
             user_administrator, person_and_organization_information_manager
        });
    }


    //will return all the roles that are site and study scoped.
    /**
     * Gets the study scoped roles.
     *
     * @return the study scoped roles
     */
    public static List<UserGroupType> getStudyScopedRoles(){
        return Arrays.asList(new UserGroupType[]{
                data_analyst, data_reader, lab_data_user, lab_impact_calendar_notifier, ae_study_data_reviewer, ae_reporter,
                ae_expedited_report_reviewer, registrar, study_calendar_template_builder, study_subject_calendar_manager
        });
    }


    /** 
     * Gets the csm name.
     *
     * @return the csm name
     */
    public String getCsmName() {
        return csmName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
     */
    public String getDisplayName() {
        String s = EnumHelper.titleCasedName(this);
        for (String a : acronyms) {
            Pattern pattern = Pattern.compile(String.format("\\b%s\\b", a), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(s);
            s = matcher.replaceAll(a);
        }
        return s;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
        return code;
    }


    public int getPrivilege() {
        return privilege;
    }


    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static UserGroupType getByCode(final int code) {
        return getByClassAndCode(UserGroupType.class, code);
    }


    
    /**
     * Gets the by csm name.
     *
     * @param csmName the csm name
     * @return the by csm name
     */
    public static UserGroupType getByCSMName(String csmName){
        for(UserGroupType ug : UserGroupType.values()){
            if(ug.getCsmName().equals(csmName)) return ug;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return csmName;
    }
    
    /**
     * Gets the security role name.
     *
     * @return the security role name
     */
    public String getSecurityRoleName() {
    	return csmName;
    }


    /**
     * Will return the roles represented by the privilege
     * @param privilege
     * @return
     */
    public static List<UserGroupType> roles(int privilege){
        List<UserGroupType> roles = new ArrayList<UserGroupType>();
        for(UserGroupType ugt : UserGroupType.values()) {
            if((ugt.getPrivilege() & privilege)> 0) roles.add(ugt);
        }
        return roles;
    }

}
