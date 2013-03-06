/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import java.util.ArrayList;
import java.util.List;

public class ReportVersionSearchResultDTO {
	
	List<ReportVersion> results;
	
	public List<ReportVersion> getResults() {
		if(results == null) results = new ArrayList<ReportVersion>();
		return results;
	}
	public void setResults(List<ReportVersion> results) {
		this.results = results;
	}
	
	public void addResult(ReportVersion dto){
		if(results == null) results = new ArrayList<ReportVersion>();
		results.add(dto);
	}

}
