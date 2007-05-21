package gov.nih.nci.cabig.caaers.domain.notification;

import gov.nih.nci.cabig.caaers.rules.ui.DomainObject;
import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 11, 2007
 * @version     %I%, %G%
 * @since       1.0
 */

@Embeddable
public class NotificationBodyContent extends DomainObject{
	
	private byte[] body;
	
	
	
	private String contentType;
	
	public NotificationBodyContent(){
		this.contentType ="text/html";
		
	}
	
	
	@Lob
	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] content) {
		this.body = content;
	}
	@Transient
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	@Transient
	public String getBodyAsString(){
		return new String(body);
	}
	
	
}
