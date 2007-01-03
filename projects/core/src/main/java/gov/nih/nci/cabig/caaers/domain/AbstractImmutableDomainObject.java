package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Id;

/**
 * @author Rhett Sutphin
 */
@MappedSuperclass
public class AbstractImmutableDomainObject implements DomainObject {
    private Integer id;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
