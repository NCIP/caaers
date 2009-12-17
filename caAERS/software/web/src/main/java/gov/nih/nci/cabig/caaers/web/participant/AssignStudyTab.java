package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyParticipantAssignmentQuery;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
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
import com.semanticbits.rules.utils.NumberUtil;

/**
 * @author Ion C. Olaru
 */

public class AssignStudyTab extends TabWithFields<AssignParticipantStudyCommand> {
    private static final Log log = LogFactory.getLog(AssignStudyTab.class);
    private OrganizationDao organizationDao;
    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private StudySiteDao studySiteDao;

    public AssignStudyTab() {
        super("Search for Studies", "Search Study", "par/reg_study_search");
    }

    @Override
    public Map<String, Object> referenceData(AssignParticipantStudyCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        command.setOrganization(command.getParticipant().getAssignments().get(0).getStudySite().getOrganization());
        refdata.put("assignType", "study");
        return refdata;
    }

    public ModelAndView searchStudies(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        return new ModelAndView(getAjaxViewName(request), map);
    }

    @Override
    public void beforeBind(HttpServletRequest request, AssignParticipantStudyCommand command) {
        super.beforeBind(request, command);
        command.setStudySite(null);
    }

    @Override
    public void onBind(HttpServletRequest request, AssignParticipantStudyCommand command, Errors errors) {
        super.onBind(request, command, errors);
        if (command.getStudySite() != null) {
            studySiteDao.lock(command.getStudySite());
            command.setStudy(command.getStudySite().getStudy());
        }
    }

    @Override
    protected void validate(AssignParticipantStudyCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);

        List<StudyParticipantAssignment> assignments;
        assignments = command.getParticipant().getAssignments();

        if (command.getStudySite() == null) {
            errors.rejectValue("assignment.studySite", "PT_008", "Select the Study Site");
        } else {
            for (StudyParticipantAssignment assignment : assignments) {
                if (assignment.getStudySite().getId().intValue() == command.getStudySite().getId().intValue()) {
                    errors.reject("PT_009", new Object[]{command.getParticipant().getFullName(), command.getStudySite().getStudy().getShortTitle(), command.getStudySite().getOrganization().getFullName()}, "Duplicate assignment.");
                    return;
                }
            }
        }

        if (StringUtils.isEmpty(command.getStudySubjectIdentifier())) {
            errors.rejectValue("assignment.studySubjectIdentifier", "PT_003", "Specify the Study Subject Identifier");
            return;
        }

        // Checking Study-Subject identifiers, uniqueness for the selected StudySite
        Integer pID = command.getParticipant().getId();
        StudyParticipantAssignmentQuery query = new StudyParticipantAssignmentQuery();
        query.filterByStudySiteId(command.getStudySite().getId());
        query.filterByStudySubjectIdentifier(command.getStudySubjectIdentifier());
        query.filterByParticipantExcluded(pID);

        List l = studySiteDao.search(query);
        if (l.size() > 0) {
            errors.reject("ERR_DUPLICATE_STUDY_SITE_IDENTIFIER_", new Object[] {command.getStudySubjectIdentifier()}, "Duplicate Study Site identifier.");
        }
        
    }

/*
    public void postProcess(HttpServletRequest request, AssignParticipantStudyCommand command, Errors errors) {
        super.postProcess(request, command, errors);
        if (command.getStudySite() != null) {
            command.setStudy(command.getStudySite().getStudy());
            command.getStudySite().getStudy().getPrimaryIdentifier();
        }
    }
*/


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

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public StudySiteDao getStudySiteDao() {
        return studySiteDao;
    }

    public void setStudySiteDao(StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }
}