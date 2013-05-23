/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class InvestigatorIndex.
 */
@Entity
@Table(name = "investigator_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_inv_index_id") })
public class InvestigatorIndex extends AbstractMutableDomainObject{
	
	/** The login id. */
	private String loginId;
	
	/** The investigator. */
	private Investigator investigator;

     private int role;

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}

