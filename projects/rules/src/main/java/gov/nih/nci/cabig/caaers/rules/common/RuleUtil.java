package gov.nih.nci.cabig.caaers.rules.common;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RuleUtil {

    private static final Log log = LogFactory.getLog(RuleUtil.class);

    public static Category getSponsorSpecificCategory(RuleAuthoringService authService,
                    String sponsorName, String ruleSetName) throws Exception {
        Category cat = null;

        // String sponsorSpecificCatehoryPath =
        // SPONSOR_BASE_CATEGORY_PATH+"/"+getStringWithoutSpaces(sponsorName);
        String sponsorSpecificCatehoryPath = CategoryConfiguration.SPONSOR_BASE.getPath() + "/"
                        + getStringWithoutSpaces(sponsorName);

        String sponsorSpecificRuleSetCategoryPath = sponsorSpecificCatehoryPath + "/"
                        + getStringWithoutSpaces(ruleSetName);
        /**
         * First check if the caAERS rule base exist
         */
        boolean baseExist = categoryExist(authService, CategoryConfiguration.CAAERS_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!baseExist) {
            // createBaseCategory(authService);
            createCategory(authService, "/", CategoryConfiguration.CAAERS_BASE.getName(),
                            CategoryConfiguration.CAAERS_BASE.getDescription());
        }
        /**
         * Now check if the Sponsor base category exist
         */

        boolean sponsorBaseExist = categoryExist(authService, CategoryConfiguration.SPONSOR_BASE
                        .getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!sponsorBaseExist) {
            // createSponsorBaseCategory(authService);
            createCategory(authService, CategoryConfiguration.CAAERS_BASE.getPath(),
                            CategoryConfiguration.SPONSOR_BASE.getName(),
                            CategoryConfiguration.SPONSOR_BASE.getDescription());
        }

        boolean sponsorSpecificCategoryExist = categoryExist(authService,
                        sponsorSpecificCatehoryPath);

        /**
         * If sponsor specific category does not exist then go ahead nd create one
         */

        if (!sponsorSpecificCategoryExist) {
            String desc = sponsorName + " Rule Base category";
            createCategory(authService, CategoryConfiguration.SPONSOR_BASE.getPath(),
                            getStringWithoutSpaces(sponsorName), desc);
        }

        boolean sponsorSpecificRuleSetCategoryExist = categoryExist(authService,
                        sponsorSpecificRuleSetCategoryPath);

        /**
         * If it does not exist then go ahead and create it
         */

        if (!sponsorSpecificRuleSetCategoryExist) {
            createCategory(authService, sponsorSpecificCatehoryPath,
                            getStringWithoutSpaces(ruleSetName), ruleSetName);
        }

        cat = authService.getCategory(sponsorSpecificRuleSetCategoryPath);

        return cat;
    }

    public static Category getInstitutionSpecificCategory(RuleAuthoringService authService,
                    String institutionName, String ruleSetName) throws Exception {
        Category cat = null;

        String institutionSpecificCatehoryPath = CategoryConfiguration.INSTITUTION_BASE.getPath()
                        + "/" + getStringWithoutSpaces(institutionName);
        String institutionSpecificRuleSetCategoryPath = institutionSpecificCatehoryPath + "/"
                        + getStringWithoutSpaces(ruleSetName);
        /**
         * First check if the caAERS rule base exist
         */
        boolean baseExist = categoryExist(authService, CategoryConfiguration.CAAERS_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!baseExist) {
            createCategory(authService, "/", CategoryConfiguration.CAAERS_BASE.getName(),
                            CategoryConfiguration.CAAERS_BASE.getDescription());
        }
        /**
         * Now check if the Sponsor base category exist
         */

        boolean institutionBaseExist = categoryExist(authService,
                        CategoryConfiguration.INSTITUTION_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */

        if (!institutionBaseExist) {
            // createInstitutionBaseCategory(authService);
            createCategory(authService, CategoryConfiguration.CAAERS_BASE.getPath(),
                            CategoryConfiguration.INSTITUTION_BASE.getName(),
                            CategoryConfiguration.INSTITUTION_BASE.getDescription());
        }

        boolean institutionSpecificCategoryExist = categoryExist(authService,
                        institutionSpecificCatehoryPath);

        /**
         * If sponsor specific category does not exist then go ahead nd create one
         */

        if (!institutionSpecificCategoryExist) {
            String desc = institutionName + " Rule Base category";
            createCategory(authService, CategoryConfiguration.INSTITUTION_BASE.getPath(),
                            getStringWithoutSpaces(institutionName), desc);
        }

        boolean institutionSpecificRuleSetCategoryExist = categoryExist(authService,
                        institutionSpecificRuleSetCategoryPath);

        /**
         * If it does not exist then go ahead and create it
         */

        if (!institutionSpecificRuleSetCategoryExist) {
            createCategory(authService, institutionSpecificCatehoryPath,
                            getStringWithoutSpaces(ruleSetName), ruleSetName);
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
                        + "/" + getStringWithoutSpaces(studyShortTitle);
        String studySponsorSpecificCategoryPath = studySpecificCatehoryPath + "/"
                        + getStringWithoutSpaces(sponsorName);
        String studySponsorRuleSetSpecificCategoryPath = studySponsorSpecificCategoryPath + "/"
                        + getStringWithoutSpaces(ruleSetName);
        /**
         * First check if the caAERS rule base exist
         */
        boolean baseExist = categoryExist(authService, CategoryConfiguration.CAAERS_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!baseExist) {
            createCategory(authService, "/", CategoryConfiguration.CAAERS_BASE.getName(),
                            CategoryConfiguration.CAAERS_BASE.getDescription());
        }
        /**
         * Now check if the Sponsor base category exist
         */

        boolean studyBaseExist = categoryExist(authService,
                        CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */

        if (!studyBaseExist) {
            createCategory(authService, CategoryConfiguration.CAAERS_BASE.getPath(),
                            CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getName(),
                            CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getDescription());
        }

        boolean studySpecificCategoryExist = categoryExist(authService, studySpecificCatehoryPath);

        /**
         * If sponsor study specific category does not exist then go ahead nd create one
         */

        if (!studySpecificCategoryExist) {
            String desc = studyShortTitle + " Rule Base category";
            createCategory(authService, CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPath(),
                            getStringWithoutSpaces(studyShortTitle), desc);
        }

        boolean studySponsorSpecificCategoryExist = categoryExist(authService,
                        studySponsorSpecificCategoryPath);

        /**
         * If the category for a particular sponsor does not exist in particular study then go ahead
         * and create one
         */

        if (!studySponsorSpecificCategoryExist) {
            createCategory(authService, studySpecificCatehoryPath,
                            getStringWithoutSpaces(sponsorName), sponsorName);
        }

        boolean studySponsorRuleSetSpecificCategoryExist = categoryExist(authService,
                        studySponsorRuleSetSpecificCategoryPath);

        /**
         * If it does not exist then go ahead and create it
         */

        if (!studySponsorRuleSetSpecificCategoryExist) {
            createCategory(authService, studySponsorSpecificCategoryPath,
                            getStringWithoutSpaces(ruleSetName), ruleSetName);
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
                        + "/" + getStringWithoutSpaces(studyShortTitle);
        String studyInstitutionSpecificCategoryPath = studySpecificCatehoryPath + "/"
                        + getStringWithoutSpaces(institutionName);
        String studyInstitutionRuleSetSpecificCategoryPath = studyInstitutionSpecificCategoryPath
                        + "/" + getStringWithoutSpaces(ruleSetName);
        /**
         * First check if the caAERS rule base exist
         */
        boolean baseExist = categoryExist(authService, CategoryConfiguration.CAAERS_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */
        if (!baseExist) {
            createCategory(authService, "/", CategoryConfiguration.CAAERS_BASE.getName(),
                            CategoryConfiguration.CAAERS_BASE.getDescription());
        }
        /**
         * Now check if the Sponsor base category exist
         */

        boolean studyBaseExist = categoryExist(authService,
                        CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPath());
        /**
         * If does not exist then go ahead and create it
         */

        if (!studyBaseExist) {
            createCategory(authService, CategoryConfiguration.CAAERS_BASE.getPath(),
                            CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getName(),
                            CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getDescription());
        }

        boolean studySpecificCategoryExist = categoryExist(authService, studySpecificCatehoryPath);

        /**
         * If sponsor study specific category does not exist then go ahead nd create one
         */

        if (!studySpecificCategoryExist) {
            String desc = studyShortTitle + " Rule Base category";
            createCategory(authService, CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE
                            .getPath(), getStringWithoutSpaces(studyShortTitle), desc);
        }

        boolean studySponsorSpecificCategoryExist = categoryExist(authService,
                        studyInstitutionSpecificCategoryPath);

        /**
         * If the category for a particular sponsor does not exist in particular study then go ahead
         * and create one
         */

        if (!studySponsorSpecificCategoryExist) {
            createCategory(authService, studySpecificCatehoryPath,
                            getStringWithoutSpaces(institutionName), institutionName);
        }

        boolean studySponsorRuleSetSpecificCategoryExist = categoryExist(authService,
                        studyInstitutionRuleSetSpecificCategoryPath);

        /**
         * If it does not exist then go ahead and create it
         */

        if (!studySponsorRuleSetSpecificCategoryExist) {
            createCategory(authService, studyInstitutionSpecificCategoryPath,
                            getStringWithoutSpaces(ruleSetName), ruleSetName);
        }

        cat = authService.getCategory(studyInstitutionRuleSetSpecificCategoryPath);

        return cat;
    }

    public static String getPackageName(String prefix, String entityName, String ruleSetName) {
        String str = prefix + "." + getStringWithoutSpaces(entityName) + "."
                        + getStringWithoutSpaces(ruleSetName);
        return str;
    }

    public static String getStudySponsorSpecificPackageName(String prefix, String studyShortTitle,
                    String sponsorName, String ruleSetName) {
        String str = prefix + "." + getStringWithoutSpaces(studyShortTitle) + "."
                        + getStringWithoutSpaces(sponsorName) + "."
                        + getStringWithoutSpaces(ruleSetName);

        return str;
    }

    public static String getStudyInstitutionSpecificPackageName(String prefix,
                    String studyShortTitle, String institutionName, String ruleSetName) {
        String str = prefix + "." + getStringWithoutSpaces(studyShortTitle) + "."
                        + getStringWithoutSpaces(institutionName) + "."
                        + getStringWithoutSpaces(ruleSetName);

        return str;
    }

    public static String getStringWithoutSpaces(String str) {
        String _str = str.toLowerCase().trim();

        _str = _str.replace("-", "_");
        _str = _str.replace("&", "and");
        _str = _str.replace(" ", "_");
        _str = _str.replace("(", "_");
        _str = _str.replace(")", "_");
        _str = _str.replace("'", "_");
        _str = _str.replace("/", "_");
        _str = _str.replace(":", "_");

        return _str;
    }

    public static String getSponsorSpecificPath(String sponsorName) {
        String sponsorSpecificCatehoryPath = CategoryConfiguration.SPONSOR_BASE.getPath() + "/"
                        + getStringWithoutSpaces(sponsorName);
        return sponsorSpecificCatehoryPath;
    }

    public static String getInstitutionSpecificPath(String institutionName) {
        String institutionSpecificCatehoryPath = CategoryConfiguration.INSTITUTION_BASE.getPath()
                        + "/" + getStringWithoutSpaces(institutionName);
        return institutionSpecificCatehoryPath;
    }

    public static String getStudySponsorSpecificPath(String studyShortTitle, String sponsorName) {
        String studySponsorSpecificPath = CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE
                        .getPath()
                        + "/"
                        + getStringWithoutSpaces(studyShortTitle)
                        + "/"
                        + getStringWithoutSpaces(sponsorName);
        return studySponsorSpecificPath;
    }

    public static String getStudyInstitutionSpecificPath(String studyShortTitle, String sponsorName) {
        String studySponsorSpecificPath = CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE
                        .getPath()
                        + "/"
                        + getStringWithoutSpaces(studyShortTitle)
                        + "/"
                        + getStringWithoutSpaces(sponsorName);
        return studySponsorSpecificPath;
    }

    public static boolean categoryExist(RuleAuthoringService authService, String path) {
        boolean exist = true;
        Category base_cat = null;
        try {
            base_cat = authService.getCategory(path);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        exist = base_cat == null ? false : true;
        if (exist) {
            log.debug("Path:" + base_cat.getPath());
        }
        return exist;
    }

    private static void createCategory(RuleAuthoringService authService, String path, String name,
                    String desc) throws Exception {
        Category category = new Category();
        MetaData metaData = new MetaData();
        category.setPath(path);
        metaData.setName(name);
        metaData.setDescription(desc);
        category.setMetaData(metaData);

        authService.createCategory(category);

    }

    public static String readableColumn(Column column) {
        StringBuilder builder = new StringBuilder();

        FieldConstraint fc = column.getFieldConstraint().get(0);
        LiteralRestriction lr = fc.getLiteralRestriction().get(0);

        builder.append(" &nbsp;&nbsp;&nbsp;");
        // set domain-object display-uri to column
        // builder.append(column.getDisplayUri());

        // set grammer prefix to column
        builder.append(fc.getGrammerPrefix());

        // set filed display-uri to fc
        // builder.append(fc.getDisplayUri());
        // builder.append(" ");

        // set operator display-uri to lr
        builder.append(lr.getDisplayUri());
        builder.append(" ");

        String readableValues = lr.getReadableValue();
        if ("".equals(readableValues)) readableValues = lr.getValue().get(0);

        if (readableValues != null) {

            String[] values = readableValues.split(",");

            // List values = lr.getValue();
            for (int i = 0; i < values.length; i++) {

                builder.append("'" + values[i] + "' ");
                if (i != values.length - 1 && values.length > 1) {
                    builder.append(" or ");
                }
            }
        }

        builder.append(fc.getGrammerPostfix());

        return builder.toString();
    }

    public static String[] charSeparatedStringToStringArray(String aString, String chr) {
        String[] splittArray = null;
        if (aString != null && !aString.equalsIgnoreCase("")) {
            splittArray = aString.split(chr);

        }
        return splittArray;
    }

    public static List charSeparatedStringToStringList(String aString, String chr) {
        List<String> splittList = new ArrayList<String>();
        String[] splittArray = null;
        if (aString != null && !aString.equalsIgnoreCase("")) {
            splittArray = aString.split(chr);

        }
        for (int i = 0; i < splittArray.length; i++) {
            splittList.add(splittArray[i]);
        }

        return splittList;
    }

    public static void main(String[] args) {
        String message = "10 day report||5 Day Report";
        String[] messages = RuleUtil.charSeparatedStringToStringArray(message, "\\|\\|");

        for (int i = 0; i < messages.length; i++) {
            System.out.println(messages[i]);
        }

    }

}
