/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="integration_log_details")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_integration_log_details_id") })
public class IntegrationLogDetail extends AbstractMutableDomainObject{

    IntegrationLog integrationLog;

	// business Id of the entity
	private String businessId;
	
	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	// error details 
	private String outcome;
	
	private SynchStatus synchStatus;
	
	private boolean failed;
	
	@Enumerated(EnumType.STRING)
	public SynchStatus getSynchStatus() {
		return synchStatus;
	}

	public void setSynchStatus(SynchStatus synchStatus) {
		this.synchStatus = synchStatus;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

    @ManyToOne
    @JoinColumn(name = "log_id",nullable = false)
    public IntegrationLog getIntegrationLog() {
        return integrationLog;
    }

    public void setIntegrationLog(IntegrationLog integrationLog) {
        this.integrationLog = integrationLog;
    }
}
