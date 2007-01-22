package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 * @author Priyatam
 * @author Krikor Krumlian
 */

@Entity 
@Table (name = "IDENTIFIERS")
@GenericGenerator (name="id-generator", strategy = "native",
		parameters = {
			@Parameter(name="sequence", value="seq_identifiers_id")
		}
)
public class Identifier extends AbstractDomainObject
{			
	private String source;
	private String type;
	private String value;
	private Boolean primaryIndicator = false;
				
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getPrimaryIndicator() {
		return primaryIndicator;
	}

	public void setPrimaryIndicator(Boolean primaryIndicator) {
		this.primaryIndicator = primaryIndicator;
	}	
}