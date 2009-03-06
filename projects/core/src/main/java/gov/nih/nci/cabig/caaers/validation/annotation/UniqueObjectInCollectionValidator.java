package gov.nih.nci.cabig.caaers.validation.annotation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA. User: admin Date: Dec 17, 2007 Time: 5:29:22 PM To change this template
 * use File | Settings | File Templates.
 */
public class UniqueObjectInCollectionValidator implements Validator<UniqueObjectInCollection> {

    String message;

    private static Log logger = LogFactory.getLog(UniqueObjectInCollectionValidator.class);

    public boolean validate(final Object value) {
        logger.info("in the validate method of" + this.getClass().getName());
        if (value instanceof Collection) {
            Set objects = new HashSet((Collection) value);
            return objects.size() == ((Collection) value).size();
        }
        return true;
    }

    public void initialize(final UniqueObjectInCollection parameters) {
        this.message = parameters.message();
    }

    public String message() {
        return this.message;
    }
}