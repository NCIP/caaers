package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Date;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_labs")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_ae_labs_id")
    }
)
public class Lab extends AbstractExpeditedReportCollectionElementChild {
    private LabTerm labTerm;
    //private String name;
    private String other;
    private String units;  // TODO: source this from caDSR

    private LabValue baseline;
    private LabValue nadir;
    private LabValue recovery;
    
    private String site;
    private Date labDate;
    private String infectiousAgent;
    

    ////// BEAN PROPERTIES
    
    @OneToOne
	@Cascade( { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "lab_term_id")
	public LabTerm getLabTerm() {
		return labTerm;
	}

	public void setLabTerm(LabTerm labTerm) {
		this.labTerm = labTerm;
	}
    /*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "baseline_value")),
        @AttributeOverride(name = "date", column = @Column(name = "baseline_date"))
    })
    public LabValue getBaseline() {
        if (baseline == null) baseline = new LabValue();
        return baseline;
    }

    public void setBaseline(LabValue baseline) {
        this.baseline = baseline;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "nadir_value")),
        @AttributeOverride(name = "date", column = @Column(name = "nadir_date"))
    })
    public LabValue getNadir() {
        if (nadir == null) nadir = new LabValue();
        return nadir;
    }

    public void setNadir(LabValue nadir) {
        this.nadir = nadir;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "recovery_value")),
        @AttributeOverride(name = "date", column = @Column(name = "recovery_date"))
    })
    public LabValue getRecovery() {
        if (recovery == null) recovery = new LabValue();
        return recovery;
    }

    public void setRecovery(LabValue recovery) {
        this.recovery = recovery;
    }

    public String getOther() {
		return other;
	}
    public void setOther(String other) {
		this.other = other;
	}

	public String getInfectiousAgent() {
		return infectiousAgent;
	}

	public void setInfectiousAgent(String infectiousAgent) {
		this.infectiousAgent = infectiousAgent;
	}

	public Date getLabDate() {
		return labDate;
	}

	public void setLabDate(Date labDate) {
		this.labDate = labDate;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
    
    @Override
    public int hashCode() {
    	return ("" + name + other).hashCode() ;
    }
    
    
    @Override
    public boolean equals(Object obj) {
    	if(obj == this) return true;
    	
    	if(obj == null) return false;
    	
    	if(!(obj instanceof Lab)) return false;
    	
    	Lab l = (Lab)obj;
    	if(!StringUtils.equals(name, l.name)) return false;
    	if(!StringUtils.equals(other, l.other)) return false;
   
    	if(l.units != null && !units.equals(l.units)) return false;
    	if(l.baseline != null && !baseline.equals(l.baseline)) return false;
    	
    	if(l.recovery != null && !recovery.equals(l.recovery)) return false;
    	if(l.nadir != null && !nadir.equals(l.nadir)) return false;
    	
    	return true;
    }
}
