package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.AdverseEventSeriousness;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

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
			final ess.caaers.nci.nih.gov.LowLevelTerm gridLlt = new ess.caaers.nci.nih.gov.LowLevelTerm();
			gridLlt.setMeddraCode(h.ST(llt.getMeddraCode()));
			gridLlt.setMeddraTerm(h.ST(llt.getMeddraTerm()));
			gridAE.setOtherMeddra(gridLlt);
		}

	}

	private void populateCtcTerm(AdverseEvent gridAE,
			gov.nih.nci.cabig.caaers.domain.AdverseEvent ae) {
		AdverseEventCtcTerm term = ae.getAdverseEventCtcTerm();
		if (term != null) {
			CtcTerm ctcTerm = term.getCtcTerm();
			if (ctcTerm != null) {
				final ess.caaers.nci.nih.gov.CtcTerm gridTerm = new ess.caaers.nci.nih.gov.CtcTerm();
				gridTerm.setCtepTerm(h.CD(ctcTerm.getCtepTerm()));
				gridTerm.setCtepCode(h.CD(ctcTerm.getCtepCode()));
				gridAE.setAdverseEventCtcTerm(gridTerm);
			}
		}
	}

	private TSDateTime convert(TimeValue time) {
		// A day is selected arbitrarily, since it will be ignored anyway. Only
		// hours and minutes matter.
		return convert(new Date(0), time);
	}

	private TSDateTime convert(Date day, TimeValue time) {
		// My apologies for the less readable expression below.
		return convert(DateUtils.setMinutes(DateUtils.setHours(day,
				time.isAM() ? (time.getHour() == 12 ? 0 : time.getHour())
						: (time.getHour() == 12 ? 12 : time.getHour() + 12)),
				time.getMinute()));
	}

}
