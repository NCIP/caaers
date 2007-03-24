package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Subclass of @{link AbstractDomainObject> that implements
 * {@link gov.nih.nci.cabig.caaers.domain.IdentifiableByAssignedIdentifers}.
 *
 * @author Rhett Sutphin
 */
@MappedSuperclass
public abstract class AbstractIdentifiableDomainObject extends AbstractDomainObject implements IdentifiableByAssignedIdentifers {
    private static final Log log = LogFactory.getLog(AbstractIdentifiableDomainObject.class);
    private List<Identifier> identifiers;

    ////// LOGIC

    @Transient
    public Identifier getPrimaryIdentifier() {
        for (Identifier identifier : getIdentifiers()) {
            if (identifier.getPrimaryIndicator()) return identifier;
        }
        // TODO: this should probably be fatal (i.e., it should be validated at entry time)
        log.warn(getClass().getName() + '#' + getId() + " does not have a primary identifier");
        return null;
    }

    @Transient
    public List<Identifier> getSecondaryIdentifiers() {
        Identifier primary = getPrimaryIdentifier();
        List<Identifier> others = new ArrayList<Identifier>(getIdentifiers());
        others.remove(primary);
        return others;
    }

    public void addIdentifier(Identifier newIdentifier) {
        getIdentifiers().add(newIdentifier);
    }

    ////// BEAN PROPERTIES

    /**
     * Subclasses will need to override this method to attach an appropriate JoinColumn annotation.
     */
    @Transient
    public List<Identifier> getIdentifiers() {
        if (identifiers == null) identifiers = new ArrayList<Identifier>();
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }
}
