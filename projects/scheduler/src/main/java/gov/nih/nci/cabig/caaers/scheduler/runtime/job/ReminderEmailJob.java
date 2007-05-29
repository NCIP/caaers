package gov.nih.nci.cabig.caaers.scheduler.runtime.job;
/*
import gov.nih.nci.cabig.caaers.email.EmailInfo;
import gov.nih.nci.cabig.caaers.email.EmailService;
import gov.nih.nci.cabig.caaers.email.SmtpConfig;
*/
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledNotification;
import gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule;
import gov.nih.nci.cabig.caaers.scheduler.dummy.ReportSchedulerService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This Job will send an email reminder notifaction,based on a ReportSchedule.
 * @author Biju Joseph
 *
 */
public class ReminderEmailJob implements Job{
	
	/**
	 * This job will send reminder emails to the recipients. 
	 * The job searchs the following in the JobDataMap
	 *  <ul>EmailNotificaiton - Corresponds to the email notificaiton that is to be send<ul>
	 *  <ul>ReportSchedule - Corresponds to the ReportSchedule, that is linked to an AdverseEvent </ul>
	 *  
	 * This method,after retrieving the above mentioned objects, will query the present status of ReportSchedule, 
	 * if it is still <code>PENDING</code> {@link ReportScheduleStatusType}, it will send out the email. 
	 * But in case if the status is <code>COMPLETE</code>, it will not send email and will delete the subsequent 
	 * jobs scheduled on the same ScheduleReport.  
	 * 
	 * 
	 */
	  public void execute(JobExecutionContext context) throws JobExecutionException {
		  
		//Get the Metadata
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		//Lookup email service - send email
		System.out.println("In execute......" + String.valueOf(new Date()));
		int i = jobDataMap.getInt("i");
		System.out.println("i----------- " + i);
		ReportSchedule reportSchedule = (ReportSchedule)jobDataMap.get("report.schedule");
		ScheduledNotification emailNotification = (ScheduledNotification) jobDataMap.get("email.notification");
		
		System.out.println("---- in job execute ----");
		if(validateCurrentReportScheduleStatus(reportSchedule.getId())){
			sendEmail(emailNotification);
		}else{
			deleteSubsequentJobs(context);
		}
		System.out.println("---******* -----");
	  }
	  
	  /**
	   * This will query the current status of the ReportSchedule, and returns true, if the status is PENDING
	   * @param reportScheduleId
	   * @return true - when stautus is PENDING
	   * 
	   * @see ReportScheduleStatusType
	   * @see ReportSchedule
	   */
	  public boolean validateCurrentReportScheduleStatus(Integer reportScheduleId){
		  ReportStatus status =  ReportSchedulerService.getInstance().getReportScheduleStatus(reportScheduleId.intValue());
		  return status.equals(ReportStatus.PENDING);
	  }
	  
	  /**
	   * This method will delete all the jobs that are available(scheduled for later execution) in the 
	   * same group (associated to the same ScheduleReport).
	   * @param context - The JobExecutionContext
	   */
	  public void deleteSubsequentJobs(JobExecutionContext context){
	  		System.out.println("deleting jobs");
	  		
	  		//get a handle of the scheduler
	  		Scheduler s = context.getScheduler();

	  		try {
	  			JobDetail jobDetail = context.getJobDetail();
	  			String currentJobName = jobDetail.getName();
	  			String groupName = jobDetail.getGroup();
	  			
	  			//fetch all the active jobs belonging to present job group
	  			String[] jobNames = s.getJobNames(groupName);
	  			
	  			//delete all jobs, other than the current job.
	  			for(String jobName : jobNames){
	  				if(!currentJobName.equals(jobName)){
	  					//delete this job
	  					boolean status = s.deleteJob(jobName, groupName);
	  					System.out.println("[" + jobName +":" + groupName + "  deleted =" + status);
	  				}
	  			}
	  			
	  		} catch (SchedulerException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	  	}
	  
	  
	  /**
	   * This method will send an email out to the recipients in the ScheduledNotification
	   * @param nf
	   */
	  //TODO: Find how to call email service in a better way.
	  public void sendEmail(ScheduledNotification nf){
		  System.out.println("---------------------");
		  System.out.println("Going to send email based on NF");
		  System.out.println(nf);
		  System.out.println("---------------------");
		  
		
//		 ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
//	              new String[] { "classpath*:config/spring/applicationContext-rules-services.xml" });
//	
//			//obtain the email service
//			EmailService emailService = (EmailService) applicationContext.getBean("emailService");
//			
//			SmtpConfig smtpConfig = new SmtpConfig();
//			EmailInfo emailInfo = new EmailInfo();
//			Properties p = getProperties();
//			if(p != null){
//				smtpConfig.setHost(p.getProperty("smtp.host"));
//				smtpConfig.setPort(p.getProperty("smtp.port"));
//				smtpConfig.setUser(p.getProperty("smtp.user"));
//				smtpConfig.setPassword(p.getProperty("smtp.pwd"));
//				
//				emailInfo.setFrom(nf.getFromAddress());
//				emailInfo.setReplyTo(nf.getFromAddress());
//				
//			}else{
//				smtpConfig.setHost("smtp.comcast.net");
//				smtpConfig.setPort("25");
//				smtpConfig.setUser("biju.joseph@semanticbits.com");
//				smtpConfig.setPassword("biju1234");
//				
//				emailInfo.setFrom(p.getProperty("from.addr"));
//				emailInfo.setReplyTo(p.getProperty("reply.addr"));
//				
//			}
//			smtpConfig.setAuth("true");
//			emailInfo.setContent(nf.getContent());
//			emailInfo.setTo(nf.getRecipients());
//			emailInfo.setSubject(nf.getSubject());
//			
//			//send this mail
//			try {
//				emailService.send(emailInfo, smtpConfig);
//			} catch (RuntimeException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	  }
  	
	  
  private Properties getProperties(){
	  try {
		Properties p = new Properties();
		  String file = (String.valueOf(System.getenv("OS")).contains("Win")) ? "c:/project/email/smtp.txt" :"/home/caaers_dev/email/smtp.txt";
		  p.load(new FileInputStream(file));
		  return p;
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
  }
  	  	
  private void test(JobExecutionContext context){
	  /*
		Trigger t = context.getTrigger();
		JobDetail dt = context.getJobDetail();
		System.out.println("Trigger[");
		System.out.println(t.hashCode());
		 System.out.println("name:" + t.getName());
		 System.out.println("fullName" + t.getFullName());
		 System.out.println("JobName" + t.getJobName());
		 System.out.println("Group" + t.getGroup());
		 System.out.println("InstanceId:" +t.getFireInstanceId());
		 System.out.println("Final FireTime" + String.valueOf(t.getFinalFireTime()));
		System.out.println("]");
		
		System.out.println("JobDetail[");
		System.out.println(dt.hashCode());
		System.out.println("Name" + dt.getName());
		System.out.println("fullName"+ dt.getFullName());
		System.out.println("groupName"+ dt.getGroup());
		System.out.println("" + dt.getKey());
		System.out.println("]");
		
		System.out.println("Context[");
		System.out.println(context.getJobInstance());
		System.out.println("]");
		System.out.println("===============================================");
		*/
  }
}
