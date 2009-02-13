package gov.nih.nci.cabig.caaers.domain.ajax;


/**
 *
 */
public class AdverseEventReportingPeriodAjaxableDomainObject extends AbstractAjaxableDomainObject {

	private String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
