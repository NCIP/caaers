/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers.track;


import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name="integration_log_message")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_integration_log_message_id") })
public class IntegrationLogMessage{
	
	private Integer id;
    private Integer version;

    @Id @GeneratedValue(generator = "id-generator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
	
    // business Id for the message
 	private String comboMessageId;
 	
 	// reference to the integration log
 	private IntegrationLog integrationLog;
 	
 	// error details 
 	private String message;
 	
 	private Stage stage;
 	
 	public IntegrationLogMessage(String comboMessageId, String message, Stage stage) {
		super();
		this.comboMessageId = comboMessageId;
		this.message = message;
		this.stage = stage;
	}
 	
 	public IntegrationLogMessage() {
		super();
	}

	@Enumerated(EnumType.STRING)
	@Column(name="stage")
 	public Stage getStage() {
 		return stage;
 	}

 	public void setStage(Stage stage) {
 		this.stage = stage;
 	}

 	public String getMessage() {
 		return message;
 	}

 	public void setMessage(String message) {
 		this.message = message;
 	}

 	public String getComboMessageId() {
		return comboMessageId;
	}

	public void setComboMessageId(String comboMessageId) {
		this.comboMessageId = comboMessageId;
	}

	@ManyToOne
 	@JoinColumn(name="log_id",nullable=false)
 	public IntegrationLog getIntegrationLog() {
 		return integrationLog;
 	}

 	public void setIntegrationLog(IntegrationLog integrationLog) {
 		this.integrationLog = integrationLog;
 	}
 	

}
