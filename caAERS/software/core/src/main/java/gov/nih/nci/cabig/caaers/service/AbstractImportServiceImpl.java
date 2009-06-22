package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Severity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Abstract that sets up the template for import of various domain objects like study, routine AE
 * and study.
 *
 * @author
 */
public abstract class AbstractImportServiceImpl {

    private OrganizationDao organizationDao;

    private StudyDao studyDao;

    protected final Log log = LogFactory.getLog(getClass());

    /*
     * This is common for participant and study
     */
    protected void migrateIdentifiers(AbstractIdentifiableDomainObject destination,
                                      AbstractIdentifiableDomainObject source,
                                      DomainObjectImportOutcome studyImportOutcome) {

        // Identifiers
        for (Identifier identifier : source.getIdentifiers()) {

            if (identifier instanceof OrganizationAssignedIdentifier) {
                Organization organization = getOrganization(((OrganizationAssignedIdentifier) identifier)
                        .getOrganization().getName());
                studyImportOutcome.ifNullObject(organization, Severity.ERROR,
                        "The organization specified in identifier is invalid");
                ((OrganizationAssignedIdentifier) identifier).setOrganization(organization);
            }
            if (identifier instanceof SystemAssignedIdentifier) {
                // I don't need to do anything i think
            }
            destination.getIdentifiers().add(identifier);
        }

        studyImportOutcome.ifNullOrEmptyList(destination.getIdentifiers(), Severity.ERROR,
                "Identifiers are either Empty or Not Valid");
    }

    // Helpers
    protected Organization getOrganization(String organizationName) {
        Organization organization = organizationDao.getByName(organizationName);
        return organization;
    }


    @Required
    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

}
