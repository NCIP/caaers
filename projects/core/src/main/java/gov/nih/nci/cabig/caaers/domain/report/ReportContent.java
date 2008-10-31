package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
@Entity
@Table(name = "report_contents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_contents_id") })
public class ReportContent extends AbstractMutableDomainObject{
	private byte[] content;
    private String contentType;
    
    public ReportContent(){
    	this(null);
    }
    public ReportContent(String contentType){
    	this(contentType, null);
    }
    
    public ReportContent(String contentType, byte[] content) {
    	this.content = content;
    	this.contentType = contentType;
    }
    @Lob
    public byte[] getContent() {
		return content;
	}
    public void setContent(byte[] content) {
		this.content = content;
	}
    
    public String getContentType() {
		return contentType;
	}
    public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
