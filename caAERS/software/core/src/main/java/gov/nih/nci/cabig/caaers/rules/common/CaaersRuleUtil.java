/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.common;

import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.FieldConstraint;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.utils.RuleUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

public class CaaersRuleUtil {
//
    public static final String CAN_NOT_DETERMINED = "CAN_NOT_DETERMINED";
    public static final String SERIOUS_ADVERSE_EVENT = "SERIOUS_ADVERSE_EVENT";


    public static String getStringWithoutSpaces(String str) {
        return RuleUtil.getStringWithoutSpaces(str);
    }

    public static List charSeparatedStringToStringList(String aString, String chr) {
        return RuleUtil.charSeparatedStringToStringList(aString, chr);
    }
    
    public static String getRandomBindURI(){
        return "rs_" + System.currentTimeMillis();
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

    public static String fetchFieldValue(Rule rule, String fieldName) {
        for (Column col : rule.getCondition().getColumn()) {
            if(col.getFieldConstraint() == null || col.getFieldConstraint().isEmpty()) continue;
            if(col.getFieldConstraint().get(0).getFieldName() == null) continue;
            FieldConstraint fc = col.getFieldConstraint().get(0);
            if (fc.getFieldName().equals(fieldName)) {
                return fc.getLiteralRestriction().get(0).getValue().get(0);
            }
        }
        return null;
    }



    public static void updateFieldValue(Rule rule, String fieldName, String newVal) {
        for (Column col : rule.getCondition().getColumn()) {
            if(col.getFieldConstraint() == null || col.getFieldConstraint().isEmpty()) continue;
            if(col.getFieldConstraint().get(0).getFieldName() == null) continue;
            FieldConstraint fc = col.getFieldConstraint().get(0);
            if (fc.getFieldName().equals(fieldName)) {
                fc.getLiteralRestriction().get(0).setValue(Arrays.asList(newVal));
                String[] expressionParts = StringUtils.split(col.getExpression(), ',');
                expressionParts[3] = String.format("'%s'", newVal );
                col.setExpression(StringUtils.join(expressionParts, ","));
            }
        }

    }

    public static void updateCategoryField(Rule rule, String newVal) {
        for (Column col : rule.getCondition().getColumn()) {
            if(col.getFieldConstraint() == null || col.getFieldConstraint().isEmpty()) continue;
            if(col.getFieldConstraint().get(0).getFieldName() == null) continue;
            FieldConstraint fc = col.getFieldConstraint().get(0);
            if (fc.getFieldName().equals("category")) {
                fc.setGrammerPrefix("Category");
                fc.getLiteralRestriction().get(0).setValue(Arrays.asList(newVal));

                String newOp =  fc.getLiteralRestriction().get(0).getEvaluator();
                String expression = col.getExpression();
                String[] expressionParts = StringUtils.split(expression, ',');
                expressionParts[1] = "'gov.nih.nci.cabig.caaers.domain.CtcCategory'";
                expressionParts[2] = "'id'";
                expressionParts[3] = String.format("'%s'", newVal );
                expressionParts[4] = String.format("'%s')", newOp );
                col.setExpression(StringUtils.join(expressionParts, ","));
            }
        }
    }
}
