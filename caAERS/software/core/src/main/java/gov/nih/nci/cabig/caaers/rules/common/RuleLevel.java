/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.common;

public enum RuleLevel {
    Sponsor("Sponsor", "Rules for Sponsor", "sponsor"),
    Institution("Institution", "Rules for Institution", "institution"),
    SponsorDefinedStudy("SponsorDefinedStudy", "Rules for Sponsor Defined Study", "sponsor.study"),
    InstitutionDefinedStudy( "InstitutionDefinedStudy", "Rules for Institution Defined Study", "institution.study");

    private String name;
    private String desc;
    private String pkgPrefix;

    RuleLevel(String name, String desc, String pkgPrefix) {
        this.name = name;
        this.desc = desc;
        this.pkgPrefix = pkgPrefix;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }
    
    public String getPackageName(){
        return pkgPrefix;
    }
    
    public static RuleLevel getByName(String name){

        for(RuleLevel l : values()){
            if(l.getName().equals(name)) return l;
        }

        return null;
    }

    public boolean isSponsorBased(){
        return this == Sponsor || this == SponsorDefinedStudy;
    }


    public boolean isInstitutionBased(){
        return this == Institution || this == InstitutionDefinedStudy;
    }

    public boolean isStudyBased(){
        return this == InstitutionDefinedStudy || this == SponsorDefinedStudy;
    }
}
