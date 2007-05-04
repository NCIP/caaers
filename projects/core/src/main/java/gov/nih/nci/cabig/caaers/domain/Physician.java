package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Kulasekaran
 */
@Entity
@Table
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_physician_id")
    }
)
public class Physician extends AdverseEventReportPerson {

    private List<ContactMechanism> contactMechanims = new ArrayList<ContactMechanism>();

    @OneToMany
    @JoinColumn(name="physician_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<ContactMechanism> getContactMechanims() {
        return contactMechanims;
    }

    public void setContactMechanims(List<ContactMechanism> contactMechanims) {
        this.contactMechanims = contactMechanims;
    }
}
