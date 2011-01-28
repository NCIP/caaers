package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSession;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Monish
 * @author Biju  (Refactored formBacking() and incorporated linking.
 *
 */
public class EditUserController extends UserController<UserCommand> {
	
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView processFinish(HttpServletRequest request,HttpServletResponse response, Object userCommand, BindException errors) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("admin/user_confirmation");
		UserCommand command = (UserCommand)userCommand;
        String mailSendIssue = "";

        if (!errors.hasErrors()) {

            _User user = command.getUser();
            Person person = command.getPerson();


            boolean willCreatePerson = (person != null && person.getId() == null);
            boolean willUpdatePerson = (person != null) && person.getId() != null;
            boolean willCreateUser = user != null && user.getId() == null;
            boolean willUpdateUser = user != null && user.getId() != null;

            if(user != null){
                try {
                    createOrUpdateUser(request,user);
                }catch (MailException e) {
                    mailSendIssue = ". But could not send email to the User";
                    logger.error("Could not send email to user.", e);
                }
                processRoleMemberships(command.getUser().getCsmUser(),command.getRoleMemberships());
            }

            if(command.getPerson() != null){
                personRepository.save(command.getPerson());
            }

        	StringBuilder statusMessage = new StringBuilder();
            if(willCreatePerson || willUpdatePerson){
                statusMessage.append(willUpdatePerson ? "Updated " : "Created ").append(command.getPersonType());
                if(willCreateUser || willUpdateUser) statusMessage.append(" with login capability");
            }else if (willCreateUser || willUpdateUser){
                statusMessage.append(willUpdateUser ? "Updated " : "Created ").append(" the User");
            }
            statusMessage.append(mailSendIssue);

            modelAndView.getModel().put("flashMessage", statusMessage);
        }
        modelAndView.addAllObjects(errors.getModel());
		return modelAndView;
	}
	
	
	@Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));

        String linkType = request.getParameter("linkType");
        String linkedId = request.getParameter("linkedId");
        String linkedUserName = request.getParameter("linkedUserName");
        String linkedRecordType = request.getParameter("linkedRecordType");
		String recordType = request.getParameter("recordType");
		String userName = request.getParameter("userName");
		String id = request.getParameter("id");

		
		UserCommand command = new UserCommand();
		command.setCreateMode(Boolean.FALSE);
		command.setEditMode(Boolean.TRUE);

        Person person = null;
        _User user = null;

        if(StringUtils.isNotEmpty(linkType)){
            //edit call from popup - for linking
            if(StringUtils.equals(linkType, "person")){
                Integer personId = Integer.parseInt(linkedId);
                person = personRepository.getById(personId);
                user = userRepository.getUserByLoginName(userName);
            }else if(StringUtils.equals(linkType, "user")){
                user = userRepository.getUserByLoginName(linkedUserName);
                Integer personId = Integer.parseInt(id);
                person = personRepository.getById(personId);
            }
        }else{

            //normal edit.
            if(StringUtils.equals("CSM_RECORD", recordType)){
                 user = userRepository.getUserByLoginName(userName);
            }else if(StringUtils.equals("RESEARCHSTAFF_RECORD", recordType) ||
                     StringUtils.equals("INVESTIGATOR_RECORD", recordType)){
                Integer personId = Integer.parseInt(id);
                person = personRepository.getById(personId);
                user = person.getCaaersUser();
            }
            
        }

        if(user != null){
            command.setCreateAsUser(true);
            command.setUser(user);
            if(user.getCsmUser() != null){
				command.setFirstName(user.getCsmUser().getFirstName());
				command.setLastName(user.getCsmUser().getLastName());
				command.setEmailAddress(user.getCsmUser().getEmailId());
				command.setUserName(user.getCsmUser().getLoginName());
                populateRoleMemberships(user,command);
		        populateSiteMap(command);
		        populateStudyMap(command);
            }
            command.buildRolesHelper();

        }
        if(person != null){
            command.setCreateAsPerson(true);
            command.setPerson(person);
            command.setFirstName(person.getFirstName());
			command.setMiddleName(person.getMiddleName());
			command.setLastName(person.getLastName());
			command.setEmailAddress(person.getEmailAddress());

            if(person instanceof ResearchStaff){
                ResearchStaff rs = (ResearchStaff) person;
                command.setNciIdentifier(rs.getNciIdentifier());
                command.setPersonType("ResearchStaff");
                SitePerson sitePerson = null;
				for(SiteResearchStaff srs : rs.getSiteResearchStaffs()){
					sitePerson = new SitePerson();
					sitePerson.setId(srs.getId());
					sitePerson.setOrganization(srs.getOrganization());
					sitePerson.setPerson(srs.getResearchStaff());
					sitePerson.setAddress(srs.getAddress());
					sitePerson.setPhoneNumber(srs.getPhoneNumber());
					sitePerson.setFaxNumber(srs.getFaxNumber());
					sitePerson.setEmailAddress(srs.getEmailAddress());
					command.addSitePersonnel(sitePerson);
				}
            }else if(person instanceof Investigator) {
                Investigator investigator = (Investigator) person;
                command.setNciIdentifier(investigator.getNciIdentifier());
				command.setPersonType("Investigator");
				SitePerson sitePerson = null;
				for(SiteInvestigator siteInv : investigator.getSiteInvestigators()){
					sitePerson = new SitePerson();
					sitePerson.setId(siteInv.getId());
					sitePerson.setOrganization(siteInv.getOrganization());
					sitePerson.setPerson(siteInv.getInvestigator());
					sitePerson.setEmailAddress(siteInv.getEmailAddress());
					command.addSitePersonnel(sitePerson);
				}
            }
        }

        return command;
        
	}
	
	/**
	 * Populates SuiteRoleMembeships for the user.
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
