package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.*;

 
/**
 * The Class TransitionConfig.
 */
@Entity
@Table(name = "wf_transition_configs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_wf_transition_configs_id")})

public class TransitionConfig extends AbstractMutableDomainObject {
	
	/** The transition name. */
	private String transitionName;
	
	/** The owners. */
	private List<TransitionOwner> owners;
	
	/**
	 * Gets the transition name.
	 *
	 * @return the transition name
	 */
	public String getTransitionName() {
		return transitionName;
	}
	
	/**
	 * Sets the transition name.
	 *
	 * @param transitionName the new transition name
	 */
	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}
	
	/**
	 * Gets the owners.
	 *
	 * @return the owners
	 */
	@OneToMany
    @JoinColumn(name = "transition_config_id", nullable = false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<TransitionOwner> getOwners() {
		return owners;
	}
	
	/**
	 * Sets the owners.
	 *
	 * @param owners the new owners
	 */
	public void setOwners(List<TransitionOwner> owners) {
		this.owners = owners;
	}
	
	/**
	 * Adds the transition owner.
	 *
	 * @param owner the owner
	 */
	public void addTransitionOwner(TransitionOwner owner){
		if(owners == null) owners = new ArrayList<TransitionOwner>();
		owners.add(owner);
	}
	
}
