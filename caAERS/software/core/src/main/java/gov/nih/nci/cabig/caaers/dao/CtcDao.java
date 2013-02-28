/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Ctc;

import java.util.List;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements the Data access related operations for the Ctc domain object.
 * 
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public class CtcDao extends CaaersDao<Ctc> {
    /**
     * Get the Class representation of the domain object that this DAO is representing.
     * 
     * @return Class representation of the domain object that this DAO is representing.
     */
	 @Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
    public Class<Ctc> domainClass() {
        return Ctc.class;
    }

    /**
     * Get the list of all CTC terms.
     * 
     * @return return the list of CTC terms.
     */
    @SuppressWarnings("unchecked")
    public List<Ctc> getAll() {
        return getHibernateTemplate().find("from Ctc");
    }

    /**
     * Will retrive the CTC verion 2.
     * @return
     */
    public Ctc getCtcV2() {
        return getByName("2.0");
    }

    @Override
    public Ctc getById(int id) {
        return super.getById(id);    //To change body of overridden methods use File | Settings | File Templates.
    }

    /**
     * Will retrieve the CTC version 3. 
      * @return
     */
    public Ctc getCtcaeV3() {
        return getByName("3.0");
    }

    /**
     * Will retrieve a Ctc object along with its categories.
     * @param id - DB ID of the  Ctc Object to fetch. 
     * @return
     */
    public Ctc getCtcWithCategories(Integer id) {
        String query = "select c from Ctc as c left join fetch c.categories as cats where c.id = ?";
        return (Ctc)getHibernateTemplate().find(query, new Object[] {id}).get(0);
    }
    
    /**
     * Looks for one with the given name and returns null if not found.
     * @param name
     * @return
     */
    public Ctc getByName(String name) {
    	return (Ctc)CollectionUtils.firstElement(getHibernateTemplate().find("select c from Ctc as c where c.name = ?", new Object[] {name}));
    }
    
}
