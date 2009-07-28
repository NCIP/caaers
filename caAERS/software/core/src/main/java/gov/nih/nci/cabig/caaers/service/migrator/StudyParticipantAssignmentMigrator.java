package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.security.util.StringUtilities;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

public class StudyParticipantAssignmentMigrator implements Migrator<Participant> {
	
	private StudySiteDao studySiteDao;
	private final Log log = LogFactory.getLog(getClass());
	private OrganizationDao organizationDao;
	private OrganizationRepository organizationRepository;
	private StudyDao studyDao;
	private StudyRepository studyRepository;
	  
	public void migrate(Participant src, Participant dest,DomainObjectImportOutcome<Participant> outcome) {

        for (StudyParticipantAssignment studyParticipantAssignment : src.getAssignments()) {
        	
        	StudySite srcSite = studyParticipantAssignment.getStudySite();
        	 log.debug("Size of identifiers : " + srcSite.getStudy().getIdentifiers());
        	 
            for (Identifier identifier : studyParticipantAssignment.getStudySite().getStudy().getIdentifiers()) {
               
                String identifierValue = identifier.getValue();
                String identifierType = identifier.getType();
                String organizationName = srcSite.getOrganization().getName();
                String organizationNciInstituteCode = srcSite.getOrganization().getNciInstituteCode();
              
                StudySite studySite = null;
                Organization organization = null;
                if (StringUtilities.isBlank(organizationNciInstituteCode)) {
                	//System.out.println("looking by name");
                	organization = organizationDao.getByName(organizationName);
                	studySite = studySiteDao.matchByStudyAndOrg(organizationName, identifierValue, identifierType);
                } else {
                	//System.out.println("looking by id");
                	organization = fetchOrganization(organizationNciInstituteCode);
                	studySite = studySiteDao.matchByStudyAndOrgNciId(organizationNciInstituteCode, identifierValue, identifierType);
                }
                //If study site is not found add the incoming studysite to the study.
                if(studySite == null){
                	Study study = studyDao.getByIdentifier(identifier);
                	studySite = new StudySite();
                	studySite.setOrganization(organization);
                	studySite.setStartDate(new Date());
                	studySite.setEndDate(srcSite.getEndDate());
                	if(study == null){
                		outcome.ifNullObject(study, DomainObjectImportOutcome.Severity.ERROR,
                                "The Study with Identifier \" " + identifierValue
                                        + " \" is nonexistant");
                	}else{
                		study.addStudySite(studySite);
                	//	studyDao.updateStudyForServiceUseOnly(study);
                		//Introduced this call  
                		studyRepository.save(study);
                	}
                }
                StudyParticipantAssignment studParticipantAssignment = new StudyParticipantAssignment(dest, studySite);
                studParticipantAssignment.setStudySubjectIdentifier(studyParticipantAssignment.getStudySubjectIdentifier());
                
                dest.addAssignment(studParticipantAssignment);

            }

        }
        outcome.ifNullOrEmptyList(src.getAssignments(), DomainObjectImportOutcome.Severity.ERROR,
                "Assignments are either Empty or Not Valid");
	}
	
	///BEAN METHODS
	 @Required
	 public void setStudySiteDao(final StudySiteDao studySiteDao) {
		 this.studySiteDao = studySiteDao;
	 }
	 
    private Organization fetchOrganization(String nciInstituteCode) {
        OrganizationQuery orgQuery = new OrganizationQuery();

        if (StringUtils.isNotEmpty(nciInstituteCode)) {
            orgQuery.filterByNciCodeExactMatch(nciInstituteCode);
        }

        List<Organization> orgList = organizationRepository.searchOrganization(orgQuery);

        if (orgList == null || orgList.isEmpty()) {
            return null;
        }
        if (orgList.size() > 1) {
            //("Multiple organizations exist with same NCI Institute Code :" + nciInstituteCode);
        }

        return orgList.get(0);
    }

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}
	    
}
