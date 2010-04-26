package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

public class StudyQueryFilterByLoginIdAspect extends AbstractFilterByLoginIdAspect{

	/*@Override
	public String getInQuery() {
		// TODO Auto-generated method stub
		return "study.id IN (:ids)";
	}*/

	@Override
	public String getJoinQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}
