package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
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
public class DiseaseCategoryDao extends CaaersDao<DiseaseCategory> {
    
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("name");
    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

	public Class<DiseaseCategory> domainClass() {
        return DiseaseCategory.class;
    }

	@SuppressWarnings("unchecked")
    public List<DiseaseCategory> getAll() {
        return getHibernateTemplate().find("from DiseaseCategory");
    }
    
    @SuppressWarnings("unchecked")
    public List<DiseaseCategory> getByParentId(Integer parentId) {
        return getHibernateTemplate().find("from DiseaseCategory dc where dc.parentCategory.id =?",
        		new Object[] { parentId });
    }
    
    public List<DiseaseCategory> getBySubname(String[] subnames, Integer diseaseCategoryId) {
        List<Object> extraParams = new LinkedList<Object>();
        StringBuilder extraConds = new StringBuilder("");
        if (diseaseCategoryId != null) {
            extraConds.append(" o.parentCategory.id = ?");
            extraParams.add(diseaseCategoryId);
        }
        else {
        	extraConds.append(" o.parentCategory is null");
        }
        return findBySubname(subnames, extraConds.toString(), extraParams,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }
    
}
