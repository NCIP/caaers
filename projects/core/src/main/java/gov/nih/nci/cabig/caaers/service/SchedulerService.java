package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.report.Report;
/**
 *
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 29, 2007
 * @version     %I%, %G%
 * @since       1.0
 */

public interface SchedulerService {
	public  void scheduleNotification(Report report);
	public void unScheduleNotification(Report report);
}
