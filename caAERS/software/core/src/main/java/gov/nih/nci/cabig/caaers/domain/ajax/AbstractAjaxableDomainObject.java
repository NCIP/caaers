package gov.nih.nci.cabig.caaers.domain.ajax;


 
/**
 * This class should be used whenever objects are displayed via ajax. This will help in performance.
 *
 * @author Biju Joseph
 */
public class AbstractAjaxableDomainObject implements AjaxableDomainObject {
    
    /** The id. */
    private Integer id;
    
    /** The external id. */
    private String externalId;
    
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.AjaxableDomainObject#getId()
     */
    public Integer getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.AjaxableDomainObject#setId(java.lang.Integer)
     */
    public void setId(Integer id) {
        this.id = id;
    }

	/**
	 * Gets the external id.
	 *
	 * @return the external id
	 */
	public String getExternalId() {
		return externalId;
	}

	/**
	 * Sets the external id.
	 *
	 * @param externalId the new external id
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
}
