package gov.nih.nci.cabig.caaers.validation.annotation;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;

public class UniqueIdentifierForStudyValidator implements Validator<UniqueIdentifierForStudy>{
	private String message;
	private StudyDao studyDao;

	public boolean validate(Object value) {
		if(!(value instanceof Identifier))	return true;
		Identifier id = (Identifier) value;
		StudyQuery query = new StudyQuery();
		query.filterByIdentifierValueExactMatch(id.getValue());
		query.filterByIdentifierType(id.getType());
		List<Study> studies = studyDao.find(query);
		for(Study study : studies){
			for(Identifier eId : study.getIdentifiersLazy()){
			 if(eId.getValue().equals(id.getValue()) &&  eId.getType().equals(id.getType()) && 
					 !ObjectUtils.equals(id.getId(),eId.getId())){
				 return false;
			 }
			}
		}
		return true;
	}
	
	public void initialize(UniqueIdentifierForStudy parameters) {
		 message = parameters.message();
	}

	public String message() {
		return message;
	}
	
	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
}
