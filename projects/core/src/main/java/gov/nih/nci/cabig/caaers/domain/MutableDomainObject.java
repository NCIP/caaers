package gov.nih.nci.cabig.caaers.domain;

/**
 * @author Rhett Sutphin
 * @author Sujith Vellat Thayyilthodi
 */
public interface MutableDomainObject extends DomainObject {
	/**
	 * @return the optimistic locking version value for this object.
	 */
	Integer getVersion();

	/**
	 * Set the optimistic locking version value for this object.  In practice this should not be
	 * called by application code -- just the persistence mechanism.
	 * @param version
	 */
	void setVersion(Integer version);

	/**
	 * @return the grid-scoped unique identifier for this object
	 */
	String getGridId();

	/**
	 * Specify the grid-scoped unique identifier for this object
	 * @param gridId
	 */
	void setGridId(String bigId);

}
