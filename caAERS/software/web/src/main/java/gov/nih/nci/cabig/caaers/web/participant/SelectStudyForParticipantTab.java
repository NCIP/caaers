package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyParticipantAssignmentQuery;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.ReadonlyFieldDecorator;
import gov.nih.nci.cabig.caaers.web.fields.SecurityObjectIdFieldDecorator;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 */

public class SelectStudyForParticipantTab <T extends ParticipantInputCommand> extends TabWithFields<T> {

    private final String STUDY_SUBJECT_IDENTIFIER_FIELD_GROUP = "studySubjectIdentifier";
    private final String STUDY_SUBJECT_IDENTIFIER_FIELD = "assignment.studySubjectIdentifier";
    
    StudyRepository studyRepository;
    private StudySiteDao studySiteDao;
    public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	private StudyDao studyDao;

    public SelectStudyForParticipantTab() {
        super("Choose Study", "Choose Study", "par/par_create_choose_study");
        addFieldDecorators(new SecurityObjectIdFieldDecorator(Participant.class), new ReadonlyFieldDecorator());
    }

    private ListValues listValues;

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, T command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        int studyId = 0;

        try {
            if (StringUtils.isNotEmpty(request.getParameter("studyId")))
                studyId = Integer.parseInt(request.getParameter("studyId"));
        } catch (NumberFormatException e) {
            // do not need to process this exception, just ignore the studyId value
        }

        if (StringUtils.isEmpty(command.getSearchText()) && studyId > 0) {
            Study s = studyRepository.getById(studyId);
            if (s != null) {
                command.setSearchText(s.getPrimaryIdentifier().getValue());
                command.setSearchType("idtf");
            }
        }

        return refdata;
    }

    @Override
    public Map<String, Object> referenceData(T command) {
        Map<String, Object> refdata = super.referenceData(command);
        refdata.put("searchType", listValues.getStudySearchType());
        return refdata;
    }

    public void onBind(HttpServletRequest request, T command, Errors errors) {
        super.onBind(request, command, errors);
        if (command.getStudy() != null && command.getStudy().getId() != null) {
            StudySite studySite = studySiteDao.findByStudyAndOrganization(command.getStudy().getId(), command.getOrganization().getId(), true);
            command.getAssignment().setStudySite(studySite);
            command.getAssignment().setParticipant(command.getParticipant());
            studySite.getStudy().getPrimaryIdentifier();
        }
    }

    public void postProcess(HttpServletRequest request, T command, Errors errors) {
        super.postProcess(request, command, errors);
        command.getParticipant().addAssignment(command.getAssignment());
    }

    @Override
    protected void validate(T command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        boolean studySiteArray = (command.getStudy() == null || command.getStudy().getId() == null);

        if (studySiteArray) {
            errors.rejectValue("studySiteArray", "PT_012", "Please select a Study to Continue");
        }

        if (StringUtils.isEmpty(command.assignment.getStudySubjectIdentifier())) {
            errors.rejectValue("assignment.studySubjectIdentifier", "PT_003", "Specify the Study Subject Identifier");

            // this is because the previewsly selected study is not selected anymore in the radiobuttons, causing the error on continue
            command.setStudy(null);
        }

        Integer pID = command.getAssignment().getParticipant().getId();

        StudyParticipantAssignmentQuery query = new StudyParticipantAssignmentQuery();
        query.filterByStudySiteId(command.getAssignment().getStudySite().getId());
        query.filterByStudySubjectIdentifier(command.getAssignment().getStudySubjectIdentifier());

        if (pID != null) {
            query.filterByParticipantExcluded(pID);
        }

        List l = studySiteDao.search(query);
        if (l.size() > 0) {
            errors.reject("ERR_DUPLICATE_STUDY_SITE_IDENTIFIER", new Object[] {command.getAssignment().getStudySubjectIdentifier(), command.getAssignment().getStudySite().getStudy().getShortTitle(), command.getAssignment().getStudySite().getOrganization().getName()}, "Duplicate Study Site identifier.");
        }
        
        // Check uniqueness of Study Subject identifier across study
        for(StudyParticipantAssignment assignment : command.getAssignments()){
        	if(assignment.getId() != null){
        		// the assignment is already saved, so the number of study subjects in same study that share the same study subject identifier should not be more than 1
        		validateUniqueStudySubjectIdentifiersInStudy(assignment.getStudySite().getStudy(),errors,1, assignment.getStudySubjectIdentifier());
        	} else {
        		validateUniqueStudySubjectIdentifiersInStudy(assignment.getStudySite().getStudy(),errors,0,assignment.getStudySubjectIdentifier());
        	}
        	
        }
    }
    
    protected void validateUniqueStudySubjectIdentifiersInStudy(Study study, Errors errors, int repitionCount, String studySubjectIdentifier){
		if(studyDao.checkIfStudyHasRepeatedAssignmentIdentifiers(study, repitionCount)){
			errors.reject("PT_013",new Object[]{studySubjectIdentifier},"The same study subject identifier, cannot be assigned" +
					" to more than one subject across the study");
		}
}

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(T command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        InputFieldGroup studySubjectIdentifierFieldGroup = new DefaultInputFieldGroup(STUDY_SUBJECT_IDENTIFIER_FIELD_GROUP);
        studySubjectIdentifierFieldGroup.getFields().add(InputFieldFactory.createTextField(STUDY_SUBJECT_IDENTIFIER_FIELD, "Study subject identifier", true));
        map.addInputFieldGroup(studySubjectIdentifierFieldGroup);
        return map;
    }

    public void setListValues(final ListValues listValues) {
        this.listValues = listValues;
    }

    /**
     *  this is an Ajaxable method called using <tags:tabMethod>
     */
    public ModelAndView searchStudies(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        return new ModelAndView(getAjaxViewName(request), map);
    }

    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public StudySiteDao getStudySiteDao() {
        return studySiteDao;
    }

    public void setStudySiteDao(StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }
}

/*
        if (participantCommand.getStudySiteArray() != null) {
            Set<String> studySiteIdSet = new java.util.HashSet<String>(java.util.Arrays.asList(participantCommand.getStudySiteArray()));
            for (String studyId : studySiteIdSet) {
                StudySite studySite = studySiteDao.findByStudyAndOrganization(Integer.parseInt(studyId),participantCommand.getOrganization().getId());
                studySites.add(studySite);

            }
            Participant participant = participantCommand.getParticipant();
            List<StudyParticipantAssignment> assignments = new ArrayList<StudyParticipantAssignment>();
            for (int i = 0; i < studySites.size(); i++) {
                final StudyParticipantAssignment studyParticipantAssignment = new StudyParticipantAssignment(participant, studySites.get(i));
                studyParticipantAssignment.setStudySubjectIdentifier(participantCommand.getStudySubjectIdentifier());
                assignments.add(studyParticipantAssignment);
            }
            participant.setAssignments(assignments);
            participantCommand.setStudySites(studySites);
        }
*/

