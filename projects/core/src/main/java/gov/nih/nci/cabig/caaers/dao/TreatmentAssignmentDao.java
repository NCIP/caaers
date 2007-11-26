package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

import java.util.List;

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
	
	
	@SuppressWarnings(value="unchecked")
	public TreatmentAssignment getAssignmentsByStudyIdExactMatch(String code, int studyId){
		 List<TreatmentAssignment> tas =  getHibernateTemplate().findByNamedParam("from TreatmentAssignment ta where " +
		 		" ta.code like :tac " +
		 		" and ta.study.id = :studyId",
				 new String[]{"tac", "studyId"},
				 new Object[]{ code ,studyId});
		 return tas.size() > 0 ? tas.get(0) : null;
	}

	@SuppressWarnings(value="unchecked")
	public List<TreatmentAssignment> getAll(){
		 return getHibernateTemplate().find("from TreatmentAssignment ta order by ta.id");
	}
	
}
