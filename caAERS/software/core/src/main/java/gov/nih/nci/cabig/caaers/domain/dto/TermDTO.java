package gov.nih.nci.cabig.caaers.domain.dto;

/**
 * @author: Biju Joseph
 */
public class TermDTO {
    private Integer id;
    private Integer _id;
    
    private String code;
    private String _code;
    
    private String name;
    private String _name;
    
    private String otherSpecify;
    private String _otherSpecify;

    public Integer getOldId() {
        return _id;
    }

    public String getOldCode() {
        return _code;
    }

    public String getOldName() {
        return _name;
    }

    public String getOldOtherSpecify() {
        return _otherSpecify;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        this._id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        this._code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this._name = name;
    }

    public String getOtherSpecify() {
        return otherSpecify;
    }

    public void setOtherSpecify(String otherSpecify) {
        this.otherSpecify = otherSpecify;
        this._otherSpecify = otherSpecify;
    }

}
