package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

/**
 * @author Priyatam
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@Entity
@Table (name = "IDENTIFIERS")
@GenericGenerator (name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_identifiers_id")
    }
)
public class Identifier extends AbstractMutableDomainObject {
    private String source;
    private String type;
    private String value;
    private Boolean primaryIndicator = false;

    public static Identifier createTemplate(String source, String type, String value) {
        Identifier id = new Identifier();
        id.setSource(source);
        id.setType(type);
        id.setValue(value);
        return id;
    }

    public static Identifier createTemplate(String value) {
        return createTemplate(null, null, value);
    }

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