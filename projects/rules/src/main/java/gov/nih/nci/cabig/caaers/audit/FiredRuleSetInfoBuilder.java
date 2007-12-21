package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;

public class FiredRuleSetInfoBuilder {
	
	public FiredRuleSetInfo build(String bindUri){
		FiredRuleSetInfo firedRuleSetInfo = new FiredRuleSetInfo(bindUri);
		
		String baseString = CategoryConfiguration.CAAERS_BASE.getPackagePrefix();
		String str1 = bindUri.substring(baseString.length()+1);
		
		int i= str1.indexOf(".");
		if(i < 0){
			//this is a system wide rule, defined at CAAERS_BASE
			firedRuleSetInfo.setRuleSetName(str1);
			firedRuleSetInfo.setRuleSetType("System Rules [" + baseString + "]");
			
		}else{
			String role = str1.substring(0,i);
			firedRuleSetInfo.setRole(role);

			if(role.equalsIgnoreCase("sponsor")){
				buildForSponsor(str1, firedRuleSetInfo);
			}else{
				buildForInstitution(str1, firedRuleSetInfo);
			}
		}
		
				
		return firedRuleSetInfo;
	}
	
	private void buildForSponsor(String partialUri, FiredRuleSetInfo firedRuleSetInfo){
		int i = partialUri.indexOf("sponsor.study.");
		if(i==-1){
			
			int k = partialUri.indexOf(".");
			String str1 = partialUri.substring(k+1, partialUri.length());
			int m = str1.indexOf(".");
			String organizationName = str1.substring(0,m);
			firedRuleSetInfo.setOrganizationName(organizationName);
			String ruleSetName = str1.substring(m+1, str1.length());
			firedRuleSetInfo.setRuleSetName(ruleSetName);
			firedRuleSetInfo.setRuleSetType("Rules for Sponsor");
			
		}else{
			buildForSponsorDefinedStudyLevelRules(partialUri, firedRuleSetInfo);
		}
		
	}
	
	private void buildForInstitution(String partialUri, FiredRuleSetInfo firedRuleSetInfo){
		int i = partialUri.indexOf("institution.study.");
		if(i==-1){
			
			int k = partialUri.indexOf(".");
			String str1 = partialUri.substring(k+1, partialUri.length());
			int m = str1.indexOf(".");
			String organizationName = str1.substring(0,m);
			firedRuleSetInfo.setOrganizationName(organizationName);
			String ruleSetName = str1.substring(m+1, str1.length());
			firedRuleSetInfo.setRuleSetName(ruleSetName);
			firedRuleSetInfo.setRuleSetType("Rules for Institution");
			
			
		}else{
			buildForInstitutionDefinedStudyLevelRules(partialUri, firedRuleSetInfo);
		}
	}
	
	private void buildForSponsorDefinedStudyLevelRules(String partialUri, FiredRuleSetInfo firedRuleSetInfo){
		int i = partialUri.indexOf(".");
		String useFullString = partialUri.substring(i+1,partialUri.length());
		int a = useFullString.indexOf(".");
		String _str = useFullString.substring(a+1,useFullString.length());
		
		int j = _str.indexOf(".");
		String studyName = _str.substring(0,j);
		firedRuleSetInfo.setStudyName(studyName);
		String str1 = _str.substring(j+1, _str.length());
		
		int k = str1.indexOf(".");
		String organizationName = str1.substring(0,k);
		firedRuleSetInfo.setOrganizationName(organizationName);
		String ruleSetName = str1.substring(k+1,str1.length());
		firedRuleSetInfo.setRuleSetName(ruleSetName);
		firedRuleSetInfo.setRuleSetType("Rules for Sponsor Defined Study");
		
	}
	private void buildForInstitutionDefinedStudyLevelRules(String partialUri, FiredRuleSetInfo firedRuleSetInfo){
		int i = partialUri.indexOf(".");
		String useFullString = partialUri.substring(i+1,partialUri.length());
		int a = useFullString.indexOf(".");
		String _str = useFullString.substring(a+1,useFullString.length());
		
		int j = _str.indexOf(".");
		String studyName = _str.substring(0,j);
		firedRuleSetInfo.setStudyName(studyName);
		String str1 = _str.substring(j+1, _str.length());
		
		int k = str1.indexOf(".");
		String organizationName = str1.substring(0,k);
		firedRuleSetInfo.setOrganizationName(organizationName);
		String ruleSetName = str1.substring(k+1,str1.length());
		firedRuleSetInfo.setRuleSetName(ruleSetName);
		firedRuleSetInfo.setRuleSetType("Rules for Institution Defined Study");
		
	}
	
	public static void main(String[] args){
		FiredRuleSetInfoBuilder bc = new FiredRuleSetInfoBuilder();
		/**
		 * Sponsor level rules
		 */
		String sponsorLevel = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), "NCI", "ASSES_AE_RULES");
		
		/**
		 * Sponsor study level
		 */
		
		String sponsorstudylevel = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix(), "mytestStudy", "wake_forest", "Asses_AE_RULES");
		
		/**
		 * Institution level
		 */
		String institutionlevel= RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix(), "Duke Cancer Center", "Report_ scheuling rules");
		
		/**
		 * Institution study level
		 */
		String institutionStudy = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix(), "yourteststudy", "Semanticbits", "Report Scheduling rules");
		System.out.println(institutionStudy);
		bc.build(institutionStudy);
	}

}
