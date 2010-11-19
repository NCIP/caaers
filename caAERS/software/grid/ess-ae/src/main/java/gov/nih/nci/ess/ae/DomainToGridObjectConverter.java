package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.TimeValue;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import _21090.org.iso.TSDateTime;

public class DomainToGridObjectConverter {

	private static final String TS_DATETIME_PATTERN = "yyyyMMddHHmmss";
	private static final ISO21090Helper h = null;

	private CtcTermDao ctcTermDao;
	private LowLevelTermDao lowLevelTermDao;

	private Date convertToDate(TSDateTime tsDateTime) {
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
		return gridAE;
	}

}
