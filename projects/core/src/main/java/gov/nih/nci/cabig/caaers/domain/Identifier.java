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
	private String source;

	private String type;

	private String value;

	private Boolean primaryIndicator = false;

	public static Identifier createTemplate(final String source, final String type, final String value) {
		Identifier id = new Identifier();
		id.setSource(source);
		id.setType(type);
		id.setValue(value);
		return id;
	}

	public static Identifier createTemplate(final String value) {
		return createTemplate(null, null, value);
	}

	/**
	 * Null-safe conversion from primaryIndicator property to simple boolean. TODO: switch the db field to not-null, default false so this
	 * isn't necessary.
	 */
	@Transient
	public boolean isPrimary() {
		return getPrimaryIndicator() == null ? false : getPrimaryIndicator();
	}

	// //// BEAN PROPERTIES

	/**
	 * Gets the source. Deprecated and will be removed in next release.
	 * 
	 * @return the source
	 */
	@Deprecated
	@Transient
	public String getSource() {
		return source;
	}

	/**
	 * Sets the source. Deprecated and will be removed in next release.
	 * 
	 * @param source the new source
	 */
	@Deprecated
	@Transient
	public void setSource(final String source) {
		this.source = source;
	}

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
		return new StringBuilder(getClass().getSimpleName()).append("[value=").append(getValue()).append("; primary? ")
				.append(getPrimaryIndicator()).append("; type=").append(getType()).append("; source=").append(
						getSource()).append(']').toString();
	}

	@Override
	public String toString() {
		return getValue();
	}
}