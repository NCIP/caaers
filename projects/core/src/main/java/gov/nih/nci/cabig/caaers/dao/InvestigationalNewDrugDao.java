package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.INDHolder;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public class InvestigationalNewDrugDao extends GridIdentifiableDao<InvestigationalNewDrug>
implements MutableDomainObjectDao<InvestigationalNewDrug>{

	//queries
	private static final String CTEP_IND_QUERY = "select o from "
		+ InvestigationalNewDrug.class.getName()
		+" o where indNumber = -111";

	private static final String FIND_BY_IND_ID_QUERY =  "select distinct o from "
		+ InvestigationalNewDrug.class.getName()
		+ " o where str(indNumber) like :indNo";

	private static final String SEARCH_IND_QUERY =  "select h.investigationalNewDrug from "
		+ INDHolder.class.getName() + " h "
		+ " join h.investigationalNewDrug as ind "
		+ " left outer join h.organization as org "
		+ " left join h.investigator as inv"
		+ " where (org.name like :name or inv.firstName like :name) and str(ind.indNumber) like :indNo";

	@Override
	public Class<InvestigationalNewDrug> domainClass() {
		return InvestigationalNewDrug.class;
	}

	@Transactional(readOnly=false)
	public void save(InvestigationalNewDrug iNewDrug) {
		getHibernateTemplate().saveOrUpdate(iNewDrug);
	}

	@SuppressWarnings("unchecked")
	public List<InvestigationalNewDrug> searchInvestigationalNewDrugs(Map<String, String> paramMap){
	 return getHibernateTemplate().findByNamedParam(SEARCH_IND_QUERY,
			 new String[]{"name","indNo"},
			 new String[]{"%" + MapUtils.getString(paramMap,"sponsorName","%") + "%", "%" + MapUtils.getString(paramMap,"strINDNumber","%") + "%"});
	}

	@SuppressWarnings("unchecked")
	public List<InvestigationalNewDrug> findByIds(String[] ids){
		 return getHibernateTemplate().findByNamedParam(FIND_BY_IND_ID_QUERY,
				 new String[]{"indNo"},
				 new String[]{"%" + ids[0] + "%" });

	}

	public InvestigationalNewDrug fetchCtepInd(){
		return (InvestigationalNewDrug) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query=session.createQuery(CTEP_IND_QUERY);
				return query.uniqueResult();
			};

		});
	}
}
