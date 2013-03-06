/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 
 * @author Biju Joseph (refacrored)
 *
 */
public class DefaultObjectPropertyReader{
	BeanWrapper wrapper;
	String objectPath;
	
    public DefaultObjectPropertyReader(Object currentObject, String objectPath) {
        wrapper = new BeanWrapperImpl(currentObject);
        this.objectPath = objectPath;
    }


   

    public Object getPropertyValueFromPath() throws Exception {
    	return wrapper.getPropertyValue(objectPath);
    }
}
