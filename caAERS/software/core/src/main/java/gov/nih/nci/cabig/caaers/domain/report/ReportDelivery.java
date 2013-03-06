package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "report_deliveries")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_deliveries_id") })
public class ReportDelivery extends AbstractMutableDomainObject {

    private ReportDeliveryDefinition template;

    private DeliveryStatus deliveryStatus;

    private String endPoint;

    private Report report;

    /**
     * Will return a copy of the ReportDelivery.
     * @param template
     * @return
     */
    public static ReportDelivery copy(ReportDelivery template){
        ReportDelivery rd = new ReportDelivery();
        rd.endPoint = template.endPoint;
        rd.deliveryStatus = template.deliveryStatus;
        rd.template = ReportDeliveryDefinition.copy(template.template);
        return rd;
    }


    /**
     * Gets the report delivery definition.
     *
     * @return the report delivery definition
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rdd_id")
    public ReportDeliveryDefinition getReportDeliveryDefinition() {
        return template;
    }

    public void setReportDeliveryDefinition(ReportDeliveryDefinition template) {
        this.template = template;
    }

    /**
     * @return the deliveryStatus
     */
    @Column(name = "delivery_status")
    @Type(type = "deliveryStatus")
    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * @param deliveryStatus
     *                the deliveryStatus to set
     */
    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rp_id", nullable = false)
    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
    
    @Transient
    public boolean isEmailType(){
    	return getReportDeliveryDefinition().getEndPointType().equals(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL);
    }
    
    @Transient
    public boolean isSystemType(){
    	return getReportDeliveryDefinition().getEndPointType().equals(ReportDeliveryDefinition.ENDPOINT_TYPE_URL) 
    		&& getReportDeliveryDefinition().getEntityType() == (ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
    }
    
    @Transient
    public String getUserName(){
    	return getReportDeliveryDefinition().getUserName();
    }
    @Transient
    public String getPassword(){
    	return getReportDeliveryDefinition().getPassword();
    }
}
