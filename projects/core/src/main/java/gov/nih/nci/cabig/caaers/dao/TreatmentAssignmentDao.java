package gov.nih.nci.cabig.caaers.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

public class TreatmentAssignmentDao extends GridIdentifiableDao<TreatmentAssignment> {

	@Override
	public Class<TreatmentAssignment> domainClass() {
		return TreatmentAssignment.class;
	}

	@SuppressWarnings(value="unchecked")
	public List<TreatmentAssignment> getAssignmentsByStudyId(String code, int studyId){
		 return getHibernateTemplate().findByNamedParam("from TreatmentAssignment ta where " +
		 		" ta.code like :tac " +
		 		" and ta.study.id = :studyId",
				 new String[]{"tac", "studyId"},
				 new Object[]{"%" + code + "%",studyId});
	}

}
