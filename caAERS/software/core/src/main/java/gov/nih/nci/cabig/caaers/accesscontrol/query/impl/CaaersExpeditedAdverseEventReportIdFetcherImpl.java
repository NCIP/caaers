package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.utils.FetcherUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;

public class CaaersExpeditedAdverseEventReportIdFetcherImpl extends BaseSecurityFilterer implements IdFetcher {
	
	private UserDao userDao;
	private FetcherUtils fetcherUtils;
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	
	/**
	 * 
	 */
	public List fetch(String loginId) {
		List<String> allowedExpeditedAdverseEventReportIds = new ArrayList<String>();
		gov.nih.nci.cabig.caaers.domain.User caaersUser = userDao.getByLoginId(loginId);
		List<String> userOrganizationCodes = getUserOrganizations(caaersUser);
		List<String> rolesToExclude = Arrays.asList(getRolesWhichDoesntRequireStudyLevelFiltering());
		boolean studyFilteringRequired = fetcherUtils.studyFilteringRequired(caaersUser.getUserGroupTypes(), rolesToExclude);
		
		boolean isAuthorizedOnThisStudy = true;
		ExpeditedAdverseEventReport aer = expeditedAdverseEventReportDao.getById(-1);//getHibernateTemplate().find("from ExpeditedAdverseEventReport");
		
		List<ExpeditedAdverseEventReport> allExpeditedAdverseEventReports = new ArrayList<ExpeditedAdverseEventReport>();
		allExpeditedAdverseEventReports.add(aer);
		
		for (ExpeditedAdverseEventReport expeditedAdverseEventReport:allExpeditedAdverseEventReports) {
			isAuthorizedOnThisStudy = true;
        	
			if (studyFilteringRequired) {
				if (!isUserAssociatedToStudy(caaersUser.getId(),expeditedAdverseEventReport.getAssignment())) {
					isAuthorizedOnThisStudy=false;
				}
				//if not SLRR , or SLRR is authorized , then apply site level filtering.
	        	if (isAuthorizedOnThisStudy || isAuthorized(userOrganizationCodes,expeditedAdverseEventReport.getAssignment())) {
	        		allowedExpeditedAdverseEventReportIds.add(expeditedAdverseEventReport.getId()+"");
	        	}
			} else {
				allowedExpeditedAdverseEventReportIds.add(expeditedAdverseEventReport.getId()+"");
			}
		}
		
		return allowedExpeditedAdverseEventReportIds;
	}
	
	/**
	 * 
	 * @param userOrganizations
	 * @param assignment
	 * @return
	 */
	private boolean isAuthorized(List<String> userOrganizations , StudyParticipantAssignment assignment) {
		
		//check if the study site is still active?
		StudySite studySite = assignment.getStudySite();
		if(studySite.isRetired()) return false;
		
		// check if user is part of co-ordinating center 
		if (userOrganizations.contains(studySite.getStudy().getStudyCoordinatingCenter().getOrganization().getNciInstituteCode())) return true;
		
		Organization site = studySite.getOrganization();
		//for (StudyOrganization so:soList) {
			//if (so instanceof StudySite) {
				if (userOrganizations.contains(site.getNciInstituteCode())) {
					return true;
				}
			//}			
		//}
		return false;
	}
	
	/**
	 * 
	 * @param userId
	 * @param assignment
	 * @return
	 */
	private boolean isUserAssociatedToStudy(Integer userId, StudyParticipantAssignment assignment) {
		
		//check if the study site is still active?
		StudySite studySite = assignment.getStudySite();
		if(studySite.isRetired()) return false;
		
		//StudyOrganization so = assignment.getStudySite();//.getOrganization();
		List<StudyOrganization> soList = assignment.getStudySite().getStudy().getActiveStudyOrganizations();

		for (StudyOrganization so:soList) {
			List<StudyPersonnel> spList = so.getActiveStudyPersonnel();
			for (StudyPersonnel sp:spList) {
				
				if(sp.isInActive()) continue;
				
				if (sp.getSiteResearchStaff().getResearchStaff().getId().equals(userId)) {
					return true;
				}
			}
			/* FUTURE REQUIREMENT .. check for investigators also , physician role dont need study level filtering now , but this code covers the future requirement if any 
			List<StudyInvestigator> studyInvList = so.getStudyInvestigators();
			for (StudyInvestigator studyInv:studyInvList) {
				if (studyInv.getSiteInvestigator().getInvestigator().getId().equals(userId)) {
					return true;
				}
			}*/
		}
		return false;
	}
	
	private String[] getRolesWhichDoesntRequireStudyLevelFiltering() {
        String[] roles = {
				  UserGroupType.caaers_site_cd.getSecurityRoleName(),
				  UserGroupType.caaers_physician.getSecurityRoleName()
				  };
        return roles;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setFetcherUtils(FetcherUtils fetcherUtils) {
		this.fetcherUtils = fetcherUtils;
	}

	public void setExpeditedAdverseEventReportDao(
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}

	public List fetch(String id, Object query) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
