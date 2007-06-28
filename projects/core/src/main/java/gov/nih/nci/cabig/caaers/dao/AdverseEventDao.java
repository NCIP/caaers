package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Participant;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhett Sutphin
 */
@Transactional(readOnly=true)
public class AdverseEventDao extends CaaersDao<AdverseEvent> {
    public Class<AdverseEvent> domainClass() {
        return AdverseEvent.class;
    }
    
    private static final String JOINS 
	= " join o.ctcTerm as ctcTerm join ctcTerm.category as ctcCategory  " + 
	 "  join o.report as expeditedReport join expeditedReport.assignment as spa join spa.studySite as studySite join studySite.study as study join study.identifiers as identifier";
    
    public List<AdverseEvent> getByCriteria(String[] subnames, List<String> subStringMatchProperties)
    {
    	return findBySubname(subnames,null,null,subStringMatchProperties,null,JOINS);
    }

    @Transactional(readOnly=false)
    public void save(AdverseEvent event) {
        getHibernateTemplate().saveOrUpdate(event);
    }
}
