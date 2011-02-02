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
 * The Class SolicitedAdverseEvent.
 *
 * @author ArunKumarK
 */
@Entity
@Table(name = "solicited_events")//solicited_events
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_solicited_events_id") })
public class SolicitedAdverseEvent  extends AbstractMutableDomainObject {

    /** The verbatim. */
    private String verbatim;
    
    /** The ctcterm. */
    private CtcTerm ctcterm;
	
	/** The medraterm. */
	private LowLevelTerm medraterm;
	
	/** The other term. */
	private LowLevelTerm otherTerm;

	/**
	 * Gets the ctcterm.
	 *
	 * @return the ctcterm
	 */
	@OneToOne
	@JoinColumn(name="ctc_term_id")
	public CtcTerm getCtcterm() {
		return ctcterm;
	}

    /**
     * Sets the ctcterm.
     *
     * @param ctcterm the new ctcterm
     */
    public void setCtcterm(CtcTerm ctcterm) {
		this.ctcterm = ctcterm;
	}

	/**
	 * Gets the low level term.
	 *
	 * @return the low level term
	 */
	@OneToOne
	@JoinColumn(name="lowlevel_term_id")
	public LowLevelTerm getLowLevelTerm() {
		return medraterm;
	}
	
	/**
	 * Sets the low level term.
	 *
	 * @param medraterm the new low level term
	 */
	public void setLowLevelTerm(LowLevelTerm medraterm) {
		this.medraterm = medraterm;
	}
	
	/**
	 * Gets the other term.
	 *
	 * @return the other term
	 */
	@OneToOne
	@JoinColumn(name="other_term_id")
	public LowLevelTerm getOtherTerm() {
		return otherTerm;
	}
	
	/**
	 * Sets the other term.
	 *
	 * @param otherTerm the new other term
	 */
	public void setOtherTerm(LowLevelTerm otherTerm) {
		this.otherTerm = otherTerm;
	}

    /**
     * Gets the verbatim.
     *
     * @return the verbatim
     */
    public String getVerbatim() {
        return verbatim;
    }

    /**
     * Sets the verbatim.
     *
     * @param verbatim the new verbatim
     */
    public void setVerbatim(String verbatim) {
        this.verbatim = verbatim;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ctcterm == null) ? 0 : ctcterm.getId());
		result = prime * result + ((otherTerm == null) ? 0 : otherTerm.getId());
		result = prime * result
				+ ((medraterm == null) ? 0 : medraterm.getId());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	/**
	 * Checks if is other required.
	 *
	 * @return true, if is other required
	 */
	@Transient
    public boolean isOtherRequired() {
		if(getCtcterm() == null) return false;
		return getCtcterm().isOtherRequired();
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(ctcterm != null ? ctcterm.getFullName() : medraterm.getFullName()).append("]");
		return sb.toString();
	}
}
