/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
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

    /** The type. */
    private String type;
    
    /** The value. */
    private String value;
    
    /** The primary indicator. */
    private Boolean primaryIndicator = false;

    /**
     * Creates the template.
     *
     * @param type the type
     * @param value the value
     * @return the identifier
     */
    public static Identifier createTemplate(final String type, final String value) {
        Identifier id = new Identifier();
        id.setType(type);
        id.setValue(value);
        return id;
    }

    /**
     * Creates the template.
     *
     * @param value the value
     * @return the identifier
     */
    public static Identifier createTemplate(final String value) {
        return createTemplate(null, value);
    }

    /**
     * Null-safe conversion from primaryIndicator property to simple boolean. TODO: switch the db
     * field to not-null, default false so this isn't necessary.
     *
     * @return true, if is primary
     */
    @Transient
    public boolean isPrimary() {
        return getPrimaryIndicator() == null ? false : getPrimaryIndicator();
    }

    // //// BEAN PROPERTIES

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Gets the primary indicator.
     *
     * @return the primary indicator
     */
    public Boolean getPrimaryIndicator() {
        return primaryIndicator;
    }

    /**
     * Sets the primary indicator.
     *
     * @param primaryIndicator the new primary indicator
     */
    public void setPrimaryIndicator(Boolean primaryIndicator) {
        if (primaryIndicator == null) {
            primaryIndicator = false;
        }
        this.primaryIndicator = primaryIndicator;
    }

    /**
     * Gets the summary.
     *
     * @return the summary
     */
    @Transient
    public String getSummary() {
        return new StringBuilder(getClass().getSimpleName()).append("[value=").append(getValue())
                        .append("; primary? ").append(getPrimaryIndicator()).append("; type=")
                        .append(getType()).append(']').toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getValue();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (value == null ? 0 : value.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        return true;
    }

}
