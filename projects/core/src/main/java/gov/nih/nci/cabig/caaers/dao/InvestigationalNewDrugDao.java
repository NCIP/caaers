package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.INDHolder;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public class InvestigationalNewDrugDao extends GridIdentifiableDao<InvestigationalNewDrug>
implements MutableDomainObjectDao<InvestigationalNewDrug>{
	 private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays
		.asList("indNumber");

	 private static final List<String> EXACT_MATCH_PROPERTIES = Collections
		.emptyList();

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
	 String query = "select h.investigationalNewDrug from " + INDHolder.class.getName() + " h " +
	 		" join h.investigationalNewDrug as ind " +
	 		" left outer join h.organization as org" +
	 		" left join h.investigator as inv" +
	 		" where (org.name like :name or inv.firstName like :name) and to_char(ind.indNumber,'0000000000') like :indNo";
	 return getHibernateTemplate().findByNamedParam(query,
			 new String[]{"name","indNo"},
			 new String[]{"%" + MapUtils.getString(paramMap,"sponsorName","%") + "%", "%" + MapUtils.getString(paramMap,"strINDNumber","%") + "%"});
	}

	public List<InvestigationalNewDrug> findByIds(String[] ids){
		return findBySubname(ids, SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
	}


}
