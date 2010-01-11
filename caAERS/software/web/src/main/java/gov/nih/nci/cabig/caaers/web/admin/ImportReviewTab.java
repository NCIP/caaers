package gov.nih.nci.cabig.caaers.web.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventController;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

public class ImportReviewTab extends Tab<ImportCommand>{
	
	private static final String BACK_ACTION = "back";
	private ImporterFactory importerFactory;
	public ImportReviewTab(String longTitle, String shortTitle, String viewName){
		super(longTitle, shortTitle, viewName);
	}
	
    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        return refdata;
    }
    
    @Override
    public void postProcess(HttpServletRequest request, ImportCommand command, Errors errors) {
        // TODO: see why the command variable type has a comma attached to it

    	String action = (String)findInRequest(request, "_action");
    	if(!action.equals(BACK_ACTION)){
    		Importer importer = importerFactory.createImporterInstance(command.getType());
    		importer.save(command, request);
    	}
    }
    
    /**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final HttpServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
    

	public void setImporterFactory(ImporterFactory importerFactory){
		this.importerFactory = importerFactory;
	}
}
