/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils;

/**
 * @author Rhett Sutphin
 */
public interface GridIdentifierCreator {

    /**
     * @param identification
     *                is a string that is used by the caAERS application to store the uniquely
     *                identifyable information for that resource.
     */
    String getGridIdentifier(String identification);
}
