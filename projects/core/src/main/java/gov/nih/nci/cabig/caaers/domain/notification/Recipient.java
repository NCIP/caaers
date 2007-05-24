/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.domain.notification;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;


/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 11, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Entity
@Table(name = "recipients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "dtype",
    discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("dtype")
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_recipients_id")
    }
)
public abstract class Recipient extends AbstractMutableDomainObject {
	
}
