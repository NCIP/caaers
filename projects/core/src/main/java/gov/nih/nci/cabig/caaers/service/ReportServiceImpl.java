package gov.nih.nci.cabig.caaers.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.PersonContact;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.UnsatisfiedProperty;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Arrays;

import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is an service class, which is used to obtain the correct address (toAddress) of a
 * recipient.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com","xxxxxx");//Biju Joseph</a>
 * Created-on : May 31, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Transactional(readOnly=true)
public class ReportServiceImpl  implements ReportService {
	private static final Log log = LogFactory.getLog(ReportServiceImpl.class);
    private NowFactory nowFactory;
    private SchedulerService schedulerService;
    private ReportDao reportDao;
    private ExpeditedReportTree expeditedReportTree;


    
    public  List<String> findToAddresses(PlannedNotification pnf, Report report){
		assert pnf != null : "PlannedNotification should not be null";
		List<String> toAddressList = new ArrayList<String>();
		for(Recipient r : pnf.getRecipients()){
			if(r instanceof ContactMechanismBasedRecipient){
				if(StringUtils.isNotEmpty(r.getContact())) toAddressList.add(r.getContact());
			}else if(r instanceof RoleBasedRecipient && pnf instanceof PlannedEmailNotification){
				toAddressList.addAll(findEmailAddress(r.getContact(), report.getAeReport()));
			}
			
		}//for each r

		return toAddressList;
	}
    
    
    List<String> findEmailAddress(String role, ExpeditedAdverseEventReport aeReport){
    	List<String> addresses = new ArrayList<String>();
    	String address = null;
    	try {
			if(StringUtils.equals("REP", role)){//Reporter
				address = aeReport.getReporter().getContactMechanisms().get(PersonContact.EMAIL);
				if(StringUtils.isNotEmpty(address)) addresses.add(address);
			}else if(StringUtils.equals(role, "SUB")){//Submitter
				address = aeReport.getReports().get(0).getLastVersion().getSubmitter().getContactMechanisms().get(PersonContact.EMAIL);
				if(StringUtils.isNotEmpty(address)) addresses.add(address);
			}else if(StringUtils.equals(role, "SPI")){//Site Principal Investigator
				addresses.addAll(findEmailsOfSiteInvestigators(aeReport.getStudy().getStudyOrganizations(), "Site Principal Investigator"));
			}else if(StringUtils.equals(role, "SI")){//Site Investigator
				addresses.addAll(findEmailsOfSiteInvestigators(aeReport.getStudy().getStudyOrganizations(), "Site Investigator"));
			}else if(StringUtils.equals(role, "PI")){//Principal Investigator
				addresses.addAll(findEmailsOfSiteInvestigators(aeReport.getStudy().getStudyOrganizations(), "Principal Investigator"));
			}else if(StringUtils.equals(role, "PC")){//Participant Coordinator
				addresses.addAll(findEmailsOfResearchStaff(aeReport.getStudy().getStudyOrganizations(), "Participant Coordinator"));
			}else if(StringUtils.equals(role, "SC")){//Study Coordinator
				addresses.addAll(findEmailsOfResearchStaff(aeReport.getStudy().getStudyOrganizations(), "Study Coordinator"));
			}else if(StringUtils.equals(role, "AEC")){//Adverse Event Coordinator
				addresses.addAll(findEmailsOfResearchStaff(aeReport.getStudy().getStudyOrganizations(), "Adverse Event Coordinator"));
			}
		} catch (Exception e) {
			log.warn("Unable to fetch the email address of Role :" + role + ", mechanismType : EMAIL", e);
		}
    	return addresses;
    }
    
    List<String> findPhoneNumbers(String role, ExpeditedAdverseEventReport aeReport){
    	assert false : "Not implemented";
    	return null;
    }
    
    List<String> findFaxNumbers(String role, ExpeditedAdverseEventReport aeReport){
    	assert false : "Not implemented";
		return null;
    }
    
