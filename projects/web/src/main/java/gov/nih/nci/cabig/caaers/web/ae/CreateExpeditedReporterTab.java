package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.ae.AeTab.AeInputFieldCreator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Sameer Sawant
 */
public class CreateExpeditedReporterTab extends AeTab{
	private static final Log log = LogFactory.getLog(CreateExpeditedReporterTab.class);
	
	public CreateExpeditedReporterTab() {
        super(ExpeditedReportSection.REPORTER_INFO_SECTION.getDisplayName(), "Reporter",
                "ae/createExpeditedreporter");
    }
	
	@Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.REPORTER_INFO_SECTION};
    }
	
	@Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                                     ExpeditedAdverseEventInputCommand command) {
    }
}