package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.DiseaseSite;

/*
 * @author kulasekaran
 * 
 */
public class DiseaseSiteDao extends CaaersDao<DiseaseSite> {
    public Class<DiseaseSite> domainClass() {
        return DiseaseSite.class;
    } 
    
    public List<DiseaseSite> getAll() {
        return getHibernateTemplate().find("from DiseaseSite");
    }
    
}

