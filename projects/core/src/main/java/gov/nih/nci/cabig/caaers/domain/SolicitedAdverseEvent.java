package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * @author ArunKumarK
 */
@Entity
@Table(name = "solicited_events")//solicited_events
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_solicited_events_id") })
public class SolicitedAdverseEvent  extends AbstractMutableDomainObject {
	private CtcTerm ctcterm;
	private LowLevelTerm medraterm;
	private LowLevelTerm otherTerm;

	@OneToOne
	@JoinColumn(name="ctc_term_id")
	public CtcTerm getCtcterm() {
		return ctcterm;
	}
	public void setCtcterm(CtcTerm ctcterm) {
		this.ctcterm = ctcterm;
	}
	@OneToOne
	@JoinColumn(name="lowlevel_term_id")
	public LowLevelTerm getLowLevelTerm() {
		return medraterm;
	}
	public void setLowLevelTerm(LowLevelTerm medraterm) {
		this.medraterm = medraterm;
	}
	
	@OneToOne
	@JoinColumn(name="other_term_id")
	public LowLevelTerm getOtherTerm(){
		return otherTerm;
	}
	
	public void setOtherTerm(LowLevelTerm otherTerm){
		this.otherTerm = otherTerm;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ctcterm == null) ? 0 : ctcterm.getId());
		result = prime * result
				+ ((medraterm == null) ? 0 : medraterm.getId());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {

	       if (obj != null && this.getClass().equals(obj.getClass())) {
	           SolicitedAdverseEvent object = (SolicitedAdverseEvent)obj;

	           if ((this.getCtcterm() != null && object.getCtcterm() == null) || (this.getCtcterm() == null && object.getCtcterm() != null)) return false;
	           if ((this.getLowLevelTerm() != null && object.getLowLevelTerm() == null) || (this.getLowLevelTerm() == null && object.getLowLevelTerm() != null)) return false;

	           if (this.getCtcterm() == null && object.getCtcterm() == null) {
	               if (this.getLowLevelTerm() == null && object.getLowLevelTerm() == null) return true;
	               else return this.getLowLevelTerm().equals(object.getLowLevelTerm());
	           } else {
	               if (!this.getCtcterm().equals(object.getCtcterm())) return false;
	               if ((this.getOtherTerm() == null && object.getOtherTerm() != null) || ((this.getOtherTerm() != null && object.getOtherTerm() == null))) return false;
	                              if (this.getOtherTerm() == null && object.getOtherTerm() == null) return true;
	               else return this.getOtherTerm().equals(object.getOtherTerm());
	           }
	           
	       }
	       return false; 
	}
	
	@Transient
    public boolean isOtherRequired() {
		if(getCtcterm() == null) return false;
		return getCtcterm().isOtherRequired();
    }
}
