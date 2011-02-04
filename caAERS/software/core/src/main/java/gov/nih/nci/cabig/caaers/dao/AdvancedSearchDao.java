package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The AdvancedSearchDao is used to execute the advance search SQL queries. 
 */
public class AdvancedSearchDao extends CaaersDao{
    /**
     * The domain object this DAO is representing. 
     * @return
     */
	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
	public Class domainClass() {
		// TODO Auto-generated method stub
		return AdvancedSearchDao.class;
	}

    /**
     *
     * {@inheritDoc}
     */
	@Override
	public List<? extends Object> search(final AbstractQuery query){
		return super.search(query);
	}
}
