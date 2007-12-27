package gov.nih.nci.cabig.caaers.rules.objectgraph;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.NullValueInNestedPathException;

public class NullSafeFieldExtractor {
	
	public static Object extractField(Object source, String expr){
		if(source == null) return "null";
		try {
			BeanWrapper wrapper = new BeanWrapperImpl(source);
			return wrapper.getPropertyValue(expr);
		} catch (NullValueInNestedPathException e) {
		} catch (NullPointerException e){
		}
		return "null";
	}
	
	public static String extractStringField(Object source, String expr){
		return (String) extractField(source, expr);
	}
}
