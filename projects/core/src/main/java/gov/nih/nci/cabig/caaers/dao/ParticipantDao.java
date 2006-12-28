package gov.nih.nci.cabig.caaers.dao;

//import edu.nwu.bioinformatics.commons.CollectionUtils;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import gov.nih.nci.cabig.caaers.domain.Participant;
//import edu.northwestern.bioinformatics.studycalendar.domain.Site;
//import edu.northwestern.bioinformatics.studycalendar.domain.Study;
//import edu.northwestern.bioinformatics.studycalendar.domain.StudyParticipantAssignment;

//import javax.servlet.http.HttpSession;


public class ParticipantDao extends CaaersDao<Participant> {
    public Class<Participant> domainClass() {
        return Participant.class;
    }

    public void save(Participant participant) {
        getHibernateTemplate().saveOrUpdate(participant);
    }

    public List<Participant> getAll() {
        return getHibernateTemplate().find("from Participant p order by p.lastName, p.firstName");
    }
    /*
    public StudyParticipantAssignment getAssignment(Participant participant, Study study, Site site) {
        return (StudyParticipantAssignment) CollectionUtils.firstElement(
            getHibernateTemplate().find(
                "from StudyParticipantAssignment a where a.participant = ? and a.studySite.site = ? and a.studySite.study = ?",
                new Object[] { participant, site, study }
            )
        );
    }*/
}

