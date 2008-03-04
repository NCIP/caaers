package gov.nih.nci.cabig.caaers.domain;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public interface IdentifiableByAssignedIdentifers {
    List<Identifier> getIdentifiers();

    void setIdentifiers(List<Identifier> identifiers);

    Identifier getPrimaryIdentifier();

    List<Identifier> getSecondaryIdentifiers();
}
