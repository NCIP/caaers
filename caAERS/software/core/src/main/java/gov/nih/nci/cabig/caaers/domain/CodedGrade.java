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
    
    /**
     * Gets the code.
     *
     * @return the code
     */
    Integer getCode();

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    String getDisplayName();
    
   /**
    * Gets the name.
    *
    * @return the name
    */
   String getName(); 
}
