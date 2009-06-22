package gov.nih.nci.cabig.caaers.web.ae;

public class ReviewAeReportAjaxFacade extends CreateAdverseEventAjaxFacade{
	private static Class<?>[] CONTROLLERS = {ReviewAeReportController.class};
	
	@Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
}