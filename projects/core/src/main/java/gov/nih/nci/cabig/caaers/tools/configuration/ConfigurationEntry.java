package gov.nih.nci.cabig.caaers.tools.configuration;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name="configuration")
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public class ConfigurationEntry {
    private String key;
    private String value;
    private Integer version;

    @Id // assigned identifier
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
