/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
