package gov.nih.nci.cabig.caaers.domain;

import java.util.List;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class encapsulates all types of organizations associated with a Study
 * 
 * @author Ram Chilukuri
 * 
 */
@Entity
@Table(name = "study_organizations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_organizations_id") })
public abstract class StudyOrganization extends AbstractMutableDomainObject implements StudyChild {

    private Study study;

    private Organization organization;

    private LazyListHelper lazyListHelper;

    public StudyOrganization() {
        lazyListHelper = new LazyListHelper();
        lazyListHelper.add(StudyInvestigator.class,
                        new StudyOrganizationChildInstantiateFactory<StudyInvestigator>(this,
                                        StudyInvestigator.class));
        lazyListHelper.add(StudyPersonnel.class,
                        new StudyOrganizationChildInstantiateFactory<StudyPersonnel>(this,
                                        StudyPersonnel.class));

    }

    public void addStudyPersonnel(StudyPersonnel studyPersonnel) {
        getStudyPersonnels().add(studyPersonnel);
        studyPersonnel.setStudyOrganization(this);
    }

    public void addStudyInvestigators(StudyInvestigator studyInvestigator) {
        getStudyInvestigators().add(studyInvestigator);
        studyInvestigator.setStudyOrganization(this);
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    @Cascade(value = { CascadeType.LOCK })
    public Organization getOrganization() {
        return organization;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = { CascadeType.LOCK, CascadeType.EVICT })
    public Study getStudy() {
        return study;
    }

    @OneToMany(mappedBy = "studyOrganization")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyInvestigator> getStudyInvestigatorsInternal() {
        return lazyListHelper.getInternalList(StudyInvestigator.class);
    }

    public void setStudyInvestigatorsInternal(List<StudyInvestigator> studyInvestigators) {
        lazyListHelper.setInternalList(StudyInvestigator.class, studyInvestigators);
    }

    @OneToMany(mappedBy = "studyOrganization", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
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

    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }

    public void setStudy(final Study study) {
        this.study = study;
    }

    @Transient
    public abstract String getRoleName();

}
