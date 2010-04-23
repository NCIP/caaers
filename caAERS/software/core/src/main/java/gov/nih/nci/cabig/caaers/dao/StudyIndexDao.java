package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.StudyIndex;

import org.springframework.transaction.annotation.Transactional;

public class StudyIndexDao extends CaaersDao<StudyIndex> {

	@Override
	public Class<StudyIndex> domainClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Transactional(readOnly = false)
    public void save(final StudyIndex studyIndex) {
        getHibernateTemplate().saveOrUpdate(studyIndex);
    }

}
