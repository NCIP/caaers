package gov.nih.nci.cabig.caaers.rules.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class AbstractDomainObject implements DomainObject {

    private Integer id;

    @Id
    @GeneratedValue(generator = "id-generator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
