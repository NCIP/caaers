package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrganizationServiceImpl implements OrganizationService {

    private UserProvisioningManager userProvisioningManager;

    private String csmApplicationContextName;
    private String siteProtectionGroupId;
    private String siteAccessRoleId;

    private CSMObjectIdGenerator siteObjectIdGenerator;

    private Logger log = Logger.getLogger(OrganizationService.class);
//
//    public void save(Organization site) throws CaaersSystemException {
//        createGroupForOrganization(site);
//        organizationDao.save(site);
//    }

    /*
     */
//    public void merge(Organization site) throws CaaersSystemException {
//        organizationDao.save(site);
//    }

    public Group createGroupForOrganization(Organization organization) throws CaaersSystemException {
        Group group = new Group();
        try {
            String siteId = siteObjectIdGenerator.generateId(organization);

            Application app = userProvisioningManager.getApplication(csmApplicationContextName);
            group.setApplication(app);
            group.setGroupDesc(organization.getDescriptionText());
            group.setGroupName(siteId);
            group.setUpdateDate(new Date());
            log.debug("Creating group for new organization:" + siteId);
            userProvisioningManager.createGroup(group);

            ProtectionGroup protectionGroup = new ProtectionGroup();
            protectionGroup.setApplication(userProvisioningManager.getApplication(csmApplicationContextName));
            protectionGroup.setParentProtectionGroup(userProvisioningManager.getProtectionGroupById(siteProtectionGroupId));
            protectionGroup.setProtectionGroupName(siteId);
            log.debug("Creating protection group for new organization:" + siteId);
            userProvisioningManager.createProtectionGroup(protectionGroup);

            log.debug("Creating Protection Element for new organization:" + siteId);
            ProtectionElement protectionElement = new ProtectionElement();
            protectionElement.setApplication(userProvisioningManager.getApplication(csmApplicationContextName));
            protectionElement.setObjectId(siteId);
            protectionElement.setProtectionElementName(siteId);
            protectionElement.setProtectionElementDescription("Site Protection Element");
            Set<ProtectionGroup> protectionGroups = new HashSet<ProtectionGroup>();
            protectionGroups.add(protectionGroup);
            protectionElement.setProtectionGroups(protectionGroups);
            userProvisioningManager.createProtectionElement(protectionElement);

            userProvisioningManager.assignGroupRoleToProtectionGroup(protectionGroup.getProtectionGroupId().toString(), group.getGroupId().toString(), new String[]{siteAccessRoleId});


        } catch (CSObjectNotFoundException e) {
            e.getStackTrace();
            log.error("###Error getting info for" + csmApplicationContextName + " application from CSM. Application configuration exception###");
            throw new CaaersSystemException("Application configuration problem. Cannot find application '" + csmApplicationContextName + "' in CSM", e);
        }
        catch (CSTransactionException e) {
            log.warn("Could not create group for organization:" + organization.getNciInstituteCode());
            throw new CaaersSystemException("Cannot create group for organization.", e);
        }
        return group;
    }

    @Required
    public void setSiteObjectIdGenerator(CSMObjectIdGenerator siteObjectIdGenerator) {
        this.siteObjectIdGenerator = siteObjectIdGenerator;
    }


    @Required
    public void setSiteAccessRoleId(String siteAccessRoleId) {
        this.siteAccessRoleId = siteAccessRoleId;
    }


    @Required
    public void setSiteProtectionGroupId(String siteProtectionGroupId) {
        this.siteProtectionGroupId = siteProtectionGroupId;
    }


    @Required
    public void setUserProvisioningManager(UserProvisioningManager userProvisioningManager) {
        this.userProvisioningManager = userProvisioningManager;
    }



    @Required
    public void setCsmApplicationContextName(String csmApplicationContextName) {
        this.csmApplicationContextName = csmApplicationContextName;
    }

}