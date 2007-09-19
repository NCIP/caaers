package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rhett Sutphin
 */
@Transactional(readOnly=true)
public class CtcTermDao extends CaaersDao<CtcTerm> {
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("term", "ctepTerm", "select");
    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    @Override
	public Class<CtcTerm> domainClass() {
        return CtcTerm.class;
    }
    @Override
    public CtcTerm getById(int id) {
    	return super.getById(id);
    }
    public List<CtcTerm> getBySubname(String[] subnames, Integer ctcVersionId, Integer ctcCategoryId) {
        List<Object> extraParams = new LinkedList<Object>();
        StringBuilder extraConds = new StringBuilder("");
        if (ctcVersionId != null) {
        	extraConds.append("o.category.ctc.id = ?");
        	extraParams.add(ctcVersionId);
        }
        if (ctcCategoryId != null) {
            extraConds.append(" and o.category.id = ?");
            extraParams.add(ctcCategoryId);
        }
        return findBySubname(subnames, extraConds.toString(), extraParams,
            SUBSTRING_MATCH_PROPERTIES, EXACT_MATCH_PROPERTIES);
    }

    @SuppressWarnings("unchecked")
    public List<CtcTerm> getAll() {
        return getHibernateTemplate().find("from CtcTerm");
    }

}
