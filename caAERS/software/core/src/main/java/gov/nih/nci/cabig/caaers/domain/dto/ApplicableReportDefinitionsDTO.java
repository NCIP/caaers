/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.Collections;
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
 * The Class ApplicableReportDefinitionsDTO.
 *
 * @author Biju Joseph
 */
public class ApplicableReportDefinitionsDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1905414999906152610L;
	
	/** The organization type map. */
	Map<Organization, LinkedHashMap<String, List<ReportDefinition>>> organizationTypeMap;
	
	
	/**
	 * Instantiates a new applicable report definitions dto.
	 */
	public ApplicableReportDefinitionsDTO(){
		organizationTypeMap = new LinkedHashMap<Organization, LinkedHashMap<String,List<ReportDefinition>>>();
	}
	
	/**
	 * Adds the report definition.
	 *
	 * @param rd the rd
	 */
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
	
	
	/**
	 * Gets the organizations.
	 *
	 * @return the organizations
	 */
	public Collection<Organization> getOrganizations(){
		return organizationTypeMap.keySet();
	}
	
	/**
	 * Gets the report definitions.
	 *
	 * @return the report definitions
	 */
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
	
	/**
	 * Gets the reports by organization.
	 *
	 * @param org the org
	 * @return the reports by organization
	 */
	public HashMap<String, List<ReportDefinition>> getReportsByOrganization(Organization org){
		return organizationTypeMap.get(org);
	}
	
	/**
	 * Gets the organization type map.
	 *
	 * @return the organization type map
	 */
	public Map<Organization, LinkedHashMap<String, List<ReportDefinition>>> getOrganizationTypeMap() {
		return organizationTypeMap;
	}
	
	/**
	 * Sets the organization type map.
	 *
	 * @param organizationTypeMap the organization type map
	 */
	public void setOrganizationTypeMap(Map<Organization, LinkedHashMap<String, List<ReportDefinition>>> organizationTypeMap) {
		this.organizationTypeMap = organizationTypeMap;
	}
	
	/**
	 * Gets the report definition map.
	 *
	 * @return the report definition map
	 */
	public Map<Integer, ReportDefinition> getReportDefinitionMap(){
		Map<Integer, ReportDefinition> reportDefinitionMap = new LinkedHashMap<Integer, ReportDefinition>();
		for(ReportDefinition reportDefinition : getReportDefinitions()){
			reportDefinitionMap.put(reportDefinition.getId(), reportDefinition);
		}
		return reportDefinitionMap;
	}
	
}
