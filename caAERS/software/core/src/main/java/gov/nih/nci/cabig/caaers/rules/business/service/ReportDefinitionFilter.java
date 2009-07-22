package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * This class is used to filter report definitions, so that only the earliest one 
 * in a group is returned.
 * The group is a combination of Organization and ReportType
 * 
 * @author Biju Joseph
 *
 */
class ReportDefinitionFilter {
	HashMap<String, ReportDefinition> map = new HashMap<String, ReportDefinition>();
	ReportDefinitionComparator comparator = new ReportDefinitionComparator();

	protected boolean add(ReportDefinition rd) {
		String key = generateKey(rd);
		ReportDefinition existing = map.get(key);
		if (existing == null) {
			map.put(key, rd);
			return true;
		} else if (comparator.compare(existing, rd) < 1) {
			map.put(key, rd);
			return true;
		}
		return false;
	}

	protected String generateKey(ReportDefinition rd) {
		return rd.getOrganization().getId() + ":"
				+ rd.getGroup().getCode();
	}

	public List<ReportDefinition> filter(List<ReportDefinition> list) {
		map.clear();//reset the state.
		for(ReportDefinition rd : list) add(rd);
		return new ArrayList<ReportDefinition>(map.values());
	}
}
