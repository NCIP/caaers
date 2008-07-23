package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the LabLoad domain object associated with the Adverse event report.
 * 
 * @author Srini Akkala
 */
@Entity
@Table(name = "labs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_labs_id") })
public class LabLoad extends AbstractMutableDomainObject {
 
	private String name;

    private String units; 

    private String result;

    private boolean  dismissed;

    private Date labDate;
    
    private StudyParticipantAssignment assignment;

    // //// BEAN PROPERTIES

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Date getLabDate() {
        return labDate;
    }

    public void setLabDate(Date labDate) {
        this.labDate = labDate;
    }

	public boolean isDismissed() {
		return dismissed;
	}

	public void setDismissed(boolean dismissed) {
		this.dismissed = dismissed;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getName() {
		return name;
	}
    
    @Transient
    public Participant getParticipant() {
        return getAssignment() == null ? null : getAssignment().getParticipant();
    }

    @Transient
    public Study getStudy() {
        StudySite ss = getAssignment() == null ? null : getAssignment().getStudySite();
        return ss == null ? null : ss.getStudy();
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }
    
    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }
    
	@Override
    public int hashCode() {
        return ("" + getName() ).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null) return false;

        if (!(obj instanceof Lab)) return false;

        LabLoad l = (LabLoad) obj;
        if (!StringUtils.equals(name, l.name)) return false;

        if (l.units != null && !units.equals(l.units)) return false;
        if (l.result != null && !result.equals(l.result)) return false;

        return true;
    }
}
