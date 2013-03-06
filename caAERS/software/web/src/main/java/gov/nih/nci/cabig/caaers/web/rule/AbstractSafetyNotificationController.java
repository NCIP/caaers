/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Notification;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.report.ReportType;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Biju Joseph
 * @date 3/22/12
 */
public class AbstractSafetyNotificationController extends AutomaticSaveAjaxableFormController<ManageSafetyNotificationCommand, Notification , NotificationDao> {
    
    protected StudyDao studyDao;
    protected NotificationDao notificationDao;
    protected Map<String, String> roles;


    public AbstractSafetyNotificationController() {
        setBindOnNewForm(false);
    }



    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        ControllerTools.registerDomainObjectEditor(binder, studyDao);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        
        ManageSafetyNotificationCommand command = new ManageSafetyNotificationCommand();
        String nfId = request.getParameter("id");
        if(StringUtils.isEmpty(nfId)){
            command.setNotification(new Notification());
            command.setMode("Create");
        }else{
            command.setMode("Edit");
            Notification nf = notificationDao.getById(NumberUtils.toInt(nfId));
            if(nf == null) nf = new Notification();
            command.setNotification(nf);
        }
        command.setRoles(roles);
       
        return command;
    }

    @Override
    protected Notification getPrimaryDomainObject(ManageSafetyNotificationCommand command) {
        return command.getNotification();
    }

    @Override
    protected NotificationDao getDao() {
        return notificationDao;
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, BindException e) throws Exception {
        if(!e.hasErrors()){
            ManageSafetyNotificationCommand cmd = (ManageSafetyNotificationCommand) o;
            notificationDao.save(cmd.getNotification());
        }
        Map<String, Object> model = new ModelMap();
        return new ModelAndView("redirectToSafetyList", model);
    }

    @Required
    public NotificationDao getNotificationDao() {
        return notificationDao;
    }

    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }
}
