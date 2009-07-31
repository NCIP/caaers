package gov.nih.nci.cabig.caaers.tools.editors;

import gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * This will Bind the StudyDisease
 * 
 * @author Biju Joseph
 *
 */
public class AbstractStudyDiseaseEditor extends PropertyEditorSupport {
	List<? extends AbstractStudyDisease<? extends DomainObject>> diseases;
	public AbstractStudyDiseaseEditor(List<? extends AbstractStudyDisease<? extends DomainObject>> diseases) {
		this.diseases = diseases;
	}
	
	@Override
	public String getAsText() {
		DomainObject value = (DomainObject) getValue();
		if(value != null){
			return String.valueOf(value.getId());
		}
		return null;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		for(AbstractStudyDisease<? extends DomainObject> disease : diseases){
			if(StringUtils.equals(text, disease.getId().toString())){
				setValue(disease);
				return;
			}
		}
		setValue(null);
	}
}
