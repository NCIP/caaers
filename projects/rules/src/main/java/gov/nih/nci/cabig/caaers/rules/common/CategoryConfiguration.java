/**
 * 
 */
package gov.nih.nci.cabig.caaers.rules.common;

/**
 * @author vinaykumar
 *
 */
public enum CategoryConfiguration {
	
	CAAERS_BASE("CAAERS_BASE","CAAERS_BASE","gov.nih.nci.cabig.caaers.rules","The rule base for all caaers rules"),
	SPONSOR_BASE("SPONSOR","/CAAERS_BASE/SPONSOR","gov.nih.nci.cabig.caaers.rules.sponsor",""),
	INSTITUTION_BASE("INSTITUTION","/CAAERS_BASE/INSTITUTION","gov.nih.nci.cabig.caaers.rules.institution",""),
	STUDY_BASE("STUDY","/CAAERS_BASE/STUDY","gov.nih.nci.cabig.caaers.rules.study","");
	
	
	private String name;
	private String path;
	private String packagePrefix;
	private String desc;
	
	CategoryConfiguration(String name, String path, String packagePrefix,String desc){
		this.name=name;
		this.path=path;
		this.packagePrefix=packagePrefix;
		this.desc=desc;
	}
	
	public String getName(){
		return name;
	}
	public String getPath(){
		return path;
	}
	public String getPackagePrefix(){
		return packagePrefix;
	}
	public String getDescription(){
		return desc;
	}

}
