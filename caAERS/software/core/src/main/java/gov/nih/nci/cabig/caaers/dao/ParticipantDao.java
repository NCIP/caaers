package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the Participant domain object.
 *
 * @author Rhett Sutphin
 * @author Biju Joseph
 * @author Ion C. Olaru
 */
@Transactional(readOnly = true)
public class ParticipantDao extends GridIdentifiableDao<Participant> implements MutableDomainObjectDao<Participant> {
    // these are for getBySubnames
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("firstName", "lastName");
    private static final List<String> EXACT_MATCH_PROPERTIES = Arrays.asList("institutionalPatientNumber");
    private static final List<String> EXACT_MATCH_UNIQUE_PROPERTIES = Arrays.asList("firstName","lastName");
    private static final List<String> EMPTY_PROPERTIES = Collections.emptyList();

    private static final String JOINS = "join o.identifiers as identifier join o.assignments as spa join spa.studySite as ss join ss.study as s join s.identifiers as sIdentifier ";

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     *
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<Participant> domainClass() {
        return Participant.class;
    }

    /**
     * Save or update the participant in the db.
     *
     * @param The participant.
     */
    @Transactional(readOnly = false)
    public void save(final Participant participant) {
        getHibernateTemplate().saveOrUpdate(participant);
    }

    /**
     * Get the list of all participants.
     *
     * @return return the list of participants.
     */
    @SuppressWarnings("unchecked")
    public List<Participant> getAll() {
        return getHibernateTemplate().find("from Participant p order by p.lastName, p.firstName");
    }

    /**
     * @param subnames a set of substrings to match
     * @return a list of participants such that each entry in <code>subnames</code> is a
     *         case-insensitive substring match of the participant's name or other identifier
     */
    @SuppressWarnings("unchecked")
    public List<Participant> getBySubnames(final String[] subnames) {
        return findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }

    /**
     * Gets the participant by id. This initializes the participant and loads all the objects.
     *
     * @param identifier the id.
     * @return the participant by id.
     */
    public Participant getByIdentifier(final Identifier identifier) {
        return findByIdentifier(identifier);
    }

    /**
     * This method returns a Fully loaded Participant.
     *
     * @param identifier
     * @return
     */
    public Participant getParticipantDesignByIdentifier(final Identifier identifier) {
        Participant participant = getByIdentifier(identifier);
        if (participant != null) {
            initialize(participant);
        }
        return participant;
    }

    /**
     * Return the list of Primaty Identifiers for a given Site
     *
     * @param siteID The site ID for which to return the list of identifiers
     * @return List<Identidier>
     */
    public List<Identifier> getSitePrimaryIdentifiers(int siteID) {
        return getHibernateTemplate().find("FROM Identifier idt WHERE idt.organization.id = ? AND idt.primaryIndicator = ?", new Object[]{siteID, true});
    }

  

    /**
     * Gets the participant by id. This initialize the participant and load all the objects.
     *
     * @param id the id
     * @return the participant by id
     */
    public Participant getParticipantById(final int id) {
        Participant participant = (Participant) getHibernateTemplate().get(domainClass(), id);
        initialize(participant);

        return participant;
    }

    /**
     * This will initialize a lazy collection, consisting of participant objects.
     *
     * @param participant The participant object.
     */

    public Participant initialize(final Participant participant) {
        HibernateTemplate ht = getHibernateTemplate();
        ht.initialize(participant.getIdentifiers());
        ht.initialize(participant.getAssignments());
        ht.initialize(participant.getStudies());

        return participant;
    }

    /**
     * Search for participants using query.
     *
     * @param query The query used to search for participants
     * @return The list of participants.
     */
    @SuppressWarnings("unchecked")
    public List<Participant> searchParticipant(final ParticipantQuery query) {
        String queryString = query.getQueryString();
        log.debug("::: " + queryString.toString());
        return (List<Participant>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
                Map<String, Object> queryParameterMap = query.getParameterMap();
                for (String key : queryParameterMap.keySet()) {
                    Object value = queryParameterMap.get(key);
                    hiberanteQuery.setParameter(key, value);

                }
                return hiberanteQuery.list();
            }

        });
    }
    
    /**
     * This method will reassociate the domain object to hibernate session. With a lock mode none.
     *
     * @param o -
     *          the domain object instance that is to be reassociated.
     */
    public void reassociateUsingLock(Participant o) {
        getHibernateTemplate().lock(o, LockMode.NONE);
    }

    /**
     * Delete the specified participant.
     *
     * @param p The participant.
     */
    @Transactional(readOnly = false)
    public void delete(Participant p) {
        getHibernateTemplate().delete(p);
    }

}
