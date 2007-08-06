package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
@Entity
@Table (name = "investigational_new_drugs")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_investigational_new_dr_id")
    }
)
public class InvestigationalNewDrug extends AbstractMutableDomainObject {
	private Integer indNumber;
	private INDHolder iNDHolder;
	private List<StudyAgentINDAssociation> studyAgentINDAssociations;

	public Integer getIndNumber() {
		return indNumber;
	}

	public void setIndNumber(Integer number) {
		indNumber = number;
	}


	@OneToOne(mappedBy="investigationalNewDrug", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	public INDHolder getINDHolder() {
		return iNDHolder;
	}


	public void setINDHolder(INDHolder holder) {
		iNDHolder = holder;
	}

	@OneToMany(mappedBy="investigationalNewDrug", fetch=FetchType.EAGER)
	public List<StudyAgentINDAssociation> getStudyAgentINDAssociations() {
		return studyAgentINDAssociations;
	}

	public void setStudyAgentINDAssociations(
			List<StudyAgentINDAssociation> studyAgentINDAssociations) {
		this.studyAgentINDAssociations = studyAgentINDAssociations;
	}


	@Transient
	public String getHolderName(){
		return (iNDHolder != null)? iNDHolder.getName(): "";
	}
	@Transient
	public String getStrINDNo(){
		return String.valueOf(indNumber);
	}
	@Override
	public String toString() {
		return "InvestigationalNewDrug[" + indNumber +" : " + getHolderName() +"]";
	}
}
