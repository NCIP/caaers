package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

 
/**
 * The Class NotificationBodyContent.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 11, 2007
 * @version %I%, %G%
 * @since 1.0
 */

@Embeddable
public class NotificationBodyContent {
    
    /** The body. */
    private String body;

    /** The content type. */
    private String contentType;

    /**
     * Instantiates a new notification body content.
     */
    public NotificationBodyContent() {
        this.contentType = "text/html";

    }

    /**
     * Gets the body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body.
     *
     * @param content the new body
     */
    public void setBody(String content) {
        this.body = content;
    }

    /**
     * Gets the content type.
     *
     * @return the content type
     */
    @Transient
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the content type.
     *
     * @param contentType the new content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
