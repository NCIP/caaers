package gov.nih.nci.cabig.caaers.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.springframework.web.servlet.tags.form.AbstractDataBoundFormElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;
/**
 * This tag will print the value of a path evaluated against the command object.
 * &lt;caaersTag:value path=&quots;....&quots; /&gt;
 * 
 * @author Biju Joseph
 * 
 */
public class ValueTag extends AbstractDataBoundFormElementTag {
	
	 @Override
	protected int writeTagContent(TagWriter writer) throws JspException {
		try {
			pageContext.getOut().write(getDisplayString(getBoundValue(), getPropertyEditor()));
		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return EVAL_PAGE;
	}
	

}
