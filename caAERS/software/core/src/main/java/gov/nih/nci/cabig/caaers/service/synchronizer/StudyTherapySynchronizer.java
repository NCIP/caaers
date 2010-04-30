package gov.nih.nci.cabig.caaers.service.synchronizer;

import org.apache.commons.collections.CollectionUtils;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

public class StudyTherapySynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.Study>{
	
	/**
	 *	If none of the below given elements are provided in the XML, then StudyTherapies list will be null.
		In this case do not do anything.
		If one or more of the below given elements are provided in the XML, 
		In this case the existing StudyTherapies on the study will be replaced with the ones provided.
		
        <xs:element name="drugAdministrationTherapyType" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
        <xs:element name="deviceTherapyType" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
        <xs:element name="radiationTherapyType" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
        <xs:element name="surgeryTherapyType" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
        <xs:element name="behavioralTherapyType" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
        <xs:element name="biologicalTherapyType" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
        <xs:element name="geneticTherapyType" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
        <xs:element name="dietarySupplementTherapyType" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
        <xs:element name="otherTherapyType" type="xs:boolean"  minOccurs="0" maxOccurs="1"/>
	 */
	public void migrate(Study dbStudy, Study xmlStudy,DomainObjectImportOutcome<Study> outcome) {

		if(CollectionUtils.isEmpty(xmlStudy.getStudyTherapies())){
			return;
		}

		dbStudy.getStudyTherapies().clear();
		for(StudyTherapy therapy : xmlStudy.getStudyTherapies()){
			dbStudy.addStudyTherapy(therapy.getStudyTherapyType());
		}
	}
}
