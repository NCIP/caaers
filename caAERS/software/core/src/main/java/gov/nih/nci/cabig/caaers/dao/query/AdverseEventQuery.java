package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;

public class AdverseEventQuery extends AbstractQuery {
	
	public static final String AE_ALIAS = "ae";
	
	public AdverseEventQuery() {
		super("select distinct "+AE_ALIAS+" from AdverseEvent "+ AE_ALIAS);
	}
	
	public void filterBySolicited(boolean flag , String operator) {
		andWhere (AE_ALIAS+".solicited "+operator+" :flag");
		setParameter("flag" , flag);
	}

	public void filterByGrade(Integer code , String operator) {
		andWhere (AE_ALIAS+".grade "+operator+" :grade");
		setParameter("grade" , Grade.getByCode(code));
	}

	public void filterByHospitalization(Integer code , String operator) {
		andWhere (AE_ALIAS+".hospitalization "+operator+" :hospitalization");
		setParameter("hospitalization" , Hospitalization.getByCode(code));
	}
	
	public void filterByExpected(boolean flag , String operator) {
		andWhere (AE_ALIAS+".expected "+operator+" :flag");
		setParameter("flag" , flag);
	}
	
	public void filterByAttribution(Integer code , String operator) {
		andWhere (AE_ALIAS+".attributionSummary "+operator+" :attributionSummary");
		setParameter("attributionSummary" , Attribution.getByCode(code));
	}
	
	public void filterByVerbatim(String verbatim , String operator) {
		andWhere (AE_ALIAS+".detailsForOther "+operator+" :verbatim");
		if (operator.equals("like")) {
			setParameter("verbatim" , getLikeValue(verbatim));
		} else {
			setParameter("verbatim" , verbatim);
		}
	}
}
