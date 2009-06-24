package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;

import java.util.List;

public abstract class AbstractImportService {

	protected StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
	protected OrganizationRepository organizationRepository;
	
	public List<StudySearchableAjaxableDomainObject> getAuthorizedStudies(String studyIdentifier) {
		StudySearchableAjaxableDomainObjectQuery qry = new StudySearchableAjaxableDomainObjectQuery();
		qry.filterStudiesWithExactMatchingIdentifierOnly(studyIdentifier);
		return studySearchableAjaxableDomainObjectRepository.findStudies(qry);
	}

	public List<Organization> getAuthorizedOrganizationsByNameOrNciId(String name,String nciId) {
		OrganizationQuery organizationQuery = new OrganizationQuery();
		if (name != null) {
            organizationQuery.filterByOrganizationName(name);
        }
        if (nciId != null) {
            organizationQuery.filterByNciInstituteCode(nciId);
        }
		return organizationRepository.searchOrganization(organizationQuery);
	}
	
	public void setStudySearchableAjaxableDomainObjectRepository(
			StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository) {
		this.studySearchableAjaxableDomainObjectRepository = studySearchableAjaxableDomainObjectRepository;
	}

	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}
	
}
