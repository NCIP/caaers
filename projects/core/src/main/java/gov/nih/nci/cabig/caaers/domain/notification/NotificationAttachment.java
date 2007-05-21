package gov.nih.nci.cabig.caaers.domain.notification;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "attachments")
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "attachments_id_seq")
    }
)
public class NotificationAttachment extends AbstractMutableDomainObject{
	private byte[] content;

	/**
	 * @return the content
	 */
	@Lob
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
