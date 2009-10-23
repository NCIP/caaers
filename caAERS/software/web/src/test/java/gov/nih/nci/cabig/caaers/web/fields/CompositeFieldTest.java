package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;
import gov.nih.nci.cabig.caaers.web.fields.validators.DecimalRangeValidator;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class CompositeFieldTest extends AbstractTestCase {
    
    private DefaultInputFieldGroup group;
    private CompositeField field;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        group = new DefaultInputFieldGroup(null, "Group display name");
        field = new CompositeField("root", group);
    }

    public void testDisplayNameIsGroupDisplayName() throws Exception {
        assertEquals(group.getDisplayName(), field.getDisplayName());
    }

    public void testSubfieldPropertyNames() throws Exception {
        group.setFields(Collections.<InputField> singletonList(InputFieldFactory.createTextField("field", "DC", true)));
        List<InputField> subfields = field.createSubfields();
        assertEquals("Wrong number of subfields", 1, subfields.size());
        assertEquals("Wrong name for subfield", "root.field", subfields.get(0).getPropertyName());
    }

    public void testSubfieldPropertyNamesWhenPropertyNameIsNull() throws Exception {
        group.setFields(Collections.<InputField> singletonList(InputFieldFactory.createTextField("field", "DC", true)));
        field.setPropertyName(null);
        List<InputField> subfields = field.createSubfields();
        assertEquals("Wrong number of subfields", 1, subfields.size());
        assertEquals("Wrong name for subfield", "field", subfields.get(0).getPropertyName());
    }

    public void testValidateCompositeField() throws Exception {
    	ParticipantHistory.Measure measure = new ParticipantHistory.Measure();

    	InputField textField = InputFieldFactory.createTextField("quantity", "", new DecimalRangeValidator(1, 999));
    	group.addField(textField);

    	field = new CompositeField("weight", group);

    	ServletRequestDataBinder binder = new ServletRequestDataBinder(measure, "measure");
		BindException errors = new BindException(binder.getBindingResult());

		field.validate(new BeanWrapperImpl(measure), errors);

		assertFalse(errors.hasErrors());
    }

    public void testValidateCompositeField_InvalidValue() throws Exception {
    	ParticipantHistory.Measure measure = new ParticipantHistory.Measure();
    	measure.setQuantity(new BigDecimal(-99));

    	InputField textField = InputFieldFactory.createTextField("quantity", "", new DecimalRangeValidator(1, 999));
    	group.addField(textField);

    	field = new CompositeField("weight", group);

    	ServletRequestDataBinder binder = new ServletRequestDataBinder(measure, "measure");
		BindException errors = new BindException(binder.getBindingResult());

		field.validate(new BeanWrapperImpl(measure), errors);
		assertTrue(errors.hasErrors());
    }

}
