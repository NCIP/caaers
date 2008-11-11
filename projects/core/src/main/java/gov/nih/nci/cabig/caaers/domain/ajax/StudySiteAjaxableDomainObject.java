package gov.nih.nci.cabig.caaers.domain.ajax;


/**
 *
 */
public class StudySiteAjaxableDomainObject extends AbstractAjaxableDomainObject {


    private String name;
    private String nciInstituteCode;

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
}