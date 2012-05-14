package gov.nih.nci.cabig.caaers.dao.query;

/**
 * @author Ion C. Olaru
 *         Date: 5/14/12 -12:23 PM
 */
public class AgentQuery extends AbstractQuery {

    public AgentQuery() {
        super("SELECT a FROM Agent a");
    }

    public void filterByRetiredStatus(Boolean status) {
        super.filterByRetiredStatus("a", status);
    }

    public void filterByName(String name) {
        andWhere("lower(a.name) LIKE :name");
        setParameter("name", "%" + name.toLowerCase() + "%");
    }

    public void filterByNSC(String nsc) {
        andWhere("lower(a.nscNumber) LIKE :nsc");
        setParameter("nsc", "%" + nsc.toLowerCase() + "%");
    }

    public void filterByNameOrNSC(String name, String nsc) {
        andWhere("(lower(a.name) LIKE :name OR lower(a.nscNumber) LIKE :nsc)");
        setParameter("name", "%" + name.toLowerCase() + "%");
        setParameter("nsc", "%" + nsc.toLowerCase() + "%");
    }

}
