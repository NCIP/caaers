package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;


/**
 * The Class AbstractTerm.
 * @author Ion C. Olaru
 */

@MappedSuperclass
public abstract interface AbstractTerm extends MutableDomainObject {
    
    @Transient
    public abstract boolean isOtherRequired();
    
    @Transient
    public abstract boolean isMedDRA();

    @Transient
    public abstract String getFullName();

}