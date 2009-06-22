package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Rhett Sutphin
 */
public class RepeatingFieldGroupTransformerTest extends AbstractTestCase {
    private RepeatingFieldGroupTransformer transformer;

    private static final InputField.Category FACTORY_0_FIELD_0_CATEGORY = InputField.Category.TEXT;

    private static final InputField.Category FACTORY_1_FIELD_0_CATEGORY = InputField.Category.DATE;

    private static final InputField.Category FACTORY_2_FIELD_0_CATEGORY = InputField.Category.TEXTAREA;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RepeatingFieldGroupFactory factory0 = new RepeatingFieldGroupFactory("one", "list");
        factory0.addField(InputFieldFactory.createInputField(InputField.Category.TEXT));
        RepeatingFieldGroupFactory factory1 = new RepeatingFieldGroupFactory("th", "zedz");
        factory1.addField(InputFieldFactory.createInputField(InputField.Category.DATE));
        RepeatingFieldGroupFactory factory2 = new RepeatingFieldGroupFactory("three", "alignments");
        factory2.addField(InputFieldFactory.createInputField(InputField.Category.TEXTAREA));

        transformer = new RepeatingFieldGroupTransformer();
        transformer.addFactory(factory0);
        transformer.addFactory(factory1);
        transformer.addFactory(factory2);
    }

    public void testLongerMatchUsedIfAvailable() throws Exception {
        RepeatingFieldGroupFactory.RepeatingFieldGroup actual = transform("three4");
        assertEquals(4, actual.getIndex());
        assertEquals("three4", actual.getName());
        assertEquals(FACTORY_2_FIELD_0_CATEGORY, actual.getFields().get(0).getCategory());
    }

    public void testShortMatchUsedIfAppropriate() throws Exception {
        RepeatingFieldGroupFactory.RepeatingFieldGroup actual = transform("th8");
        assertEquals(8, actual.getIndex());
        assertEquals("th8", actual.getName());
        assertEquals(FACTORY_1_FIELD_0_CATEGORY, actual.getFields().get(0).getCategory());
    }

    public void testExceptionIfNoMatches() throws Exception {
        assertNull(transform("o90"));
    }

    public void testNullIfNotValidFieldGroupName() throws Exception {
        assertNull(transform("14alp"));
    }

    private RepeatingFieldGroupFactory.RepeatingFieldGroup transform(String input) {
        return (RepeatingFieldGroupFactory.RepeatingFieldGroup) transformer.transform(input);
    }
}
