package gov.nih.nci.cabig.caaers.domain.report;


import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.lang.ComparisonTools;
import org.apache.commons.collections15.functors.InstantiateFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * ReportDefinition represents the predefined set of notifications <code>PlannedNotification</code> objects for an AdverseEventReport.
 * A ReportDefinition is applied or used by the Report to determine the notifications that are to be send out
 * on a particular instance of time.
 *
 * A ReportDefinition instance to be used, is picked-up by the Rules Engine Component, for a specific kind of report
 * based on the <code>name</code>.
 *
 * @author Biju Joseph
 *
 */
@Entity
@Table(name="REPORT_CALENDAR_TEMPLATES")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
    @Parameter(name="sequence", value="seq_report_calendar_templat_id")
        }
)
public class ReportDefinition extends AbstractMutableDomainObject implements Serializable{
    private static final Log log = LogFactory.getLog(ReportDefinition.class);

    private String name;
    private String description;
    private int duration;
    private TimeScaleUnit timeScaleUnitType;
    private List<PlannedNotification> plannedNotifications;
    private LazyListHelper lazyListHelper;
    private Organization organization;
    private List<ReportMandatoryFieldDefinition> mandatoryFields;

    public ReportDefinition(){
        lazyListHelper = new LazyListHelper();
        lazyListHelper.add(ReportDeliveryDefinition.class,
            new InstantiateFactory<ReportDeliveryDefinition>(ReportDeliveryDefinition.class));
    }

    ////// LOGIC

    public Report createReport() {
        Report report = new Report();
        report.setReportDefinition(this);
        report.setStatus(ReportStatus.PENDING);
        return report;
    }

    public PlannedNotification fetchPlannedNotification(int indexOnScale) {
        if (plannedNotifications == null) return null;

        for (PlannedNotification pn : plannedNotifications) {
            if (pn.getIndexOnTimeScale() == indexOnScale) return pn;
        }
        return null;
    }

    public void addPlannedNotification(PlannedNotification pn) {
        if (pn == null) return;

        if (plannedNotifications == null) {
            plannedNotifications = new ArrayList<PlannedNotification>();
        }
        plannedNotifications.add(pn);
    }


	public void addReportDeliveryDefinition(ReportDeliveryDefinition rdd){
		if(rdd == null) return;
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
	public boolean isFieldMandatory(String fieldPath){
		if(mandatoryFields == null) return false;
		for(ReportMandatoryFieldDefinition mandatoryField : mandatoryFields){
			if(StringUtils.equals(fieldPath, mandatoryField.getFieldPath())){
				return mandatoryField.getMandatory();
			}
		}

		return false;
	}

    ////// BEAN PROPERTIES

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

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
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

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="rct_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<PlannedNotification> getPlannedNotifications() {
        return plannedNotifications;
    }
    public void setPlannedNotifications(List<PlannedNotification> eventList) {
        this.plannedNotifications = eventList;
    }


	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="rct_id")
	@Cascade(value = { CascadeType.ALL})
	public List<ReportDeliveryDefinition> getDeliveryDefinitionsInternal() {
		return lazyListHelper.getInternalList(ReportDeliveryDefinition.class);
	}
	public void setDeliveryDefinitionsInternal(
			List<ReportDeliveryDefinition> deliveryDefinitions) {
		lazyListHelper.setInternalList(ReportDeliveryDefinition.class, deliveryDefinitions);
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="org_id")
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="rct_id")
	@Cascade(value = { CascadeType.ALL})
	public List<ReportMandatoryFieldDefinition> getMandatoryFields() {
		return mandatoryFields;
	}
	public void setMandatoryFields(List<ReportMandatoryFieldDefinition> mandatoryFields) {
		this.mandatoryFields = mandatoryFields;
	}



	////// OBJECT METHODS

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
		result = PRIME * result + getDuration();
		result = PRIME * result + ((getName() == null) ? 0 : getName().hashCode());
		result = PRIME * result + ((getOrganization() == null) ? 0 : getOrganization().hashCode());
		result = PRIME * result + ((getTimeScaleUnitType() == null) ? 0 : getTimeScaleUnitType().hashCode());
		return result;
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

        if (!ComparisonTools.nullSafeEquals(getDescription(), other.getDescription())) {
            if (trace) log.debug("!= descriptions");
            return false;
        }
        if (getDuration() != other.getDuration()) {
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
        if (trace) log.debug("== by properties");
        return true;
    }


}
