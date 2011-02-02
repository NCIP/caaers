package gov.nih.nci.cabig.caaers.domain.index;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

 
/**
 * The Class ParticipantIndex.
 */
@Entity
@Table(name = "participant_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_participant_index_id") })
public class ParticipantIndex extends AbstractMutableDomainObject{
	
	/** The login id. */
	private String loginId;
	
	/** The participant. */
	private Participant participant;

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
     * Gets the participant.
     *
     * @return the participant
     */
    @ManyToOne
    @JoinColumn(name = "participant_id")
	public Participant getParticipant() {
		return participant;
	}
	
	/**
	 * Sets the participant.
	 *
	 * @param participant the new participant
	 */
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

}