    List<String> findEmailsOfResearchStaff(List<StudyOrganization> orgs, String personnelRole){
    	List<String> emails = new ArrayList<String>();
    	String email = null;
    	for(StudyOrganization org : orgs){
    		for(StudyPersonnel personnel : org.getStudyPersonnels()){
    			if(StringUtils.equals(personnel.getRoleCode(), personnelRole)){
    				email = personnel.getResearchStaff().getEmailAddress();
    				if(StringUtils.isNotEmpty(email)) emails.add(email);
    			}
    		}
    	}
    	return emails;
    }
    
    List<String> findEmailsOfSiteInvestigators(List<StudyOrganization> orgs, String investigatorRole){
    	List<String> emails = new ArrayList<String>();
    	String email = null;
    	for(StudyOrganization org : orgs){
    		for(StudyInvestigator studyInvestigator : org.getStudyInvestigators()){
    			if(StringUtils.equals(studyInvestigator.getRoleCode(), investigatorRole)){
    				email = studyInvestigator.getSiteInvestigator().getInvestigator().getEmailAddress();
    				if(StringUtils.isNotEmpty(email)) emails.add(email);
    			}
    		}
    	}
    	return emails;
    }


	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.service.ReportService#applyRuntimeReplacements(java.lang.String, gov.nih.nci.cabig.caaers.domain.report.Report)
	 */
	public String applyRuntimeReplacements(String rawText, Report report) {
		Configuration cfg = new Configuration();
        try {
            Template t = new Template("message", new StringReader(rawText),cfg);
            Map<Object, Object> map = getContextVariables(report);
            StringWriter writer = new StringWriter();
            t.process(map, writer);
            return writer.toString();
        } catch (TemplateException e) {
            throw new CaaersSystemException("Error while applying freemarker template [PlannedNotificatiton.body]", e);
        } catch (IOException e) {
            throw new CaaersSystemException("Error while applying freemarker template [PlannedNotificatiton.body]", e);
        }
	}

	public Map<Object,Object> getContextVariables(Report report){
		//TODO : properly populate the following....
		//TODO: add appropriate null-checks
		Map<Object, Object> map = new HashMap<Object, Object>();
		// map.put("nCIProtocolNumber", report.getAeReport().getStudy().getPrimaryIdentifier().getValue());
		// map.put("reportId", report.getAeReport().getId());
		map.put("nCIProtocolNumber","xxxxxx");//NCI Protocol Number
		map.put("ticketNumber","xxxxxx");//Ticket Number
		StudyParticipantAssignment a = report.getAeReport().getAssignment();
		Participant p = (a != null)? a.getParticipant() : null;
		Identifier patientId = (p != null) ? p.getPrimaryIdentifier() : null;
		map.put("patientId", ((patientId != null))? patientId.getValue() : "xxxxx");//Patient ID
		map.put("amendmentNumber","xxxxxx");//Amendment Number
		map.put("reportId","xxxxxx");//Report ID
		map.put("reportURL","xxxxxx");//URL To Report
		map.put("report", report);
		map.put("study", report.getAeReport().getStudy());
		return map;
	}



