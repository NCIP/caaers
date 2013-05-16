/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
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
 * The Class AdverseEventIndex.
 */
@Entity
@Table(name = "adverseevent_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_adverseevent_index_id") })
public class AdverseEventIndex extends AbstractMutableDomainObject{
	
	/** The login id. */
	private String loginId;
	
	/** The adverse event. */
	private AdverseEvent adverseEvent;

	int role;
	
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
	 * Gets the adverse event.
	 *
	 * @return the adverse event
	 */
	@ManyToOne
    @JoinColumn(name = "adverseevent_id")
	public AdverseEvent getAdverseEvent() {
		return adverseEvent;
	}
	
	/**
	 * Sets the adverse event.
	 *
	 * @param adverseEvent the new adverse event
	 */
	public void setAdverseEvent(AdverseEvent adverseEvent) {
		this.adverseEvent = adverseEvent;
	}

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
