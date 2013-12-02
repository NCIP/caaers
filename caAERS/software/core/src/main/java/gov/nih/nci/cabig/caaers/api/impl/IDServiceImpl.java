/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.dao.SafetyReportIDDao;
import gov.nih.nci.cabig.caaers.integration.schema.aereportid.ReportIdCriteria;
import gov.nih.nci.cabig.caaers.integration.schema.aereportid.SafetyReportIdentifer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ID service to generate identifiers based on criteria
 * 
 * @author vinodh
 * 
 */

public class IDServiceImpl implements ApplicationContextAware {

	private static Log logger = LogFactory.getLog(IDServiceImpl.class);
	
	private ApplicationContext applicationContext;
	
	private SafetyReportIDDao safetyReportIDDao;

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public SafetyReportIDDao getSafetyReportIDDao() {
		return safetyReportIDDao;
	}

	public void setSafetyReportIDDao(SafetyReportIDDao safetyReportIDDao) {
		this.safetyReportIDDao = safetyReportIDDao;
	}

	public SafetyReportIdentifer generateSafetyReportId(ReportIdCriteria reportIdCriteria) {
		final SafetyReportIdentifer sri = new SafetyReportIdentifer();
		sri.setSafetyReportId(safetyReportIDDao.getNewID());
		sri.setCaseNumber(reportIdCriteria.getCaseNumber());
		return sri;
	}

}
