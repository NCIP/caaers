package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
class PersonnelTab extends StudyTab {
    private List<InputField> fields;

    public PersonnelTab() {
        super("Personnel", "Personnel", "study/study_personnel");
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, StudyCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        return refdata;
    }

    @Override
    public void postProcess(HttpServletRequest request, StudyCommand command, Errors errors) {
        String action = request.getParameter("_action");
        String selectedPersonnel = request.getParameter("_selectedPersonnel");
        String prevSiteIndex = request.getParameter("_prevSite");
        int selectedIndex = command.getStudySiteIndex();
        if ("removeStudyPersonnel".equals(action) && selectedIndex >= 0) {
        	command.deleteStudyPersonAtIndex(selectedIndex, Integer.parseInt(selectedPersonnel));
        } else if ("changeSite".equals(action) && errors.hasErrors()) {
            int siteIndex = Integer.parseInt(prevSiteIndex);
            command.setStudySiteIndex(siteIndex);
            if (siteIndex >= 0) {
                command.getStudy().getActiveStudyOrganizations().get(siteIndex).getStudyPersonnels().get(0);
            }
        }
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(StudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        InputFieldGroup siteFieldGroup = new DefaultInputFieldGroup("site");
        siteFieldGroup.getFields().add(InputFieldFactory.createSelectField("studySiteIndex", "Site", true, WebUtils.collectOptions(collectStudyOrganizations(command.getStudy()), "code", "desc")));
        map.addInputFieldGroup(siteFieldGroup);

        if (fields == null) {
            fields = new ArrayList<InputField>();
            InputField investigatorField = InputFieldFactory.createAutocompleterField("researchStaff", "Research Staff", true);
            fields.add(investigatorField);
            fields.add(InputFieldFactory.createSelectField("roleCode", "Role", true, collectOptionsFromConfig("studyPersonnelRoleRefData", "desc", "desc")));
            //TODO: BJ need to show startDate and endDate and deactivate/activate
            fields.add(InputFieldFactory.createLabelField("active", "Status", false));
        }

        int ssIndex = command.getStudySiteIndex();
        if (ssIndex >= 0) {
            RepeatingFieldGroupFactory rfgFactory = new RepeatingFieldGroupFactory("main", "study.activeStudyOrganizations[" + ssIndex + "].studyPersonnels");

            for (InputField f : fields) {
                rfgFactory.addField(f);
            }
            map.addRepeatingFieldGroupFactory(rfgFactory, command.getStudy().getActiveStudyOrganizations().get(ssIndex).getStudyPersonnels().size());
        }
        return map;
    }

    @Override
    protected void validate(StudyCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        int soIndex = -1;

        for (StudyOrganization studyOrg : command.getStudy().getActiveStudyOrganizations()) {
            soIndex++;
            int spIndex = -1;
            HashSet<StudyPersonnel> hSet = new HashSet<StudyPersonnel>();
            for (StudyPersonnel sp : studyOrg.getStudyPersonnels()) {
                spIndex++;
                if (!hSet.add(sp)) {
                    errors.rejectValue("study.activeStudyOrganizations[" + soIndex + "].studyPersonnels[" + spIndex + "].siteResearchStaff.researchStaff", "STU_012", "Duplicate entry");
                }
            }

        }
    }

}
