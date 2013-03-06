/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.datamigrator;

import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author: Biju Joseph
 */
public interface CaaersDataMigrator {

    enum MigraorType {

        //NOTE : Make sure we give a unique value for code.
        USER(1),MEDICAL_DEVICE(2), RADIATION(3), SURGERY(4), USER_PRIVILEGE(5), INVALID_USER(6);
        private Integer code;
        
        private MigraorType(Integer code){
            this.code = code;
        }
        public int getCode(){
            return code;
        }
        
    }
    
    /**
     * The logic of migrating database should go here. 
     */
    void migrateData();

    /**
     * A unique ID for the migator, which will be used while loging.
     * @return
     */
    MigraorType migratorType();


}
