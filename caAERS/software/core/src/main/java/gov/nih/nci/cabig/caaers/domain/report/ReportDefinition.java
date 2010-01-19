package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.rules.business.service.ReportDefinitionComparator;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.lang.ComparisonTools;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections15.functors.InstantiateFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * ReportDefinition represents the predefined set of notifications <code>PlannedNotification</code>
 * objects for an AdverseEventReport. A ReportDefinition is applied or used by the Report to
 * determine the notifications that are to be send out on a particular instance of time.
 * 
 * A ReportDefinition instance to be used, is picked-up by the Rules Engine Component, for a
 * specific kind of report based on the <code>name</code>.
 * 
 * @author Biju Joseph
 * 
 */
@Entity
@Table(name = "REPORT_CALENDAR_TEMPLATES")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_calendar_templat_id") })
public class ReportDefinition extends AbstractMutableDomainObject implements Serializable , Comparable<ReportDefinition>{
 
	private static final long serialVersionUID = 6196679267140283638L;

	private static final Log log = LogFactory.getLog(ReportDefinition.class);

    private String name;
    private String label;
    private String header;
    private String footer;
    private String description;
    private Boolean amendable;
    private Integer duration;
    private TimeScaleUnit timeScaleUnitType;
    private LazyListHelper lazyListHelper;
    private Organization organization;
    private List<ReportMandatoryFieldDefinition> mandatoryFields;
    private Boolean attributionRequired;
    private ReportFormatType reportFormatType;
    private Boolean physicianSignOff;
    private Boolean workflowEnabled;
    private ConfigProperty group;
    private ReportType reportType;
    private ReportDefinition parent;
    
    //PURE FABRICATED VARIABLES.
    protected ReportDefinitionComparator comprator;
    protected boolean manuallySelected; //will store the manually selected indicator.
    protected Date baseDate; //will store the base date, (for new report creation)
    protected List<String> mandatoryFieldsForXML;

    public ReportDefinition() {
        lazyListHelper = new LazyListHelper();
        lazyListHelper.add(ReportDeliveryDefinition.class, new InstantiateFactory<ReportDeliveryDefinition>(ReportDeliveryDefinition.class));
        lazyListHelper.add(PlannedNotification.class, new InstantiateFactory<PlannedNotification>(PlannedNotification.class));
        attributionRequired = false;
        workflowEnabled = false;
        comprator = new ReportDefinitionComparator();
    }

    // //// LOGIC
    /**
     * This method will create a Report object(an instance of, in the context of an AE Report) of a
     * ReportDefinition.
     */
    public Report createReport() {
        Report report = new Report();
        report.setReportDefinition(this);
        report.setStatus(ReportStatus.PENDING);
        return report;
    }
    
    public void addReportMandatoryFieldDefinition(ReportMandatoryFieldDefinition mandatoryField){
    	if(mandatoryFields == null) mandatoryFields = new ArrayList<ReportMandatoryFieldDefinition>();
    	mandatoryFields.add(mandatoryField);
    }
    
    /**
     * This method will return the details of the reminder(PlannedNotification) configured at the
     * specific index (represented by indexOnScale) of the this ReportDefinition
     * 
     * @param indexOnScale
     * @return
     */
    public List<PlannedNotification> fetchPlannedNotification(int indexOnScale) {
        List<PlannedNotification> plannedNotificaitons = new ArrayList<PlannedNotification>();

        for (PlannedNotification pn : getPlannedNotifications()) {
            if (pn.getIndexOnTimeScale() == indexOnScale) plannedNotificaitons.add(pn);
        }
        return plannedNotificaitons;
    }
    
    public PlannedNotification findPlannedNotificationById(Integer id){
    	for(PlannedNotification pn : getPlannedNotifications()){
    		if(pn.getId().equals(id)) return pn;
    	}
    	return null;
    }

    /**
     * This method will add a PlannedNotification to the plannedNotifications list.
     * 
     * @param pn
     */
    public void addPlannedNotification(PlannedNotification pn) {
        if (pn == null) return;
        getPlannedNotifications().add(pn);
    }

    /**
     * This method will append a ReportDeliveryDefinition to the reportDeliveriesInternal list.
     * 
     * @param rdd
     */
    public void addReportDeliveryDefinition(ReportDeliveryDefinition rdd) {
        if (rdd == null) return;
        getDeliveryDefinitionsInternal().add(rdd);
    }

    @Transient
    public List<ReportDeliveryDefinition> getDeliveryDefinitions() {
        return lazyListHelper.getLazyList(ReportDeliveryDefinition.class);
    }

    /**
     * Returns the mandatory flag associated with the ReportMandatoryField, whose fieldPath matches
     * with the parameter <param>fieldPath</param>
     */
    public boolean isFieldMandatory(String fieldPath) {
        if (mandatoryFields == null) return false;
        for (ReportMandatoryFieldDefinition mandatoryField : mandatoryFields) {
            if (StringUtils.equals(fieldPath, mandatoryField.getFieldPath())) {
                if (mandatoryField.getMandatory().equals(Mandatory.MANDATORY)) {
                	return true;
                }
            	//return mandatoryField.getMandatory();
            }
        }

        return false;
    }

