package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;

/**
 * @author: Biju Joseph
 */
public class AdverseEventDTO {
    private String source;
    private String uUID;
    private String associatedTo;
    private String action;

    private int id;
    private int _id;
    private String externalID;
    private String _externalID;
    
    private TermDTO term;
    private TermDTO _term;
    private String grade;
    private String _grade;
    private String verbatim;
    private String _verbatim;
    private String hosptialization;
    private String _hosptialization;
    private String attribution;
    private String _attribution;
    private String expected;
    private String _expected;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getuUID() {
        return uUID;
    }

    public void setuUID(String uUID) {
        this.uUID = uUID;
    }

    public String getAssociatedTo() {
        return associatedTo;
    }

    public void setAssociatedTo(String associatedTo) {
        this.associatedTo = associatedTo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this._id = id;

    }

    public String getExternalID() {
        return externalID;
    }

    public void setExternalID(String externalID) {
        this.externalID = externalID;
        this._externalID = externalID;
    }

    public TermDTO getTerm() {
        return term;
    }

    public void setTerm(TermDTO term) {
        this.term = term;
        this._term = term;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
        this._grade = grade;
    }

    public String getVerbatim() {
        return verbatim;
    }

    public void setVerbatim(String verbatim) {
        this.verbatim = verbatim;
        this._verbatim = verbatim;
    }

    public String getHosptialization() {
        return hosptialization;
    }

    public void setHosptialization(String hosptialization) {
        this.hosptialization = hosptialization;
        this._hosptialization = hosptialization;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
        this._attribution = attribution;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
        this._expected = expected;
    }

    public int getOldId() {
        return _id;
    }

    public String getOldExternalID() {
        return _externalID;
    }

    public TermDTO getOldTerm() {
        return _term;
    }

    public String getOldGrade() {
        return _grade;
    }

    public String getOldVerbatim() {
        return _verbatim;
    }

    public String getOldHospitalization() {
        return _hosptialization;
    }

    public String getOldAttribution() {
        return _attribution;
    }

    public String getOldExpected() {
        return _expected;
    }
}
