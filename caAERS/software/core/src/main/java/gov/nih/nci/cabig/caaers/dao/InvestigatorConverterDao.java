/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ConverterInvestigator;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(readOnly=true)
public class InvestigatorConverterDao extends CaaersDao<ConverterInvestigator>{

	@Override
	 @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
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
