package gov.nih.nci.cabig.caaers.dao.query;

/**
 * Will retrieve Notification objects
 * @author Biju Joseph.
 */
public class NotificationQuery extends AbstractQuery {

    public NotificationQuery() {
        super("select n from Notification n");
    }

    public void joinStudy(){
        join("n.study as s");
    }


    public void filterByStudyId(Integer id){
        joinStudy();
        andWhere("s.id = :sId");
        setParameter("sId", id);
    }

    public void filterByName(String name){
        andWhere("n.name = :aName");
        setParameter("aName", name);
    }

    public void ignoreNotificationId(Integer id){
        andWhere("n.id <> :nid");
        setParameter("nid", id);
    }


}
