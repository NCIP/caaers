/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 * @author Ion C. Olaru
 */
public class DetailsTab extends StudyTab {

    private CtcDao ctcDao;
    private MeddraVersionDao meddraVersionDao;


    public DetailsTab() {
        super("Details", "Details", "study/study_details");
        setAutoPopulateHelpKey(true);
        /*addHelpKeyExclusion("multiInstitutionIndicator", "adeersReporting", "term", "ctcVersion", "meddraVersion", "diseaseCodeTerm");*/
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, StudyCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        if (command.getStudy().getAeTerminology().getTerm() == null) {
            command.getStudy().getAeTerminology().setTerm(Term.CTC);
        }
        return refdata;
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final StudyCommand command) {


        // TODO: Add validation for when terminology.term = Term.CTC
        List<Ctc> ctcList = ctcDao.getAll();

        InputFieldGroup fieldGroup = null,
                        fundSponsorFieldGroup = null,
                        coordinatingCenterFieldGroup = null;

        if (fieldGroup == null) {
            // set up the fields
            fieldGroup = new DefaultInputFieldGroup("studyDetails");
            List<InputField> fields = fieldGroup.getFields();
            InputField shortTitleField = InputFieldFactory.createTextArea("study.shortTitle", "Title", true);
            InputFieldAttributes.setColumns(shortTitleField, 100);
            fields.add(shortTitleField);

            fields.add(InputFieldFactory.createSelectField("study.phaseCode", "Phase", true, collectOptionsFromConfig("phaseCodeRefData", "desc", "desc")));
            fields.add(InputFieldFactory.createSelectField("study.aeReportingLevel", "AE reporting level", false, collectOptionsFromConfig("aeReportingLevelRefData", "desc", "desc")));

            Map<Object, Object> options = new LinkedHashMap<Object, Object>();
            options.put("", "Please select");
            options.put(Boolean.FALSE, "No");
            options.put(Boolean.TRUE, "Yes");

            Map<Object, Object> verbatimFirstOptions = new LinkedHashMap<Object, Object>();
            verbatimFirstOptions.put(Boolean.FALSE, "No");
            verbatimFirstOptions.put(Boolean.TRUE, "Yes");

            fields.add(InputFieldFactory.createSelectField("study.verbatimFirst", "verbatimFirst", false, verbatimFirstOptions));
            fields.add(InputFieldFactory.createLabelField("dataEntryStatus", "Data Entry Status", false));

            Map<Object, Object> studyPurposeMap = new LinkedHashMap<Object, Object>();
            studyPurposeMap.put("", "Please select");
            fields.add(InputFieldFactory.createSelectField("study.studyPurpose", "Study Purpose", false, collectOptionsFromConfig("studyPurposeRefData", "code", "desc")));

            fields.add(InputFieldFactory.createSelectField("study.participationType", "Participation type", false, collectOptionsFromConfig("studyParticipationTypeRefData", "code", "desc")));

        }


        if (fundSponsorFieldGroup == null) {
            fundSponsorFieldGroup = new DefaultInputFieldGroup("fsFieldGroup");
            List<InputField> fields = fundSponsorFieldGroup.getFields();
            InputField sponsorField = InputFieldFactory.createAutocompleterField("study.primaryFundingSponsorOrganization", "Funding sponsor", true);
            // sponsorField.getAttributes().put(InputField.DETAILS,"Enter a portion of the sponsor
            // name you are looking for");
            fields.add(sponsorField);
            InputField sponsorIdentiferField = InputFieldFactory.createTextField("study.identifiersLazy[0].value", "Funding sponsor study identifier", true);
            fields.add(sponsorIdentiferField);

        }

        if (coordinatingCenterFieldGroup == null) {
            coordinatingCenterFieldGroup = new DefaultInputFieldGroup("ccFieldGroup");
            List<InputField> fields = coordinatingCenterFieldGroup.getFields();
            fields.add(InputFieldFactory.createAutocompleterField("study.studyCoordinatingCenter.organization", "Coordinating center", true));
            fields.add(InputFieldFactory.createTextField("study.identifiersLazy[1].value", "Coordinating center study identifier", true));

        }
        
        // Create fieldGroup for AeTerminology
        List<MeddraVersion> meddraVersions = meddraVersionDao.getAll();
        List<Term> terms = null;
        if (meddraVersions.size() == 0) {
            terms = new ArrayList(Arrays.asList(Term.CTC));
        } else {
            terms = new ArrayList(Arrays.asList(Term.values()));
        }

        InputFieldGroup studyCodeFieldGroup = new DefaultInputFieldGroup("scFieldGroup");
        List<InputField> scFields = studyCodeFieldGroup.getFields();
        
        scFields.add(InputFieldFactory.createSelectField("study.aeTerminology.term", "Terminology", true, WebUtils.collectOptions(terms, null, "displayName")));
        scFields.add(InputFieldFactory.createSelectField("study.aeTerminology.ctcVersion", "CTC version", false, collectOptions(ctcList, "id", "name")));
        if (meddraVersions.size() > 0) {
            scFields.add(InputFieldFactory.createSelectField("study.aeTerminology.meddraVersion", "MedDRA version", false, collectOptions(meddraVersions, "id", "name")));
            scFields.add(InputFieldFactory.createSelectField("study.otherMeddra", "Other MedDRA Version", false, collectOptions(meddraVersions,"id", "name")));
        }

        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        options.put(Boolean.FALSE, "No");
        options.put(Boolean.TRUE, "Yes");
        scFields.add(InputFieldFactory.createSelectField("study.aeTermUnique", "Enforce AE term uniqueness", true,
                options));


        // Create fieldGroup for DiseaseTerminology
        InputFieldGroup studyDiseaseCodeFieldGroup = new DefaultInputFieldGroup("sdcFieldGroup");
        List<InputField> sdFields = studyDiseaseCodeFieldGroup.getFields();
        Map<Object, Object> diseaseOpt = new LinkedHashMap<Object, Object>();

        List<DiseaseCodeTerm> diseaseCodeTerms = null; 
        if (meddraVersions.size() == 0) {
            diseaseCodeTerms = new ArrayList(Arrays.asList(DiseaseCodeTerm.CTEP, DiseaseCodeTerm.OTHER));
        } else {
            diseaseCodeTerms = new ArrayList(Arrays.asList(DiseaseCodeTerm.values()));
        }
        diseaseOpt.put("", "Please select");
        diseaseOpt.putAll(WebUtils.collectOptions(diseaseCodeTerms, null, "displayName"));
        sdFields.add(InputFieldFactory.createSelectField("study.diseaseTerminology.diseaseCodeTerm", "Terminology", true,  diseaseOpt));
        if (meddraVersions.size() > 0) {
            sdFields.add(InputFieldFactory.createSelectField("study.diseaseTerminology.meddraVersion", "MedDRA version", false, collectOptions(meddraVersions, "id", "name")));
        }


        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(fieldGroup);
        map.addInputFieldGroup(fundSponsorFieldGroup);
        map.addInputFieldGroup(coordinatingCenterFieldGroup);
        map.addInputFieldGroup(studyCodeFieldGroup);
        map.addInputFieldGroup(studyDiseaseCodeFieldGroup);

        return map;
    }

