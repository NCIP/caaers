package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.semanticbits.security.contentfilter.IdFetcher;
/**
 * 
 * @author akkalas
 *
 */
public class CaaersParticipantIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {

	
	/**
	 * 
	 */
	public List fetch(String loginId) {
		
		gov.nih.nci.cabig.caaers.domain.User caaersUser = csmUserRepository.getUserByName(loginId);
		
		Integer userId = caaersUser.getId();
		// get organization IDS for this user ... 
		
		String orgsQuery = " select organizations.id " +
			"from SiteResearchStaff as siteResearchStaff " +
			"left outer join siteResearchStaff.organization as organizations " +
			"where siteResearchStaff.researchStaff.id = " + userId;
		
		AbstractQuery organizationsDomainObjectQuery = new AbstractQuery(orgsQuery);
		
		
		//List<Integer> userOrganizationCodes = new ArrayList<Integer>();
		

		List<String> rolesToExclude = Arrays.asList(getRolesWhichDoesntRequireStudyLevelFiltering());
		boolean studyFilteringRequired = fetcherUtils.studyFilteringRequired(caaersUser.getUserGroupTypes(), rolesToExclude);
		
		// get participants belong to above user , we have organizations here also , we can avoid above query  .. 
		String 	participantsQuery = "Select participant.id , studyOrgs.organization.nciInstituteCode , studyOrgs.class " +
			    "from Participant participant "+
			            "left join participant.assignments as spa " +
			            "join spa.studySite as ss "+
			            "join ss.study as study "+
			            "join study.studyOrganizations as studyOrgs ";
			            //"left join studyOrgs.studyPersonnelsInternal as stper " +
			            //"left join stper.siteResearchStaff as siteResearchStaff " +
			            //"left outer join siteResearchStaff.organization as organizations " +
			    //" where  siteResearchStaff.researchStaff.id = "+userId;
		AbstractQuery participantsDomainObjectQuery = new AbstractQuery(participantsQuery);
		
		if (studyFilteringRequired) {
			participantsQuery = participantsQuery +  
				"left join studyOrgs.studyPersonnelsInternal as stper " +
				"left join stper.siteResearchStaff as siteResearchStaff " +
				"left outer join siteResearchStaff.organization as organizations " +
				"where siteResearchStaff.researchStaff.id = "+userId;
		} else {
			//for (Object[] obj:organizations) {
				//userOrganizationCodes.add((Integer)obj[0]);
			//}
			List<Object[]> organizations = (List<Object[]>) this.search(organizationsDomainObjectQuery);
			participantsDomainObjectQuery.filterByAnyAnd(" studyOrgs.organization.id IN (:ids)");
			participantsDomainObjectQuery.setParameterList("ids",organizations);
			//participantsQuery = participantsQuery + " where studyOrgs.organization.id IN (:ids)";
		}
		
		
		//if (!studyFilteringRequired) {
		//	ajaxableDomainObjectQuery.setOrgIds(userOrganizationCodes);
		//}
		
		//if one of the above organization is a SCC .. 
		
		
		List<Object[]> participants = (List<Object[]>) search(participantsDomainObjectQuery);
		Set uniqueList = new HashSet();
		
		
		for (Object[] participantId:participants) {
			uniqueList.add((Integer)participantId[0]);
			/*
			Participant participant = participantDao.getParticipantById((Integer)participantId[0]);
			if (isUserOrganizationPartOfStudySites(userOrganizationCodes,getAuthorizedStudies(participant,caaersUser.getId(),studyFilteringRequired))) {
				allowedParticipantIds.add(participant.getId());
			}*/
		}

		return new ArrayList(uniqueList);
	}
	
	/**
	public List fetch(String loginId, Object query) {
		List<Object[]> objects = participantAjaxableDomainObjectRepository.getDataForFiltering((ParticipantAjaxableDomainObjectQuery)query);
		
		List allowedParticipantIds = new ArrayList();
		gov.nih.nci.cabig.caaers.domain.User caaersUser = csmUserRepository.getUserByName(loginId);
		
		List<String> userOrganizationCodes = getUserOrganizations(caaersUser);
		List<String> rolesToExclude = Arrays.asList(getRolesWhichDoesntRequireStudyLevelFiltering());
		boolean studyFilteringRequired = fetcherUtils.studyFilteringRequired(caaersUser.getUserGroupTypes(), rolesToExclude);
		
		Set uniqueParticipants = new HashSet();
		
		for (Object[] o : objects) {
			uniqueParticipants.add((Integer) o[0]);
		}
		for (Object obj:uniqueParticipants) {
			Integer id = (Integer)obj;
			Participant participant = participantDao.getById(id);
			if (isUserOrganizationPartOfStudySites(userOrganizationCodes,getAuthorizedStudies(participant,caaersUser.getId(),studyFilteringRequired))) {
				allowedParticipantIds.add(participant.getId());
			}
		}
		
		return allowedParticipantIds;
	}*/
	
	/**
	 * Check if userOrganization is one of the study sites of authorized study , check CC and SFS also .. 
	 * @param userOrganizations
	 * @param authorizedStudies
	 * @return
	 */
	/**
	private boolean isUserOrganizationPartOfStudySites(Set<String> userOrganizations, List <Study> authorizedStudies) {
	
		for (Study study:authorizedStudies) {
				for (StudyOrganization ss : study.getActiveStudyOrganizations()) {
					//CAAERS-2441					
					if (userOrganizations.contains(ss.getOrganization().getNciInstituteCode())) {
						return true;
					}
				}
				// may be i dont need thses folowing checks as study orgs shud have CC and SFS ... 
				if (study.getCoordinatingCenter() != null && userOrganizations.contains(study.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization().getNciInstituteCode())) {
					return true;
				}
				for (StudyFundingSponsor sfs : study.getStudyFundingSponsors()) {				
					if (userOrganizations.contains(sfs.getOrganization().getNciInstituteCode())) {
						return true;
					}
				}
		}

		return false;
	}
	*/
	/**
	 * Check if user is assigned to this study ..
	 * @param participant
	 * @param researchStaffId
	 * @param studyFilteringRequired
	 * @return
	 */
	/**
	private List <Study> getAuthorizedStudies(Participant participant, Integer researchStaffId, boolean studyFilteringRequired) {
		List <Study> authorizedStudies = new ArrayList<Study>();
		
//		 study level filtering for AE Coordinator or Subject Coordinator  ...
		if (studyFilteringRequired) {
			List <Study> studies = participant.getStudies();				
			for (Study study:studies) {
				List<StudyOrganization> soList = study.getActiveStudyOrganizations();
				for (StudyOrganization so:soList) {
					
					List<StudyPersonnel> spList = so.getActiveStudyPersonnel();
					for (StudyPersonnel sp:spList) {
						if(sp.isActive()) {
							if (sp.getSiteResearchStaff().getResearchStaff().getId().equals(researchStaffId)) {
								authorizedStudies.add(study);
								break;
							}
						}
					}

					
				}

			}
		} else {
			authorizedStudies = participant.getStudies();
		}	
		
		return authorizedStudies;
	}
	*/
	private String[] getRolesWhichDoesntRequireStudyLevelFiltering() {
        String[] roles = {
				  UserGroupType.caaers_site_cd.getSecurityRoleName(),
				  UserGroupType.caaers_physician.getSecurityRoleName()
				  };
        return roles;
	}


	
}
