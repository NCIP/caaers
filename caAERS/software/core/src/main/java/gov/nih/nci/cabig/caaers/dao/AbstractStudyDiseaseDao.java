/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease;

public class AbstractStudyDiseaseDao extends CaaersDao<AbstractStudyDisease>{
    /**
     * The domain class this Dao represents in this case  AbstractStudyDisease
     * @return   A AbstractStudyDisease class. 
     */
	@Override
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
	public Class<AbstractStudyDisease> domainClass() {
		// TODO Auto-generated method stub
		return AbstractStudyDisease.class;
	}
	
	
	
}
