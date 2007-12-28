package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.LabTerm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Krikor Krumlian
 */
@Transactional(readOnly=true)
public class LabTermDao extends CaaersDao<LabTerm> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("term");
    private static final List<String> EMPTY_PROPERTIES = Collections.emptyList();
    private static final List<String> EXACT_MATCH_PROPERTIES = Arrays.asList("term", "ctepCode");

    @Override
	public Class<LabTerm> domainClass() {
        return LabTerm.class;
    }
    @Override
    public LabTerm getById(int id) {
    	return super.getById(id);
    }
    public List<LabTerm> getBySubname(String[] subnames, Integer labCategoryId) {
        List<Object> extraParams = new LinkedList<Object>();
        StringBuilder extraConds = new StringBuilder("");
        
        if (labCategoryId != null) {
            extraConds.append(" o.category.id = ? ");
            extraParams.add(labCategoryId);
        }
        return findBySubname(subnames, extraConds.toString(), extraParams,SUBSTRING_MATCH_PROPERTIES, EMPTY_PROPERTIES);
    }
    
    @SuppressWarnings("unchecked")
	public LabTerm getCtcTerm(final String[] subnames) {
		List<LabTerm> labTerms = findBySubname(subnames, SUBSTRING_MATCH_PROPERTIES, EMPTY_PROPERTIES);
		return labTerms.isEmpty() ? null : labTerms.get(0);
	}

    @SuppressWarnings("unchecked")
    public List<LabTerm> getAll() {
        return getHibernateTemplate().find("from LabTerm");
    }

}
