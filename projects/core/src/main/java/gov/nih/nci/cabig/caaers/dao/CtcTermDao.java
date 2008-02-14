package gov.nih.nci.cabig.caaers.dao;

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
    private static final List<String> EMPTY_PROPERTIES = Collections.emptyList();
    private static final List<String> EXACT_MATCH_PROPERTIES = Arrays.asList("term", "ctepCode");

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
            SUBSTRING_MATCH_PROPERTIES, EMPTY_PROPERTIES);
    }
    
    public CtcTerm getCtcTerm(String[] subnames, Integer ctcVersionId, Integer ctcCategoryId) {
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
        List<CtcTerm> ctcTerms =  findBySubname(subnames, extraConds.toString(), extraParams,EMPTY_PROPERTIES, EXACT_MATCH_PROPERTIES);
        System.out.println("Size of ctcTerms returned : " + ctcTerms.size() + subnames.toString() + " : " + ctcVersionId.toString() );
        return ctcTerms.isEmpty() ? null : ctcTerms.get(0);
    }
    
    @SuppressWarnings("unchecked")
	public CtcTerm getCtcTerm(final String[] subnames) {
		List<CtcTerm> ctcTerms = findBySubname(subnames, EMPTY_PROPERTIES, EXACT_MATCH_PROPERTIES);
		return ctcTerms.isEmpty() ? null : ctcTerms.get(0);
	}

    @SuppressWarnings("unchecked")
    public List<CtcTerm> getAll() {
        return getHibernateTemplate().find("from CtcTerm");
    }

}
