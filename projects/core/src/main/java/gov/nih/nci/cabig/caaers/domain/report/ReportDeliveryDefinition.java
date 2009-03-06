package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "report_delivery_defs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_delivery_defs_id") })
public class ReportDeliveryDefinition extends AbstractMutableDomainObject {

    /**
     * The entity type corresponds to Recipient Type
     */
    public static final int ENTITY_TYPE_SYSTEM = 1;

    public static final int ENTITY_TYPE_PERSON = 2;

    public static final int ENTITY_TYPE_ROLE = 3;

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

    private String userName;

    private String password;

    // LOGIC
    public ReportDelivery createReportDelivery() {
        ReportDelivery rd = new ReportDelivery();
        rd.setReportDeliveryDefinition(this);
        rd.setDeliveryStatus(DeliveryStatus.CREATED);
        return rd;
    }

    /**
     * @return the endPoint
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * @param endPoint
     *                the endPoint to set
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
     * @param endPointType
     *                the endPointType to set
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
     * @param entityDescription
     *                the entityDescription to set
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
     * @param entityName
     *                the entityName to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * @return the format
     */
    @Column(name = "format")
    @Type(type = "reportFormat")
    public ReportFormat getFormat() {
        return format;
    }

    /**
     * @param format
     *                the format to set
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
     * @param entityType
     *                the entityType to set
     */
    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
