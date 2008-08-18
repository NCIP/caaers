package gov.nih.nci.cabig.caaers.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;


public class RenderDecisionManagerTag extends TagSupport{
	
	public static final String UI_TYPE_FIELD = "FIELD";
	public static final String UI_TYPE_DIVISION = "DIVISION";
	
	private String uiType = UI_TYPE_FIELD; //defaulted to element FIELD
	private String elementID;

	
	@Override
	public int doStartTag() throws JspException {
		//validate parameters
		validateParameters();
		return EVAL_BODY_INCLUDE;
	}
	
	
	private boolean validateParameters() throws JspException {
		if(StringUtils.isEmpty(elementID)) throw new JspException("The elementID property cannot be empty");
		
		if( !(StringUtils.equalsIgnoreCase(UI_TYPE_DIVISION, uiType) || StringUtils.equalsIgnoreCase(UI_TYPE_FIELD, uiType)) ){
			throw new JspException(String.format("Unknown value for uiType attribute allowed entries are - %s, %s", UI_TYPE_DIVISION, UI_TYPE_FIELD ) );
		}
		return true;
	}
	
	/**
	 * Tells the type of the UI element , eg: Field or Section
	 * @return
	 */
	public String getUiType() {
		return uiType;
	}
	
	/**
	 * Sets the type of the UI element , whose rendering decisition to be made
	 * @param uiType
	 */
	public void setUiType(String uiType) {
		this.uiType = uiType;
	}
	
	public String getElementID() {
		return elementID;
	}
	public void setElementID(String elementID) {
		this.elementID = elementID;
	}
	
	@Override
	public void release() {
		super.release();
	}
	
	
}
