package gov.nih.nci.cabig.caaers.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Temporary domain object to hold values of static data loaded from CADsr
 * 
 * @author Kulasekaran
 * 
 */
// TODO Remove this class when a service for loading static data from CADsr exists
public class Lov implements Serializable {

    private String code;

    private String desc;

    List<Lov> data = new ArrayList();

    public Lov() {
    }

    public Lov(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public void addData(String code, String desc) {
        data.add(new Lov(code, desc));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Lov> getData() {
        return data;
    }

    public void setData(List<Lov> data) {
        this.data = data;
    }
}