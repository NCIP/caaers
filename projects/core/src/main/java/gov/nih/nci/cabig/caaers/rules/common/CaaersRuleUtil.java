package gov.nih.nci.cabig.caaers.rules.common;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.brxml.Category;
import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.FieldConstraint;
import com.semanticbits.rules.brxml.LiteralRestriction;
import com.semanticbits.rules.brxml.MetaData;
import com.semanticbits.rules.utils.RuleUtil;

public class CaaersRuleUtil {
	private static final Log log = LogFactory.getLog(CaaersRuleUtil.class);

    public static Category getSponsorSpecificCategory(RuleAuthoringService authService,
                    String sponsorName, String ruleSetName) throws Exception {
        Category cat = null;

        // String sponsorSpecificCatehoryPath =
        // SPONSOR_BASE_CATEGORY_PATH+"/"+getStringWithoutSpaces(sponsorName);
        String sponsorSpecificCatehoryPath = CategoryConfiguration.SPONSOR_BASE.getPath() + "/"
                        + RuleUtil.getStringWithoutSpaces(sponsorName);

        String sponsorSpecificRuleSetCategoryPath = sponsorSpecificCatehoryPath + "/"
                        + RuleUtil.getStringWithoutSpaces(ruleSetName);
        /**
         * First check if the caAERS rule base exist
         */
        boolean baseExist = RuleUtil.categoryExist(authService, CategoryConfiguration.CAAERS_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!baseExist) {
            // createBaseCategory(authService);
        	RuleUtil.createCategory(authService, "/", CategoryConfiguration.CAAERS_BASE.getName(),
                            CategoryConfiguration.CAAERS_BASE.getDescription());
        }
        /**
         * Now check if the Sponsor base category exist
         */

        boolean sponsorBaseExist = RuleUtil.categoryExist(authService, CategoryConfiguration.SPONSOR_BASE
                        .getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!sponsorBaseExist) {
            // createSponsorBaseCategory(authService);
        	RuleUtil.createCategory(authService, CategoryConfiguration.CAAERS_BASE.getPath(),
                            CategoryConfiguration.SPONSOR_BASE.getName(),
                            CategoryConfiguration.SPONSOR_BASE.getDescription());
        }

        boolean sponsorSpecificCategoryExist = RuleUtil.categoryExist(authService,
                        sponsorSpecificCatehoryPath);

        /**
         * If sponsor specific category does not exist then go ahead nd create one
         */

        if (!sponsorSpecificCategoryExist) {
            String desc = sponsorName + " Rule Base category";
            RuleUtil.createCategory(authService, CategoryConfiguration.SPONSOR_BASE.getPath(),
            		RuleUtil.getStringWithoutSpaces(sponsorName), desc);
        }

        boolean sponsorSpecificRuleSetCategoryExist = RuleUtil.categoryExist(authService,
                        sponsorSpecificRuleSetCategoryPath);

        /**
         * If it does not exist then go ahead and create it
         */

        if (!sponsorSpecificRuleSetCategoryExist) {
        	RuleUtil.createCategory(authService, sponsorSpecificCatehoryPath,
            		RuleUtil.getStringWithoutSpaces(ruleSetName), ruleSetName);
        }

        cat = authService.getCategory(sponsorSpecificRuleSetCategoryPath);

        return cat;
    }

