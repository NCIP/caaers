package gov.nih.nci.cabig.caaers.domain;

/**
 * @author Rhett Sutphin
 */
/* TODO: this class is shared with PSC.  Refactor into a shared library. */
public interface DomainObject {
    /**
     * @return the internal database identifier for this object
     */
    Integer getId();

    /**
     * Set the internal database identifier for this object.  In practice this should not be
     * called by application code -- just the persistence mechanism.
     * @param id
     */
    void setId(Integer id);
}
