/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.annotation;

import gov.nih.nci.cabig.caaers.domain.Retireable;
import gov.nih.nci.cabig.caaers.validation.AbstractConstraintValidator;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA. User: admin Date: Dec 17, 2007 Time: 5:29:22 PM To change this template
 * use File | Settings | File Templates.
 */
public class UniqueObjectInCollectionValidator extends AbstractConstraintValidator<UniqueObjectInCollection, Object> implements Validator<UniqueObjectInCollection> {


	String message;

    private static Log logger = LogFactory.getLog(UniqueObjectInCollectionValidator.class);

    /**
     * Will return true if there is no duplicate
     * Will return false if there is a duplicate
     */
    //BJ : Changed the orginal HashSet based implementation as some of the objects' hashCode and equals are not in sync.
    public boolean validate(final Object value) {
        logger.info("in the validate method of" + this.getClass().getName());
        if (value instanceof Collection) {
        	Collection c = (Collection) value;
        	ArrayList<Object> list = new ArrayList<Object>(c.size());
        	for(Object o : c){

                if( (o instanceof Retireable) && ((Retireable) o).isRetired()) continue;

        		if(list.contains(o)) return false;
        		list.add(o);
        	}
        }
        return true;
    }

    public void initialize(final UniqueObjectInCollection parameters) {
    	super.initialize(parameters);
        this.message = parameters.message();
    }

    public String message() {
        return this.message;
    }
    
}