    public static Category getInstitutionSpecificCategory(RuleAuthoringService authService,
                    String institutionName, String ruleSetName) throws Exception {
        Category cat = null;

        String institutionSpecificCatehoryPath = CategoryConfiguration.INSTITUTION_BASE.getPath()
                        + "/" + RuleUtil.getStringWithoutSpaces(institutionName);
        String institutionSpecificRuleSetCategoryPath = institutionSpecificCatehoryPath + "/"
                        + RuleUtil.getStringWithoutSpaces(ruleSetName);
        /**
         * First check if the caAERS rule base exist
         */
        boolean baseExist = RuleUtil.categoryExist(authService, CategoryConfiguration.CAAERS_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!baseExist) {
        	RuleUtil.createCategory(authService, "/", CategoryConfiguration.CAAERS_BASE.getName(),
                            CategoryConfiguration.CAAERS_BASE.getDescription());
        }
        /**
         * Now check if the Sponsor base category exist
         */

        boolean institutionBaseExist = RuleUtil.categoryExist(authService,
                        CategoryConfiguration.INSTITUTION_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */

        if (!institutionBaseExist) {
            // createInstitutionBaseCategory(authService);
        	RuleUtil.createCategory(authService, CategoryConfiguration.CAAERS_BASE.getPath(),
                            CategoryConfiguration.INSTITUTION_BASE.getName(),
                            CategoryConfiguration.INSTITUTION_BASE.getDescription());
        }

        boolean institutionSpecificCategoryExist = RuleUtil.categoryExist(authService,
                        institutionSpecificCatehoryPath);

        /**
         * If sponsor specific category does not exist then go ahead nd create one
         */

        if (!institutionSpecificCategoryExist) {
            String desc = institutionName + " Rule Base category";
            RuleUtil.createCategory(authService, CategoryConfiguration.INSTITUTION_BASE.getPath(),
            		RuleUtil.getStringWithoutSpaces(institutionName), desc);
        }

        boolean institutionSpecificRuleSetCategoryExist = RuleUtil.categoryExist(authService,
                        institutionSpecificRuleSetCategoryPath);

        /**
         * If it does not exist then go ahead and create it
         */

        if (!institutionSpecificRuleSetCategoryExist) {
        	RuleUtil.createCategory(authService, institutionSpecificCatehoryPath,
            		RuleUtil.getStringWithoutSpaces(ruleSetName), ruleSetName);
        }

        cat = authService.getCategory(institutionSpecificRuleSetCategoryPath);

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
     * This method sets up the category for study and sponsor combination specific It will be like /
     * CAARES_RULEBASE STUDY <STUDY_SHORT_TITLE> <SPONSOR_NAME> <RULE_SET_NAME>
     */

    public static Category getStudySponsorSpecificCategory(RuleAuthoringService authService,
                    String sponsorName, String studyShortTitle, String ruleSetName)
                    throws Exception {
        Category cat = null;

        String studySpecificCatehoryPath = CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE
                        .getPath()
                        + "/" + RuleUtil.getStringWithoutSpaces(studyShortTitle);
        String studySponsorSpecificCategoryPath = studySpecificCatehoryPath + "/"
                        + RuleUtil.getStringWithoutSpaces(sponsorName);
        String studySponsorRuleSetSpecificCategoryPath = studySponsorSpecificCategoryPath + "/"
                        + RuleUtil.getStringWithoutSpaces(ruleSetName);
        /**
         * First check if the caAERS rule base exist
         */
        boolean baseExist = RuleUtil.categoryExist(authService, CategoryConfiguration.CAAERS_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!baseExist) {
        	RuleUtil.createCategory(authService, "/", CategoryConfiguration.CAAERS_BASE.getName(),
                            CategoryConfiguration.CAAERS_BASE.getDescription());
        }
        /**
         * Now check if the Sponsor base category exist
         */

        boolean studyBaseExist = RuleUtil.categoryExist(authService,
                        CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */

        if (!studyBaseExist) {
        	RuleUtil.createCategory(authService, CategoryConfiguration.CAAERS_BASE.getPath(),
                            CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getName(),
                            CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getDescription());
        }

        boolean studySpecificCategoryExist = RuleUtil.categoryExist(authService, studySpecificCatehoryPath);

        /**
         * If sponsor study specific category does not exist then go ahead nd create one
         */

        if (!studySpecificCategoryExist) {
            String desc = studyShortTitle + " Rule Base category";
            RuleUtil.createCategory(authService, CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPath(),
            		RuleUtil.getStringWithoutSpaces(studyShortTitle), desc);
        }

        boolean studySponsorSpecificCategoryExist = RuleUtil.categoryExist(authService,
                        studySponsorSpecificCategoryPath);

        /**
         * If the category for a particular sponsor does not exist in particular study then go ahead
         * and create one
         */

        if (!studySponsorSpecificCategoryExist) {
        	RuleUtil.createCategory(authService, studySpecificCatehoryPath,
            		RuleUtil.getStringWithoutSpaces(sponsorName), sponsorName);
        }

        boolean studySponsorRuleSetSpecificCategoryExist = RuleUtil.categoryExist(authService,
                        studySponsorRuleSetSpecificCategoryPath);

        /**
         * If it does not exist then go ahead and create it
         */

        if (!studySponsorRuleSetSpecificCategoryExist) {
        	RuleUtil.createCategory(authService, studySponsorSpecificCategoryPath,
            		RuleUtil.getStringWithoutSpaces(ruleSetName), ruleSetName);
        }

        cat = authService.getCategory(studySponsorRuleSetSpecificCategoryPath);

        return cat;
    }

    public static Category getStudyInstitutionSpecificCategory(RuleAuthoringService authService,
                    String institutionName, String studyShortTitle, String ruleSetName)
                    throws Exception {
        Category cat = null;

        String studySpecificCatehoryPath = CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE
                        .getPath()
                        + "/" + RuleUtil.getStringWithoutSpaces(studyShortTitle);
        String studyInstitutionSpecificCategoryPath = studySpecificCatehoryPath + "/"
                        + RuleUtil.getStringWithoutSpaces(institutionName);
        String studyInstitutionRuleSetSpecificCategoryPath = studyInstitutionSpecificCategoryPath
                        + "/" + RuleUtil.getStringWithoutSpaces(ruleSetName);
        /**
         * First check if the caAERS rule base exist
         */
        boolean baseExist = RuleUtil.categoryExist(authService, CategoryConfiguration.CAAERS_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!baseExist) {
        	RuleUtil.createCategory(authService, "/", CategoryConfiguration.CAAERS_BASE.getName(),
                            CategoryConfiguration.CAAERS_BASE.getDescription());
        }
        /**
         * Now check if the Sponsor base category exist
         */

        boolean studyBaseExist = RuleUtil.categoryExist(authService,
                        CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */

        if (!studyBaseExist) {
        	RuleUtil.createCategory(authService, CategoryConfiguration.CAAERS_BASE.getPath(),
                            CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getName(),
                            CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getDescription());
        }

        boolean studySpecificCategoryExist = RuleUtil.categoryExist(authService, studySpecificCatehoryPath);

        /**
         * If sponsor study specific category does not exist then go ahead nd create one
         */

        if (!studySpecificCategoryExist) {
            String desc = studyShortTitle + " Rule Base category";
            RuleUtil.createCategory(authService, CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE
                            .getPath(), RuleUtil.getStringWithoutSpaces(studyShortTitle), desc);
        }

        boolean studySponsorSpecificCategoryExist = RuleUtil.categoryExist(authService,
                        studyInstitutionSpecificCategoryPath);

        /**
         * If the category for a particular sponsor does not exist in particular study then go ahead
         * and create one
         */

        if (!studySponsorSpecificCategoryExist) {
        	RuleUtil.createCategory(authService, studySpecificCatehoryPath,
            		RuleUtil.getStringWithoutSpaces(institutionName), institutionName);
        }

        boolean studySponsorRuleSetSpecificCategoryExist = RuleUtil.categoryExist(authService,
                        studyInstitutionRuleSetSpecificCategoryPath);

        /**
         * If it does not exist then go ahead and create it
         */

        if (!studySponsorRuleSetSpecificCategoryExist) {
        	RuleUtil.createCategory(authService, studyInstitutionSpecificCategoryPath,
            		RuleUtil.getStringWithoutSpaces(ruleSetName), ruleSetName);
        }

        cat = authService.getCategory(studyInstitutionRuleSetSpecificCategoryPath);

        return cat;
    }

    public static String getStudySponsorSpecificPackageName(String prefix, String studyShortTitle,
                    String sponsorName, String ruleSetName) {
        String str = prefix + "." + RuleUtil.getStringWithoutSpaces(studyShortTitle) + "."
                        + RuleUtil.getStringWithoutSpaces(sponsorName) + "."
                        + RuleUtil.getStringWithoutSpaces(ruleSetName);

        return str;
    }

    public static String getStudyInstitutionSpecificPackageName(String prefix,
                    String studyShortTitle, String institutionName, String ruleSetName) {
        String str = prefix + "." + RuleUtil.getStringWithoutSpaces(studyShortTitle) + "."
                        + RuleUtil.getStringWithoutSpaces(institutionName) + "."
                        + RuleUtil.getStringWithoutSpaces(ruleSetName);

        return str;
    }

    public static String getSponsorSpecificPath(String sponsorName) {
        String sponsorSpecificCatehoryPath = CategoryConfiguration.SPONSOR_BASE.getPath() + "/"
                        + RuleUtil.getStringWithoutSpaces(sponsorName);
        return sponsorSpecificCatehoryPath;
    }

    public static String getInstitutionSpecificPath(String institutionName) {
        String institutionSpecificCatehoryPath = CategoryConfiguration.INSTITUTION_BASE.getPath()
                        + "/" + RuleUtil.getStringWithoutSpaces(institutionName);
        return institutionSpecificCatehoryPath;
    }

    public static String getStudySponsorSpecificPath(String studyShortTitle, String sponsorName) {
        String studySponsorSpecificPath = CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE
                        .getPath()
                        + "/"
                        + RuleUtil.getStringWithoutSpaces(studyShortTitle)
                        + "/"
                        + RuleUtil.getStringWithoutSpaces(sponsorName);
        return studySponsorSpecificPath;
    }

    public static String getStudyInstitutionSpecificPath(String studyShortTitle, String sponsorName) {
        String studySponsorSpecificPath = CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE
                        .getPath()
                        + "/"
                        + RuleUtil.getStringWithoutSpaces(studyShortTitle)
                        + "/"
                        + RuleUtil.getStringWithoutSpaces(sponsorName);
        return studySponsorSpecificPath;
    }


}
