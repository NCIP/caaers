package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.ParticipantIndex;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ParticipantIndexDao extends CaaersDao<ParticipantIndex> {

	@Override
	public Class<ParticipantIndex> domainClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Transactional(readOnly = false)
    public void save(final ParticipantIndex participantIndex) {
        getHibernateTemplate().saveOrUpdate(participantIndex);
    }

}
