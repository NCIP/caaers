/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sameer Sawant
 */
@Transactional(readOnly = true)
public class AdverseEventReportingPeriodRepository {
	
	AdverseEventReportingPeriodDao reportingPeriodDao;
	 /**
     * Saves a AdverseEventReportingPeriod object
     *
     * @param reportingPeriod the AdverseEventReportingPeriod object
     * @throws Exception runtime exception object
     */

    @Transactional(readOnly = false)
    public void save(AdverseEventReportingPeriod reportingPeriod) throws Exception {
    	reportingPeriodDao.save(reportingPeriod);
    }
}
