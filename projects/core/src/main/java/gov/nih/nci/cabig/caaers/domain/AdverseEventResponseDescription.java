package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name="ae_report_descriptions")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_ae_report_descriptions_id")
    }
)
public class AdverseEventResponseDescription extends AbstractExpeditedReportSingleChild {
    private String eventDescription;
    private PostAdverseEventStatus presentStatus;
    private Date recoveryDate;
    private Boolean retreated;
    private Date dateRemovedFromProtocol;

    ////// LOGIC

    @Transient
    public boolean isRemovedFromProtocol() {
        return getDateRemovedFromProtocol() != null;
    }

    ////// BEAN PROPERTIES

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Column(name = "present_status_code")
    @Type(type = "postAdverseEventStatus")
    public PostAdverseEventStatus getPresentStatus() {
        return presentStatus;
    }

    public void setPresentStatus(PostAdverseEventStatus presentStatus) {
        this.presentStatus = presentStatus;
    }

    public Date getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
    }

    public Boolean getRetreated() {
        return retreated;
    }

    public void setRetreated(Boolean retreated) {
        this.retreated = retreated;
    }

    public Date getDateRemovedFromProtocol() {
        return dateRemovedFromProtocol;
    }

    public void setDateRemovedFromProtocol(Date dateRemovedFromProtocol) {
        this.dateRemovedFromProtocol = dateRemovedFromProtocol;
    }
}
