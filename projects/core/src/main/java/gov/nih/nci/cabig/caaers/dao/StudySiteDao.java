package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudySite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Padmaja Vedula
 */
@Transactional(readOnly=true)
public class StudySiteDao extends CaaersDao<StudySite> {
    public Class<StudySite> domainClass() {
        return StudySite.class;
    }
    
    /*
     * @See ParticipantService
     */
    public StudySite matchByStudyAndOrg(final String organizationName , final String identifierValue) {

		String joins = " join o.study as study join study.identifiers as identifier ";

		List<Object> params = new ArrayList<Object>();
		StringBuilder queryBuf = new StringBuilder(" select distinct o from ").append(domainClass().getName()).append(
				" o ").append(joins);

		queryBuf.append(" where ");
		queryBuf.append("LOWER(").append("identifier.value").append(") LIKE ? ");
		params.add( identifierValue.toLowerCase());

		queryBuf.append(" and ");
		queryBuf.append("LOWER(").append("o.organization.name").append(") LIKE ? ");
		params.add(organizationName.toLowerCase());

		log.debug("matchStudyByParticipant : " + queryBuf.toString());
		getHibernateTemplate().setMaxResults(5);
		List<StudySite> studySites = getHibernateTemplate().find(queryBuf.toString(), params.toArray());
		return studySites.size()== 1  ? studySites.get(0) : null;
	}
}