    /**
     * Creates a report from the given definition and associates it with the
     * given aeReport.  Initiates all notifications for the report.
     */
	@Transactional(readOnly=false)
    public Report createReport(ReportDefinition repDef, ExpeditedAdverseEventReport aeReport) {
        assert repDef != null : "ReportDefinition must be not null. Unable to create a Report";
        assert aeReport != null : "ExpeditedAdverseEventReport should not be null. Unable to create a Report";

        Date now = nowFactory.getNow();
        Calendar cal = GregorianCalendar.getInstance();

        Report report = repDef.createReport();
        report.setCreatedOn(now);

        ReportVersion reportVersion = new ReportVersion();
        reportVersion.setCreatedOn(now);
        reportVersion.setReportStatus(ReportStatus.PENDING);
        report.addReportVersion(reportVersion);

        //attach the aeReport to report
        aeReport.addReport(report);

        //set the due date
        cal.setTime(now);
        cal.add(repDef.getTimeScaleUnitType().getCalendarTypeCode(), repDef.getDuration());
        report.setDueOn(cal.getTime());
        reportVersion.setDueOn(cal.getTime());

        //populate the delivery definitions
        if (repDef.getDeliveryDefinitions() != null) {
            for (ReportDeliveryDefinition rdd : repDef.getDeliveryDefinitions()) {
	             //fetch the contact mechanism for role based entities.
	             if(rdd.getEndPointType().equals(ReportDeliveryDefinition.ENTITY_TYPE_ROLE)){
	            	 List<String> addresses = findEmailAddress(rdd.getEndPoint(), aeReport); 
	            	 for(String address : addresses){
	            		 if(StringUtils.isNotEmpty(address)){
	                		 ReportDelivery rd = rdd.createReportDelivery();
	                		 rd.setEndPoint(address);
	                		 report.addReportDelivery(rd);
	                	 } 
	            	 }
	             }else{
	            	 if(StringUtils.isNotEmpty(rdd.getEndPoint())){
	            		 ReportDelivery rd = rdd.createReportDelivery();
	            		 rd.setEndPoint(rdd.getEndPoint());
	            		 report.addReportDelivery(rd);
	            	 }
	             }

            }//~for rdd
        }//~if

        //populate the scheduled notifications.
        //Note:- ScheduledNotification is per Recipient. A PlannedNotificaiton has many recipients.
        if (repDef.getPlannedNotifications() != null) {

            for (PlannedNotification pnf : repDef.getPlannedNotifications()) {
            	 String subjectLine = null;
                 String bodyContent = null;

                //obtain all valid recipient address
                List<String> toAddressList = findToAddresses(pnf, report);
                // for each recipient(address) create a ScheduledNotification.
                for (String to : toAddressList) {

                    ScheduledNotification snf = null;
                    // TODO: instanceof indicates an abstraction failure.  Could this be domain logic?
                    if (pnf instanceof PlannedEmailNotification) {
                    	PlannedEmailNotification penf = (PlannedEmailNotification) pnf;
                    	ScheduledEmailNotification senf  = penf.createScheduledNotification(to);
                        snf = senf;
                    	if (subjectLine == null) {
                            subjectLine = applyRuntimeReplacements(penf.getSubjectLine(), report);
                        }
                        senf.setSubjectLine(subjectLine);
                    }

                    if (bodyContent == null) {
                        bodyContent = applyRuntimeReplacements(pnf.getNotificationBodyContent().getBody(), report);
                    }
                    snf.setBody(bodyContent);

                    // TODO: consider some or all of this domain logic, too
                    snf.setCreatedOn(now);
                    snf.setDeliveryStatus(DeliveryStatus.CREATED);
                    cal.setTime(now);
                    cal.add(repDef.getTimeScaleUnitType().getCalendarTypeCode(), (pnf.getIndexOnTimeScale()));
                    snf.setScheduledOn(cal.getTime());

                    report.addScheduledNotification(snf);

                }//for each to
            }//for each pnf
        }//~if

        //save the report
        reportDao.save(report);

        //schedule the report, if there are scheduled notificaitons.
        if(report.hasScheduledNotifications()) schedulerService.scheduleNotification(report);

        return report;
    }

    /**
     * Will mark the report as deleted:-
     *   <br />Unschedules all scheduled notifications.
     *   <br />Sets the status of the Report as {@link ReportStatus#WITHDRAWN}
     */
	@Transactional(readOnly=false)
	public void deleteReport(Report report) {
		assert !report.getStatus().equals(ReportStatus.WITHDRAWN) : "Cannot withdraw a report that is already withdrawn";
		schedulerService.unScheduleNotification(report);
		report.setStatus(ReportStatus.WITHDRAWN);
		reportDao.save(report);
	}

