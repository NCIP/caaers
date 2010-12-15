package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSession;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;

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
		_User user = userRepository.getUserByLoginName(request.getParameter("userName"));
		UserCommand command = new UserCommand();
		if(user.getCsmUser() != null){
 			//Get all the suite role memberships for user
			populateRoleMemberships(user,command);
			populateSiteMap(command);
			populateStudyMap(command);
		}
		command.setUser(user);
		command.buildRolesHelper();
		return command;
	}
	
	/**
	 * Populates SuiteRoleMembeships for the user.
	 * @param csmUser
	 * @param command
	 */
	private void populateRoleMemberships(_User user,UserCommand command){
		ProvisioningSession session =  proSessionFactory.createSession(user.getCsmUser().getUserId());
		for(UserGroupType group : user.getUserGroupTypes()){
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
