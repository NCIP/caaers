/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

 
/**
 * The Class ReportPerson.
 *
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_report_people")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ABSTRACT_BASE")
// should be ignored
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {
        @Parameter(name = "sequence", value = "seq_ae_report_people_id")
})
public abstract class ReportPerson extends PersonContact {

    /** The expedited report. */
    private ExpeditedAdverseEventReport expeditedReport;

	/** The investigator. */
	private Investigator investigator;
	
	/** The research staff. */
	private ResearchStaff researchStaff;
	

    // ////BOUND PROPERTIES

    /**
     * Gets the expedited report.
     *
     * @return the expedited report
     */
    @OneToOne
    @JoinColumn(name = "report_id")
    public ExpeditedAdverseEventReport getExpeditedReport() {
        return expeditedReport;
    }

    /**
     * Sets the expedited report.
     *
     * @param expeditedReport the new expedited report
     */
    public void setExpeditedReport(ExpeditedAdverseEventReport expeditedReport) {
        this.expeditedReport = expeditedReport;
    }
    
    
    /**
     * Gets the person.
     *
     * @return the person
     */
    @Transient
	public Person getPerson() {
		if(getResearchStaff() != null) return researchStaff;
		return getInvestigator();
	}
	
	/**
	 * Sets the person.
	 *
	 * @param person the new person
	 */
	public void setPerson(Person person) {
		if(person instanceof ResearchStaff) setResearchStaff((ResearchStaff)person);
		else setInvestigator((Investigator)person);
	}
    

	/**
	 * Gets the investigator.
	 *
	 * @return the investigator
	 */
	@ManyToOne
    @JoinColumn(name = "investigator_id")
	public Investigator getInvestigator() {
		return investigator;
	}

	/**
	 * Sets the investigator.
	 *
	 * @param investigator the new investigator
	 */
	public void setInvestigator(Investigator investigator) {
		this.investigator = investigator;
	}

	/**
	 * Gets the research staff.
	 *
	 * @return the research staff
	 */
	@ManyToOne
    @JoinColumn(name = "researchstaff_id")
	public ResearchStaff getResearchStaff() {
		return researchStaff;
	}

	/**
	 * Sets the research staff.
	 *
	 * @param researchStaff the new research staff
	 */
	public void setResearchStaff(ResearchStaff researchStaff) {
		this.researchStaff = researchStaff;
	}

    /**
     * Copy.
     *
     * @return the report person
     */
    public ReportPerson copy() {
        ReportPerson reportPerson = (ReportPerson) BeanUtils.instantiateClass(getClass());
        BeanUtils.copyProperties(this, reportPerson, new String[]{"id", "gridId",
                "version", "expeditedReport", "primaryIdentifierValue"});

        return reportPerson;

    }

}
