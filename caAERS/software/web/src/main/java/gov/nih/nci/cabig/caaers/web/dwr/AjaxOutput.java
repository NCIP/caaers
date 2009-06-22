package gov.nih.nci.cabig.caaers.web.dwr;

import java.util.Collections;
import java.util.List;

/**
 * The output from an AJAX function is represented using the AjaxOutput object.
 * 
 * @author Biju Joseph
 * 
 */
// TODO: At present the only the remove method in the framework use this.
// Needs refactoring to incorporate other DWR methods to use this.
public class AjaxOutput {
    private boolean error;

    private String errorMessage;

    private List<IndexChange> changes;
    
    private String htmlContent; //will be used by some pages
    private Object objectContent; //will be used by some pages

    public AjaxOutput() {
        changes = Collections.emptyList();
    }

    public AjaxOutput(String errorMessage) {
        this();
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public AjaxOutput(List<IndexChange> changes) {
        this.changes = changes;
    }
    
    public AjaxOutput(Object objContent){
    	this.objectContent = objContent;
    }
    
    public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public Object getObjectContent() {
		return objectContent;
	}

	public void setObjectContent(Object objectContent) {
		this.objectContent = objectContent;
	}

	public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<IndexChange> getChanges() {
        return changes;
    }

    public void setChanges(List<IndexChange> changes) {
        this.changes = changes;
    }

}
