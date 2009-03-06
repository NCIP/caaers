package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 */

public class AssignStudyTab extends TabWithFields<AssignParticipantStudyCommand> {
    private static final Log log = LogFactory.getLog(AssignStudyTab.class);
    private OrganizationDao organizationDao;

    private ParticipantDao participantDao;

    public AssignStudyTab() {
        super("Search for Studies", "Search Study", "par/reg_study_search");
    }

    @Override
    public Map<String, Object> referenceData(AssignParticipantStudyCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        refdata.put("assignType", "study");


        return refdata;
    }

    public ModelAndView searchStudies(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        return new ModelAndView(getAjaxViewName(request), map);
    }

    @Override
    protected void validate(AssignParticipantStudyCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);

        if (command.getStudySite() == null || command.getStudySite().getId() == null) {
            errors.rejectValue("assignment.studySite", "PT_008", "Select the Study Site");
        } else {
            participantDao.reassociate(command.getParticipant());
            List<StudyParticipantAssignment> l;
            l = command.getParticipant().getAssignments();

            for (StudyParticipantAssignment assignment : l) {
                if (assignment.getStudySite().getId().intValue() == command.getStudySite().getId().intValue()) {
                    errors.reject("PT_009", new Object[]{command.getParticipant().getFullName(), assignment.getStudySite().getStudy().getShortTitle(), assignment.getStudySite().getOrganization().getFullName()}, "Duplicate assignment.");
                    break;
                }
            }
        }

        if (StringUtils.isEmpty(command.getStudySubjectIdentifier())) {
            errors.rejectValue("assignment.studySubjectIdentifier", "PT_003", "Specify the Study Subject Identifier");
        }


    }

    public void postProcess(HttpServletRequest request, AssignParticipantStudyCommand command, Errors errors) {
        super.postProcess(request, command, errors);
        if (command.getStudySite() != null) {
            command.setStudy(command.getStudySite().getStudy());
            command.getStudySite().getStudy().getPrimaryIdentifier();
        }
    }

    
//    @Override
//    public void beforeBind(HttpServletRequest request,AssignParticipantStudyCommand command) {
//    	super.beforeBind(request, command);
//    	StudySite site = command.getStudySite();
//    	if(site != null)
//    		organizationDao.lock(command.getStudySite().getOrganization());
//    }
//    
    
    public Map<String, InputFieldGroup> createFieldGroups(AssignParticipantStudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }
    public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}
    public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}