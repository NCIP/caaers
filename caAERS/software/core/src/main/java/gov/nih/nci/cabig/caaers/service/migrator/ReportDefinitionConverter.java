/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.ConfigPropertyDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportDefinitionNotificationType;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportType;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.GroupType;
import gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ObjectFactory;
import gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ParentType;
import gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.RecipientType;
import gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitionType;
import gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitions;
import gov.nih.nci.cabig.caaers.service.synchronizer.ReportNotificationDefinitionSynchronizer;

import java.util.ArrayList;
import java.util.List;

/*
* @author Ion C. Olaru
* @author Biju Joseph
* */
public class ReportDefinitionConverter {
	
	private OrganizationDao organizationDao;
	private ConfigPropertyDao configPropertyDao;
	private ReportDefinitionDao reportDefinitionDao;
	
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	
	public void setConfigPropertyDao(ConfigPropertyDao configPropertyDao) {
		this.configPropertyDao = configPropertyDao;
	}
	
	public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao){
		this.reportDefinitionDao = reportDefinitionDao;
	}

    /*
    * Converting the JAXB object to caAERS equivalent object
    * @author Ion C. Olaru
    * @param reportDefinitionDto - JAXB object
    * */
	public ReportDefinition dtoToDomain(ReportDefinitionType reportDefinitionDto){
		ReportDefinition reportDefinitionDomain = new ReportDefinition();
		
		//Populate Class Level Attributes
		reportDefinitionDomain.setName(reportDefinitionDto.getName());
		reportDefinitionDomain.setLabel(reportDefinitionDto.getLabel());
		reportDefinitionDomain.setDescription(reportDefinitionDto.getDescription());
		reportDefinitionDomain.setAmendable(reportDefinitionDto.isAmendable());
		reportDefinitionDomain.setDuration(reportDefinitionDto.getDuration());
		reportDefinitionDomain.setPhysicianSignOff(reportDefinitionDto.isPhysicianSignOff());
		
		if("SECOND".equals(reportDefinitionDto.getTimeScaleUnit())){
			reportDefinitionDomain.setTimeScaleUnitType(TimeScaleUnit.SECOND);
		}
		if("MINUTE".equals(reportDefinitionDto.getTimeScaleUnit())){
			reportDefinitionDomain.setTimeScaleUnitType(TimeScaleUnit.MINUTE);
		}
		if("HOUR".equals(reportDefinitionDto.getTimeScaleUnit())){
			reportDefinitionDomain.setTimeScaleUnitType(TimeScaleUnit.HOUR);
		}
		if("DAY".equals(reportDefinitionDto.getTimeScaleUnit())){
			reportDefinitionDomain.setTimeScaleUnitType(TimeScaleUnit.DAY);
		}
		if("WEEK".equals(reportDefinitionDto.getTimeScaleUnit())){
			reportDefinitionDomain.setTimeScaleUnitType(TimeScaleUnit.WEEK);
		}
		if("MONTH".equals(reportDefinitionDto.getTimeScaleUnit())){
			reportDefinitionDomain.setTimeScaleUnitType(TimeScaleUnit.MONTH);
		}
		
		//Populate Organization
		Organization organization = null;
		if(reportDefinitionDto.getOrganization().getNciInstituteCode() != null)
			organization = organizationDao.getByNCIcode(reportDefinitionDto.getOrganization().getNciInstituteCode());
		else if(reportDefinitionDto.getOrganization().getName() != null)
			organization = organizationDao.getByName(reportDefinitionDto.getOrganization().getName().trim());
		reportDefinitionDomain.setOrganization(organization);
		
		//Populate Parent
		if(reportDefinitionDto.getParent() != null){
			ReportDefinition parentReportDefinition = reportDefinitionDao.getByName(reportDefinitionDto.getParent().getName());
			reportDefinitionDomain.setParent(parentReportDefinition);
		}
		
		//Populate workflowEnabled
		reportDefinitionDomain.setWorkflowEnabled(reportDefinitionDto.isWorkflowEnabled());
		
		//Populate enabled
		reportDefinitionDomain.setEnabled(reportDefinitionDto.isEnabled());
		
		reportDefinitionDomain.setAttributionRequired(reportDefinitionDto.isAttributionRequired());
		//populate the correct config property
		if(reportDefinitionDto.getGroup() == null){
			reportDefinitionDomain.setGroup(configPropertyDao.getByTypeAndCode(ConfigPropertyType.REPORT_GROUP,"RT_AdEERS"));
		}else{
			reportDefinitionDomain.setGroup(configPropertyDao.getByTypeAndCode(ConfigPropertyType.valueOf(reportDefinitionDto.getGroup().getConfigType()), 
					reportDefinitionDto.getGroup().getCode()));
		}
		if(reportDefinitionDto.getReportType() == null){
			reportDefinitionDomain.setReportType(ReportType.REPORT);
		}else{
			reportDefinitionDomain.setReportType(ReportType.valueOf(reportDefinitionDto.getReportType().name()));
		}

		if("CAAERSXML".equals(reportDefinitionDto.getReportFormat())){
			reportDefinitionDomain.setReportFormatType(ReportFormatType.CAAERSXML);
		}
		if("ADEERSPDF".equals(reportDefinitionDto.getReportFormat())){
			reportDefinitionDomain.setReportFormatType(ReportFormatType.ADEERSPDF);
		}
		if("MEDWATCHPDF".equals(reportDefinitionDto.getReportFormat())){
			reportDefinitionDomain.setReportFormatType(ReportFormatType.MEDWATCHPDF);
		}
		if("DCPSAEFORM".equals(reportDefinitionDto.getReportFormat())){
			reportDefinitionDomain.setReportFormatType(ReportFormatType.DCPSAEFORM);
		}
		if("CIOMSFORM".equals(reportDefinitionDto.getReportFormat())){
			reportDefinitionDomain.setReportFormatType(ReportFormatType.CIOMSFORM);
		}
		if("CIOMSSAEFORM".equals(reportDefinitionDto.getReportFormat())){
			reportDefinitionDomain.setReportFormatType(ReportFormatType.CIOMSSAEFORM);
		}
		if("CUSTOM_REPORT".equals(reportDefinitionDto.getReportFormat())){
			reportDefinitionDomain.setReportFormatType(ReportFormatType.CUSTOM_REPORT);
		}

		//Populate Delivery Details.
		ReportDeliveryDefinition reportDeliveryDefinition = null;
		
		for(gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDeliveryDefinition deliveryDefinition : reportDefinitionDto.getDeliveryDefinition()){
			reportDeliveryDefinition = new ReportDeliveryDefinition();
			if("TEXT".equals(deliveryDefinition.getFormat())){
				reportDeliveryDefinition.setFormat(ReportFormat.TEXT);
			}
			if("XML".equals(deliveryDefinition.getFormat())){
				reportDeliveryDefinition.setFormat(ReportFormat.XML);
			}
			if("PDF".equals(deliveryDefinition.getFormat())){
				reportDeliveryDefinition.setFormat(ReportFormat.PDF);
			}
			reportDeliveryDefinition.setEntityName(deliveryDefinition.getEntityName());
			reportDeliveryDefinition.setEntityDescription(deliveryDefinition.getEntityDescription());
			reportDeliveryDefinition.setEntityType(deliveryDefinition.getEntityType());
			reportDeliveryDefinition.setEndPointType(deliveryDefinition.getEndPointType());
			reportDeliveryDefinition.setEndPoint(deliveryDefinition.getEndPoint());
			reportDeliveryDefinition.setUserName(deliveryDefinition.getUserName());
			reportDeliveryDefinition.setPassword(deliveryDefinition.getPassword());
			reportDefinitionDomain.addReportDeliveryDefinition(reportDeliveryDefinition);
		}
		
		//Populate Mandatory Fields.
		ReportMandatoryFieldDefinition reportMandatoryFieldDefinition = null;
		List<ReportMandatoryFieldDefinition> mandatoryFields = new ArrayList<ReportMandatoryFieldDefinition>();
		
		for(gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportMandatoryFieldDefinition repoDefinition : reportDefinitionDto.getMandatoryField()){
			reportMandatoryFieldDefinition = new ReportMandatoryFieldDefinition("", RequirednessIndicator.OPTIONAL);
			reportMandatoryFieldDefinition.setFieldPath(repoDefinition.getFieldPath());
			if("OPTIONAL".equals(repoDefinition.getMandatory())){
				reportMandatoryFieldDefinition.setMandatory(RequirednessIndicator.OPTIONAL);
			}
			if("MANDATORY".equals(repoDefinition.getMandatory())){
				reportMandatoryFieldDefinition.setMandatory(RequirednessIndicator.MANDATORY);
			}
			if("NA".equals(repoDefinition.getMandatory())){
				reportMandatoryFieldDefinition.setMandatory(RequirednessIndicator.NA);
			}
            if("RULE".equals(repoDefinition.getMandatory())){
                reportMandatoryFieldDefinition.setMandatory(RequirednessIndicator.RULE);
                reportMandatoryFieldDefinition.setRuleBindURL(repoDefinition.getRuleBindURL());
                reportMandatoryFieldDefinition.setRuleName(repoDefinition.getRuleName());
                reportMandatoryFieldDefinition.setSelfReferenced(repoDefinition.isSelfReferenced());
            }
			mandatoryFields.add(reportMandatoryFieldDefinition);
		}
		reportDefinitionDomain.setMandatoryFields(mandatoryFields);
		
		//Populate PlannedNotifications.
		PlannedEmailNotification plannedEmailNotification = null;
		NotificationBodyContent notificationBodyContentDomain = null;
		List<PlannedNotification> plannedNotifications = new ArrayList<PlannedNotification>();
		
		for(gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.PlannedNotification plannedNotification : reportDefinitionDto.getPlannedNotificaiton()){
			plannedEmailNotification = new PlannedEmailNotification();
			List<Recipient> recipients = new ArrayList<Recipient>();
			plannedEmailNotification.setIndexOnTimeScale(plannedNotification.getIndexOnTimeScale());
			if(plannedNotification.getNotificationType() != null) {
				plannedEmailNotification.setReportDefinitionNotificationType(ReportDefinitionNotificationType.
						valueOf(plannedNotification.getNotificationType().name()));
			}
			
			for(gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.Recipient recipientDto : plannedNotification.getRecipients()){
				if("ROLE".equals(recipientDto.getType().toString())){
					RoleBasedRecipient roleBasedrecipient = new RoleBasedRecipient();
					roleBasedrecipient.setRoleName(recipientDto.getValue());
					recipients.add(roleBasedrecipient);
				}
				if("CONTACT".equals(recipientDto.getType().toString())){
					ContactMechanismBasedRecipient contactMechanismBasedRecipient = new ContactMechanismBasedRecipient();
					contactMechanismBasedRecipient.setContactName(recipientDto.getValue());
					recipients.add(contactMechanismBasedRecipient);
				}
				plannedEmailNotification.setRecipients(recipients);
			}
			
			notificationBodyContentDomain = new NotificationBodyContent();
			notificationBodyContentDomain.setContentType(plannedNotification.getBodyContent().getContentType());
			notificationBodyContentDomain.setBody(plannedNotification.getBodyContent().getBody());
			plannedEmailNotification.setNotificationBodyContent(notificationBodyContentDomain);
			plannedEmailNotification.setSubjectLine(plannedNotification.getSubject());
			
			plannedNotifications.add(plannedEmailNotification);
		}
		reportDefinitionDomain.setPlannedNotifications(plannedNotifications);
		return reportDefinitionDomain;
	}
	
	public ReportDefinitions domainToDto(ReportDefinition reportDefinitionDomain){
		ObjectFactory objectFactory = new ObjectFactory();
		//Populate Class Level attributes
		ReportDefinitionType reportDefinitionDto = objectFactory.createReportDefinitionType();
		reportDefinitionDto.setName(reportDefinitionDomain.getName());
		reportDefinitionDto.setLabel(reportDefinitionDomain.getLabel());
		reportDefinitionDto.setDescription(reportDefinitionDomain.getDescription());
		reportDefinitionDto.setAmendable(reportDefinitionDomain.getAmendable());
		reportDefinitionDto.setDuration(reportDefinitionDomain.getDuration());
		reportDefinitionDto.setTimeScaleUnit(reportDefinitionDomain.getTimeScaleUnitType().getName());
		reportDefinitionDto.setPhysicianSignOff((reportDefinitionDomain.getPhysicianSignOff() == null) ? false: reportDefinitionDomain.getPhysicianSignOff());
		
		//Populate Organization
		OrganizationType org = objectFactory.createOrganizationType();
		org.setName(reportDefinitionDomain.getOrganization().getName());
		org.setNciInstituteCode(reportDefinitionDomain.getOrganization().getNciInstituteCode());
		reportDefinitionDto.setOrganization(org);
		reportDefinitionDto.setAttributionRequired(reportDefinitionDomain.getAttributionRequired());
		//set the group
		GroupType groupType = new GroupType();
		groupType.setCode(reportDefinitionDomain.getGroup().getCode());
		groupType.setConfigType(reportDefinitionDomain.getGroup().getConfigType().name());
		reportDefinitionDto.setGroup(groupType);
		
		//set the parent
		if(reportDefinitionDomain.getParent() != null){
			ParentType parent = objectFactory.createParentType();
			parent.setName(reportDefinitionDomain.getParent().getName());
			reportDefinitionDto.setParent(parent);
		}
		
		//set workflowEnabled flag
		if(reportDefinitionDomain.getWorkflowEnabled() != null)
			reportDefinitionDto.setWorkflowEnabled(reportDefinitionDomain.getWorkflowEnabled());
		
		//set enabled flag
		if(reportDefinitionDomain.getEnabled() != null)
			reportDefinitionDto.setEnabled(reportDefinitionDomain.getEnabled());
		
		//set the report type
		reportDefinitionDto.setReportType(gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportType.valueOf(reportDefinitionDomain.getReportType().name()));
		
		reportDefinitionDto.setExpectedDisplayDueDate(reportDefinitionDomain.getExpectedDisplayDueDate());
		reportDefinitionDto.setReportFormat(reportDefinitionDomain.getReportFormatType().getName());
		
		//Populate Delivery Details.
		gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDeliveryDefinition rDDDto = null; 
		
		List<gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDeliveryDefinition> deliveryDefinitions = 
				new ArrayList<gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDeliveryDefinition>();
		
		for(ReportDeliveryDefinition reportDeliveryDefinition : reportDefinitionDomain.getDeliveryDefinitions()){
			rDDDto = objectFactory.createReportDeliveryDefinition();
			rDDDto.setFormat(reportDeliveryDefinition.getFormat().getDisplayName());
			rDDDto.setEntityName(reportDeliveryDefinition.getEntityName());
			rDDDto.setEntityDescription(reportDeliveryDefinition.getEntityDescription());
			rDDDto.setEntityType(reportDeliveryDefinition.getEntityType());
			rDDDto.setEndPointType(reportDeliveryDefinition.getEndPointType());
			rDDDto.setEndPoint(reportDeliveryDefinition.getEndPoint());
			rDDDto.setUserName(reportDeliveryDefinition.getUserName());
			rDDDto.setPassword(reportDeliveryDefinition.getPassword());
			deliveryDefinitions.add(rDDDto);
			
		}
		reportDefinitionDto.setDeliveryDefinition(deliveryDefinitions);
		
		//Populate Mandatory Fields.
		List<gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportMandatoryFieldDefinition> mandatoryFields = 
			new ArrayList<gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportMandatoryFieldDefinition>();
		
		gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportMandatoryFieldDefinition rMFDDto = null;
		
		for(ReportMandatoryFieldDefinition repoMandatoryFieldDefinition : reportDefinitionDomain.getMandatoryFields()){
			rMFDDto = objectFactory.createReportMandatoryFieldDefinition();
			rMFDDto.setFieldPath(repoMandatoryFieldDefinition.getFieldPath());
			rMFDDto.setMandatory(repoMandatoryFieldDefinition.getMandatory().getName());
            rMFDDto.setRuleBindURL(repoMandatoryFieldDefinition.getRuleBindURL());
            rMFDDto.setRuleName(repoMandatoryFieldDefinition.getRuleName());
            rMFDDto.setSelfReferenced(repoMandatoryFieldDefinition.isSelfReferenced());
			mandatoryFields.add(rMFDDto);
			
		}
		reportDefinitionDto.setMandatoryField(mandatoryFields);
		
		//Populate PlannedNotifications.
		gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.PlannedNotification plannedNotificationDto = null;
		gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.Recipient recipientDto = null;
		gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.NotificationBodyContent notificationBodyContentDto = null;
		PlannedEmailNotification plannedEmailNotification = null;
		
		List<gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.PlannedNotification> plannedNotifications = 
				new ArrayList<gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.PlannedNotification>();
			
		for(PlannedNotification plannedNotification : reportDefinitionDomain.getPlannedNotifications()){
			
			List<gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.Recipient> recipients = 
					new ArrayList<gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.Recipient>();
			
			if(plannedNotification instanceof PlannedEmailNotification){
				plannedEmailNotification = (PlannedEmailNotification)plannedNotification;
			
				plannedNotificationDto = objectFactory.createPlannedNotification();
				plannedNotificationDto.setIndexOnTimeScale(plannedEmailNotification.getIndexOnTimeScale());
				plannedNotificationDto.setSubject(plannedEmailNotification.getSubjectLine());
				if(plannedEmailNotification.getReportDefinitionNotificationType() != null){
					plannedNotificationDto.setNotificationType(gov.nih.nci.cabig.caaers.integration.schema.
							reportdefinition.ReportDefinitionNotificationType.valueOf(
							plannedEmailNotification.getReportDefinitionNotificationType().name()));
				}
				for(Recipient recipient : plannedEmailNotification.getRecipients()){
					recipientDto = objectFactory.createRecipient();
					if(recipient instanceof ContactMechanismBasedRecipient){
						recipientDto.setType(RecipientType.CONTACT);
						recipientDto.setValue(recipient.getContact());
					}
					if(recipient instanceof RoleBasedRecipient){
						recipientDto.setType(RecipientType.ROLE);
						recipientDto.setValue(recipient.getContact());
					}
					recipients.add(recipientDto);
				}
				plannedNotificationDto.setRecipients(recipients);
				
				notificationBodyContentDto = objectFactory.createNotificationBodyContent();
				notificationBodyContentDto.setBody(plannedNotification.getNotificationBodyContent().getBody());
				notificationBodyContentDto.setContentType(plannedNotification.getNotificationBodyContent().getContentType());
				plannedNotificationDto.setBodyContent(notificationBodyContentDto);
				
				plannedNotifications.add(plannedNotificationDto);
			}
		}
		reportDefinitionDto.setPlannedNotificaiton(plannedNotifications);
		
		gov.nih.nci.cabig.caaers.integration.schema.reportdefinition.ReportDefinitions reportDefinitions = objectFactory.createReportDefinitions();
		reportDefinitions.getReportDefinition().add(reportDefinitionDto);
		
		return reportDefinitions;
	}
	
}
