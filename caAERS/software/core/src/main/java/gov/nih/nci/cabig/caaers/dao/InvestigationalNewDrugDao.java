package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.INDHolder;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This class implements the Data access related operations for the InvestigationalNewDrug domain
 * object.
 * 
 */
@Transactional(readOnly = true)
public class InvestigationalNewDrugDao extends GridIdentifiableDao<InvestigationalNewDrug>
                implements MutableDomainObjectDao<InvestigationalNewDrug> {

    // queries
    // TODO: Migrate this to query framework.
    private static final String CTEP_IND_QUERY = "select o from "
                    + InvestigationalNewDrug.class.getName() + " o where indNumber = -111";

    private static final String DCP_IND_QUERY = "select o from "
                    + InvestigationalNewDrug.class.getName() + " o where indNumber = -222";

    private static final String FIND_BY_IND_ID_QUERY = "select distinct o from "
                    + InvestigationalNewDrug.class.getName()
                    + " o where str(indNumber) like :indNo";

    private static final String SEARCH_IND_QUERY = "select h.investigationalNewDrug from "
                    + INDHolder.class.getName()
                    + " h "
                    + " join h.investigationalNewDrug as ind "
                    + " left outer join h.organization as org "
                    + " left join h.investigator as inv"
                    + " where (org.name like :name or inv.firstName like :name) and str(ind.indNumber) like :indNo";

    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
    @Override
    @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<InvestigationalNewDrug> domainClass() {
        return InvestigationalNewDrug.class;
    }

    /**
     * Save the investigational new drug.
     * 
     * @param iNewDrug
     *                The investigational new drug.
     */
    @Transactional(readOnly = false)
    public void save(InvestigationalNewDrug iNewDrug) {
        getHibernateTemplate().saveOrUpdate(iNewDrug);
    }

    /**
     * Get the list of investigational new drugs which match the input parameters.
     * 
     * @param paramMap
     *                The input parameters.
     * @return The list of investigational new drugs.
     */
    @SuppressWarnings("unchecked")
    public List<InvestigationalNewDrug> searchInvestigationalNewDrugs(Map<String, String> paramMap) {
        return getHibernateTemplate().findByNamedParam(
                        SEARCH_IND_QUERY,
                        new String[] { "name", "indNo" },
                        new String[] {
                                "%" + MapUtils.getString(paramMap, "sponsorName", "%") + "%",
                                "%" + MapUtils.getString(paramMap, "strINDNumber", "%") + "%" });
    }

    /**
     * Get the list of investigational new drugs which match the IND number.
     * 
     * @param ids
     *                The IND number.
     * @return The list of investigational new drugs.
     */
    @SuppressWarnings("unchecked")
    public List<InvestigationalNewDrug> findByIds(String[] ids) {
        return getHibernateTemplate().findByNamedParam(FIND_BY_IND_ID_QUERY,
                        new String[] { "indNo" }, new String[] { "%" + ids[0] + "%" });

    }

    /**
     * TODO
     * 
     * @return
     */
    public InvestigationalNewDrug fetchCtepInd() {
        return (InvestigationalNewDrug) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(CTEP_IND_QUERY);
                return query.uniqueResult();
            };

        });
    }

    /**
     * TODO
     * 
     * @return
     */
    public InvestigationalNewDrug fetchDcpInd() {
        return (InvestigationalNewDrug) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(DCP_IND_QUERY);
                return query.uniqueResult();
            };

        });
    }
}
