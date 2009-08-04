package gov.nih.nci.cabig.caaers.domain;

/**
 * Any domain object that can be soft deleted should implement this interface. 
 * @author Biju Joseph
 *
 */
public interface Retireable {
	
	/**
	 * Will set the retired indicator
	 */
	public abstract void retire();
	public abstract Integer getId();
	public abstract boolean isRetired();
	public abstract Boolean getRetiredIndicator();
	public abstract void setRetiredIndicator(Boolean retiredIndicator);

}