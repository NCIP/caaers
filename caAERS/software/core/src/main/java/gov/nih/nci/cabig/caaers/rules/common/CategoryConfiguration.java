/**
 * 
 */
package gov.nih.nci.cabig.caaers.rules.common;

/**
 * @author vinaykumar
 * @author Biju Joseph
 */
public enum CategoryConfiguration {

    CAAERS_BASE("CAAERS_BASE", "CAAERS_BASE",
            "gov.nih.nci.cabig.caaers.rules","The rule base for all caaers rules"),
    SPONSOR_BASE("SPONSOR", "/CAAERS_BASE/SPONSOR",
            "gov.nih.nci.cabig.caaers.rules.sponsor", "Sponsor rules"),
    INSTITUTION_BASE("INSTITUTION", "/CAAERS_BASE/INSTITUTION",
            "gov.nih.nci.cabig.caaers.rules.institution", "Institution rules"),
    SPONSOR_DEFINED_STUDY_BASE("SPONSOR_DEFINED_STUDY", "/CAAERS_BASE/SPONSOR_DEFINED_STUDY",
            "gov.nih.nci.cabig.caaers.rules.sponsor.study", "Sponsor defined rules for a study"),
    INSTITUTION_DEFINED_STUDY_BASE("INSTITUTION_DEFINED_STUDY", "/CAAERS_BASE/INSTITUTION_DEFINED_STUDY",
            "gov.nih.nci.cabig.caaers.rules.institution.study", "Institution defined rules for a study")
    ;

    private String name;

    private String path;

    private String packagePrefix;

    private String desc;

    CategoryConfiguration(String name, String path, String packagePrefix, String desc) {
        this.name = name;
        this.path = path;
        this.packagePrefix = packagePrefix;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    public String getDescription() {
        return desc;
    }


    public static CategoryConfiguration findByPackagePrefix(String prefix){
        for(CategoryConfiguration c : values()){
            if(c.packagePrefix.equals(prefix)) return c;
        }
        
        return null;
    }

    public static CategoryConfiguration findByPath(String path){
        for(CategoryConfiguration c : values()){
            if(c.path.equals(path)) return c;
        }
        return null;
    }

    public static CategoryConfiguration findByName(String name){
        for(CategoryConfiguration c : values()){
            if(c.name.equals(name)) return c;
        }
        return null;
    }
}
