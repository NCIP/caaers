package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author Rhett Sutphin
 */
/* TODO: this class is shared with PSC.  Refactor into a shared library. */
@MappedSuperclass
public abstract class AbstractDomainObject implements DomainObject {
    private Integer id;
    private Integer version;

    @Id @GeneratedValue(generator = "id-generator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public static <T extends DomainObject> boolean equalById(T t1, T t2) {
        if (t1 == t2) return true;
        if (t1 == null) {
            // t2 must be non-null, so
            return false;
        } else if (t2 == null) {
            // ditto
            return false;
        } else {
            return t1.getId() == null
                ? t2.getId() == null
                : t1.getId().equals(t2.getId());
        }
    }
}