package gov.nih.nci.cabig.caaers.web.utils;


import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.drools.util.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class WebUtils {

    /**
     * Creates and options map using the same principles as spring's <code>form:options</code>
     * tag.
     * 
     * @param items
     *                A collection of items that should make up the options. The options will be in
     *                the same iteration order as this collection.
     * @param itemValueProperty
     *                The property of the collection's elements which should be used as as the
     *                submitted value for each item. If null, the result of
     *                <code>item.toString()</code> will be used instead.
     * @param itemLabelProperty
     *                The property of the collection's elements which should be used as as the
     *                displayed label for each item. If null, the result of
     *                <code>item.toString()</code> will be used instead.
     * @return an options map suitable for use as the {@link InputField#OPTIONS} attribute
     */
    public static Map<Object, Object> collectOptions(Collection<?> items, String itemValueProperty,
                    String itemLabelProperty, String blankValue) {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        if (blankValue != null) options.put("", blankValue);
        for (Object item : items) {
            BeanWrapper wrappedItem = new BeanWrapperImpl(item);
            Object value = extractProperty(wrappedItem, itemValueProperty);
            Object label = extractProperty(wrappedItem, itemLabelProperty);
            options.put(value, label);
        }
        return options;
    }
    
    /**
     * Creates and options map using the same principles as spring's <code>form:options</code>
     * tag.
     * 
     * @param items
     *                A collection of items that should make up the options. The options will be in
     *                the same iteration order as this collection.
     * @param itemValueProperty
     *                The property of the collection's elements which should be used as as the
     *                submitted value for each item. If null, the result of
     *                <code>item.toString()</code> will be used instead.
     * @param itemLabel1Property
     *                The property of the collection's elements which should be used as as the
     *                displayed label for each item before the separator. If null, the result of
     *                <code>item.toString()</code> will be used instead.
     * @param itemLabel2Property
     *                The property of the collection's elements which should be used as as the
     *                displayed label for each item after the separator. If null, the result of
     *                <code>item.toString()</code> will be used instead.
     * @param separator
     * 				  The string that separates label1 and label2 property.                                             
     * @param items
     * @param itemValueProperty
     * @param itemLabel1Property
     * @param itemLabel2Property
     * @param separator
     * @return
     */
    public static Map<Object, Object> collectCustomOptions(Collection<?> items, String itemValueProperty,
    			String itemLabel1Property, String itemLabel2Property, String separator) {
    	Map<Object, Object> options = new LinkedHashMap<Object, Object>();
    	for (Object item : items) {
    			BeanWrapper wrappedItem = new BeanWrapperImpl(item);
    			Object value = extractProperty(wrappedItem, itemValueProperty);
    			Object label1 = extractProperty(wrappedItem, itemLabel1Property);
    			Object label2 = extractProperty(wrappedItem, itemLabel2Property);
    			if(separator == null)
    				separator = "-";
    			options.put(value, label1.toString() + separator + label2.toString());
    	}
    	return options;
    }
    
    
    
    public static Map<Object, Object> collectOptions(Collection<?> items, String itemValueProperty,
                    String itemLabelProperty) {
        return collectOptions(items, itemValueProperty, itemLabelProperty, null);
    }

    private static Object extractProperty(BeanWrapper wrappedItem, String propertyName) {
        if (wrappedItem.getWrappedInstance() == null) {
            return null;
        } else if (propertyName == null) {
            return wrappedItem.getWrappedInstance().toString();
        } else {
            return wrappedItem.getPropertyValue(propertyName);
        }
    }
    
    /**
     * Returns the previous page, based on the request parameter _page
     * @param request
     * @return
     */
    public static int getPreviousPage(HttpServletRequest request){
    	String pg = request.getParameter("_page");
    	if(StringUtils.isEmpty(pg)) return -1;
    	return Integer.parseInt(pg);
    }
}
