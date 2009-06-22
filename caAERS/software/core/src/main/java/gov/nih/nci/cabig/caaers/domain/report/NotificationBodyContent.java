package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 11, 2007
 * @version %I%, %G%
 * @since 1.0
 */

@Embeddable
public class NotificationBodyContent {
    private String body;

    private String contentType;

    public NotificationBodyContent() {
        this.contentType = "text/html";

    }

    public String getBody() {
        return body;
    }

    public void setBody(String content) {
        this.body = content;
    }

    @Transient
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
