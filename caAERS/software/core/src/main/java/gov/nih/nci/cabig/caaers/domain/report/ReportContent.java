/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
 

/**
 * The Class ReportContent.
 */
@Entity
@Table(name = "report_contents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_contents_id") })
public class ReportContent extends AbstractMutableDomainObject{
	
	/** The content. */
	private byte[] content;
    
    /** The content type. */
    private String contentType;
    
    /**
     * Instantiates a new report content.
     */
    public ReportContent(){
    	this(null);
    }
    
    /**
     * Instantiates a new report content.
     *
     * @param contentType the content type
     */
    public ReportContent(String contentType){
    	this(contentType, null);
    }
    
    /**
     * Instantiates a new report content.
     *
     * @param contentType the content type
     * @param content the content
     */
    public ReportContent(String contentType, byte[] content) {
    	this.content = content;
    	this.contentType = contentType;
    }
    
    /**
     * Gets the content.
     *
     * @return the content
     */
    @Lob
    public byte[] getContent() {
		return content;
	}
    
    /**
     * Sets the content.
     *
     * @param content the new content
     */
    public void setContent(byte[] content) {
		this.content = content;
	}
    
    /**
     * Gets the content type.
     *
     * @return the content type
     */
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
