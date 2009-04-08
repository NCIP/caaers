package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationConverterDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.ConverterOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class OrganizationRepositoryImpl implements OrganizationRepository {
	private Logger log = Logger.getLogger(getClass());

    private UserProvisioningManager userProvisioningManager;
	
    private OrganizationDao organizationDao;
    private OrganizationConverterDao organizationConverterDao;
    
    private String csmApplicationContextName;

    private String siteProtectionGroupId;

    private String siteAccessRoleId;

    private CSMObjectIdGenerator siteObjectIdGenerator;

    public void createOrUpdate(Organization organization) {
        if (organization.getId() == null) {
            create(organization);
        } else {
            organizationDao.save(organization);
        }
    }

    /**
     * Create a new organization. Note that this method must be used when entering a new
     * organization (not {@link OrganizationDao#save}). As written, it is not suitable for updating
     * an existing organization.
     * 
     * @param site
     * @throws CaaersSystemException
     */
    public void create(Organization site) throws CaaersSystemException {
        createGroupForOrganization(site);
        organizationDao.save(site);
    }

    private Group createGroupForOrganization(Organization organization)
                    throws CaaersSystemException {
        Group group = new Group();
        try {
            String siteId = siteObjectIdGenerator.generateId(organization.getNciInstituteCode());

            Application app = userProvisioningManager.getApplication(csmApplicationContextName);
            group.setApplication(app);
            group.setGroupDesc(organization.getDescriptionText());
            group.setGroupName(siteId);
            group.setUpdateDate(new Date());
            log.debug("Creating group for new organization:" + siteId);
            userProvisioningManager.createGroup(group);

            ProtectionGroup protectionGroup = new ProtectionGroup();
            protectionGroup.setApplication(app);
            protectionGroup.setParentProtectionGroup(userProvisioningManager
                            .getProtectionGroupById(siteProtectionGroupId));
            protectionGroup.setProtectionGroupName(siteId);
            log.debug("Creating protection group for new organization:" + siteId);
            userProvisioningManager.createProtectionGroup(protectionGroup);

            log.debug("Creating Protection Element for new organization:" + siteId);
            ProtectionElement protectionElement = new ProtectionElement();
            protectionElement.setApplication(app);
            protectionElement.setObjectId(siteId);
            protectionElement.setProtectionElementName(siteId);
            protectionElement.setProtectionElementDescription("Site Protection Element");
            Set<ProtectionGroup> protectionGroups = new HashSet<ProtectionGroup>();
            protectionGroups.add(protectionGroup);
            protectionElement.setProtectionGroups(protectionGroups);
            userProvisioningManager.createProtectionElement(protectionElement);

            userProvisioningManager.assignGroupRoleToProtectionGroup(protectionGroup
                            .getProtectionGroupId().toString(), group.getGroupId().toString(),
                            new String[] { siteAccessRoleId });

        } catch (CSObjectNotFoundException e) {
            log.error("###Error getting info for" + csmApplicationContextName
                            + " application from CSM. Application configuration exception###", e);
            throw new CaaersSystemException(
                            "Application configuration problem. Cannot find application '"
                                            + csmApplicationContextName + "' in CSM", e);
        } catch (CSTransactionException e) {
            log.warn("Could not create group for organization: "
                            + organization.getNciInstituteCode());
            throw new CaaersSystemException("Cannot create group for organization.", e);
        }
        return group;
    }

    @Required
    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
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

    public List<Organization> getOrganizationsHavingStudySites() {
        return organizationDao.getOrganizationsHavingStudySites();
    }
    
    /**
     * This method converts a LocalOrganization to a RemoteOrganization.
     * 
     */
 	public void convertToRemote(Organization localOrganization,
			Organization remoteOrganization) {
		ConverterOrganization conOrg = organizationConverterDao.getById(localOrganization.getId());
		conOrg.setType("REMOTE");
		conOrg.setExternalId(remoteOrganization.getExternalId());
		conOrg.setName(remoteOrganization.getName());
		conOrg.setNciInstituteCode(remoteOrganization.getNciInstituteCode());
		conOrg.setCity(remoteOrganization.getCity());
		conOrg.setState(remoteOrganization.getState());
		conOrg.setCountry(remoteOrganization.getCountry());
		organizationConverterDao.save(conOrg);
	}
 	
 	@SuppressWarnings("unchecked")
	public List<Organization> searchOrganization(final OrganizationQuery query){
 		List organizations =  organizationDao.getLocalOrganizations(query);
        Organization searchCriteria = new RemoteOrganization();
    	List<Organization> remoteOrganizations = organizationDao.getRemoteOrganizations(searchCriteria);
    	return mergeOrgs(organizations,remoteOrganizations);
 	}
 	
 	public List<Organization> restrictBySubnames(final String[] subnames) {
 		List<Organization> localOrganizations = organizationDao.getBySubnames(subnames);
 		//get organizations from remote service
    	Organization searchCriteria = new RemoteOrganization();
    	List<Organization> remoteOrganizations = organizationDao.getRemoteOrganizations(searchCriteria);
    	return mergeOrgs (localOrganizations,remoteOrganizations);
 	}
 	
 	private List<Organization> mergeOrgs(List<Organization> localList , List<Organization> remoteList) {
    		for (Organization remoteOrganization:remoteList) {
        		Organization org = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
        		if (org == null ) {
        			create(remoteOrganization);
            		localList.add(remoteOrganization);
            	} else {
            		// if it exist in local list , remote interceptor would have loaded the rest of the details .
            		// ? what if the existing organization is local . ?
            		//if (org instanceof LocalOrganization) {        		}
            		if (!localList.contains(org)) {
            			localList.add(org);
            		}
            	}
        	}
    	return localList;
	}

	public void setOrganizationConverterDao(
			OrganizationConverterDao organizationConverterDao) {
		this.organizationConverterDao = organizationConverterDao;
	}
    
}