package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.workflow.StudySiteWorkflowConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
class SitesTab extends StudyTab {

    private RepeatingFieldGroupFactory rfgFactory;
    private WorkflowConfigDao workflowConfigDao;
     
    public SitesTab() {
        super("Sites", "Sites", "study/study_sites");
    }

    @Override
    public void postProcess(HttpServletRequest request, StudyCommand command, Errors errors) {
        String action = request.getParameter("_action");
        Object isAjax = request.getAttribute("_isAjax");

        if (isAjax != null) return;

        if ("removeSite".equals(action)) {

            int index = Integer.parseInt(request.getParameter("_selected"));
            StudySite site = command.getStudy().getStudySites().get(index);

            if (site.getId() != null) {
                if (CollectionUtils.isNotEmpty(site.getActiveStudyInvestigators())) {
                  errors.reject("STU_013", "The site is associated to investigators, so unable to delete");
                }
                if (CollectionUtils.isNotEmpty(site.getActiveStudyPersonnel())) {
                  errors.reject("STU_014", "The site is associated to research staffs, so unable to delete");
                }
            }
            
            //remove site, if no investigator or research person is associated to site.
            if(!errors.hasErrors()){
            	command.deleteStudySiteAtIndex(index);
                command.setStudySiteIndex(-1);
            }
            
            
        }

        // checking new Study Sites
        HashMap<Integer, StudyOrganization> hm = new HashMap<Integer, StudyOrganization>();
        List<StudyOrganization> copySS = new ArrayList(command.getStudy().getStudyOrganizations());

        int i = 0;
        for (StudyOrganization ss : copySS) {
            if (ss instanceof StudySite && ss.getOrganization() != null && ss.getOrganization().getId() != null) {
                if (ss.getId() == null) {
                    StudyOrganization existingStudySite = hm.get(ss.getOrganization().getId());
                    if (existingStudySite != null) {
                        if (existingStudySite.getRetiredIndicator()) {
                            command.getStudy().getStudyOrganizations().remove(i);
                            i--;
                            existingStudySite.setRetiredIndicator(false);
                        }
                    }
                } else {
                    hm.put(ss.getOrganization().getId(), ss);
                }
            }
            i++;
        }

        command.getStudyRepository().synchronizeStudyPersonnel(command.getStudy());

    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(StudyCommand command) {
        if (rfgFactory == null) {
            rfgFactory = new RepeatingFieldGroupFactory("main", "study.studySites");
            InputField siteField = InputFieldFactory.createAutocompleterField("organization", "Site", true);
            siteField.getAttributes().put(InputField.ENABLE_CLEAR, true);
            rfgFactory.addField(siteField);
        }
        InputFieldGroupMap map = new InputFieldGroupMap();
        Study study = command.getStudy();
        map.addRepeatingFieldGroupFactory(rfgFactory, study.getStudySites().size());
        return map;
    }
    

    @Override
    protected void validate(StudyCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        // check if there are duplicate sites.
        HashSet<Integer> set = new HashSet<Integer>();
        int size = command.getStudy().getStudySites().size();
        StudySite site = null;
        for(int i = 0; i < size; i++){
        	site = command.getStudy().getStudySites().get(i);
        	if(site.isRetired() || site.getOrganization() == null) continue;
        	
        	if(!set.add(site.getOrganization().getId())){
        		rejectFields(fieldGroups.get("main" + i).getFields(), errors, "Duplicate");
        	}
        }
        
    }
    
    public void setWorkflowConfigDao(WorkflowConfigDao workflowConfigDao) {
		this.workflowConfigDao = workflowConfigDao;
	}

}
