package gov.nih.nci.ess.ae;

import ess.caaers.nci.nih.gov.AdverseEvent;
import ess.caaers.nci.nih.gov.AdverseEventSeriousness;
import ess.caaers.nci.nih.gov.LowLevelTerm;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.TimeValue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import _21090.org.iso.CD;
import _21090.org.iso.TSDateTime;

public class GridToDomainObjectConverter {

	private static final String TS_DATETIME_PATTERN = "yyyyMMddHHmmss";
	
	private CtcTermDao ctcTermDao;
	private LowLevelTermDao lowLevelTermDao;

	public gov.nih.nci.cabig.caaers.domain.AdverseEvent convertAdverseEvent(AdverseEvent gridAe) {
		gov.nih.nci.cabig.caaers.domain.AdverseEvent caaersAe = new gov.nih.nci.cabig.caaers.domain.AdverseEvent();
		if (gridAe.getIdentifier() != null) {
			caaersAe.setId(Integer.parseInt(gridAe.getIdentifier().getExtension()));
		}
		if (gridAe.getComment() != null) {
			caaersAe.setComments(gridAe.getComment().getValue());
		}
		if (gridAe.getResolutionDate() != null) {
			caaersAe.setEndDate(convertToDate(gridAe.getResolutionDate()));
		}
		if (gridAe.getOnsetDate() != null) {
			Date startDate = convertToDate(gridAe.getOnsetDate());
			TimeValue t = new TimeValue();
			t.setHour(startDate.getHours());
			t.setMinute(startDate.getMinutes());
			System.out.println(t.getHourString() + ":" +t.getMinuteString());
			if (t != null) caaersAe.setEventApproximateTime(t);
		}
		
		if (gridAe.getLocationDescription() != null) {
			caaersAe.setEventLocation(gridAe.getLocationDescription().getValue());
		}
		if (gridAe.getExpectedIndicator() != null) {
			caaersAe.setExpected(gridAe.getExpectedIndicator().getValue());
		}
		if (gridAe.getGradeCode() != null) {
			caaersAe.setGrade(Grade.getByCode(Integer.parseInt(gridAe.getGradeCode().getCode())));
		}
		if (gridAe.getReportedDate() != null) {
			caaersAe.setGradedDate(convertToDate(gridAe.getReportedDate()));
		}
		if (gridAe.getHospitalizationRequiredIndicator() != null) {
			if (gridAe.getHospitalizationRequiredIndicator().getValue()) {
				caaersAe.setHospitalization(Hospitalization.YES);
			} else {
				caaersAe.setHospitalization(Hospitalization.NO);
			}
		}
		if (gridAe.getPostReportUpdateDate() != null) {
			caaersAe.setPostSubmissionUpdatedDate(convertToDate(gridAe.getPostReportUpdateDate()));
		}
		if (gridAe.getOnsetDate() != null) {
			caaersAe.setStartDate(convertToDate(gridAe.getOnsetDate()));
		}
		
		if (gridAe.getResult() != null) {
			caaersAe.setDetailsForOther(gridAe.getResult().getOriginalText().getValue());
		}
		
		if (gridAe.getProbabilityCode() != null) {
			caaersAe.setAttributionSummary(Attribution.getByCode(Integer.parseInt(gridAe.getProbabilityCode().getCode())));
		}
		
		if (gridAe.getAdverseEventCtcTerm() != null) {
			AdverseEventCtcTerm adverseEventCtcTerm = new AdverseEventCtcTerm();
			CtcTerm ctcTerm = new CtcTerm();
			if (gridAe.getAdverseEventCtcTerm().getCtepTerm().getOriginalText() != null) {
				ctcTerm.setCtepTerm(gridAe.getAdverseEventCtcTerm().getCtepTerm().getOriginalText().getValue());
			}
			if (gridAe.getAdverseEventCtcTerm().getCtepTerm().getCode() != null) {
				ctcTerm.setCtepCode(gridAe.getAdverseEventCtcTerm().getCtepTerm().getCode());
			}
			adverseEventCtcTerm.setCtcTerm(ctcTerm);
			caaersAe.setAdverseEventCtcTerm(adverseEventCtcTerm);
		}
		
		if (gridAe.getOtherMeddra() != null) {
			if (gridAe.getOtherMeddra() != null) {
				gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm lowLevelTerm = new gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm();
				if (gridAe.getOtherMeddra().getMeddraCode() != null) {
					lowLevelTerm.setMeddraCode(gridAe.getOtherMeddra().getMeddraCode().toString());
				}
				if (gridAe.getOtherMeddra().getMeddraTerm() != null) {
					lowLevelTerm.setMeddraTerm(gridAe.getOtherMeddra().getMeddraTerm().toString());
				}				
				AdverseEventMeddraLowLevelTerm adverseEventMeddraLowLevelTerm = new AdverseEventMeddraLowLevelTerm();
				adverseEventMeddraLowLevelTerm.setLowLevelTerm(lowLevelTerm);
				caaersAe.setAdverseEventMeddraLowLevelTerm(adverseEventMeddraLowLevelTerm);
			}
		}
		
		if (gridAe.getAdverseEventSeriousness() != null) {
			populateOutcomes(gridAe.getAdverseEventSeriousness(),caaersAe);
		}
		return caaersAe;
	}
	
