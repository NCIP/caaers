/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;


 
/**
 * The Class AdverseEventReportingPeriodAjaxableDomainObject.
 */
public class AdverseEventReportingPeriodAjaxableDomainObject extends AbstractAjaxableDomainObject {

	/** The name. */
	private String name;
	
	/** The start date. */
	private String startDate;
	
	/** The end date. */
	private String endDate;
	
	/** The epoch name. */
	private String epochName;
	
	/** The tac code. */
	private String tacCode;
	
	/** The tac description. */
	private String tacDescription;
	
	/** The cycle number. */
	private String cycleNumber;
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate){
		if(startDate != null){
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		    this.startDate = formatter.format(startDate);
		}else
			this.startDate = "";
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate(){
		return startDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate){
		if(endDate != null){
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		    this.endDate = formatter.format(endDate);
		}else
			this.endDate = "";
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate(){
		return endDate;
	}
	
	/**
	 * Sets the epoch name.
	 *
	 * @param epochName the new epoch name
	 */
	public void setEpochName(String epochName){
		this.epochName = epochName;
	}
	
	/**
	 * Gets the epoch name.
	 *
	 * @return the epoch name
	 */
	public String getEpochName(){
		return epochName;
	}
	
	/**
	 * Sets the tac code.
	 *
	 * @param tacCode the new tac code
	 */
	public void setTacCode(String tacCode){
		this.tacCode = tacCode;
	}
	
	/**
	 * Gets the tac code.
	 *
	 * @return the tac code
	 */
	public String getTacCode(){
		return tacCode;
	}
	
	/**
	 * Sets the tac description.
	 *
	 * @param tacDescription the new tac description
	 */
	public void setTacDescription(String tacDescription){
		this.tacDescription = tacDescription;
	}
	
	/**
	 * Gets the tac description.
	 *
	 * @return the tac description
	 */
	public String getTacDescription(){
		return tacDescription;
	}
	
	/**
	 * Sets the cycle number.
	 *
	 * @param cycleNumber the new cycle number
	 */
	public void setCycleNumber(String cycleNumber){
		this.cycleNumber = cycleNumber;
	}
	
	/**
	 * Gets the cycle number.
	 *
	 * @return the cycle number
	 */
	public String getCycleNumber(){
		return cycleNumber;
	}
}
