/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.TreatmentAssignmentStudyIntervention;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -3:08 PM
 */
public class TreatmentAssignmentStudyInterventionDao extends CaaersDao<TreatmentAssignmentStudyIntervention> implements MutableDomainObjectDao<TreatmentAssignmentStudyIntervention> {

    @Override
    public Class<TreatmentAssignmentStudyIntervention> domainClass() {
        return TreatmentAssignmentStudyIntervention.class;
    }

    public TreatmentAssignmentStudyIntervention getByGridId(String gridId) {
        return null;
    }

    public List<TreatmentAssignmentStudyIntervention> getAll() {
        return getHibernateTemplate().find("from TreatmentAssignmentStudyIntervention");
    }

    public List<TreatmentAssignmentStudyIntervention> loadAllByClass(Class klass) {
        return getHibernateTemplate().loadAll(klass);
    }

}
