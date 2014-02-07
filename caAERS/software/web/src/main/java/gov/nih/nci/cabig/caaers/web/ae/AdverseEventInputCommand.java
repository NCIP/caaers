/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

/**
 * @author Krikor Krumlian
 */
public interface AdverseEventInputCommand {
	
	Integer ZERO = new Integer(0);
	
    StudyParticipantAssignment getAssignment();

    Participant getParticipant();

    Study getStudy();
    
    AdverseEventReportingPeriod getAdverseEventReportingPeriod();

    void save();

    public String getTreatmentDescriptionType();

    public void setTreatmentDescriptionType(String type);

    public boolean getIgnoreCompletedStudy();
    
    public boolean getWorkflowEnabled();
    
    public String getCommandType();
    
    public String fetchLoggedInUserEmail();
}
