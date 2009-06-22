package gov.nih.nci.cabig.caaers.domain.ajax;


/**
 * This class should be used whenever objects are displayed via ajax. This will help in performance.
 *
 * @author Biju Joseph
 */
public class AbstractAjaxableDomainObject implements AjaxableDomainObject {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
