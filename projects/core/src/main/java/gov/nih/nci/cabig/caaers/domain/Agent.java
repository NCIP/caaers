package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Krikor Krumlian
 * 
 */
@Entity
@Table (name = "agents")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_agents_id")
    }
)
public class Agent extends AbstractDomainObject{
	
	private String name;
	private String description;
	private String nameDescription;
	private List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany (mappedBy="agent", fetch=FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
	public List<StudyAgent> getStudyAgents() {
		return studyAgents;
	}

	public void setStudyAgents(List<StudyAgent> studyAgents) {
		this.studyAgents = studyAgents;
	}

	@Transient
	public String getNameDescription() {
		return nameDescription;
	}
	
	public void setNameDescription(String nameDescription) {
		this.nameDescription = nameDescription;
	}
	
	
	 
	 

}
