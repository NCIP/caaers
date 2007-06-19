package gov.nih.nci.cabig.caaers.domain;

import java.util.List;

import javax.persistence.Entity;
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
    @Parameter(name="sequence", value="seq_reporters_id")
} )
public class Reporter extends ExpeditedReportPerson {
    @Override
    @OneToMany
    @JoinColumn(name="reporter_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<ContactMechanism> getContactMechanisms() {
        return super.getContactMechanisms();
    }
}
