package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;

import java.util.List;

public abstract class AbstractImportService {

	protected StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
	
	public List<StudySearchableAjaxableDomainObject> getAuthorizedStudies(String studyIdentifier) {
		StudySearchableAjaxableDomainObjectQuery qry = new StudySearchableAjaxableDomainObjectQuery();
		qry.filterStudiesWithExactMatchingIdentifierOnly(studyIdentifier);
		return studySearchableAjaxableDomainObjectRepository.findStudies(qry);
	}

	public void setStudySearchableAjaxableDomainObjectRepository(
			StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository) {
		this.studySearchableAjaxableDomainObjectRepository = studySearchableAjaxableDomainObjectRepository;
	}
	
}
