package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Rhett Sutphin
 */
@MappedSuperclass
public class ExpeditedReportPerson extends Person implements ExpeditedAdverseEventReportChild {
    private List<ContactMechanism> contactMechanisms = new ArrayList<ContactMechanism>();

    private ExpeditedAdverseEventReport report;

    private static String EMAIL = "email";
    private static String FAX = "fax";
    private static String PHONE = "phone";

    public static <T extends ExpeditedReportPerson> T createEmptyPerson(Class<T> klass) {
        try {
            T instance = klass.newInstance();

            List<ContactMechanism> contacts = new ArrayList<ContactMechanism>(3);
            // XXX: this ordering makes little sense, but I'm using it now to limit
            // the scope of the current refactoring (moved from CreateAdverseEventCommand)
            // TODO: refactor to use a Map instead of a List since the order isn't important
            contacts.add(new ContactMechanism(EMAIL));
            contacts.add(new ContactMechanism(FAX));
            contacts.add(new ContactMechanism(PHONE));

            instance.setContactMechanisms(contacts);
            
            return instance;
        } catch (InstantiationException e) {
            throw new CaaersSystemException("Could not instantiate " + klass.getName(), e);
        } catch (IllegalAccessException e) {
            throw new CaaersSystemException("Could not instantiate " + klass.getName(), e);
        }
    }

    @OneToOne
    @JoinColumn(name="report_id")
    public ExpeditedAdverseEventReport getReport() {
        return report;
    }

    public void setReport(ExpeditedAdverseEventReport report) {
        this.report = report;
    }

    // this is only transient here.  Subclasses will need to override it and map it properly.
    @Transient
    public List<ContactMechanism> getContactMechanisms() {
        return contactMechanisms;
    }

    public void setContactMechanisms(List<ContactMechanism> contactMechanisms) {
        this.contactMechanisms = contactMechanisms;
    }
}
