/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import org.apache.commons.lang.StringUtils;

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

    public void clearFields(String... fields){
        for(String f : fields) {
            if(StringUtils.equals(f, "code")) setCode(null);
            if(StringUtils.equals(f, "name")) setName(null);
            if(StringUtils.equals(f, "otherSpecify")) setOtherSpecify(null);
        }
    }
    public boolean isSame(TermDTO other){
        return StringUtils.equals(this.code, other.code) && StringUtils.equals(this.otherSpecify, other.otherSpecify);
    }
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

    public String getDisplayName(){
        if(StringUtils.isEmpty(otherSpecify)) return name;
        return name + ", " + otherSpecify;
    }
    
    public TermDTO clone(){
        TermDTO t = new TermDTO();
        t.id = this.id;
        t.name = this.name;
        t.code = this.code;
        t.otherSpecify = this.otherSpecify;
                
        return t;
    }
    
}
