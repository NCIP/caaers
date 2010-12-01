package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.CSMUser;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSession;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.security.authorization.domainobjects.User;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Monish
 *
 */
public class EditUserController extends UserController<UserCommand> {
	@Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
		User csmUser = caaersSecurityFacade.getCsmUserRepository().getCSMUserByName(request.getParameter("userName"));
		CSMUser caaersUser = new CSMUser();
		UserCommand command = new UserCommand();
		if(csmUser != null){
			//Set user details
			caaersUser.setId(csmUser.getUserId().intValue());
			caaersUser.setFirstName(csmUser.getFirstName());
			caaersUser.setLastName(csmUser.getLastName());
			caaersUser.setEmailAddress(csmUser.getEmailId());
			caaersUser.setLoginId(csmUser.getLoginName());
			//Get all the suite role memberships for user
			populateRoleMemberships(csmUser,command);
			populateSiteMap(command);
			populateStudyMap(command);
		}
		command.setCsmUser(caaersUser);
		command.buildRolesHelper();
		return command;
	}
	
	/**
	 * Populates SuiteRoleMembeships for the user.
	 * @param csmUser
	 * @param command
	 */
	private void populateRoleMemberships(User csmUser,UserCommand command){
		List<UserGroupType> userGroups = caaersSecurityFacade.getCsmUserRepository().getUserGroups(csmUser.getLoginName());
		ProvisioningSession session =  proSessionFactory.createSession(csmUser.getUserId());
		for(UserGroupType group : userGroups){
			command.addRoleMembership(session.getProvisionableRoleMembership(SuiteRole.getByCsmName(group.getCsmName())));
		}
	}
	
	/**
	 * Builds a Map containing <NCICode> <String to display>. This Map is used in the UI. 
	 * @param command
	 */
	private void populateSiteMap(UserCommand command){
		OrganizationQuery query = null;
		StringBuilder displayValue = null;
		for(SuiteRoleMembership srM : command.getRoleMemberships()){
			if(srM.getRole().isScoped()){
				if(!srM.isAllSites()){
					for(String nciCode : srM.getSiteIdentifiers()){
						if(!command.getSiteMap().containsKey(nciCode)){
							query = new OrganizationQuery();
							query.filterByNciCodeExactMatch(nciCode);
							List<Organization> orgs = organizationRepository.getLocalOrganizations(query);
							if(orgs.isEmpty()){
								command.getSiteMap().put(nciCode, nciCode);
							}else{
								displayValue = new StringBuilder();
								Organization org = orgs.get(0);
								displayValue.append("(").append(org.getNciInstituteCode()).append(") ");
								displayValue.append(org.getName());
								command.getSiteMap().put(nciCode, displayValue.toString());
							}							
						}
					}
				}
			}
		}
	}
	
	/**
	 * Builds a Map containing <Study Coordinating Center Identifier> <String to display>. This Map is used in the UI. 
	 * @param command
	 */
	private void populateStudyMap(UserCommand command){
		StudyQuery query = null;
		StringBuilder displayValue = null;
		for(SuiteRoleMembership srM : command.getRoleMemberships()){
			if(srM.getRole().isScoped()){
				if(srM.getRole().isSiteScoped() && srM.getRole().isStudyScoped()){
					if(!srM.isAllStudies()){
						for(String studyIdentifier : srM.getStudyIdentifiers()){
							if(!command.getStudyMap().containsKey(studyIdentifier)){
								query = new StudyQuery();
								query.filterByIdentifierValueExactMatch(studyIdentifier);
								List<Study> studies = studyRepository.find(query);
								if(studies.isEmpty()){
									command.getStudyMap().put(studyIdentifier, studyIdentifier);
								}else{
									displayValue = new StringBuilder();
									Study study = studies.get(0);
									displayValue.append("(").append(study.getCoordinatingCenterIdentifierValue()).append(") ");
							        String suffix = "";
							        String studyTitle = study.getShortTitle();
							        int end = studyTitle.length();
							        if(end > 30){
							        	end = 30;
							        	suffix = "...";
							        }
							        studyTitle = StringUtils.substring(studyTitle, 0, end);
							        studyTitle = studyTitle+suffix;
							        displayValue.append(studyTitle);
							        command.getStudyMap().put(studyIdentifier, displayValue.toString());
								}								
							}
						}						
					}
				}
			}
		}
	}
	
}
