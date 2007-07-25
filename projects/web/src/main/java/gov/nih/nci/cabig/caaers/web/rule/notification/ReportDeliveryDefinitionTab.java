package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : Jun 22, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class ReportDeliveryDefinitionTab extends TabWithFields<ReportDefinitionCommand> {

    private RepeatingFieldGroupFactory rfgFactory;

    public ReportDeliveryDefinitionTab(){
        super("Report Delivery Details", "Report Delivery Configuration","rule/notification/reportDeliveryTab");
        rfgFactory = new RepeatingFieldGroupFactory("main", "reportDefinition.deliveryDefinitions");
        InputField eNameField = InputFieldFactory.createTextField("entityName","Name", true);
        InputFieldAttributes.setSize(eNameField, 50);
        rfgFactory.addField(eNameField);
        InputField descField = InputFieldFactory.createTextArea("entityDescription", "Description", false);
        InputFieldAttributes.setColumns(descField, 50);
        rfgFactory.addField(descField);
        rfgFactory.addField(InputFieldFactory.createSelectField("format", "Report Format", true,
            collectSelectOptions(ReportFormat.values(), null, "displayName")));
        InputField addressField = InputFieldFactory.createTextField("endPoint","Address", true);
        InputFieldAttributes.setSize(addressField, 50);
        rfgFactory.addField(addressField);
        rfgFactory.addField(InputFieldFactory.createSelectField("endPoint", "Role", true,
            collectSelectOptions(new String[]{"Sponsor", "Study PI"}, null, null)));
        rfgFactory.addField(InputFieldFactory.createSelectField("endPointType", "Address Type", true,
            collectSelectOptions(new String[]{"e-mail", "phone", "fax", "url"}, null, null)));
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ReportDefinitionCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        int rddCount = command.getReportDefinition().getDeliveryDefinitions().size();
        map.addRepeatingFieldGroupFactory(rfgFactory, rddCount);
        return map;
    }

    private Map<Object, Object> collectSelectOptions(Object[] items, String nameProperty, String valueProperty){
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("" , "Please select");
        options.putAll(InputFieldFactory.collectOptions(Arrays.asList(items), nameProperty, valueProperty));
        return options;
    }
}
