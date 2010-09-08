package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AdvancedSearchDao extends CaaersDao{

	@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
	public Class domainClass() {
		// TODO Auto-generated method stub
		return AdvancedSearchDao.class;
	}
	@Override
	public List<? extends Object> search(final AbstractQuery query){
		return super.search(query);
	}
}
