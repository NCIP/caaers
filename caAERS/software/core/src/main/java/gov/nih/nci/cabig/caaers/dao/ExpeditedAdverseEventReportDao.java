package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LazyInitializationException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the ExpeditedAdverseEventReport
 * domain object.
 * 
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
@Transactional(readOnly = true)
public class ExpeditedAdverseEventReportDao extends
                GridIdentifiableDao<ExpeditedAdverseEventReport> implements
                MutableDomainObjectDao<ExpeditedAdverseEventReport> {
	
	protected final Log log = LogFactory.getLog(getClass());


    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<ExpeditedAdverseEventReport> domainClass() {
        return ExpeditedAdverseEventReport.class;
    }


    /**
     * @param report
     *                Save the Expedited AE report.
     */
    @Transactional(readOnly = false)
    public void save(final ExpeditedAdverseEventReport report) {
    	saveOrUpdateExpeditedReport(report);
    }
    
    /**
     * This method is invoked from save or modifyOrSaveReviewStatusAndComments methods
     * @param report
     */
    private void saveOrUpdateExpeditedReport(final ExpeditedAdverseEventReport report){
    	log.debug("Saving ExpeditedAdverseEventReport..");
        getHibernateTemplate().saveOrUpdate(report);
        for (AdverseEvent ae : report.getAdverseEvents()) {
            getHibernateTemplate().saveOrUpdate(ae);
        }
        try {
            if (report.getReporter() != null && report.getReporter().isSavable()) {
                getHibernateTemplate().saveOrUpdate(report.getReporter());
            } else {
                log.debug("Reporter not savable; skipping cascade");
            }
        } catch (LazyInitializationException lie) {
            log.debug("Reporter not initialized, skipping cascade", lie);
            lie.printStackTrace();
        }
        try {
            if (report.getPhysician() != null && report.getPhysician().isSavable()) {
                getHibernateTemplate().saveOrUpdate(report.getPhysician());
            } else {
                log.debug("Physican not savable; skipping cascade");
            }
        } catch (LazyInitializationException lie) {
            log.debug("Physician not initialized, skipping cascade", lie);
            lie.printStackTrace();
        }
    }
    
    /**
     * This method will reassociate the domain object to hibernate session. With a lock mode none.
     * 
     * @param report -
     *                the domain object instance that is to be reassociated.
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void reassociate(final ExpeditedAdverseEventReport report) {
    	log.debug("Reassociating ExpeditedAdverseEventReport...");
        super.reassociate(report);
        
        if (report.getReporter() == null || report.getReporter().isTransient()) {
            log.debug("Reporter unsaved; skipping reassociate cascade");
        } else {
           getHibernateTemplate().lock(report.getReporter(), LockMode.NONE);
        }
        if (report.getPhysician() == null || report.getPhysician().isTransient()) {
            log.debug("Physican unsaved; skipping reassociate cascade");
        } else {
            getHibernateTemplate().lock(report.getPhysician(), LockMode.NONE);
        }
    }




    /**
     * When we delte an element which has been attributed, the attribution also needs to be deleted.
     * @param o
     */
    public boolean cascaeDeleteToAttributions(DomainObject o, ExpeditedAdverseEventReport aeReport){
    	for(AdverseEvent ae : aeReport.getAdverseEvents()){
    		if(o instanceof RadiationIntervention){
    			deleteAttribution(o, ae.getRadiationAttributions(), ae);
        	}else if(o instanceof MedicalDevice) {
        		deleteAttribution(o, ae.getDeviceAttributions(), ae);
        	}else if(o instanceof SurgeryIntervention) {
        		deleteAttribution(o, ae.getSurgeryAttributions(), ae);
        	}else if(o instanceof CourseAgent) {
        		deleteAttribution(o, ae.getCourseAgentAttributions(), ae);
        	}else if(o instanceof ConcomitantMedication) {
        		deleteAttribution(o, ae.getConcomitantMedicationAttributions(), ae);
        	}else if(o instanceof OtherCause) {
        		deleteAttribution(o, ae.getOtherCauseAttributions(), ae);
        	}else if(o instanceof DiseaseHistory) {
        		deleteAttribution(o, ae.getDiseaseAttributions(), ae);
        	}
    	}
    	return true;
    }

    public void deleteAttribution(DomainObject obj, List<? extends AdverseEventAttribution<? extends DomainObject>> attributions, AdverseEvent ae){
        if(obj == null || obj.getId() == null) return;
    	AdverseEventAttribution<? extends DomainObject> unwantedAttribution = null;
    	for(AdverseEventAttribution<? extends DomainObject> attribution : attributions){
    		if(obj.getId().equals(attribution.getCause().getId())) {
    			unwantedAttribution = attribution;
    			break;
    		}

    	}
    	if(unwantedAttribution != null){
    		attributions.remove(unwantedAttribution);
    		unwantedAttribution.setAdverseEvent(null);
    	}
    }

}
