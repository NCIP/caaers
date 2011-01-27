package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;
import gov.nih.nci.cabig.ctms.domain.EnumHelper;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This enumeration represents the user group types
 * 
 * @author Biju Joseph
 */
public enum UserGroupType implements CodedEnum<Integer> {
	// check *SecurityFilterer classes and RoutingAndReviewResolverController when adding new group . Need to change those classes accordingly
    caaers_super_user(-3,"caaers_super_user"),  //DO not throw away super user entry
    caaers_study_cd(-4, "caaers_study_cd"), 
    caaers_participant_cd(-5, "caaers_participant_cd"), 
    caaers_ae_cd(-13, "caaers_ae_cd"), 
    caaers_site_cd(-14, "caaers_site_cd"), 
    caaers_physician(-8, "caaers_physician"),
    caaers_central_office_sae_cd(-7943, "caaers_central_office_sae_cd"),
    caaers_data_cd(-7942, "caaers_data_cd"),
    system_administrator(-101, "system_administrator"),
    business_administrator(-102, "business_administrator"),
    person_and_organization_information_manager(-103, "person_and_organization_information_manager"),
    data_importer(-104, "data_importer"),
    user_administrator(-105, "user_administrator"),
    study_qa_manager(-106, "study_qa_manager"),
    study_creator(-107, "study_creator"),
    supplemental_study_information_manager(-108, "supplemental_study_information_manager"),
    study_team_administrator(-109, "study_team_administrator"),
    study_site_participation_administrator(-110, "study_site_participation_administrator"),
    ae_rule_and_report_manager(-111, "ae_rule_and_report_manager"),
    study_calendar_template_builder(-112, "study_calendar_template_builder"),
    registration_qa_manager(-113, "registration_qa_manager"),
    subject_manager(-114, "subject_manager"),
    study_subject_calendar_manager(-115, "study_subject_calendar_manager"),
    registrar(-116, "registrar"),
    ae_reporter(-117, "ae_reporter"),
    ae_expedited_report_reviewer(-118, "ae_expedited_report_reviewer"),
    ae_study_data_reviewer(-119, "ae_study_data_reviewer"),
    lab_impact_calendar_notifier(-120, "lab_impact_calendar_notifier"),
    lab_data_user(-121, "lab_data_user"),
    data_reader(-122, "data_reader"),
    data_analyst(-123, "data_analyst");
    
    private String csmName;
    private static String[] acronyms = {"AE", "QA"};

    private int code;

    UserGroupType(final int code, final String name) {
        this.code = code;
        this.csmName = name;
        register(this);

    }

    //will return all the roles that are not scoped. 
    public static List<UserGroupType> getUnScopedRoles(){
        return Arrays.asList(new UserGroupType[]{
                system_administrator, business_administrator, data_importer,ae_rule_and_report_manager 
        });
    }

    //will return all the roles that are site scoped.
    public static List<UserGroupType> getSiteScopedRoles(){
        return Arrays.asList(new UserGroupType[]{
             subject_manager, registration_qa_manager, data_importer, study_site_participation_administrator,
             study_team_administrator, supplemental_study_information_manager, study_creator, study_qa_manager,
             user_administrator, person_and_organization_information_manager
        });
    }


    //will return all the roles that are site and study scoped.
    public static List<UserGroupType> getStudyScopedRoles(){
        return Arrays.asList(new UserGroupType[]{
                data_analyst, data_reader, lab_data_user, lab_impact_calendar_notifier, ae_study_data_reviewer, ae_reporter,
                ae_expedited_report_reviewer, registrar, study_calendar_template_builder, study_subject_calendar_manager
        });
    }


    public String getCsmName() {
        return csmName;
    }

    public String getDisplayName() {
        String s = EnumHelper.titleCasedName(this);
        for (String a : acronyms) {
            Pattern pattern = Pattern.compile(String.format("\\b%s\\b", a), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(s);
            s = matcher.replaceAll(a);
        }
        return s;
    }

    public Integer getCode() {
        return code;
    }

    public static UserGroupType getByCode(final int code) {
        return getByClassAndCode(UserGroupType.class, code);
    }

    public static UserGroupType getByCSMName(String csmName){
        for(UserGroupType ug : UserGroupType.values()){
            if(ug.getCsmName().equals(csmName)) return ug;
        }
        return null;
    }

    public String toString() {
        return csmName;
    }
    
    public String getSecurityRoleName() {
    	return csmName;
    }

    public static final String[] strValues() {
        UserGroupType[] groupTypes = UserGroupType.values();
        String[] strUserGroupTypes = new String[groupTypes.length];
        for (int i = 0; i < groupTypes.length; i++) {
            strUserGroupTypes[i] = groupTypes[i].toString();
        }
        return strUserGroupTypes;
    }
    
    public static final int[] codes(){
    	UserGroupType[] groupTypes = UserGroupType.values();
    	int[] codes = new int[groupTypes.length];
    	for(int i =0; i < codes.length; i++){
    		codes[i] = groupTypes[i].code;
    	}
    	return codes;
    }
}
