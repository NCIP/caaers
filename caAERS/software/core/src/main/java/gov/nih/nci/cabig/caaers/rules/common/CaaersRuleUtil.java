/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.common;

import com.semanticbits.rules.utils.RuleUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

public class CaaersRuleUtil {
//
    public static final String CAN_NOT_DETERMINED = "CAN_NOT_DETERMINED";
    public static final String SERIOUS_ADVERSE_EVENT = "SERIOUS_ADVERSE_EVENT";
//
//	private static final Log log = LogFactory.getLog(CaaersRuleUtil.class);


    public static String getStringWithoutSpaces(String str) {
        return RuleUtil.getStringWithoutSpaces(str);
    }

    public static List charSeparatedStringToStringList(String aString, String chr) {
        return RuleUtil.charSeparatedStringToStringList(aString, chr);
    }
    
    public static String getRandomBindURI(){
        return "rs_" + System.currentTimeMillis();
    }


//
//    /**
//     * Will return the category identified the path if exists, otherwise creates. 
//     * @param authService
//     * @param path
//     * @return
//     * @throws Exception
//     */
//    public static Category  createCategory(RuleAuthoringService authService, String path) throws Exception{
//        if(StringUtils.isEmpty(path)) return null;
//
//        if(RuleUtil.categoryExist(authService, path)){
//            return authService.getCategory(path);
//        }
//
//        int i = path.lastIndexOf('/');
//        
//        String childPath = null;
//        String parentPath = null;
//
//        if(path.length() > i){
//            childPath = path.substring(i+1);
//        }
//
//        if(i >= 0){
//            parentPath = path.substring(0,i);
//
//        }
//
//        if(!StringUtils.equals(parentPath, "/")) createCategory(authService, parentPath);
//
//        CategoryConfiguration c = CategoryConfiguration.findByName(childPath);
//        if(c != null){
//            if(!RuleUtil.categoryExist(authService, c.getPath())){
//                RuleUtil.createCategory(authService, parentPath,c.getName(),c.getDescription());
//                log.debug(" -->" + parentPath + "/" + c.getName());
//            }
//
//        }else{
//            if(!RuleUtil.categoryExist(authService, childPath)){
//                RuleUtil.createCategory(authService, parentPath,childPath,childPath);
//                log.debug(" -->" + parentPath + "/" + childPath);
//            }
//        }
//
//        return authService.getCategory(path);
//
//    }
//
//
//
//    
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


    public static Map<String, Object> multiplexAndEvaluate(Object src, String path) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] pathParts = path.split("\\[\\]\\.");
        if (pathParts.length < 2) return map;

        BeanWrapper bw = new BeanWrapperImpl(src);
        Object coll = bw.getPropertyValue(pathParts[0]);
        if(coll instanceof Collection){
            int i =0;
            for(Object o : (Collection) coll){
               if(pathParts.length == 2){
                    String s = pathParts[0] + "[" + i + "]." + pathParts[1];
                    map.put(s, o);

               }else{
                    String s = pathParts[0] + "[" + i +"]";
                    String[] _newPathParts = new String[pathParts.length -1];
                    System.arraycopy(pathParts, 1, _newPathParts, 0, _newPathParts.length);
                    String _p = StringUtils.join(_newPathParts, "[].");
                    Map<String , Object> m =   multiplexAndEvaluate(o, _p );
                    //map.put(s, o);
                    for(String k : m.keySet()){
                        map.put(s + "." + k, m.get(k));
                    }
               }
               i++;
            }
        }


        return map;
    }
}
