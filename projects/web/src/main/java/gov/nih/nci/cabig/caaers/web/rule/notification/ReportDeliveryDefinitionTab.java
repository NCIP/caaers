package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.web.fields.BaseSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

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
		rfgFactory.addField(new DefaultTextField("entityName","Name", true));
		rfgFactory.addField(new DefaultTextArea("entityDescription", "Description", false));
		rfgFactory.addField(new DefaultSelectField("format", "Report Format", true,
				collectSelectOptions(ReportFormat.values(), null, "displayName")));
		rfgFactory.addField(new DefaultTextField("endPoint","Address", true));
		rfgFactory.addField(new DefaultSelectField("endPoint", "Role", true, 
				collectSelectOptions(new String[]{"Sponsor", "Study PI"}, null, null)));
		rfgFactory.addField(new DefaultSelectField("endPointType", "Address Type", true, 
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
		options.putAll(BaseSelectField.collectOptions(Arrays.asList(items), nameProperty, valueProperty));
		return options;
	}
}
