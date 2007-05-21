package gov.nih.nci.cabig.caaers.rules.common;

import java.rmi.RemoteException;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;

public class RuleUtil {
	
	private static final String CAAERS_RULEBASE_CATEGORY_NAME = "CAAERS_RULEBASE";
	private static final String CAAERS_RULEBASE_CATEGORY_PATH ="/CAAERS_RULEBASE";
	
	private static final String INSTITUTION_BASE_CATEGORY_NAME ="INSTITUTION";
	private static final String INSTITUTION_BASE_CATEGORY_PATH = "/CAAERS_RULEBASE/INSTITUTION";
	
	private static final String SPONSOR_BASE_CATEGORY_NAME="SPONSOR";
	private static final String SPONSOR_BASE_CATEGORY_PATH="/CAAERS_RULEBASE/SPONSOR";
	
	private static final String STUDY_BASE_CATEGORY_NAME ="STUDY";
	private static final String STUDY_BASE_CATEGORY_PATH="/CAAERS_RULEBASE/SPONSOR";
	
	
	public static Category getSponsorSpecificCategory(RuleAuthoringService authService, String sponsorName, String ruleSetName){
		Category cat = null;
		
		String sponsorSpecificCatehoryPath = SPONSOR_BASE_CATEGORY_PATH+"/"+getStringWithoutSpaces(sponsorName);
		String sponsorSpecificRuleSetCategoryPath = sponsorSpecificCatehoryPath+"/"+getStringWithoutSpaces(ruleSetName);
		/**
		 * First check if the caAERS rule base exist
		 */
		boolean baseExist = categoryExist(authService, CAAERS_RULEBASE_CATEGORY_PATH);
		/**
		 * If does not exist then go ahead and create it
		 */
		if(!baseExist){
			createBaseCategory(authService);
		}
		/**
		 * Now check if the Sponsor base category exist
		 */
		
		boolean sponsorBaseExist=categoryExist(authService,SPONSOR_BASE_CATEGORY_PATH);
		/**
		 * If does not exist then go ahead and create it
		 */
		if(!sponsorBaseExist){
		  createSponsorBaseCategory(authService);
		}
		
		boolean sponsorSpecificCategoryExist = categoryExist(authService,sponsorSpecificCatehoryPath);
		
		/**
		 * If sponsor specific category does not exist then go ahead nd create one
		 */
		
		if(!sponsorSpecificCategoryExist){
			Category category = new Category();
			MetaData metaData = new MetaData();
			category.setPath(SPONSOR_BASE_CATEGORY_PATH);
			metaData.setName(getStringWithoutSpaces(sponsorName));
			metaData.setDescription(sponsorName+" Rule Base category");
			category.setMetaData(metaData);
			try {
				authService.createCategory(category);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		boolean sponsorSpecificRuleSetCategoryExist = categoryExist(authService,sponsorSpecificRuleSetCategoryPath);
		
		/**
		 * If it does not exist then go ahead and create it
		 */
		
		if(!sponsorSpecificRuleSetCategoryExist){
			Category category = new Category();
			MetaData metaData = new MetaData();
			category.setPath(sponsorSpecificCatehoryPath);
			metaData.setName(getStringWithoutSpaces(ruleSetName));
			metaData.setDescription(ruleSetName);
			category.setMetaData(metaData);
			try {
				authService.createCategory(category);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			cat = authService.getCategory(sponsorSpecificRuleSetCategoryPath);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cat;
	}
	
	
	public static Category getInstitutionSpecificCategory(RuleAuthoringService authService, String institutionName, String ruleSetName){
		Category cat = null;
		
		String institutionSpecificCatehoryPath = SPONSOR_BASE_CATEGORY_PATH+"/"+getStringWithoutSpaces(institutionName);
		String institutionSpecificRuleSetCategoryPath = institutionSpecificCatehoryPath+"/"+getStringWithoutSpaces(ruleSetName);
		/**
		 * First check if the caAERS rule base exist
		 */
		boolean baseExist = categoryExist(authService, CAAERS_RULEBASE_CATEGORY_PATH);
		/**
		 * If does not exist then go ahead and create it
		 */
		if(!baseExist){
			createBaseCategory(authService);
		}
		/**
		 * Now check if the Sponsor base category exist
		 */
		
		boolean institutionBaseExist=categoryExist(authService,INSTITUTION_BASE_CATEGORY_PATH);
		/**
		 * If does not exist then go ahead and create it
		 */
		if(!institutionBaseExist){
		  createInstitutionBaseCategory(authService);
		}
		
		boolean institutionSpecificCategoryExist = categoryExist(authService,institutionSpecificCatehoryPath);
		
		/**
		 * If sponsor specific category does not exist then go ahead nd create one
		 */
		
		if(!institutionSpecificCategoryExist){
			Category category = new Category();
			MetaData metaData = new MetaData();
			category.setPath(INSTITUTION_BASE_CATEGORY_PATH);
			metaData.setName(getStringWithoutSpaces(institutionName));
			metaData.setDescription(institutionName+" Rule Base category");
			category.setMetaData(metaData);
			try {
				authService.createCategory(category);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		boolean institutionSpecificRuleSetCategoryExist = categoryExist(authService,institutionSpecificRuleSetCategoryPath);
		
		/**
		 * If it does not exist then go ahead and create it
		 */
		
		if(!institutionSpecificRuleSetCategoryExist){
			Category category = new Category();
			MetaData metaData = new MetaData();
			category.setPath(institutionSpecificCatehoryPath);
			metaData.setName(getStringWithoutSpaces(ruleSetName));
			metaData.setDescription(ruleSetName);
			category.setMetaData(metaData);
			try {
				authService.createCategory(category);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			cat = authService.getCategory(institutionSpecificRuleSetCategoryPath);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cat;
	}
	/**
	 * 
	 * @param authService
	 * @param sponsorName
	 * @param studyShortTitle
	 * @param ruleSetName
	 * @return
	 * 
	 * This method sets up the category for study and sponsor combination specific 
	 *       It will be like
	 *         /
	 *           CAARES_RULEBASE
	 *                         STUDY
	 *                              <STUDY_SHORT_TITLE>
	 *                                                 <SPONSOR_NAME>
	 *                                                               <RULE_SET_NAME>
	 */
	
	public static Category getStudySponsorSpecificCategory(RuleAuthoringService authService, String sponsorName, String studyShortTitle, String ruleSetName){
		Category cat = null;
		
		String studySpecificCatehoryPath = SPONSOR_BASE_CATEGORY_PATH+"/"+getStringWithoutSpaces(studyShortTitle);
		String studySponsorSpecificCategoryPath = studySpecificCatehoryPath+"/"+getStringWithoutSpaces(sponsorName);
		String studySponsorRuleSetSpecificCategoryPath = studySponsorSpecificCategoryPath+"/"+ getStringWithoutSpaces(ruleSetName);
		/**
		 * First check if the caAERS rule base exist
		 */
		boolean baseExist = categoryExist(authService, CAAERS_RULEBASE_CATEGORY_PATH);
		/**
		 * If does not exist then go ahead and create it
		 */
		if(!baseExist){
			createBaseCategory(authService);
		}
		/**
		 * Now check if the Sponsor base category exist
		 */
		
		boolean studyBaseExist=categoryExist(authService,STUDY_BASE_CATEGORY_PATH);
		/**
		 * If does not exist then go ahead and create it
		 */
		if(!studyBaseExist){
		  createStudyBaseCategory(authService);
		}
		
		boolean studySpecificCategoryExist = categoryExist(authService,studySpecificCatehoryPath);
		
		/**
		 * If sponsor study specific category does not exist then go ahead nd create one
		 */
		
		if(!studySpecificCategoryExist){
			Category category = new Category();
			MetaData metaData = new MetaData();
			category.setPath(STUDY_BASE_CATEGORY_PATH);
			metaData.setName(getStringWithoutSpaces(studyShortTitle));
			metaData.setDescription(studyShortTitle+" Rule Base category");
			category.setMetaData(metaData);
			try {
				authService.createCategory(category);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		boolean studySponsorSpecificCategoryExist = categoryExist(authService, studySponsorSpecificCategoryPath);
		
		/**
		 * If the category for a particular sponsor does not exist in particular study then go ahead and create one
		 */
		
		if(!studySponsorSpecificCategoryExist){
			Category category = new Category();
			MetaData metaData = new MetaData();
			category.setPath(studySpecificCatehoryPath);
			metaData.setName(getStringWithoutSpaces(sponsorName));
			metaData.setDescription(sponsorName);
			category.setMetaData(metaData);
			try {
				authService.createCategory(category);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		boolean studySponsorRuleSetSpecificCategoryExist = categoryExist(authService,studySponsorRuleSetSpecificCategoryPath);
		
		/**
		 * If it does not exist then go ahead and create it
		 */
		
		if(!studySponsorRuleSetSpecificCategoryExist){
			Category category = new Category();
			MetaData metaData = new MetaData();
			category.setPath(studySponsorSpecificCategoryPath);
			metaData.setName(getStringWithoutSpaces(ruleSetName));
			metaData.setDescription(ruleSetName);
			category.setMetaData(metaData);
			try {
				authService.createCategory(category);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			cat = authService.getCategory(studySponsorRuleSetSpecificCategoryPath);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cat;
	}
	
	public static String getPackageName(String prefix, String entityName, String ruleSetName){
		String str = prefix+"."+getStringWithoutSpaces(entityName)+"."+getStringWithoutSpaces(ruleSetName);
		return str;
	}
	
	public static String getStudySponsorSpecificPackageName(String prefix,String studyShortTitle, String sponsorName,String ruleSetName){
		String str = prefix+"."+ getStringWithoutSpaces(studyShortTitle)+"."+getStringWithoutSpaces(sponsorName)+"."+getStringWithoutSpaces(ruleSetName);
		
		return str;
	}
	
	public static String getStringWithoutSpaces(String str){
		String _str= str.trim();
		return _str.replace(" ", "_");
	}
	
	private static boolean categoryExist(RuleAuthoringService authService, String path){
		boolean exist = true;
		Category base_cat = null;
		try {
		    base_cat = authService.getCategory(path);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exist = base_cat==null?false:true;
		return exist;
	}
	
	private static void createBaseCategory(RuleAuthoringService authService){
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath("/");
		metaData.setName(CAAERS_RULEBASE_CATEGORY_NAME);
		metaData.setDescription("caAERS Base Rule Level");
		category.setMetaData(metaData);
		try {
			authService.createCategory(category);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createSponsorBaseCategory(RuleAuthoringService authService){
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath(CAAERS_RULEBASE_CATEGORY_PATH);
		metaData.setName(SPONSOR_BASE_CATEGORY_NAME);
		metaData.setDescription("Sponsor base category");
		category.setMetaData(metaData);
		try {
			authService.createCategory(category);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createInstitutionBaseCategory(RuleAuthoringService authService){
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath(CAAERS_RULEBASE_CATEGORY_PATH);
		metaData.setName(INSTITUTION_BASE_CATEGORY_NAME);
		metaData.setDescription("Institution base category");
		category.setMetaData(metaData);
		try {
			authService.createCategory(category);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createStudyBaseCategory(RuleAuthoringService authService){
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath(CAAERS_RULEBASE_CATEGORY_PATH);
		metaData.setName(STUDY_BASE_CATEGORY_NAME);
		metaData.setDescription("Study base category");
		category.setMetaData(metaData);
		try {
			authService.createCategory(category);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
