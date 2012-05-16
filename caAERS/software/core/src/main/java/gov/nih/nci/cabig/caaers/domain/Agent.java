package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the Agent domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 * 
 */
@Entity
@Table(name = "agents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_agents_id") })
public class Agent extends AbstractMutableRetireableDomainObject implements Serializable, Comparable<Agent>{

    /** The name. */
    private String name;

    /** The description. */
    private String description;

    /** The nsc number. */
    private String nscNumber;

    /** The display name. */
    private String displayName;
    
    /** The display name. */
    private List<AgentSpecificTerm> agentSpecificTerms = new ArrayList<AgentSpecificTerm>();
    
    private Date lastSynchedDate;

    public Date getLastSynchedDate() {
		return lastSynchedDate;
	}

	public void setLastSynchedDate(Date lastSynchedDate) {
		this.lastSynchedDate = lastSynchedDate;
	}

	/**
     * Gets the description.
     *
     * @return the description
     */
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

  
    /**
     * Gets the nsc number.
     *
     * @return the nsc number
     */
    @Column(name = "nsc")
    public String getNscNumber() {
        return nscNumber;
    }

    /**
     * Sets the nsc number.
     *
     * @param nsc the new nsc number
     */
    public void setNscNumber(String nsc) {
        this.nscNumber = nsc;
    }
    
    /**
     * Gets the display name.
     *
     * @return the display name
     */
    @Transient
    public String getDisplayName() {
        if (StringUtils.isNotEmpty(nscNumber)) {
            return nscNumber + "::" + name;
        }
        return name;
    }

    /**
     * Sets the display name.
     *
     * @param displayName the new display name
     */
    public void setDisplayName(String displayName){
    	this.displayName = displayName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Agent that = (Agent) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (nscNumber != null ? !nscNumber.equals(that.nscNumber) : that.nscNumber != null) return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result + (description != null ? description.hashCode() : 0);
        result = 29 * result + (nscNumber != null ? nscNumber.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
    @Cascade({ CascadeType.LOCK, CascadeType.SAVE_UPDATE })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    //@Transient
	public List<AgentSpecificTerm> getAgentSpecificTerms() {
		return agentSpecificTerms;
	}

	public void setAgentSpecificTerms(List<AgentSpecificTerm> agentSpecificTerms) {
		this.agentSpecificTerms = agentSpecificTerms;
	}
	
	@Transient
	public AgentSpecificTerm getAgentSpecificTerm(DomainObject term){
		for(AgentSpecificTerm agentSpecificTerm: agentSpecificTerms){
			if(agentSpecificTerm.getTerm().getId().equals(term.getId())){
				return agentSpecificTerm;
			}
		}
		return null;
	}

	public int compareTo(Agent o) {
		
		if(this.getName().compareTo(o.getName()) !=0){
			return this.getName().compareTo(o.getName());
		}
		
		if(this.getDescription().compareTo(o.getDescription()) !=0){
			return this.getDescription().compareTo(o.getDescription());
		}
		
		if(this.getNscNumber().compareTo(o.getNscNumber()) !=0){
			return this.getNscNumber().compareTo(o.getNscNumber());
		}
		
		if(this.getRetiredIndicator().compareTo(o.getRetiredIndicator()) != 0){
			return this.getRetiredIndicator().compareTo(o.getRetiredIndicator());
		}
		return 0;
	}
    
    public AgentSpecificTerm  findTerm(String termName, String termCategory, String terminologyVersion, String otherToxicity, String otherMeddra)  {
        for(AgentSpecificTerm t : getAgentSpecificTerms()){
            if(t.isOfSameTerm(termName, termCategory, terminologyVersion, otherToxicity, otherMeddra)) return t;
        }
        return null;
    }

    public boolean removeAgentSpecificTerm(AgentSpecificTerm term){
        if(term == null) return false;
        return getAgentSpecificTerms().remove(term);
    }

    public boolean addAgentSpecificTerm(AgentSpecificTerm term){
        if(term == null) return false;
        term.setAgent(this);
        return getAgentSpecificTerms().add(term);
    }
}
