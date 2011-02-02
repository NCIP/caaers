package gov.nih.nci.cabig.caaers.domain.report;

import java.util.Comparator;

 
/**
 * The Class AttemptNumberReportTrackingComparator.
 */
@SuppressWarnings("unchecked")
public class AttemptNumberReportTrackingComparator implements Comparator{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object reportTracking1, Object reportTracking2) {
		
		int attemptNumber1 = ( (ReportTracking) reportTracking1).getAttemptNumber();
		int attemptNumber2 = ( (ReportTracking) reportTracking2).getAttemptNumber();

		if( attemptNumber1 > attemptNumber2 )
		return 1;
		else if( attemptNumber1 < attemptNumber2 )
		return -1;
		else
		return 0;
	}
}