    // //// BEAN PROPERTIES

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Type(type = "timeScaleUnit")
    @Column(name = "TIME_SCALE_UNIT_CODE")
    public TimeScaleUnit getTimeScaleUnitType() {
        return timeScaleUnitType;
    }

    public void setTimeScaleUnitType(TimeScaleUnit timeScaleUnitType) {
        this.timeScaleUnitType = timeScaleUnitType;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "rct_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<PlannedNotification> getPlannedNotificationsInternal() {
        return lazyListHelper.getInternalList(PlannedNotification.class);
    }

    public void setPlannedNotificationsInternal(List<PlannedNotification> plannedNotifications) {
        lazyListHelper.setInternalList(PlannedNotification.class, plannedNotifications);
    }

    @Transient
    public List<PlannedNotification> getPlannedNotifications() {
        return lazyListHelper.getLazyList(PlannedNotification.class);
    }

    @Transient
    public void setPlannedNotifications(List<PlannedNotification> plannedNotifications) {
        lazyListHelper.setInternalList(PlannedNotification.class, plannedNotifications);
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rct_id")
    @Cascade(value = { CascadeType.ALL })
    public List<ReportDeliveryDefinition> getDeliveryDefinitionsInternal() {
        return lazyListHelper.getInternalList(ReportDeliveryDefinition.class);
    }

    public void setDeliveryDefinitionsInternal(List<ReportDeliveryDefinition> deliveryDefinitions) {
        lazyListHelper.setInternalList(ReportDeliveryDefinition.class, deliveryDefinitions);
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rct_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<ReportMandatoryFieldDefinition> getMandatoryFields() {
        return mandatoryFields;
    }

    public void setMandatoryFields(List<ReportMandatoryFieldDefinition> mandatoryFields) {
        this.mandatoryFields = mandatoryFields;
    }

    public Boolean getAmendable() {
        return amendable;
    }

    public void setAmendable(Boolean amendable) {
        this.amendable = amendable;
    }

    public Boolean getAttributionRequired() {
        return attributionRequired;
    }

    public void setAttributionRequired(Boolean attributionRequired) {
        this.attributionRequired = attributionRequired;
    }

    public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public ReportDefinition getParent() {
		return parent;
	}
	
	public void setParent(ReportDefinition parent) {
		this.parent = parent;
	}
    
	@ManyToOne
	@JoinColumn(name="group_id")
	public ConfigProperty getGroup() {
		return group;
	}
	public void setGroup(ConfigProperty group) {
		this.group = group;
	}
	
    // //// OBJECT METHODS


	@Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append('[').append(getName()).append(
                        ", ").append(getOrganization()).append(']').toString();
    }
	
	public int compareTo(ReportDefinition o) {
		return comprator.compare(this, o);
	}

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = PRIME * result + ((getDuration() == null) ? 0 : getDuration());
        result = PRIME * result + ((getName() == null) ? 0 : getName().hashCode());
        result = PRIME * result + ((getOrganization() == null || getOrganization().getId() == null) ? 0 : getOrganization().getId().hashCode());
        result = PRIME * result + ((getGroup() == null || getGroup().getId() == null) ? 0 : getGroup().getId().hashCode());
        result = PRIME
                        * result
                        + ((getTimeScaleUnitType() == null) ? 0 : getTimeScaleUnitType().hashCode());
        return result;
    }
    
    /**
     * Returns true, if the other report definition belongs to same organization and report type
     * @param other
     * @return
     */
    public boolean isOfSameReportTypeAndOrganization(ReportDefinition other){
    	if(this == other) return true;
    	if(this.getId().equals(other.getId())) return true;
    	if(!this.getOrganization().getId().equals(other.getOrganization().getId())) return false;
    	if(!this.getGroup().getCode().equals(other.getGroup().getCode())) return false;
    	return true;
    }
    
    @Override
    public boolean equals(Object obj) {
        return equals(obj, false);
    }

    public boolean equals(Object obj, boolean trace) {
        if (this == obj) {
            if (trace) log.debug("== same object");
            return true;
        }
        if (obj == null) {
            if (trace) log.debug("!= other is null");
            return false;
        }
        if (!(obj instanceof ReportDefinition)) {
            if (trace) log.debug("!= other is not ReportDefinition: " + obj.getClass().getName());
            return false;
        }
        final ReportDefinition other = (ReportDefinition) obj;

        if(getId()!= null && other.getId() != null && getId().equals(other.getId()))
        	return true;
        
        if (!ComparisonTools.nullSafeEquals(getDescription(), other.getDescription())) {
            if (trace) log.debug("!= descriptions");
            return false;
        }
        if (getDuration() != null && !getDuration().equals(other.getDuration())) {
            if (trace) log.debug("!= durations");
            return false;
        }
        if (!ComparisonTools.nullSafeEquals(getName(), other.getName())) {
            if (trace) log.debug("!= names");
            return false;
        }
        if (!ComparisonTools.nullSafeEquals(getOrganization(), other.getOrganization())) {
            if (trace) log.debug("!= organizations");
            return false;
        }
        if (!ComparisonTools.nullSafeEquals(getTimeScaleUnitType(), other.getTimeScaleUnitType())) {
            if (trace) log.debug("!= time scale units");
            return false;
        }
        if(!ComparisonTools.nullSafeEquals(getGroup(), other.getGroup())){
        	if(trace) log.debug("!= group");
        	return false;
        }
        if (trace) log.debug("== by properties");
        return true;
    }
    
