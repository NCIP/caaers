package gov.nih.nci.cabig.caaers.web.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

import gov.nih.nci.cabig.ctms.web.tabs.Tab;

public class ImportReviewTab extends Tab<ImportCommand>{
	
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
    	Importer importer = importerFactory.createImporterInstance(command.getType());
    	importer.save(command, request);
    }

	public void setImporterFactory(ImporterFactory importerFactory){
		this.importerFactory = importerFactory;
	}
}
