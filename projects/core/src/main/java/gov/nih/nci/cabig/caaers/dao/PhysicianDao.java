package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.Physician;

/**
 * @author Kulasekaran
 */
public class PhysicianDao extends GridIdentifiableDao<Physician> {

    @Override
    public Class<Physician> domainClass() {
        return Physician.class;
    }

    public void save(Physician physician) {
        getHibernateTemplate().saveOrUpdate(physician);
    }
}

