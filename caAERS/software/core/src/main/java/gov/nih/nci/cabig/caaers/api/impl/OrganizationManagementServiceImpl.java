package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.api.OrganizationManagementService;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.service.migrator.OrganizationMigrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

public class OrganizationManagementServiceImpl implements OrganizationManagementService{
	
	private static Log logger = LogFactory.getLog(OrganizationManagementServiceImpl.class);
	private MessageSource messageSource;
    private OrganizationDao organizationDao;
    private OrganizationMigrator organizationMigrator;
    private OrganizationRepository  organizationRepository;

	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public void setOrganizationMigrator(OrganizationMigrator organizationMigrator) {
		this.organizationMigrator = organizationMigrator;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Transactional(readOnly=false)
	public ProcessingOutcome createOrUpdateOrganization(Organization organization) {
		try {
			Organization dbOrganization = organizationDao.getByNCIcode(organization.getNciInstituteCode());
            if(dbOrganization == null){
                logger.info("didn't find db Organization with NCI identifier:" + organization.getNciInstituteCode() + ". Creating new Organization");
                dbOrganization = new LocalOrganization();
            }
            organizationMigrator.migrate(organization, dbOrganization, null);
            organizationRepository.createOrUpdate(dbOrganization);
            if(logger.isDebugEnabled()){
                logger.debug("Organization with NCI Code : " +
                        organization.getNciInstituteCode() + ", id : " + dbOrganization.getId() + " modified/created" );
            }
            return Helper.createOutcome(Organization.class, organization.getNciInstituteCode(), false, "Organization with NCI Code : " +
                        organization.getNciInstituteCode() + ", id : " + dbOrganization.getId() + " modified/created" );
		} catch (Exception e) {
			logger.error(e);
            return Helper.createOutcome(Organization.class, organization.getNciInstituteCode(), true, e.getMessage());
		}
		
	}
	
	@Transactional(readOnly=false)
	public ProcessingOutcome mergeOrganization(Organization organization){
        Organization dbOrganization = organizationDao.getByNCIcode(organization.getNciInstituteCode());
        if(dbOrganization == null){
            logger.warn("Could not find the Organization getting merged (NCI Code : " + organization.getNciInstituteCode() +")");
            return Helper.createOutcome(Organization.class, organization.getNciInstituteCode(), true, "Cannot find NCI code");
        }
        if(logger.isInfoEnabled()) logger.info("Processing merge for : " + organization.getNciInstituteCode());
        ProcessingOutcome outcome = createOrUpdateOrganization(organization.getMergedOrganization()); //call to create the merged org if it is not available
        if(outcome.isFailed()) return outcome;

        
		try {
            dbOrganization.retire();
            Organization mergedDbOrganization = organizationDao.getByNCIcode(organization.getMergedOrganization().getNciInstituteCode());
            dbOrganization.setMergedOrganization(mergedDbOrganization);
            organizationRepository.createOrUpdate(dbOrganization);
            return Helper.createOutcome(Organization.class, organization.getNciInstituteCode(), false, "successful");
        } catch (Exception e) {
            logger.error("Error while merging organizations :", e);
            return Helper.createOutcome(Organization.class, organization.getNciInstituteCode(), true, e.getMessage());
		}
		
	}

	public List<ProcessingOutcome> createOrUpdateOrganizations(List<Organization> organizations) {
        long start = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        
        List<ProcessingOutcome> outcomes = new ArrayList<ProcessingOutcome>();

        List<Organization> allOrgs = organizationDao.getAll();
        if(logger.isInfoEnabled()) {
            logger.info("Time taken by createOrUpdateOrganizations to query all orgs : " + ( ( System.currentTimeMillis() - now)/1000) + " seconds" );
        }
        now = System.currentTimeMillis();
        Map<String, Organization> orgMap = new HashMap<String, Organization>(allOrgs.size());
        for(Organization o: allOrgs){
            orgMap.put(o.getNciInstituteCode(), o);
        }
        if(logger.isInfoEnabled()) {
            logger.info("Time taken by  createOrUpdateOrganizations to load all orgs :(" + allOrgs.size() + ") " + ( ( System.currentTimeMillis() - now)/1000) + " seconds" );
        }
        ArrayList<Organization> newOrgs = new ArrayList<Organization>();
        now = System.currentTimeMillis();
        for(Organization organization : organizations){
           Organization dbOrganization = orgMap.get(organization.getNciInstituteCode());
           if(dbOrganization != null){
               if(dbOrganization.basicAttributesSame(organization)) {
                   ProcessingOutcome outcome =  Helper.createOutcome(Organization.class, dbOrganization.getNciInstituteCode(), false,
                           "Organization with NCI Code : " + dbOrganization.getNciInstituteCode() + ", id : " + dbOrganization.getId() + " left unchanged" );
                   outcomes.add(outcome);
                   continue;
               }
           }else{
               dbOrganization = new LocalOrganization();
           }
           organizationMigrator.migrate(organization, dbOrganization, null);
           newOrgs.add(dbOrganization);
        }
        if(logger.isInfoEnabled()) {
            logger.info("Time taken by  createOrUpdateOrganizations to migrate : (" + newOrgs.size() + ") "  + ( ( System.currentTimeMillis() - now)/1000) + " seconds" );
        }
        now = System.currentTimeMillis();
        int i = 0;
        for(Organization o : newOrgs){
            i++;
            organizationRepository.saveImportedOrganization(o);
            if((i%250) == 0 ) organizationDao.flush();
            if(logger.isInfoEnabled()) logger.info( "Organization with NCI Code : " + o.getNciInstituteCode() + ", id : " + o.getId() + " modified/created" );
            ProcessingOutcome outcome =  Helper.createOutcome(Organization.class, o.getNciInstituteCode(), false,
                    "Organization with NCI Code : " + o.getNciInstituteCode() + ", id : " + o.getId() + " modified/created" );
            outcomes.add(outcome);
        }

        long end = System.currentTimeMillis();
        
        if(logger.isInfoEnabled()) {
            logger.info("Time taken by  createOrUpdateOrganizations : to save all orgs " + ( ( end - now)/1000) + " seconds" );
            logger.info("Total Time taken by  createOrUpdateOrganizations : " + ( ( end - start)/1000) + " seconds" );
        }
        
		return outcomes;
	}

	public List<ProcessingOutcome> mergeOrganizations(List<Organization> organizations) {
		List<ProcessingOutcome> outcomes = new ArrayList<ProcessingOutcome>();
		for (Organization organization:organizations) {
            outcomes.add(mergeOrganization(organization));
            organizationDao.flush();
            organizationDao.clearSession();
		}
		return outcomes;
	}
}
