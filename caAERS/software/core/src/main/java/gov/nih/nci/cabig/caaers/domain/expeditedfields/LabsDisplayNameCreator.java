/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * @author Rhett Sutphin
 */
public class LabsDisplayNameCreator implements DisplayNameCreator {
    public String createGenericName() {
        return "Labs";
    }

    public String createIndexedName(int i) {
        return "Lab " + ((char) (i + 'A'));
    }
}
