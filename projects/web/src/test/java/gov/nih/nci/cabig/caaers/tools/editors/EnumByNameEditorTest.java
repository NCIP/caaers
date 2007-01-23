package gov.nih.nci.cabig.caaers.tools.editors;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class EnumByNameEditorTest extends CaaersTestCase {
    private EnumByNameEditor<TestEnum> editor = new EnumByNameEditor<TestEnum>(TestEnum.class);

    public void testSetAsText() throws Exception {
        editor.setAsText("F8");
        assertSame(TestEnum.F8, editor.getValue());
    }

    public void testSetAsTextNull() throws Exception {
        editor.setAsText(null);
        assertNull(editor.getValue());
    }

    public void testGetAsText() throws Exception {
        editor.setValue(TestEnum.F3);
        assertEquals("F3", editor.getAsText());
    }

    public void testGetAsTextNull() throws Exception {
        editor.setValue(null);
        assertNull(editor.getAsText());
    }

    private static enum TestEnum {
        F3, F5, F8, F13
    }
}
