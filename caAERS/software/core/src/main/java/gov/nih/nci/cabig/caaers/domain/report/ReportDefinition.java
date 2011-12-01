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
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.functors.InstantiateFactory;
import org.apache.commons.lang.StringUtils;

import org.hibernate.annotations.Parameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

 
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
 
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6196679267140283638L;

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(ReportDefinition.class);

    /** The name. */
    private String name;
    
    /** The label. */
    private String label;
    
    /** The header. */
    private String header;
    
    /** The footer. */
    private String footer;
    
    /** The description. */
    private String description;
    
    /** The amendable. */
    private Boolean amendable;
    
    /** The duration. */
    private Integer duration;
    
    /** The time scale unit type. */
    private TimeScaleUnit timeScaleUnitType;
    
    /** The lazy list helper. */
    private LazyListHelper lazyListHelper;
    
    /** The organization. */
    private Organization organization;
    
    /** The mandatory fields. */
    private List<ReportMandatoryFieldDefinition> mandatoryFields;
    
    /** The attribution required. */
    private Boolean attributionRequired;
    
    /** The report format type. */
    private ReportFormatType reportFormatType;
    
    /** The physician sign off. */
    private Boolean physicianSignOff;
    
    /** The workflow enabled. */
    private Boolean workflowEnabled;
    
    /** The group. */
    private ConfigProperty group;
    
    /** The report type. */
    private ReportType reportType;
    
    /** The parent. */
    private ReportDefinition parent;
    
    /** The enabled. */
    private Boolean enabled;
    
    //PURE FABRICATED VARIABLES.
    /** The comprator. */
    protected ReportDefinitionComparator comprator;
    
    /** The manually selected. */
    protected boolean manuallySelected; //will store the manually selected indicator.
    
    /** The base date. */
    protected Date baseDate; //will store the base date, (for new report creation)

    /**
     * Instantiates a new report definition.
     */
    public ReportDefinition() {
        lazyListHelper = new LazyListHelper();
        lazyListHelper.add(ReportDeliveryDefinition.class, new InstantiateFactory<ReportDeliveryDefinition>(ReportDeliveryDefinition.class));
        lazyListHelper.add(PlannedNotification.class, new InstantiateFactory<PlannedNotification>(PlannedNotification.class));
        attributionRequired = false;
        workflowEnabled = false;
        comprator = new ReportDefinitionComparator();
    }

    /**
     * Instantiates a new report definition.
     *
     * @param id the id
     * @param name the name
     * @param label the label
     */
    public ReportDefinition(Integer id, String name, String label){
        this();
        this.setId(id);
        this.name = name;
        this.label = label;
    }

    // //// LOGIC
    /**
     * This method will create a Report object(an instance of, in the context of an AE Report) of a
     * ReportDefinition.
     *
     * @return the report
     */
    public Report createReport() {
        Report report = new Report();
        report.setReportDefinition(this);
        report.setStatus(ReportStatus.PENDING);
        return report;
    }
    
    /**
     * Adds the report mandatory field definition.
     *
     * @param mandatoryField the mandatory field
     */
    public void addReportMandatoryFieldDefinition(ReportMandatoryFieldDefinition mandatoryField){
    	if(mandatoryFields == null) mandatoryFields = new ArrayList<ReportMandatoryFieldDefinition>();
    	mandatoryFields.add(mandatoryField);
    }
    
    /**
     * This method will return the details of the reminder(PlannedNotification) configured at the
     * specific index (represented by indexOnScale) of the this ReportDefinition.
     *
     * @param indexOnScale the index on scale
     * @return the list
     */
    public List<PlannedNotification> fetchPlannedNotification(int indexOnScale) {
        List<PlannedNotification> plannedNotificaitons = new ArrayList<PlannedNotification>();

        for (PlannedNotification pn : getPlannedNotifications()) {
            if (pn.getIndexOnTimeScale() == indexOnScale) plannedNotificaitons.add(pn);
        }
        return plannedNotificaitons;
    }

    /**
     * This method will add a PlannedNotification to the plannedNotifications list.
     *
     * @param pn the pn
     */
    public void addPlannedNotification(PlannedNotification pn) {
        if (pn == null) return;
        getPlannedNotifications().add(pn);
    }

    /**
     * This method will append a ReportDeliveryDefinition to the reportDeliveriesInternal list.
     *
     * @param rdd the rdd
     */
    public void addReportDeliveryDefinition(ReportDeliveryDefinition rdd) {
        if (rdd == null) return;
        getDeliveryDefinitionsInternal().add(rdd);
    }

    /**
     * Gets the delivery definitions.
     *
     * @return the delivery definitions
     */
    @Transient
    public List<ReportDeliveryDefinition> getDeliveryDefinitions() {
        return lazyListHelper.getLazyList(ReportDeliveryDefinition.class);
    }

    /**
     * Finds the ReportMandatoryFieldDefinition having the specified path.
     *
     * @param fieldPath the field path
     * @return the report mandatory field definition
     */
    public ReportMandatoryFieldDefinition findReportMandatoryFieldDefinitionByPath(String fieldPath){
      if(getMandatoryFields() == null || getMandatoryFields().isEmpty()) return null;
      for(ReportMandatoryFieldDefinition mfd: getMandatoryFields()){
          if(StringUtils.equals(mfd.getFieldPath(), fieldPath )) return mfd;
      }
      return null;
    }
    // //// BEAN PROPERTIES

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the duration.
     *
     * @return the duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets the duration.
     *
     * @param duration the new duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * Gets the time scale unit type.
     *
     * @return the time scale unit type
     */
    @Type(type = "timeScaleUnit")
    @Column(name = "TIME_SCALE_UNIT_CODE")
    public TimeScaleUnit getTimeScaleUnitType() {
        return timeScaleUnitType;
    }

    /**
     * Sets the time scale unit type.
     *
     * @param timeScaleUnitType the new time scale unit type
     */
    public void setTimeScaleUnitType(TimeScaleUnit timeScaleUnitType) {
        this.timeScaleUnitType = timeScaleUnitType;
    }

    /**
     * Gets the planned notifications internal.
     *
     * @return the planned notifications internal
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "rct_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<PlannedNotification> getPlannedNotificationsInternal() {
        return lazyListHelper.getInternalList(PlannedNotification.class);
    }

    /**
     * Sets the planned notifications internal.
     *
     * @param plannedNotifications the new planned notifications internal
     */
    public void setPlannedNotificationsInternal(List<PlannedNotification> plannedNotifications) {
        lazyListHelper.setInternalList(PlannedNotification.class, plannedNotifications);
    }

    /**
     * Gets the planned notifications.
     *
     * @return the planned notifications
     */
    @Transient
    public List<PlannedNotification> getPlannedNotifications() {
        return lazyListHelper.getLazyList(PlannedNotification.class);
    }

    /**
     * Sets the planned notifications.
     *
     * @param plannedNotifications the new planned notifications
     */
    @Transient
    public void setPlannedNotifications(List<PlannedNotification> plannedNotifications) {
        lazyListHelper.setInternalList(PlannedNotification.class, plannedNotifications);
    }

    /**
     * Gets the delivery definitions internal.
     *
     * @return the delivery definitions internal
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rct_id")
    @Cascade(value = { CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportDeliveryDefinition> getDeliveryDefinitionsInternal() {
        return lazyListHelper.getInternalList(ReportDeliveryDefinition.class);
    }

    /**
     * Sets the delivery definitions internal.
     *
     * @param deliveryDefinitions the new delivery definitions internal
     */
    public void setDeliveryDefinitionsInternal(List<ReportDeliveryDefinition> deliveryDefinitions) {
        lazyListHelper.setInternalList(ReportDeliveryDefinition.class, deliveryDefinitions);
    }

    /**
     * Gets the organization.
     *
     * @return the organization
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Sets the organization.
     *
     * @param organization the new organization
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * Gets the mandatory fields.
     *
     * @return the mandatory fields
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rct_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportMandatoryFieldDefinition> getMandatoryFields() {
        return mandatoryFields;
    }

    /**
     * Sets the mandatory fields.
     *
     * @param mandatoryFields the new mandatory fields
     */
    public void setMandatoryFields(List<ReportMandatoryFieldDefinition> mandatoryFields) {
        this.mandatoryFields = mandatoryFields;
    }

    /**
     * Will return all the ReportMandatoryFieldDefinition that are not rule based.
     *
     * @return the all non rule based mandatory fields
     */
    @Transient
    public Collection<ReportMandatoryFieldDefinition> getAllNonRuleBasedMandatoryFields(){
        return CollectionUtils.select(getMandatoryFields(), new Predicate<ReportMandatoryFieldDefinition>(){
            public boolean evaluate(ReportMandatoryFieldDefinition rd) {
                return !rd.isRuleBased();
            }
        });
    }

    /**
     * Will return the ReportMandatoryFieldDefinition that are associated to rules.
     *
     * @return the all rule based mandatory fields
     */
    @Transient
    public Collection<ReportMandatoryFieldDefinition> getAllRuleBasedMandatoryFields(){
       return CollectionUtils.select(getMandatoryFields(), new Predicate<ReportMandatoryFieldDefinition>(){
            public boolean evaluate(ReportMandatoryFieldDefinition rd) {
                return rd.isRuleBased();
            }
        });
    }

    /**
     * Will return the ReportMandatoryFieldDefinition that are self referenced.
     *
     * @return the self referenced rule based mandatory fields
     */
    @Transient
    public Collection<ReportMandatoryFieldDefinition> getSelfReferencedRuleBasedMandatoryFields(){
        return CollectionUtils.select(getMandatoryFields(), new Predicate<ReportMandatoryFieldDefinition>(){
            public boolean evaluate(ReportMandatoryFieldDefinition rd) {
                return rd.isRuleBased() && rd.isSelfReferenced();
            }
        });
    }

    /**
     * Will return the ReportMandatoryFieldDefinition that are not self referenced.
     *
     * @return the non self referenced rule based mandatory fields
     */
    @Transient
    public Collection<ReportMandatoryFieldDefinition> getNonSelfReferencedRuleBasedMandatoryFields(){
        return CollectionUtils.select(getMandatoryFields(), new Predicate<ReportMandatoryFieldDefinition>(){
            public boolean evaluate(ReportMandatoryFieldDefinition rd) {
                return rd.isRuleBased() && !rd.isSelfReferenced();
            }
        });
    }

    /**
     * Gets the amendable.
     *
     * @return the amendable
     */
    public Boolean getAmendable() {
        return amendable;
    }

    /**
     * Sets the amendable.
     *
     * @param amendable the new amendable
     */
    public void setAmendable(Boolean amendable) {
        this.amendable = amendable;
    }

    /**
     * Gets the attribution required.
     *
     * @return the attribution required
     */
    public Boolean getAttributionRequired() {
        return attributionRequired;
    }

    /**
     * Sets the attribution required.
     *
     * @param attributionRequired the new attribution required
     */
    public void setAttributionRequired(Boolean attributionRequired) {
        this.attributionRequired = attributionRequired;
    }

    /**
     * Gets the label.
     *
     * @return the label
     */
    public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public ReportDefinition getParent() {
		return parent;
	}
	
	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public void setParent(ReportDefinition parent) {
		this.parent = parent;
	}
    
	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	@ManyToOne
	@JoinColumn(name="group_id")
	public ConfigProperty getGroup() {
		return group;
	}
	
	/**
	 * Sets the group.
	 *
	 * @param group the new group
	 */
	public void setGroup(ConfigProperty group) {
		this.group = group;
	}
	
    // //// OBJECT METHODS


	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append('[').append(getName()).append(
                        ", ").append(getOrganization()).append(']').toString();
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ReportDefinition o) {
		return comprator.compare(this, o);
	}

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
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
     * Returns true, if the other report definition belongs to same organization and report type.
     *
     * @param other the other
     * @return true, if is of same report type and organization
     */
    public boolean isOfSameReportTypeAndOrganization(ReportDefinition other){
    	if(this == other) return true;
    	if(this.getId().equals(other.getId())) return true;
    	if(!this.getOrganization().getId().equals(other.getOrganization().getId())) return false;
    	if(!this.getGroup().getCode().equals(other.getGroup().getCode())) return false;
    	return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return equals(obj, false);
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @param trace the trace
     * @return true, if successful
     */
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
    
    /**
     * Gets the expected due date.
     *
     * @param baseDate the base date
     * @return the expected due date
     */
    @Transient
    public Date getExpectedDueDate(Date baseDate){
    	Calendar c = Calendar.getInstance();
    	c.setTime(baseDate);
    	c.add(timeScaleUnitType.getCalendarTypeCode(), duration);
    	return c.getTime();
    }
    
    /**
     * Gets the expected display due date.
     *
     * @param baseDate the base date
     * @return the expected display due date
     */
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
    
    /**
     * Gets the expected display due date.
     *
     * @return the expected display due date
     */
    @Transient
    public String getExpectedDisplayDueDate(){
    	return getExpectedDisplayDueDate(new Date());
    }
    
    /**
     * Gets the expected due date.
     *
     * @return the expected due date
     */
    @Transient
    public Date getExpectedDueDate(){
    	return getExpectedDueDate(new Date());
    }
    
    
    /**
     * Gets the report format type.
     *
     * @return the report format type
     */
    @Type(type = "reportFormatType")
    @Column(name = "report_format_type")
	public ReportFormatType getReportFormatType() {
		return reportFormatType;
	}

	/**
	 * Sets the report format type.
	 *
	 * @param reportFormatType the new report format type
	 */
	public void setReportFormatType(ReportFormatType reportFormatType) {
		this.reportFormatType = reportFormatType;
	}
	
	/**
	 * Gets the report type.
	 *
	 * @return the report type
	 */
	@Type(type = "reportType")
	@Column(name="report_type")
	public ReportType getReportType() {
		return reportType;
	}
	
	/**
	 * Sets the report type.
	 *
	 * @param reportType the new report type
	 */
	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	/**
	 * Gets the physician sign off.
	 *
	 * @return the physician sign off
	 */
	@Column(name = "physician_signoff")
	public Boolean getPhysicianSignOff() {
		return physicianSignOff;
	}
	
	/**
	 * Sets the physician sign off.
	 *
	 * @param physicianSignOff the new physician sign off
	 */
	public void setPhysicianSignOff(Boolean physicianSignOff) {
		this.physicianSignOff = physicianSignOff;
	}
	
	/**
	 * Gets the workflow enabled.
	 *
	 * @return the workflow enabled
	 */
	@Column(name = "workflow_enabled")
	public Boolean getWorkflowEnabled() {
		return workflowEnabled;
	}
	
	/**
	 * Sets the workflow enabled.
	 *
	 * @param workflowEnabled the new workflow enabled
	 */
	public void setWorkflowEnabled(Boolean workflowEnabled){
		this.workflowEnabled = workflowEnabled;
	}
	
	/**
	 * Gets the expedited.
	 *
	 * @return the expedited
	 */
	@Transient
	public boolean getExpedited(){
		if(group == null) return false;
		return group.getCode().equals("RT_AdEERS");
	}
	
	/**
	 * Gets the display duration.
	 *
	 * @return the display duration
	 */
	@Transient
	public String getDisplayDuration(){
		if(duration > 0) return duration + " " + timeScaleUnitType.getDisplayName() + "s";
		 return duration + " " + timeScaleUnitType.getDisplayName();
	}
	
	
	//This is a purely fabricated method, used only while creating new report. 
	/**
	 * Gets the base date.
	 *
	 * @return the base date
	 */
	@Transient
	public Date getBaseDate() {
		return baseDate;
	}
	
	/**
	 * Sets the base date.
	 *
	 * @param baseDate the new base date
	 */
	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}
	
	//This is a purely fabricated method, used only while creating new report. 
	/**
	 * Checks if is manually selected.
	 *
	 * @return true, if is manually selected
	 */
	@Transient
	public boolean isManuallySelected() {
		return manuallySelected;
	}

	/**
	 * Sets the manually selected.
	 *
	 * @param manuallySelected the new manually selected
	 */
	public void setManuallySelected(boolean manuallySelected) {
		this.manuallySelected = manuallySelected;
	}

   
    /**
     * Gets the header.
     *
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * Sets the header.
     *
     * @param header the new header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Gets the footer.
     *
     * @return the footer
     */
    public String getFooter() {
        return footer;
    }

    /**
     * Sets the footer.
     *
     * @param footer the new footer
     */
    public void setFooter(String footer) {
        this.footer = footer;
    }

	/**
	 * Gets the enabled.
	 *
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