	public void populateOutcomes(AdverseEventSeriousness[] adverseEventSeriousnessList, gov.nih.nci.cabig.caaers.domain.AdverseEvent adverseEvent) {
		for (int i=0 ; i<adverseEventSeriousnessList.length ; i++) {
			
			AdverseEventSeriousness adverseEventSeriousness = adverseEventSeriousnessList[i];
			CD cd = adverseEventSeriousness.getCode();
			OutcomeType oct = OutcomeType.getByCode(Integer.parseInt((cd.getCode())));
			Outcome outCome = new Outcome();
			outCome.setOutcomeType(oct);
			if (oct.equals(OutcomeType.OTHER_SERIOUS) && adverseEventSeriousness.getCode().getOriginalText() !=null) {
				outCome.setOther(adverseEventSeriousness.getCode().getOriginalText().getValue());
			}
			adverseEvent.addOutcome(outCome);
		}
	}
	
	private void populateLowLevelTerm(LowLevelTerm gridLowLevelTerm, gov.nih.nci.cabig.caaers.domain.AdverseEvent adverseEvent) {
		if (gridLowLevelTerm != null) {
			gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm lowLevelTerm = getLowLevelTerm(gridLowLevelTerm.getMeddraCode().getValue(),gridLowLevelTerm.getMeddraTerm().getValue());
			if (lowLevelTerm == null) {
				//throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_021", new String[]{xmlLowLevelTerm.getMeddraCode().toString()},"",Locale.getDefault()));
			} else {			
				AdverseEventMeddraLowLevelTerm adverseEventMeddraLowLevelTerm = new AdverseEventMeddraLowLevelTerm();
				adverseEventMeddraLowLevelTerm.setLowLevelTerm(lowLevelTerm);
				adverseEventMeddraLowLevelTerm.setAdverseEvent(adverseEvent);
				adverseEvent.setAdverseEventMeddraLowLevelTerm(adverseEventMeddraLowLevelTerm);
			}
		}
	}
	private void populateCtcTermLight(AdverseEvent gridAe, gov.nih.nci.cabig.caaers.domain.AdverseEvent adverseEvent) {
		if (gridAe.getAdverseEventCtcTerm() != null) {
			CtcTerm ctcTerm = new CtcTerm();
			if (gridAe.getAdverseEventCtcTerm().getCtepTerm().getOriginalText() != null) {
				ctcTerm.setCtepTerm(gridAe.getAdverseEventCtcTerm().getCtepTerm().getOriginalText().getValue());
			}
			if (gridAe.getAdverseEventCtcTerm().getCtepTerm().getCode() != null) {
				ctcTerm.setCtepTerm(gridAe.getAdverseEventCtcTerm().getCtepTerm().getCode());
			}

		}
	}
	private void populateCtcTerm(AdverseEvent gridAe, gov.nih.nci.cabig.caaers.domain.AdverseEvent adverseEvent) throws CaaersSystemException{
		if (gridAe.getAdverseEventCtcTerm() != null) {
			CtcTerm ctcTerm = ctcTermDao.getCtcTerm(new String[]{gridAe.getAdverseEventCtcTerm().getCtepTerm().getOriginalText().getValue()});//getByCtepCodeandVersion(adverseEventDto.getAdverseEventCtcTerm().getCtepCode(), adverseEventDto.getAdverseEventCtcTerm().getCtcVersion());
			if (ctcTerm == null) {
				throw new CaaersSystemException (" no term found ..");
			} else {
				if (ctcTerm.isOtherRequired()) {
					if (gridAe.getOtherMeddra() != null) {
						gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm lowLevelTerm = getLowLevelTerm(gridAe.getOtherMeddra().getMeddraCode().getValue(),gridAe.getOtherMeddra().getMeddraTerm().getValue());
						if (lowLevelTerm == null) {
							//throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_021", new String[]{adverseEventDto.getOtherMeddra().getMeddraCode().toString()},"",Locale.getDefault()));
							
						} else {
							adverseEvent.setLowLevelTerm(lowLevelTerm);
						}
					} else {
						//throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_022", new String[]{},"",Locale.getDefault()));
					}
				} else {
					if (gridAe.getOtherMeddra() != null) {
						//throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_023", new String[]{adverseEventDto.getAdverseEventCtcTerm().getCtepTerm()},"",Locale.getDefault()));
					}
				}
				List<CtcGrade> ctcGrades = ctcTerm.getContextualGrades();
				boolean gradeAllowed = false;
				for (CtcGrade ctcGrade:ctcGrades) {
					if (ctcGrade.getGrade().getCode() == Integer.parseInt( gridAe.getGradeCode().getCode())) {
						gradeAllowed = true;
						break;
					}
				}
				if (!gradeAllowed) {
					//throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_030", new String[]{adverseEventDto.getGrade()+"",adverseEventDto.getAdverseEventCtcTerm().getCtepTerm()},"",Locale.getDefault()));
				}
				AdverseEventCtcTerm adverseEventCtcTerm = new AdverseEventCtcTerm();
				adverseEventCtcTerm.setCtcTerm(ctcTerm);
				adverseEventCtcTerm.setAdverseEvent(adverseEvent);
				adverseEvent.setAdverseEventTerm(adverseEventCtcTerm);
			}

		}
	}
	private gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm getLowLevelTerm(String code, String term) {
		gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm lowLevelTerm = null;
		lowLevelTerm=lowLevelTermDao.getByCodeAndTerm(code,term);
		return lowLevelTerm;
	}

	
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
					if (type == "AM") {
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

}
	