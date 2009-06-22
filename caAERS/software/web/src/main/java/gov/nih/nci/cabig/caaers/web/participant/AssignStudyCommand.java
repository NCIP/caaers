package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ion C. Olaru
 */

public class AssignStudyCommand {
    protected final Log log = LogFactory.getLog(getClass());

    private Participant subject;
    private Study study;
    private List<ConcomitantMedication> listOfConcomitantMedications = new ArrayList<ConcomitantMedication>();

    public List<ConcomitantMedication> getListOfConcomitantMedications() {
		return listOfConcomitantMedications;
	}

	public void setListOfConcomitantMedications(
			List<ConcomitantMedication> listOfConcomitantMedications) {
		this.listOfConcomitantMedications = listOfConcomitantMedications;
	}

    public Participant getSubject() {
        return subject;
    }

    public void setSubject(Participant subject) {
        this.subject = subject;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }
}