/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

 
/**
 * This enumeration represents the loading status. It is mainly used by classes that are loaded into
 * caaers system, via grid service.
 * 
 * @author Biju Joseph
 * 
 */
public enum LoadStatus {

    /** The INPROGRESS. */
    INPROGRESS(0), /** The COMPLETE. */
 COMPLETE(1);
    
    /** The code. */
    int code;

    /**
     * Instantiates a new load status.
     *
     * @param code the code
     */
    private LoadStatus(int code) {
        this.code = code;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

}
