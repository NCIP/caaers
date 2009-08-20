package gov.nih.nci.cabig.caaers.scheduler.runtime.trigger;

import org.quartz.SimpleTrigger;
/**
 * A trigger that runs, every 5 minutes. 
 * @author Biju Joseph
 *
 */
@SuppressWarnings("serial")
public class ReportStatusResetTrigger extends SimpleTrigger{
	private static int DEFAULT_INTERVAL = 1000 * 60 * 5;
	public ReportStatusResetTrigger() {
		this( DEFAULT_INTERVAL);
	}
	
	public ReportStatusResetTrigger(Integer interval){
		super("reportResetTrigger", "DEFAULT", SimpleTrigger.REPEAT_INDEFINITELY, interval);
	}
}
