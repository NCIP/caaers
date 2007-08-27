package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.ctms.web.tabs.AbstractTabbedFlowFormController;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

public class CreateParticipantController extends AbstractTabbedFlowFormController<NewParticipantCommand> {
	private static Log log = LogFactory.getLog(CreateParticipantController.class);

	private StudySiteDao studySiteDao;

	private StudyService studyService;

	private StudyDao studyDao;

	private ParticipantDao participantDao;

	private ListValues listValues;

	private OrganizationDao organizationDao;

	public ListValues getListValues() {
		return listValues;
	}

	public void setListValues(final ListValues listValues) {
		this.listValues = listValues;
	}

	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(final ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public StudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(final StudyService studyService) {
		this.studyService = studyService;
	}

	public StudySiteDao getStudySiteDao() {
		return studySiteDao;
	}

	public void setStudySiteDao(final StudySiteDao studySiteDao) {
		this.studySiteDao = studySiteDao;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(final StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public CreateParticipantController() {
		setCommandClass(NewParticipantCommand.class);
		setAllowDirtyBack(false);

		setAllowDirtyForward(false);
		setFlow(new Flow<NewParticipantCommand>("Create Participant"));
		getFlow().addTab(new Tab("Enter Participant Information", "New Participant", "par/par_create_participant") {



			private static final String PARTICIPANT_FIELD_GROUP = "participant";

			private static final String SITE_FIELD_GROUP = "site";

			@Override
			public Map<String, Object> referenceData(final NewParticipantCommand command) {
				Map<String, Object> refdata = referenceData();
				Map<String, InputFieldGroup> groupMap = createFieldGroups(command);
				refdata.put("fieldGroups", groupMap);

				return refdata;
			}

			protected Map<Object, Object> collectOptions(final List<ListValues> list) {
				Map<Object, Object> options = new LinkedHashMap<Object, Object>();
				options.putAll(InputFieldFactory.collectOptions(list, "code", "desc"));
				return options;
			}

			private Map<String, InputFieldGroup> createFieldGroups(final NewParticipantCommand command) {

				InputFieldGroup participantFieldGroup;
				InputFieldGroup siteFieldGroup;
				RepeatingFieldGroupFactory rfgFactory;

				siteFieldGroup = new DefaultInputFieldGroup(SITE_FIELD_GROUP);

				Map<Object, Object> options = new LinkedHashMap<Object, Object>();
				options.put("", "Please select");
				List<Organization> organizations = organizationDao.getOrganizationsHavingStudySites();
				if (organizations != null) {
					options.putAll(InputFieldFactory.collectOptions(organizations, "id", "name"));
				}
				siteFieldGroup.getFields().add(
						InputFieldFactory.createSelectField("organization", "Site", true, options));


				participantFieldGroup = new DefaultInputFieldGroup(PARTICIPANT_FIELD_GROUP);
				participantFieldGroup.getFields().add(
						InputFieldFactory.createTextField("firstName", "First Name", true));

				participantFieldGroup.getFields().add(
						InputFieldFactory.createTextField("lastName", "Last Name", true));
				participantFieldGroup.getFields().add(
						InputFieldFactory.createTextField("maidenName", "Maiden Name", false));
				participantFieldGroup.getFields().add(
						InputFieldFactory.createTextField("middleName", "Middle Name", false));
				participantFieldGroup.getFields().add(
						InputFieldFactory.createDateField("dateOfBirth", "Date of Birth", true));

				participantFieldGroup.getFields().add(
						InputFieldFactory.createSelectField("gender", "Gender", true, collectOptions(listValues
								.getParticipantGender())));
				participantFieldGroup.getFields().add(
						InputFieldFactory.createSelectField("ethnicity", "Ethnicity", true,
								collectOptions(listValues.getParticipantEthnicity())));

				participantFieldGroup.getFields().add(
						InputFieldFactory.createSelectField("race", "Race", true, collectOptions(listValues
								.getParticipantRace())));
				rfgFactory = new RepeatingFieldGroupFactory("main", "identifiers");
				rfgFactory.addField(InputFieldFactory.createTextField("value", "Identifier", true));

				options = new LinkedHashMap<Object, Object>();
				List<ListValues> list = listValues.getParticipantIdentifierType();
				options.put("", "Please select");
				options.putAll(InputFieldFactory.collectOptions(list, "code", "desc"));

				rfgFactory.addField(InputFieldFactory.createSelectField("type", "Identifier Type", true, options));

				rfgFactory.addField(InputFieldFactory.createTextField("systemName", "System Name", false));
				rfgFactory.addField(InputFieldFactory.createAutocompleterField("organization",
						"Organization Identifier", false));
				rfgFactory.addField(InputFieldFactory.createCheckboxField("primaryIndicator", "Primary Indicator"));


				InputFieldGroupMap map = new InputFieldGroupMap();
				map.addRepeatingFieldGroupFactory(rfgFactory, command.getIdentifiers().size());
				map.addInputFieldGroup(participantFieldGroup);
				map.addInputFieldGroup(siteFieldGroup);
				return map;
			}

			@Override
			public Map<String, Object> referenceData() {

				Map<String, Object> refdata = super.referenceData();
				refdata.put("action", "New");
				return refdata;
			}

			@Override
			public void postProcess(final HttpServletRequest request, final NewParticipantCommand command,
					final Errors errors) {
				String action = request.getParameter("_action");
				String selected = request.getParameter("_selected");
				if ("removeIdentifier".equals(action)) {
					NewParticipantCommand newParticipantCommand = command;
					newParticipantCommand.getIdentifiers().remove(Integer.parseInt(selected));
				}
			}

			@Override
			public void validate(final NewParticipantCommand command, final Errors errors) {
				boolean noPrimaryIdentifier = true;

				boolean organization = command.getOrganization() == null
						|| command.getOrganization().getName().equals("");

				boolean firstName = command.getFirstName() == null || command.getFirstName().equals("");
				boolean lastName = command.getLastName() == null || command.getLastName().equals("");
				boolean dateOfBirth = command.getDateOfBirth() == null;
				boolean gender = command.getGender().equals("---");
				boolean ethnicity = command.getEthnicity().equals("---");
				boolean race = command.getRace().equals("---");
				if (organization) {
					errors.rejectValue("organization", "REQUIRED", "Missing Site");
				}
				if (firstName) {
					errors.rejectValue("firstName", "REQUIRED", "Missing First Name");
				}
				if (lastName) {
					errors.rejectValue("lastName", "REQUIRED", "Missing Last Name");
				}
				if (dateOfBirth) {
					errors.rejectValue("dateOfBirth", "REQUIRED", "Missing Date Of Birth");
				}
				if (gender) {
					errors.rejectValue("gender", "REQUIRED", "Please Specify a Gender");
				}
				if (ethnicity) {
					errors.rejectValue("ethnicity", "REQUIRED", "Please Specify the Ethnicity");
				}
				if (race) {
					errors.rejectValue("race", "REQUIRED", "Please specify the Race");
				}

				List<Identifier> identifiers = command.getIdentifiers();
				for (int i = 0; i < identifiers.size(); i++) {
					Identifier identifier = identifiers.get(i);
					if (identifier instanceof OrganizationAssignedIdentifier
							&& ((OrganizationAssignedIdentifier) identifier).getOrganization() == null) {
						errors.rejectValue("identifiers[" + i + "].organization", "REQUIRED",
								"Organization is required..!");

					}
					else if (identifier instanceof SystemAssignedIdentifier
							&& (((SystemAssignedIdentifier) identifier).getSystemName() == null || ((SystemAssignedIdentifier) identifier)
									.getSystemName().equals(""))) {

						errors.rejectValue("identifiers[" + i + "].systemName", "REQUIRED",
								"System Name is required..!");
					}
					if (identifier.getValue() == null || identifier.getValue().trim().equals("")) {

						errors.rejectValue("identifiers[" + i + "].value", "REQUIRED", "Identifier is required..!");
					}
					if (identifier.getType() == null || identifier.getType().trim().equals("")) {

						errors.rejectValue("identifiers[" + i + "].type", "REQUIRED", "Identifier type is required..!");
					}
					noPrimaryIdentifier = false;
				}
				if (noPrimaryIdentifier) {
					errors
							.rejectValue("identifiers", "REQUIRED",
									"Please Include at least a single primary Identifier");
				}
			}
		});
		getFlow().addTab(new Tab("Choose Study", "Choose Study", "par/par_choose_study") {
			@Override
			public Map<String, Object> referenceData() {
				Map<String, Object> refdata = super.referenceData();
				refdata.put("searchType", listValues.getStudySearchType());
				return refdata;
			}

			@Override
			public void validate(final NewParticipantCommand command, final Errors errors) {
				// boolean studiesArray = command.getStudies() == null || command.getStudies().size() == 0;
				// if (studiesArray) {
				// errors.rejectValue("studySiteArray", "REQUIRED", "Please Select a Study to Continue");
				// }
				boolean studySiteArray = command.getStudySiteArray() == null || command.getStudySiteArray().length == 0;
				if (studySiteArray) {
					errors.rejectValue("studySiteArray", "REQUIRED", "Please Select a Study to Continue");
				}
			}

		});
		getFlow().addTab(new Tab("Review and Submit", "Review and Submit", "par/par_confirmation") {
			@Override
			public Map<String, Object> referenceData() {
				Map<String, Object> refdata = super.referenceData();
				return refdata;
			}
		});
		// getFlow().addTab(new Tab("Confirmation", "Confirmation", "par/par_confirmation"));
	}

	@Override
	protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
		ControllerTools.registerDomainObjectEditor(binder, organizationDao);
	}

	@Override
	protected Object formBackingObject(final HttpServletRequest request) throws Exception {
		log.debug("Entering formBackingObject ...");
		NewParticipantCommand participantCommand = new NewParticipantCommand();
		participantCommand.setIdentifiers(new ArrayList<Identifier>());
		return participantCommand;
	}

	@Override
	protected boolean suppressValidation(HttpServletRequest request) {

		Object go = findInRequest(request, "_action");
		if (go instanceof String && ((String) go).equalsIgnoreCase("go")) {
			return true;
		}
		return super.suppressValidation(request);
	}

	@Override
	protected void onBind(final HttpServletRequest request, final Object command, final BindException errors)
			throws Exception {
		log.debug("Entering onBind...");
		NewParticipantCommand participantCommand = (NewParticipantCommand) command;
		String searchtext = participantCommand.getSearchText();
		String type = participantCommand.getSearchType();
		List<StudySite> studySites = new ArrayList<StudySite>();
		List<Study> studies = null;
		// This will happen on page #2
		if (searchtext != null && type != null && !searchtext.equals("")) {
			log.debug("Search text : " + searchtext + "Type : " + type);
			Study study = new Study();
			StudyOrganization studyOrganization = new StudySite();
			studyOrganization.setStudy(study);
			studyOrganization.setOrganization(participantCommand.getOrganization());
			study.setStudyOrganizations(Arrays.asList(studyOrganization));

			participantCommand.setStudies(new ArrayList<Study>());
			if ("st".equals(type)) {
				study.setShortTitle(searchtext);
			}
			else if ("lt".equals(type)) {
				study.setLongTitle(searchtext);
			}
			else if ("idtf".equals(type)) {
				Identifier identifier = new Identifier();
				identifier.setValue(searchtext);
				study.addIdentifier(identifier);
				studies = studyDao.searchByExample(study, true);
			}

			if (studies == null) {
				studies = studyService.search(study);
			}
			participantCommand.setStudies(studies);
			participantCommand.setSearchTypeText("");
			participantCommand.setSearchType("");

			// participantCommand.setStudySiteArray(new String[] {});
			// participantCommand.setStudySites(new ArrayList<StudySite>());
		}

		// This will happen everytime studySiteArray is populated
		if (participantCommand.getStudySiteArray() != null) {
			for (String st : participantCommand.getStudySiteArray()) {
				StudySite studySite = studySiteDao.getById(Integer.parseInt(st));
				studySites.add(studySite);
			}
			participantCommand.setStudySites(studySites);
		}
	}

	@Override
	protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response,
			final Object command, final BindException errors) throws Exception {
		log.debug("Entering Process Finish ...");
		NewParticipantCommand participantCommand = (NewParticipantCommand) command;
		Participant participant = participantCommand.createParticipant();
		participantDao.save(participant);

		ModelAndView modelAndView = new ModelAndView("par/par_confirm");
		modelAndView.addObject("participant", participant);
		modelAndView.addAllObjects(errors.getModel());
		response.sendRedirect("view?participantId=" + participant.getId() + "&type=confirm");
		return null;
		// return modelAndView;
	}

	private static class Tab extends gov.nih.nci.cabig.ctms.web.tabs.Tab<NewParticipantCommand> {
		public Tab(final String longTitle, final String shortTitle, final String viewName) {
			super(longTitle, shortTitle, viewName);
		}
	}

	@Override
	protected boolean suppressValidation(final HttpServletRequest request, final Object command) {
		// supress for ajax and delete requests
		Object isAjax = findInRequest(request, "_isAjax");
		if (isAjax != null) {
			return true;
		}
		String action = (String) findInRequest(request, "_action");
		if (org.apache.commons.lang.StringUtils.isNotEmpty(action)) {
			return true;
		}
		return super.suppressValidation(request, command);
	}

	@Override
	protected String getViewName(final HttpServletRequest request, final Object command, final int page) {
		Object subviewName = findInRequest(request, "_subview");
		if (subviewName != null) {
			return "par/ajax/" + subviewName;
		}
		else {
			return super.getViewName(request, command, page);
		}
	}

	/**
	 * Returns the value associated with the <code>attributeName</code>, if present in HttpRequest parameter, if not available, will
	 * check in HttpRequest attribute map.
	 */
	private Object findInRequest(final HttpServletRequest request, final String attributName) {

		Object attr = request.getParameter(attributName);
		if (attr == null) {
			attr = request.getAttribute(attributName);
		}
		return attr;
	}

	@Required
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}
