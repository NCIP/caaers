package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class MigrateStudiesCommand {

    private String fileName;

    private List<Study> studies = new ArrayList<Study>();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

}
