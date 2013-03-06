/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.CtcCategoryType;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.CtcGradeType;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.CtcTermType;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.CtcVersionType;

import java.util.ArrayList;
import java.util.List;

public class CtcConverter {

	public Ctc convert(CtcVersionType ctcVersionType){
		Ctc ctc = new Ctc();
		ctc.setName(ctcVersionType.getName());
		ctc.getCategories().addAll(convertCtcCategories(ctcVersionType.getCtcCategory()));
		return ctc;
	}
	
	public List<CtcCategory> convertCtcCategories(List<CtcCategoryType> ctcCategoryTypes){
		List<CtcCategory> ctcCategories = new ArrayList<CtcCategory>();
		for(CtcCategoryType ctcCategoryType : ctcCategoryTypes){
			ctcCategories.add(convert(ctcCategoryType));
		}
		return ctcCategories;
	}
	
	public CtcCategory convert(CtcCategoryType ctcCategoryType){
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setName(ctcCategoryType.getName());
		ctcCategory.getTerms().addAll(convertCtcTerms(ctcCategoryType.getCtcTerm()));
		return ctcCategory;
	}
	
	public List<CtcTerm> convertCtcTerms(List<CtcTermType> ctcTermTypes){
		List<CtcTerm> ctcTerms = new ArrayList<CtcTerm>();
		for(CtcTermType ctcTermType : ctcTermTypes){
			ctcTerms.add(convert(ctcTermType));
		}
		return ctcTerms;
	}
	
	public CtcTerm convert(CtcTermType ctcTermType){
		CtcTerm ctcTerm = new CtcTerm();
		ctcTerm.setCtepCode(ctcTermType.getCtepCode());
		ctcTerm.setCtepTerm(ctcTermType.getCtepTerm());
		ctcTerm.setDefinition(ctcTermType.getDefinition());
		if(ctcTermType.isOtherRequired() != null){
			ctcTerm.setOtherRequired(ctcTermType.isOtherRequired());
		}
		ctcTerm.setSelect(ctcTermType.getSelectAE());
		ctcTerm.setTerm(ctcTermType.getTerm());
		ctcTerm.getContextualGrades().addAll(convertCtcGrades(ctcTermType.getCtcGrade()));
		return ctcTerm;
	}
	
	public List<CtcGrade> convertCtcGrades(List<CtcGradeType> ctcGradeTypes){
		List<CtcGrade> ctcGrades = new ArrayList<CtcGrade>();
		for(CtcGradeType ctcGradeType : ctcGradeTypes){
			ctcGrades.add(convert(ctcGradeType));
		}
		return ctcGrades;
	}
	
	public CtcGrade convert(CtcGradeType ctcGradeType){
		CtcGrade ctcGrade = new CtcGrade();
		ctcGrade.setGrade(Grade.getByCode(ctcGradeType.getGrade()));
		ctcGrade.setText(ctcGradeType.getText());
		return ctcGrade;
	}
	
}
