package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;



import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * @author Krikor Krumlian
 */
public class DiseaseTermDao extends CaaersDao<DiseaseTerm> {

	public Class<DiseaseTerm> domainClass() {
        return DiseaseTerm.class;
    }

	@SuppressWarnings("unchecked")
    public List<DiseaseTerm> getAll() {
        return getHibernateTemplate().find("from DiseaseTerm");
    }
    
    @SuppressWarnings("unchecked")
    public List<DiseaseTerm> getByCategoryId(Integer parentId) {
        return getHibernateTemplate().find("from DiseaseTerm dt where dt.category.id =?",
        		new Object[] { parentId });
    }
    
}
