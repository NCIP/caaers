/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils.ranking;

/**
 * @author: Biju Joseph
 */
public interface Serializer<T> {
    /**
     * Will serailize to string the object passed-in. 
     * @param object
     * @return
     */
    public String serialize(T object);
}
