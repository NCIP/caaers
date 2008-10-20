package gov.nih.nci.cabig.caaers.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.LazyInitializationException;

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
	
    private static final String JOINS = " join o.adverseEventsInternal as adverseEvents " 
    		        + " join adverseEvents.adverseEventTerm as aeTerm " 
    		        + " join aeTerm.term as ctcTerm "
                    + " join o.reportingPeriod as rp " 
                    + " join rp.assignment as assignment " 
                    + " join assignment.participant as p " 
                    + " join p.identifiers as pIdentifier "
                    + " join assignment.studySite as ss " 
                    + " join ss.study as s " 
                    + " join s.identifiers as sIdentifier";

    private ReportDao reportDao;

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    public Class<ExpeditedAdverseEventReport> domainClass() {
        return ExpeditedAdverseEventReport.class;
    }

    /**
     * Get list of Adverse event reports with supplied criteria.
     * 
     * @param subnames
     *                The name fragments to search on.
     * @param substringMatchProperties
     *                A list of properties of the implementing object which should be matched as
     *                case-insensitive substrings
     * @see findBySubname {@link CaaersDao#findBySubname(String[], String, List, List, List)}
     * @return
     */
    public List<ExpeditedAdverseEventReport> getByCriteria(final String[] subnames,
                    final List<String> substringMatchProperties) {
        return findBySubname(subnames, null, null, substringMatchProperties, null, JOINS);
    }

    /**
     * @param report
     *                Save the Expedited AE report.
     */
    @Transactional(readOnly = false)
    public void save(final ExpeditedAdverseEventReport report) {
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
        
        // delegate to ReportDao to save reports so that it can control transactionality
        for (Report r : report.getReports()) {
            reportDao.save(r);
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
        // delegate to ReportDao to reassociate reports so that it can control transactionality
        for (Report r : report.getReports()) {
            reportDao.reassociate(r);
        }
    }

    /**
     * Search for Adverse event reports with the supplied property list.
     * 
     * @param props
     *                The criteria for searching Adverse Event reports.
     * @return The list of Adverse event reports that match the criteria.
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public List<ExpeditedAdverseEventReport> searchExpeditedReports(final Map props)
                    throws ParseException {

        List<Object> params = new ArrayList<Object>();
        boolean firstClause = true;
        StringBuilder queryBuf = new StringBuilder(" select distinct o from ").append(
                        domainClass().getName()).append(" o ").append(JOINS);

        if (props.get("expeditedDate") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append(" o.detectionDate").append(" = ? ");
            String p = (String) props.get("expeditedDate");
            params.add(stringToDate(p));
            firstClause = false;
        }

        if (props.get("ctcTerm") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("ctcTerm.term").append(") LIKE ?");
            String p = (String) props.get("ctcTerm");
            params.add('%' + p.toLowerCase() + '%');
            firstClause = false;
        }
        if (props.get("ctcCtepCode") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("ctcTerm.ctepCode").append(") LIKE ?");
            String p = (String) props.get("ctcCtepCode");
            params.add('%' + p.toLowerCase() + '%');
            firstClause = false;
        }

        if (props.get("ctcCategory") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("ctcTerm.category.name").append(") LIKE ?");
            String p = (String) props.get("ctcCategory");
            params.add(p.toLowerCase());
            firstClause = false;
        }

        if (props.get("studyIdentifier") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("sIdentifier.value").append(") LIKE ?");
            String p = (String) props.get("studyIdentifier");
            params.add('%' + p.toLowerCase() + '%');
            firstClause = false;
        }
        if (props.get("studyShortTitle") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("s.shortTitle").append(") LIKE ?");
            String p = (String) props.get("studyShortTitle");
            params.add('%' + p.toLowerCase() + '%');
            firstClause = false;
        }
        if (props.get("participantIdentifier") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("pIdentifier.value").append(") LIKE ?");
            String p = (String) props.get("participantIdentifier");
            params.add('%' + p.toLowerCase() + '%');
            firstClause = false;
        }
        if (props.get("participantFirstName") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("p.firstName").append(") LIKE ?");
            String p = (String) props.get("participantFirstName");
            params.add('%' + p.toLowerCase() + '%');
            firstClause = false;
        }
        if (props.get("participantLastName") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("p.lastName").append(") LIKE ?");
            String p = (String) props.get("participantLastName");
            params.add('%' + p.toLowerCase() + '%');
            firstClause = false;
        }
        if (props.get("participantEthnicity") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("p.ethnicity").append(") LIKE ?");
            String p = (String) props.get("participantEthnicity");
            params.add(p.toLowerCase());
            firstClause = false;
        }
        if (props.get("participantGender") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append("LOWER(").append("p.gender").append(") LIKE ?");
            String p = (String) props.get("participantGender");
            params.add(p.toLowerCase());
            firstClause = false;
        }

        if (props.get("participantDateOfBirth") != null) {
            queryBuf.append(firstClause ? " where " : " and ");
            queryBuf.append(" p.dateOfBirth").append(" = ? ");
            String p = (String) props.get("participantDateOfBirth");
            params.add(stringToDate(p));
            firstClause = false;
        }

        log.debug("::: " + queryBuf.toString());
        getHibernateTemplate().setMaxResults(CaaersDao.DEFAULT_MAX_RESULTS_SIZE);
        return getHibernateTemplate().find(queryBuf.toString(), params.toArray());
    }

    /**
     * Set the report DAO.
     * 
     * @param reportDao
     *                The report DAO to be set.
     */
    public void setReportDao(final ReportDao reportDao) {
        this.reportDao = reportDao;
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
