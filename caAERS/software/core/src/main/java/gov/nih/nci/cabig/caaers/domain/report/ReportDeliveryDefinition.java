/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

 
/**
 * The Class ReportDeliveryDefinition.
 */
@Entity
@Table(name = "report_delivery_defs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_delivery_defs_id") })
public class ReportDeliveryDefinition extends AbstractMutableDomainObject {

    /** The entity type corresponds to Recipient Type. */
    public static final int ENTITY_TYPE_SYSTEM = 1;

    /** The Constant ENTITY_TYPE_PERSON. */
    public static final int ENTITY_TYPE_PERSON = 2;

    /** The Constant ENTITY_TYPE_ROLE. */
    public static final int ENTITY_TYPE_ROLE = 3;

    /** The contact mechanism type. */
    public static final String ENDPOINT_TYPE_EMAIL = "email";

    /** The Constant ENDPOINT_TYPE_FAX. */
    public static final String ENDPOINT_TYPE_FAX = "fax";

    /** The Constant ENDPOINT_TYPE_URL. */
    public static final String ENDPOINT_TYPE_URL = "url";

    /** The format. */
    private ReportFormat format;

    /** The entity name. */
    private String entityName;

    /** The entity description. */
    private String entityDescription;

    /** The entity type. */
    private int entityType;

    /** The end point. */
    private String endPoint;

    /** The end point type. */
    private String endPointType;

    /** The user name. */
    private String userName;

    /** The password. */
    private String password;

    // dummy field to be used in serialized XML to check the status of the delivery using this ReportDeliveryDefinition
    /** The status. */
    private String status;

    // LOGIC
    /**
     * Creates the report delivery.
     *
     * @return the report delivery
     */
    public ReportDelivery createReportDelivery() {
        ReportDelivery rd = new ReportDelivery();
        rd.setReportDeliveryDefinition(this);
        rd.setDeliveryStatus(DeliveryStatus.CREATED);
        return rd;
    }

    /**
     * Gets the end point.
     *
     * @return the endPoint
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * Sets the end point.
     *
     * @param endPoint the endPoint to set
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Gets the end point type.
     *
     * @return the endPointType
     */
    public String getEndPointType() {
        return endPointType;
    }

    /**
     * Sets the end point type.
     *
     * @param endPointType the endPointType to set
     */
    public void setEndPointType(String endPointType) {
        this.endPointType = endPointType;
    }

    /**
     * Gets the entity description.
     *
     * @return the entityDescription
     */
    public String getEntityDescription() {
        return entityDescription;
    }

    /**
     * Sets the entity description.
     *
     * @param entityDescription the entityDescription to set
     */
    public void setEntityDescription(String entityDescription) {
        this.entityDescription = entityDescription;
    }

    /**
     * Gets the entity name.
     *
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Sets the entity name.
     *
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * Gets the format.
     *
     * @return the format
     */
    @Column(name = "format")
    @Type(type = "reportFormat")
    public ReportFormat getFormat() {
        return format;
    }

    /**
     * Sets the format.
     *
     * @param format the format to set
     */
    public void setFormat(ReportFormat format) {
        this.format = format;
    }

    /**
     * Gets the entity type.
     *
     * @return the entityType
     */
    public int getEntityType() {
        return entityType;
    }

    /**
     * Sets the entity type.
     *
     * @param entityType the entityType to set
     */
    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    @Transient
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Will copy the content into the
     * @param template
     */
    public static ReportDeliveryDefinition copy(ReportDeliveryDefinition template){
        ReportDeliveryDefinition rd = new ReportDeliveryDefinition();
        rd.format = template.format;
        rd.entityName = template.entityName;
        rd.entityDescription = template.entityDescription;
        rd.entityType = template.entityType;
        rd.endPoint = template.endPoint;
        rd.status = template.status;
        return rd;
    }

}
