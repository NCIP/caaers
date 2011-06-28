package gov.nih.nci.cabig.caaers.web.tags;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.jstl.test.PageContextImpl;
import org.easymock.classextension.EasyMock;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Biju Joseph
 * @date 6/28/11
 */
public class FormTagTest extends AbstractTestCase {

    FormTag tag;

    public void testIsValidate() throws Exception {
        tag = new FormTag();
        tag.setHtmlEscape("false");
        TagWriter writer = new TagWriter(new PageContextImpl()){



            Map<String, String> map = new HashMap<String, String>();
            {
                map.put("novalidate", "novalidate");
                map.put("id", "command");
                map.put("class", "");
                map.put("style", "");
            }

            @Override
            public void writeOptionalAttributeValue(String attributeName, String attributeValue) throws JspException {

                assertTrue(StringUtils.equals(attributeValue , map.get(attributeName)));

            }
            

        };


        tag.writeDefaultAttributes(writer);
        verifyMocks();

    }
}
