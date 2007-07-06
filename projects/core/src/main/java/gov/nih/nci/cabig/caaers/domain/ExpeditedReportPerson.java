package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.MapKey;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinTable;
import javax.persistence.Column;
import javax.persistence.AttributeOverride;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_report_people")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "role",
    discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("ABSTRACT_BASE") // should be ignored
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_ae_report_people_id")
    }
)
public abstract class ExpeditedReportPerson extends Person implements ExpeditedAdverseEventReportChild {
    private Map<String, String> contactMechanisms = new HashMap<String, String>();

    private ExpeditedAdverseEventReport report;

    // TODO: it may be more appropriate to locate these constants somewhere else

    /** {@link #getContactMechanisms} key for the e-mail address */
    public static final String EMAIL = "e-mail";
    /** {@link #getContactMechanisms} key for the fax number */
    public static final String FAX = "fax";
    /** {@link #getContactMechanisms} key for the phone number */
    public static final String PHONE = "phone";

    public static final List<String> DEFAULT_CONTACT_MECHANISM_KEYS
        = Arrays.asList(EMAIL, PHONE, FAX); 

    ////// LOGIC

    @Transient
    public boolean isTransient() { // TODO: this should go in one of the base classes
        return getId() == null;
    }

    @Transient
    public boolean isSavable() {
        return getFirstName() != null && getLastName() != null
            && getContactMechanisms().get(EMAIL) != null;
    }

    ////// BOUND PROPERTIES

    @OneToOne
    @JoinColumn(name="report_id")
    public ExpeditedAdverseEventReport getReport() {
        return report;
    }

    public void setReport(ExpeditedAdverseEventReport report) {
        this.report = report;
    }

    @CollectionOfElements
    @JoinTable(
        name="contact_mechanisms",
        joinColumns = @JoinColumn(name="person_id")
    )
    @MapKey(columns=@Column(name="type"))
    @Column(name="value")
    public Map<String, String> getContactMechanisms() {
        return contactMechanisms;
    }

    public void setContactMechanisms(Map<String, String> contactMechanisms) {
        this.contactMechanisms = contactMechanisms;
    }
}
