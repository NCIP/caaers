/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.domain.Device;
import gov.nih.nci.cabig.caaers.domain.repository.DeviceRepository;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* @author Ion C. Olaru
* 
* */

public class DeviceController extends AutomaticSaveAjaxableFormController<DeviceCommand, Device, DeviceDao> {

    protected static final Log log = LogFactory.getLog(DeviceController.class);

    protected DeviceRepository deviceRepository;
    protected DeviceDao deviceDao;

    @Override
    protected Device getPrimaryDomainObject(DeviceCommand command) {
        return command.getDevice();
    }

    @Override
    protected DeviceDao getDao() {
        return deviceDao;
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        return null;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
        request.getSession().removeAttribute(DeviceEditController.class.getName() + ".FORM.command");
        request.getSession().removeAttribute(DeviceCreateController.class.getName() + ".FORM.command");
        return null;
    }

    @Override
    protected DeviceCommand save(DeviceCommand command, Errors errors) {
        getDao().save(getPrimaryDomainObject(command));
        return command;
    }

    @Override
    protected boolean shouldSave(final HttpServletRequest request, final DeviceCommand command, final Tab<DeviceCommand> tab) {
        return true;
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        ControllerTools.registerDomainObjectEditor(binder, deviceDao);
    }

    public DeviceRepository getDeviceRepository() {
        return deviceRepository;
    }

    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }
}
