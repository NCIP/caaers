package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

/**
 * Subclass of {@link AbstractMutableDomainObject> that implements {@link gov.nih.nci.cabig.caaers.domain.IdentifiableByAssignedIdentifers}.
 * 
 * @author Rhett Sutphin
 */
@MappedSuperclass
public abstract class AbstractIdentifiableDomainObject extends AbstractMutableDomainObject implements
		IdentifiableByAssignedIdentifers {
	private static final Log log = LogFactory.getLog(AbstractIdentifiableDomainObject.class);

	private List<Identifier> identifiers = new ArrayList<Identifier>();

	// //// LOGIC

	@Transient
	public Identifier getPrimaryIdentifier() {
		if (log.isDebugEnabled()) {
			log.debug("Searching for primary identifier in " + getIdentifiers());
		}
		for (Identifier identifier : getIdentifiers()) {
			if (identifier.isPrimary()) {
				return identifier;
			}
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

	public void addIdentifier(final Identifier newIdentifier) {
		getIdentifiers().add(newIdentifier);
	}

	// //// BEAN PROPERTIES

	/**
	 * Subclasses will need to override this method to attach an appropriate JoinColumn annotation.
	 */
	@Transient
	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(final List<Identifier> identifiers) {
		this.identifiers = identifiers;
	}
}
