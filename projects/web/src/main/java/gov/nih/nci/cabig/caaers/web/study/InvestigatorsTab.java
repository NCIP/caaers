package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
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
 */
class InvestigatorsTab extends StudyTab {
    private List<InputField> fields;

    public InvestigatorsTab() {
        super("Investigators", "Investigators", "study/study_investigators");
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, Study study) {
        Map<String, Object> refdata = super.referenceData(request, study);
        addConfigMapToRefdata(refdata, "invRoleCodeRefData");
        addConfigMapToRefdata(refdata, "invStatusCodeRefData");
        return refdata;
    }

    @Override
    public void postProcess(HttpServletRequest request, Study study, Errors errors) {
        String action = request.getParameter("_action");
        String selectedInvestigator = request.getParameter("_selectedInvestigator");
        String prevSiteIndex = request.getParameter("_prevSite");
        int selectedIndex = study.getStudySiteIndex();
        if ("removeInv".equals(action) && selectedIndex >= 0) {
        	
         StudyOrganization studyOrg = study.getStudyOrganizations().get(selectedIndex);
	         if(studyOrg != null){
	        	 StudyInvestigator rmStudyInvestigator = studyOrg.getStudyInvestigators().remove(
	                     Integer.parseInt(selectedInvestigator));
	        	 if(rmStudyInvestigator != null){
	        		 rmStudyInvestigator.setStudyOrganization(null);
	        		 SiteInvestigator siteInvestigator = rmStudyInvestigator.getSiteInvestigator();
	        		 if(siteInvestigator != null){
	        			 siteInvestigator.getStudyInvestigators().remove(rmStudyInvestigator);
	        	         rmStudyInvestigator.setSiteInvestigator(null);
	        		 }
	        	 }
	         }
         
        } else if ("changeSite".equals(action) && errors.hasErrors()) {
            int siteIndex = Integer.parseInt(prevSiteIndex);
            study.setStudySiteIndex(siteIndex);
            if (siteIndex >= 0) {
                study.getStudyOrganizations().get(siteIndex).getStudyInvestigators().get(0);
            }
        }
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(Study command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        InputFieldGroup siteFieldGroup = new DefaultInputFieldGroup("site");
        siteFieldGroup.getFields().add(
                        InputFieldFactory.createSelectField("studySiteIndex", "Site", true,
                        		WebUtils.collectOptions(
                                                        collectStudyOrganizations(command), "code",
                                                        "desc")));
        map.addInputFieldGroup(siteFieldGroup);

        if (fields == null) {
            fields = new ArrayList<InputField>();
            InputField investigatorField = InputFieldFactory.createAutocompleterField(
                            "siteInvestigator", "Investigator", true);
            // sponsorField.getAttributes().put(InputField.DETAILS,"Enter a portion of the
            // investigator name you are looking for");
            fields.add(investigatorField);
            fields.add(InputFieldFactory.createSelectField("roleCode", "Role", true,
                            collectOptionsFromConfig("invRoleCodeRefData", "desc", "desc")));
            fields.add(InputFieldFactory.createSelectField("statusCode", "Status", true,
                            collectOptionsFromConfig("invStatusCodeRefData", "desc", "desc")));
        }

        int ssIndex = command.getStudySiteIndex();
        if (ssIndex >= 0) {
            RepeatingFieldGroupFactory rfgFactory = new RepeatingFieldGroupFactory("main",
                            "studyOrganizations[" + ssIndex + "].studyInvestigators");
            for (InputField f : fields) {
                rfgFactory.addField(f);
            }
            map.addRepeatingFieldGroupFactory(rfgFactory, command.getStudyOrganizations().get(
                            ssIndex).getStudyInvestigators().size());
        }
        return map;
    }

    @Override
    protected void validate(Study study, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        super.validate(study, commandBean, fieldGroups, errors);
        int soIndex = -1;

        for (StudyOrganization studyOrg : study.getStudyOrganizations()) {
            soIndex++;
            int siIndex = -1;
            HashSet<StudyInvestigator> hSet = new HashSet<StudyInvestigator>();
            for (StudyInvestigator si : studyOrg.getStudyInvestigators()) {
                siIndex++;
                if (!hSet.add(si)) {
                    errors.rejectValue("studyOrganizations[" + soIndex + "].studyInvestigators["
                                    + siIndex + "].siteInvestigator", "DUPLICATE",
                                    "Duplicate entry");
                }
            }
        }
    }

}
