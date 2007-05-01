package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.AbstractDomainObject;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.DomainObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.FetchType;

/**
 * @author Krikor Krumlian
 */

@Entity
@Table(name = "study_diseases")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "term_type",
    discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("ABSTRACT_TERM") // should be ignored
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_study_diseases_id")
    }
)
public abstract class AbstractStudyDisease<T extends DomainObject> extends AbstractDomainObject {
    private T term;
    private Study study;

    ////// BEAN PROPERTIES

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(insertable=false, updatable=false, nullable=false)
    @ManyToOne
    @JoinColumn(insertable=false, updatable=false)
    public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}
    

    @Transient
    /* this is only transient here -- subclasses need to override it and specify what it refers to
       This should work:
    @ManyToOne
    @JoinColumn(name = "cause_id", nullable = false)
     */
    public T getTerm() {
        return term;
    }

    public void setTerm(T term) {
        this.term = term;
    }
}