    @Override
    protected void validate(final StudyCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {

        // Adverse event coding terminology
        if (command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA && command.getStudy().getAeTerminology().getMeddraVersion() == null) {
            InputField field = fieldGroups.get("scFieldGroup").getFields().get(2);
            errors.rejectValue(field.getPropertyName(), "STU_006", new Object[]{field.getDisplayName()}, "Missing " + field.getDisplayName());
        }

        if (command.getStudy().getAeTerminology().getTerm() == Term.CTC) {

            if (command.getStudy().getAeTerminology().getCtcVersion() == null) {
                InputField field = fieldGroups.get("scFieldGroup").getFields().get(1);
                errors.rejectValue(field.getPropertyName(), "STU_006",new Object[]{field.getDisplayName()}, "Missing " + field.getDisplayName());
            }

        }

        // Disease coding terminology
        // This is to validate if meddra version is selected incase the disease term is of type meddra
        if (command.getStudy().getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.MEDDRA && command.getStudy().getDiseaseTerminology().getMeddraVersion() == null) {
        	InputField field = fieldGroups.get("sdcFieldGroup").getFields().get(1);
        	errors.rejectValue(field.getPropertyName(), "STU_006",new Object[]{field.getDisplayName()}, "Missing " + field.getDisplayName());
        }
        
        Study aStudy;
        
        //validate Sponsor - identifiers
      
        if(command.getStudy().getPrimaryFundingSponsorOrganization() != null){
        	OrganizationAssignedIdentifier sponsorIdentifier = (OrganizationAssignedIdentifier)command.getStudy().getIdentifiers().get(0);
            InputField sponsorIdentifierField = fieldGroups.get("fsFieldGroup").getFields().get(1);
        	OrganizationAssignedIdentifier identifier = new OrganizationAssignedIdentifier();
        	identifier.setType(sponsorIdentifier.getType());
        	identifier.setOrganization(command.getStudy().getPrimaryFundingSponsorOrganization());
        	identifier.setValue(sponsorIdentifier.getValue());
        	aStudy = command.checkForDuplicateStudyByIdentifier(identifier);
        	if(aStudy != null){
        		errors.rejectValue(sponsorIdentifierField.getPropertyName(), "STU_021", new Object[]{aStudy.getShortTitle(), aStudy.getPrimaryIdentifierValue()}, 
				"The primary identifier you choose for this study is present in another study");
        	}
        }
        
        //validate Coordinating center - identifiers
      
        if(command.getStudy().getStudyCoordinatingCenter() != null && command.getStudy().getStudyCoordinatingCenter().getOrganization() != null){
        	OrganizationAssignedIdentifier ccIdentifier = (OrganizationAssignedIdentifier)command.getStudy().getIdentifiers().get(1);
            InputField ccIdentifierField = fieldGroups.get("ccFieldGroup").getFields().get(1);
        	OrganizationAssignedIdentifier identifier = new OrganizationAssignedIdentifier();
        	identifier.setType(ccIdentifier.getType());
        	identifier.setOrganization(command.getStudy().getStudyCoordinatingCenter().getOrganization());
        	identifier.setValue(ccIdentifier.getValue());
        	aStudy = command.checkForDuplicateStudyByIdentifier(identifier);
        	if(aStudy != null){
        		errors.rejectValue(ccIdentifierField.getPropertyName(), "STU_021", new Object[]{aStudy.getShortTitle(), aStudy.getPrimaryIdentifierValue()}, 
				"The primary identifier you choose for this study is present in another study");
        	}
        }
    }

    @Override
    public void postProcess(final HttpServletRequest request, final StudyCommand command, final Errors errors) {
        super.postProcess(request, command, errors);


        if (errors.hasErrors()) {
            return;
        }

        for (int i = 0; i <= 1; i++) {
            OrganizationAssignedIdentifier identifier = (OrganizationAssignedIdentifier) command.getStudy().getIdentifiers().get(i);
            if (identifier.getType().equals(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE)) {
                identifier.setOrganization(command.getStudy().getPrimaryFundingSponsorOrganization());
            }
            if (identifier.getType().equals(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE)) {
                identifier.setOrganization(command.getStudy().getStudyCoordinatingCenter().getOrganization());
            }
        }

        if (!command.getPrevCC().equals(command.getStudy().getStudyCoordinatingCenter())) {
            command.getStudyRepository().clearStudyPersonnel(command.getStudy().getStudyCoordinatingCenter());
            command.getStudyRepository().clearStudyInvestigators(command.getStudy().getStudyCoordinatingCenter());
            command.setPrevCC(command.getStudy().getStudyCoordinatingCenter());
        }
        
        if (!command.getPrevFS().equals(command.getStudy().getPrimaryFundingSponsor())) {
            command.getStudyRepository().clearStudyPersonnel(command.getStudy().getPrimaryFundingSponsor());
            command.getStudyRepository().clearStudyInvestigators(command.getStudy().getPrimaryFundingSponsor());
            command.setPrevFS(command.getStudy().getPrimaryFundingSponsor());
        }

        request.setAttribute("tabFlashMessage", messageSource.getMessage(String.format("MSG_study.%s.flash_message", this.getClass().getSimpleName()), null, Locale.getDefault()));
    }

    public CtcDao getCtcDao() {
        return ctcDao;
    }

    public void setCtcDao(final CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    public MeddraVersionDao getMeddraVersionDao() {
        return meddraVersionDao;
    }

    public void setMeddraVersionDao(final MeddraVersionDao meddraVersionDao) {
        this.meddraVersionDao = meddraVersionDao;
    }
}
