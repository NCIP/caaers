package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;

import java.util.List;

/**
 * @author Ion C. Olaru
 */
public class TerminologyRepositoryImpl implements TerminologyRepository {

    private CtcCategoryDao ctcCategory;

    public List<CtcCategory> getAllCtcCategories(Integer versionID) {
        if (versionID != null)
            return ctcCategory.getByCtcVersion(versionID);
        else
            return ctcCategory.getAll();
    }

    public CtcCategoryDao getCtcCategory() {
        return ctcCategory;
    }

    public void setCtcCategory(CtcCategoryDao ctcCategory) {
        this.ctcCategory = ctcCategory;
    }
}