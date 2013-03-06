/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

 
/**
 * Any domain object that can be soft deleted should implement this interface. 
 * @author Biju Joseph
 *
 */
public interface Retireable {
	
	/**
	 * Will set the retired indicator.
	 */
	public abstract void retire();
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public abstract Integer getId();
	
	/**
	 * Checks if is retired.
	 *
	 * @return true, if is retired
	 */
	public abstract boolean isRetired();
	
	/**
	 * Gets the retired indicator.
	 *
	 * @return the retired indicator
	 */
	public abstract Boolean getRetiredIndicator();
	
	/**
	 * Sets the retired indicator.
	 *
	 * @param retiredIndicator the new retired indicator
	 */
	public abstract void setRetiredIndicator(Boolean retiredIndicator);

}
