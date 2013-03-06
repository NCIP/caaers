/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

public class ReviewAeReportAjaxFacade extends CreateAdverseEventAjaxFacade{
	private static Class<?>[] CONTROLLERS = {ReviewAeReportController.class};
	
	@Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
}