    @Transient
    public Date getExpectedDueDate(Date baseDate){
    	Calendar c = Calendar.getInstance();
    	c.setTime(baseDate);
    	c.add(timeScaleUnitType.getCalendarTypeCode(), duration);
    	return c.getTime();
    }
    
    @Transient
    public String getExpectedDisplayDueDate(Date baseDate){
    	
    	Date now = new Date();
    	Date expectedDueDate = getExpectedDueDate(baseDate);
    	
    	
    	int actualDuration = duration;
    	
    	String msgPrefix = "Due in ";
    	String msgSuffix = " overdue";
    	double difference = 0.0;
    	
    	if( DateUtils.compateDateAndTime(now, expectedDueDate) >= 0 ){
    		msgPrefix = "";
    		difference = now.getTime() - expectedDueDate.getTime();
    	}else {
    		msgSuffix = "";
    		difference = expectedDueDate.getTime() - now.getTime();
    	}
    	actualDuration = (int) Math.round(difference / timeScaleUnitType.getMilliSecondConversionFactor());
    	return msgPrefix + actualDuration + " " + timeScaleUnitType.name().toLowerCase() + ((actualDuration > 1)? "s": "") +  msgSuffix; 
    	
    }
    
    @Transient
    public String getExpectedDisplayDueDate(){
    	return getExpectedDisplayDueDate(new Date());
    }
    
    @Transient
    public Date getExpectedDueDate(){
    	return getExpectedDueDate(new Date());
    }
    
    
    @Type(type = "reportFormatType")
    @Column(name = "report_format_type")
	public ReportFormatType getReportFormatType() {
		return reportFormatType;
	}

	public void setReportFormatType(ReportFormatType reportFormatType) {
		this.reportFormatType = reportFormatType;
	}
	
	@Type(type = "reportType")
	@Column(name="report_type")
	public ReportType getReportType() {
		return reportType;
	}
	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	@Column(name = "physician_signoff")
	public Boolean getPhysicianSignOff() {
		return physicianSignOff;
	}
	
	public void setPhysicianSignOff(Boolean physicianSignOff) {
		this.physicianSignOff = physicianSignOff;
	}
	
	@Column(name = "workflow_enabled")
	public Boolean getWorkflowEnabled() {
		return workflowEnabled;
	}
	
	public void setWorkflowEnabled(Boolean workflowEnabled){
		this.workflowEnabled = workflowEnabled;
	}
	
	@Transient
	public boolean getExpedited(){
		if(group == null) return false;
		return group.getCode().equals("RT_AdEERS");
	}
	
	@Transient
	public String getDisplayDuration(){
		if(duration > 0) return duration + " " + timeScaleUnitType.getDisplayName() + "s";
		 return duration + " " + timeScaleUnitType.getDisplayName();
	}
	
	
	//This is a purely fabricated method, used only while creating new report. 
	@Transient
	public Date getBaseDate() {
		return baseDate;
	}
	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}
	
	//This is a purely fabricated method, used only while creating new report. 
	@Transient
	public boolean isManuallySelected() {
		return manuallySelected;
	}

	public void setManuallySelected(boolean manuallySelected) {
		this.manuallySelected = manuallySelected;
	}

    @Transient
    public List<ReportMandatoryFieldDefinition> getMandatoryFieldsForXMLByApplicability(Mandatory...types) {
        List<ReportMandatoryFieldDefinition> fields = new LinkedList<ReportMandatoryFieldDefinition>();
        if (getMandatoryFields() != null) {
            for (ReportMandatoryFieldDefinition field : getMandatoryFields()) {
                for (Mandatory m : types) {
                    if (field.getMandatory().equals(m)) fields.add(field);
                }
            }
        }
        return fields;
    }

    @Transient
    public void buildMandatoryFieldsForXML(Mandatory...types) {
        for (ReportMandatoryFieldDefinition mf : getMandatoryFieldsForXMLByApplicability(types)) {
            if (this.mandatoryFieldsForXML == null) this.mandatoryFieldsForXML = new ArrayList<String>();
            this.mandatoryFieldsForXML.add(mf.getFieldPath());
        }
    }

    @Transient
    public List<String> getMandatoryFieldsForXML() {
        return mandatoryFieldsForXML;
    }

    @Transient
    public void setMandatoryFieldsForXML(List<String> mf) {
        this.mandatoryFieldsForXML = mf;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}
