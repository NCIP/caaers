package gov.nih.nci.cabig.caaers.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: Biju Joseph
 */
public class JSONUtils {

    private static char delimiter = '"';
    private static char colon = ':';

    public static Map<String, Object> describe(Object o){
        Map<String, Object> m = new LinkedHashMap<String, Object> ();
        Method[] methods = o.getClass().getMethods();
        for(Method method : methods){
            String name = method.getName();
            if(!name.startsWith("get")) continue;
            name = name.substring(3);
            String firstChar = "" + name.charAt(0);
            name = firstChar.toLowerCase() + name.substring(1);
            Object value = null;
            try{
                value = method.invoke(o);
            }catch (Exception ignore){
                
            }
            if(name.equals("class")) value = String.valueOf(value);
            m.put(name, value);
            
        }
        return m;
    }
    public static String toJSON(Object o) throws Exception {
        Map<String, Object> map = describe(o);
        StringBuilder sb = new StringBuilder("{");
        boolean useComma = false;
        for(Map.Entry e : map.entrySet() ){
            if(useComma) sb.append(",");
            useComma = true;
            String key = (String) e.getKey();
            sb.append(delimiter).append(key).append(delimiter).append(colon);
            
            Object value = e.getValue();
            if(value == null){ 
                sb.append("null");
                continue;
            }else if (e instanceof Collection){
                sb.append("[");
                for(Object c : (Collection)value){
                    sb.append(toJSON(c));
                }
                sb.append("]");
            }else if (value instanceof Number || value instanceof Boolean){
                sb.append(String.valueOf(value));
            }else if (value instanceof  String || value instanceof  Character){
                sb.append(delimiter).append(String.valueOf(value)).append(delimiter);
            }else {
                sb.append(toJSON(value));
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
