package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "other_causes")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_other_causes_id")
    }
)
public class OtherCause extends AbstractExpeditedReportCollectionElementChild {
    private String text;

    @Column(name = "cause_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
