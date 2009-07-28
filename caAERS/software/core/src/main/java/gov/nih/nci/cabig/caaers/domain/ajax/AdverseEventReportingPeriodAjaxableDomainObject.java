package gov.nih.nci.cabig.caaers.domain.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 */
public class AdverseEventReportingPeriodAjaxableDomainObject extends AbstractAjaxableDomainObject {

	private String name;
	private String startDate;
	private String endDate;
	private String epochName;
	private String tacCode;
	private String tacDescription;
	private String cycleNumber;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setStartDate(Date startDate){
		if(startDate != null){
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		    this.startDate = formatter.format(startDate);
		}else
			this.startDate = "";
	}
	
	public String getStartDate(){
		return startDate;
	}
	
	public void setEndDate(Date endDate){
		if(endDate != null){
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		    this.endDate = formatter.format(endDate);
		}else
			this.endDate = "";
	}
	
	public String getEndDate(){
		return endDate;
	}
	
	public void setEpochName(String epochName){
		this.epochName = epochName;
	}
	
	public String getEpochName(){
		return epochName;
	}
	
	public void setTacCode(String tacCode){
		this.tacCode = tacCode;
	}
	
	public String getTacCode(){
		return tacCode;
	}
	
	public void setTacDescription(String tacDescription){
		this.tacDescription = tacDescription;
	}
	
	public String getTacDescription(){
		return tacDescription;
	}
	
	public void setCycleNumber(String cycleNumber){
		this.cycleNumber = cycleNumber;
	}
	
	public String getCycleNumber(){
		return cycleNumber;
	}
}
