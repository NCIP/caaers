package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.dao.query.StudySitesQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the StudySite domain object.
 * 
 * @author Padmaja Vedula
 */
@Transactional(readOnly = true)
public class StudySiteDao extends CaaersDao<StudySite> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<StudySite> domainClass() {
        return StudySite.class;
    }

    /*
     * @See ParticipantService
     */
    public StudySite matchByStudyAndOrg(final String organizationName,
                    final String identifierValue, final String identifierType) {
 
        String joins = " join o.study as study join study.identifiers as identifier ";

        List<Object> params = new ArrayList<Object>();
        StringBuilder queryBuf = new StringBuilder(" select distinct o from ").append(
                        domainClass().getName()).append(" o ").append(joins);

        queryBuf.append(" where ");
        queryBuf.append("LOWER(").append("identifier.value").append(") = ? ");
        params.add(identifierValue.toLowerCase());

        queryBuf.append(" and ");
        queryBuf.append("LOWER(").append("identifier.type").append(") = ? ");
        params.add(identifierType.toLowerCase());

        queryBuf.append(" and ");
        queryBuf.append("LOWER(").append("o.organization.name").append(") = ? ");
        params.add(organizationName.toLowerCase());

        log.debug("matchByStudyAndOrg : " + queryBuf.toString());
        getHibernateTemplate().setMaxResults(5);
        List<StudySite> studySites = getHibernateTemplate().find(queryBuf.toString(),
                        params.toArray());
        getHibernateTemplate().setMaxResults(DEFAULT_MAX_RESULTS_SIZE);
        return studySites.size() == 1 ? studySites.get(0) : null;
    }

    public StudySite matchByStudyAndOrgNciId(final String organizationNciId,
            final String identifierValue, final String identifierType) {
    	
		String joins = " join o.study as study join study.identifiers as identifier ";
		
		List<Object> params = new ArrayList<Object>();
		StringBuilder queryBuf = new StringBuilder(" select distinct o from ").append(domainClass().getName()).append(" o ").append(joins);
		
		queryBuf.append(" where ");
		queryBuf.append("LOWER(").append("identifier.value").append(") = ? ");
		params.add(identifierValue.toLowerCase());
		
		queryBuf.append(" and ");
		queryBuf.append("LOWER(").append("identifier.type").append(") = ? ");
		params.add(identifierType.toLowerCase());
		
		queryBuf.append(" and ");
		queryBuf.append("LOWER(").append("o.organization.nciInstituteCode").append(") = ? ");
		params.add(organizationNciId.toLowerCase());
		
		log.debug("matchByStudyAndOrgNciId : " + queryBuf.toString());
		getHibernateTemplate().setMaxResults(5);
		List<StudySite> studySites = getHibernateTemplate().find(queryBuf.toString(), params.toArray());
		getHibernateTemplate().setMaxResults(DEFAULT_MAX_RESULTS_SIZE);
		return studySites.size() == 1 ? studySites.get(0) : null;
}
    
    /*
     * @See ParticipantService
     */
    public StudySite findByStudyAndOrganization(final Integer studyId, final Integer orgId) {
        StringBuilder queryBuf = new StringBuilder(" select distinct ss from StudySite ss " + "where ss.organization.id=? and ss.study.id=? ");
        log.debug("findByStudyAndOrganization : " + queryBuf.toString());
        getHibernateTemplate().setMaxResults(5);
        List<StudySite> studySites = getHibernateTemplate().find(queryBuf.toString(), new Object[]{orgId,studyId});
        getHibernateTemplate().setMaxResults(DEFAULT_MAX_RESULTS_SIZE);
        return studySites.size() == 1 ? studySites.get(0) : null;
    }

    public List<String> getSitesOfCoordinatedStudiesByOrganizationCodes(List orgCodes) {
        StudySitesQuery query = new StudySitesQuery("SELECT o.nciInstituteCode FROM StudyCoordinatingCenter so");
        query.joinStudies();
        query.joinStudyOrganizations();
        query.joinOrganizations();
        query.filterBySuperOrganizationCode(orgCodes);
        List<String> coordinatedOrganizationsCodes = this.getHibernateTemplate().find(query.getQueryString());
        return coordinatedOrganizationsCodes;
    }

}
