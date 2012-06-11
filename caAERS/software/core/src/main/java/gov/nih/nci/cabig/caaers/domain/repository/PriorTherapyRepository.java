package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.query.PriorTherapyQuery;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;

import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 5/16/12 -1:32 PM
 */
public class PriorTherapyRepository {

    PriorTherapyDao priorTherapyDao;

    public List<PriorTherapy> getAll(boolean excludeNoPriorTherapy, boolean activeOnly) {
        PriorTherapyQuery q = new PriorTherapyQuery();
        if (excludeNoPriorTherapy) q.filterOutNoPriorTherapy();
        if (activeOnly) q.filterByRetiredStatus(false);
        q.orderBy("text");
        return (List<PriorTherapy>)priorTherapyDao.search(q);
    }

    public PriorTherapyDao getPriorTherapyDao() {
        return priorTherapyDao;
    }

    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
        this.priorTherapyDao = priorTherapyDao;
    }
}
