package gov.nih.nci.cabig.caaers.web.ae;

public class ReviewEvaluationPeriodAjaxFacade extends CaptureAdverseEventAjaxFacade{
	private static Class<?>[] CONTROLLERS = {ReviewEvaluationPeriodController.class};
	
	@Override
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
}