package gov.nih.nci.cabig.caaers.domain.repository;

import java.util.Collection;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;

/**
 * This is an service class, which is used to validate and check for report submitability.
 *
 * @author <a href="mailto:sameer.sawant@semanticbits.com">Sameer Sawant</a> Created-on : Nov 05, 2009
 */
public interface ReportValidationService{
	
	/**
     * Will tell whether all the mandatory field for this report is duly filled. This method will
     * validate against all the sections defined in {@link gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection}
     *
     * @return ErrorMessages, if any.
     */
    ReportSubmittability isSubmittable(Report report);
    
    /**
     * Will tell whether all the mandatory field for this report is duly filled.
     *
     * @return ErrorMessages, if any.
     */
    ReportSubmittability validate(Report report, Collection<ExpeditedReportSection> expeditedReportSections, Collection<ExpeditedReportSection> mandatorySections);
    
    
}