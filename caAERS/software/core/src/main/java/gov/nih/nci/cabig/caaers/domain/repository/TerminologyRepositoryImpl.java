/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

import java.util.List;

 
/**
 * The Class TerminologyRepositoryImpl.
 *
 * @author Ion C. Olaru
 */
public class TerminologyRepositoryImpl implements TerminologyRepository {

    /** The ctc category. */
    private CtcCategoryDao ctcCategory;
    
    /** The ctc term dao. */
    private CtcTermDao ctcTermDao;

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.TerminologyRepository#getAllCtcCategories(java.lang.Integer)
     */
    public List<CtcCategory> getAllCtcCategories(Integer versionID) {
        if (versionID != null)
            return ctcCategory.getByCtcVersion(versionID);
        else
            return ctcCategory.getAll();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.TerminologyRepository#getCtcTerm(java.lang.String, java.lang.Integer, java.lang.String)
     */
    public List<CtcTerm> getCtcTerm(String categoryName, Integer ctcaeVersion, String termName) {
        return getCtcTermDao().getCtcTerm(categoryName, ctcaeVersion, termName);
    }

    /**
     * Gets the ctc term dao.
     *
     * @return the ctc term dao
     */
    public CtcTermDao getCtcTermDao() {
        return ctcTermDao;
    }

    /**
     * Sets the ctc term dao.
     *
     * @param ctcTermDao the new ctc term dao
     */
    public void setCtcTermDao(CtcTermDao ctcTermDao) {
        this.ctcTermDao = ctcTermDao;
    }

    /**
     * Gets the ctc category.
     *
     * @return the ctc category
     */
    public CtcCategoryDao getCtcCategory() {
        return ctcCategory;
    }

    /**
     * Sets the ctc category.
     *
     * @param ctcCategory the new ctc category
     */
    public void setCtcCategory(CtcCategoryDao ctcCategory) {
        this.ctcCategory = ctcCategory;
    }
}
