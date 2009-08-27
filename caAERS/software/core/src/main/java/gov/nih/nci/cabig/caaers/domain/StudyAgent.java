package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the StudyAgent domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */

@Entity
@Table(name = "study_agents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_agents_id") })
public class StudyAgent extends AbstractMutableRetireableDomainObject implements StudyChild {

    private LazyListHelper lazyListHelper;
    private Study study;
    private Agent agent;
    private String agentAsString;
    private String otherAgent;

    @Embedded
    private Participation participation;
    private INDType indType;
    private Boolean partOfLeadIND;

    /*
     * Constructor -- Initializes participation at create time
     */
    public StudyAgent() {
        participation = new Participation();
        lazyListHelper = new LazyListHelper();
        lazyListHelper.add(StudyAgentINDAssociation.class, new StudyAgentChildInstantiateFactory<StudyAgentINDAssociation>(this,StudyAgentINDAssociation.class));
    }
    
    public StudyAgent(Agent agent){
        this();
        this.agent = agent;
        
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    @ManyToOne
    @JoinColumn(name = "agent_id")
    // We should never create new agents here, so no cascades
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @OneToMany(mappedBy = "studyAgent", fetch = FetchType.LAZY)
    @Cascade( { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyAgentINDAssociation> getStudyAgentINDAssociationsInternal() {
        return lazyListHelper.getInternalList(StudyAgentINDAssociation.class);
    }

    public void setStudyAgentINDAssociationsInternal(
                    List<StudyAgentINDAssociation> studyAgentINDAssociations) {
        lazyListHelper.setInternalList(StudyAgentINDAssociation.class, studyAgentINDAssociations);
    }

    @Transient
    public List<StudyAgentINDAssociation> getStudyAgentINDAssociations() {
        return lazyListHelper.getLazyList(StudyAgentINDAssociation.class);
    }

    @Transient
    public void setStudyAgentINDAssociations(
                    List<StudyAgentINDAssociation> studyAgentINDAssociations) {
        setStudyAgentINDAssociationsInternal(studyAgentINDAssociations);
    }

    @Transient
    public String getAgentAsString() {
        return agentAsString;
    }

    public void setAgentAsString(String agentAsString) {
        this.agentAsString = agentAsString;
    }

    public Participation getParticipation() {
        return participation;
    }

    public void setParticipation(Participation participation) {
        this.participation = participation;
    }

    @Transient
    public boolean getInvestigationalNewDrugIndicator() {
        return getStudyAgentINDAssociations() != null && getStudyAgentINDAssociations().size() > 0;
    }

    @Transient
    public void addStudyAgentINDAssociation(StudyAgentINDAssociation ass) {
        getStudyAgentINDAssociations().add(ass);
        ass.setStudyAgent(this);
    }

    public INDType getIndType() {
        return indType;
    }

    public void setIndType(INDType indType) {
        this.indType = indType;
    }

    public String getOtherAgent() {
        return otherAgent;
    }

    public void setOtherAgent(String otherAgent) {
        this.otherAgent = otherAgent;
    }

    public Boolean getPartOfLeadIND() {
        return partOfLeadIND;
    }

    public void setPartOfLeadIND(Boolean partOfLeadIND) {
        this.partOfLeadIND = partOfLeadIND;
    }
    
    @Transient
    public String getPartOfLeadINDAsString(){
    	if(partOfLeadIND == null) return "";
    	return partOfLeadIND ? "Yes" : "No";
    }

    @Transient
    public String getAgentName() {
        if (StringUtils.isNotEmpty(otherAgent)) return otherAgent;
        if (agent != null) return agent.getName();
        return "no-agent-name";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agent == null) ? 0 : agent.hashCode());
        result = prime * result + ((otherAgent == null) ? 0 : otherAgent.hashCode());
        return result;
    }

    // /OBJECT METHODS

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof StudyAgent)) return false;
        if (getClass() != obj.getClass()) return false;
        final StudyAgent other = (StudyAgent) obj;
        if(this.isRetired() || other.isRetired()) return false;
        if (agent == null) {
            if (other.agent != null) return false;
        } else if (!agent.equals(other.agent)) return false;
        if (otherAgent == null) {
            if (other.otherAgent != null) return false;
        } else if (!otherAgent.equals(other.otherAgent)) return false;
        return true;
    }

}
