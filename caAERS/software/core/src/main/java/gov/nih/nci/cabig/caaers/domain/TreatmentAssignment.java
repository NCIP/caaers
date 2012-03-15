package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;


/**
 * This class represents the TreatmentAssignment domain object associated with the Adverse event
 * report. Domain object representing Study Therapy
 *
 * @author Saurabh Agrawal
 */
@Entity
@Table(name = "treatment_assignment")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_treatment_assignment_id") })
public class TreatmentAssignment extends AbstractMutableRetireableDomainObject implements StudyChild, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3837235599935227241L;

	/** The study. */
	private Study study;

    /** The code. */
    private String code;

    /** The dose level order. */
    private Integer doseLevelOrder;

    /** The description. */
    private String description;

    /** The comments. */
    private String comments;

    protected List<TreatmentAssignmentStudyIntervention> treatmentAssignmentStudyInterventions = new ArrayList<TreatmentAssignmentStudyIntervention>();
    protected List<AbstractStudyInterventionExpectedAE> abstractStudyInterventionExpectedAEs = new ArrayList<AbstractStudyInterventionExpectedAE>();
    
    private List<Integer> selectedStudyAgentInterventionIds;
    private List<Integer> selectedStudyDeviceInterventionIds;
    private List<Integer> selectedOtherInteterventionIds;
    
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "treatment_assignment_id", nullable = false)
    @OrderBy
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<AbstractStudyInterventionExpectedAE> getAbstractStudyInterventionExpectedAEs() {
        return abstractStudyInterventionExpectedAEs;
    }

    public void setAbstractStudyInterventionExpectedAEs(List<AbstractStudyInterventionExpectedAE> abstractStudyInterventionExpectedAEs) {
        this.abstractStudyInterventionExpectedAEs = abstractStudyInterventionExpectedAEs;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyChild#getStudy()
     */
    @ManyToOne
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyChild#setStudy(gov.nih.nci.cabig.caaers.domain.Study)
     */
    public void setStudy(final Study study) {
        this.study = study;
    }

    /**
     * Instantiates a new treatment assignment.
     */
    public TreatmentAssignment() {
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the dose level order.
     *
     * @return the dose level order
     */
    public Integer getDoseLevelOrder() {
        return doseLevelOrder;
    }

    /**
     * Sets the dose level order.
     *
     * @param doseLevelOrder the new dose level order
     */
    public void setDoseLevelOrder(Integer doseLevelOrder) {
        this.doseLevelOrder = doseLevelOrder;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
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
     * Gets the escaped description.
     *
     * @return the escaped description
     */
    @Transient
    /**
     * The below function is only used for UI purpose
     */
    public String getEscapedDescription() {
        return StringEscapeUtils.escapeJavaScript(description);
    }

    /**
     * Gets the html escaped description.
     *
     * @return the html escaped description
     */
    @Transient
    public String getHtmlEscapedDescription() {
        String descriptionHtml = StringUtils.replace(description, "\r\n", "<br>" );
        return StringEscapeUtils.escapeJavaScript(descriptionHtml);
    }

    /**
     * Gets the comments.
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments.
     *
     * @param comments the new comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "treatmentAssignment", fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<TreatmentAssignmentStudyIntervention> getTreatmentAssignmentStudyInterventions() {
        return treatmentAssignmentStudyInterventions;
    }

    public void setTreatmentAssignmentStudyInterventions(List<TreatmentAssignmentStudyIntervention> treatmentAssignmentStudyInterventions) {
        this.treatmentAssignmentStudyInterventions = treatmentAssignmentStudyInterventions;
    }

    public void removeInterventionFromTreatmentAssignment(StudyIntervention studyIntervention){
        TreatmentAssignmentStudyIntervention taStudyIntervention = hasIntervention(studyIntervention);
        if(taStudyIntervention != null){
            if(taStudyIntervention instanceof TreatmentAssignmentAgent) removeExpectedAEs((TreatmentAssignmentAgent) taStudyIntervention);
            getTreatmentAssignmentStudyInterventions().remove(taStudyIntervention);
        }
    }
    public void addInterventionToTreatmentAssignment(StudyIntervention ti) {
        TreatmentAssignmentStudyIntervention tasi = null;
        switch (ti.getStudyTherapyType()) {
            case DRUG_ADMINISTRATION:{
                TreatmentAssignmentAgent taa = new TreatmentAssignmentAgent();
                taa.setStudyAgent((StudyAgent)ti);
                addExpectedAEs(taa);
                tasi = taa;
            }; break;
            case DEVICE:{
                TreatmentAssignmentDevice tad = new TreatmentAssignmentDevice();
                tad.setStudyDevice((StudyDevice)ti);
                tasi = tad;
            }; break;
            default:{
                TreatmentAssignmentOtherIntervention tao = new TreatmentAssignmentOtherIntervention();
                tao.setOtherIntervention((OtherIntervention)ti);
                tasi = tao;
                break;
            }
        }
        if (tasi == null) return;
        tasi.setTreatmentAssignment(this);
        getTreatmentAssignmentStudyInterventions().add(tasi);
    }

    public void addTreatmentAssignmentStudyIntervention(TreatmentAssignmentStudyIntervention tasi) {
        tasi.setTreatmentAssignment(this);
        getTreatmentAssignmentStudyInterventions().add(tasi);
    }

    /**
     * This method returns the association TreatmentAssignmentStudyInterventionobject
     * if this TreatmentAssignment object is associated with the StudyIntervention through
     * a TreatmentAssignmentStudyIntervention object.
     * @param studyIntervention - StudyIntervention
     * @return boolean
     */
    @Transient
    public TreatmentAssignmentStudyIntervention hasIntervention(StudyIntervention studyIntervention) {
        for (TreatmentAssignmentStudyIntervention taStudyIntervention : getTreatmentAssignmentStudyInterventions()) {
            if (taStudyIntervention.getStudyIntervention().equals(studyIntervention)) return taStudyIntervention;
        }
        return null;
    }
    
    @Transient
    public boolean isHavingInterventions(){
    	return !getTreatmentAssignmentStudyInterventions().isEmpty();
    }

    @Transient
    public List<Integer> getSelectedStudyAgentInterventionIds() {
        if(selectedStudyAgentInterventionIds == null) selectedStudyAgentInterventionIds = new ArrayList<Integer>();
        return selectedStudyAgentInterventionIds;
    }

    public void setSelectedStudyAgentInterventionIds(List<Integer> selectedStudyAgentInterventionIds) {
        this.selectedStudyAgentInterventionIds = selectedStudyAgentInterventionIds;
    }
    @Transient
    public List<Integer> getSelectedStudyDeviceInterventionIds() {
        if(selectedStudyDeviceInterventionIds == null) selectedStudyDeviceInterventionIds = new ArrayList<Integer>();
        return selectedStudyDeviceInterventionIds;
    }

    public void setSelectedStudyDeviceInterventionIds(List<Integer> selectedStudyDeviceInterventionIds) {
        this.selectedStudyDeviceInterventionIds = selectedStudyDeviceInterventionIds;
    }
    @Transient
    public List<Integer> getSelectedOtherInteterventionIds() {
        if(selectedOtherInteterventionIds == null) selectedOtherInteterventionIds = new ArrayList<Integer>();
        return selectedOtherInteterventionIds;
    }

    public void setSelectedOtherInteterventionIds(List<Integer> selectedOtherInteterventionIds) {
        this.selectedOtherInteterventionIds = selectedOtherInteterventionIds;
    }
    
    public void regenerateAllInterventionIdList(){
        getSelectedOtherInteterventionIds().clear();
        getSelectedStudyDeviceInterventionIds().clear();
        getSelectedStudyAgentInterventionIds().clear();
        
        for(TreatmentAssignmentStudyIntervention taStudyIntervention : getTreatmentAssignmentStudyInterventions()){
            StudyIntervention studyIntervention = taStudyIntervention.getStudyIntervention();
            if(studyIntervention == null) continue;
            Integer id = studyIntervention.getId();
            if(id == null) continue; //will never happen, but some crappy tests do not populate ids correctly
            if(studyIntervention instanceof StudyAgent) {
                getSelectedStudyAgentInterventionIds().add(id);
            } else if (studyIntervention instanceof StudyDevice){
                getSelectedStudyDeviceInterventionIds().add(id);
            } else if( studyIntervention instanceof OtherIntervention){
                getSelectedOtherInteterventionIds().add(id);
            }
        }
    }

    /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (code == null ? 0 : code.hashCode());
        result = prime * result + (comments == null ? 0 : comments.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (study.getId() == null ? 0 : study.getId().hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if(!(obj instanceof TreatmentAssignment)) return false;

        final TreatmentAssignment other = (TreatmentAssignment) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        if (comments == null) {
            if (other.comments != null) {
                return false;
            }
        } else if (!comments.equals(other.comments)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }

        if (study.getId() == null) {
            if (other.study.getId() != null) {
                return false;
            }
        } else if (!study.getId().equals(other.study.getId())) {
            return false;
        }
        return true;
    }
    
    public void addExpectedAEs(TreatmentAssignmentAgent treatmentAssignmentAgent){
    	if (treatmentAssignmentAgent.getStudyAgent().getAgent() == null) return;
    	//Import Expected AEs
    	for (AgentSpecificTerm agentSpecificTerm: treatmentAssignmentAgent.getStudyAgent().getAgent().getAgentSpecificTerms()){
    		addExpectedAEs(treatmentAssignmentAgent, agentSpecificTerm);
    	}
    }
    
    public void addExpectedAEs(TreatmentAssignmentAgent treatmentAssignmentAgent, AgentSpecificTerm agentSpecificTerm){
    	// shouldHonor = true if study has a CTEP Ind and given studyAgent is CTEP Ind
    	// shouldHonor = true if study has no CTEP Ind and given studyAgent is not CTEP Ind
    	// shouldHonor = false if study has a CTEP Ind and given studyAgent is not CTEP Ind
    	// All other combinations are invalid
    	boolean shouldHonor = treatmentAssignmentAgent.getStudyAgent().shouldHonor();
		AbstractStudyInterventionExpectedAE ta_ae = getExistingTerm(agentSpecificTerm.getTerm());
		if( ta_ae != null){
			//Duplicate AE.
			//Check if the ae is already added for this treatment assignment
			if(ta_ae.getTreatmentAssignmentAgents().contains(treatmentAssignmentAgent)){
				//TODO: For now just return. Maybe we update the expectedness values and copy them from agentspecificterm to abstractstudyinterventionAE
				return;
			}
			//Add the new source of AE and recalculate expectedness.
			ta_ae.getTreatmentAssignmentAgents().add(treatmentAssignmentAgent);
			ta_ae.recalculateExpectedness(agentSpecificTerm, shouldHonor && agentSpecificTerm.isExpected());
		}else{
			AbstractStudyInterventionExpectedAE asiea = null;
			if (agentSpecificTerm instanceof AgentSpecificCtcTerm) {
				asiea = new StudyInterventionExpectedCtcTerm(treatmentAssignmentAgent, agentSpecificTerm, shouldHonor && agentSpecificTerm.isExpected());
			}else {
				asiea = new StudyInterventionExpectedMeddraLowLevelTerm(treatmentAssignmentAgent, agentSpecificTerm, shouldHonor && agentSpecificTerm.isExpected());
			}
			this.getAbstractStudyInterventionExpectedAEs().add(asiea);
		}
    }
    
    public void removeExpectedAEs(TreatmentAssignmentAgent treatmentAssignmentAgent){
    	if (treatmentAssignmentAgent.getStudyAgent().getAgent() == null) return;
    	for (AgentSpecificTerm agentSpecificTerm: treatmentAssignmentAgent.getStudyAgent().getAgent().getAgentSpecificTerms()){
    		removeExpectedAE(treatmentAssignmentAgent, agentSpecificTerm.getTerm());
    	}
    }
    
    public void removeExpectedAE(TreatmentAssignmentAgent treatmentAssignmentAgent, DomainObject term){
		AbstractStudyInterventionExpectedAE ta_ae = getExistingTerm(term);
		if( ta_ae != null){
			if(ta_ae.getTreatmentAssignmentAgents().size() == 1){
				//remove the AbstractStudyInterventionExpectedAE completely
				getAbstractStudyInterventionExpectedAEs().remove(ta_ae);
			}
			ta_ae.removeTreatmentAssignmentAgent(treatmentAssignmentAgent);
		}
    }
    
    private AbstractStudyInterventionExpectedAE getExistingTerm(DomainObject term){
    	for(AbstractStudyInterventionExpectedAE asiea : getAbstractStudyInterventionExpectedAEs()){
    		if (asiea.getTerm().getId().equals(term.getId())){
    			return asiea;
    		}
    	}
    	return null;
    }
    

}
