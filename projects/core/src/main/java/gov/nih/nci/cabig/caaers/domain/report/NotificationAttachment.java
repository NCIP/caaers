package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "attachments")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_attachments_id") })
public class NotificationAttachment extends AbstractMutableDomainObject {
    private byte[] content;

    /**
     * @return the content
     */
    @Lob
    public byte[] getContent() {
        return content;
    }

    /**
     * @param content
     *                the content to set
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

}
