package gov.nih.nci.ess.sr;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.ess.safetyreporting.types.ReportDeliveryDefinition;
import gov.nih.nci.ess.safetyreporting.types.ReportMandatoryFieldDefinition;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportDefinitionNotification;
import gov.nih.nci.ess.safetyreporting.types.SafetyReportVersion;
import _21090.org.iso.BAG_TEL;
import _21090.org.iso.TEL;

public class SafetyToExpeditedReportConverterImpl implements SafetyToExpeditedReportConverter{
	private OrganizationDao organizationDao;
	public ExpeditedAdverseEventReport convertSafetyReport(SafetyReportVersion safetyReportVersion) {
		// TODO Auto-generated method stub
		return null;
	}

	public gov.nih.nci.cabig.caaers.domain.report.ReportDefinition convertSafetyReportDefinition(gov.nih.nci.ess.safetyreporting.types.ReportDefinition safetyReportDefinition , 
			gov.nih.nci.cabig.caaers.domain.report.ReportDefinition rd) {
		if (rd == null) {
			rd = new gov.nih.nci.cabig.caaers.domain.report.ReportDefinition();
		}
		if (safetyReportDefinition.getName() != null) {
			rd.setName(ISO21090Helper.value(safetyReportDefinition.getName()));
		}
		if (safetyReportDefinition.getLabel() != null) {
			rd.setLabel(ISO21090Helper.value(safetyReportDefinition.getLabel()));
		}
		if (safetyReportDefinition.getHeader() != null) {
			rd.setHeader(ISO21090Helper.value(safetyReportDefinition.getHeader()));
		}
		if (safetyReportDefinition.getFooter() != null) {
			rd.setFooter(ISO21090Helper.value(safetyReportDefinition.getFooter()));
		}
		if (safetyReportDefinition.getDescription() != null) {
			rd.setDescription(ISO21090Helper.value(safetyReportDefinition.getDescription()));
		}
		if (safetyReportDefinition.getAmendable() != null) {
			rd.setAmendable(ISO21090Helper.value(safetyReportDefinition.getAmendable()));
		}
		if (safetyReportDefinition.getDuration() != null) {
			rd.setDuration(ISO21090Helper.value(safetyReportDefinition.getDuration()));
		}
		if (safetyReportDefinition.getTimeScaleUnitType() != null) {
			rd.setTimeScaleUnitType(TimeScaleUnit.valueOf(ISO21090Helper.value(safetyReportDefinition.getTimeScaleUnitType())));
		}
		if (safetyReportDefinition.getOrganizationIdentifier() != null) {
			rd.setOrganization(organizationDao.getById(ISO21090Helper.value(safetyReportDefinition.getOrganizationIdentifier())));
		}
		if (safetyReportDefinition.getAttributionRequired() != null) {
			rd.setAttributionRequired(ISO21090Helper.value(safetyReportDefinition.getAttributionRequired()));
		}
		if (safetyReportDefinition.getReportFormatType() != null) {
			rd.setReportFormatType(ReportFormatType.valueOf(ISO21090Helper.value(safetyReportDefinition.getReportFormatType())));
		}
		if (safetyReportDefinition.getPhysicianSignOff() != null) {
			rd.setPhysicianSignOff(ISO21090Helper.value(safetyReportDefinition.getPhysicianSignOff()));
		}
		if (safetyReportDefinition.getWorkflowEnabled() != null) {
			rd.setWorkflowEnabled(ISO21090Helper.value(safetyReportDefinition.getWorkflowEnabled()));
		}
		if (safetyReportDefinition.getEnabled() != null) {
			rd.setEnabled(ISO21090Helper.value(safetyReportDefinition.getEnabled()));
		}
		
		//for (ReportDeliveryDefinition srdd:safetyReportDefinition.getReportDeliveryDefinitions()) {
			//rd.addReportDeliveryDefinition(convertReportDeliveryDefinition(srdd));
		//}
		
		return rd;
	}
	
	public gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition convertReportDeliveryDefinition (ReportDeliveryDefinition srdd) {
		gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition rdd = new gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition();
		if (srdd.getFormat()!=null) {
			rdd.setFormat(ReportFormat.valueOf(ISO21090Helper.value(srdd.getFormat())));
		}
		if (srdd.getEntityName()!=null) {
			rdd.setEntityName(ISO21090Helper.value(srdd.getEntityName()));
		}
		if (srdd.getEntityDescription()!=null) {
			rdd.setEntityDescription(ISO21090Helper.value(srdd.getEntityDescription()));
		}
		if (srdd.getEntityType()!=null) {
			rdd.setEntityType(ISO21090Helper.value(srdd.getEntityType()));
		}
		if (srdd.getEndPoint()!=null) {
			rdd.setEndPoint(ISO21090Helper.value(srdd.getEndPoint()));
		}
		if (srdd.getEndPointType()!=null) {
			rdd.setEndPointType(ISO21090Helper.value(srdd.getEndPointType()));
		}
		if (srdd.getUserName()!=null) {
			rdd.setUserName(ISO21090Helper.value(srdd.getUserName()));
		}
		if (srdd.getPassword()!=null) {
			rdd.setPassword(ISO21090Helper.value(srdd.getPassword()));
		}
		if (srdd.getStatus()!=null) {
			rdd.setStatus(ISO21090Helper.value(srdd.getStatus()));
		}
		return rdd;
	}
	
	public gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition convertReportMandatoryFieldDefinition (ReportMandatoryFieldDefinition srmd) {
		gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition rmd = new gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition();
		if (srmd.getFieldPath()!=null) {
			rmd.setFieldPath(ISO21090Helper.value(srmd.getFieldPath()));
		}
		if (srmd.getRuleBindURL()!=null) {
			rmd.setRuleBindURL(ISO21090Helper.value(srmd.getRuleBindURL()));
		}
		if (srmd.getRuleName()!=null) {
			rmd.setRuleName(ISO21090Helper.value(srmd.getRuleName()));
		}
		if (srmd.getSelfReferenced()!=null) {
			rmd.setSelfReferenced(ISO21090Helper.value(srmd.getSelfReferenced()));
		}
		if (srmd.getRequirednessIndicator()!=null) {
			rmd.setMandatory(RequirednessIndicator.valueOf(ISO21090Helper.value(srmd.getFieldPath())));
		}
		
		return rmd;
	}
	
	public gov.nih.nci.cabig.caaers.domain.report.PlannedNotification convertReportDefinitionNotification (SafetyReportDefinitionNotification notification) {
		gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification pn = new gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification();
		if (notification.getIndexOnTimeScale()!=null) {
			pn.setIndexOnTimeScale(ISO21090Helper.value(notification.getIndexOnTimeScale()));
		}

		if (notification.getRecipients()!=null) {
			BAG_TEL bagTel = notification.getRecipients();
			TEL[] tels = bagTel.getItem();
			for (int i=0;i<tels.length;i++) {
				TEL tel = tels[0];
				// parse email from TEL and build Recipient..
				Recipient rr = null;
				pn.addRecipient(rr);
			}
		}
		if (notification.getBodyContent()!=null) {
			NotificationBodyContent nbc = new NotificationBodyContent();
			nbc.setBody(ISO21090Helper.value(notification.getBodyContent()));
			pn.setNotificationBodyContent(nbc);
		}
		if (notification.getSubjectLIne()!=null) {
			pn.setSubjectLine(ISO21090Helper.value(notification.getSubjectLIne()));
		}
		
		return pn;
	}
	
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

}
