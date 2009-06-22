package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ConverterInvestigator;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public class InvestigatorConverterDao extends CaaersDao<ConverterInvestigator>{

	@Override
	public Class<ConverterInvestigator> domainClass() {
		return ConverterInvestigator.class;
	}
	
    @Transactional(readOnly = false)
	public void save(ConverterInvestigator converterInvestigator){
		getHibernateTemplate().saveOrUpdate(converterInvestigator);
	}
	
    @SuppressWarnings("unchecked")
	public ConverterInvestigator getByEmailAddress(String email) {
        List<ConverterInvestigator> results = getHibernateTemplate().find(
                        "from ConverterInvestigator where emailAddress= ?", email);
        return results.size() > 0 ? results.get(0) : null;
    }
	
}
