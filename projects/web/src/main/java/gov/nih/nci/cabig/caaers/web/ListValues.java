package gov.nih.nci.cabig.caaers.web;

import java.util.ArrayList;
import java.util.List;

public class ListValues {

    private String code;

    private String desc;

    public ListValues() {
        // TODO Auto-generated constructor stub
    }

    public ListValues(final String code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(final String desc) {
        this.desc = desc;
    }

    public List<ListValues> getStudySearchType() {
        List<ListValues> col = new ArrayList<ListValues>();
        ListValues lov1 = new ListValues("st", "Short Title");
        ListValues lov2 = new ListValues("idtf", "Identifier");
        // ListValues lov2 = new ListValues("lt", "Long Title");
        // ListValues lov4 = new ListValues("d", "Description");
        // ListValues lov5 = new ListValues("psc", "Primary Sponsor Code");
        // ListValues lov6 = new ListValues("pc", "Phase Code");

        col.add(lov1);
        col.add(lov2);

        return col;
    }

    public List<ListValues> getParticipantSearchType() {
        List<ListValues> col = new ArrayList<ListValues>();
        ListValues lov1 = new ListValues("fn", "First Name");
        ListValues lov2 = new ListValues("ln", "Last Name");
        ListValues lov3 = new ListValues("idtf", "Identifier");
        // ListValues lov3 = new ListValues("g", "gender");
        // ListValues lov4 = new ListValues("r", "race");
        // ListValues lov5 = new ListValues("e", "ethnicity");
        col.add(lov1);
        col.add(lov2);
        col.add(lov3);
        // col.add(lov4);
        // col.add(lov5);
        return col;
    }

    public List<ListValues> getParticipantGender() {
        List<ListValues> col = new ArrayList<ListValues>();
        ListValues lov1 = new ListValues("", "Please select");
        ListValues lov2 = new ListValues("Male", "Male");
        ListValues lov3 = new ListValues("Female", "Female");
        //ListValues lov4 = new ListValues("Not Reported", "Not Reported");
        ListValues lov5 = new ListValues("Unknown", "Unknown");
        col.add(lov1);
        col.add(lov2);
        col.add(lov3);
        //col.add(lov4);
        col.add(lov5);
        return col;
    }

    public List<ListValues> getParticipantEthnicity() {
        List<ListValues> col = new ArrayList<ListValues>();
        ListValues lov1 = new ListValues("", "Please select");
        ListValues lov2 = new ListValues("Hispanic or Latino", "Hispanic or Latino");
        ListValues lov3 = new ListValues("Not Hispanic or Latino", "Not Hispanic or Latino");
        ListValues lov4 = new ListValues("Not Reported", "Not Reported");
        ListValues lov5 = new ListValues("Unknown", "Unknown");
        col.add(lov1);
        col.add(lov2);
        col.add(lov3);
        col.add(lov4);
        col.add(lov5);
        return col;
    }

    public List<ListValues> getParticipantRace() {
        List<ListValues> col = new ArrayList<ListValues>();
        ListValues lov1 = new ListValues("", "Please select");
        ListValues lov2 = new ListValues("Asian", "Asian");
        //ListValues lov3 = new ListValues("Hispanic or Latino", "Hispanic or Latino");
        ListValues lov4 = new ListValues("White", "White");
        ListValues lov5 = new ListValues("Black or African American", "Black or African American");
        ListValues lov6 = new ListValues("American Indian or Alaska Native", "American Indian or Alaska Native");
        ListValues lov7 = new ListValues("Native Hawaiian or Other Pacific Islander", "Native Hawaiian or Other Pacific Islander");
        ListValues lov8 = new ListValues("Not Reported", "Not Reported");
        //ListValues lov9 = new ListValues("More than one race", "More than one race");
        //ListValues lov10 = new ListValues("Other", "Other");
        ListValues lov11 = new ListValues("Unknown", "Unknown");
        col.add(lov1);
        col.add(lov2);
        //col.add(lov3);
        col.add(lov4);
        col.add(lov5);
        col.add(lov6);
        col.add(lov7);
        col.add(lov8);
        //col.add(lov9);
        //col.add(lov10);
        col.add(lov11);        
        return col;
    }

    @Deprecated
    /** Use the 'participantIdentifiersType' in applicationContext-configProperties.xml */
    public List<ListValues> getParticipantIdentifierType() {
        List<ListValues> col = new ArrayList<ListValues>();
        ListValues lov1 = new ListValues("Medical Record Number", "Medical Record Number");
        ListValues lov2 = new ListValues("Other", "Other");
        col.add(lov1);
        col.add(lov2);

        return col;
    }

}
