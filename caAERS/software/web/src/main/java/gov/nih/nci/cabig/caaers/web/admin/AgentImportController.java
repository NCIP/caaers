package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/*
* @author Ion C. Olaru
*
* */

public class AgentImportController extends SimpleFormController {

    private AgentDao agentDao;
    private AgentSpecificTermDao agentSpecificTermDao;
    private AgentSpecificAdverseEventListService service;

    public AgentImportController() {
        setFormView("admin/agentImportForm");
        setSuccessView("admin/agentImportForm");
        setCommandClass(AgentCommand.class);
    }

    @Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        // binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws Exception {
        AgentCommand command = new AgentCommand();
        return command;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors) throws Exception {
        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        return refDataMap;
    }

    /**
     * Validate the form,if no errors found, save the InvestigationalNewDrug object. Then return to
     * the success view.
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        AgentCommand c = (AgentCommand) command;
        validate(c, errors);
        if (!errors.hasErrors()) {
        }
        Map map = this.referenceData(request, c, errors);
        map.putAll(errors.getModel());
        ModelAndView modelAndView = new ModelAndView(getSuccessView(), map);
        return modelAndView;
    }

    public void validate(AgentCommand command, BindException errors) {
    }

}