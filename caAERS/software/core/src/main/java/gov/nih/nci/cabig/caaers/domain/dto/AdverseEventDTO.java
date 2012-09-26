package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class AdverseEventDTO {
    private String source;
    private boolean rejected;

    private int id;
    private String externalID;

    private TermDTO term;
    private String grade;
    private String startDate;
    private String endDate;

    private String verbatim;
    private String whySerious;
    private String attribution;
    private String expected;
    private String error;


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

    }

    public String getExternalID() {
        return externalID;
    }

    public void setExternalID(String externalID) {
        this.externalID = externalID;
    }

    public TermDTO getTerm() {
        return term;
    }

    public void setTerm(TermDTO term) {
        this.term = term;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getVerbatim() {
        return verbatim;
    }

    public void setVerbatim(String verbatim) {
        this.verbatim = verbatim;
    }

    public String getWhySerious() {
        return whySerious;
    }

    public void setWhySerious(String whySerious) {
        this.whySerious = whySerious;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
    
    public AdverseEventDTO clone(){
        AdverseEventDTO d =  new AdverseEventDTO();
        d.id = this.id;
        d.term = this.term.clone();
        d.source = this.source;
        d.externalID = this.externalID;
        d.grade = this.grade;
        d.startDate = this.startDate;
        d.endDate = this.endDate;
        d.verbatim = this.verbatim;
        d.whySerious = this.whySerious;
        d.attribution = this.attribution;
        return d;
    }
    
    public List<String> diff(AdverseEventDTO d){
        ArrayList<String> l = new ArrayList<String>();
        if(!d.term.isSame(term)) l.add("term");
        if(!StringUtils.equals(d.grade, grade)) l.add("grade");
        if(!StringUtils.equals(d.startDate, startDate)) l.add("startDate");
        if(!StringUtils.equals(d.endDate, endDate)) l.add("endDate");
        if(!StringUtils.equals(d.verbatim, verbatim)) l.add("verbatim");
        if(!StringUtils.equals(d.whySerious, whySerious)) l.add("whySerious");
        if(!StringUtils.equals(d.attribution, attribution)) l.add("attribution");
        return l;
    }
    
    public void clearFields(String... l){
        for(String f : l) {
            if(f.equals("grade")) setGrade(null);
            if(f.equals("startDate")) setStartDate(null);
            if(f.equals("endDate")) setEndDate(null);
            if(f.equals("verbatim")) setVerbatim(null);
            if(f.equals("whySerious")) setWhySerious(null);
            if(f.equals("attribution")) setAttribution(null);
            if(f.equals("term")) term.clearFields("code", "name", "otherSpecify");
        }
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

    public ReconciledAdverseEvent getReconciledAdverseEvent(ReconciliationAction action){
        ReconciledAdverseEvent ae = new ReconciledAdverseEvent();
        ae.setSystem(ReconciliationSystem.getByDisplayName(source));
        ae.setItemId(id);
        ae.setAction(action);
        if(StringUtils.isNotEmpty(grade)) ae.setGrade(Grade.getByShortName(grade));
        if(StringUtils.isNotEmpty(attribution)) ae.setAttribution(Attribution.getByDisplayName(attribution));
        if(StringUtils.isNotEmpty(externalID)) ae.setExternalId(externalID);
        if(term != null){
            if(StringUtils.isNotEmpty(term.getCode())) ae.setTermCode(term.getCode());
            if(StringUtils.isNotEmpty(term.getName())) ae.setTermName(term.getName());
            if(StringUtils.isNotEmpty(term.getOtherSpecify())) ae.setTermOtherSpecify(term.getOtherSpecify());
        }
        if(StringUtils.isNotEmpty(startDate)){
            try {ae.setStartDate(DateUtils.parseDate(startDate));} catch (Exception e) {}
        }
        if(StringUtils.isNotEmpty(endDate)){
            try {ae.setEndDate(DateUtils.parseDate(endDate));} catch (Exception e) {}
        }
        if(StringUtils.isNotEmpty(verbatim)) ae.setVerbatim(verbatim);
        if(StringUtils.isNotEmpty(externalID)) ae.setVerbatim(externalID);
        return ae;
    }

    public void mergeChanges(AdverseEvent ae) {
        //not term will not be touched.
        ae.setExternalId(externalID);
        ae.setGrade(isEmpty(grade) ? null : Grade.getByShortName(grade));
        try{ae.setStartDate(isEmpty(startDate) ? null : DateUtils.parseDate(startDate));}catch (Exception e){}
        try{ae.setEndDate(isEmpty(endDate) ? null : DateUtils.parseDate(endDate));}catch (Exception e){}
        ae.setDetailsForOther(isEmpty(verbatim) ? null : verbatim);
        ae.setAttributionSummary(isEmpty(attribution) ? null : Attribution.getByDisplayName(attribution));
        //TODO: why seriosu???
    }

    public static AdverseEventDTO create(ExternalAdverseEvent eae){
        AdverseEventDTO ae = new AdverseEventDTO();
        ae.setTerm(new TermDTO());
        ae.setId(eae.getId());
        ae.setExternalID(eae.getExternalId());
        ae.getTerm().setCode(eae.getAdverseEventTermCode());
        ae.getTerm().setName(eae.getAdverseEventTerm());
        ae.getTerm().setOtherSpecify(eae.getAdverseEventTermOtherValue());
        if(eae.getGrade() != null) ae.setGrade(eae.getGrade().getShortName());
        if(eae.getStartDate() != null) ae.setStartDate(DateUtils.formatDate(eae.getStartDate()));
        if(eae.getEndDate() != null) ae.setEndDate(DateUtils.formatDate(eae.getEndDate()));
        ae.setVerbatim(eae.getVerbatim());
        ae.setWhySerious(eae.getHowSerious());
        ae.setAttribution(eae.getAttribution());
        return ae;
    }

    public static AdverseEventDTO create(AdverseEvent iae){
        AdverseEventDTO ae = new AdverseEventDTO();
        ae.setTerm(new TermDTO());
        ae.setId(iae.getId());
        ae.setExternalID(iae.getExternalId());
        if(iae.getAdverseEventCtcTerm() != null){
            ae.getTerm().setId(iae.getAdverseEventCtcTerm().getTerm().getId());
            ae.getTerm().setCode(iae.getAdverseEventCtcTerm().getTerm().getCtepCode());
            ae.getTerm().setName(iae.getAdverseEventCtcTerm().getTerm().getCtepTerm());
            ae.getTerm().setName(iae.getLowLevelTerm() != null ? iae.getLowLevelTerm().getMeddraTerm() : null);
        }else if(iae.getAdverseEventMeddraLowLevelTerm() != null){
            ae.getTerm().setId(iae.getAdverseEventMeddraLowLevelTerm().getTerm().getId());
            ae.getTerm().setCode(iae.getAdverseEventMeddraLowLevelTerm().getTerm().getMeddraCode());
            ae.getTerm().setName(iae.getAdverseEventMeddraLowLevelTerm().getTerm().getMeddraTerm());
        }

        if(iae.getGrade() != null) ae.setGrade(iae.getGrade().getShortName());
        if(iae.getStartDate() != null) ae.setStartDate(DateUtils.formatDate(iae.getStartDate()));
        if(iae.getEndDate() != null) ae.setEndDate(DateUtils.formatDate(iae.getEndDate()));
        ae.setVerbatim(iae.getDetailsForOther());
        if(iae.getOutcomes() != null){
            StringBuilder outcomeBuilder = new StringBuilder();
            for(Outcome o: iae.getOutcomes()){
                if(outcomeBuilder.length() > 0) outcomeBuilder.append(",");
                outcomeBuilder.append(o.getOutcomeType().getDisplayName());
            }
            ae.setWhySerious(outcomeBuilder.toString());
        }

        ae.setAttribution(iae.getAttributionSummary() != null ?  iae.getAttributionSummary().getDisplayName() : null);
        return ae;
    }

    public static boolean isEmpty(String s){
        if(StringUtils.isEmpty(s)) return true;
        if(StringUtils.equals("--", s)) return true;
        return false;
    }
}
