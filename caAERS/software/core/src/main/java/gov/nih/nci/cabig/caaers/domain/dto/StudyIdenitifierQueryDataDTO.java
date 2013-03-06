/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

public class StudyIdenitifierQueryDataDTO {
	
	String nciInstituteCode;
	String systemName;
	String identifierType;
	Integer studyId;
	public String getNciInstituteCode() {
		return nciInstituteCode;
	}
	public void setNciInstituteCode(String nciInstituteCode) {
		this.nciInstituteCode = nciInstituteCode;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	public String getIdentifierType() {
		return identifierType;
	}
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}
	public Integer getStudyId() {
		return studyId;
	}
	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}

}
