package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.List;


/**
 * This is an service class, which is used to obtain the correct address (toAddress) of a
 * recipient.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 31, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public interface ReportService {
    List<String> findToAddresses(PlannedNotification pnf, Report rs);

    void applyCalendarTemplate(ReportDefinition rcTemplate, Report rs);

    String applyRuntimeReplacements(String rawContent, Report report);
}