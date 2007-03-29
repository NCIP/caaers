package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Kulasekaran
 */
@Entity
@Table (name="study_personnels")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_study_personnels_id")
    }
)
public class StudyPersonnel extends AbstractDomainObject {
		
	private ResearchStaff researchStaff;
	private StudySite studySite;
			
	@ManyToOne
    @JoinColumn(name = "research_staffs_id")
	public ResearchStaff getResearchStaff() {
		return researchStaff;
	}
	
	public void setResearchStaff(ResearchStaff researchStaff) {
		this.researchStaff = researchStaff;
	}
		
	@ManyToOne
    @JoinColumn(name = "study_sites_id")    	
	public StudySite getStudySite() {
		return studySite;
	}

	public void setStudySite(StudySite studySite) {
		this.studySite = studySite;
	}
}
