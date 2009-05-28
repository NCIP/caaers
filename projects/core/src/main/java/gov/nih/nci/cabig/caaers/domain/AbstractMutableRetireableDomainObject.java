package gov.nih.nci.cabig.caaers.domain;

import java.util.Collection;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang.BooleanUtils;

/**
 * The domain objects that are retire-able (soft delete-able) should subclass this class. 
 * @author Biju Joseph
 *
 */
@MappedSuperclass
public class AbstractMutableRetireableDomainObject extends AbstractMutableDomainObject implements Retireable {
	
    protected Boolean retiredIndicator = false;//if true, means soft deleted. 
    
    /* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Retireable#setRetiredIndicator(java.lang.Boolean)
	 */
    public void setRetiredIndicator(Boolean retiredIndicator) {
		this.retiredIndicator = retiredIndicator;
	}
    
    /* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Retireable#getRetiredIndicator()
	 */
    public Boolean getRetiredIndicator() {
		return retiredIndicator;
	}
    
    /* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Retireable#retire()
	 */
    public void retire(){
    	this.retiredIndicator = true;
    }
    
    /* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Retireable#isRetired()
	 */
    @Transient
    public boolean isRetired(){
    	return BooleanUtils.isTrue(retiredIndicator);
    }
    
    /**
     * This utility method could be used to mark the retired flag of a collection of {@link Retireable} objects
     * @param retireables
     */
    public static void retire(Retireable...retireables){
    	for(Retireable retireable : retireables) retireable.retire();
    }
    
    /**
     * This utility method could be used to mark the retired flag of a collection of {@link Retireable} objects
     * @param retireables
     */
    public static void retire(Collection<? extends Retireable> retireables){
    	 if(retireables == null || retireables.isEmpty()) return;
    	 for(Retireable retireable : retireables) retireable.retire();
    }
   
}
