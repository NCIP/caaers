/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.resolver.RemoteStudyResolver;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.semanticbits.coppa.domain.annotations.RemoteEntity;
import com.semanticbits.coppa.domain.annotations.RemoteProperty;
import com.semanticbits.coppa.domain.annotations.RemoteUniqueId;

 
/**
 * The Class RemoteStudy.
 *
 * @author Monish Dombla
 */

@Entity
@RemoteEntity(entityResolver=RemoteStudyResolver.class)
@DiscriminatorValue("REMOTE")
public class RemoteStudy extends Study{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The investigational new drug list. */
	private List<InvestigationalNewDrug> investigationalNewDrugList = new ArrayList<InvestigationalNewDrug>();

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Study#getExternalId()
	 */
	@RemoteUniqueId
    @RemoteProperty
    public String getExternalId() {
		return externalId;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Study#getShortTitle()
	 */
	@RemoteProperty
    public String getShortTitle() {
        return shortTitle;
    }
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Study#getLongTitle()
	 */
	@RemoteProperty
    public String getLongTitle() {
        return longTitle;
    }
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Study#getPhaseCode()
	 */
	@RemoteProperty
    public String getPhaseCode() {
        return phaseCode;
    }
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Study#getDescription()
	 */
	@RemoteProperty
    public String getDescription() {
        return description;
    }

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Study#getStatus()
	 */
	@RemoteProperty
    public String getStatus() {
        return status;
    }
	
	/**
	 * Gets the investigational new drug list.
	 *
	 * @return the investigational new drug list
	 */
	@Transient
	public List<InvestigationalNewDrug> getInvestigationalNewDrugList() {
		return investigationalNewDrugList;
	}

	/**
	 * Sets the investigational new drug list.
	 *
	 * @param investigationalNewDrugList the new investigational new drug list
	 */
	public void setInvestigationalNewDrugList(
			List<InvestigationalNewDrug> investigationalNewDrugList) {
		this.investigationalNewDrugList = investigationalNewDrugList;
	}
	
	/**
	 * Adds the investigational new drug.
	 *
	 * @param investigationalNewDrug the investigational new drug
	 */
	public void addInvestigationalNewDrug(InvestigationalNewDrug investigationalNewDrug){
		getInvestigationalNewDrugList().add(investigationalNewDrug);
	}
}
