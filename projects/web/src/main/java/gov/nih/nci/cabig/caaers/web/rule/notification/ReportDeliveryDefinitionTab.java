package gov.nih.nci.cabig.caaers.web.rule.notification;

public class ReportDeliveryDefinitionTab extends DefaultTab{

	public ReportDeliveryDefinitionTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}
	
	public ReportDeliveryDefinitionTab(){
		this("Specify Final Report Delivery Details", "Report Delivery Configuration", "rule/notification/reportDeliveryTab");
	}
	
}
