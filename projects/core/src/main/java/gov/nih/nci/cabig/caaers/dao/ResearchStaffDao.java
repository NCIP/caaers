package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.service.UserService;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

/**
 * @author Kulasekaran
 */
@Transactional(readOnly = true)
public class ResearchStaffDao extends GridIdentifiableDao<ResearchStaff> implements
        MutableDomainObjectDao<ResearchStaff> {

    private UserProvisioningManager userProvisioningManager;
    private static final List<String> SUBSTRING_MATCH_PROPERTIES = Arrays.asList("firstName", "lastName");

    private static final List<String> EXACT_MATCH_PROPERTIES = Collections.emptyList();

    private static final List<Object> EXTRA_PARAMS = Collections.emptyList();


    private UserService userService;

    public List<ResearchStaff> getAll() {
        ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
        return searchResearchStaff(researchStaffQuery);
    }

    @Override
    public Class<ResearchStaff> domainClass() {
        return ResearchStaff.class;
    }

    @Transactional(readOnly = false)
    public void save(final ResearchStaff researchStaff) {
        userService.createOrUpdateCSMUserAndGroupsForResearchStaff(researchStaff);
        getHibernateTemplate().saveOrUpdate(researchStaff);
    }

    public ResearchStaff getById(int id) {
        ResearchStaff researchStaff = super.getById(id);
        researchStaff = initialize(researchStaff);
        return researchStaff;
    }

    public ResearchStaff initialize(final ResearchStaff researchStaff) {

        try {
            List<Group> groups = new ArrayList(userProvisioningManager.getGroups(researchStaff.getLoginId()));

            for (Group group : groups) {

                UserGroupType userGroupType = UserGroupType.getByCode(Long.valueOf(group.getGroupId()).intValue());

                if (userGroupType != null) {
                    researchStaff.addUserGroupType(userGroupType);
                }
            }


        } catch (CSObjectNotFoundException e) {

        }

        return researchStaff;
    }

    @SuppressWarnings({"unchecked"})
    public List<ResearchStaff> searchResearchStaff(final ResearchStaffQuery query) {
        String queryString = query.getQueryString();
        log.debug("::: " + queryString.toString());
        return (List<ResearchStaff>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                org.hibernate.Query hiberanteQuery = session.createQuery(query.getQueryString());
                Map<String, Object> queryParameterMap = query.getParameterMap();
                for (String key : queryParameterMap.keySet()) {
                    Object value = queryParameterMap.get(key);
                    hiberanteQuery.setParameter(key, value);

                }
                return hiberanteQuery.list();
            }

        });

    }

    public List<ResearchStaff> getBySubnames(final String[] subnames, final int site) {

        return findBySubname(subnames, "o.organization.id = '" + site + "'", EXTRA_PARAMS, SUBSTRING_MATCH_PROPERTIES,
                EXACT_MATCH_PROPERTIES);
    }

    @Required
    public void setUserProvisioningManager(UserProvisioningManager userProvisioningManager) {
        this.userProvisioningManager = userProvisioningManager;
    }

    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}