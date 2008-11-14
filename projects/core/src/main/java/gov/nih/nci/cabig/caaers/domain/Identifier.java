package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

/**
 * This class represents the Identifier domain object associated with the Adverse event report.
 * 
 * @author Priyatam
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "IDENTIFIERS")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_identifiers_id") })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator_column", discriminatorType = DiscriminatorType.INTEGER)
public class Identifier extends AbstractMutableDomainObject {

    private String type;

    private String value;

    private Boolean primaryIndicator = false;

    public static Identifier createTemplate(final String type, final String value) {
        Identifier id = new Identifier();
        id.setType(type);
        id.setValue(value);
        return id;
    }

    public static Identifier createTemplate(final String value) {
        return createTemplate(null, value);
    }

    /**
     * Null-safe conversion from primaryIndicator property to simple boolean. TODO: switch the db
     * field to not-null, default false so this isn't necessary.
     */
    @Transient
    public boolean isPrimary() {
        return getPrimaryIndicator() == null ? false : getPrimaryIndicator();
    }

    // //// BEAN PROPERTIES

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public Boolean getPrimaryIndicator() {
        return primaryIndicator;
    }

    public void setPrimaryIndicator(Boolean primaryIndicator) {
        if (primaryIndicator == null) {
            primaryIndicator = false;
        }
        this.primaryIndicator = primaryIndicator;
    }

    @Transient
    public String getSummary() {
        return new StringBuilder(getClass().getSimpleName()).append("[value=").append(getValue())
                        .append("; primary? ").append(getPrimaryIndicator()).append("; type=")
                        .append(getType()).append(']').toString();
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getId() == null ? 0 : getId().hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (value == null ? 0 : value.hashCode());
        result = prime * result + (primaryIndicator == null ? 0 : primaryIndicator.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Identifier)) {
            return false;
        }
        final Identifier other = (Identifier) obj;

        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        if (primaryIndicator == null) {
            if (other.primaryIndicator != null) {
                return false;
            }
        } else if (!primaryIndicator.equals(other.primaryIndicator)) {
            return false;
        }
        return true;
    }

}