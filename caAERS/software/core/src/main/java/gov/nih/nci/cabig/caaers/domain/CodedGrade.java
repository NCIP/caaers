/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

/**
 * An adapter interface so that {@link Grade} and {@link CtcGrade} can be used interchangeably.
 * 
 * @author Rhett Sutphin
 */
public interface CodedGrade {
    Integer getCode();

    String getDisplayName();
    
   String getName(); 
}
