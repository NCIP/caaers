package gov.nih.nci.cabig.caaers.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.util.ExpressionEvaluationUtils;
import org.springframework.web.util.TagUtils;
/**
 * {@inheritDoc}
 * Similar to {@link org.springframework.web.servlet.tags.MessageTag}, but will resolve the code against the {@link MessageSource}s twice. 
 * First with the actual property name, next with array notation removed property names
 * 
 * @author Biju Joseph
 *
 */
@SuppressWarnings("serial")
public class MessageTag extends org.springframework.web.servlet.tags.MessageTag {
	
	private String argumentSeparator = DEFAULT_ARGUMENT_SEPARATOR;
	
	private String code;

	private Object arguments;

	private String text;
	
	private String var;
	
	private String scope = TagUtils.SCOPE_PAGE;

	private boolean javaScriptEscape = false;
	
	/**
	 * {@inheritDoc}
	 */
	public void setCode(String code) {
		this.code = code;
		super.setCode(code);
	}
	
	public String getCode() {
		return code;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setArguments(Object arguments) {
		this.arguments = arguments;
		super.setArguments(arguments);
	}
	
	public Object getArguments() {
		return arguments;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void setArgumentSeparator(String argumentSeparator) {
		this.argumentSeparator = argumentSeparator;
		super.setArgumentSeparator(argumentSeparator);
	}
	
	public String getArgumentSeparator() {
		return argumentSeparator;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void setText(String text) {
		this.text = text;
		super.setText(text);
	}
	public String getText() {
		return text;
	}
	/**
	 *  {@inheritDoc}
	 */
	public void setVar(String var) {
		this.var = var;
		super.setVar(var);
	}
	public String getVar() {
		return var;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void setScope(String scope) {
		this.scope = scope;
		super.setScope(scope);
	}
	public String getScope() {
		return scope;
	}

	/**
	 *  {@inheritDoc}
	 */
	public void setJavaScriptEscape(String javaScriptEscape) throws JspException {
		this.javaScriptEscape = ExpressionEvaluationUtils.evaluateBoolean("javaScriptEscape", javaScriptEscape, pageContext);
		super.setJavaScriptEscape(javaScriptEscape);
	}
	
	public boolean getJavaScriptEscape() {
		return javaScriptEscape;
	}
	
	/**
	 * This will try to resolve the code in properties files,if not found again will try
	 * to resolve the property 
	 */
	@Override
	protected String resolveMessage() throws JspException,NoSuchMessageException {
	 	MessageSource messageSource = getMessageSource();
		if (messageSource == null) {
			throw new JspTagException("No corresponding MessageSource found");
		}

		
		String resolvedCode = ExpressionEvaluationUtils.evaluateString("code", this.code, pageContext);
		String resolvedText = ExpressionEvaluationUtils.evaluateString("text", this.text, pageContext);
		
		if(resolvedCode == null) return resolvedText;
		
		Object[] argumentsArray = resolveArguments(this.arguments);
		
		
		String msg = null;
		//first try to resolve using the code.
		try {
			msg = messageSource.getMessage(resolvedCode, argumentsArray, (getRequestContext() != null ? getRequestContext().getLocale() : null));
		} catch (NoSuchMessageException ignore) {
		}
		
		//try to resolve using the updated code
		if(msg == null || msg.length() < 1){
			resolvedCode = resolvedCode.replaceAll("(\\[\\d+\\])", "");
			msg = messageSource.getMessage(resolvedCode, argumentsArray, resolvedText, (getRequestContext() != null ? getRequestContext().getLocale() : null));
		}
		return msg;

	}
}
