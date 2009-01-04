package gov.nih.nci.cabig.caaers.domain.ajax;



/**
 *
 */
public class StudySiteAjaxableDomainObject extends AbstractAjaxableDomainObject {


    private String name;
    private String nciInstituteCode;
    private Integer studyId;
    private String type;

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStudyId() {
		return studyId;
	}

	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}

	public String getNciInstituteCode() {
		return nciInstituteCode;
	}

	public void setNciInstituteCode(String nciInstituteCode) {
		this.nciInstituteCode = nciInstituteCode;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean equals(Object arg0) {
        if (arg0 == null) {
            return false;
        }

        if (!(arg0 instanceof StudySiteAjaxableDomainObject)) {
            return false;
        }

        StudySiteAjaxableDomainObject other = (StudySiteAjaxableDomainObject) arg0;

        if (this.getNciInstituteCode().equals(other.getNciInstituteCode())) {
            return true;
        }

        return false;
    }
    
}