    @SuppressWarnings("unchecked")
    private void validate(
       ExpeditedAdverseEventReport aeReport, List<String> mandatoryFields, ExpeditedReportSection section,
       ReportSubmittability messages
   ) {
        TreeNode sectionNode = expeditedReportTree.getNodeForSection(section);
        if (sectionNode == null) throw new CaaersSystemException("There is no section node in the report tree for " + section.name() + ".  This shouldn't be possible.");

        List<String> applicableFields = new LinkedList<String>();
        for (String field : mandatoryFields) {
            TreeNode n = sectionNode.find(field);
            if (n == null) continue;
            applicableFields.add(field);
       }
       List<UnsatisfiedProperty> unsatisfied = expeditedReportTree.verifyPropertiesPresent(
           applicableFields, aeReport);
       for (UnsatisfiedProperty uProp : unsatisfied) {
           TreeNode unsatisfiedNode = uProp.getTreeNode();

           messages.addMissingField(
               section,
               unsatisfiedNode.getDisplayName(),
               uProp.getBeanPropertyName());
       }
   }

   /**
    * Will tell whether all the mandatory field for this report is duly filled.
    * Internally this will call the validate method for each element having children in the {@link ExpeditedReportTree}
    * @param mandatorySections
    * @return ErrorMessages
    */
   public ReportSubmittability validate(Report report, Collection<ExpeditedReportSection> mandatorySections) {
       // TODO: should validate against complex rules

       ReportSubmittability messages = new ReportSubmittability();
       List<String> mandatoryFields = createMandatoryFieldList(report);

       for (ExpeditedReportSection section : mandatorySections) {
           if (section == null) throw new NullPointerException("The mandatory sections collection must not contain nulls");
           validate(report.getAeReport(), mandatoryFields, section, messages);
       }

       for (AdverseEvent ae : report.getAeReport().getAdverseEvents()) {
           Attribution max = null;
           for (AdverseEventAttribution<?> attribution : requiredAttribution(ae)) {
               if (max == null || attribution.getAttribution().getCode() > max.getCode()) {
                   max = attribution.getAttribution();
               }
           }
           if (max == null || max.getCode() < Attribution.POSSIBLE.getCode()) {
               messages.addValidityMessage(ExpeditedReportSection.ATTRIBUTION_SECTION,
                   String.format(
                       "The adverse event %s is not attributed to a cause. " +
                       "An attribution of possible or higher must be selected for at least one of the causes.",
                       ae.getAdverseEventTerm().getUniversalTerm()));
           }
       }

       return messages;
   }

    private List<AdverseEventAttribution<?>> requiredAttribution(AdverseEvent event) {
        List<AdverseEventAttribution<?>> attributions = new LinkedList<AdverseEventAttribution<?>>();
        attributions.addAll(event.getDiseaseAttributions());
        attributions.addAll(event.getConcomitantMedicationAttributions());
        attributions.addAll(event.getCourseAgentAttributions());
        attributions.addAll(event.getOtherCauseAttributions());
        return attributions;
    }

    private List<String> createMandatoryFieldList(Report report){
        List<String> fields = new LinkedList<String>();
        if(report.getReportDefinition().getMandatoryFields() != null){
            for(ReportMandatoryFieldDefinition field : report.getReportDefinition().getMandatoryFields()){
                if (field.getMandatory()) fields.add(field.getFieldPath());
            }
        }
        return fields;
    }
	
	@Transient
	public void withdrawLastReportVersion(Report report){
		
		ReportVersion reportVersion = report.getLastVersion();
		reportVersion.setReportStatus(ReportStatus.WITHDRAWN);
		reportVersion.setWithdrawnOn(nowFactory.getNow());
		reportVersion.setDueOn(null);
	}
	

	public void setNowFactory(NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }

	public void setSchedulerService(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public void setExpeditedReportTree(ExpeditedReportTree expeditedReportTree) {
		this.expeditedReportTree = expeditedReportTree;
	}



	//TODO: Move this to some utility class

	@SuppressWarnings("unchecked")
    private static boolean isEmpty(Object o){
		if(o instanceof Collection){
			return CollectionUtils.isEmpty((Collection)o);
		}
		return o == null;
	}

	//TODO : Move this to some utility class
	@SuppressWarnings("unchecked")
    private static int size(Object o){
		if(o == null) return 0;
		if(o instanceof Collection){
			return CollectionUtils.size(o);
		}
		return -1;
	}
}