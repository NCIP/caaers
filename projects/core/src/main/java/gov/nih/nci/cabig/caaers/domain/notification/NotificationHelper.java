package gov.nih.nci.cabig.caaers.domain.notification;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ContactMechanism;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * This is an helper class, which is used to obtain the correct address (toAddress) of a 
 * recipient. 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 31, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class NotificationHelper {
	private Map<String, String> roleEntityMapping;
	
	
	public static List<String> findToAddresses(PlannedNotification pnf, ReportSchedule rs){
		assert pnf != null : "PlannedNotification should not be null";
		List<String> toAddressList = new ArrayList<String>();
		String address = null;
		String type = "email";
		if(pnf instanceof PlannedEmailNotification)
			type ="email";
		
		for(Recipient r : pnf.getRecipients()){
			if(r instanceof ContactMechanismBasedRecipient){
				address = ((ContactMechanismBasedRecipient)r).getContactName();
				toAddressList.add(address);
			}else if(r instanceof RoleBasedRecipient){
				String roleName = ((RoleBasedRecipient)r).getRoleName();
				List<ContactMechanism> contactMechanismList = fetchContactMechanism(roleName, rs.getAeReport());
				toAddressList.addAll(findContactValuesOfType(type, contactMechanismList ));
			}
		}//for each r
		
		return toAddressList;
	}
	
	public static List<ContactMechanism> fetchContactMechanism(String role, AdverseEventReport aeReport){
		//TODO : do runtime expression evaluation using roleEntityMapping.
		try{
			if(StringUtils.equals("Reporter", role)){
				return aeReport.getReporter().getContactMechanisms();
			}else if(StringUtils.equals("Site Study PI", role)){
				
			}else if(StringUtils.equals("Study Chair", role)){
				
			}else if(StringUtils.equals("Treating Physician", role)){
				return aeReport.getPhysician().getContactMechanisms();
			}else if(StringUtils.equals("Sponsor", role)){
				
			}else if(StringUtils.equals("IRB", role)){
			}
		}catch(Exception ignore){
			
		}
		return null;
	}
	
	public static List<String> findContactValuesOfType(String type, List<ContactMechanism> cmList){
		List<String> addressList = new ArrayList<String>(3);
		for(ContactMechanism cm : cmList){
			if(StringUtils.equals(cm.getType(), type) && 
			   StringUtils.isNotEmpty(cm.getValue())) addressList.add(cm.getValue());
		}//for each cm
		return addressList;
	}
	
	public static void applyCalendarTemplate(ReportCalendarTemplate rcTemplate, ReportSchedule rs){
		
		assert rcTemplate != null : "ReportCalendarTemplate must be not null, inorder to schedule notfications";
		Date now = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		
		//set the due date
		cal.setTime(now);
		cal.add(rcTemplate.getTimeScaleUnitType().getCalendarTypeCode(), rcTemplate.getDuration());
		rs.setDueOn(cal.getTime());
		
		//populate scheduled notifications
		List<ScheduledNotification> snfList = new ArrayList<ScheduledNotification>();
		List<PlannedNotification> pnfList = rcTemplate.getPlannedNotifications();

		for(PlannedNotification pnf : pnfList){
			//obtain the toAddress to use.
			List<String> toAddressList = findToAddresses(pnf, rs);
			
			for(String to : toAddressList){
				ScheduledNotification snf = null;
				if(pnf instanceof PlannedEmailNotification){
					PlannedEmailNotification penf = (PlannedEmailNotification)pnf;
					ScheduledEmailNotification senf = new ScheduledEmailNotification();
					snf = senf;
					//set the values specific to email
					senf.setFromAddress(penf.getFromAddress());
					senf.setToAddress(to);
				}
				assert snf != null : "ScheduledNotification (snf) must be initiailized";
				snf.setBody(pnf.getNotificationBodyContent().getBody());
				snf.setCreatedOn(now);
				snf.setDeliveryStatus(DeliveryStatus.CREATED);
				snf.setPlanedNotificaiton(pnf);
				cal.setTime(now);
				cal.add(rcTemplate.getTimeScaleUnitType().getCalendarTypeCode(), pnf.getIndexOnTimeScale());
				snf.setScheduledOn(cal.getTime());
				
				snfList.add(snf);
			}//for each to
		}//for each pnf
		//set the scheduled notificaitons
		rs.setScheduledNotifications(snfList);
	}
}