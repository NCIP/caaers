package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ConverterResearchStaff;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ResearchStaffConverterDao extends CaaersDao<ConverterResearchStaff>{

	@Override
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
	public Class<ConverterResearchStaff> domainClass() {
		return ConverterResearchStaff.class;
	}

    @Transactional(readOnly = false)
	public void save(ConverterResearchStaff converrterResearchStaff){
		getHibernateTemplate().saveOrUpdate(converrterResearchStaff);
	}
	
    @SuppressWarnings("unchecked")
	public ConverterResearchStaff getByEmailAddress(String email) {
        List<ConverterResearchStaff> results = getHibernateTemplate().find(
                        "from ConverterResearchStaff where emailAddress= ?", email);
        return results.size() > 0 ? results.get(0) : null;
    }
}
