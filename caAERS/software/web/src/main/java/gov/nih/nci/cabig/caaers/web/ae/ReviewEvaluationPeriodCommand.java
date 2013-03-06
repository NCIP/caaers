/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;

/**
 *
 * @author Sameer Sawant
 */
public class ReviewEvaluationPeriodCommand extends CaptureAdverseEventInputCommand{
	
	public ReviewEvaluationPeriodCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao){
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
	
	@Override
	public void reassociate(){
		if(this.adverseEventReportingPeriod != null && this.adverseEventReportingPeriod.getId() != null){
			adverseEventReportingPeriodDao.reassociate(this.adverseEventReportingPeriod);
		}
	}
	
}
