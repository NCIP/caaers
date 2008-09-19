package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.BeanWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Ion C. Olaru
 */

public class AssignStudyTab extends TabWithFields<AssignParticipantStudyCommand> {
    private static final Log log = LogFactory.getLog(AssignStudyTab.class);

    private StudyRepository studyRepository;

    public AssignStudyTab() {
        super("Search for Studies", "Search Study", "par/reg_protocol_search");
    }

    @Override
    public Map<String, Object> referenceData(AssignParticipantStudyCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        refdata.put("assignType", "study");

        String searchType = command.getSearchType();
        String searchText = command.getSearchText();

        log.debug("Search text : " + searchText + "Type : " + searchType);
        List<Study> studies = null;

        if (searchText != null && searchType != null && !StringUtils.isEmpty(searchText) && searchText.trim().length() >= 2) {

            Study exampleStudy = new Study();
            if (StringUtils.equals("st", searchType)) {
                exampleStudy.setShortTitle(searchText);
            } else if (StringUtils.equals("lt", searchType)) {
                exampleStudy.setLongTitle(searchText);
            } else if (StringUtils.equals("idtf", searchType)) {
                Identifier identifier = new Identifier();
                identifier.setValue(searchText);
                exampleStudy.addIdentifier(identifier);
            }
            try {
                studies = studyRepository.search(exampleStudy);
            } catch (Exception ex) {
                log.error("Error in search: ", ex);
            }

            command.setSearchText("");
            command.setStudies(studies);
        }


        return refdata;
    }

    public ModelAndView searchStudies(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        return new ModelAndView(getAjaxViewName(request), map);
    }

    @Override
    protected void validate(AssignParticipantStudyCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);

        if (StringUtils.isEmpty(command.getStudySubjectIdentifier())) {
            errors.rejectValue("assignment.studySubjectIdentifier", "PT_003", "Specify the Study Subject Identifier");
        }

        if (command.getStudySite() == null || command.getStudySite().getId() == null) {
            errors.rejectValue("assignment.studySite", "PT_008", "Select the Study Site");
        }
    }

    public void postProcess(HttpServletRequest request, AssignParticipantStudyCommand command, Errors errors) {
        super.postProcess(request, command, errors);
        if (command.getStudySite() != null) {
            command.setStudy(command.getStudySite().getStudy());
            command.getStudySite().getStudy().getPrimaryIdentifier();
        };
    }

    public void setStudyRepository(final StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public Map<String, InputFieldGroup> createFieldGroups(AssignParticipantStudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }
}