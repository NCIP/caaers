package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class CreateINDController extends SimpleFormController {

    private InputFieldGroupMap fieldMap;
    private InvestigationalNewDrugDao investigationalNewDrugDao;
    private OrganizationDao organizationDao;
    private InvestigatorDao investigatorDao;

    public CreateINDController() {
        setFormView("admin/ind_details");
        setSuccessView("admin/ind_details");
        setCommandClass(INDCommand.class);
        fieldMap = new InputFieldGroupMap();
        InputFieldGroup fieldGroup = new DefaultInputFieldGroup("main");
        InputField indNumberField = InputFieldFactory.createTextField("strINDNumber", "IND #", true);

        Map<Object, Object> holderTypeOptions = new LinkedHashMap<Object, Object>();
        holderTypeOptions.put("", "Select a value");
        holderTypeOptions.put("org", "Organization");
        holderTypeOptions.put("inv", "Investigator");
        InputField holderTypeField = InputFieldFactory.createSelectField("holderType", "IND held by?", true, holderTypeOptions);
        InputField sponsorField = InputFieldFactory.createAutocompleterField("strSponsorId", "IND Holder", true);
        sponsorField.getAttributes().put(InputField.ENABLE_CLEAR, true);
        // InputFieldAttributes.setDetails(sponsorField, "Enter a portion of the Sponsor name");

        fieldGroup.getFields().add(indNumberField);
        fieldGroup.getFields().add(holderTypeField);
        fieldGroup.getFields().add(sponsorField);
        fieldMap.addInputFieldGroup(fieldGroup);
    }

    @Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws Exception {
        INDCommand command = new INDCommand();
        command.setInvestigatorDao(investigatorDao);
        command.setOrganizationDao(organizationDao);
        return command;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command,
                    final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        refDataMap.put("fieldGroups", fieldMap);

        return refDataMap;
    }

    /**
     * Validate the form,if no errors found, save the InvestigationalNewDrug object. Then return to
     * the success view.
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
                    Object cmd, BindException errors) throws Exception {
        INDCommand command = (INDCommand) cmd;
        validate(command, errors);
        if (!errors.hasErrors()) {
            InvestigationalNewDrug iNewDrug = command.createInvestigationalNewDrug();
            investigationalNewDrugDao.save(iNewDrug);
            request.setAttribute("flashMessage",
                            "Successfully saved the Investigational New Drug details");
            command.reset();
        }
        Map map = this.referenceData(request, command, errors);
        map.putAll(errors.getModel());
        ModelAndView modelAndView = new ModelAndView(getSuccessView(), map);
        // needed for saving session state
        request.getSession().setAttribute(getFormSessionAttributeName(), command);

        return modelAndView;
    }

    public void validate(INDCommand command, BindException errors) {
        BeanWrapper commandBean = new BeanWrapperImpl(command);
        for (InputFieldGroup fieldGroup : fieldMap.values()) {
            for (InputField field : fieldGroup.getFields()) {
                field.validate(commandBean, errors);
            }
        }
        // check if the strINDNumber is a numeric value
        if (!StringUtils.isNumeric((String) commandBean.getPropertyValue("strINDNumber"))) {
            errors.rejectValue("strINDNumber", "ADM_IND_001", "IND# must be numeric");
        }
    }

    public InvestigationalNewDrugDao getInvestigationalNewDrugDao() {
        return investigationalNewDrugDao;
    }

    public void setInvestigationalNewDrugDao(InvestigationalNewDrugDao investigationalNewDrugDao) {
        this.investigationalNewDrugDao = investigationalNewDrugDao;
    }

    /**
     * @return the organizationDao
     */
    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    /**
     * @param organizationDao
     *                the organizationDao to set
     */
    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    /**
     * @return the investigatorDao
     */
    public InvestigatorDao getInvestigatorDao() {
        return investigatorDao;
    }

    /**
     * @param investigatorDao
     *                the investigatorDao to set
     */
    public void setInvestigatorDao(InvestigatorDao investigatorDao) {
        this.investigatorDao = investigatorDao;
    }

}
