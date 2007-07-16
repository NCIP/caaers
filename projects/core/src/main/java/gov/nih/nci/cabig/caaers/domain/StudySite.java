package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Krikor Krumlian
 * @author Kulasekaran
 */
@Entity
@DiscriminatorValue(value = "SST")
public class StudySite extends StudyOrganization {    
	private Date irbApprovalDate;
    private String roleCode;
    private String statusCode;
    private Date startDate;
    private Date endDate;
    private List<StudyParticipantAssignment> studyParticipantAssignments = new ArrayList<StudyParticipantAssignment>();
    private LazyListHelper lazyListHelper;
    
    //TODO : remove the below commented properties
    //private List<StudyInvestigator> studyInvestigators = new ArrayList<StudyInvestigator>();
    //private List<StudyPersonnel> studyPersonnels = new ArrayList<StudyPersonnel>();
    
    
    //////LOGIC
    
    public StudySite(){
    	lazyListHelper = new LazyListHelper();
    	lazyListHelper.add(StudyInvestigator.class, new StudySiteChildInstantiateFactory<StudyInvestigator>( this, StudyInvestigator.class));
    	lazyListHelper.add(StudyPersonnel.class, new StudySiteChildInstantiateFactory<StudyPersonnel>(this, StudyPersonnel.class) );
    	
    	//set the studyInvestigators and studyPersonals
    	setStudyPersonnelsInternal(new ArrayList<StudyPersonnel>());
    	setStudyInvestigatorsInternal(new ArrayList<StudyInvestigator>());
    	
    }
    public void addStudyPersonnel(StudyPersonnel studyPersonnel) {
        getStudyPersonnels().add(studyPersonnel);
        studyPersonnel.setStudySite(this);
    }
    
    public void addStudyInvestigators(StudyInvestigator studyInvestigator) {
        getStudyInvestigators().add(studyInvestigator);
        studyInvestigator.setStudySite(this);
    }
    
    public void addAssignment(StudyParticipantAssignment assignment) {
        getStudyParticipantAssignments().add(assignment);
        assignment.setStudySite(this);
    }
    
	/** Are there any assignments using this relationship? */
    @Transient
    public boolean isUsed() {
        return getStudyParticipantAssignments().size() > 0;
    }

    @Transient
    public String getSiteStudyNames() {
        return getStudy().getShortTitle() + " : " + getOrganization().getName();
    }
    
    /// BEAN PROPERTIES
    
    public void setStudyParticipantAssignments(List<StudyParticipantAssignment> studyParticipantAssignments) {
        this.studyParticipantAssignments = studyParticipantAssignments;
    }

    @OneToMany (mappedBy = "studySite")
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyParticipantAssignment> getStudyParticipantAssignments() {
        return studyParticipantAssignments;
    }    
    
    @OneToMany (mappedBy = "studySite")
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyInvestigator> getStudyInvestigatorsInternal() {
		return lazyListHelper.getInternalList(StudyInvestigator.class);
	}

	public void setStudyInvestigatorsInternal(List<StudyInvestigator> studyInvestigators) {
		lazyListHelper.setInternalList(StudyInvestigator.class, studyInvestigators);
	}	
	
    public void setIrbApprovalDate(Date irbApprovalDate) {
        this.irbApprovalDate = irbApprovalDate;
    }

    public Date getIrbApprovalDate() {
        return irbApprovalDate;
    }

    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany (mappedBy = "studySite", fetch=FetchType.LAZY)
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })    
	public List<StudyPersonnel> getStudyPersonnelsInternal() {
		return lazyListHelper.getInternalList(StudyPersonnel.class);
	}

	public void setStudyPersonnelsInternal(List<StudyPersonnel> studyPersonnels) {
		lazyListHelper.setInternalList(StudyPersonnel.class, studyPersonnels);
	}
	
	@Transient
	public List<StudyPersonnel> getStudyPersonnels() {
		return lazyListHelper.getLazyList(StudyPersonnel.class);
	}

	public void setStudyPersonnels(List<StudyPersonnel> studyPersonnels) {
		setStudyPersonnelsInternal(studyPersonnels);
	}
	@Transient
	public List<StudyInvestigator> getStudyInvestigators() {
		return lazyListHelper.getLazyList(StudyInvestigator.class);
	}

	public void setStudyInvestigators(List<StudyInvestigator> studyInvestigators) {
		setStudyInvestigatorsInternal(studyInvestigators);
	}
	
	//////OBJECT METHODS

   /* public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StudySite)) return false;
        final StudySite studySite = (StudySite) obj;
        Study study = studySite.getStudy();
        Site site = studySite.getSite();
        if (!getStudy().equals(study)) return false;
        if (!getSite().equals(site)) return false;
        return true;
    }

    public int hashCode() {
        int result;
        result = (site != null ? site.hashCode() : 0);
        //result = 29 * result + (study != null ? study.hashCode() : 0);
        result = 29 * result + (studyParticipantAssignments != null ? studyParticipantAssignments.hashCode() : 0);
        return result;
    } */
}
