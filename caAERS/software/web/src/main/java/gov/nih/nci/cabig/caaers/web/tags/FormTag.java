package gov.nih.nci.cabig.caaers.web.tags;

import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * This is same as the form tag provided by Spring Framework. 
 * @author Biju Joseph
 * @date 6/28/11
 */
public class FormTag extends org.springframework.web.servlet.tags.form.FormTag {


    private boolean validate;

    @Override
    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
        super.writeDefaultAttributes(tagWriter);
        if(!validate) writeOptionalAttribute(tagWriter, "novalidate", "novalidate");
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }
}