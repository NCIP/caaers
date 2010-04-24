package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

import java.util.List;

/**
 * @author Ion C. Olaru
 */
public class TerminologyRepositoryImpl implements TerminologyRepository {

    private CtcCategoryDao ctcCategory;
    private CtcTermDao ctcTermDao;

    public List<CtcCategory> getAllCtcCategories(Integer versionID) {
        if (versionID != null)
            return ctcCategory.getByCtcVersion(versionID);
        else
            return ctcCategory.getAll();
    }

    public List<CtcTerm> getCtcTerm(String categoryName, Integer ctcaeVersion, String termName) {
        return getCtcTermDao().getCtcTerm(categoryName, ctcaeVersion, termName);
    }

    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    public CtcCategoryDao getCtcCategory() {
        return ctcCategory;
    }

    public void setCtcCategory(CtcCategoryDao ctcCategory) {
        this.ctcCategory = ctcCategory;
    }
}