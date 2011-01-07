package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import org.acegisecurity.Authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author: Biju Joseph
 */
public class CaaersSecurityFacadeTestAdapter implements CaaersSecurityFacade {

    public boolean checkAuthorization(Authentication auth, String objectId, String privilege) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void provisionUser(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public gov.nih.nci.security.authorization.domainobjects.User createOrUpdateCSMUser(User user, String changeURL) {
        //To change body of implemented methods use File | Settings | File Templates.
        return null;
    }

    public boolean checkAuthorization(Authentication auth, String objectPrivilege) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> getAccessibleProtectionElements(String loginId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<IndexEntry> getAccessibleStudyIds(String loginId) {
        return new ArrayList<IndexEntry>();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<IndexEntry> getAccessibleOrganizationIds(String loginId) {
        return new ArrayList<IndexEntry>();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<String> getRoles(String userLoginName, Organization org) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<String> getRoles(String userLoginName, Study study) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Set<ProtectionGroupRoleContext> getProtectionGroupRoleContextForUser(String loginId) throws CSObjectNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Set<ProtectionElementPrivilegeContext> getProtectionElementPrivilegeContextForUser(String loginId) throws CSObjectNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void clearUserCache(String userName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void provisionStudies(StudyPersonnel studyPersonnel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void provisionStudies(StudyInvestigator studyInvestigator) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public gov.nih.nci.security.authorization.domainobjects.User createOrUpdateUser(User user, String changeURL) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void provisionRoleMemberships(gov.nih.nci.security.authorization.domainobjects.User csmUser, List<SuiteRoleMembership> roleMemberships) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}