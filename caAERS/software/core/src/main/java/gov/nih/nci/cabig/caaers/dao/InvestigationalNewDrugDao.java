/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.domain.INDHolder;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.cxf.common.util.CollectionUtils;
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
 * @author Biju Joseph
 * @author Monish Dombla
 * 
 */
@Transactional(readOnly = true)
public class InvestigationalNewDrugDao extends GridIdentifiableDao<InvestigationalNewDrug>implements MutableDomainObjectDao<InvestigationalNewDrug> {

    // queries
    // TODO: Migrate this to query framework.
    private static final String CTEP_IND_QUERY = "select o from " + InvestigationalNewDrug.class.getName() + " o where indNumber = -111";
    private static final String DCP_IND_QUERY = "select o from " + InvestigationalNewDrug.class.getName() + " o where indNumber = -222";
    private static final String FIND_BY_IND_ID_QUERY = "select distinct o from " + InvestigationalNewDrug.class.getName() + " o where str(indNumber) like :indNo";
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
     * @param ids The IND number.
     * @return The list of investigational new drugs.
     */
    @SuppressWarnings("unchecked")
    public List<InvestigationalNewDrug> findByIds(String[] ids) {
        return getHibernateTemplate().findByNamedParam(FIND_BY_IND_ID_QUERY, new String[] { "indNo" }, new String[] { "%" + ids[0] + "%" });

    }

    /**
     * Find INDs by its number and holder name
     * @param number indNumber
     * @param holderName - IND Holder name
     * @return list of found INDs
     */
    public List<InvestigationalNewDrug> findByNumberAndHolderName(String number, String holderName) {
        HQLQuery q = new HQLQuery("SELECT holder.investigationalNewDrug FROM gov.nih.nci.cabig.caaers.domain.OrganizationHeldIND holder");
        q.join("holder.investigationalNewDrug as ind");
        q.leftJoin("holder.organization as org");
        q.andWhere("org.name = :name and str(ind.indNumber) = :indNumber");
        return getHibernateTemplate().findByNamedParam(q.getQueryString(), new String[] {"name", "indNumber"}, new String[] {holderName, number});
    }

    
    public List<InvestigationalNewDrug> findOrganizationHeldIND(String number, String nciCode){
        HQLQuery q = new HQLQuery("SELECT holder.investigationalNewDrug FROM gov.nih.nci.cabig.caaers.domain.OrganizationHeldIND holder");
        q.join("holder.investigationalNewDrug as ind");
        q.leftJoin("holder.organization as org");
        q.andWhere("org.nciInstituteCode = :nciCode and str(ind.indNumber) = :indNumber");
        return getHibernateTemplate().findByNamedParam(q.getQueryString(), new String[] {"nciCode", "indNumber"}, new String[] {nciCode, number});
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
    
    /**
     * Get the list of all InvestigationalNewDrugs.
     * 
     * @return return the list of InvestigationalNewDrugs.
     */
    @SuppressWarnings("unchecked")
    public List<InvestigationalNewDrug> getAll() {
    	List<InvestigationalNewDrug> indList = getHibernateTemplate().find("from InvestigationalNewDrug");
    	return indList;
    }
}
