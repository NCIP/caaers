package gov.nih.nci.cabig.caaers.domain.dto;

import edu.emory.mathcs.backport.java.util.Collections;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.ReportDefinitionComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ApplicableReportDefinitionsDTO implements Serializable{

	private static final long serialVersionUID = 1905414999906152610L;
	Map<Organization, LinkedHashMap<String, List<ReportDefinition>>> organizationTypeMap;
	
	
	public ApplicableReportDefinitionsDTO(){
		organizationTypeMap = new LinkedHashMap<Organization, LinkedHashMap<String,List<ReportDefinition>>>();
	}
	
	public void addReportDefinition(ReportDefinition rd){
		LinkedHashMap<String, List<ReportDefinition>> typeMap = organizationTypeMap.get(rd.getOrganization());
		
		if(typeMap == null){
			typeMap = new LinkedHashMap<String, List<ReportDefinition>>();
			organizationTypeMap.put(rd.getOrganization(), typeMap);
		}
		
		List<ReportDefinition> rdSet = typeMap.get(rd.getGroup().getName());
		if(rdSet == null){
			rdSet = new ArrayList<ReportDefinition>();
			typeMap.put(rd.getGroup().getName(), rdSet);
		}
		rdSet.add(rd);
	}
	
	
	public Collection<Organization> getOrganizations(){
		return organizationTypeMap.keySet();
	}
	
	public List<ReportDefinition> getReportDefinitions(){
		List<ReportDefinition> reportDefinitions = new ArrayList<ReportDefinition>();
		for(Organization org : getOrganizations()){
			HashMap<String, List<ReportDefinition>> defMap = getReportsByOrganization(org);
			for(List<ReportDefinition> defs : defMap.values()){
				Collections.sort(defs);
				reportDefinitions.addAll(defs);
			}
		}
		return reportDefinitions;
	}
	
	public HashMap<String, List<ReportDefinition>> getReportsByOrganization(Organization org){
		return organizationTypeMap.get(org);
	}
	
	public Map<Organization, LinkedHashMap<String, List<ReportDefinition>>> getOrganizationTypeMap() {
		return organizationTypeMap;
	}
	public void setOrganizationTypeMap(Map<Organization, LinkedHashMap<String, List<ReportDefinition>>> organizationTypeMap) {
		this.organizationTypeMap = organizationTypeMap;
	}
	
	public Map<Integer, ReportDefinition> getReportDefinitionMap(){
		Map<Integer, ReportDefinition> reportDefinitionMap = new LinkedHashMap<Integer, ReportDefinition>();
		for(ReportDefinition reportDefinition : getReportDefinitions()){
			reportDefinitionMap.put(reportDefinition.getId(), reportDefinition);
		}
		return reportDefinitionMap;
	}
	
}
