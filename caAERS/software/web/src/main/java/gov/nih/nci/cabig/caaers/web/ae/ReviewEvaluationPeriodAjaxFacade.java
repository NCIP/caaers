/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

public class ReviewEvaluationPeriodAjaxFacade extends CaptureAdverseEventAjaxFacade{
	private static Class<?>[] CONTROLLERS = {ReviewEvaluationPeriodController.class};
	
	@Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
}
