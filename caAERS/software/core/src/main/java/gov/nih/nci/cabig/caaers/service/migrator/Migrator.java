package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

public interface Migrator <C extends AbstractMutableDomainObject> {
	void migrate(C src, C dest, DomainObjectImportOutcome<C> outcome);
}

