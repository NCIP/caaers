package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "wf_transition_configs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_wf_transition_configs_id")})

public class TransitionConfig extends AbstractMutableDomainObject {
	private String transitionName;
	private List<TransitionOwner> owners;
	
	public String getTransitionName() {
		return transitionName;
	}
	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}
	@OneToMany
    @JoinColumn(name = "transition_config_id", nullable = false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<TransitionOwner> getOwners() {
		return owners;
	}
	public void setOwners(List<TransitionOwner> owners) {
		this.owners = owners;
	}
	
	public void addTransitionOwner(TransitionOwner owner){
		if(owners == null) owners = new ArrayList<TransitionOwner>();
		owners.add(owner);
	}
	
}
