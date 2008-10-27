package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.reportdefinition.ObjectFactory;
import gov.nih.nci.cabig.caaers.reportdefinition.OrganizationType;
import gov.nih.nci.cabig.caaers.reportdefinition.RecipientType;
import gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitionType;
import gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions;

import java.util.ArrayList;
import java.util.List;

public class ReportDefinitionConverter {
	
	private OrganizationDao organizationDao;
	
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public ReportDefinition dtoToDomain(ReportDefinitionType reportDefinitionDto){
		ReportDefinition reportDefinitionDomain = new ReportDefinition();
		
		//Populate Class Level Attributes
		reportDefinitionDomain.setName(reportDefinitionDto.getName());
		reportDefinitionDomain.setLabel(reportDefinitionDto.getLabel());
		reportDefinitionDomain.setDescription(reportDefinitionDto.getDescription());
		reportDefinitionDomain.setAmendable(reportDefinitionDto.isAmendable());
		reportDefinitionDomain.setDuration(reportDefinitionDto.getDuration());
		
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
		Organization organization = organizationDao.getByName(reportDefinitionDto.getOrganization().getName().trim());
		reportDefinitionDomain.setOrganization(organization);
		
		reportDefinitionDomain.setAttributionRequired(reportDefinitionDto.isAttributionRequired());
		reportDefinitionDomain.setExpedited(reportDefinitionDto.isExpedited());
		
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
		
		//Populate Delivery Details.
		ReportDeliveryDefinition reportDeliveryDefinition = null;
		
		for(gov.nih.nci.cabig.caaers.reportdefinition.ReportDeliveryDefinition deliveryDefinition : reportDefinitionDto.getDeliveryDefinition()){
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
		
		for(gov.nih.nci.cabig.caaers.reportdefinition.ReportMandatoryFieldDefinition repoDefinition : reportDefinitionDto.getMandatoryField()){
			reportMandatoryFieldDefinition = new ReportMandatoryFieldDefinition();
			reportMandatoryFieldDefinition.setFieldPath(repoDefinition.getFieldPath());
			if("OPTIONAL".equals(repoDefinition.getMandatory())){
				reportMandatoryFieldDefinition.setMandatory(Mandatory.OPTIONAL);
			}
			if("MANDATORY".equals(repoDefinition.getMandatory())){
				reportMandatoryFieldDefinition.setMandatory(Mandatory.MANDATORY);
			}
			if("NA".equals(repoDefinition.getMandatory())){
				reportMandatoryFieldDefinition.setMandatory(Mandatory.NA);
			}
			mandatoryFields.add(reportMandatoryFieldDefinition);
		}
		reportDefinitionDomain.setMandatoryFields(mandatoryFields);
		
		//Populate PlannedNotifications.
		PlannedEmailNotification plannedEmailNotification = null;
		NotificationBodyContent notificationBodyContentDomain = null;
		List<Recipient> recipients = new ArrayList<Recipient>();
		List<PlannedNotification> plannedNotifications = new ArrayList<PlannedNotification>();
		
		for(gov.nih.nci.cabig.caaers.reportdefinition.PlannedNotification plannedNotification : reportDefinitionDto.getPlannedNotificaiton()){
			plannedEmailNotification = new PlannedEmailNotification();
			plannedEmailNotification.setIndexOnTimeScale(plannedNotification.getIndexOnTimeScale());
			
			for(gov.nih.nci.cabig.caaers.reportdefinition.Recipient recipientDto : plannedNotification.getRecipients()){
				if("ROLE".equals(recipientDto.getType())){
					RoleBasedRecipient roleBasedrecipient = new RoleBasedRecipient();
					roleBasedrecipient.setRoleName(recipientDto.getValue());
					recipients.add(roleBasedrecipient);
				}
				if("CONTACT".equals(recipientDto.getType())){
					ContactMechanismBasedRecipient contactMechanismBasedRecipient = new ContactMechanismBasedRecipient();
					contactMechanismBasedRecipient.setContactName(recipientDto.getValue());
					recipients.add(contactMechanismBasedRecipient);
				}
				plannedEmailNotification.setRecipients(recipients);
				
				notificationBodyContentDomain = new NotificationBodyContent();
				notificationBodyContentDomain.setContentType(plannedNotification.getBodyContent().getContentType());
				notificationBodyContentDomain.setBody(plannedNotification.getBodyContent().getBody());
				plannedEmailNotification.setNotificationBodyContent(notificationBodyContentDomain);
				plannedEmailNotification.setSubjectLine(plannedNotification.getSubject());
				
				plannedNotifications.add(plannedEmailNotification);
			}
			reportDefinitionDomain.setPlannedNotifications(plannedNotifications);
		}
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
		//Populate Organization
		OrganizationType org = objectFactory.createOrganizationType();
		org.setName(reportDefinitionDomain.getOrganization().getName());
		org.setNciInstituteCode(reportDefinitionDomain.getOrganization().getNciInstituteCode());
		reportDefinitionDto.setOrganization(org);
		reportDefinitionDto.setAttributionRequired(reportDefinitionDomain.getAttributionRequired());
		reportDefinitionDto.setExpedited(reportDefinitionDomain.getExpedited());
		reportDefinitionDto.setExpectedDisplayDueDate(reportDefinitionDomain.getExpectedDisplayDueDate());
		reportDefinitionDto.setReportFormat(reportDefinitionDomain.getReportFormatType().getName());
		
		//Populate Delivery Details.
		gov.nih.nci.cabig.caaers.reportdefinition.ReportDeliveryDefinition rDDDto = null; 
		
		List<gov.nih.nci.cabig.caaers.reportdefinition.ReportDeliveryDefinition> deliveryDefinitions = 
				new ArrayList<gov.nih.nci.cabig.caaers.reportdefinition.ReportDeliveryDefinition>();
		
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
		List<gov.nih.nci.cabig.caaers.reportdefinition.ReportMandatoryFieldDefinition> mandatoryFields = 
			new ArrayList<gov.nih.nci.cabig.caaers.reportdefinition.ReportMandatoryFieldDefinition>();
		
		gov.nih.nci.cabig.caaers.reportdefinition.ReportMandatoryFieldDefinition rMFDDto = null;
		
		for(ReportMandatoryFieldDefinition repoMandatoryFieldDefinition : reportDefinitionDomain.getMandatoryFields()){
			rMFDDto = objectFactory.createReportMandatoryFieldDefinition();
			rMFDDto.setFieldPath(repoMandatoryFieldDefinition.getFieldPath());
			rMFDDto.setMandatory(repoMandatoryFieldDefinition.getMandatory().getName());
			mandatoryFields.add(rMFDDto);
			
		}
		reportDefinitionDto.setMandatoryField(mandatoryFields);
		
		//Populate PlannedNotifications.
		gov.nih.nci.cabig.caaers.reportdefinition.PlannedNotification plannedNotificationDto = null;
		gov.nih.nci.cabig.caaers.reportdefinition.Recipient recipientDto = null;
		gov.nih.nci.cabig.caaers.reportdefinition.NotificationBodyContent notificationBodyContentDto = null;
		PlannedEmailNotification plannedEmailNotification = null;
		
		List<gov.nih.nci.cabig.caaers.reportdefinition.PlannedNotification> plannedNotifications = 
				new ArrayList<gov.nih.nci.cabig.caaers.reportdefinition.PlannedNotification>();
		
		List<gov.nih.nci.cabig.caaers.reportdefinition.Recipient> recipients = 
			new ArrayList<gov.nih.nci.cabig.caaers.reportdefinition.Recipient>();
		
		for(PlannedNotification plannedNotification : reportDefinitionDomain.getPlannedNotifications()){
			if(plannedNotification instanceof PlannedEmailNotification){
				plannedEmailNotification = (PlannedEmailNotification)plannedNotification;
			
				plannedNotificationDto = objectFactory.createPlannedNotification();
				plannedNotificationDto.setIndexOnTimeScale(plannedEmailNotification.getIndexOnTimeScale());
				plannedNotificationDto.setSubject(plannedEmailNotification.getSubjectLine());
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
		
		gov.nih.nci.cabig.caaers.reportdefinition.ReportDefinitions reportDefinitions = objectFactory.createReportDefinitions();
		reportDefinitions.getReportDefinition().add(reportDefinitionDto);
		
		return reportDefinitions;
	}
	
}
