/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.scheduler.runtime.trigger;

import org.quartz.SimpleTrigger;
/**
 * A trigger that runs, every 5 minutes. 
 * @author Ramakrishna Gundala
 *
 */
@SuppressWarnings("serial")
public class UnreportedAENotificationsTrigger extends SimpleTrigger{
	private static int DEFAULT_INTERVAL = 1000 * 60 * 15;
	public UnreportedAENotificationsTrigger() {
		this( DEFAULT_INTERVAL);
	}
	
	public UnreportedAENotificationsTrigger(Integer interval){
		super("unreportedAENotificationsTrigger", "DEFAULT", SimpleTrigger.REPEAT_INDEFINITELY, interval);
	}
}
