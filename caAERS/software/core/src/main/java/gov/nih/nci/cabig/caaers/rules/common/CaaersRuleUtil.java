package gov.nih.nci.cabig.caaers.rules.common;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
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
	
    public static final String CAN_NOT_DETERMINED = "CAN_NOT_DETERMINED";
    public static final String SERIOUS_ADVERSE_EVENT = "SERIOUS_ADVERSE_EVENT";
	 
	private static final Log log = LogFactory.getLog(CaaersRuleUtil.class);

    /**
     * Will return the category identified the path if exists, otherwise creates. 
     * @param authService
     * @param path
     * @return
     * @throws Exception
     */
    public static Category  createCategory(RuleAuthoringService authService, String path) throws Exception{
        if(StringUtils.isEmpty(path)) return null;

        if(RuleUtil.categoryExist(authService, path)){
            return authService.getCategory(path);
        }

        int i = path.lastIndexOf('/');
        
        String childPath = null;
        String parentPath = null;

        if(path.length() > i){
            childPath = path.substring(i+1);
        }

        if(i >= 0){
            parentPath = path.substring(0,i);

        }

        if(!StringUtils.equals(parentPath, "/")) createCategory(authService, parentPath);

        CategoryConfiguration c = CategoryConfiguration.findByName(childPath);
        if(c != null){
            if(!RuleUtil.categoryExist(authService, c.getPath())){
                RuleUtil.createCategory(authService, parentPath,c.getName(),c.getDescription());
                log.debug(" -->" + parentPath + "/" + c.getName());
            }

        }else{
            if(!RuleUtil.categoryExist(authService, childPath)){
                RuleUtil.createCategory(authService, parentPath,childPath,childPath);
                log.debug(" -->" + parentPath + "/" + childPath);
            }
        }

        return authService.getCategory(path);

    }



    
    /**
     * This method will parse the rules result and return the list of 
     * report definition names.
     * @param message
     * @return
     */
    public static List<String> parseRulesResult(String message){
    	List<String> reportDefinitionNames = new ArrayList<String>();
    	if (!message.equals(CAN_NOT_DETERMINED)) {

            if (message.indexOf("IGNORE") < 0) {
                // add the report definitions to the map
                String[] messages = RuleUtil.charSeparatedStringToStringArray(message, "\\|\\|");
                for (int i = 0; i < messages.length; i++) {
                    reportDefinitionNames.add(messages[i]);
                }
            }

        }
    	return reportDefinitionNames;
    }


}
