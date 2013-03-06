/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.scheduler.runtime.trigger;

import org.quartz.CronTrigger;
/**
 * A trigger that runs, every 5 minutes. 
 * @author Biju Joseph
 *
 */
@SuppressWarnings("serial")
public class SafetySignallingReportTrigger extends CronTrigger{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8662304169493501783L;
	
}
