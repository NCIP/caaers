package gov.nih.nci.cabig.caaers.accesscontrol.filter.query;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will take care of applying the filter based on the indexes. 
 * @author: Biju Joseph
 */
public class QuerySecurityFilterer {
    
    protected final Log log = LogFactory.getLog(QuerySecurityFilterer.class);
    private String indexName;
    private String indexAlias;
    private String entityName;
    private String entityAssociation;
    
    private String[] rolesNotNeedFiltering;


    public void filter(AbstractQuery query){

        boolean shouldFilter = shouldFilter();
        if(log.isInfoEnabled()){
            log.info("Query filtering will " + (shouldFilter ? "" : "not ") + "get applied");
        }
        if(shouldFilter){
            applyFilter(query);
        }
    }

    /**
     * If returns true will apply the filtering. 
     * @return - true - if the logged in user is not "caaers_super_user" or any of the roles mentioned in rolesNotNeedFiltering property. 
     */
    public boolean shouldFilter(){
        List<UserGroupType> rolesToExclude = new ArrayList<UserGroupType>();
        rolesToExclude.add(UserGroupType.caaers_super_user);
        
        if(rolesNotNeedFiltering != null){
            for(String roleName : rolesNotNeedFiltering){
                rolesToExclude.add(UserGroupType.valueOf(roleName));
            }
        }
        
        return !SecurityUtils.checkAuthorization(rolesToExclude.toArray(new UserGroupType[0]));
    }
  
    /**
     * Will take care of applying the filter.
     * Obtain the base query, then replace the base entity and its alias with "index name" and "index alias"
     * then issue couple of joins with the entity.
     * 
     * @param query
     */
    public void applyFilter(AbstractQuery query){
        /*
        Note :-
        "select p from Participant p, ParticipantIndex i where p = i.participant" takes longer time
        compared to "select p from ParticipantIndex i join i.participant p".
        */

        String hql = query.getBaseQueryString();
        String[] hqlElements = StringUtils.split(hql);

        int etIndex = ArrayUtils.indexOf(hqlElements, entityName);
        if(etIndex <=0 && hqlElements.length <= etIndex){
            log.warn("Query [" +query.getClass().getName() + "] is incorrectly framed [" + hql +"]");
            throw new IllegalArgumentException("The query supplied for index filtering " +
                    "[" + query.getClass().getName() +"], do not follow the conventions");
        }
        

        String entityAlias = hqlElements[etIndex + 1];

        //replace the base query after replacing the entity and its alias with equivalent indexName and indexAlias
        hqlElements[etIndex] = indexName;
        hqlElements[etIndex + 1] = indexAlias;
             
        String newQuery = StringUtils.join(hqlElements, " ");
        query.modifyQueryString(newQuery);

        //issue joins with index and extra conditions.
       query.join(getIndexAlias() + "." + getEntityAssociation() + " " +  entityAlias);
       query.andWhere(getIndexAlias() + ".loginId = :loginId");
       query.setParameter("loginId", getLoginId());
    }

    /**
     * Will return the logged-in persons login name. 
     * @return
     */
    public String getLoginId(){
        return SecurityUtils.getUserLoginName();
    }

    /**
     * The association name of the entity from the index.
     * Eg:- ParticipantIndex  to Participant the association is "participant".
     * @return
     */
    public String getEntityAssociation() {
        return entityAssociation;
    }

    public void setEntityAssociation(String entityAssociation) {
        this.entityAssociation = entityAssociation;
    }

    /**
     * The entity name associated with the index
     * @return
     */
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * The alias name to be used in Query for the index
     * @return
     */
    public String getIndexAlias() {
        return indexAlias;
    }

    public void setIndexAlias(String indexAlias) {
        this.indexAlias = indexAlias;
    }

    /**
     * Tht entity name of the index
     * @return
     */
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String[] getRolesNotNeedFiltering() {
        return rolesNotNeedFiltering;
    }

    public void setRolesNotNeedFiltering(String[] rolesNotNeedFiltering) {
        this.rolesNotNeedFiltering = rolesNotNeedFiltering;
    }
}
