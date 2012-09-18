package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import org.apache.commons.lang.StringUtils;

/**
 * @author: Biju Joseph
 */
public class AdverseEventDTO {
    private String source;
    private boolean rejected;

    private int id;
    private int _id;
    private String externalID;
    private String _externalID;
   
    private TermDTO term;
    private TermDTO _term;
    private String grade;
    private String _grade;
    private String startDate;
    private String _startDate;
    private String endDate;
    private String _endDate;

    private String verbatim;
    private String _verbatim;
    private String whySerious;
    private String _whySerious;
    private String attribution;
    private String _attribution;
    private String expected;
    private String _expected;


    public boolean isSame(AdverseEventDTO other){
        if(StringUtils.isNotEmpty(externalID)){
            if(StringUtils.equals(externalID, other.externalID)) return true;
        }
        return term.isSame(other.term) &&
                StringUtils.equals(grade, other.grade) &&
                StringUtils.equals(startDate, other.startDate) &&
                StringUtils.equals(endDate, other.endDate) &&
                StringUtils.equals(verbatim, other.verbatim) &&
                StringUtils.equals(whySerious, other.whySerious) &&
                StringUtils.equals(attribution, other.attribution);
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getWhySerious() {
        return whySerious;
    }

    public void setWhySerious(String whySerious) {
        this.whySerious = whySerious;
        this._whySerious = whySerious;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getOldWhySerious() {
        return _whySerious;
    }

    public String getOldAttribution() {
        return _attribution;
    }

    public String getOldExpected() {
        return _expected;
    }

    public String getOldStartDate() {
        return _startDate;
    }

    public String getOldEndDate() {
        return _endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdverseEventDTO)) return false;

        AdverseEventDTO that = (AdverseEventDTO) o;

        if (id != that.id) return false;
        if (!source.equals(that.source)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "AE [" +
                "source:'" + source + '\'' +
                ", id:" + id +
                ", externalID:'" + externalID + '\'' +
                ", term:" + term.getDisplayName() +
                ", grade:'" + grade + '\'' +
                ", startDate:'" + startDate + '\'' +
                ", endDate:'" + endDate + '\'' +
                ']';
    }
}
