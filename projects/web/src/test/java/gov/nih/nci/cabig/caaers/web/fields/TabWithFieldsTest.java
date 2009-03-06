package gov.nih.nci.cabig.caaers.web.fields;

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.reportMatcher;
import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.Map;

import org.easymock.IArgumentMatcher;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class TabWithFieldsTest extends AbstractTestCase {
    private TestBean command;

    private TabWithFields<TestBean> tab;

    private InputFieldGroup group;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        group = new DefaultInputFieldGroup("sole");
        group.getFields().add(registerMockFor(InputField.class));
        group.getFields().add(registerMockFor(InputField.class));

        command = new TestBean();

        tab = new TabWithFields<TestBean>("DC", "dc", "dc") {
            @Override
            public Map<String, InputFieldGroup> createFieldGroups(TestBean command) {
                return InputFieldGroupMap.create(group);
            }
        };
    }

    public void testValidateValidatesEachField() throws Exception {
        Errors errors = new BindException(command, "command");
        group.getFields().get(0).validate(wrapperOf(command), eq(errors));
        group.getFields().get(1).validate(wrapperOf(command), eq(errors));
        replayMocks();

        tab.validate(command, errors);
        verifyMocks();
        assertFalse(errors.hasErrors());
    }

    private static BeanWrapper wrapperOf(final Object expectedUnwrapped) {
        reportMatcher(new IArgumentMatcher() {
            public boolean matches(Object argument) {
                assertTrue("Parameter is not a bean wrapper", argument instanceof BeanWrapper);
                assertSame("Wrapper is of wrong instance", expectedUnwrapped,
                                ((BeanWrapper) argument).getWrappedInstance());
                return true;
            }

            public void appendTo(StringBuffer buffer) {
                buffer.append("BeanWrapper of ").append(expectedUnwrapped);
            }
        });

        return null;
    }

    private static class TestBean {
        private Integer integer;

        private String string;

        public Integer getInteger() {
            return integer;
        }

        public void setInteger(Integer integer) {
            this.integer = integer;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }
    }
}
