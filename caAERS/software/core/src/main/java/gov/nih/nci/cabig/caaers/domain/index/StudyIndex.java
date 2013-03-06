/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.index;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

 
/**
 * The Class StudyIndex.
 */
@Entity
@Table(name = "study_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_study_index_id") })
public class StudyIndex extends AbstractMutableDomainObject{
	
	/** The login id. */
	private String loginId;
	
	/** The study. */
	private Study study;

    /** The role code. */
    private Integer roleCode;

    /**
     * Gets the role code.
     *
     * @return the role code
     */
    public Integer getRoleCode() {
        return roleCode;
    }

    /**
     * Sets the role code.
     *
     * @param roleCode the new role code
     */
    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }
	
	/**
	 * Gets the login id.
	 *
	 * @return the login id
	 */
	public String getLoginId() {
		return loginId;
	}
	
	/**
	 * Sets the login id.
	 *
	 * @param loginId the new login id
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
    /**
     * Gets the study.
     *
     * @return the study
     */
    @ManyToOne
    @JoinColumn(name = "study_id")
	public Study getStudy() {
		return study;
	}
	
	/**
	 * Sets the study.
	 *
	 * @param study the new study
	 */
	public void setStudy(Study study) {
		this.study = study;
	}
	
}
