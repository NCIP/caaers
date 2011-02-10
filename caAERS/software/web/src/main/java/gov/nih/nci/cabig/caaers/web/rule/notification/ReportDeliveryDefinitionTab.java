package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.web.fields.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : Jun 22, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public class ReportDeliveryDefinitionTab extends TabWithFields<ReportDefinitionCommand> {

    public ReportDeliveryDefinitionTab() {
        super("Report Delivery Details", "Delivery Details", "rule/notification/reportDeliveryTab");
        addFieldDecorators(new SecurityObjectIdFieldDecorator(ReportDefinition.class), new ReadonlyFieldDecorator());
    }

    @Override
    public void onBind(HttpServletRequest request, ReportDefinitionCommand command, Errors errors) {
        super.onBind(request, command, errors); //To change body of overridden methods use File | Settings | File Templates.
        List<ReportDeliveryDefinition> reportDefinitions = command.getReportDefinition().getDeliveryDefinitions();
        Iterator<ReportDeliveryDefinition> it = reportDefinitions.iterator();

        for(ReportDeliveryDefinition rd: reportDefinitions)   {
                if (StringUtils.isEmpty(rd.getEntityName())) {
                 rd.setEntityName(rd.getEndPoint());
            }
        }

    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ReportDefinitionCommand command) {
        // -
        RepeatingFieldGroupFactory rfgFactory;
        rfgFactory = new RepeatingFieldGroupFactory("main", "reportDefinition.deliveryDefinitions");
        InputField eNameField = InputFieldFactory.createTextField("entityName", "Name", false);
        InputFieldAttributes.setSize(eNameField, 30);
        rfgFactory.addField(eNameField);

        InputField uName = InputFieldFactory.createTextField("userName", "Username");
        rfgFactory.addField(uName);

        InputField pwd = InputFieldFactory.createTextField("password", "Password");
        rfgFactory.addField(pwd);

        InputField addressField = InputFieldFactory.createTextField("endPoint", "Address", false);
        InputFieldAttributes.setSize(addressField, 50);
        rfgFactory.addField(addressField);
        rfgFactory.addField(InputFieldFactory.createSelectField("endPoint", "false", false, command.getRoles()));
        // -
        InputFieldGroupMap map = new InputFieldGroupMap();
        int rddCount = command.getReportDefinition().getDeliveryDefinitions().size();
        map.addRepeatingFieldGroupFactory(rfgFactory, rddCount);
        return map;
    }



    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ReportDefinitionCommand command) {
        Map refdata = super.referenceData(request, command);
        String action = request.getParameter("_action");
        if (StringUtils.equals(action, "deleteDelivery")) {
            refdata.put("flashMessage", "Delivery deleted successfully");
        }
        return refdata;
    }

    @Override
    public void postProcess(HttpServletRequest request, ReportDefinitionCommand command, Errors errors) {
        String action = request.getParameter("_action");
        String selectedIndex = request.getParameter("_selected");
        if (StringUtils.equals(action, "deleteDelivery")) {
            int index = Integer.parseInt(selectedIndex);
            command.getReportDefinition().getDeliveryDefinitions().remove(index);
        }
        super.postProcess(request, command, errors);
    }

}
