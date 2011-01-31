package gov.nih.nci.ess.sr;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.AdverseEventSeriousness;
import ess.caaers.nci.nih.gov.AeTerminology;
import ess.caaers.nci.nih.gov.AuditTrail;
import ess.caaers.nci.nih.gov.AuditTrailValue;
import ess.caaers.nci.nih.gov.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.audit.domain.AuditHistory;
import gov.nih.nci.cabig.ctms.audit.domain.AuditHistoryDetail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import _21090.org.iso.CD;
import _21090.org.iso.NullFlavor;
import _21090.org.iso.TSDateTime;

public class DomainToGridObjectConverter {

	private static final String TS_DATETIME_PATTERN = "yyyyMMddHHmmss";
	private static final ISO21090Helper h = null;

	private CtcTermDao ctcTermDao;
	private LowLevelTermDao lowLevelTermDao;

	private Date convert(TSDateTime tsDateTime) {
		try {
			if (tsDateTime != null && tsDateTime.getNullFlavor() == null) {
				String value = tsDateTime.getValue();
				if (value != null) {
					return DateUtils.parseDate(value,
							new String[] { TS_DATETIME_PATTERN });
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private TSDateTime convert(Date date) {
		TSDateTime tsDateTime = new TSDateTime();
		if (date != null) {
			tsDateTime.setValue(DateFormatUtils.format(date,
					TS_DATETIME_PATTERN));
		} else {
			tsDateTime.setNullFlavor(NullFlavor.NI);
		}
		return tsDateTime;

	}

	private TimeValue convertToTimeValue(TSDateTime tsDateTime) {
		TimeValue t = new TimeValue();
		try {
			if (tsDateTime != null && tsDateTime.getNullFlavor() == null) {
				String value = tsDateTime.getValue();
				String[] values = value.split(":");
				if (values.length == 3) {
					t.setHourString(values[0]);
					t.setMinuteString(values[1]);
					String type = values[2];
					if ("AM".equals(type)) {
						t.setType(0);
					} else {
						t.setType(1);
					}
					return t;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
	}

	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}

	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}

	public AdverseEvent convertAdverseEvent(
			gov.nih.nci.cabig.caaers.domain.AdverseEvent ae) {
		AdverseEvent gridAE = new AdverseEvent();
		gridAE.setIdentifier(h.II(ae.getId().toString()));
		gridAE.setComment(h.ST(ae.getComments()));
		gridAE.setResolutionDate(convert(ae.getEndDate()));
		gridAE.setLocationDescription(h.ST(ae.getEventLocation()));
		gridAE.setExpectedIndicator(h.BL(ae.getExpected()));
		gridAE.setGradeCode(h.CD(ae.getGrade() != null ? ae.getGrade()
				.getCode().toString() : null));
		gridAE.setReportedDate(convert(ae.getGradedDate()));
		if (ae.getHospitalization() != null) {
			gridAE.setHospitalizationRequiredIndicator(h.BL(ae
					.getHospitalization().equals(Hospitalization.YES)));
		} else {
			gridAE.setHospitalizationRequiredIndicator(h.BL(NullFlavor.NI));
		}
		gridAE.setPostReportUpdateDate(convert(ae
				.getPostSubmissionUpdatedDate()));
		gridAE.setOnsetDate(convert(ae.getStartDate(),
				ae.getEventApproximateTime()));
		gridAE.setResult(h.CD(ae.getDetailsForOther()));
		if (ae.getAttributionSummary() != null) {
			gridAE.setProbabilityCode(h.CD(ae.getAttributionSummary().getCode()
					.toString()));
		} else {
			gridAE.setProbabilityCode(h.CD(NullFlavor.NI));
		}
		populateCtcTerm(gridAE, ae);
		populateLowLevelTerm(gridAE, ae);
		populateOutcomes(gridAE, ae);
		return gridAE;
	}

	private void populateOutcomes(AdverseEvent gridAE,
			gov.nih.nci.cabig.caaers.domain.AdverseEvent ae) {
		List<AdverseEventSeriousness> list = new ArrayList<AdverseEventSeriousness>();
		for (Outcome outcome : ae.getOutcomes()) {
			AdverseEventSeriousness seriousness = new AdverseEventSeriousness();
			final CD cd = h.CD(outcome.getOutcomeType().getCode().toString());
			cd.setOriginalText(h.EDText(outcome.getOther()));
			seriousness.setCode(cd);
			list.add(seriousness);
		}
		gridAE.setAdverseEventSeriousness(list
				.toArray(new AdverseEventSeriousness[0]));
	}

	private void populateLowLevelTerm(AdverseEvent gridAE,
			gov.nih.nci.cabig.caaers.domain.AdverseEvent ae) {
		LowLevelTerm llt = ae.getLowLevelTerm();
		if (llt != null) {
			final ess.caaers.nci.nih.gov.LowLevelTerm gridLlt = convert(llt);
			gridAE.setOtherMeddra(gridLlt);
		}

	}

	/**
	 * @param llt
	 * @return
	 */
	public ess.caaers.nci.nih.gov.LowLevelTerm convert(LowLevelTerm llt) {
		final ess.caaers.nci.nih.gov.LowLevelTerm gridLlt = new ess.caaers.nci.nih.gov.LowLevelTerm();
		if (llt != null) {
			gridLlt.setMeddraCode(h.ST(llt.getMeddraCode()));
			gridLlt.setMeddraTerm(h.ST(llt.getMeddraTerm()));
		}
		return gridLlt;
	}

	private void populateCtcTerm(AdverseEvent gridAE,
			gov.nih.nci.cabig.caaers.domain.AdverseEvent ae) {
		AdverseEventCtcTerm term = ae.getAdverseEventCtcTerm();
		if (term != null) {
			CtcTerm ctcTerm = term.getCtcTerm();
			if (ctcTerm != null) {
				final ess.caaers.nci.nih.gov.CtcTerm gridTerm = convert(ctcTerm);
				gridAE.setAdverseEventCtcTerm(gridTerm);
			}
		}
	}

	/**
	 * @param ctcTerm
	 * @return
	 */
	public ess.caaers.nci.nih.gov.CtcTerm convert(CtcTerm ctcTerm) {
		final ess.caaers.nci.nih.gov.CtcTerm gridTerm = new ess.caaers.nci.nih.gov.CtcTerm();
		if (ctcTerm != null) {
			gridTerm.setCtepTerm(h.CD(ctcTerm.getCtepTerm()));
			gridTerm.setCtepCode(h.CD(ctcTerm.getCtepCode()));
		}
		return gridTerm;
	}

	private TSDateTime convert(TimeValue time) {
		// A day is selected arbitrarily, since it will be ignored anyway. Only
		// hours and minutes matter.
		return convert(new Date(0), time);
	}

	private TSDateTime convert(Date day, TimeValue time) {
		if (time == null || time.isBlank()) {
			return convert(day);
		}
		day = day == null ? new Date(0) : day;
		// My apologies for the less readable expression below.
		return convert(DateUtils.setMinutes(DateUtils.setHours(day,
				time.isAM() ? (time.getHour() == 12 ? 0 : time.getHour())
						: (time.getHour() == 12 ? 12 : time.getHour() + 12)),
				time.getMinute()));
	}

	public AuditTrail convert(AuditHistory his) {
		AuditTrail trail = new AuditTrail();
		trail.setUrl(h.ST(his.getUrl()));
		trail.setUserName(h.ST(his.getUsername()));
		trail.setIpAddress(h.ST(his.getIp()));
		trail.setRecordedTime(convert(his.getTime()));
		trail.setObjectName(h.ST(his.getClassName()));
		trail.setObjectId(h.ST(his.getEntityId()));
		trail.setOperation(h.ST(his.getOperation() != null ? his.getOperation()
				.name() : null));
		trail.setIdentifier(h.II(his.getAuditEventId()));

		List<AuditTrailValue> list = new ArrayList<AuditTrailValue>();
		for (AuditHistoryDetail detail : his.getAuditHistoryDetails()) {
			list.add(convert(detail));
		}
		trail.setValues(list.toArray(new AuditTrailValue[0]));
		return trail;
	}

	public AuditTrailValue convert(AuditHistoryDetail detail) {
		AuditTrailValue v = new AuditTrailValue();
		v.setAttributeName(h.ST(detail.getAttributeName()));
		v.setPreviousValue(h.ST(detail.getPreviousValue()));
		v.setNewValue(h.ST(detail.getCurrentValue()));
		return v;
	}

	public AeTerminology convert(
			gov.nih.nci.cabig.caaers.domain.AeTerminology aeTerminology) {
		AeTerminology gridTerm = new AeTerminology();
		if (aeTerminology.getTerm() != null) {
			gridTerm.setTermCode(h.II(aeTerminology.getTerm().name()));
		}
		if (aeTerminology.getCtcVersion() != null) {
			gridTerm.setTermVersion(h.II(aeTerminology.getCtcVersion()
					.getName()));
		}
		if (aeTerminology.getMeddraVersion() != null) {
			gridTerm.setMeddra(h.II(aeTerminology.getMeddraVersion().getName()));
		}
		return gridTerm;
	}

	public SolicitedAdverseEvent convert(
			gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent ae) {
		SolicitedAdverseEvent gridAe = new SolicitedAdverseEvent();
		if (ae.getCtcterm() != null) {
			gridAe.setCtcTerm(convert(ae.getCtcterm()));
		}
		if (ae.getLowLevelTerm() != null) {
			gridAe.setLowLevelTerm(convert(ae.getLowLevelTerm()));
		}
		return gridAe;
	}

}
