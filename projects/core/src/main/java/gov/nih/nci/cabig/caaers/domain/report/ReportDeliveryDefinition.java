package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

@Entity
@Table(name="report_delivery_defs")
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_report_delivery_defs_id")
    }
)
public class ReportDeliveryDefinition extends AbstractMutableDomainObject{
	
	/**
	 * The entity type corresponds to Recipient Type
	 */
	public static final int ENTITY_TYPE_SYSTEM = 1;
	public static final int ENTITY_TYPE_PERSON = 1;
	public static final int ENTITY_TYPE_ROLE = 2;
	
	/**
	 * The contact mechanism type
	 */
	public static final String ENDPOINT_TYPE_EMAIL = "email";
	public static final String ENDPOINT_TYPE_FAX = "fax";
	public static final String ENDPOINT_TYPE_URL = "url";
	
	
	private ReportFormat format;
	private String entityName;
	private String entityDescription;
	private int entityType;
	private String endPoint;
	private String endPointType;
	
	
	/**
	 * @return the endPoint
	 */
	public String getEndPoint() {
		return endPoint;
	}
	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	/**
	 * @return the endPointType
	 */
	public String getEndPointType() {
		return endPointType;
	}
	/**
	 * @param endPointType the endPointType to set
	 */
	public void setEndPointType(String endPointType) {
		this.endPointType = endPointType;
	}
	/**
	 * @return the entityDescription
	 */
	public String getEntityDescription() {
		return entityDescription;
	}
	/**
	 * @param entityDescription the entityDescription to set
	 */
	public void setEntityDescription(String entityDescription) {
		this.entityDescription = entityDescription;
	}
	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return the format
	 */
	@Column(name="format")
	@Type(type="reportFormat")
	public ReportFormat getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(ReportFormat format) {
		this.format = format;
	}
	/**
	 * @return the entityType
	 */
	public int getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}
	
}
